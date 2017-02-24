package com.sot.ecommerce.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.persistance.UmTbCntryMstr;
import com.sbsc.fos.persistance.UmTbUser;
import com.sbsc.fos.product.web.form.ProductBasicForm;
import com.sbsc.fos.promotion.commons.PromotionVO;
import com.sbsc.fos.store.web.handler.StoreHomeHandler;
import com.sbsc.fos.store.web.vo.Category;
import com.sbsc.fos.store.web.vo.Product;
import com.sbsc.fos.um.common.UserAuthVO;
import com.sbsc.fos.um.common.UserInfoVO;
import com.sbsc.fos.um.service.UserService;
import com.sbsc.fos.um.web.form.ChangePasswordForm;
import com.sbsc.fos.um.web.form.DeliveryAddressesForm;
import com.sbsc.fos.um.web.form.ForgetPasswordForm;
import com.sbsc.fos.um.web.form.ProfileForm;
import com.sbsc.fos.um.web.form.UserSignInForm;
import com.sbsc.fos.um.web.form.UserSignUpForm;
import com.sbsc.fos.um.web.form.validator.ForgetPasswordSubmitValidator;
import com.sbsc.fos.um.web.form.validator.ForgetPasswordValidator;
import com.sbsc.fos.um.web.form.validator.SignInValidator;
import com.sbsc.fos.um.web.form.validator.SignUpValidator;
import com.sbsc.fos.um.web.handler.AdminDashboardHandler;
import com.sbsc.fos.um.web.handler.LoginHandler;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.EncryptionAndDecryption;
import com.sbsc.fos.utils.LoginCommonUtil;
import com.sbsc.fos.utils.UniqueIdGenerator;
import com.sbsc.fos.wishlist.web.form.RedirectForm;

/**
 * Handles requests for the application login page.
 * 
 * @author simanchal.patra
 */

@Controller
public class LoginController {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(LoginController.class);

	@Autowired
	private LoginHandler loginHandler;

	@Autowired
	private AdminDashboardHandler adminHandler;

	@Autowired
	private SignUpValidator signUpValidator;

	@Autowired
	UserService userService;

	@Autowired
	private SignInValidator signInValidator;

	@Autowired
	private ForgetPasswordValidator forgetPasswordValidator;

	@Autowired
	private ForgetPasswordSubmitValidator forgetPasswordSubmitValidator;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	UserSignInForm userSignInForm;

	@Autowired
	UserSignUpForm userSignUpForm;

	@Autowired
	ChangePasswordForm changePasswordForm;

	@Autowired
	ProfileForm profileForm;

	@Autowired
	DeliveryAddressesForm deliveryAddressForm;

	@Autowired
	private StoreHomeHandler storeHomeHandler;

	private UmTbUser userInfo;

	private List<UmTbCntryMstr> countryList;

	/**
	 * Shows the merchant admin login Page.
	 */

	@RequestMapping(value = "admin/login.htm", method = RequestMethod.GET)
	public ModelAndView login(
			@ModelAttribute("userSignInForm") UserSignInForm userSignInForm,BindingResult bindingResult,
			Locale locale, Model model, ModelMap modelMap,
			HttpServletRequest request,final RedirectAttributes redirectAttributes) {

		HttpSession session = request.getSession(false);

		String loogedIn = (String) request.getSession()
				.getAttribute("loggedIn");
		modelMap.put(BindingResult.MODEL_KEY_PREFIX + "userSignInForm",
				modelMap.get("bindingResult"));

		
		LoginCommonUtil loginutil = new LoginCommonUtil();
		HashMap userLogin = loginutil.isRemember(request);
		String userEmail = (String) userLogin.get("userEmail");
		String userPassword = (String) userLogin.get("userPassword");

		UmTbUser user=loginutil.checkifUserRemebered(userEmail,userPassword,userService);
		
		if(user!=null)
		{
			
			try
			{
			return redirectToAdminDashboard(userSignInForm, bindingResult,
					request, redirectAttributes, session, loginutil, userEmail,
					userPassword);
		}
		 catch (BusinessFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		}

		
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("userPassword", userPassword);
		model.addAttribute("userSignInForm", userSignInForm);

		return new ModelAndView("/usermanagement/login");
	}


	
	
	
	@RequestMapping(value = "admin/logout.htm", method = RequestMethod.GET)
	public ModelAndView logout(@ModelAttribute("userSignInForm") UserSignInForm userSignInForm,BindingResult bindingResult,
			Locale locale, Model model, HttpServletRequest request) {
		
		    request.getSession().invalidate();
		    LoginCommonUtil loginutil = new LoginCommonUtil();
			
		    loginutil.removeCredentials(request);
		    
			String successMessage = messageSource.getMessage(
					"logout.successful", null, "Logged out successfully",
					request.getLocale());
			model.addAttribute("loggedout", successMessage);
		
		return new ModelAndView("/usermanagement/login");
	}
	
	
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "admin/dashboard.htm", method = RequestMethod.GET)
	public ModelAndView dashboard(Model model, HttpServletRequest request) {
		SessionInfo sessioninfo = CommonUtil.getSessionInfo(request);
		List<ProductBasicForm> productBasicFormlist = new ArrayList<ProductBasicForm>();
		List<OrdersVO> orderlist = null;
		try {
			orderlist = adminHandler.todayOrderCount(sessioninfo.getStoreId());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productBasicFormlist = adminHandler.listRecentlyAddedProduct(
				productBasicFormlist, sessioninfo.getStoreId());
		List<ProductBasicForm> outofstock = adminHandler
				.productoutofstock(sessioninfo.getStoreId());
		List<PromotionVO> promotionlist = adminHandler
				.todayPramotions(sessioninfo.getStoreId());
		System.out.println("promotionlist:::::" + promotionlist.size());
		model.addAttribute("promotionlist", promotionlist);
		model.addAttribute("orderlist", orderlist);
		model.addAttribute("productList", productBasicFormlist);
		model.addAttribute("outofstock", outofstock);
		return new ModelAndView("/usermanagement/admindashboard");
	}

	@RequestMapping(value = "admin/admindashboard.htm")
	public ModelAndView signIn(
			@ModelAttribute("userSignInForm") UserSignInForm userSignInForm,
			BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {

		modelMap.put(BindingResult.MODEL_KEY_PREFIX + "userSignInForm",
				modelMap.get("bindingResult"));
		
		
		signInValidator.validate(userSignInForm, bindingResult);

		if (bindingResult.hasErrors()) {

			logger.error("An user is signing-in, has validation errors."
					+ userSignInForm);

			redirectAttributes.addFlashAttribute("userSignInForm",
					userSignInForm);
			redirectAttributes
					.addFlashAttribute("bindingResult", bindingResult);
			return new ModelAndView("redirect:/admin/login.htm");

		} else {

			try {

				LoginCommonUtil loginutil = new LoginCommonUtil();
				UserAuthVO signInfoVo = new UserAuthVO();
				signInfoVo = storeSignInFormInfoToVO(userSignInForm, signInfoVo);
				loginHandler.signIn(signInfoVo);

				getandSetMenuList(request.getSession(false), signInfoVo,
						loginutil);

				getUserInfo(request);
				storeUserNameInSession(request, userInfo);
				loginutil.setUserTheme(request, signInfoVo);

				loginutil.rememberMe(userSignInForm, response);
				loginutil = null;
				redirectAttributes.addFlashAttribute("userSignInForm",
						userSignInForm);
				redirectAttributes.addFlashAttribute("bindingResult",
						bindingResult);
				return new ModelAndView("redirect:/admin/dashboard.htm");

			} catch (BusinessFailureException bfe) {

				logger.error("An user is signing-Up, has BusinessFailureException."
						+ userSignInForm);
				bindingResult.rejectValue("errorMessage",
						String.valueOf(bfe.getErrorID()));
				redirectAttributes.addFlashAttribute("userSignInForm",
						userSignInForm);
				redirectAttributes.addFlashAttribute("bindingResult",
						bindingResult);
				return new ModelAndView("redirect:/admin/login.htm");

			} catch (GenericFailureException gfe) {
				logger.error("An user is signing-Up, has GenericFailureException."
						+ userSignInForm);
				throw gfe;
			} catch (NoSuchAlgorithmException e) {
				logger.error("An user is signing-Up, has exception in decryption :NoSuchAlgorithmException."
						+ userSignInForm);
				return new ModelAndView("redirect:login.htm");

			} catch (NoSuchPaddingException e) {
				logger.error("An user is signing-Up, has exception in decryption :NoSuchPaddingException."
						+ userSignInForm);
				return new ModelAndView("redirect:/admin/login.htm");

			}
		}
	}

	private void storeUserNameInSession(HttpServletRequest request,
			UmTbUser userInfo) {
		// TODO Auto-generated method stub// TODO Auto-generated method stub

		request.getSession().setAttribute("userFirstName",
				userInfo.getVcFrstNm());
		request.getSession()
				.setAttribute("userLastName", userInfo.getVcLstNm());
	}

	/**
	 * Shows the buyer login Page.
	 */

	@RequestMapping(value = "store/storelogin.htm")
	public ModelAndView storeLogin(
			@ModelAttribute("userSignInForm") UserSignInForm userSignInForm,
			@ModelAttribute("userSignUpForm") UserSignUpForm userSignUpForm,
			@ModelAttribute("redirectForm") RedirectForm redirectForm,
			ModelMap modelMap, Locale locale, Model model,
			BindingResult bindingResult, HttpServletRequest request,HttpServletResponse response,
			HttpSession session,final RedirectAttributes redirectAttributes) {

		modelMap.put(BindingResult.MODEL_KEY_PREFIX + "userSignInForm",
				modelMap.get("bindingResult"));

		modelMap.put(BindingResult.MODEL_KEY_PREFIX + "userSignUpForm",
				modelMap.get("bindingResult"));

		if (bindingResult.hasErrors()) {

			logger.error("An user is signing-in, has validation errors."
					+ userSignInForm);

			model.addAttribute("userSignInForm", userSignInForm);
			model.addAttribute("userSignUpForm", userSignUpForm);

			return new ModelAndView("/usermanagement/buyerlogin");

		} else {

			model.addAttribute("userSignInForm", userSignInForm);
			model.addAttribute("userSignUpForm", userSignUpForm);
			model.addAttribute("redirectForm", redirectForm);

			getCountryList();
			model.addAttribute("countryList", countryList);
			LoginCommonUtil loginutil = new LoginCommonUtil();
			HashMap userLogin = loginutil.isRemember(request);
			String userEmail = (String) userLogin.get("userEmail");
			String userPassword = (String) userLogin.get("userPassword");

			

			UmTbUser user=loginutil.checkifUserRemebered(userEmail,userPassword,userService);
			
			if(user!=null)
			{
			
				try
				{
				UserAuthVO signInfoVo = new UserAuthVO();
				signInfoVo.setUserEmail(userEmail);
				signInfoVo.setUserPassword(userPassword);
				signInfoVo.setRolename("Buyer");
				signInfoVo.setStoreid(1);	
				loginHandler.signIn(signInfoVo);				
				getandSetMenuList(session, signInfoVo, loginutil);
				getUserInfo(request);
				storeUserNameInSession(request, userInfo);
				getCountryList();
				model.addAttribute("countryList", countryList);
				profileForm = loginutil.setUserDataInAdminProfileForm(
						profileForm, userInfo);
				model.addAttribute("buyerProfileForm", profileForm);

				String prodId = redirectForm.getProdId();
				// String url=userSignInForm.getUrl();

				if (null!=prodId && (!prodId.isEmpty())){

					redirectAttributes.addFlashAttribute("redirectForm",
							redirectForm);
					return new ModelAndView("redirect:" + redirectForm.getUrl());
				}

				loginutil.rememberMe(userSignInForm, response);
				loginutil = null;
				
				}
				catch(BusinessFailureException ex)
				{
					System.out.println("exception");
					
				} catch (GenericFailureException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				return new ModelAndView("redirect:/store/storehome.htm");
				
				}
			else
			{
				
			model.addAttribute("userEmail", userEmail);
			model.addAttribute("userPassword", userPassword);

			return new ModelAndView("/usermanagement/buyerlogin");
			}
			}
	}

	/**
	 * this will take you to the logout Page.
	 */

	@RequestMapping(value = "store/storelogout.htm", method = RequestMethod.GET)
	public ModelAndView storeLogout(Locale locale, Model model,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		HttpSession session = request.getSession(false);
		String successMessage = "";
		if (session == null) {
			successMessage = messageSource.getMessage("logout.session.expire",
					null, "Session Expired", request.getLocale());
		} else {
			System.out.println("Befor logout session created id ::"
					+ session.getId() + " created at ::"
					+ session.getCreationTime());
			session.invalidate();
			session = null;
			userInfo = null;
			LoginCommonUtil  loginutil=new LoginCommonUtil();
			 loginutil.removeCredentials(request);
			successMessage = messageSource.getMessage("logout.successful",
					null, "Logged out successfully", request.getLocale());
		}

		redirectAttributes.addFlashAttribute("message", successMessage);
		return new ModelAndView("redirect:/store/storelogin.htm");
	}

	/**
	 * Simply selects the buyer home view to render by returning its name.
	 * 
	 * @throws Exception
	 */

	@RequestMapping(value = "store/storesignIn.htm")
	public ModelAndView marketplaceHome(
			@ModelAttribute("redirectForm") RedirectForm redirectForm,
			@ModelAttribute("userSignInForm") UserSignInForm userSignInForm,
			BindingResult bindingResult, ModelMap modelMap,
			final RedirectAttributes redirectAttributes, Locale locale,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		modelMap.put(BindingResult.MODEL_KEY_PREFIX + "userSignInForm",
				modelMap.get("bindingResult"));
		signInValidator.validate(userSignInForm, bindingResult);

		if (bindingResult.hasErrors()) {

			logger.error("An user is signing-in, has validation errors."
					+ userSignInForm);

			redirectAttributes.addFlashAttribute("userSignInForm",
					userSignInForm);
			redirectAttributes
					.addFlashAttribute("bindingResult", bindingResult);
			return new ModelAndView("redirect:/store/storelogin.htm");

		} else {

			try {

				LoginCommonUtil loginutil = new LoginCommonUtil();
				UserAuthVO signInfoVo = new UserAuthVO();
				signInfoVo = storeSignInFormInfoToVO(userSignInForm, signInfoVo);
				loginHandler.signIn(signInfoVo);
				HttpSession session = request.getSession(true);
				getandSetMenuList(session, signInfoVo, loginutil);
				getUserInfo(request);
				storeUserNameInSession(request, userInfo);
				getCountryList();
				model.addAttribute("countryList", countryList);
				profileForm = loginutil.setUserDataInAdminProfileForm(
						profileForm, userInfo);
				model.addAttribute("buyerProfileForm", profileForm);

				String prodId = redirectForm.getProdId();
				// String url=userSignInForm.getUrl();

				if (null!=prodId && (!prodId.isEmpty())) {

					redirectAttributes.addFlashAttribute("redirectForm",
							redirectForm);
					return new ModelAndView("redirect:" + redirectForm.getUrl());
				}

				loginutil.rememberMe(userSignInForm, response);
				loginutil = null;
				return new ModelAndView("redirect:/store/storehome.htm");

			} catch (BusinessFailureException bfe) {

				logger.error("An user is signing-Up, has BusinessFailureException."
						+ userSignInForm);
				bindingResult.rejectValue("errorMessage",
						String.valueOf(bfe.getErrorID()));
				redirectAttributes.addFlashAttribute("userSignInForm",
						userSignInForm);
				redirectAttributes.addFlashAttribute("bindingResult",
						bindingResult);

				String errorId = String.valueOf(bfe.getErrorID());
				redirectAttributes.addFlashAttribute("errorMessage",
						messageSource.getMessage(errorId, null,
								"emailId Password Mismatch",
								request.getLocale()));
				return new ModelAndView("redirect:/store/storelogin.htm");

			} catch (GenericFailureException gfe) {
				logger.error("An user is signing-Up, has GenericFailureException."
						+ userSignInForm);
				throw gfe;
			} catch (NoSuchAlgorithmException e) {
				logger.error("An user is signing-Up, has exception in decryption :NoSuchAlgorithmException."
						+ userSignInForm);
				redirectAttributes.addFlashAttribute("userSignInForm",
						userSignInForm);
				redirectAttributes.addFlashAttribute("bindingResult",
						bindingResult);
				return new ModelAndView("redirect:/store/storelogin.htm");

			} catch (NoSuchPaddingException e) {
				logger.error("An user is signing-Up, has exception in decryption :NoSuchPaddingException."
						+ userSignInForm);
				redirectAttributes.addFlashAttribute("userSignInForm",
						userSignInForm);
				redirectAttributes.addFlashAttribute("bindingResult",
						bindingResult);
				return new ModelAndView("redirect:/store/storelogin.htm");

			}
		}

	}

	/**
	 * Simply selects the home view after successful signup to render by
	 * returning its name.
	 */

	@RequestMapping(value = "store/buyersignup.htm", method = RequestMethod.POST)
	public ModelAndView signUp(
			@ModelAttribute("redirectForm") RedirectForm redirectForm,
			@ModelAttribute("userSignInForm") UserSignInForm userSignInForm,
			@ModelAttribute("userSignUpForm") UserSignUpForm userSignUpForm,
			Model model, BindingResult bindingResult,
			HttpServletRequest request, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		logger.info("An user is Signing-Up ..." + userSignUpForm);

		signUpValidator.validate(userSignUpForm, bindingResult);

		if (bindingResult.hasErrors()) {

			logger.error("An user is signing-Up, has validation errors."
					+ userSignInForm);

			redirectAttributes.addFlashAttribute("userSignUpForm",
					userSignUpForm);
			redirectAttributes
					.addFlashAttribute("bindingResult", bindingResult);

			return new ModelAndView("redirect:/store/storelogin.htm");

		} else {

			try {

				loginHandler.signUp(userSignUpForm);
				LoginCommonUtil loginutil = new LoginCommonUtil();
				UserAuthVO userAuthVO = new UserAuthVO();
				userAuthVO = storeSignUpFormInfoToVO(userSignUpForm, userAuthVO);
				getandSetMenuList(request.getSession(true), userAuthVO,
						loginutil);
				getUserInfo(request);
				storeUserNameInSession(request, userInfo);
				profileForm = loginutil.setUserDataInAdminProfileForm(
						profileForm, userInfo);
				model.addAttribute("buyerProfileForm", profileForm);
				loginutil = null;
				String prodId = redirectForm.getProdId();

				if (null!=prodId && (!prodId.isEmpty())) {

					redirectAttributes.addFlashAttribute("redirectForm",
							redirectForm);
					return new ModelAndView("redirect:" + redirectForm.getUrl());
				} else {

					return new ModelAndView("redirect:/store/storehome.htm");
				}
			} catch (BusinessFailureException bfe) {

				String email = (String) request.getSession().getAttribute(
						"email");
				if (email != null) {
					LoginCommonUtil loginutil = new LoginCommonUtil();
					UserAuthVO userAuthVO = new UserAuthVO();
					userAuthVO = storeSignUpFormInfoToVO(userSignUpForm,
							userAuthVO);
					profileForm = loginutil.setUserDataInAdminProfileForm(
							profileForm, userInfo);
					model.addAttribute("buyerProfileForm", profileForm);
					loginutil = null;
					return new ModelAndView("redirect:/store/storehome.htm");
				}
				logger.error("An user is signing-Up, has BusinessFailureException."
						+ userSignInForm);
				bindingResult.rejectValue("errorMessage",
						String.valueOf(bfe.getErrorID()));
				redirectAttributes.addFlashAttribute("userSignUpForm",
						userSignUpForm);
				redirectAttributes.addFlashAttribute("bindingResult",
						bindingResult);
				String errorId = String.valueOf(bfe.getErrorID());
				redirectAttributes
						.addFlashAttribute("signUperrorMessage", messageSource
								.getMessage(errorId, null,
										"Email Id already Exists",
										request.getLocale()));
				return new ModelAndView("redirect:/store/storelogin.htm");

			} catch (GenericFailureException gfe) {
				logger.error("An user is signing-Up, has GenericFailureException."
						+ userSignInForm);
				throw gfe;
			}
		}
	}

	@RequestMapping(value = "admin/adminForgetPassword.htm")
	public ModelAndView adminForgetPassword(
			@ModelAttribute("forgetPasswordForm") ForgetPasswordForm forgetPasswordForm,
			BindingResult bindingResult, Model model, ModelMap modelMap,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		modelMap.put(BindingResult.MODEL_KEY_PREFIX + "forgetPasswordForm",
				modelMap.get("bindingResult"));

		model.addAttribute("forgetPasswordForm", forgetPasswordForm);

		return new ModelAndView("/usermanagement/adminforgetpassword");

	}

	@RequestMapping(value = "store/storeForgetPassword.htm")
	public ModelAndView storeForgetPassword(
			@ModelAttribute("forgetPasswordForm") ForgetPasswordForm forgetPasswordForm,
			BindingResult bindingResult, Model model, ModelMap modelMap,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		modelMap.put(BindingResult.MODEL_KEY_PREFIX + "forgetPasswordForm",
				modelMap.get("bindingResult"));

		model.addAttribute("forgetPasswordForm", forgetPasswordForm);

		// saveStoreInfo(request);

		List<Category> categoryList = null;

		try {

			categoryList = storeHomeHandler.getAllCategories();

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}

		modelMap.put("categoryList", categoryList);
		return new ModelAndView("/usermanagement/storeforgetpassword", modelMap);
		// return new ModelAndView("store/store_home", modelMap);

	}

	
	@RequestMapping(value = "store/storerecoverPassword.htm")
	public ModelAndView storerecoverPassword(
			@ModelAttribute("forgetPasswordForm") ForgetPasswordForm forgetPasswordForm,
			BindingResult bindingResult, Model model,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		forgetPasswordForm.setRole("buyer");
		forgetPasswordValidator.validate(forgetPasswordForm, bindingResult);

		if (bindingResult.hasErrors()) {

			logger.error("An user forget password email id has validation errors.");
			redirectAttributes.addFlashAttribute("forgetPasswordForm",
					forgetPasswordForm);
			redirectAttributes
					.addFlashAttribute("bindingResult", bindingResult);

			return new ModelAndView("redirect:/store/storeForgetPassword.htm");

		} else {

			String email = forgetPasswordForm.getEmail();

			List<UmTbUser> user = userService.findAllByProperty("vcEml", email);

			String token = UniqueIdGenerator.getNextId("",1,user.get(0).getNmUsrId());

			StringBuilder builder = new StringBuilder();

			builder.append(email);
			builder.append("&&");
			builder.append(token);
			try {
				user.get(0).setVcTkn(token);
				userService.update(user.get(0));
				String encryptedToken = encryptPassword(builder.toString());
				StringBuilder baseURL = new StringBuilder(request
						.getRequestURL()
						.toString()
						.replace(request.getRequestURI().substring(1),
								request.getContextPath()));

				InputStream inputStreamForproperties = this.getClass()
						.getClassLoader()
						.getResourceAsStream("sbsc.properties");

				Properties properties = new Properties();

				properties.load(inputStreamForproperties);
				String forgetpasswordUrl = (String) properties
						.get("storeforgetpasswordurl");

				baseURL.append(forgetpasswordUrl);

				String forgetPasswordlink = baseURL + "?" + encryptedToken;
				logger.info("link sent to user is :"+forgetPasswordlink);
				// noew have to send the mail to user

				model.addAttribute("successmessage",
						"An email is sent to "+ email+".Please check your email and continue to Login.");
				forgetPasswordForm.clear();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ModelAndView("/usermanagement/storeforgetpassword");
			
		}
	}

	@RequestMapping(value = "admin/adminrecoverPassword.htm")
	public ModelAndView recoverPassword(
			@ModelAttribute("forgetPasswordForm") ForgetPasswordForm forgetPasswordForm,
			BindingResult bindingResult, Model model,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		forgetPasswordForm.setRole("Merchant Admin");
		forgetPasswordValidator.validate(forgetPasswordForm, bindingResult);

		if (bindingResult.hasErrors()) {

			logger.error("An user forget password email id has validation errors.");
			redirectAttributes.addFlashAttribute("forgetPasswordForm",
					forgetPasswordForm);
			redirectAttributes
					.addFlashAttribute("bindingResult", bindingResult);

			return new ModelAndView("redirect:/admin/adminForgetPassword.htm");

		} else {

			String email = forgetPasswordForm.getEmail();

			List<UmTbUser> user = userService.findAllByProperty("vcEml", email);

			String token = UniqueIdGenerator.getNextId("", Long
					.parseLong(request.getParameter("storeId")), user.get(0)
					.getNmUsrId());

			StringBuilder builder = new StringBuilder();

			builder.append(email);
			builder.append("&&");
			builder.append(token);
			try {
				user.get(0).setVcTkn(token);
				userService.update(user.get(0));
				String encryptedToken = encryptPassword(builder.toString());
				StringBuilder baseURL = new StringBuilder(request
						.getRequestURL()
						.toString()
						.replace(request.getRequestURI().substring(1),
								request.getContextPath()));

				InputStream inputStreamForproperties = this.getClass()
						.getClassLoader()
						.getResourceAsStream("sbsc.properties");

				Properties properties = new Properties();

				properties.load(inputStreamForproperties);
				String forgetpasswordUrl = (String) properties
						.get("forgetpasswordurl");

				baseURL.append(forgetpasswordUrl);

				String forgetPasswordlink = baseURL + "?" + encryptedToken;
			    logger.info("link sent to user is :"+forgetPasswordlink);
				
				// now have to send the mail to user

				model.addAttribute("successmessage",
						"An email is sent to "+ email+".Please check your email and continue to Login.");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return new ModelAndView("/usermanagement/adminforgetpassword");
		}
	}
	
	
	
	
	
	
	
	

	

	@RequestMapping(value = "store/storeForgetChangePassword.htm")
	public ModelAndView storeForgetChangePassword(
			@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
			BindingResult bindingResult, Model model, ModelMap modelMap,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes)

	{

		String queryString = request.getQueryString();

		EncryptionAndDecryption decrypt = new EncryptionAndDecryption("");

		String decryptedToken = decrypt.decrypt(queryString);

		String[] tokenData = decryptedToken.split("&&");

		String email = tokenData[0];
		String uid = tokenData[1];

		List<UmTbUser> user = userService.findAllByProperty("vcEml", email);

		String token = user.get(0).getVcTkn();

		if (uid.equals(token)) {
			model.addAttribute("email",email);
			return new ModelAndView("/usermanagement/storeforgetchangepassword");

		}

		else {

			return new ModelAndView("/usermanagement/storeforgetpassword");
		}

	}
	
	

	@RequestMapping(value = "admin/forgetChangePassword.htm")
	public ModelAndView forgetChangePassword(
			@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
			BindingResult bindingResult, Model model, ModelMap modelMap,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes)

	{

		String queryString = request.getQueryString();

		EncryptionAndDecryption decrypt = new EncryptionAndDecryption("");

		String decryptedToken = decrypt.decrypt(queryString);

		String[] tokenData = decryptedToken.split("&&");

		String email = tokenData[0];
		String uid = tokenData[1];

		List<UmTbUser> user = userService.findAllByProperty("vcEml", email);
        setUserInfo(user.get(0));
		String token = user.get(0).getVcTkn();

		if (uid.equals(token)) {
			
			model.addAttribute("email",email);
			return new ModelAndView("/usermanagement/forgetchangepassword");

		}

		else {

			return new ModelAndView("/usermanagement/adminforgetpassword");
		}

	}

	@RequestMapping(value = "admin/forgetPasswordSubmit.htm")
	public ModelAndView forgetPasswordSubmit(
			@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
			BindingResult bindingResult, Model model, ModelMap modelMap,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes)

	{

		forgetPasswordSubmitValidator.validate(changePasswordForm,
				bindingResult);

		if (bindingResult.hasErrors()) {
			logger.info("password change updation has validation errors."
					+ changePasswordForm);

			model.addAttribute("changePasswordForm", changePasswordForm);

			return new ModelAndView("/usermanagement/forgetchangepassword");

		} else {
			String encryptedPassword = encryptPassword(changePasswordForm
					.getPassword());
			
			List<UmTbUser> user = userService.findAllByProperty("vcEml",changePasswordForm.getEmail());
			user.get(0).setVcPswrd(encryptedPassword);
			user.get(0).setVcTkn(null);
			adminHandler.updateAdminPassword(user.get(0));

			user.get(0).setVcPswrd(encryptedPassword);
			adminHandler.updateAdminPassword(user.get(0));
			changePasswordForm.clear();

			model.addAttribute("message", "Password updated successfully");

			return new ModelAndView("/usermanagement/forgetchangepassword");
		}

	}

	
	
	

	@RequestMapping(value = "store/storeforgetPasswordSubmit.htm")
	public ModelAndView storeForgetPasswordSubmit(
			@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
			BindingResult bindingResult, Model model, ModelMap modelMap,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes)

	{

		forgetPasswordSubmitValidator.validate(changePasswordForm,
				bindingResult);

		if (bindingResult.hasErrors()) {
			logger.info("password change updation has validation errors."
					+ changePasswordForm);

			model.addAttribute("changePasswordForm", changePasswordForm);

			return new ModelAndView("/usermanagement/forgetchangepassword");

		} else {
			String encryptedPassword = encryptPassword(changePasswordForm
					.getPassword());

			List<UmTbUser> user = userService.findAllByProperty("vcEml", changePasswordForm.getEmail());
			if(user.isEmpty())
			{
				return new ModelAndView("/usermanagement/storeforgetchangepassword");
				
			}
			else
			{
			user.get(0).setVcPswrd(encryptedPassword);
			adminHandler.updateAdminPassword(user.get(0));
			changePasswordForm.clear();

			model.addAttribute("message", "Password updated successfully");

			return new ModelAndView("/usermanagement/storeforgetchangepassword");
			}
			}

	}
	
	
	
	private String encryptPassword(String password) {
		String encryptedPassword = "";

		try {
			EncryptionAndDecryption encrypt = new EncryptionAndDecryption("");
			encryptedPassword = encrypt.encrypt(password);
			throw new Exception();
		} catch (Exception e) {
			logger.error("password encryption  has exception ."
					+ changePasswordForm);
		}

		return encryptedPassword;

	}

	private void getandSetMenuList(HttpSession session, UserAuthVO userAuthVO,
			LoginCommonUtil loginutil) throws BusinessFailureException {
		List<UserInfoVO> menulist = adminHandler.getLeftMenuContent(userAuthVO);
		/*System.out.println("session created id ::" + session.getId()
				+ " created at ::" + session.getCreationTime());*/
		loginutil.storeUserDataInSession(session, menulist, userAuthVO);
	}

	@RequestMapping(value = "/testegenexp.htm", method = RequestMethod.GET)
	public ModelAndView testGenException(Model model,
			BindingResult bindingResult) {

		throw new GenericFailureException(
				"Test GenericFailureException message");
	}

	@RequestMapping(value = "/testexp.htm", method = RequestMethod.GET)
	public ModelAndView testException(Model model, BindingResult bindingResult) {

		throw new NullPointerException("Test Runtime exception message");
	}

	private UserAuthVO storeSignInFormInfoToVO(UserSignInForm userSignInForm,
			UserAuthVO userAuthVO) {
		userAuthVO.setUserEmail(userSignInForm.getUserEmail());
		userAuthVO.setUserPassword(userSignInForm.getUserPassword());
		userAuthVO.setStoreid(userSignInForm.getStoreId());

		String usrRoleName = userSignInForm.getRolename();

		if (usrRoleName == null || "".equals(usrRoleName)) {
			userAuthVO.setRolename("Buyer");
		} else {
			userAuthVO.setRolename(usrRoleName);
		}
		return userAuthVO;
	}

	private UserAuthVO storeSignUpFormInfoToVO(UserSignUpForm userSignUpForm,
			UserAuthVO userAuthVO) {
		userAuthVO.setUserEmail(userSignUpForm.getEmailID());
		userAuthVO.setUserPassword(userSignUpForm.getPassword());
		userAuthVO.setStoreid(userSignUpForm.getStoreId());

		String usrRoleName = userSignUpForm.getRolename();

		if (usrRoleName == null || "".equals(usrRoleName)) {
			userAuthVO.setRolename("Buyer");
		} else {
			userAuthVO.setRolename(usrRoleName);
		}
		return userAuthVO;
	}

	private void getCountryList() {

		if (countryList == null) {
			countryList = adminHandler.getCountryList();
			setCountryList(countryList);
		}
	}

	public void setCountryList(List<UmTbCntryMstr> countryList) {
		this.countryList = countryList;
	}

	private void getUserInfo(HttpServletRequest request)
			throws BusinessFailureException {

		userInfo = adminHandler.getUserInfo((long) request.getSession()
				.getAttribute("userId"));
		setUserInfo(userInfo);

	}

	public void setUserInfo(UmTbUser userInfo) {
		this.userInfo = userInfo;
	}



	private ModelAndView redirectToAdminDashboard(
			UserSignInForm userSignInForm, BindingResult bindingResult,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes, HttpSession session,
			LoginCommonUtil loginutil, String userEmail, String userPassword)
			throws BusinessFailureException {
		UserAuthVO signInfoVo = new UserAuthVO();
		signInfoVo.setUserEmail(userEmail);
		signInfoVo.setUserPassword(userPassword);
		signInfoVo.setRolename("Merchant Admin");
		signInfoVo.setStoreid(1);		
		getandSetMenuList(session, signInfoVo,
				loginutil);
		getUserInfo(request);
		storeUserNameInSession(request, userInfo);
		loginutil.setUserTheme(request, signInfoVo);
		loginutil = null;
		redirectAttributes.addFlashAttribute("userSignInForm",
				userSignInForm);
		redirectAttributes.addFlashAttribute("bindingResult",
				bindingResult);
		return new ModelAndView("redirect:/admin/dashboard.htm");
	}

}
