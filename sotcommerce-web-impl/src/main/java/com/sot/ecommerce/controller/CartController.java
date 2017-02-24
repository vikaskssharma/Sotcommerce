package com.sot.ecommerce.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sbsc.fos.category.service.AttributeService;
import com.sbsc.fos.category.service.CategoryService;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.order.service.OrderBillingService;
import com.sbsc.fos.order.service.OrderService;
import com.sbsc.fos.order.service.OrderShippingService;
import com.sbsc.fos.order.service.OrderproductDetailService;
import com.sbsc.fos.order.web.handler.OrderHandler;
import com.sbsc.fos.persistance.OrderBillingTbDtl;
import com.sbsc.fos.persistance.OrderProductTbDetail;
import com.sbsc.fos.persistance.OrderShippingTbDtl;
import com.sbsc.fos.persistance.OrderStatusTbMaster;
import com.sbsc.fos.persistance.OrderTbMaster;
import com.sbsc.fos.persistance.PaymentStatusTbMaster;
import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.persistance.PromotionTbMaster;
import com.sbsc.fos.persistance.ShipmentStatusTbMaster;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sbsc.fos.persistance.UmTbCntryMstr;
import com.sbsc.fos.persistance.UmTbUser;
import com.sbsc.fos.product.service.ProductService;
import com.sbsc.fos.um.common.CheckoutOrderForm;
import com.sbsc.fos.um.common.OrderVO;
import com.sbsc.fos.um.common.ProductOrderVO;
import com.sbsc.fos.um.common.UserAuthVO;
import com.sbsc.fos.um.common.UserInfoVO;
import com.sbsc.fos.um.service.CountryService;
import com.sbsc.fos.um.service.StoreService;
import com.sbsc.fos.um.web.form.ChangePasswordForm;
import com.sbsc.fos.um.web.form.DeliveryAddressesForm;
import com.sbsc.fos.um.web.form.OrderForm;
import com.sbsc.fos.um.web.form.PaymentForm;
import com.sbsc.fos.um.web.form.ProfileForm;
import com.sbsc.fos.um.web.form.UserSignInForm;
import com.sbsc.fos.um.web.form.UserSignUpForm;
import com.sbsc.fos.um.web.form.validator.AdminPasswordValidator;
import com.sbsc.fos.um.web.form.validator.BuyerProfilleValidator;
import com.sbsc.fos.um.web.form.validator.DeliveryAddressValidator;
import com.sbsc.fos.um.web.form.validator.GuestDeliveryAddressValidator;
import com.sbsc.fos.um.web.form.validator.SignInValidator;
import com.sbsc.fos.um.web.form.validator.SignUpValidator;
import com.sbsc.fos.um.web.handler.AdminDashboardHandler;
import com.sbsc.fos.um.web.handler.CartHandler;
import com.sbsc.fos.um.web.handler.LoginHandler;
import com.sbsc.fos.utils.DashboardCommonUtil;
import com.sbsc.fos.utils.DateUtil;
import com.sbsc.fos.utils.LoginCommonUtil;
import com.sbsc.fos.utils.SBSConstants;
import com.sbsc.fos.utils.UniqueIdGenerator;

@Controller
public class CartController {

	private static final Logger logger = Logger.getLogger(CartController.class);

	@Autowired
	private LoginHandler loginHandler;

	@Autowired
	private AdminDashboardHandler adminHandler;
	
	@Autowired
	private CartHandler cartHandler;

	@Autowired
	private SignUpValidator signUpValidator;

	@Autowired
	private SignInValidator signInValidator;
	@Autowired
	private MessageSource messageSource;

	UserSignInForm userSignInForm;

	UserSignUpForm userSignUpForm;

	ProfileForm profileForm;
	@Resource
	OrderService orderService;

	@Autowired
	DeliveryAddressesForm deliveryAddressForm;

	private UmTbUser userInfo;

	private List<UmTbCntryMstr> countryList;

	ProfileForm buyerProfileForm;

	ChangePasswordForm changePasswordForm;

	OrderForm orderForm;
	
	@Autowired
	PaymentForm paymentForm;

	@Autowired
	private BuyerProfilleValidator profileValidator;

	@Autowired
	private DeliveryAddressValidator deliverAddressValidator;

	@Autowired
	private GuestDeliveryAddressValidator guestDeliverAddressValidator;

	@Autowired
	private AdminPasswordValidator adminPasswordValidator;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AttributeService attributeService;

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
	 * this will be called when user adds a product to cart.
	 */

	@RequestMapping(value = "store/addToCart.htm", method = RequestMethod.GET)
	public void addToCart(HttpServletRequest request, Model model) {
		logger.info(" adding product to cart.");
		try {
		addProductToCart(request);
		} catch (Exception e) {
			logger.error("error occured during product addition to cart.");
		}
		logger.info("product added to cart.");
		// return new ModelAndView("redirect:./storehome.htm");
	}

	/**
	 * this will take you to the dialog page where it displays all the selected
	 * product.
	 */
	

	@RequestMapping(value = "store/cartCheckoutDialog.htm", method = RequestMethod.GET)
	public ModelAndView cartCheckoutDialog(@ModelAttribute("orderForm") OrderForm orderForm,HttpServletRequest request,
			Model model) {
		
		String headerpopup=request.getParameter("headerpopup");
		
		logger.info(" cartCheckoutDialog starts ");
		try{
		List<ProductOrderVO> productOrderList = createProductOrderList(request);
		BigDecimal totalAmount = calculateTotalAmount(productOrderList);
		
		orderForm.setOrderList(productOrderList);
		orderForm.setTotalCost(totalAmount);
		
		InputStream inputStreamForproperties = this.getClass().getClassLoader()
				.getResourceAsStream("sbsc.properties");

		Properties properties = new Properties();
		properties.load(inputStreamForproperties);
		long quantitySize = Long.parseLong(properties
				.getProperty("cart.quantity"));
		
		
		
		
		model.addAttribute("quantitySize",quantitySize);
		model.addAttribute("orderForm", orderForm);
		}
		catch(Exception e)
		{
			logger.error("error occured while  cartCheckoutDialog");
		}
		logger.info(" cartCheckoutDialog ends ");
		if("true".equals(headerpopup))
		{
			return new ModelAndView("/store/layout/store_header_cartpopup");
		}
		else
		{
		return new ModelAndView("/usermanagement/add-to-cart-dialog");
		}
	}
	
	
	
	/**
	 * This will take you to the page where it displays all the selected
	 * products. customer can edit or delete the product . can checkout from
	 * this page.
	 */

	@RequestMapping(value = "store/cartCheckout.htm", method = RequestMethod.GET)
	public ModelAndView cartCheckout(@ModelAttribute("orderForm") OrderForm orderForm,HttpServletRequest request, Model model) {

		logger.info(" cart checking out starts ");
		
		try {

			List<ProductOrderVO> productOrderList = createProductOrderList(request);
			if (!(productOrderList == null || productOrderList.isEmpty())) {
				BigDecimal totalAmount = calculateTotalAmount(productOrderList);
				orderForm.setOrderList(productOrderList);
				orderForm.setTotalCost(totalAmount);
				
				InputStream inputStreamForproperties = this.getClass().getClassLoader()
						.getResourceAsStream("sbsc.properties");
				Properties properties = new Properties();
				properties.load(inputStreamForproperties);
				long quantitySize = Long.parseLong(properties
						.getProperty("cart.quantity"));				
				
				model.addAttribute("quantitySize",quantitySize);
			}
			model.addAttribute("orderForm", orderForm);
		} catch (Exception e) {
			logger.error(" error occured while cart checking out");
		}
		logger.info(" cart checking out ends");
		
			
			return new ModelAndView("/usermanagement/add-to-cart");
				
		
		

	}

	
	
	
	/**
	 * this will be called when user changes the quantity.
	 */

	@RequestMapping(value = "store/cartCheckoutUpdate.htm", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, String> cartCheckoutUpdate(HttpServletRequest request) {
		Map<String, String> cartAmounts = new HashMap<>();
		logger.info(" cartcheckout update starts ");
		try {

			String productId = request.getParameter("productId");
			List<ProductOrderVO> productOrderList = createProductOrderList(request);
			BigDecimal totalAmount = calculateTotalAmount(productOrderList);
			BigDecimal productAmount = getProductAmount(productOrderList,
					Long.parseLong(productId));
			cartAmounts.put("totalAmount", totalAmount.toString());
			cartAmounts.put("productAmount", productAmount.toString());
		} catch (Exception e) {
			logger.error(" error occured while cart checking out");
		}
		logger.info(" cartcheckout update ends ");
		return cartAmounts;

	}

	/**
	 * it returns the amount of a particular product.
	 */

	private BigDecimal getProductAmount(List<ProductOrderVO> productOrderList,
			long productId) {
		BigDecimal productAmount = new BigDecimal(0);
		Iterator<ProductOrderVO> prodOrderItr = productOrderList.iterator();
		while (prodOrderItr.hasNext()) {
			ProductOrderVO prodOrderVo = prodOrderItr.next();
			if (prodOrderVo.getProduct().getNmPrdId() == productId) {
				productAmount = prodOrderVo.getProduct().getNmSp()
						.multiply(new BigDecimal(prodOrderVo.getQuantity()));
				break;

			}

		}
		return productAmount;

	}

	/**
	 * it returns the size of the cart.
	 */

	@RequestMapping(value = "store/cartGetSize.htm", method = RequestMethod.GET)
	@ResponseBody
	public String getCartSize(HttpServletRequest request) {
		String cartSize = "0";
		logger.info(" cartGetSize starts ");
		
		try{
			long storeId=(long) request.getSession().getAttribute("storeId");
		Map orderListMap = (Map) request.getSession()
				.getAttribute("orderList");
		
		List<OrderVO> orderList=(List<OrderVO>) orderListMap.get(storeId);
		if (orderList != null) {
			cartSize = String.valueOf(orderList.size());
		}
		}
		catch(Exception e)
		{
			logger.error("error occured while cartGetSize");
			
		}
		logger.info(" cartGetSize ends ");
		return cartSize;
	}

	

	/**
	 * it calculates the total amount of the selected products.
	 */

	private BigDecimal calculateTotalAmount(
			List<ProductOrderVO> productOrderList) {

		BigDecimal totalAmount = new BigDecimal(0);
		Iterator<ProductOrderVO> prodOrdervoItr = productOrderList.iterator();

		while (prodOrdervoItr.hasNext()) {
			ProductOrderVO prodOrdervo = prodOrdervoItr.next();
			long quantity = prodOrdervo.getQuantity();
			BigDecimal sellPrice = prodOrdervo.getProduct().getNmSp();
			totalAmount = totalAmount.add((sellPrice.multiply(new BigDecimal(
					quantity))));

		}

		return totalAmount;

	}

	/**
	 * this will take you to shipping and billing address page if user already
	 * logged in and to the signin/signup if user is not logged in.
	 */

	@RequestMapping(value = "store/checkout.htm")
	public ModelAndView checkOut(
			@ModelAttribute("userSignInForm") UserSignInForm userSignInForm,
			@ModelAttribute("userSignUpForm") UserSignUpForm userSignUpForm,
			@ModelAttribute("checkoutOrderForm") CheckoutOrderForm checkoutOrderForm,
			@ModelAttribute("deliveryAddressForm") DeliveryAddressesForm deliveryAddressForm,
			@ModelAttribute("profileForm") ProfileForm profileForm,
			Model model, BindingResult bindingResult, ModelMap modelMap,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		logger.info(" checkout starts ");
		String email = (String) request.getSession().getAttribute("email");
		deliveryAddressForm.clear();
		if (checkoutOrderForm.getOrderVOList().size() > 0) {
			storeModifiedOrderList(checkoutOrderForm, request);

		}
		if (email == null) {
			modelMap.put(BindingResult.MODEL_KEY_PREFIX + "userSignUpForm",
					modelMap.get("bindingResult"));
			modelMap.put(BindingResult.MODEL_KEY_PREFIX + "userSignInForm",
					modelMap.get("bindingResult"));
			// setting product information in session (hard coded)later it will
			// be removed after checkout page in place
			model.addAttribute("userSignInForm", userSignInForm);
			model.addAttribute("userSignUpForm", userSignUpForm);
			getCountryList();
			model.addAttribute("countryList", countryList);
			logger.info(" checkout ends ");
			return new ModelAndView("/usermanagement/userinformation");
		} else {
			try {
				LoginCommonUtil loginutil = new LoginCommonUtil();
				UserAuthVO signInfoVo = new UserAuthVO();
				getUserInfo(request);

				profileForm = loginutil.setUserDataInAdminProfileForm(
						profileForm, userInfo);
				deliveryAddressForm.setSameBillAddress(true);
				deliveryAddressForm.setSameShipAddress(true);
				model.addAttribute("profileForm", profileForm);
				model.addAttribute("deliveryAddressForm", deliveryAddressForm);
				getCountryList();
				model.addAttribute("countryList", countryList);
				loginutil = null;
				logger.info(" checkout ends ");
				return new ModelAndView("/usermanagement/shippingaddress");
			} catch (BusinessFailureException bfe) {
				logger.error(" checkout page, has validation errors."
						+ userSignInForm);
				bindingResult.rejectValue("errorMessage",
						String.valueOf(bfe.getErrorID()));
				String errorId = String.valueOf(bfe.getErrorID());
				redirectAttributes.addFlashAttribute("errorMessage",
						messageSource.getMessage(errorId, null,
								"emailId Password Mismatch",
								request.getLocale()));
				getCountryList();
				model.addAttribute("countryList", countryList);
				
				return new ModelAndView("/usermanagement/userinformation");
			}

		}
	}

	/**
	 * This method store the modified order list to session.
	 */

	private void storeModifiedOrderList(CheckoutOrderForm checkoutOrderForm,
			HttpServletRequest request) {

		List<OrderVO> orderList = checkoutOrderForm.getOrderVOList();
		long storeId=(long) request.getSession().getAttribute("storeId");
		Map orderListMap=new HashMap();
		orderListMap.put(storeId,orderList);
		
		request.getSession().setAttribute("orderList", orderListMap);
	}

	/**
	 * This will take you to shipping address page.
	 */

	@RequestMapping(value = "store/checkoutsignIn.htm", method = RequestMethod.POST)
	public ModelAndView checkOutSignIn(
			@ModelAttribute("profileForm") ProfileForm profileForm,
			@ModelAttribute("deliveryAddressForm") DeliveryAddressesForm deliveryAddressForm,
			@ModelAttribute("userSignInForm") UserSignInForm userSignInForm,
			Model model, BindingResult bindingResult, ModelMap modelMap,
			final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		logger.info(" checkoutsignIn starts ");
		modelMap.put(BindingResult.MODEL_KEY_PREFIX + "userSignInForm",
				modelMap.get("bindingResult"));
		signInValidator.validate(userSignInForm, bindingResult);

		if (bindingResult.hasErrors()) {

			logger.error("An user is signing-in after checkout, has validation errors."
					+ userSignInForm);

			redirectAttributes.addFlashAttribute("userSignInForm",
					userSignInForm);
			redirectAttributes
					.addFlashAttribute("bindingResult", bindingResult);
			return new ModelAndView("redirect:checkout.htm");

		} else {

			try {
				LoginCommonUtil loginutil = new LoginCommonUtil();
				UserAuthVO signInfoVo = new UserAuthVO();
				signInfoVo = storeSignInFormInfoToVO(userSignInForm, signInfoVo);
				loginHandler.signIn(signInfoVo);

				getandSetMenuList(request, signInfoVo, loginutil);
				 storeUserNameInSession(request,userInfo);
				getUserInfo(request);
				profileForm = loginutil.setUserDataInAdminProfileForm(
						profileForm, userInfo);
				model.addAttribute("profileForm", profileForm);
				deliveryAddressForm.setSameBillAddress(true);
				deliveryAddressForm.setSameShipAddress(true);
				model.addAttribute("deliveryAddressForm", deliveryAddressForm);
				getCountryList();
				model.addAttribute("countryList", countryList);
				loginutil = null;
				logger.info(" checkoutsignIn ends ");
				return new ModelAndView("/usermanagement/shippingaddress");

			} catch (BusinessFailureException bfe) {
				logger.error("An user is signing-in after checkout, has BusinessFailureException."
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
				return new ModelAndView("redirect:checkout.htm");

			} catch (GenericFailureException gfe) {
				logger.error("An user is signing-Up, has GenericFailureException."
						+ userSignInForm);
				throw gfe;
			} catch (NoSuchAlgorithmException e) {
				logger.error("An user is signing-Up after checkout, has exception in decryption :NoSuchAlgorithmException."
						+ userSignInForm);
				model.addAttribute("userSignInForm", userSignInForm);
				model.addAttribute("userSignUpForm", userSignUpForm);
				return new ModelAndView("redirect:checkout.htm");

			} catch (NoSuchPaddingException e) {
				logger.error("An user is signing-Up after checkout, has exception in decryption :NoSuchPaddingException."
						+ userSignInForm);
				model.addAttribute("userSignInForm", userSignInForm);
				model.addAttribute("userSignUpForm", userSignUpForm);
				return new ModelAndView("redirect:checkout.htm");

			}
		}

	}

	/**
	 * This will take you to shipping address page.
	 */

	@RequestMapping(value = "store/buyercheckoutsignup.htm", method = RequestMethod.POST)
	public ModelAndView ckeckOutSignUp(
			@ModelAttribute("profileForm") ProfileForm profileForm,
			@ModelAttribute("deliveryAddressForm") DeliveryAddressesForm deliveryAddressForm,
			@ModelAttribute("userSignUpForm") UserSignUpForm userSignUpForm,
			Model model, BindingResult bindingResult,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		logger.info("An user is Signing-Up after checkout ..."
				+ userSignUpForm);

		signUpValidator.validate(userSignUpForm, bindingResult);

		if (bindingResult.hasErrors()) {

			logger.error("An user is signing-Up after checkout, has validation errors."
					+ userSignInForm);

			redirectAttributes.addFlashAttribute("userSignUpForm",
					userSignUpForm);
			redirectAttributes
					.addFlashAttribute("bindingResult", bindingResult);
			return new ModelAndView("redirect:checkout.htm");

		} else {

			try {

				loginHandler.signUp(userSignUpForm);
				UserAuthVO userAuthVO = new UserAuthVO();
				userAuthVO = storeSignUpFormInfoToVO(userSignUpForm, userAuthVO);
				LoginCommonUtil loginutil = new LoginCommonUtil();
				getandSetMenuList(request, userAuthVO, loginutil);
				 storeUserNameInSession(request,userInfo);
				getUserInfo(request);
				profileForm = loginutil.setUserDataInAdminProfileForm(
						profileForm, userInfo);
				getCountryList();
				model.addAttribute("countryList", countryList);
				model.addAttribute("profileForm", profileForm);
				model.addAttribute("deliveryAddressForm", deliveryAddressForm);
				loginutil = null;
				deliveryAddressForm.setSameBillAddress(true);
				deliveryAddressForm.setSameShipAddress(true);
				logger.info("An user is Signing-Up after checkout ends ..."
						+ userSignUpForm);
				return new ModelAndView("/usermanagement/shippingaddress");

			} catch (BusinessFailureException bfe) {

				String email = (String) request.getSession().getAttribute(
						"email");
				logger.error("An user is signing-Up after checkout, has BusinessFailureException ."
						+ userSignInForm);
				if (email != null) {
					UserAuthVO userAuthVO = new UserAuthVO();
					userAuthVO = storeSignUpFormInfoToVO(userSignUpForm,
							userAuthVO);
					LoginCommonUtil loginutil = new LoginCommonUtil();
					profileForm = loginutil.setUserDataInAdminProfileForm(
							profileForm, userInfo);

					model.addAttribute("profileForm", profileForm);
					model.addAttribute("deliveryAddressForm",
							deliveryAddressForm);
					loginutil = null;
					return new ModelAndView("/usermanagement/shippingaddress");
				}
				getCountryList();
				model.addAttribute("countryList", countryList);
				model.addAttribute("userSignUpForm", userSignUpForm);
				model.addAttribute("userSignInForm", userSignInForm);
				bindingResult.rejectValue("signUperrorMessage",
						String.valueOf(bfe.getErrorID()));
				redirectAttributes.addFlashAttribute("userSignUpForm",
						userSignUpForm);
				redirectAttributes.addFlashAttribute("bindingResult",
						bindingResult);
				String errorId = String.valueOf(bfe.getErrorID());
				redirectAttributes.addFlashAttribute("signUperrorMessage",
						messageSource.getMessage(errorId, null,
								"emailId Password Mismatch",
								request.getLocale()));

				return new ModelAndView("redirect:checkout.htm");

			} catch (GenericFailureException gfe) {
				logger.error("An user is Signing-Up after checkout ... has GenericFailureException."
						+ userSignUpForm);
				throw gfe;
			}
		}
	}

	/**
	 * This will take you to shipping address page for guest user.
	 */

	@RequestMapping(value = "store/guestCheckoutsignup.htm", method = RequestMethod.GET)
	public ModelAndView guestCheckoutsignup(
			@ModelAttribute("deliveryAddressForm") DeliveryAddressesForm deliveryAddressForm,
			Model model,ModelMap modelMap) {

		logger.info("guestCheckoutsignup starts");
		try{
			modelMap.put(BindingResult.MODEL_KEY_PREFIX + "deliveryAddressForm",
					modelMap.get("bindingResult"));
			
		getCountryList();
		model.addAttribute("countryList", countryList);
		model.addAttribute("deliveryAddressForm", deliveryAddressForm);
		deliveryAddressForm.setUseshippingaddress(true);
		}
		catch(Exception e)
		{
			logger.error("error occured while guestCheckoutsignup");
		}
		logger.info("guestCheckoutsignup ends");
		return new ModelAndView("/usermanagement/shippingaddress_guest");

	}

	/**
	 * This will take you to review order page.
	 */

	@RequestMapping(value = "store/revieworder.htm", method = RequestMethod.POST)
	public ModelAndView reviewOrder(
			@ModelAttribute("deliveryAddressForm") DeliveryAddressesForm deliveryAddressForm,
			BindingResult bindingResult,
			@ModelAttribute("profileForm") ProfileForm profileForm,
			@ModelAttribute("orderForm") OrderForm orderForm,
			Model model, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		logger.info("revieworder starts");
		String orderId = null;

		try {
			deliveryAddressForm = populateShipAndBillAddressForExistingUser(
					deliveryAddressForm, profileForm, request);

			deliverAddressValidator
					.validate(deliveryAddressForm, bindingResult);

			if (bindingResult.hasErrors()) {

				logger.error("in revieworder.htm deliveryAddressForm  has validation errors."
						+ deliveryAddressForm);

				model.addAttribute("deliveryAddressForm", deliveryAddressForm);
				model.addAttribute("profileForm", profileForm);
				getCountryList();

				model.addAttribute("countryList", countryList);
				logger.info("revieworder ends");
				return new ModelAndView("/usermanagement/shippingaddress");

			} else {

				getUserInfo(request);
				List<ProductOrderVO> productOrderList = createProductOrderList(request);
				orderId = createOrder(deliveryAddressForm,orderForm, userInfo,
						productOrderList, request);
				orderForm.setOrderId(orderId);
				orderForm.setOrderList(productOrderList);
				model.addAttribute("deliveryAddressForm", deliveryAddressForm);
				setDeliveryAddressForm(deliveryAddressForm);
				model.addAttribute("orderForm", orderForm);
				logger.info("revieworder ends");
				return new ModelAndView("/usermanagement/revieworder");
			}
		} catch (BusinessFailureException bfe) {

			logger.error("revieworder  has BusinessFailureException ."
					+ changePasswordForm);
			bindingResult.rejectValue("errorMessage",
					String.valueOf(bfe.getErrorID()));
			String errorId = String.valueOf(bfe.getErrorID());

			redirectAttributes.addFlashAttribute("errorMessage", messageSource
					.getMessage(errorId, null, "emailId Password Mismatch",
							request.getLocale()));

			return new ModelAndView("/usermanagement/shippingaddress");

		} catch (GenericFailureException gfe) {
			logger.error("revieworder  has GenericFailureException ."
					+ changePasswordForm);
			throw gfe;
		}

	}

	/**
	 * This will be called when user will delete a product from review order
	 * page.
	 */

	@RequestMapping(value = "store/deleteOrderedProduct.htm", method = RequestMethod.GET)
	public ModelAndView deleteOrderedProduct(
			@ModelAttribute("orderForm") OrderForm orderForm,HttpServletRequest request,
			Model model) {
		logger.info("deleteOrderedProduct starts");
		
		try{
		long productId = Long.parseLong(request.getParameter("productId"));
		deleteProductfromOrderList(request, productId);
		List<ProductOrderVO> productOrderList = createProductOrderList(request);

		String orderId=createOrder(deliveryAddressForm,orderForm, userInfo, productOrderList, request);
		orderForm.setOrderId(orderId);		
		orderForm.setOrderList(productOrderList);
		model.addAttribute("deliveryAddressForm",deliveryAddressForm);
		model.addAttribute("orderForm",orderForm);
		}
		catch(Exception e)
		{
			logger.error("error occured while deleteOrderedProduct");
		}
		logger.info("deleteOrderedProduct ends");
		return new ModelAndView("/usermanagement/revieworder");

	}

	/**
	 * This will be called when user will delete a product before checkout.
	 * page.
	 */

	@RequestMapping(value = "store/deleteOrderedProductbeforeCheckout.htm", method = RequestMethod.GET)
	public ModelAndView deleteOrderedProductBeforeCheckout(
			HttpServletRequest request, Model model) {

		logger.info("deleteOrderedProductbeforeCheckout starts");
		try{
		long productId = Long.parseLong(request.getParameter("productId"));
		deleteProductfromOrderList(request, productId);
		}
		catch(Exception e)
		{
			logger.error("error occured while deleteOrderedProductbeforeCheckout");
		}
		logger.info("deleteOrderedProductbeforeCheckout ends");
		return new ModelAndView("redirect:cartCheckout.htm");

	}

	/**
	 * This will be called when user will delete a product before checkout from
	 * popup page. page.
	 */

	@RequestMapping(value = "store/deleteOrderedProductbeforeCheckoutFromPopup.htm", method = RequestMethod.GET)
	public ModelAndView deleteOrderedProductbeforeCheckoutFromPopup(
			HttpServletRequest request, Model model) {
		logger.info("deleteOrderedProductbeforeCheckoutFromPopup starts");
		try{
		long productId = Long.parseLong(request.getParameter("productId"));
		deleteProductfromOrderList(request, productId);
		}
		catch(Exception e)
		{
			logger.error("error occured while deleteOrderedProductbeforeCheckoutFromPopup");	
		}
		logger.info("deleteOrderedProductbeforeCheckoutFromPopup ends");
		return new ModelAndView("redirect:cartCheckoutDialog.htm");

	}

	/**
	 * This will take you to review order page for guest user.
	 */

	@RequestMapping(value = "store/guestrevieworder.htm", method = RequestMethod.POST)
	public ModelAndView guestReviewOrder(
			@ModelAttribute("profileForm") ProfileForm profileForm,
			@ModelAttribute("orderForm") OrderForm orderForm,
			@ModelAttribute("deliveryAddressForm") DeliveryAddressesForm deliveryAddressForm,
			BindingResult bindingResult,
			Model model, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		logger.info("guestrevieworder starts");
		try {
			UmTbUser user =adminHandler.isUserExist(deliveryAddressForm.getGuestEmail(),"buyer");
			 setUserInfo(user);
			deliveryAddressForm = populateShipAndBillAddressForGuestUser(deliveryAddressForm);
			guestDeliverAddressValidator.validate(deliveryAddressForm,
					bindingResult);
			if (bindingResult.hasErrors()) {
				logger.error("guestrevieworder has validation errors."
						+ deliveryAddressForm);
				model.addAttribute("deliveryAddressForm", deliveryAddressForm);
				model.addAttribute("profileForm", profileForm);
				getCountryList();
				model.addAttribute("countryList", countryList);
				logger.info("guestrevieworder ends");
				return new ModelAndView("/usermanagement/shippingaddress_guest");
			} else {
				
			
				
				if(user==null)
				{
					user = loginHandler.guestSignUp(deliveryAddressForm);
					 setUserInfo(user);
				}
				
				List<ProductOrderVO> productOrderList = createProductOrderList(request);
				String orderId=createOrder(deliveryAddressForm,orderForm, user, productOrderList,
						request);
				orderForm.setOrderId(orderId);
				model.addAttribute("deliveryAddressForm", deliveryAddressForm);
				orderForm.setOrderList(productOrderList);
				UserAuthVO userAuthVO = new UserAuthVO();
				userAuthVO = storeSignUpFormInfoToVO(user, userAuthVO,
						deliveryAddressForm);
				LoginCommonUtil loginutil = new LoginCommonUtil();
				//getandSetMenuList(request, userAuthVO, loginutil);
				//getUserInfo(request);
				profileForm = loginutil.setUserDataInAdminProfileForm(
						profileForm, userInfo);
				model.addAttribute("orderForm", orderForm);
				logger.info("guestrevieworder ends");
				return new ModelAndView("/usermanagement/revieworder");

			}
		} catch (BusinessFailureException bfe) {
			logger.error("guestrevieworder has BusinessFailureException errors."
					+ deliveryAddressForm);			
			String errorId = String.valueOf(bfe.getErrorID());			
			redirectAttributes.addFlashAttribute("deliveryAddressForm",
					deliveryAddressForm);
			redirectAttributes.addFlashAttribute("message",messageSource.getMessage(errorId, null,request.getLocale()));
			redirectAttributes.addFlashAttribute("bindingResult",bindingResult);
			return new ModelAndView("redirect:guestCheckoutsignup.htm");		
			
		} catch (GenericFailureException gfe) {
			logger.error("guestrevieworder has GenericFailureException errors."
					+ deliveryAddressForm);
			throw gfe;
		}

	}

	/**
	 * This method will put the shipping and billing address data to user.
	 */

	private DeliveryAddressesForm populateShipAndBillAddressForExistingUser(
			DeliveryAddressesForm deliveryAddressForm,
			ProfileForm buyerProfileForm, HttpServletRequest request)
			throws BusinessFailureException {
		boolean sameshipAddredAsUserAddress = deliveryAddressForm
				.isSameShipAddress();

		boolean sameBillAdressAsUserAddress = deliveryAddressForm
				.isSameBillAddress();

		boolean useShippingAddressForBilling = deliveryAddressForm
				.isUseshippingaddress();

		getUserInfo(request);
		DashboardCommonUtil dashboradutil = new DashboardCommonUtil();

		buyerProfileForm = dashboradutil.setAdminProfileDataInForm(userInfo,
				buyerProfileForm);

		if (true == sameshipAddredAsUserAddress) {
			deliveryAddressForm = StoreUserAddrssInShippingAddress(
					deliveryAddressForm, buyerProfileForm);
		}
		if (true == sameBillAdressAsUserAddress) {
			deliveryAddressForm = StoreUserAddrssInBillingAddress(
					deliveryAddressForm, buyerProfileForm);
		}
		if (true == useShippingAddressForBilling) {
			deliveryAddressForm = storeShippingAddrssInBillingAddress(deliveryAddressForm);
		}
		return deliveryAddressForm;
	}

	/**
	 * This method will be called when user chooses same billing address as
	 * shipping address.
	 */

	private DeliveryAddressesForm storeShippingAddrssInBillingAddress(
			DeliveryAddressesForm deliveryAddressForm) {

		deliveryAddressForm
				.setBillAddress(deliveryAddressForm.getShipAddress());
		deliveryAddressForm.setBillcity(deliveryAddressForm.getShipcity());
		deliveryAddressForm.setBillcountryId(deliveryAddressForm
				.getShipcountryId());
		deliveryAddressForm.setBillFirstName(deliveryAddressForm
				.getShipFirstName());
		deliveryAddressForm.setBillLastName(deliveryAddressForm
				.getShipLastName());
		deliveryAddressForm.setBillphone(deliveryAddressForm.getShipphone());
		deliveryAddressForm
				.setBillpincode(deliveryAddressForm.getShippincode());
		deliveryAddressForm.setBillstate(deliveryAddressForm.getShipstate());

		return deliveryAddressForm;

	}

	/**
	 * This method will be called when user chooses billing address as same as
	 * user address.
	 */

	private DeliveryAddressesForm StoreUserAddrssInBillingAddress(
			DeliveryAddressesForm deliveryAddressForm,
			ProfileForm buyerProfileForm) {
		deliveryAddressForm.setBillAddress(buyerProfileForm.getAddress());
		deliveryAddressForm.setBillcity(buyerProfileForm.getCity());
		deliveryAddressForm.setBillcountryId(buyerProfileForm.getCountryId());
		deliveryAddressForm.setBillFirstName(buyerProfileForm
				.getUserFirstName());
		deliveryAddressForm.setBillLastName(buyerProfileForm.getUserLastName());
		deliveryAddressForm.setBillphone(buyerProfileForm.getPhone());
		deliveryAddressForm.setBillpincode(buyerProfileForm.getZipcode());
		deliveryAddressForm.setBillstate(buyerProfileForm.getState());
		return deliveryAddressForm;

	}

	/**
	 * This method will be called when user chooses shipping address as same as
	 * user address.
	 */

	private DeliveryAddressesForm StoreUserAddrssInShippingAddress(
			DeliveryAddressesForm deliveryAddressForm,
			ProfileForm buyerProfileForm) {

		deliveryAddressForm.setShipAddress(buyerProfileForm.getAddress());
		deliveryAddressForm.setShipcity(buyerProfileForm.getCity());
		deliveryAddressForm.setShipcountryId(buyerProfileForm.getCountryId());
		deliveryAddressForm.setShipFirstName(buyerProfileForm
				.getUserFirstName());
		deliveryAddressForm.setShipLastName(buyerProfileForm.getUserLastName());
		deliveryAddressForm.setShipphone(buyerProfileForm.getUserPhone());
		deliveryAddressForm.setShippincode(buyerProfileForm.getZipcode());
		deliveryAddressForm.setShipstate(buyerProfileForm.getState());

		return deliveryAddressForm;

	}

	/**
	 * This method will be called when guest user provide the shipping address
	 * or billing address.
	 */

	private DeliveryAddressesForm populateShipAndBillAddressForGuestUser(
			DeliveryAddressesForm deliveryAddressForm) {
		if (true == deliveryAddressForm.isUseshippingaddress()) {
			deliveryAddressForm = storeShippingAddrssInBillingAddress(deliveryAddressForm);
		}
		return deliveryAddressForm;
	}

	/**
	 * this method create a product order list from list of ordervo stored in
	 * orderList.
	 */

	private List<ProductOrderVO> createProductOrderList(
			HttpServletRequest request) {
		
		long storeId=(long) request.getSession().getAttribute("storeId");
		Map orderListMap = (Map) request.getSession()
				.getAttribute("orderList");
		
		List<OrderVO> orderList=(List<OrderVO>) orderListMap.get(storeId);
		

		List<ProductOrderVO> productOrderList = new ArrayList<>();

		if (orderList != null) {
			Iterator<OrderVO> itr = orderList.iterator();

			while (itr.hasNext()) {

				OrderVO order = (OrderVO) itr.next();
				long productId = order.getProductId();

				ProductTbMaster product = productService.findByID(productId);
				ProductOrderVO productOrder = new ProductOrderVO();
				productOrder.setProduct(product);
				productOrder.setQuantity(order.getQuantity());
				productOrderList.add(productOrder);

			}
		}
		return productOrderList;
	}

	/**
	 * this method create the order.
	 * @param orderForm 
	 * 
	 * @param request
	 */

	private String createOrder(DeliveryAddressesForm deliveryAddressForm,
			OrderForm orderForm, UmTbUser user, List<ProductOrderVO> productOrderList,
			HttpServletRequest request) {

		OrderTbMaster order = new OrderTbMaster();

		String orderId = null;

		java.util.Date date = new java.util.Date();
		order.setDtCrtdAt(DateUtil.getCurrentDateTime());
		order.setDtOrdrDt(new Timestamp(date.getTime()));
		order.setDtPymntAt(new Timestamp(date.getTime()));
		order.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		order.setNmCrtdBy(new BigDecimal(user.getNmUsrId()));

		// need to discuss
		order.setNmPymntGtwyId(new BigDecimal(3333));
		order = calculatePrice(productOrderList, order,orderForm);
		order.setNmUpdtdBy(new BigDecimal(user.getNmUsrId()));
		order.setNmUsrId(new BigDecimal(user.getNmUsrId()));
		// order.setNmUsrId(user.getNmUsrId());

		List<OrderBillingTbDtl> orderBilling = createOrderBillingDetails(
				deliveryAddressForm, user, order);
		order.setOrderBillingTbDtls(orderBilling);

		List<OrderShippingTbDtl> shippingDetails = createOrderShippingDetails(
				deliveryAddressForm, user, order);
		order.setOrderShippingTbDtls(shippingDetails);

		List<OrderProductTbDetail> orderProductDetails = createOrderProductDetails(
				user, order, productOrderList);
		order.setOrderProductTbDetails(orderProductDetails);

		StoreTbMaster store = storeService.findByID(/*
													 * deliveryAddressForm
													 * .getStoreId()
													 */(long) request
				.getSession().getAttribute("storeId"));

		order.setStoreTbMaster(store);

		order.setVcOrdrNmbr("ORD1111");
		/*
		 * order.setVcOrdrSts("Draft"); order.setVcPymntSts("Awaiting");
		 * order.setVcShpmntSts("On Hold");
		 */

		order.setOrderStatusTbMaster(new OrderStatusTbMaster());
		order.setPaymentStatusTbMaster(new PaymentStatusTbMaster());
		order.setShipmentStatusTbMaster(new ShipmentStatusTbMaster());

		
		
		
		orderHandler.create(order);

		orderId = UniqueIdGenerator.getNextId("OR", 1, order.getNmOrdrId());
		order.setVcOrdrNmbr(orderId);
		orderHandler.update(order);
		orderBillingService.create(orderBilling.get(0));
		orderShippingService.create(shippingDetails.get(0));

		Iterator<OrderProductTbDetail> itr = orderProductDetails.iterator();

		while (itr.hasNext()) {
			OrderProductTbDetail orderProductTbDetail = itr.next();
			orderProductDetailservice.create(orderProductTbDetail);

		}

		return orderId;

	}

	/**
	 * This method will calculate total price,discount and net price.
	 * @param orderForm 
	 */

	private OrderTbMaster calculatePrice(List<ProductOrderVO> productOrderList,
			OrderTbMaster order, OrderForm orderForm) {

		Iterator<ProductOrderVO> itr = productOrderList.iterator();

		BigDecimal totalCost = new BigDecimal(0);
		BigDecimal netCost = new BigDecimal(0);
		BigDecimal discount = new BigDecimal(0);

		while (itr.hasNext()) {

			ProductOrderVO productOrderVO = itr.next();
			ProductTbMaster productTbMaster = productOrderVO.getProduct();
			BigDecimal totalSellPrice = productTbMaster.getNmSp().multiply(
					new BigDecimal(productOrderVO.getQuantity()));

			List<PromotionTbMaster> promotionList = productTbMaster
					.getPromotionTbMasters();
			Iterator<PromotionTbMaster> promoItr = promotionList.iterator();
			while (promoItr.hasNext()) {
				PromotionTbMaster promotion = promoItr.next();

				BigDecimal discountType = promotion.getNmDscntTyp();
				String discType = getDiscountType(discountType);
				BigDecimal discountValue = promotion.getNmDscntVl();

				if ("promotion_discount_type_percent".equals(discType)) {
					discount = discount.add(((totalSellPrice
							.multiply(discountValue))
							.divide(new BigDecimal(100))));
				} else if ("promotion_discount_type_cash".equals(discType)) {
					discount = discountValue;
				}

			}
			totalCost = totalCost.add(totalSellPrice);

		}
		netCost = totalCost.subtract(discount);

		order.setNmTtlCst(totalCost);
		order.setNmTtlNtCst(netCost);
		order.setNmDsct(discount);
		orderForm.setDiscount(discount);
		orderForm.setTotalCost(totalCost);
		orderForm.setTotalNetCost(netCost);

		return order;
	}

	/**
	 * This method returns the discount type.
	 */

	private String getDiscountType(BigDecimal discountType) {
		String discType = "";
		if (SBSConstants.promotion_discount_type_cash == discountType
				.intValue()) {
			discType = "promotion_discount_type_cash";
		} else if (SBSConstants.promotion_discount_type_percent == discountType
				.intValue()) {
			discType = "promotion_discount_type_percent";
		}
		return discType;
	}

	/**
	 * This method create order shipping details to be inserted in database .
	 */

	private List<OrderShippingTbDtl> createOrderShippingDetails(
			DeliveryAddressesForm deliveryAddressForm, UmTbUser user,
			OrderTbMaster order) {

		OrderShippingTbDtl orderShipping = new OrderShippingTbDtl();
		List<OrderShippingTbDtl> orderShiipingList = new ArrayList<>();
		java.util.Date date = new java.util.Date();

		orderShipping.setDtCrtdAt(DateUtil.getCurrentDateTime());
		orderShipping.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		orderShipping.setIsDltd(new BigDecimal(0));
		orderShipping.setNmCrtdBy(new BigDecimal(user.getNmUsrId()));
		orderShipping.setNmUpdtdBy(new BigDecimal(user.getNmUsrId()));
		orderShipping.setOrderTbMaster(order);

		long countryId = deliveryAddressForm.getShipcountryId();
		UmTbCntryMstr country = countryService.findByID(countryId);

		orderShipping.setUmTbCntryMstr(country);
		orderShipping.setVcShpAddr(deliveryAddressForm.getShipAddress());
		orderShipping.setVcShpFrstNm(deliveryAddressForm.getShipFirstName());
		orderShipping.setVcShpLstNm(deliveryAddressForm.getShipLastName());
		orderShipping.setVcCty(deliveryAddressForm.getShipcity());
		orderShipping.setVcPhn(deliveryAddressForm.getShipphone());
		orderShipping.setVcSt(deliveryAddressForm.getShipstate());
		orderShipping.setVcZpcd(deliveryAddressForm.getShippincode());
		orderShipping.setNmShpAmt(new BigDecimal(50));
		orderShiipingList.add(orderShipping);

		return orderShiipingList;
	}

	/**
	 * This method create order product details to be inserted in database .
	 */

	private List<OrderProductTbDetail> createOrderProductDetails(UmTbUser user,
			OrderTbMaster order, List<ProductOrderVO> productOrderList) {

		List<OrderProductTbDetail> orderProductDetailList = new ArrayList<>();

		Iterator<ProductOrderVO> itr = productOrderList.iterator();
		java.util.Date date = new java.util.Date();
		while (itr.hasNext()) {

			ProductOrderVO productOrderVO = itr.next();
			ProductTbMaster product = productOrderVO.getProduct();

			OrderProductTbDetail orderProductDetail = new OrderProductTbDetail();
			BigDecimal sellPrice = product.getNmSp();
			orderProductDetail.setDtCrtdAt(DateUtil.getCurrentDateTime());
			orderProductDetail.setDtUpdtdAt(DateUtil.getCurrentDateTime());
			orderProductDetail.setNmCrtdBy(new BigDecimal(user.getNmUsrId()));

			Iterator<PromotionTbMaster> promotionItr = product
					.getPromotionTbMasters().iterator();
			BigDecimal discount = new BigDecimal(0);
			BigDecimal discountValue = new BigDecimal(0);
			while (promotionItr.hasNext()) {

				PromotionTbMaster promotion = promotionItr.next();
				BigDecimal discountType = promotion.getNmDscntTyp();
				String discType = getDiscountType(discountType);
				discountValue = promotion.getNmDscntVl();
				orderProductDetail.setPromotionTbMaster(promotion);

				if ("promotion_discount_type_percent".equals(discType)) {
					discount = discount
							.add(((sellPrice.multiply(discountValue))
									.divide(new BigDecimal(100))));
				} else if ("promotion_discount_type_cash".equals(discType)) {
					discount = discountValue;
				}

			}

			orderProductDetail.setNmCst(product.getNmSp());
			orderProductDetail.setNmTtlCst((product.getNmSp()
					.subtract(discount)).multiply(new BigDecimal(productOrderVO
					.getQuantity())));
			orderProductDetail.setNmDscnt(discountValue);
			orderProductDetail.setNmNtCst(product.getNmSp().subtract(discount));
			orderProductDetail.setNmQnty(new BigDecimal(productOrderVO
					.getQuantity()));

			orderProductDetail.setNmUpdtdBy(new BigDecimal(user.getNmUsrId()));
			orderProductDetail.setOrderTbMaster(order);
			orderProductDetail.setProductTbMaster(product);
			orderProductDetailList.add(orderProductDetail);

		}

		return orderProductDetailList;

	}

	/**
	 * This method create order billing details to be inserted in database .
	 */

	private List<OrderBillingTbDtl> createOrderBillingDetails(
			DeliveryAddressesForm deliveryAddressForm, UmTbUser user,
			OrderTbMaster order) {

		OrderBillingTbDtl orderBilling = new OrderBillingTbDtl();
		List<OrderBillingTbDtl> orderBillingList = new ArrayList<>();
		java.util.Date date = new java.util.Date();

		orderBilling.setDtCrtdAt(DateUtil.getCurrentDateTime());
		orderBilling.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		orderBilling.setIsDltd(new BigDecimal(0));
		orderBilling.setNmCrtdBy(new BigDecimal(user.getNmUsrId()));
		orderBilling.setNmUpdtdBy(new BigDecimal(user.getNmUsrId()));
		orderBilling.setOrderTbMaster(order);

		long countryId = deliveryAddressForm.getBillcountryId();
		UmTbCntryMstr country = countryService.findByID(countryId);

		orderBilling.setUmTbCntryMstr(country);
		orderBilling.setVcBlngAddr(deliveryAddressForm.getBillAddress());
		orderBilling.setVcBlngFrstNm(deliveryAddressForm.getBillFirstName());
		orderBilling.setVcBlngLstNm(deliveryAddressForm.getBillLastName());
		orderBilling.setVcCty(deliveryAddressForm.getBillcity());
		orderBilling.setVcPhn(deliveryAddressForm.getBillphone());
		orderBilling.setVcSt(deliveryAddressForm.getBillstate());
		orderBilling.setVcZpcd(deliveryAddressForm.getBillpincode());
		orderBillingList.add(orderBilling);
		return orderBillingList;

	}

	public ProductTbMaster getProductInfo(long productId)
			throws BusinessFailureException, GenericFailureException {

		return productService.findByID(productId);

	}

	private void getandSetMenuList(HttpServletRequest request,
			UserAuthVO userAuthVO, LoginCommonUtil loginutil)
			throws BusinessFailureException {

		List<UserInfoVO> menulist = adminHandler.getLeftMenuContent(userAuthVO);
		loginutil.storeUserDataInSession(request.getSession(false), menulist,
				userAuthVO);
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

	private UserAuthVO storeSignUpFormInfoToVO(UmTbUser user,
			UserAuthVO userAuthVO, DeliveryAddressesForm deliveryAddressForm) {

		userAuthVO.setUserEmail(user.getVcEml());
		userAuthVO.setUserPassword(user.getVcPswrd());
		userAuthVO.setStoreid(deliveryAddressForm.getStoreId());
		userAuthVO.setRolename("Buyer");

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

	public DeliveryAddressesForm getDeliveryAddressForm() {
		return deliveryAddressForm;
	}

	public void setDeliveryAddressForm(DeliveryAddressesForm deliveryAddressForm) {
		this.deliveryAddressForm = deliveryAddressForm;
	}

	/**
	 * This method add the seleted product to cart .
	 */

	private void addProductToCart(HttpServletRequest request) {

		
		long productId= Long.parseLong(request.getParameter("productId"));
		
		long storeId=(long) request.getSession().getAttribute("storeId");
		
		Map orderListMap = (Map) request.getSession()
				.getAttribute("orderList");
		List<OrderVO> orderList=new ArrayList<>();
		if(orderListMap==null)
		{
			orderListMap=new HashMap();
			
			
		}
		else
		{
		
		 orderList=(List<OrderVO>) orderListMap.get(storeId);
		}
		
		long prodQuantity = 1;
	String quantity=request.getParameter("quantity");
	if(quantity==null || quantity.equals("undefined"))
	{	
		prodQuantity=getProductQuantity(orderList,productId);
		
		
		
	}
	else
	{
		prodQuantity=Long.parseLong(quantity);
	}
	
		
		
	
		if (orderList == null) {
			orderList = new ArrayList<OrderVO>();
			OrderVO orderVO = new OrderVO();
			orderVO.setProductId(productId);
			orderVO.setQuantity(prodQuantity);
			orderList.add(orderVO);
			
			orderListMap.put(storeId,orderList);
			
			request.getSession().setAttribute("orderList", orderListMap);
			orderVO = null;
		} else {
			boolean exist = checkIfProductAlreadyExist(orderList, productId);
			if (exist) {
				deleteProductfromOrderList(request, productId);
				OrderVO orderVO = new OrderVO();
				orderVO.setProductId(productId);
				orderVO.setQuantity(prodQuantity);
				orderList.add(orderVO);
				orderListMap.put(storeId,orderList);				
				request.getSession().setAttribute("orderList", orderListMap);
				orderVO = null;
			} else {
				OrderVO orderVO = new OrderVO();
				orderVO.setProductId(productId);
				orderVO.setQuantity(prodQuantity);
				orderList.add(orderVO);
				orderListMap.put(storeId,orderList);
				request.getSession().setAttribute("orderList", orderListMap);
				orderVO = null;
			}

		}

	}

	private long getProductQuantity(List<OrderVO> orderList, long productId) {
		// TODO Auto-generated method stub
		long quantity=1;
		if(orderList!=null)
		{
		Iterator<OrderVO> ordetItr = orderList.iterator();

		while (ordetItr.hasNext()) {
			OrderVO orderVo = ordetItr.next();
			if (productId == orderVo.getProductId()) {
				quantity= orderVo.getQuantity();
				break;
				
			}

		}
		}
		return quantity;
	}

	/**
	 * This method deletes the product from order list.
	 */

	private void deleteProductfromOrderList(HttpServletRequest request,
			long productId) {
		Map orderListMap=new HashMap();
		 orderListMap = (Map) request.getSession()
				.getAttribute("orderList");
		List<OrderVO> orderList= (List<OrderVO>) orderListMap.get(request.getSession().getAttribute("storeId"));
		
		

		Iterator<OrderVO> itr = orderList.iterator();
		while (itr.hasNext()) {
			OrderVO order = itr.next();
			if (order.getProductId() == productId) {
				itr.remove();

			}
		}
		long storeId=(long) request.getSession().getAttribute("storeId");
		
		orderListMap.put(storeId,orderList);
		
		request.getSession().setAttribute("orderList", orderListMap);
		

	}

	/**
	 * This method checks if selected product is already in cart.
	 */

	private boolean checkIfProductAlreadyExist(List<OrderVO> orderList,
			long productId) {
		boolean exist = false;
		Iterator<OrderVO> ordetItr = orderList.iterator();

		while (ordetItr.hasNext()) {
			OrderVO orderVo = ordetItr.next();
			if (productId == orderVo.getProductId()) {
				exist = true;
				break;
			}

		}

		return exist;

	}

	/**
	 * This method will be called for payment gateway.
	 */

	@RequestMapping(value = "store/jambopay_result.htm")
	public String paymentResult(@ModelAttribute("paymentForm")PaymentForm paymentForm,ModelMap model,HttpServletRequest req,
			final RedirectAttributes redirectAttributes) {

		
	String status=	req.getParameter("ii");
	String orderId=req.getParameter("JP_MERCHANT_ORDERID");
	
	
	if(status.equals("0"))
	{
		return successReturn(paymentForm, req, orderId);
	}
	else if(status.equals("1"))
	{
		return failureReturn();
	}
	else
	{
		return cancelReturn();
		
	}
	
	}

	private String cancelReturn() {
		return "redirect:paymentCancel.htm";
	}

	private String failureReturn() {
		return "redirect:paymentFailure.htm";
	}

	private String successReturn(PaymentForm paymentForm,
			HttpServletRequest req, String orderId) {
		paymentForm.setOrderId(orderId);
		paymentForm.setTransId(req.getParameter("JP_TRANID"));
		paymentForm.setItemName(req.getParameter("JP_ITEM_NAME"));
		paymentForm.setAmount(req.getParameter("JP_AMOUNT"));
		
		HashMap propertiesMap=new HashMap();
		
		propertiesMap.put("vcOrdrNmbr",orderId);
		
		List<OrderTbMaster> orders=orderService.findAllByProperty(propertiesMap);
		OrderTbMaster order=orders.get(0);
	   PaymentStatusTbMaster paymentStatus=	cartHandler.getPaymentStatusById(1);
		
	  order.setPaymentStatusTbMaster(paymentStatus);
		orderHandler.update(order);
		
		req.getSession().removeAttribute("orderList");
		req.getSession().setAttribute("paymentForm",paymentForm);
		
		
		return "redirect:paymentSuccess.htm";
	}
	
	
	
	@RequestMapping(value = "store/paymentFailure.htm")
	public String paymentFailure(ModelMap model,HttpServletRequest req) {

		
	
		return "/usermanagement/paymentFailure";
	}
	
	@RequestMapping(value = "store/paymentSuccess.htm")
	public String paymentSuccess(ModelMap model,HttpServletRequest req) {

		PaymentForm paymentForm	=(PaymentForm)req.getSession().getAttribute("paymentForm");
		model.addAttribute("paymentForm",paymentForm);
		return "/usermanagement/paymentSuccess";
	}
	
	
	@RequestMapping(value = "store/paymentCancel.htm")
	public String paymentCancel(ModelMap model,HttpServletRequest req) {

		
	
		return "/usermanagement/paymentCancel";
	}

	
	
	
	private void storeUserNameInSession(HttpServletRequest request, UmTbUser userInfo) {
		// TODO Auto-generated method stub// TODO Auto-generated method stub
		
		request.getSession().setAttribute("userFirstName", userInfo.getVcFrstNm());
		request.getSession().setAttribute("userLastName",userInfo.getVcLstNm());
	}

	
	
	
	

}
