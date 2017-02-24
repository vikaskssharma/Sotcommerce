package com.sot.ecommerce.controller;

import java.text.ParseException;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.nls.common.CustomerSubscriptionVO;
import com.sbsc.fos.nls.web.form.NewsSubscribeForm;
import com.sbsc.fos.nls.web.form.UnsubscribeNewsletterForm;
import com.sbsc.fos.nls.web.form.validator.CustomerSubscriptionValidator;
import com.sbsc.fos.nls.web.handler.NewsLetterSubscriptionHandler;
import com.sbsc.fos.utils.FormSearchDataExtract;
import com.sbsc.fos.utils.SBSConstants.SUSCRIBER_STATUS;
import com.sbsc.fos.web.form.FormSearch;
import com.sbsc.fos.wishlist.web.controller.WishListController;

/**
 * Controller for News Letter Subscription
 * 
 * 
 * @author abhishek.vishnoi
 * 
 */
@Controller
public class NewsLetterSubscriptionController {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(WishListController.class);

	@Autowired
	NewsLetterSubscriptionHandler newsLetterSubscriptionHandler;

	@Autowired
	private CustomerSubscriptionValidator subscriptionValidator;

	private SessionInfo sessionInfo;

	private static final String Error = "Please enter valid e-mail Id" + "|1";
	private static final String Exists = "EMAIL ALREADY SUBSCRIBED";
	private static final String Success = "SUCCESSFULLY SUBSCRIBED";

	/**
	 * Controller Mapping for Subscribing Email Adds email to database table.
	 * 
	 * 
	 * 
	 * @param newsSu
	 *            bscribeForm
	 * @param locale
	 * @param model
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "store/news_subscribe.htm", method = RequestMethod.POST)
	 public @ResponseBody String SubscribeUser(
			@ModelAttribute("newsSubscribeForm") NewsSubscribeForm newsSubscribeForm,
			Locale locale, Model model, BindingResult result,
			HttpServletRequest request) {

		String message = null;

		sessionInfo = getSessionInfo(request);
		String email = request.getParameter("subscrb_email");

		if (subscriptionValidator.validateEmailID(email) == false) {
			message = Error;

		} else {
			newsSubscribeForm.setEmail(email);
			newsSubscribeForm.setStoreId(sessionInfo.getStoreId());
			newsSubscribeForm.setCreatedAt(new Date());

			//boolean checkStatus;
			try {
				message = newsLetterSubscriptionHandler
						.AddSubscription(newsSubscribeForm);
				// checks the existence of the email in database
				/*if (checkStatus == true) {
					message = Success;
					logger.info("Successfully.Subscribed -" + message);

				} else {

					message = Exists;
					logger.info("Email.Already.Exists -" + message);

				}*/

				//model.addAttribute("message", message);
				//model.addAttribute("newsSubscribeForm", new NewsSubscribeForm());

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		//redirect:/admin/newsletter_management.htm
		return message;

	}

	/**
	 * 
	 * 
	 * Controller method to Show Subscriber List.
	 * 
	 * @param newsSubscribeForm
	 * @param locale
	 * @param model
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/subscriber_list.htm", method = RequestMethod.GET)
	public ModelAndView SubscriberList(
			@ModelAttribute("formSearch") FormSearch formSearch, Locale locale,
			Model model, BindingResult result, HttpServletRequest request) {

		sessionInfo = getSessionInfo(request);
		List<CustomerSubscriptionVO> subList = newsLetterSubscriptionHandler
				.subscriberList(sessionInfo);

		model.addAttribute("subList", subList);
		model.addAttribute("formSearch", new FormSearch());
		model.addAttribute("allStatuses",getAllStatus());
		return new ModelAndView("/nls/show_subscribers");

	}

	/**
	 * 
	 * 
	 * Controller mapping for filtering on List of newsLetters.
	 * 
	 * 
	 * @param formSearch
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/subscriber_list_filter.htm", method = RequestMethod.POST)
	public ModelAndView SubscriberListFilter(
			@ModelAttribute("formSearch") FormSearch formSearch,
			BindingResult bindingResult, HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<CustomerSubscriptionVO> subList = null;
		sessionInfo = getSessionInfo(request);

		try {

			subList = newsLetterSubscriptionHandler.findSubscribersByFilters(
					FormSearchDataExtract.extractFormData(formSearch),
					sessionInfo);
			modelMap.put("subList", subList);
			modelMap.put("allStatuses",getAllStatus());
			String selectedStatus = formSearch.getFilters().get(1).getValue();
			modelMap.put("selectedStatus",selectedStatus);
			return new ModelAndView("/nls/show_subscribers", modelMap);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return new ModelAndView("/nls/show_subscribers", modelMap);

	}

	/**
	 * 
	 * delete subscriber: : Soft Delete.
	 * 
	 * @param newsLetterForm
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/delete_subscriber.htm", method = RequestMethod.GET)
	public String DeleteNewsLetter(
			@ModelAttribute("formSearch") FormSearch formSearch,
			HttpServletRequest request, Model model) {

		long subId = Long.parseLong(request.getParameter("newsLttrSubID"));
		sessionInfo = getSessionInfo(request);

		List<CustomerSubscriptionVO> subList = newsLetterSubscriptionHandler
				.deleteNewsLetter(subId, sessionInfo);
		model.addAttribute("allStatuses",getAllStatus());
		model.addAttribute("formSearch", formSearch);
		model.addAttribute("subList", subList);

		return "/nls/show_subscribers";
	}

	/**
	 * To un-subscribe the user.
	 * 
	 * 
	 * @param unsubscribeForm
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "store/static_news_unsubscribe.htm", method = RequestMethod.POST)
	public String UnsubscribeUser(
			@ModelAttribute("unsubscribeForm") UnsubscribeNewsletterForm unsubscribeForm,
			HttpServletRequest request, Model model) {

		try {
			newsLetterSubscriptionHandler.Unsubscription(sessionInfo,
					unsubscribeForm);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "/nls/unsubscribe_newsletter";

	}

	/**
	 * Redirect to the un-subscription page with the subscriber details.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "store/static_unsubscribe_red.htm", method = RequestMethod.GET)
	public String UnsubscribeUser(HttpServletRequest request, Model model) {

		long subscriberId = Long.parseLong(request.getParameter("subId"));

		UnsubscribeNewsletterForm unsubscribeForm = newsLetterSubscriptionHandler
				.findSubscriber(sessionInfo, subscriberId);

		model.addAttribute("unsubscribeForm", unsubscribeForm);

		return "/nls/unsubscribe_newsletter";

	}

	/**
	 * Provides the User and Store information from Session.
	 * 
	 * @param request
	 *            The HttpServletRequest
	 * 
	 * @return The SessionInfo object containing UserId and StoreId.
	 */
	private SessionInfo getSessionInfo(HttpServletRequest request) {

		return new SessionInfo((Long) request.getSession().getAttribute(
				"userId"), (Long) request.getSession().getAttribute("storeId"));
	}
	
	private HashMap<String, String> getAllStatus() {

		HashMap<String, String> allStatus = new HashMap<String, String>();

		allStatus.put(SUSCRIBER_STATUS.Subscribed.toString(), SUSCRIBER_STATUS.Subscribed.toString());

		allStatus.put(SUSCRIBER_STATUS.UnSubscribed.toString(), SUSCRIBER_STATUS.UnSubscribed.toString());
		
		return allStatus;
	}
	
}
