package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.nls.common.CustomerSubscriptionVO;
import com.sbsc.fos.nls.web.form.NewsSubscribeForm;
import com.sbsc.fos.nls.web.form.UnsubscribeNewsletterForm;
import com.sbsc.fos.persistance.CustomerSubscriptionTbDtl;
import com.sot.ecommerce.um.service.StoreService;
import com.sot.ecommerce.utils.DateUtil;
import com.sot.ecommerce.utils.SBSConstants;
import com.sot.ecommerce.wishlist.web.controller.WishListController;

/**
 * 
 * Service to Newsletter Subscription
 * 
 * 
 * 1) checks whether email is already subscribed 2) subscribes a new email ID
 * 
 * 
 * @author abhishek.vishnoi
 * 
 */
@Service
public class NewsLetterSubscriptionServiceImpl implements
		NewsLetterSubscriptionService {

	private GenericDAO<CustomerSubscriptionTbDtl> dao;

	private long storeID;

	@Autowired
	private StoreService storeService;

	@Autowired
	private MessageSource messageSource;

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(WishListController.class);

	@Autowired
	public void setDAO(GenericDAO<CustomerSubscriptionTbDtl> daoToSet) {
		logger.info("daoToSet for NewsSubscription" + daoToSet);
		dao = daoToSet;
		dao.setClazz(CustomerSubscriptionTbDtl.class);
	}

	/**
	 * Service method to add email Id subscription to database
	 * 
	 * 
	 */
	@Transactional
	public String AddSubscription(NewsSubscribeForm newsSubscribeForm)
			throws Exception {

		HashMap<String, Object> newsMap = new HashMap<>();
		newsMap.put("vcEml", newsSubscribeForm.getEmail());
		newsMap.put("storeTbMaster",
				storeService.findByID(newsSubscribeForm.getStoreId()));

		List<CustomerSubscriptionTbDtl> newsList = dao
				.findAllByProperty(newsMap);

		logger.info("size of existing email list return true if  >  0   :"
				+ newsList.size());

		if (newsList.size() > 0) {
			if (newsList.get(0).getVcStts().equals("Subscribed")) {
				System.out.println("already subscrbed");
				logger.info("Email already exists --> Subscribed mode.");
				return "You are already subscribed."+ "|1";
				// Email already exists --> Subscribed mode.
			}
			if (newsList.get(0).getVcStts().equals("Unsubscribed")) {

				if (newsList.get(0).getIsDltd() == new BigDecimal("1")) {
					logger.info(" Email already exists but was deleted by admin so not subscribing again "
							+ "--> Unsubscribed mode.");

					// messageSource.getMessage("newsletter.subscription.cannot.subscribe",
					// request.getLocale());

					return "You cannot be subscribed";
					// Email already exists but was deleted by admin so not
					// subscribing again --> Unsubscribed mode.
				}
				System.out.println("already Unsubscrbed once");

				CustomerSubscriptionTbDtl custSubscription = new CustomerSubscriptionTbDtl();

				custSubscription.setStoreTbMaster(storeService
						.findByID(newsSubscribeForm.getStoreId()));
				custSubscription.setVcEml(newsSubscribeForm.getEmail());
				custSubscription.setDtCrtdAt(DateUtil.getCurrentDateTime());
				custSubscription.setIsDltd(new BigDecimal("0"));
				custSubscription
						.setVcStts(SBSConstants.SUSCRIBER_STATUS.Subscribed
								.toString());
				custSubscription.setVcStts("Subscribed");

				dao.saveOrUpdate(custSubscription);

				logger.info("Email already exists & again Subscribing --> Unsubscribed mode.");
				return "You are successfully subscribed"+ "|0";
				// Email already exists & again Subscribing --> Unsubscribed
				// mode.
			}

		} else {
			System.out.println("New e-mail subscription");

			CustomerSubscriptionTbDtl custSubscription = new CustomerSubscriptionTbDtl();

			custSubscription.setStoreTbMaster(storeService
					.findByID(newsSubscribeForm.getStoreId()));
			custSubscription.setVcEml(newsSubscribeForm.getEmail());
			custSubscription.setDtCrtdAt(DateUtil.getCurrentDateTime());
			custSubscription.setIsDltd(new BigDecimal("0"));
			custSubscription.setVcStts(SBSConstants.SUSCRIBER_STATUS.Subscribed
					.toString());
			dao.saveOrUpdate(custSubscription);
			logger.info("Email doesnot exist --> new subscription.");

			return "You are successfully subscribed" + "|0";// Email doesnot exist -->
														// new subscription.
		}

		logger.info("checks for already existence of email. ");

		return "You cannot be subscribed" + "|1";
	}

	/**
	 * 
	 * Service method t ofind List of Subscribers
	 * 
	 * 
	 */
	@Transactional
	public List<CustomerSubscriptionVO> findSubscriberList(
			SessionInfo sessionInfo) {

		HashMap<String, Object> userMap = getUserMap(sessionInfo);

		List<CustomerSubscriptionVO> subList = new ArrayList<CustomerSubscriptionVO>();

		List<CustomerSubscriptionTbDtl> detailList = dao
				.findAllByProperty(userMap);

		for (int itr = 0; itr < detailList.size(); itr++) {
			CustomerSubscriptionVO subscriberListVO = new CustomerSubscriptionVO();

			subscriberListVO.setSubEmail(detailList.get(itr).getVcEml());
			subscriberListVO.setStatus(detailList.get(itr).getVcStts());

			subscriberListVO.setSubId(detailList.get(itr).getNmCstSbscrptnId());

			subList.add(subscriberListVO);
		}

		return subList;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	@Transactional
	public List<CustomerSubscriptionVO> findSusbcriberListByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo)
			throws ParseException {

		List<CustomerSubscriptionVO> subList = new ArrayList<CustomerSubscriptionVO>();

		storeID = sessionInfo.getStoreId();

		filter_criteria.put("filter.NewsletterMngmntTbDtl.storeTbMaster",
				storeService.findByID(storeID));
		filter_criteria.put("filter.NewsletterMngmntTbDtl.isDltd",
				BigDecimal.valueOf(0L));

		List<CustomerSubscriptionTbDtl> detailList = dao
				.findAllByFilters(filter_criteria);

		for (int itr = 0; itr < detailList.size(); itr++) {
			CustomerSubscriptionVO subscriberListVO = new CustomerSubscriptionVO();

			subscriberListVO.setSubEmail(detailList.get(itr).getVcEml());
			subscriberListVO.setStatus(detailList.get(itr).getVcStts());
			subscriberListVO.setSubId(detailList.get(itr).getNmCstSbscrptnId());

			subList.add(subscriberListVO);
		}

		return subList;
	}

	/**
	 * Service Method to Delete a subscriber :: Soft Delete
	 * 
	 */
	@Transactional
	public List<CustomerSubscriptionVO> deleteSubscriber(long subscriptionId,
			SessionInfo sessionInfo) {

		CustomerSubscriptionTbDtl susbcriberDel = dao.findByID(subscriptionId);
		susbcriberDel.setIsDltd(new BigDecimal("1"));

		susbcriberDel.setVcStts(SBSConstants.SUSCRIBER_STATUS.UnSubscribed
				.toString());

		dao.update(susbcriberDel);

		List<CustomerSubscriptionVO> subscriberListVO = findSubscriberList(sessionInfo);
		return subscriberListVO;

	}

	/**
	 * 
	 * Method to unsusbcribe a user. Set the subscription status to
	 * "Unsubscribed"
	 * 
	 * 
	 */
	@Transactional
	public boolean unsubscribe(SessionInfo sessionInfo,
			UnsubscribeNewsletterForm unsubscribeForm) {

		CustomerSubscriptionTbDtl susbcriberDel = dao.findByID(unsubscribeForm
				.getSubId());

		susbcriberDel.setVcStts(SBSConstants.SUSCRIBER_STATUS.UnSubscribed
				.toString());
		susbcriberDel.setVcUnsbscrptnRsn(unsubscribeForm.getUnsubRsn());

		dao.update(susbcriberDel);

		return true;
	}

	/**
	 * 
	 * Method to find a subscriber information for a unique subscriber ID
	 * 
	 */
	@Transactional
	public UnsubscribeNewsletterForm findSubscriber(long subscriberId) {
		UnsubscribeNewsletterForm subscriberVO = new UnsubscribeNewsletterForm();

		CustomerSubscriptionTbDtl subcriber = dao.findByID(subscriberId);

		subscriberVO.setEmail(subcriber.getVcEml());
		subscriberVO.setSubId(subcriber.getNmCstSbscrptnId());
		subscriberVO.setStatus(subcriber.getVcStts());

		return subscriberVO;
	}

	/**
	 * returns a Map containing user info from session object
	 * 
	 * 
	 * @param sessionInfo
	 * @return
	 */
	public HashMap<String, Object> getUserMap(SessionInfo sessionInfo) {
		// usrID = sessionInfo.getUserId();
		storeID = sessionInfo.getStoreId();
		HashMap<String, Object> userMap = new HashMap<>();
		userMap.put("storeTbMaster", storeService.findByID(storeID));
		//userMap.put("desc", "dtCrtdAt");
		userMap.put("isDltd", BigDecimal.valueOf(0L));

		return userMap;
	}

}
