package com.sot.ecommerce.controller;

import java.io.IOException;
import java.text.ParseException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.nls.common.NewsLetterManagementVO;
import com.sbsc.fos.nls.web.form.AddNewsLetterForm;
import com.sbsc.fos.nls.web.form.validator.NewsLetterFormValidator;
import com.sbsc.fos.nls.web.handler.NewsLetterManagementHandler;
import com.sbsc.fos.utils.FormSearchDataExtract;
import com.sbsc.fos.utils.SBSConstants.NEWSLETTER_STATUS;
import com.sbsc.fos.web.form.FormSearch;
import com.sbsc.fos.web.form.validator.FormSearchValidator;

/**
 * 
 * Controller for NewsLetter Management
 * 
 * 
 * @author abhishek.vishnoi
 * 
 */
@Controller
public class NewsLetterManagementController {

	@Autowired
	NewsLetterManagementHandler newsLetterManagementHandler;

	@Autowired
	FormSearchValidator formSearchValidator;

	@Autowired
	NewsLetterFormValidator newsLetterFormValidator;

	/** Logger instance. */
	private static final Logger logger = Logger
			.getLogger(NewsLetterManagementController.class);

	private SessionInfo sessionInfo;

	public final int MODE_EDIT = 1;

	public final int MODE_ADD = 0;

	/**
	 * 
	 * Controller mapping to fetch List of NewsLetters
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/newsletter_management.htm", method = RequestMethod.GET)
	public ModelAndView NewsLetterList(
			@ModelAttribute("formSearch") FormSearch formSearch, Model model,
			HttpServletRequest request) {

		sessionInfo = getSessionInfo(request);

		List<NewsLetterManagementVO> subList = newsLetterManagementHandler
				.findSubscriptionList(sessionInfo);
		model.addAttribute("allStatuses",getAllStatus());
		model.addAttribute("subList", subList);
		return new ModelAndView("/nls/newsletter_management");
	}

	/**
	 * 
	 * 
	 * Controller mapping for filtering on List of newsLetters
	 * 
	 * 
	 * @param formSearch
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/newsletter_management_filter.htm", method = RequestMethod.POST)
	public ModelAndView NewsLetterFilter(
			@ModelAttribute("formSearch") FormSearch formSearch,
			BindingResult bindingResult, HttpServletRequest request) {
		sessionInfo = getSessionInfo(request);
		//Map<String, Object> modelMap = new HashMap<String, Object>();

		ModelAndView modelAndView = null;
		
		try {
		
			formSearchValidator.validate(formSearch, bindingResult);
			modelAndView = new ModelAndView("/nls/newsletter_management");
			
		if (bindingResult.hasErrors()) {

			modelAndView.addObject("formSearch", formSearch);
			
			modelAndView.addObject("subList",  newsLetterManagementHandler
						.findAllNewsLetterFilters(FormSearchDataExtract
								.extractFormData(formSearch), sessionInfo));
			
			
			String selectedStatus = formSearch.getFilters().get(3).getValue();
			modelAndView.addObject("selectedStatus",selectedStatus);
			modelAndView.addObject("allStatuses",getAllStatus());
			return modelAndView;
		} else {
			List<NewsLetterManagementVO> subList;
			
				subList = newsLetterManagementHandler
						.findAllNewsLetterFilters(FormSearchDataExtract
								.extractFormData(formSearch), sessionInfo);
			

				modelAndView.addObject("subList", subList);
			String selectedStatus = formSearch.getFilters().get(3).getValue();
			modelAndView.addObject("selectedStatus",selectedStatus);
			modelAndView.addObject("allStatuses",getAllStatus());
			return modelAndView;
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String selectedStatus = formSearch.getFilters().get(3).getValue();
			modelAndView.addObject("selectedStatus",selectedStatus);
			modelAndView.addObject("allStatuses",getAllStatus());
			
		}
		
		return modelAndView;
	}
	
	/**
	 * Controller Mapping to add a news NewsLetter
	 * 
	 * 
	 * @param newsLetterForm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/add_newsletter.htm", method = RequestMethod.POST)
	public ModelAndView AddNewsLetter(
			@ModelAttribute("newsLetterForm") AddNewsLetterForm newsLetterForm,
			Model model, HttpServletRequest request,
			BindingResult bindingResult,
			final RedirectAttributes redirectAttributes) {

		newsLetterFormValidator.validate(newsLetterForm, bindingResult);
		ModelAndView modelAndView = null;
		List<NewsLetterManagementVO> subList = null;
		Integer mode = newsLetterForm.getMode();
		sessionInfo = getSessionInfo(request);
		if (mode == 0) {

			logger.info("Add Mode" + mode);

		} else {

			logger.info("Edit Mode" + mode);
			newsLetterForm.setNewsLttrId(newsLetterForm.getNewsLttrId());
		}

		if (bindingResult.hasErrors()) {
			modelAndView = new ModelAndView("/nls/add_newsletter");
			modelAndView.addObject("MODE", mode);
			modelAndView.addObject("newsLetterForm", newsLetterForm);
			return modelAndView;

		} else {

			try {

				logger.info("uploadling file:"
						+ newsLetterForm.getFileData().getOriginalFilename());

				String pdfPath = newsLetterManagementHandler
						.uploadPdfNewsLetter(newsLetterForm, request,
								sessionInfo);

				newsLetterForm.setNwsLttrPath(pdfPath);

				newsLetterManagementHandler.addNewsLetter(sessionInfo,
						newsLetterForm);

				subList = newsLetterManagementHandler
						.findSubscriptionList(sessionInfo);

			} catch (BusinessFailureException | ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

			model.addAttribute("subList", subList);
			model.addAttribute("formSearch", new FormSearch());
			model.addAttribute("newsLetterForm", new AddNewsLetterForm());

			return new ModelAndView("redirect:/admin/newsletter_management.htm");
		}
	}

	/**
	 * Re-direct to Add NewsLetter page
	 * 
	 * 
	 * @param newsLetterForm
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/add_newsletter_page.htm", method = RequestMethod.GET)
	public String AddNewsLetterPage(
			@ModelAttribute("newsLetterForm") AddNewsLetterForm newsLetterForm,
			HttpServletRequest request, Model model) {

		model.addAttribute("newsLetterForm", new AddNewsLetterForm());
		model.addAttribute("MODE", MODE_ADD);

		return "/nls/add_newsletter";
	}

	/**
	 * 
	 * Re-Direct to Edit newsletter with existing data from the newsLetterID
	 * 
	 * @param newsLetterForm
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/edit_newsletter_page.htm", method = RequestMethod.GET)
	public String EditNewsLetterPage(
			@ModelAttribute("newsLetterForm") AddNewsLetterForm newsLetterForm,
			HttpServletRequest request, Model model) {

		long lttrId = Long.parseLong(request.getParameter("newsLttrID"));

		AddNewsLetterForm newsLttr = newsLetterManagementHandler
				.findById(lttrId);

		model.addAttribute("newsLetterForm", newsLttr);
		model.addAttribute("MODE", MODE_EDIT);

		return "/nls/add_newsletter";
	}

	/**
	 * 
	 * Preview NewsLetter :: in a pop Up form
	 * 
	 * @param newsLetterForm
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/preview_newsletter.htm", method = RequestMethod.GET)
	public String PreviewNewsLetter(HttpServletRequest request, Model model) {

		long lttrId = Long.parseLong(request.getParameter("newsLttrID"));

		NewsLetterManagementVO newsLttr = newsLetterManagementHandler
				.findVOById(lttrId);

		model.addAttribute("newsLttr", newsLttr);

		return "/nls/preview_newsletter";
	}

	/**
	 * 
	 * delete NewsLetter : : Soft Delete
	 * 
	 * @param newsLetterForm
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/delete_newsletter.htm", method = RequestMethod.POST)
	public String DeleteNewsLetter(
			@ModelAttribute("formSearch") FormSearch formSearch,
			HttpServletRequest request, Model model) {

		long lttrId = Long.parseLong(request.getParameter("newsLttrID"));
		sessionInfo = getSessionInfo(request);

		List<NewsLetterManagementVO> subList = newsLetterManagementHandler
				.deleteNewsLetter(lttrId, sessionInfo);
		/*
		String selectedStatus = formSearch.getFilters().get(3).getValue();
		model.addAttribute("selectedStatus",selectedStatus);
		model.addAttribute("allStatuses",getAllStatus());*/
		
		model.addAttribute("formSearch", formSearch);
		model.addAttribute("subList", subList);

		return "/nls/newsletter_management";
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
	
	
	private HashMap<String, String> getAllStatus() {

		HashMap<String, String> allStatus = new HashMap<String, String>();

		allStatus.put(NEWSLETTER_STATUS.Published.toString(), NEWSLETTER_STATUS.Published.toString());

		allStatus.put(NEWSLETTER_STATUS.Pending.toString(), NEWSLETTER_STATUS.Pending.toString());
	
		return allStatus;
	}

}
