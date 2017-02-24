/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import com.sbsc.fos.category.commons.SectionVO;
import com.sbsc.fos.category.web.form.AddCategoryForm;
import com.sbsc.fos.category.web.form.AddSectionForm;
import com.sbsc.fos.category.web.form.ManageTableForm;
import com.sbsc.fos.category.web.form.validator.AddCategoryValidator;
import com.sbsc.fos.category.web.handler.CategoryHandler;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.CategoryTbMaster;
import com.sbsc.fos.product.web.form.ProductBasicForm;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.SBSConstants;

/**
 * This class manages all the Category related operation like Show, Add, Edit
 * and Delete.
 * 
 * @author simanchal.patra
 * 
 */
@Controller
public class CategoriesController implements SBSConstants {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(CategoriesController.class);

	private static final String MODE_ADD = "Add";

	private static final String MODE_EDIT = "Edit";

	/** The Handler */
	@Autowired
	private CategoryHandler categoryHandler;

	/** The Validator */
	@Autowired
	private AddCategoryValidator addCategoryValidator;

	/**
	 * This takes you to the Category Home Page which shows all the Categories
	 * of the Store in Tree format.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/managecategories.htm", method = RequestMethod.GET)
	public ModelAndView showCategories(HttpServletRequest request, Model model) {

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("CategoryVOList",
				categoryHandler.getAllCategories(sessionInfo));

		modelMap.put("manageTableForm", new ManageTableForm(null, null));

		modelMap.put("productBasicForm", new ProductBasicForm());

		return new ModelAndView("/category/show_categories", modelMap);
	}

	/**
	 * Shows the Add Category Form to add a new Category for that Store.
	 * 
	 * @param request
	 *            The HttpServletRequest which have the user session
	 *            information.
	 * @param model
	 *            The holder of the Spring model Attributes.
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/managecategories.htm", method = RequestMethod.POST, params = "addCategory")
	public ModelAndView addCategory(HttpServletRequest request, Model model) {

		long storeId = (long) request.getSession().getAttribute("storeId");

		SessionInfo sessionInfo = new SessionInfo(null, storeId);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		AddCategoryForm addCategoryForm = new AddCategoryForm(null, null, null,
				null, null, null, null, getAllStatus(),
				categoryHandler.getPlaceHolderHierarchyTree(sessionInfo),
				MODE_ADD);

		modelMap.put("addCategoryForm", addCategoryForm);

		modelMap.put("mode", MODE_ADD);

		return new ModelAndView("/category/add_category", modelMap);
	}

	/**
	 * This Updates an existing Category. This Action is bind to the "Save"
	 * button of the page.
	 * 
	 * @param request
	 *            The HttpServletRequest which have the user session
	 *            information.
	 * @param addCategoryForm
	 *            The Form which have the New or Existing Category details.
	 * @param model
	 *            The holder of the Spring model attributes.
	 * @param bindingResult
	 *            The result holder of the Spring form validation
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/managecategories.htm", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCategory(HttpServletRequest request,
			@ModelAttribute("addCategoryForm") AddCategoryForm addCategoryForm,
			Model model, BindingResult bindingResult) {

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		String viewName = null;

		String errorMessage = null;

		String mode = addCategoryForm.getMode();

		ManageTableForm manageTableForm = new ManageTableForm(null, mode);

		addCategoryValidator.validate(addCategoryForm, bindingResult);

		if (bindingResult.hasErrors()) {

			addCategoryForm.setAllStatuses(getAllStatus());
			addCategoryForm.setCategoriesHierarchyTree(categoryHandler
					.getPlaceHolderHierarchyTree(sessionInfo));

			modelMap.put("addCategoryForm", addCategoryForm);

			viewName = "/category/add_category";

		} else {

			try {

				if (addCategoryForm.getCategoryId() != null) {

					categoryHandler
							.updateCategory(addCategoryForm, sessionInfo);
				} else {

					categoryHandler.createNewCategory(addCategoryForm,
							sessionInfo);
				}

			} catch (GenericFailureException exp) {

				logger.error(
						String.format(
								"Error occured while Saving or Updating the"
										+ " Category having Id - %s for Store"
										+ " having Id - %s  ",
								addCategoryForm.getCategoryId(),
								sessionInfo.getStoreId()), exp);

				errorMessage = "Error occured : " + exp.getMessage();

				viewName = "/category/add_category";

			} finally {

			}

			modelMap.put("CategoryVOList",
					categoryHandler.getAllCategories(sessionInfo));

			modelMap.put("manageTableForm", manageTableForm);

			modelMap.put("productBasicForm", new ProductBasicForm());

			viewName = "redirect:./managecategories.htm";

		}

		modelMap.put("errorMessage", errorMessage);

		modelMap.put("mode", mode);

		return new ModelAndView(viewName, modelMap);
	}

	@RequestMapping(value = "admin/managecategories.htm", method = RequestMethod.POST, params = "manageSections")
	public ModelAndView manageSections(HttpServletRequest request,
			@ModelAttribute("addCategoryForm") AddCategoryForm addCategoryForm,
			Model model, BindingResult bindingResult) {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<SectionVO> sectionsList = null;

		String errorMessage = null;

		CategoryTbMaster category = null;

		AddSectionForm addSectionForm = null;

		ManageTableForm manageTableForm = new ManageTableForm(null,
				addCategoryForm.getMode());

		if (addCategoryForm.getCategoryId() != null) {

			category = categoryHandler.getCategory(addCategoryForm
					.getCategoryId());

			addSectionForm = new AddSectionForm(category.getNmCtgryId(),
					category.getVcCtgryNm(), null, null,
					addCategoryForm.getMode());

			try {

				sectionsList = categoryHandler.getAllSection(category
						.getNmCtgryId());
			} catch (GenericFailureException exp) {

				logger.error(String.format(
						"Error occured while fetching the list of Sections for"
								+ " Category having Id - %s ",
						addCategoryForm.getCategoryId()), exp);

				errorMessage = errorMessage + "Error occured : "
						+ exp.getMessage();
			}
		}

		modelMap.put("sectionsList", sectionsList);

		modelMap.put("addSectionForm", addSectionForm);

		modelMap.put("manageTableForm", manageTableForm);

		modelMap.put("errorMessage", errorMessage);

		modelMap.put("mode", addCategoryForm.getMode());

		return new ModelAndView("/category/add_sections", modelMap);
	}

	@RequestMapping(value = "admin/managecategories.htm", method = RequestMethod.POST, params = "saveAndContinue")
	public ModelAndView createCategory(HttpServletRequest request,
			@ModelAttribute("addCategoryForm") AddCategoryForm addCategoryForm,
			Model model, BindingResult bindingResult) {

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<SectionVO> sectionsList = null;

		String viewName = null;

		String errorMessage = null;

		Long categoryId = null;

		String mode = addCategoryForm.getMode();

		ManageTableForm manageTableForm = new ManageTableForm(null, mode);

		addCategoryValidator.validate(addCategoryForm, bindingResult);

		if (bindingResult.hasErrors()) {

			addCategoryForm.setAllStatuses(getAllStatus());
			addCategoryForm.setCategoriesHierarchyTree(categoryHandler
					.getPlaceHolderHierarchyTree(sessionInfo));

			modelMap.put("addCategoryForm", addCategoryForm);

			viewName = "/category/add_category";

		} else {

			try {

				if (addCategoryForm.getCategoryId() != null) {

					categoryHandler
							.updateCategory(addCategoryForm, sessionInfo);

					categoryId = addCategoryForm.getCategoryId();
				} else {

					categoryId = categoryHandler.createNewCategory(
							addCategoryForm, sessionInfo);
				}

			} catch (GenericFailureException exp) {

				logger.error(
						String.format(
								"Error occured while Saving or Updating the"
										+ " Category having Id - %s for Store"
										+ " having Id - %s  ",
								addCategoryForm.getCategoryId(),
								sessionInfo.getStoreId()), exp);

				errorMessage = "Error occured : " + exp.getMessage();

				viewName = "/category/add_category";

			} finally {

			}

			if (!addCategoryForm.getIsPlaceHolder()) {

				AddSectionForm addSectionForm = new AddSectionForm(categoryId,
						addCategoryForm.getCategoryName(), null, null, mode);

				if (addCategoryForm.getCategoryId() != null) {

					try {
						sectionsList = categoryHandler
								.getAllSection(addCategoryForm.getCategoryId());
					} catch (GenericFailureException exp) {

						logger.error(String.format(
								"Error occured while fetching the list of Sections for"
										+ " Category having Id - %s ",
								addCategoryForm.getCategoryId()), exp);

						errorMessage = errorMessage + "Error occured : "
								+ exp.getMessage();
					}
				}

				modelMap.put("sectionsList", sectionsList);

				modelMap.put("addSectionForm", addSectionForm);

				modelMap.put("manageTableForm", manageTableForm);

				viewName = "/category/add_sections";

			} else {

				modelMap.put("CategoryVOList",
						categoryHandler.getAllCategories(sessionInfo));

				modelMap.put("manageTableForm", manageTableForm);
				modelMap.put("productBasicForm", new ProductBasicForm());

				viewName = "redirect:./managecategories.htm";

			}
		}

		modelMap.put("errorMessage", errorMessage);

		modelMap.put("mode", mode);

		return new ModelAndView(viewName, modelMap);
	}

	/**
	 * Shows the Add Category Page.
	 */
	@RequestMapping(value = "admin/managecategories.htm", method = RequestMethod.POST, params = "editCategory")
	public ModelAndView editCategory(
			@ModelAttribute("manageTableForm") ManageTableForm manageTableForm,
			Model model) {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		CategoryTbMaster categoryToEdit = null;

		Long parentCategoryId = null;

		String parentCategoryName = null;

		String errorMessage = null;

		try {
			categoryToEdit = categoryHandler.getCategory(manageTableForm
					.getElementId());
		} catch (GenericFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();

			logger.error(String.format("Error occured while fetching the"
					+ " Category information having Id - %s from Database",
					String.valueOf(manageTableForm.getElementId()), exp));
		}

		if (categoryToEdit.getCategoryTbMaster() != null) {

			parentCategoryId = categoryToEdit.getCategoryTbMaster()
					.getNmCtgryId();

			parentCategoryName = categoryToEdit.getCategoryTbMaster()
					.getVcCtgryNm();
		}

		Boolean isPlaceHolder = categoryToEdit.getIsPlchldr().equals(
				new BigDecimal("1")) ? true : false;

		AddCategoryForm addCategoryForm = new AddCategoryForm(
				categoryToEdit.getNmCtgryId(), categoryToEdit.getVcCtgryNm(),
				parentCategoryId != null ? parentCategoryId.toString() : null,
				parentCategoryName, categoryToEdit.getVcCtgryDsc(),
				String.valueOf(categoryToEdit.getVcStts()), isPlaceHolder,
				getAllStatus(), null, MODE_EDIT);

		modelMap.put("addCategoryForm", addCategoryForm);

		modelMap.put("errorMessage", errorMessage);

		modelMap.put("mode", MODE_EDIT);

		return new ModelAndView("/category/add_category", modelMap);

	}

	/**
	 * This soft deletes a Category if there is no product exists for that
	 * Category.
	 * 
	 * @param request
	 *            The HttpServletRequest which carries the Id of the Category in
	 *            request Parameter
	 * @return The AjaxResponse
	 */
	@RequestMapping(value = "admin/managecategories.htm", method = RequestMethod.POST, params = "deleteCategory")
	public ModelAndView deleteCategory(
			@ModelAttribute("manageTableForm") ManageTableForm manageTableForm,
			Model model, HttpServletRequest request) {

		String errorMessage = null;

		Map<String, Object> modelMap = new HashMap<String, Object>();

		Long categoryId = manageTableForm.getElementId();

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);

		try {

			categoryHandler.deleteCategory(categoryId);

		} catch (GenericFailureException bfe) {

			errorMessage = "Unable to delete Category, reason : "
					+ bfe.getMessage();

			logger.error(String.format(
					"Error occured while deleting Category having id - %s",
					categoryId), bfe);
		}

		if (errorMessage != null) {
			modelMap.put("errorMessage", errorMessage);
		}

		modelMap.put("CategoryVOList",
				categoryHandler.getAllCategories(sessionInfo));

		modelMap.put("manageTableForm", new ManageTableForm(null, null));
		modelMap.put("productBasicForm", new ProductBasicForm());

		return new ModelAndView("/category/show_categories", modelMap);
	}

	/**
	 * Cancel the Add Category task and redirect to the Category Home page.
	 * 
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/managecategories.htm", method = RequestMethod.POST, params = "cancel")
	public ModelAndView cancel(HttpServletRequest request) {

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("CategoryVOList",
				categoryHandler.getAllCategories(sessionInfo));

		modelMap.put("manageTableForm", new ManageTableForm(null, null));

		modelMap.put("productBasicForm", new ProductBasicForm());

		return new ModelAndView("/category/show_categories", modelMap);
	}

	/**
	 * Provides the statuses (<b>Active</b>, <b>InActive</b>) in the form of Map
	 * which a Category can have.
	 * 
	 * @return A HashMap
	 */
	private HashMap<String, String> getAllStatus() {

		HashMap<String, String> allStatus = new HashMap<String, String>();

		allStatus.put(CATEGORY_STATUS.InActive.toString(),
				CATEGORY_STATUS.InActive.toString());

		allStatus.put(CATEGORY_STATUS.Active.toString(),
				CATEGORY_STATUS.Active.toString());

		return allStatus;
	}

	
}
