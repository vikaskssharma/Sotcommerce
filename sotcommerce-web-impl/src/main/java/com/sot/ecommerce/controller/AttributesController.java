/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.controller;

import java.math.BigDecimal;
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
import com.sbsc.fos.category.web.form.validator.AddAttributeValidator;
import com.sbsc.fos.category.web.handler.AttributeHandler;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.CategoryTbMaster;
import com.sbsc.fos.persistance.CtgrySctnAttrTbDtl;
import com.sbsc.fos.product.web.form.ProductBasicForm;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.NumericAttributeUnits;

/**
 * This manages all the Attribute related actions like Show, Add, Edit and
 * Delete.
 * 
 * @author simanchal.patra
 * 
 */
@Controller
public class AttributesController {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(AttributesController.class);

	/** The Handler */
	@Autowired
	private AttributeHandler attributeHandler;

	/** The Validator */
	@Autowired
	private AddAttributeValidator addAttributeValidator;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Provide the details of the Attribute whose Id received in request
	 * parameter.
	 * 
	 * @param request
	 *            The HttpServletRequest which carries the request parameter
	 *            "attributeId".
	 * 
	 * @return The AjaxResponse
	 */
	@RequestMapping(value = "admin/manageattributes.htm", method = RequestMethod.POST, params = "editAttribute")
	public @ResponseBody
	AjaxResponse getAttributeDetails(HttpServletRequest request) {

		CtgrySctnAttrTbDtl attribute = null;

		AddAttributeForm addAttributeForm = null;

		AjaxResponse ajaxResponse = null;

		String errorMessage = null;

		boolean success = false;

		String attributeId = request.getParameter("attributeId");

		String mode = request.getParameter("mode");

		try {

			attribute = attributeHandler
					.getAttribute(Long.valueOf(attributeId));

			success = true;

		} catch (NumberFormatException | GenericFailureException
				| BusinessFailureException exp) {

			success = false;

			logger.error(
					String.format(
							"Error occured while gettring details of Attribute having Id : %s ",
							attributeId), exp);

			errorMessage = "Error occured : " + exp.getMessage();

		} finally {

		}

		if (attribute != null) {

			boolean isSearchable = attribute.getIsSrchbl().equals(
					new BigDecimal("1")) ? true : false;

			boolean isCompulsory = attribute.getIsMndtry().equals(
					new BigDecimal("1")) ? true : false;

			boolean isVariant = attribute.getIsVrnt().equals(
					new BigDecimal("1")) ? true : false;

			boolean isNumeric = attribute.getIsRngSpprt().equals(
					new BigDecimal("1")) ? true : false;

			boolean isImagable = attribute.getIsImgbl().equals(
					new BigDecimal("1")) ? true : false;

			String unitType = null;

			String customUnitType = null;

			if (isNumeric) {

				if (attribute.getVcUntTyp().contains("Custom")) {
					unitType = attribute.getVcUntTyp().split("\\,")[0];
					customUnitType = attribute.getVcUntTyp().split("\\,")[1];
				} else {
					unitType = attribute.getVcUntTyp();
				}

			}

			addAttributeForm = new AddAttributeForm(attribute
					.getCategoryTbMaster().getNmCtgryId(), attribute
					.getCategoryTbMaster().getVcCtgryNm(), attribute
					.getCtgrySctnTbDtl().getNmSctnId(), attribute
					.getCtgrySctnTbDtl().getVcSctnNm(),
					attribute.getNmAttrId(), attribute.getVcAttrNm(),
					isSearchable, isCompulsory, isVariant, mode, isNumeric,
					isImagable, unitType, getUnitTypes(), customUnitType);
		}

		ajaxResponse = new AjaxResponse(addAttributeForm, success, errorMessage);

		return ajaxResponse;
	}

	/**
	 * This will Create a new Attribute if mode is Add or Update the Attribute
	 * if the case is Edit. This action is performed on the click of
	 * "Save & Add More" button.
	 * 
	 * @param addAttributeForm
	 *            The form which carries the information about the Attribute
	 * @param model
	 *            Holder of The Spring UI model attributes
	 * @param bindingResult
	 *            The Binding result
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/manageattributes.htm", method = RequestMethod.POST, params = "saveAndAddMore")
	public ModelAndView createOrUpdateAttributes(
			@ModelAttribute("addAttributeForm") AddAttributeForm addAttributeForm,
			Model model, BindingResult bindingResult, HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<AttributeVO> attributeList = null;

		String errorMessage = null;

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);

		String mode = addAttributeForm.getMode();

		ManageTableForm manageTableForm = new ManageTableForm(null, mode);

		addAttributeValidator.validate(addAttributeForm, bindingResult);

		if (bindingResult.hasErrors()) {

		} else {

			try {

				attributeHandler.saveOrUpdateAttribute(addAttributeForm,
						sessionInfo);
				addAttributeForm.setAttributeId(null);
				addAttributeForm.setAttributeName(null);
				addAttributeForm.setIsSearchable(false);
				addAttributeForm.setIsCompulsory(false);
				addAttributeForm.setIsVariant(false);
				addAttributeForm.setIsImagable(false);
				addAttributeForm.setIsNumeric(false);
				addAttributeForm.setCustomUnitType(null);
				addAttributeForm.setUnitType("NONE");

			} catch (GenericFailureException exp) {

				errorMessage = "Error occured : " + exp.getMessage();

				logger.error(
						String.format(
								"Error occured while Saving a Attribute for Section with Id - %s ",
								addAttributeForm.getSectionId()), exp);
			}
		}

		try {

			attributeList = attributeHandler.getAllAttributes(addAttributeForm
					.getSectionId());

			addAttributeForm.setUnitTypeMap(getUnitTypes());

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();

			logger.error(
					String.format(
							"Error occured while getting the Attribute list for Section with Id - %s ",
							addAttributeForm.getSectionId()), exp);
		}

		modelMap.put("attributeList", attributeList);

		modelMap.put("addAttributeForm", addAttributeForm);

		modelMap.put("manageTableForm", manageTableForm);

		modelMap.put("errorMessage", errorMessage);

		modelMap.put("mode", mode);

		model.addAttribute("errors", bindingResult.getAllErrors());

		return new ModelAndView("/category/add_attributes", modelMap);
	}

	/**
	 * Take back to the Add / Edit Section of the page of the Category to which
	 * this Attribute belongs.
	 * 
	 * @param addAttributeForm
	 *            The Form which Carries the Attribute details.
	 * @param model
	 *            The Holder of the Spring model attributes
	 * 
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/manageattributes.htm", method = RequestMethod.POST, params = "backToSection")
	public ModelAndView backToSection(
			@ModelAttribute("addAttributeForm") AddAttributeForm addAttributeForm,
			Model model) {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<SectionVO> sectionsList = new ArrayList<>();

		String errorMessage = null;

		AddSectionForm addSectionForm = null;

		CategoryTbMaster parentCategory = null;

		String mode = addAttributeForm.getMode();

		try {

			parentCategory = attributeHandler.getCategory(addAttributeForm
					.getCategoryId());

			sectionsList = attributeHandler.getAllSection(parentCategory
					.getNmCtgryId());

		} catch (BusinessFailureException | GenericFailureException exp) {

			logger.error(
					String.format(
							"Error occured while going back from %s Attribute to Section.",
							mode), exp);

			errorMessage = "Error occured : " + exp.getMessage();

		} finally {

		}

		addSectionForm = new AddSectionForm(parentCategory.getNmCtgryId(),
				parentCategory.getVcCtgryNm(), null, null, mode);

		modelMap.put("sectionsList", sectionsList);

		modelMap.put("manageTableForm", new ManageTableForm(null, mode));

		modelMap.put("addSectionForm", addSectionForm);

		modelMap.put("mode", mode);

		modelMap.put("errorMessage", errorMessage);

		return new ModelAndView("/category/add_sections", modelMap);
	}

	/**
	 * Soft Delete the Attribute whose Id is present in request parameter. This
	 * deletes only if there is no Product exists for the Category to which this
	 * Attribute belongs.
	 * 
	 * @param request
	 *            The HttpServletRequest which carries the parameter
	 *            "attributeId".
	 * @param model
	 *            The holder of the Spring model attributes.
	 * @return The AjaxResponse
	 */
	@RequestMapping(value = "admin/manageattributes.htm", method = RequestMethod.POST, params = "deleteAttribute")
	public @ResponseBody
	AjaxResponse deleteAttributes(HttpServletRequest request, Model model) {

		Long attributeId = Long.valueOf(request.getParameter("attributeId"));

		AjaxResponse response = null;

		boolean success = false;

		String errorMessage = null;

		try {

			attributeHandler.deleteAttribute(attributeId);

			success = true;
		} catch (BusinessFailureException bfe) {

			success = false;

			errorMessage = messageSource.getMessage(bfe.getMessage(), null,
					null);
		}

		response = new AjaxResponse(attributeId, success, errorMessage);

		return response;
	}

	/**
	 * Cancels the Add / Edit Attribute task, and takes the user to Category
	 * Home page.
	 * 
	 * @return The ModelAndView
	 */
	@RequestMapping(value = "admin/manageattributes.htm", method = RequestMethod.POST, params = "cancel")
	public ModelAndView cancel(HttpServletRequest request) {

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("CategoryVOList",
				attributeHandler.getAllCategories(sessionInfo));

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

	@RequestMapping(value = "admin/manageattributes.htm", method = RequestMethod.POST, params = "unitValues")
	public @ResponseBody
	AjaxResponse getUnitValues(HttpServletRequest request) {
		return null;
	}

}
