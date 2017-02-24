/**
 * 
 */
package com.sot.ecommerce.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.promotion.commons.PromotionVO;
import com.sbsc.fos.promotion.web.form.AddPromotionForm;
import com.sbsc.fos.promotion.web.form.CategoryAjaxForm;
import com.sbsc.fos.promotion.web.form.ProductAjaxForm;
import com.sbsc.fos.promotion.web.form.validator.PromotionValidator;
import com.sbsc.fos.promotion.web.handler.PromotionHandler;
import com.sbsc.fos.utils.FormSearchDataExtract;
import com.sbsc.fos.web.form.FormSearch;
import com.sbsc.fos.web.form.validator.FormSearchValidator;

/**
 * @author vaibhav.jain
 * 
 */
@Controller
public class PromotionController {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(PromotionController.class);

	private static final String MODE_ADD = "Add";

	private static final String MODE_EDIT = "Update";

	private SessionInfo sessionInfo;

	@Autowired
	private PromotionHandler promotionHandler;

	@Autowired
	FormSearchValidator formSearchValidator;

	/** The Validator */
	@Autowired
	private PromotionValidator promotionValidator;

	/**
	 * Ajax call handler to get all the categories.
	 * 
	 * @param request
	 * @return Ajax response as List<CategoryAjaxForm>
	 */
	@RequestMapping(value = "admin/getCategories.htm", method = RequestMethod.POST)
	public @ResponseBody
	List<CategoryAjaxForm> getCategories(HttpServletRequest request) {
		List<CategoryAjaxForm> ajaxCategoryList = null;
		try {

			ajaxCategoryList = promotionHandler.getAllCategories(sessionInfo);

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
	@RequestMapping(value = "admin/getProducts.htm", method = RequestMethod.POST)
	public @ResponseBody
	List<ProductAjaxForm> getProducts(HttpServletRequest request) {
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
	 * Shows the Promotion List by Store
	 */
	@RequestMapping(value = "admin/managePromotions.htm", method = RequestMethod.GET)
	public ModelAndView showPromotions(Locale locale, Model model,
			HttpServletRequest request) {
		List<PromotionVO> promotionList = null;
		FormSearch formSearch = new FormSearch();
		String errorMessage = null;
		sessionInfo = getSessionInfo(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			promotionList = promotionHandler.getAllPromotionList(sessionInfo);
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while showing list of promotion with error :%s",
							e.getMessage()));
		}
		modelMap.put("allStatuses",getAllStatus());
		modelMap.put("formSearch", formSearch);
		modelMap.put("promotionList", promotionList);

		return new ModelAndView("/promotion/view_promotion", modelMap);
	}

	/**
	 * Shows filtered Promotion Page.
	 */
	@RequestMapping(value = "admin/promotion_list_filter.htm", method = RequestMethod.POST)
	public ModelAndView PromotionListFilter(
			@ModelAttribute("formSearch") FormSearch formSearch,
			BindingResult bindingResult, HttpServletRequest request) {

		sessionInfo = getSessionInfo(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> filter_criteria = null;
		String errorMessage = null;
		try {
			formSearchValidator.validate(formSearch, bindingResult);
			if (bindingResult.hasErrors()) {
				logger.info("Invalid Type for filter:" + filter_criteria);
				modelMap.put("formSearch", formSearch);
				modelMap.put("detailList",
						promotionHandler.getAllPromotionList(sessionInfo));

				String selectedStatus = formSearch.getFilters().get(5).getValue();
				modelMap.put("selectedStatus",selectedStatus);
				modelMap.put("allStatuses",getAllStatus());

				return new ModelAndView("/promotion/view_promotion", modelMap);
			} else {
				List<PromotionVO> promotionList = promotionHandler
						.findAllPromotionListByFilters(FormSearchDataExtract
								.extractFormData(formSearch), sessionInfo);
				modelMap.put("promotionList", promotionList);
				String selectedStatus = formSearch.getFilters().get(5).getValue();
				modelMap.put("selectedStatus",selectedStatus);
				modelMap.put("allStatuses",getAllStatus());

			}
		} catch (ParseException | GenericFailureException e) {
			logger.error(String
					.format("Error occured while showing filtered list of promotion with error :%s",
							e.getMessage()));
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while showing filtered list of promotion with error :%s",
							e.getMessage()));
		}
		return new ModelAndView("/promotion/view_promotion", modelMap);
	}

	/**
	 * Shows the Add Promotion Page.
	 */
	@RequestMapping(value = "admin/promotion_list_filter.htm", method = RequestMethod.POST, params = "addPromotion")
	public ModelAndView addNewPromotion(
			HttpServletRequest request,
			@ModelAttribute("addPromotionForm") AddPromotionForm addPromotionForm,
			Model model, BindingResult bindingResult) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String errorMessage = null;
		try {
			addPromotionForm.setCategoryAjaxForms(promotionHandler
					.getAllCategories(sessionInfo));
			addPromotionForm.setAllStatuses(getAllStatus());
			addPromotionForm.setPageMode(MODE_ADD);
			modelMap.put("addPromotionForm", addPromotionForm);
		} catch (ParseException | BusinessFailureException e) {
			logger.error(String
					.format("Error occured while showing Add Promotion Page with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while showing Add Promotion Page with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		}

		return new ModelAndView("/promotion/add_promotion", modelMap);

	}

	/**
	 * Shows the Edit Promotion Page.
	 */
	@RequestMapping(value = "admin/promotion_list_filter.htm", method = RequestMethod.POST, params = "editPromotion")
	public ModelAndView editPromotion(
			HttpServletRequest request,
			@ModelAttribute("addPromotionForm") AddPromotionForm addPromotionForm,
			Model model, BindingResult bindingResult) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		sessionInfo = getSessionInfo(request);
		String errorMessage = null;
		try {
			try {
				addPromotionForm = promotionHandler.setPromotionVO(
						addPromotionForm,
						Long.valueOf(request.getParameter("promoId")),
						sessionInfo);
				addPromotionForm.setPageMode(MODE_EDIT);
			} catch (ParseException e) {
				logger.error(String
						.format("Error occured while showing Edit Promotion Page with error :%s",
								e.getMessage()));
				errorMessage = "Error occured : " + e.getMessage();
				modelMap.put("errorMessage", errorMessage);
			}
			addPromotionForm.setAllStatuses(getAllStatus());
		} catch (GenericFailureException | BusinessFailureException e) {
			logger.error(String
					.format("Error occured while showing Edit Promotion Page with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while showing Edit Promotion Page with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		}
		modelMap.put("addPromotionForm", addPromotionForm);
		return new ModelAndView("/promotion/add_promotion", modelMap);

	}

	/**
	 * Shows the Promotion List by Store on cancel event.
	 */
	@RequestMapping(value = "admin/managePromotions.htm", method = RequestMethod.POST, params = "cancel")
	public ModelAndView showPromotionsOnCancel(Locale locale, Model model,
			HttpServletRequest request) {

		sessionInfo = getSessionInfo(request);
		FormSearch formSearch = new FormSearch();
		List<PromotionVO> promotionList = null;
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String errorMessage = null;
		try {
			promotionList = promotionHandler.getAllPromotionList(sessionInfo);
		} catch (GenericFailureException e) {
			logger.error(String
					.format("Error occured while showing list of promotion with error :%s",
							e.getMessage()));
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while showing list of promotion with error :%s",
							e.getMessage()));
		}
		
		modelMap.put("allStatuses",getAllStatus());
		modelMap.put("formSearch", formSearch);
		modelMap.put("promotionList", promotionList);

		return new ModelAndView("/promotion/view_promotion", modelMap);
	}

	/**
	 * This Creates a new Promotion. This Action is bind to the "Add Promotion"
	 * button of the page.
	 * 
	 * @param request
	 *            The HttpServletRequest which have the user session
	 *            information.
	 * @param addPromotionForm
	 *            The Form which have the New Promotion details.
	 * @param model
	 *            The holder of the Spring model attributes.
	 * @param bindingResult
	 *            The result holder of the Srping form validation
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/managePromotions.htm", method = RequestMethod.POST, params = MODE_ADD)
	public ModelAndView createPromotion(
			@ModelAttribute("addPromotionForm") AddPromotionForm addPromotionForm,
			Model model, BindingResult bindingResult, HttpServletRequest request) {

		Long promotionId = null;
		sessionInfo = getSessionInfo(request);
		String errorMessage = null;
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			promotionValidator.validate(addPromotionForm, bindingResult);
			if (bindingResult.hasErrors()) {

				addPromotionForm.setAllStatuses(getAllStatus());

				addPromotionForm.setCategoryAjaxForms(promotionHandler
						.getAllCategories(sessionInfo));
				addPromotionForm.setProductAjaxForms(promotionHandler
						.getAllProducts(Long.valueOf(addPromotionForm
								.getCategoryId())));
				addPromotionForm.setPageMode(MODE_ADD);
				modelMap.put("addPromotionForm", addPromotionForm);
				modelMap.put("allStatuses",getAllStatus());
				return new ModelAndView("/promotion/add_promotion", modelMap);

			}
			promotionId = promotionHandler.createNewPromotion(addPromotionForm,
					sessionInfo);
		} catch (BusinessFailureException | GenericFailureException
				| ParseException e) {
			logger.error(String
					.format("Error occured while creating a new Promotion with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while creating a new Promotion with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		}
		modelMap.put("allStatuses",getAllStatus());
		modelMap.put("formSearch", new FormSearch());
		modelMap.put("promotionList",
				promotionHandler.getAllPromotionList(sessionInfo));

		return new ModelAndView("/promotion/view_promotion", modelMap);
	}

	/**
	 * This controller edits a Promotion. This Action is bind to the
	 * "Edit Promotion" button of the page.
	 * 
	 * @param request
	 *            The HttpServletRequest which have the user session
	 *            information.
	 * @param addPromotionForm
	 *            The Form which have the New Promotion details.
	 * @param model
	 *            The holder of the Spring model attributes.
	 * @param bindingResult
	 *            The result holder of the Srping form validation
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/managePromotions.htm", method = RequestMethod.POST, params = MODE_EDIT)
	public ModelAndView editPromotion(
			@ModelAttribute("addPromotionForm") AddPromotionForm addPromotionForm,
			Model model, BindingResult bindingResult, HttpServletRequest request) {

		Long promotionId = null;
		String errorMessage = null;
		sessionInfo = getSessionInfo(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			promotionValidator.validate(addPromotionForm, bindingResult);

			if (bindingResult.hasErrors()) {
				addPromotionForm.setAllStatuses(getAllStatus());
				if(addPromotionForm.getMode().toString().equals("3")){
					addPromotionForm.setCategoryAjaxForms(promotionHandler
							.getAllCategories(sessionInfo));
					addPromotionForm.setProductAjaxForms(promotionHandler
							.getAllProducts(Long.valueOf(addPromotionForm
									.getCategoryId())));
				}
				addPromotionForm.setPageMode(MODE_EDIT);
				modelMap.put("addPromotionForm", addPromotionForm);

				return new ModelAndView("/promotion/add_promotion", modelMap);

			}
			promotionHandler.updatePromotion(addPromotionForm, sessionInfo);
		} catch (GenericFailureException | ParseException e) {
			logger.error(String
					.format("Error occured while editing a new Promotion with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while editing a new Promotion with error :%s",
							e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		}
		modelMap.put("formSearch", new FormSearch());
		modelMap.put("allStatuses",getAllStatus());
		modelMap.put("promotionList",
				promotionHandler.getAllPromotionList(sessionInfo));

		return new ModelAndView("/promotion/view_promotion", modelMap);
	}

	/**
	 * Delete a promotion for a specific promotion ID.
	 * 
	 * @return A HashMap
	 */
	@RequestMapping(value = "admin/promotion_list_filter.htm", method = RequestMethod.POST, params = "deletePromotion")
	public ModelAndView DeletePromotion(
			@ModelAttribute("promotionVO") PromotionVO promotionVO,
			Model model, HttpServletRequest request) {

		String errorMessage = null;
		sessionInfo = getSessionInfo(request);
		FormSearch formSearch = new FormSearch();
		Map<String, Object> modelMap = new HashMap<String, Object>();

		long promoId = Long.parseLong(request.getParameter("promoId"));
		logger.info("soft deleting a promotion with ID -" + promoId);
		try {
			List<PromotionVO> promotionList = promotionHandler.deletePromotion(
					promoId, sessionInfo);
			errorMessage = "Promotion deleted successfully.";
			modelMap.put("errorMessage", errorMessage);
			modelMap.put("formSearch", formSearch);
			modelMap.put("promotionList", promotionList);
		} catch (GenericFailureException | BusinessFailureException e) {
			logger.error(String.format(
					"Error occured while deleting a Promotion with error :%s",
					e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		} catch (Exception e) {
			logger.error(String.format(
					"Error occured while deleting a Promotion with error :%s",
					e.getMessage()));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		}
		modelMap.put("allStatuses",getAllStatus());
		return new ModelAndView("/promotion/view_promotion", modelMap);

	}

	/**
	 * Provides the statuses (<b>Active</b>, <b>InActive</b>) in the form of Map
	 * which a Category can have.
	 * 
	 * @return A HashMap
	 */
	private HashMap<String, String> getAllStatus() {

		HashMap<String, String> allStatus = new HashMap<String, String>();

		allStatus.put("InActive", "InActive");

		allStatus.put("Active" , "Active");

		return allStatus;
	}

	/**
	 * Get Session info
	 * 
	 * @param request
	 * @return
	 */
	private SessionInfo getSessionInfo(HttpServletRequest request) {

		return new SessionInfo((Long) request.getSession().getAttribute(
				"userId"), (Long) request.getSession().getAttribute("storeId"));

	}

}
