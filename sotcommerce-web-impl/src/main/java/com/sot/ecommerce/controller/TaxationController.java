/**
 * (c) R Systems International Ltd.
 * @author gaurav_kumar
 */
package com.sot.ecommerce.controller;

import java.math.BigDecimal;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sbsc.fos.category.web.form.ManageTableForm;
import com.sbsc.fos.category.web.handler.CategoryHandler;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.feedback.common.FeedbackVO;
import com.sbsc.fos.feedback.web.form.FeedbackForm;
import com.sbsc.fos.feedback.web.form.validator.FeedbackFormValidator;
import com.sbsc.fos.order.web.handler.OrderHandler;
import com.sbsc.fos.persistance.CategoryTbMaster;
import com.sbsc.fos.persistance.UmTbCntryMstr;
import com.sbsc.fos.product.web.form.ProductBasicForm;
import com.sbsc.fos.promotion.web.form.CategoryAjaxForm;
import com.sbsc.fos.taxation.web.form.TaxationForm;
import com.sbsc.fos.taxation.web.form.validator.TaxationFormValidator;
import com.sbsc.fos.taxation.web.handler.TaxationHandler;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.SBSConstants;

/**
 * This class manages all the taxation related tasks for categories and 
 * countries
 */

@Controller
public class TaxationController implements SBSConstants{

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(TaxationController.class);


	/** The Handlers */
	@Autowired
	private CategoryHandler categoryHandler;
	
	@Autowired
	private TaxationHandler taxationHandler;
	
	@Autowired
	TaxationFormValidator taxationFormValidator;

	/**
	 * This takes you to the Category Home Page which shows all the Categories
	 * for which you can manage tax rates
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/taxMgmt.htm", method = RequestMethod.GET)
	public ModelAndView showCategoriesForTaxation(HttpServletRequest request, Model model) {

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("CategoryVOList",
				categoryHandler.getAllCategories(sessionInfo));

		modelMap.put("manageTableForm", new ManageTableForm(null, null));
		
		modelMap.put("productBasicForm", new ProductBasicForm());
		modelMap.put("taxationForm",new TaxationForm());
		return new ModelAndView("/taxationMgmt/manageTaxation", modelMap);
	}


	
	@RequestMapping(value = "admin/manageCategoryTaxation.htm", method = RequestMethod.GET)
	public ModelAndView ApproveReview(Locale locale, Model model,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException {
		String categoryId = request.getParameter("categoryId");
		CategoryTbMaster ctgry = categoryHandler.getCategory(Long.valueOf(categoryId));
		
		String categoryName = ctgry.getVcCtgryNm();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<UmTbCntryMstr> countryList;
		countryList = taxationHandler.getCountryList();
		modelMap.put("countryList", countryList);
		modelMap.put("taxationForm",new TaxationForm());
		modelMap.put("categoryId", categoryId);
		modelMap.put("categoryName", categoryName);
		modelMap.put("taxForCountries", taxationHandler.getTaxForCountries(Long.valueOf(categoryId)));
		
		return new ModelAndView("/taxationMgmt/manageCategoryTaxation", modelMap);
	}
	
	/**
	 * This is for submitting tax percentage for a country
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "admin/submitTaxation.htm", method = RequestMethod.POST)
	public ModelAndView SubmitTaxation(
			@ModelAttribute("taxationForm") TaxationForm taxationForm,
			Locale locale, BindingResult bindingResult, Model model,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		boolean taxUpdated = false;
		List<UmTbCntryMstr> countryList;
		long categoryId = Long.parseLong(taxationForm.getCategoryId());
		CategoryTbMaster ctgry = categoryHandler.getCategory(Long.valueOf(categoryId));
		String categoryName = ctgry.getVcCtgryNm();
		long countryId = -1;
		Long storeId=(Long) request.getSession().getAttribute("storeId");
		if(!taxationForm.getCountryId().equals("")){
			countryId =Long.parseLong(taxationForm.getCountryId());
		}
		taxationFormValidator.validate(taxationForm, bindingResult);
	
		if (bindingResult.hasErrors()) {
			logger.info("Tax updation has validation errors."+ taxationForm);
		}else{
			
			BigDecimal taxPercentage = new BigDecimal(taxationForm.getTaxPercentage());
			
			taxUpdated = taxationHandler.updateTaxation(categoryId, taxPercentage, countryId, storeId);
		}
		if(!taxationForm.getCountryId().equals("")){
			countryId =Long.parseLong(taxationForm.getCountryId());
		}
		countryList = taxationHandler.getCountryList();	
		modelMap.put("categoryId", categoryId);
		modelMap.put("categoryName", categoryName);
		modelMap.put("countryId", countryId);
		modelMap.put("countryList", countryList);
		modelMap.put("taxationForm", taxationForm);
		modelMap.put("taxUpdated", taxUpdated);
		modelMap.put("taxForCountries", taxationHandler.getTaxForCountries(categoryId));
		return new ModelAndView("/taxationMgmt/manageCategoryTaxation", modelMap);

	}
	
	@RequestMapping(value = "admin/getTaxationForCountry.htm", method = RequestMethod.GET)
	public @ResponseBody String getTaxRate(
			@ModelAttribute("taxationForm") TaxationForm taxationForm,
			Locale locale, BindingResult bindingResult, Model model,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		
		long categoryId = Long.parseLong(request.getParameter("categoryId"));
		long countryId = Long.parseLong(request.getParameter("countryId"));
		String taxPercentage = taxationHandler.getTax(categoryId, countryId);
		return taxPercentage;
		
	}
	
	

}
