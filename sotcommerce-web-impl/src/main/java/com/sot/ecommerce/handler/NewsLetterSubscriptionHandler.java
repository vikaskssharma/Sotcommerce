package com.sot.ecommerce.handler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.nls.common.CustomerSubscriptionVO;
import com.sbsc.fos.nls.service.NewsLetterSubscriptionService;
import com.sbsc.fos.nls.web.form.NewsSubscribeForm;
import com.sbsc.fos.nls.web.form.UnsubscribeNewsletterForm;

/**
 * Handler for NewsLetterSubscription
 * 
 * 
 * @author abhishek.vishnoi
 * 
 */
@Component
public class NewsLetterSubscriptionHandler {

	@Autowired
	NewsLetterSubscriptionService newsLetterSubscriptionService;

	/**
	 * Handler method for Subscribing email
	 * 
	 * Adds email to database
	 * 
	 * @param newsSubscribeForm
	 * @return
	 * @throws Exception
	 */
	public String AddSubscription(NewsSubscribeForm newsSubscribeForm)
			throws Exception {

		String message = newsLetterSubscriptionService
				.AddSubscription(newsSubscribeForm);

		return message;

	}

	public boolean Unsubscription(SessionInfo sessionInfo,
			UnsubscribeNewsletterForm unsubscribeForm) throws Exception {

		boolean unsubscriptionCheck = newsLetterSubscriptionService
				.unsubscribe(sessionInfo, unsubscribeForm);

		return unsubscriptionCheck;

	}

	public UnsubscribeNewsletterForm findSubscriber(SessionInfo sessionInfo,
			long subscriberId) {

		return newsLetterSubscriptionService.findSubscriber(subscriberId);
	}

	/**
	 * 
	 * 
	 * 
	 * @param sessionInfo
	 * @return
	 */
	public List<CustomerSubscriptionVO> subscriberList(SessionInfo sessionInfo) {

		return newsLetterSubscriptionService.findSubscriberList(sessionInfo);

	}

	/**
	 * 
	 * Handler method to Find news letter by filters
	 * 
	 * @param filter_criteria
	 * @param sessionInfo
	 * @return
	 * @throws BusinessFailureException
	 * @throws ParseException
	 */
	public List<CustomerSubscriptionVO> findSubscribersByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo)
			throws BusinessFailureException, ParseException {

		List<CustomerSubscriptionVO> subList = new ArrayList<CustomerSubscriptionVO>();

		subList = newsLetterSubscriptionService.findSusbcriberListByFilters(
				filter_criteria, sessionInfo);

		return subList;
	}

	/**
	 * Handler Method to delete newsletter
	 * 
	 * @param lttrId
	 * @return
	 */
	public List<CustomerSubscriptionVO> deleteNewsLetter(long subscriptionId,
			SessionInfo sessionInfo) {

		List<CustomerSubscriptionVO> subList = newsLetterSubscriptionService
				.deleteSubscriber(subscriptionId, sessionInfo);

		return subList;
	}

}
