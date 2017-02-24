/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sbsc.fos.category.commons.AttributeVO;
import com.sbsc.fos.category.commons.SectionVO;
import com.sbsc.fos.category.web.form.AddAttributeForm;
import com.sbsc.fos.category.web.form.AddSectionForm;
import com.sbsc.fos.category.web.form.AjaxResponse;
import com.sbsc.fos.category.web.form.ManageTableForm;
import com.sbsc.fos.category.web.form.validator.AddSectionValidator;
import com.sbsc.fos.category.web.handler.SectionHandler;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.CtgrySctnTbDtl;
import com.sbsc.fos.product.web.form.ProductBasicForm;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.NumericAttributeUnits;

/**
 * This class manages all the Section related operation like Show, Add, Edit and
 * Delete.
 * 
 * @author simanchal.patra
 * 
 */
@Controller
public class SectionsController {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(SectionsController.class);

	/** The Handler */
	@Autowired
	private SectionHandler sectionHandler;

	/** The Validator */
	@Autowired
	private AddSectionValidator addSectionValidator;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Provide the details of the Section to be edited by User whose Id received
	 * in request parameter.
	 * 
	 * @param request
	 *            The HttpServletRequest which carries the request parameter
	 *            "sectionId".
	 * @return The AjaxResponse
	 */
	@RequestMapping(value = "admin/managesections.htm", method = RequestMethod.POST, params = "editSection")
	public @ResponseBody
	AjaxResponse getSectionDetails(HttpServletRequest request) {

		CtgrySctnTbDtl section = null;

		String sectionId = request.getParameter("sectionId");

		String errorMessage = null;

		boolean success = false;

		try {

			section = sectionHandler.getSection(Long.valueOf(sectionId));

			success = true;

		} catch (NumberFormatException | GenericFailureException exp) {

			errorMessage = "Error occured, reason : " + exp.getMessage();

			logger.error(String.format(
					"Error occured while fetching Section information"
							+ " having Id - %s from database", sectionId), exp);
		}

		AddSectionForm addSectionForm = new AddSectionForm(section
				.getCategoryTbMaster().getNmCtgryId(), section
				.getCategoryTbMaster().getVcCtgryNm(), section.getNmSctnId(),
				section.getVcSctnNm(), null);

		return new AjaxResponse(addSectionForm, success, errorMessage);
	}

	/**
	 * This will Create a new Section if mode is Add or Update an existing
	 * Section if the mode is Edit. This action is performed is bound to the
	 * "Save & Add More" button.
	 * 
	 * @param addSectionForm
	 *            The Form which carries the new or existing Section information
	 * @param model
	 *            The holder of Spring which carries the model Attributes.
	 * @param bindingResult
	 *            The result of the Spring form validation.
	 * @param request
	 *            The HttpServletRequest which carries the User Session
	 *            information
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/managesections.htm", method = RequestMethod.POST, params = "saveAndAddMore")
	public ModelAndView addSection(
			@ModelAttribute("addSectionForm") AddSectionForm addSectionForm,
			Model model, BindingResult bindingResult, HttpServletRequest request) {

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<SectionVO> sectionsList = new ArrayList<>();

		String errorMessage = null;

		String mode = addSectionForm.getMode();

		ManageTableForm manageTableForm = new ManageTableForm(null, mode);

		addSectionValidator.validate(addSectionForm, bindingResult);

		if (bindingResult.hasErrors()) {
			// return the addSectionForm as it is i.e. no change
		} else {

			try {

				if (addSectionForm.getSectionId() != null) {
					sectionHandler.updateSection(addSectionForm, sessionInfo);
				} else {
					sectionHandler
							.createNewSection(addSectionForm, sessionInfo);
				}

			} catch (GenericFailureException exp) {

				errorMessage = "Error occured while Saving or Updating the"
						+ " Section, reason : " + exp.getMessage();

				logger.error(String.format(
						"Error occured while Saving or Updating the"
								+ " Section for Category having Id - %s ",
						addSectionForm.getCategoryId()), exp);
			}

			addSectionForm.setSectionId(null);
			addSectionForm.setSectionName(null);

		}

		try {
			sectionsList = sectionHandler.getAllSection(addSectionForm
					.getCategoryId());
		} catch (GenericFailureException exp) {

			logger.error(String.format("Error occured while fetching the"
					+ " Sections list for Category having Id - %s ",
					addSectionForm.getCategoryId()), exp);
		}

		modelMap.put("mode", mode);

		modelMap.put("errorMessage", errorMessage);

		modelMap.put("sectionsList", sectionsList);

		modelMap.put("addSectionForm", addSectionForm);

		modelMap.put("manageTableForm", manageTableForm);

		return new ModelAndView("/category/add_sections", modelMap);
	}

	/**
	 * This takes the User to Add Attribute form to add a new Attribute for the
	 * specified Section.
	 * 
	 * @param manageTableForm
	 *            The Form which carries the Id of the Section for which new
	 *            Attribute will be added.
	 * @param model
	 *            The holder of Spring which holds the model attributes.
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/managesections.htm", method = RequestMethod.POST, params = "addAttributes")
	public ModelAndView addAttribute(
			@ModelAttribute("manageTableForm") ManageTableForm manageTableForm,
			Model model) {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		CtgrySctnTbDtl section = null;

		List<AttributeVO> attributeList = null;

		String errorMessage = null;

		String mode = manageTableForm.getMode();

		try {

			section = sectionHandler.getSection(manageTableForm.getElementId());

		} catch (GenericFailureException exp) {

			errorMessage = "Error occured while fetching details of Section, reason : "
					+ exp.getMessage();

			logger.error(
					String.format(
							"Error occured while fetching the details of Section having Id - %s",
							manageTableForm.getElementId()), exp);
		} finally {

		}

		AddAttributeForm addAttributeForm = new AddAttributeForm(section
				.getCategoryTbMaster().getNmCtgryId(), section
				.getCategoryTbMaster().getVcCtgryNm(), section.getNmSctnId(),
				section.getVcSctnNm(), null, null, false, false, false, mode,
				false, false, null, getUnitTypes(), null);

		try {
			attributeList = sectionHandler.getAllAttributes(section
					.getNmSctnId());
		} catch (GenericFailureException exp) {

			errorMessage = errorMessage + "Error occured, reason : "
					+ exp.getMessage();

			logger.error(
					String.format(
							"Error occured while fetching the Attributes list of Section having Id - %s",
							manageTableForm.getElementId()), exp);
		}

		modelMap.put("attributeList", attributeList);

		modelMap.put("manageTableForm", manageTableForm);

		modelMap.put("addAttributeForm", addAttributeForm);

		modelMap.put("mode", mode);

		return new ModelAndView("/category/add_attributes", modelMap);
	}

	/**
	 * Soft Delete the Section whose Id is present in request parameter. This
	 * deletes only if there is no Product exists for the Category to which this
	 * Section belongs.
	 * 
	 * @param request
	 *            The HttpServletRequest which carries the parameter
	 *            "sectionId".
	 * @return The AjaxResponse
	 */
	@RequestMapping(value = "admin/managesections.htm", method = RequestMethod.POST, params = "deleteSection")
	public @ResponseBody
	AjaxResponse deleteSection(HttpServletRequest request) {

		Long sectionToDelete = null;

		boolean success = false;

		String errorMessage = null;

		try {

			sectionToDelete = Long.valueOf(request.getParameter("sectionId"));

			sectionHandler.deleteSection(sectionToDelete);

			success = true;

		} catch (BusinessFailureException bfe) {

			success = false;

			errorMessage = messageSource.getMessage(bfe.getMessage(), null,
					null);

		} catch (GenericFailureException gfe) {
			throw gfe;
		}

		return new AjaxResponse(sectionToDelete, success, errorMessage);
	}

	/**
	 * Cancels the Add / Edit Section task, and takes the user to Category Home
	 * page.
	 * 
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/managesections.htm", method = RequestMethod.POST, params = "cancel")
	public ModelAndView cancel(HttpServletRequest request) {

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("CategoryVOList",
				sectionHandler.getAllCategories(sessionInfo));

		modelMap.put("manageTableForm", new ManageTableForm(null, null));

		modelMap.put("productBasicForm", new ProductBasicForm());

		return new ModelAndView("/category/show_categories", modelMap);
	}

	

	private LinkedHashMap<String, String> getUnitTypes() {

		LinkedHashMap<String, String> unitTypes = new LinkedHashMap<>();

		unitTypes.put(NumericAttributeUnits.WEIGHT.class.getSimpleName(),
				NumericAttributeUnits.WEIGHT.class.getSimpleName());
		unitTypes.put(NumericAttributeUnits.HEIGHT.class.getSimpleName(),
				NumericAttributeUnits.HEIGHT.class.getSimpleName());
		unitTypes.put(NumericAttributeUnits.WIDTH.class.getSimpleName(),
				NumericAttributeUnits.WIDTH.class.getSimpleName());
		unitTypes.put(NumericAttributeUnits.LENGTH.class.getSimpleName(),
				NumericAttributeUnits.LENGTH.class.getSimpleName());
		unitTypes.put(NumericAttributeUnits.LIQUID.class.getSimpleName(),
				NumericAttributeUnits.LIQUID.class.getSimpleName());
		unitTypes.put(NumericAttributeUnits.SIZE.class.getSimpleName(),
				NumericAttributeUnits.SIZE.class.getSimpleName());

		return unitTypes;
	}

}
