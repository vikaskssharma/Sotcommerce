/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sbsc.fos.banner.common.BannerVO;
import com.sbsc.fos.banner.web.form.AddBannerForm;
import com.sbsc.fos.banner.web.form.validator.BannerValidator;
import com.sbsc.fos.banner.web.handler.BannerHandler;
import com.sbsc.fos.category.web.handler.CategoryHandler;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.promotion.web.form.CategoryAjaxForm;
import com.sbsc.fos.promotion.web.form.ProductAjaxForm;
import com.sbsc.fos.promotion.web.handler.PromotionHandler;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.FormSearchDataExtract;
import com.sbsc.fos.utils.SBSConstants;
import com.sbsc.fos.web.form.FormSearch;
import com.sbsc.fos.web.form.validator.FormSearchValidator;

/**
 * This class manages all the Banner related operation like Show, Add, Edit and
 * Delete.
 * 
 * @author vaibhav.jain
 * 
 */
@Controller
public class BannerController {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(BannerController.class);

	private static final String MODE_ADD = "Add";

	private static final String MODE_EDIT = "Update";

	private SessionInfo sessionInfo;

	@Autowired
	private PromotionHandler promotionHandler;

	@Autowired
	private BannerHandler bannerHandler;
	
	@Autowired
	private BannerValidator bannerValidator;

	@Autowired
	FormSearchValidator formSearchValidator;

	/**
	 * Ajax call handler to get all the categories.
	 * 
	 * @param request
	 * @return Ajax response as List<CategoryAjaxForm>
	 */
	@RequestMapping(value = "admin/getCategoriesBanner.htm", method = RequestMethod.POST)
	public @ResponseBody
	List<CategoryAjaxForm> getCategoriesBanner(HttpServletRequest request) {
		List<CategoryAjaxForm> ajaxCategoryList = null;
		try {
			ajaxCategoryList = promotionHandler
					.getAllCategories(CommonUtil.getSessionInfo(request));
		} catch (GenericFailureException | BusinessFailureException exp) {
		} catch (ParseException e) {
			logger.error(String
					.format("Error occured while fetching the list of Categories with error :%s",
							e.getMessage()));
		}
		return ajaxCategoryList;
	}

	/**
	 * Ajax call handler to get all the products.
	 * 
	 * @param request
	 * @return Ajax response as List<ProductAjaxForm>
	 */
	@RequestMapping(value = "admin/getProductsBanner.htm", method = RequestMethod.POST)
	public @ResponseBody
	List<ProductAjaxForm> getProductsBanner(HttpServletRequest request) {
		List<ProductAjaxForm> ajaxProductList = null;
		try {
			ajaxProductList = promotionHandler.getAllProducts(Long
					.parseLong(request.getParameter("categoryId")));

		} catch (GenericFailureException | BusinessFailureException exp) {
		} catch (ParseException e) {
			logger.error(String
					.format("Error occured while fetching the list of Products for Category - %s with error :%s",
							request.getParameter("categoryId"), e.getMessage()));
		}
		return ajaxProductList;
	}

	/**
	 * Shows all Banner List by Store
	 */
	@RequestMapping(value = "admin/manageBanner.htm", method = RequestMethod.GET)
	public ModelAndView ShowBannerList(Locale locale, Model model,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		try {
			List<BannerVO> bannerList = bannerHandler.bannerHandle(sessionInfo);
			FormSearch formSearch = new FormSearch();
			modelMap.put("formSearch", formSearch);
			modelMap.put("bannerList", bannerList);
			modelMap.put("allStatuses",getAllStatus());
		} catch (HibernateException hexp) {
			logger.error("Error occured while fetching banners " + hexp);
		}
		return new ModelAndView("banner/banner_listing", modelMap);

	}

	/**
	 * Shows filtered Banner Page.
	 */
	@RequestMapping(value = "admin/manageBanner.htm", method = RequestMethod.POST)
	public ModelAndView BannerFilter(
			@ModelAttribute("formSearch") FormSearch formSearch, Model model,
			BindingResult bindingResult, Locale locale,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException, ParseException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> filter_criteria = null;
		formSearchValidator.validate(formSearch, bindingResult);
		try {
			SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
			if (bindingResult.hasErrors()) {
				logger.info("Invalid Type for filter:" + filter_criteria);
				modelMap.put("formSearch", formSearch);
				modelMap.put("allStatuses",getAllStatus());
				modelMap.put("bannerList",
						bannerHandler.bannerHandle(sessionInfo));

			} else {
				List<BannerVO> bannerList = bannerHandler
						.findAllBannersByFilters(FormSearchDataExtract
								.extractFormData(formSearch), sessionInfo);
				modelMap.put("formSearch", formSearch);
				modelMap.put("allStatuses",getAllStatus());
				modelMap.put("bannerList", bannerList);

			}
		} catch (HibernateException hexp) {
			logger.error("Error occured while fetching filtered details "
					+ hexp);
			String errorMessage = "Error occured : " + hexp.getMessage();
			modelMap.put("allStatuses",getAllStatus());
			modelMap.put("formSearch", formSearch);
			modelMap.put("errorMessage", errorMessage);
		}
		return new ModelAndView("banner/banner_listing", modelMap);
	}
	
	
	/**
	 * Shows the Banner List by Store on cancel event.
	 */
	@RequestMapping(value = "admin/manageBanner.htm", method = RequestMethod.POST, params = "cancel")
	public ModelAndView showPromotionsOnCancel(Locale locale, Model model,
			HttpServletRequest request) {

		sessionInfo = CommonUtil.getSessionInfo(request);
		FormSearch formSearch = new FormSearch();
		List<BannerVO> bannerList = null;
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String errorMessage = null;
		try {
			bannerList = bannerHandler.bannerHandle(sessionInfo);
		} catch (GenericFailureException e) {
			logger.error(String
					.format("Error occured while showing list of promotion with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while showing list of promotion with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		}
		modelMap.put("allStatuses",getAllStatus());
		modelMap.put("formSearch", formSearch);
		modelMap.put("bannerList", bannerList);

		return new ModelAndView("banner/banner_listing", modelMap);
	}
	
	

	/*
	 * This works as a controller to delete banner
	 */
	@RequestMapping(value = "admin/manageBanner.htm", method = RequestMethod.POST, params = "deleteBanner")
	public ModelAndView DeleteBanner(Locale locale, Model model,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		Long bannerId = null;
		try {

			bannerId = Long.parseLong(request.getParameter("bannerId"));
			logger.info("Deleting a banner with ID -" + bannerId);
			modelMap.put("bannerDeleted", bannerHandler.deleteBanner(bannerId));
			modelMap.put("formSearch", new FormSearch());
			modelMap.put("allStatuses",getAllStatus());
			modelMap.put("bannerList", bannerHandler.bannerHandle(sessionInfo));

		} catch (Exception e) {
			logger.error("Error occured while deleting banner " + e);
			String errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		}

		return new ModelAndView("banner/banner_listing", modelMap);
	}

	/**
	 * Shows the Edit Banner Form to update an existing banner.
	 * 
	 * @param request
	 *            The HttpServletRequest which have the user session
	 *            information.
	 * @param model
	 *            The holder of the Spring model Attributes.
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/manageBanner.htm", method = RequestMethod.POST, params = "editBanner")
	public ModelAndView showEditBannerPage(HttpServletRequest request,
			@ModelAttribute("addBannerForm") AddBannerForm addBannerForm,
			Model model, BindingResult bindingResult) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String errorMessage = null;
		sessionInfo = CommonUtil.getSessionInfo(request);

		try {
			try {
				addBannerForm = bannerHandler.setBannerVO(
						Long.valueOf(request.getParameter("bannerId")),
						sessionInfo);

				/*addBannerForm.setCategoryAjaxForms(bannerHandler
						.getAllCategories(sessionInfo));*/
				
				addBannerForm.setCategoriesHierarchyTree(bannerHandler.getAllCategories(sessionInfo));

				addBannerForm.setApplyToPages(bannerHandler.getAllPages());

				addBannerForm.setApplyToPagePositions(bannerHandler
						.getAllPagePostion());

				//addBannerForm.setAllStatuses(getAllStatus());

				addBannerForm.setStoreId(sessionInfo.getStoreId());

				addBannerForm.setMode(MODE_EDIT);
				
				modelMap.put("addBannerForm", addBannerForm);

				return new ModelAndView("/banner/add_article", modelMap);

			} catch (ParseException e) {
				logger.error(String
						.format("Error occured while showing Edit Banner Page with error :%s",
								e.getMessage()));
				errorMessage = "Error occured : " + e.getMessage();
			}
			addBannerForm.setAllStatuses(getAllStatus());
		} catch (GenericFailureException | BusinessFailureException e) {
			logger.error(String
					.format("Error occured while showing Edit Banner Page with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while showing Edit Banner Page with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
		}
		
		modelMap.put("formSearch", new FormSearch());
		modelMap.put("bannerList", bannerHandler.bannerHandle(sessionInfo));
		modelMap.put("allStatuses",getAllStatus());
		modelMap.put("errorMessage", errorMessage);
		return new ModelAndView("banner/banner_listing", modelMap);

	}

	/**
	 * This controller edits a Banner. This Action is bind to the "Edit Banner"
	 * button of the page.
	 * 
	 * @param request
	 *            The HttpServletRequest which have the user session
	 *            information.
	 * @param addBannerForm
	 *            The Form which have the New Banner details.
	 * @param model
	 *            The holder of the Spring model attributes.
	 * @param bindingResult
	 *            The result holder of the Srping form validation
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/manageBanner.htm", method = RequestMethod.POST, params = MODE_EDIT)
	public ModelAndView editBanner(
			@ModelAttribute("addBannerForm") AddBannerForm addBannerForm,
			Model model, BindingResult bindingResult, HttpServletRequest request) {

		String errorMessage = null;
		sessionInfo = CommonUtil.getSessionInfo(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			bannerValidator.validate(addBannerForm, bindingResult);

			if (bindingResult.hasErrors()) {

				addBannerForm.setCategoryAjaxForms(promotionHandler
						.getAllCategories(sessionInfo));

				addBannerForm.setApplyToPages(bannerHandler.getAllPages());

				addBannerForm.setApplyToPages(bannerHandler.getAllPages());

				addBannerForm.setApplyToPagePositions(bannerHandler
						.getAllPagePostion());

				addBannerForm.setAllStatuses(getAllStatus());

				addBannerForm.setStoreId(sessionInfo.getStoreId());

				addBannerForm.setMode(MODE_EDIT);

				modelMap.put("addBannerForm", addBannerForm);

				return new ModelAndView("/banner/add_article", modelMap);

			}
			bannerHandler.updateBanner(addBannerForm, sessionInfo, request);

		} catch (GenericFailureException | ParseException e) {
			logger.error(String.format(
					"Error occured while editing a new Banner with error :%s",
					e.getMessage()));
			errorMessage = "Record updation failed.Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		} catch (Exception e) {
			logger.error(String.format(
					"Error occured while editing a new Banner with error :%s",
					e.getMessage()));
			errorMessage = "Record updation failed.Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		}
		modelMap.put("formSearch", new FormSearch());
		modelMap.put("bannerList", bannerHandler.bannerHandle(sessionInfo));
		modelMap.put("allStatuses",getAllStatus());
		return new ModelAndView("banner/banner_listing", modelMap);
	}

	/**
	 * Shows the Add Banner Form to add a new banner for that Store.
	 * 
	 * @param request
	 *            The HttpServletRequest which have the user session
	 *            information.
	 * @param model
	 *            The holder of the Spring model Attributes.
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/showBannerAddEditPage.htm", method = RequestMethod.POST, params = "addBanner")
	public ModelAndView showAddBannerPage(HttpServletRequest request,
			@ModelAttribute("addBannerForm") AddBannerForm addBannerForm,
			Model model, BindingResult bindingResult) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		String errorMessage = null;

		sessionInfo = CommonUtil.getSessionInfo(request);
		try {
			/*addBannerForm.setCategoryAjaxForms(bannerHandler
					.getAllCategories(sessionInfo));*/

			addBannerForm.setApplyToPages(bannerHandler.getAllPages());

			addBannerForm.setApplyToPages(bannerHandler.getAllPages());

			addBannerForm.setApplyToPagePositions(bannerHandler
					.getAllPagePostion());

			//addBannerForm.setAllStatuses(getAllStatus());
			addBannerForm.setCategoriesHierarchyTree(bannerHandler.getAllCategories(sessionInfo));
			
			addBannerForm.setStatus(SBSConstants.BANNER_STATUS.Active.name());

			addBannerForm.setStoreId(sessionInfo.getStoreId());

			addBannerForm.setMode(MODE_ADD);

			modelMap.put("addBannerForm", addBannerForm);
			
			return new ModelAndView("/banner/add_article", modelMap);

		} catch (ParseException | BusinessFailureException e) {
			logger.error(String
					.format("Error occured while showing Add Banner Page with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while showing Add Banner Page with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
		}

		modelMap.put("formSearch", new FormSearch());
		modelMap.put("bannerList", bannerHandler.bannerHandle(sessionInfo));
		modelMap.put("errorMessage", errorMessage);
		modelMap.put("allStatuses",getAllStatus());
		return new ModelAndView("banner/banner_listing", modelMap);
	}

	/**
	 * This Creates a new Banner. This Action is bind to the "Add Banner" button
	 * of the page.
	 * 
	 * @param request
	 *            The HttpServletRequest which have the user session
	 *            information.
	 * @param addBannerForm
	 *            The Form which have the New Banner details.
	 * @param model
	 *            The holder of the Spring model attributes.
	 * @param bindingResult
	 *            The result holder of the Srping form validation
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/manageBanner.htm", method = RequestMethod.POST, params = MODE_ADD)
	public ModelAndView createBanner(
			@ModelAttribute("addBannerForm") AddBannerForm addBannerForm,
			Model model, BindingResult bindingResult, HttpServletRequest request) {

		Long bannerId = null;

		SessionInfo sessioninfo = CommonUtil.getSessionInfo(request);

		String errorMessage = null;

		Map<String, Object> modelMap = new HashMap<String, Object>();

		try {

			bannerValidator.validate(addBannerForm, bindingResult);

			if (bindingResult.hasErrors()) {

				addBannerForm.setCategoryAjaxForms(promotionHandler
						.getAllCategories(sessioninfo));

				addBannerForm.setApplyToPages(bannerHandler.getAllPages());

				addBannerForm.setApplyToPages(bannerHandler.getAllPages());

				addBannerForm.setApplyToPagePositions(bannerHandler
						.getAllPagePostion());

				addBannerForm.setAllStatuses(getAllStatus());

				addBannerForm.setStoreId(sessionInfo.getStoreId());

				addBannerForm.setMode(MODE_ADD);

				modelMap.put("addBannerForm", addBannerForm);

				return new ModelAndView("/banner/add_article", modelMap);

			}

			bannerId = bannerHandler.createNewBanner(addBannerForm,
					CommonUtil.getSessionInfo(request), request);

			modelMap.put("bannerList", bannerHandler.bannerHandle(sessionInfo));

		} catch (BusinessFailureException | GenericFailureException
				| ParseException e) {
			logger.error(String.format(
					"Error occured while creating a new Banner with error :%s",
					e.getMessage()));
			errorMessage = "Error occured while creating a new Banner : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		} catch (Exception e) {
			logger.error(String.format(
					"Error occured while creating a new Banner with error :%s",
					e.getMessage()));
			errorMessage = "Error occured while creating a new Banner : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		}

		modelMap.put("formSearch", new FormSearch());
		modelMap.put("allStatuses",getAllStatus());
		return new ModelAndView("banner/banner_listing", modelMap);
	}
	
	
	/*
	 * Activate the Banner
	 */
	@RequestMapping(value = "admin/manageBanner.htm", method = RequestMethod.POST, params = "activateBanner")
	public ModelAndView activateBanner(
			 @ModelAttribute("formSearch") FormSearch formSearch, Locale locale,
				BindingResult bindingResult, HttpServletRequest request){
		String errorMessage = null;
		Map<String, Object> modelMap = new HashMap<String, Object>();
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		try{
			long bannerId = Long.parseLong(request.getParameter("bannerId"));
			bannerHandler.setBannerStatus(bannerId, SBSConstants.BANNER_STATUS.Active.name());
			
		}catch(Exception exp){
			errorMessage = "Error occured : " + exp.getMessage();
			modelMap.put("errorMessage", errorMessage);
			logger.error(String
					.format("Error occured while changing Banner status to active :" + errorMessage, exp));
		}
		modelMap.put("bannerList", bannerHandler.bannerHandle(sessionInfo));
		return new ModelAndView("banner/banner_listing", modelMap);
	}
	
	
	
	/*
	 * De-Activate the Banner
	 */
	@RequestMapping(value = "admin/manageBanner.htm", method = RequestMethod.POST, params = "deActivateBanner")
	public ModelAndView deActivateBanner(
			 @ModelAttribute("formSearch") FormSearch formSearch, Locale locale,
				BindingResult bindingResult, HttpServletRequest request){
		String errorMessage = null;
		Map<String, Object> modelMap = new HashMap<String, Object>();
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		try{
			long bannerId = Long.parseLong(request.getParameter("bannerId"));
			bannerHandler.setBannerStatus(bannerId, SBSConstants.BANNER_STATUS.InActive.name());
			
		}catch(Exception exp){
			errorMessage = "Error occured : " + exp.getMessage();
			modelMap.put("errorMessage", errorMessage);
			logger.error(String
					.format("Error occured while changing Banner status to In-Active :" + errorMessage, exp));
		}
		modelMap.put("bannerList", bannerHandler.bannerHandle(sessionInfo));
		return new ModelAndView("banner/banner_listing", modelMap);
	}
	
	

	/**
	 * Provides the statuses (<b>Active</b>, <b>InActive</b>) in the form of Map
	 * which a Category can have.
	 * 
	 * @return A HashMap
	 */
	private HashMap<String, String> getAllStatus() {

		HashMap<String, String> allStatus = new HashMap<String, String>();

		allStatus.put(SBSConstants.BANNER_STATUS.InActive.toString(),
				SBSConstants.BANNER_STATUS.InActive.toString());

		allStatus.put(SBSConstants.BANNER_STATUS.Active.toString(),
				SBSConstants.BANNER_STATUS.Active.toString());

		return allStatus;
	}

	
}
