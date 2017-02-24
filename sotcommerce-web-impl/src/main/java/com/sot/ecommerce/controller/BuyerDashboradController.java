package com.sot.ecommerce.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sbsc.fos.category.service.AttributeService;
import com.sbsc.fos.category.service.CategoryService;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.order.service.OrderBillingService;
import com.sbsc.fos.order.service.OrderShippingService;
import com.sbsc.fos.order.service.OrderproductDetailService;
import com.sbsc.fos.order.web.handler.OrderHandler;
import com.sbsc.fos.persistance.UmTbCntryMstr;
import com.sbsc.fos.persistance.UmTbUser;
import com.sbsc.fos.product.service.ProductService;
import com.sbsc.fos.um.service.CountryService;
import com.sbsc.fos.um.service.StoreService;
import com.sbsc.fos.um.web.form.ChangePasswordForm;
import com.sbsc.fos.um.web.form.DeliveryAddressesForm;
import com.sbsc.fos.um.web.form.OrderForm;
import com.sbsc.fos.um.web.form.ProfileForm;
import com.sbsc.fos.um.web.form.validator.AdminPasswordValidator;
import com.sbsc.fos.um.web.form.validator.BuyerProfilleValidator;
import com.sbsc.fos.um.web.form.validator.DeliveryAddressValidator;
import com.sbsc.fos.um.web.form.validator.GuestDeliveryAddressValidator;
import com.sbsc.fos.um.web.handler.AdminDashboardHandler;
import com.sbsc.fos.um.web.handler.LoginHandler;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.DashboardCommonUtil;
import com.sbsc.fos.utils.EncryptionAndDecryption;

/**
 * @author brijesh.kumar
 * 
 */
@Controller

public class BuyerDashboradController {

	private static final Logger logger = Logger
			.getLogger(BuyerDashboradController.class);

	@Autowired
	ProfileForm buyerProfileForm;

	@Autowired
	ChangePasswordForm changePasswordForm;

	@Autowired
	OrderForm orderForm;

	@Autowired
	private BuyerProfilleValidator profileValidator;

	@Autowired
	private DeliveryAddressValidator deliverAddressValidator;

	@Autowired
	private GuestDeliveryAddressValidator guestDeliverAddressValidator;

	@Autowired
	private AdminPasswordValidator adminPasswordValidator;

	@Autowired
	private AdminDashboardHandler adminHandler;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	DeliveryAddressesForm deliveryAddressForm;

	private UmTbUser userInfo;

	private List<UmTbCntryMstr> countryList;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private LoginHandler loginHandler;

	@Autowired
	private OrderHandler orderHandler;

	@Autowired
	private CountryService countryService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private OrderBillingService orderBillingService;

	@Autowired
	private OrderShippingService orderShippingService;

	@Autowired
	private OrderproductDetailService orderProductDetailservice;

	/**
	 * This takes you to the buyer profile Page which shows the profile of the
	 * buyer.
	 */

	@RequestMapping(value = "store/buyerprofile.htm", method = RequestMethod.GET)
	public ModelAndView buyerProfile(
			@ModelAttribute("buyerProfileForm") ProfileForm buyerProfileForm,
			Locale locale, BindingResult bindingResult, Model model,
			HttpServletRequest request) {

		try {
			DashboardCommonUtil dashboardutil = new DashboardCommonUtil();
			getUserInfo(request);
			getCountryList();
			buyerProfileForm = dashboardutil.setAdminProfileDataInForm(
					userInfo, buyerProfileForm);
			model.addAttribute("countryList", countryList);
			model.addAttribute("buyerProfileForm", buyerProfileForm);
			return new ModelAndView("/usermanagement/buyerprofile");
		} catch (BusinessFailureException bfe) {

			logger.error("buyer profile  has BusinessFailureException ."
					+ buyerProfileForm);
			bindingResult.rejectValue("errorMessage",
					String.valueOf(bfe.getErrorID()));
			return new ModelAndView("/usermanagement/adminprofile");

		} catch (GenericFailureException gfe) {
			logger.error("buyer profile  has GenericFailureException ."
					+ buyerProfileForm);
			throw gfe;
		}

	}

	/**
	 * This action will be called when buyer will update his profile.
	 * @throws BusinessFailureException 
	 * @throws GenericFailureException 
	 */

	@RequestMapping(value = "store/updatebuyerprofile.htm", method = RequestMethod.POST)
	public ModelAndView updateBuyerProfile(
			@ModelAttribute("buyerProfileForm") ProfileForm buyerProfileForm,
			Locale locale, BindingResult bindingResult, Model model,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) throws GenericFailureException, BusinessFailureException {

		profileValidator.validate(buyerProfileForm, bindingResult);
		getCountryList();
		if (bindingResult.hasErrors()) {

			logger.info("admin profile updation has validation errors."
					+ buyerProfileForm);

			model.addAttribute("buyerProfileForm", buyerProfileForm);

			model.addAttribute("countryList", countryList);
			String pageType=buyerProfileForm.getPagetype();
			buyerProfileForm.setHasErrors("true");
			if(pageType.equals("profile"))
			{
			
			return new ModelAndView("/usermanagement/buyerprofile");
			}
			else
			{
				return new ModelAndView("/usermanagement/buyeraccount");
			}
			} 
				
				SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
				List<OrdersVO> detailList = orderHandler
						.orderHistoryHandle(sessionInfo);
				getUserInfo(request);
				
				userInfo=editBuyerProfileData(buyerProfileForm,userInfo);
				userInfo = adminHandler.updateAdminProfile(userInfo);
				DashboardCommonUtil dashboardutil = new DashboardCommonUtil();
				buyerProfileForm = dashboardutil.setAdminProfileDataInForm(
						userInfo, buyerProfileForm);
				model.addAttribute("countryList", countryList);
				model.addAttribute("detailList", detailList);
				model.addAttribute("buyerProfileForm", buyerProfileForm);
				String successMessage = messageSource.getMessage(
						"profileupdate.successful", null,
						"Profile updated successfully", request.getLocale());
				model.addAttribute("message",successMessage);
				
				String pageType=buyerProfileForm.getPagetype();
				
				if(pageType.equals("profile"))
				{
				
				return new ModelAndView("/usermanagement/buyerprofile");
				}
				else
				{
					return new ModelAndView("/usermanagement/buyeraccount");
				}
		

	}

	
	
	

	/**
	 * This takes you to the buyer account Page which shows the account of the
	 * buyer.
	 */

	@RequestMapping(value = "store/myaccount.htm", method = RequestMethod.GET)
	public ModelAndView myAccount(
			@ModelAttribute("buyerProfileForm") ProfileForm buyerProfileForm,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		try {

			getUserInfo(request);
			getCountryList();
			DashboardCommonUtil dashboardUtil = new DashboardCommonUtil();
			buyerProfileForm = dashboardUtil.setAdminProfileDataInForm(
					userInfo, buyerProfileForm);
			SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
			List<OrdersVO> detailList = orderHandler
					.orderHistoryHandle(sessionInfo);
			model.addAttribute("buyerProfileForm", buyerProfileForm);
			model.addAttribute("detailList", detailList);
			model.addAttribute("countryList", countryList);
			return new ModelAndView("/usermanagement/buyeraccount");
		} catch (BusinessFailureException bfe) {

			logger.info("buyer profile  has BusinessFailureException ."
					+ buyerProfileForm);
			bindingResult.rejectValue("errorMessage",
					String.valueOf(bfe.getErrorID()));
            model.addAttribute("txt_orange","txt_orange");
            model.addAttribute("countryList", countryList);
			return new ModelAndView("/usermanagement/buyeraccount");

		} catch (GenericFailureException gfe) {
			logger.error("buyer profile has GenericFailureException ."
					+ buyerProfileForm);
			throw gfe;
		}

	}

	/**
	 * This action will be called when buyer will change his password.
	 * @throws BusinessFailureException 
	 */

	@RequestMapping(value = "store/buyerUpdatePassword.htm", method = RequestMethod.POST)
	public ModelAndView merchantupdatepassword(
			@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
			BindingResult bindingResult, Model model,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) throws BusinessFailureException {
		

			getUserInfo(request);
			EncryptionAndDecryption decrypt = new EncryptionAndDecryption("");
			String oldPassword = decrypt.decrypt(userInfo.getVcPswrd());
			decrypt = null;
			changePasswordForm.setUserPassword(oldPassword);
			adminPasswordValidator.validate(changePasswordForm, bindingResult);

			if (bindingResult.hasErrors()) {

				logger.info("password change updation has validation errors."
						+ changePasswordForm);

				model.addAttribute("changePasswordForm", changePasswordForm);

				return new ModelAndView("/usermanagement/buyerChangePassword");

			} 

				String encryptedPassword = encryptPassword(changePasswordForm
						.getPassword());
				userInfo.setVcPswrd(encryptedPassword);
				adminHandler.updateAdminPassword(userInfo);
				changePasswordForm.clear();
				
				redirectAttributes.addFlashAttribute("message",
						"Password updated successfully");
			
				return new ModelAndView("redirect:/store/buyerchangePassword.htm");
			
		
	}

	/**
	 * This will take you to buyer change password screen.
	 */

	@RequestMapping(value = "store/buyerchangePassword.htm", method = RequestMethod.GET)
	public ModelAndView buyerChangePassword(
			@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
			BindingResult bindingResult, Model model, HttpServletRequest request,final RedirectAttributes redirectAttributes) {

		model.addAttribute("changePasswordForm", changePasswordForm);

		return new ModelAndView("/usermanagement/buyerChangePassword");

	}
	
	

	@RequestMapping(value = "store/buyerwishlist.htm", method = RequestMethod.GET)
	public ModelAndView buyerWishList(Locale locale, Model model,
			HttpServletRequest request) {

		return new ModelAndView("/usermanagement/buyerwishlist");

	}

	@RequestMapping(value = "store/orderhistory.htm", method = RequestMethod.GET)
	public ModelAndView orderHistory(Locale locale, Model model,
			HttpServletRequest request) {

		return new ModelAndView("/usermanagement/orderhistory");

	}

	public void setUserInfo(UmTbUser userInfo) {
		this.userInfo = userInfo;
	}

	public void getCountryList() {
		if (countryList == null) {
			countryList = adminHandler.getCountryList();
			setCountryList(countryList);
		}
	}

	public void setCountryList(List<UmTbCntryMstr> countryList) {
		this.countryList = countryList;
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

	private void getUserInfo(HttpServletRequest request)
			throws BusinessFailureException {

		userInfo = adminHandler.getUserInfo((long) request.getSession()
				.getAttribute("userId"));
		setUserInfo(userInfo);

	}

	
	public UmTbUser editBuyerProfileData(ProfileForm adminProfileForm,
			UmTbUser userInfo) {

		userInfo.setVcFrstNm(adminProfileForm.getUserFirstName());
		userInfo.setVcLstNm(adminProfileForm.getUserLastName());
		userInfo.setVcPhn(adminProfileForm.getUserPhone());
		userInfo.setVcAddr(adminProfileForm.getAddress());
		userInfo.setVcCty(adminProfileForm.getCity());
		userInfo.setVcSt(adminProfileForm.getState());
		userInfo.setVcZpcd(adminProfileForm.getZipcode());
		userInfo.setUmTbCntryMstr(adminHandler.getCountryById(adminProfileForm.getCountryId()));
		return userInfo;

	}
	

}
