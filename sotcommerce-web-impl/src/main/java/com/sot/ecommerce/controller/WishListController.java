package com.sot.ecommerce.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.um.service.StoreService;
import com.sbsc.fos.um.service.UserService;
import com.sbsc.fos.um.web.form.UserSignInForm;
import com.sbsc.fos.um.web.handler.AdminDashboardHandler;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.FormSearchDataExtract;
import com.sbsc.fos.web.form.FormSearch;
import com.sbsc.fos.web.form.validator.FormSearchValidator;
import com.sbsc.fos.wishlist.common.WishListVO;
import com.sbsc.fos.wishlist.web.form.RedirectForm;
import com.sbsc.fos.wishlist.web.handler.WishListHandler;

/**
 * Controller for WishList Operations : 1. Display all WishLists 2. Delete a
 * Wish from the List 3. Filter the WishList
 * 
 * 
 * @author Abhishek.Vishnoi
 * 
 */
@Controller
public class WishListController {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(WishListController.class);

	private long usrID;

	private long storeID;

	private SessionInfo sessionInfo;

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private AdminDashboardHandler adminHandler;

	@Autowired
	private WishListHandler wishListHandler;

	@Autowired
	FormSearchValidator formSearchValidator;
	
	@Autowired
	private MessageSource messageSource;


	
	
	/**
	 * Controller Mapping to Display the Wish List for a unique User and Store.
	 * 
	 * @param allOrdersForm
	 * @param locale
	 * @param model
	 * @param request
	 * @return Model&View 
	 * @throws GenericFailureException
	 * @throws BusinessFailureException
	 */
	@RequestMapping(value = "store/wish_list.htm", method = RequestMethod.GET)
	public ModelAndView WishList(
			@ModelAttribute("wishListVO") WishListVO wishListVO, Locale locale,
			Model model, HttpServletRequest request)
 {

		try {
			
			if (getSessionInfo(request) != null) {

				sessionInfo = getSessionInfo(request);

				FormSearch formSearch = new FormSearch();

				Map<String, Object> modelMap = new HashMap<String, Object>();

				List<WishListVO> wishList = wishListHandler
						.getwishListbyID(sessionInfo);
				logger.info("fetching WishList for userID -" + usrID
						+ "and Store ID -" + storeID);

				modelMap.put("formSearch", formSearch);
				modelMap.put("wishList", wishList);
				return new ModelAndView("/wishlist/wish_list", modelMap);
			} else {

				return new ModelAndView("404");

			}

		} catch (GenericFailureException gexp) {
			logger.error("Error occured while fetching wishlist " + gexp);
			gexp.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Controller Mapping to Select a WishList record on Id basis and delete it.
	 * 
	 * @param allOrdersForm
	 * @param locale
	 * @param model
	 *            wishListVO
	 * @param request
	 * @return
	 * @throws GenericFailureException
	 * @throws BusinessFailureException
	 */
	@RequestMapping(value = "store/wish_list_filter.htm", method = RequestMethod.POST, params = "deleteWish")
	public ModelAndView DeleteWish(
			@ModelAttribute("wishListVO") WishListVO wishListVO, Model model,
			HttpServletRequest request) {
		
			

		try {
			sessionInfo = getSessionInfo(request);
			FormSearch formSearch = new FormSearch();
			Map<String, Object> modelMap = new HashMap<String, Object>();
			long wishid = Long.parseLong(request.getParameter("wishListID"));
			logger.info("deleting a wish with ID -" + wishid);
			List<WishListVO> wishList = wishListHandler.deleteWish(wishid,
					sessionInfo);
			modelMap.put("formSearch", formSearch);
			modelMap.put("wishList", wishList);
			modelMap.put("deleteSuccess", messageSource.getMessage("store.wishlist.delete",null,null));
			return new ModelAndView("/wishlist/wish_list", modelMap);
		
		}catch (BusinessFailureException bexp) {
			
			logger.error("Business Error occured in deleting a wish from wishList " + bexp);
			bexp.printStackTrace();
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		return null;

	}
	


	/**
	 * Controller Mapping to Filter the WishList
	 * 
	 * @param locale
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws GenericFailureException
	 * @throws BusinessFailureException
	 * @throws ParseException
	 */
	@RequestMapping(value = "store/wish_list_filter.htm", method = RequestMethod.POST)
	public ModelAndView WishListFilter(
			@ModelAttribute("formSearch") FormSearch formSearch,
			BindingResult bindingResult, HttpServletRequest request) 
			 {
		sessionInfo = getSessionInfo(request);
		ModelAndView modelAndView = null;
		
	try {
			//Map<String, Object> modelMap = new HashMap<String, Object>();
			Map<String, Object> filter_criteria = null;
			formSearchValidator.validate(formSearch, bindingResult);
			modelAndView = new ModelAndView("/wishlist/wish_list");
			
			if (bindingResult.hasErrors()) {
				logger.info("Invalid Type for filter:" + filter_criteria);
				modelAndView.addObject("formSearch", formSearch);
				modelAndView.addObject("wishList",wishListHandler.getwishListbyID(sessionInfo));
				return modelAndView;
			} else {
				List<WishListVO> wishList;
			
					
			   wishList = wishListHandler.findAllWishListByFilters(FormSearchDataExtract
										.extractFormData(formSearch), sessionInfo);
					
				
				modelAndView.addObject("wishList", wishList);
				return modelAndView;
			}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		return modelAndView;
		}

	
	
	/**
	 * Controller Mapping to Add to wishList
	 * 
	 * @param locale
	 * @param request
	 * @return
	 * @throws GenericFailureException
	 * @throws BusinessFailureException
	 * @throws ParseException
	 */
	@RequestMapping(value = "store/add_wish.htm")
	public ModelAndView addWishList(
			@ModelAttribute("formSearch") FormSearch formSearch,
			@ModelAttribute("userSignInForm") UserSignInForm userSignInForm,
			@ModelAttribute("redirectForm") RedirectForm redirectForm,
			Model model, final RedirectAttributes redirectAttributes,
			BindingResult bindingResult, HttpServletRequest request) {
		String	prodAndVaraintId =null;
		String prodId = null;
		//Map prodAndVaraintIds = null;
		CommonUtil commonUtil =new CommonUtil();
		HashMap<String, Long> ids;
		if (request.getParameter("prod_Id") == null) {
				  // simple call redirection for guest user.
			prodAndVaraintId = redirectForm.getProdId();
			ids=commonUtil.getProductandVariantIds(prodAndVaraintId);
			logger.info("Add to wishList simple call redirection for guest user.");
		} else { // for ajax call for signed in user.
			//prodAndVaraintIds=	parseProdId(request.getParameter("prodId"));
			prodId = request.getParameter("prod_Id");
			
			ids=commonUtil.getProductandVariantIds(prodId);		
			logger.info("Add to wishList for ajax call for signed in user.");
		}

		sessionInfo = getSessionInfo(request);

		if (sessionInfo.getUserId() == null) {

			System.out.println("not signed in : : please sign in to continue ");

		}
		wishListHandler.addWish(ids, sessionInfo);
		
		
		redirectForm.setProdId(prodAndVaraintId);
		redirectAttributes.addFlashAttribute("redirectForm", redirectForm);
		
		
			return new ModelAndView("redirect:productdetails.htm");
		

		//return new ModelAndView("redirect:productdetails.htm");
	}

	

	/**
	 * Provides the User and Store information from Session.
	 * 
	 * @param request
	 *            The HttpServletRequest
	 * 
	 * @return The SessionInfo object containing UserId and StoreId
	 */
	private SessionInfo getSessionInfo(HttpServletRequest request) {

		return new SessionInfo((Long) request.getSession().getAttribute(
				"userId"), (Long) request.getSession().getAttribute("storeId"));
	}

}
