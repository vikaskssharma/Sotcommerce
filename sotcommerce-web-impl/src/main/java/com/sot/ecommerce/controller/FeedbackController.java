package com.sot.ecommerce.controller;

/**
 * Handles requests for orders history for customers
 * 
 * @author gaurav.kumar
 */

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
import com.sbsc.fos.feedback.common.FeedbackVO;
import com.sbsc.fos.feedback.web.form.FeedbackForm;
import com.sbsc.fos.feedback.web.form.validator.FeedbackFormValidator;
import com.sbsc.fos.feedback.web.handler.FeedbackHandler;
import com.sbsc.fos.rr.commons.ReviewRatingVO;
import com.sbsc.fos.rr.web.handler.RRHandler;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.FormSearchDataExtract;
import com.sbsc.fos.web.form.FormSearch;
import com.sbsc.fos.web.form.validator.FormSearchValidator;

@Controller
public class FeedbackController {

	@Autowired
	private FeedbackHandler feedbackHandler;

	@Autowired
	FeedbackFormValidator feedbackFormValidator;

	@Autowired
	private RRHandler rrHandler;

	@Autowired
	FormSearchValidator formSearchValidator;

	@Autowired
	private MessageSource messageSource;

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(FeedbackController.class);

	/*
	 * This works as a controller to show all the orders for a customer
	 */
	@RequestMapping(value = "store/submit_feedback.htm", method = RequestMethod.GET)
	public ModelAndView SubmitFeedback(Locale locale, Model model,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		Long orderId = Long.parseLong(request.getParameter("orderId"));
		List<FeedbackVO> feedbackList = feedbackHandler.getProductsForOrder(
				sessionInfo, orderId);
		model.addAttribute("feedbackForm", new FeedbackForm());
		modelMap.put("feedbackList", feedbackList);

		return new ModelAndView("/feedback/submit_feedback", modelMap);

	}

	/*
	 * @RequestMapping(value = "/updateAdminProfile.htm", method =
	 * RequestMethod.POST) public ModelAndView updateAdminProfile(
	 * 
	 * @ModelAttribute("adminProfileForm") ProfileForm adminProfileForm, Locale
	 * locale, BindingResult bindingResult, Model model, HttpServletRequest
	 * request, final RedirectAttributes redirectAttributes) {
	 */

	/*
	 * This works as a controller to submit reviews and ratings for the product
	 */
	@RequestMapping(value = "store/submit_review_rating.htm", method = RequestMethod.POST)
	public ModelAndView SubmitReviewRating(
			@ModelAttribute("feedbackForm") FeedbackForm feedbackForm,
			Locale locale, BindingResult bindingResult, Model model,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		
		feedbackFormValidator.validate(feedbackForm, bindingResult);
		List<FeedbackVO> feedbackList = null;
		if (bindingResult.hasErrors()) {

			logger.info("Review and rating updation has validation errors."
					+ feedbackForm);

			feedbackList = feedbackHandler.getProductsForOrder(sessionInfo,
					Long.parseLong((feedbackForm.getOrderId())));

			modelMap.put("feedbackForm", feedbackForm);
			modelMap.put("feedbackList", feedbackList);

		} else {
			int updatedProductId = Integer.parseInt(feedbackForm.getProdId());
			int updatedRating =0;
			if(feedbackForm.getProductRating()!=""){
				updatedRating = Integer.parseInt(feedbackForm.getProductRating());
			}
			model.addAttribute("updatedProductId", updatedProductId);
			model.addAttribute("updatedRating", updatedRating);
			feedbackHandler.updateReviewAndRating(sessionInfo, feedbackForm);
			
			
				/*
			 * List<FeedbackVO> productList =
			 * feedbackHandler.getProductsForOrder( sessionInfo,
			 * Long.parseLong(feedbackForm.getOrderId()));
			 * model.addAttribute("feedbackForm", feedbackForm);
			 * modelMap.put("productList", productList); } return new
			 * ModelAndView("submit_feedback", modelMap);
			 */
			feedbackList = feedbackHandler.getProductsForOrder(sessionInfo,
					Long.parseLong((feedbackForm.getOrderId())));
			model.addAttribute("feedbackForm", feedbackForm);
			modelMap.put("feedbackList", feedbackList);
			modelMap.put("successMsg",
					messageSource.getMessage("add.feedback.submit", null, null));
		}
		return new ModelAndView("/feedback/submit_feedback", modelMap);

	}

	/**
	 * This works as a controller to show all the reviews to the merchant admin
	 */
	@RequestMapping(value = "admin/review_listing.htm", method = RequestMethod.GET)
	public ModelAndView AllReviews(Locale locale, Model model,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		try {

			List<ReviewRatingVO> reviewList = rrHandler
					.getAllReviewRatings(CommonUtil.getSessionInfo(request));

			modelMap.put("formSearch", new FormSearch());
			modelMap.put("reviewList", reviewList);
			
		} catch (Exception e) {
			logger.error("Error occured while fetching review list " + e);
		}

		return new ModelAndView("/rr/review_listing", modelMap);
	}

	/**
	 * This works as a controller to show all the filtered reviews to the admin
	 * 
	 */
	@RequestMapping(value = "admin/filter_reviews.htm", method = RequestMethod.POST)
	public ModelAndView ReviewFilter(
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
				modelMap.put("reviewList",
						rrHandler.getAllReviewRatings(sessionInfo));
			} else {
				List<ReviewRatingVO> reviewList = rrHandler
						.findAllReviewsByFilters(FormSearchDataExtract
								.extractFormData(formSearch), sessionInfo);
				modelMap.put("formSearch", formSearch);
				modelMap.put("reviewList", reviewList);

			}
		} catch (Exception e) {
			logger.error("Error occured while fetching filtered details of reviews "
					+ e);
		}
		return new ModelAndView("/rr/review_listing", modelMap);
	}

	/*
	 * This works as a controller to delete review
	 */
	@RequestMapping(value = "admin/delete_review.htm", method = RequestMethod.GET)
	public ModelAndView DeleteReview(Locale locale, Model model,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		Long reviewid = null;
		try {

			reviewid = Long.parseLong(request.getParameter("reviewid"));

			logger.info("Deleting a review with ID -" + reviewid);

			List<ReviewRatingVO> reviewList = rrHandler.deleteReview(reviewid,
					sessionInfo);

			modelMap.put("formSearch", new FormSearch());
			modelMap.put("reviewList", reviewList);

		} catch (Exception e) {
			logger.error("Error occured while deleting review " + e);
		}

		return new ModelAndView("/rr/review_listing", modelMap);
	}

	/*
	 * This works as a controller to approve a review
	 */
	@RequestMapping(value = "admin/approve_review.htm", method = RequestMethod.GET)
	public ModelAndView ApproveReview(Locale locale, Model model,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		Long reviewid = null;
		List<ReviewRatingVO> reviewList = null;
		try {

			reviewid = Long.parseLong(request.getParameter("reviewid"));
			reviewList = rrHandler.updateReview(reviewid, sessionInfo);
			
			if(reviewList!=null){
				modelMap.put("updationSuccess", true);
			}else{
				modelMap.put("updationSuccess", false);
			}
			modelMap.put("formSearch", new FormSearch());
			modelMap.put("reviewList", reviewList);

		} catch (Exception e) {
			logger.error("Error occured while approving review " + e);
		}

		return new ModelAndView("/rr/review_listing", modelMap);
	}

	

}
