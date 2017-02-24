package com.sot.ecommerce.service.impl;

/**
 * @author gaurav.kumar
 */

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.feedback.common.FeedbackProductVO;
import com.sbsc.fos.feedback.common.FeedbackVO;
import com.sbsc.fos.feedback.web.form.FeedbackForm;
import com.sbsc.fos.order.service.OrderService;
import com.sbsc.fos.persistance.OrderProductTbDetail;
import com.sbsc.fos.persistance.OrderTbMaster;
import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.persistance.ReviewNRatingTbDetail;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sbsc.fos.persistance.UmTbUser;
import com.sbsc.fos.product.service.ProductService;
import com.sbsc.fos.rr.commons.ReviewRatingVO;
import com.sot.ecommerce.um.service.StoreService;
import com.sot.ecommerce.um.service.UserService;
import com.sot.ecommerce.utils.DateUtil;
import com.sot.ecommerce.utils.SBSConstants;

@Service
public class ReviewServiceImpl implements ReviewService, SBSConstants {

	private GenericDAO<ReviewNRatingTbDetail> dao;

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(ReviewServiceImpl.class);

	@Autowired
	public void setDAO(GenericDAO<ReviewNRatingTbDetail> daoToSet) {
		dao = daoToSet;
		dao.setClazz(ReviewNRatingTbDetail.class);
	}

	@Autowired
	private StoreService storeService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Transactional
	public void delete(ReviewNRatingTbDetail review) {

	}

	@Transactional
	public ReviewNRatingTbDetail findByID(final long id) {
		return dao.findByID(id);
	}

	@Transactional
	public ReviewNRatingTbDetail update(ReviewNRatingTbDetail review) {
		return dao.update(review);
	}

	/*
	 * Service Method to Delete a single review on basis of reviewId
	 */
	@Transactional
	public boolean deleteById(long reviewId) throws BusinessFailureException {
		if (dao.findByID(reviewId) != null) {
			dao.deleteById(reviewId);
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	@Override
	public void create(ReviewNRatingTbDetail entity) {

	}

	@Transactional
	public List<ReviewNRatingTbDetail> findAll() {
		List<ReviewNRatingTbDetail> review = dao.findAll();
		return review;
	}
	
	@Transactional
	public List<ReviewNRatingTbDetail> findAllByProperty(String propertyName, Object value){
		return dao.findAllByProperty(propertyName, value);
	}
	
	/*
	 * Method to get a list of review
	 */

	@Transactional
	public List<ReviewRatingVO> findReview(HashMap<String, Object> properties) {
		List<ReviewRatingVO> rrVOList = new ArrayList<ReviewRatingVO>();
		List<ReviewNRatingTbDetail> reviewList = null;

		ReviewRatingVO rrVO = null;

		try {
			properties.put("desc", "dtCrtdAt");
			reviewList = dao.findAllByProperty(properties);

			for (ReviewNRatingTbDetail review : reviewList) {

				rrVO = new ReviewRatingVO(review.getNmRvwId(), review
						.getProductTbMaster().getNmPrdId(), review
						.getProductTbMaster().getVcPrdNm(),
						review.getVcRvwDscptn(), review.getNmRtng(), review
								.getDtCrtdAt().getTime(), review.getVcStts(),
						review.getOrderTbMaster().getVcOrdrNmbr());

				rrVOList.add(rrVO);
			}
		} catch (HibernateException hexp) {
			logger.error(String
					.format("Error occured while fetching filtered reviews"
							+ hexp.getMessage()));
			hexp.printStackTrace();
		}

		return rrVOList;
	}

	/*
	 * Method to get all reviews on the basis of filter criteria
	 */

	@Override
	@Transactional
	public List<ReviewRatingVO> findAllReviewsByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo) {
		List<ReviewRatingVO> rrVOList = new ArrayList<ReviewRatingVO>();
		List<ReviewNRatingTbDetail> reviewList = null;
		try {

			/*
			 * filter_criteria.put("filter.ReviewNRatingTbDetail.storeTbMaster",
			 * storeService.findByID(storeID));
			 */
			filter_criteria.put("filter.ReviewNRatingTbDetail.nmStrId",
					BigDecimal.valueOf(sessionInfo.getStoreId()));
			reviewList = dao.findAllByFilters(filter_criteria);

			ReviewRatingVO rrVO = null;

			for (ReviewNRatingTbDetail review : reviewList) {

				rrVO = new ReviewRatingVO(review.getNmRvwId(), review
						.getProductTbMaster().getNmPrdId(), review
						.getProductTbMaster().getVcPrdNm(),
						review.getVcRvwDscptn(), review.getNmRtng(), review
								.getDtCrtdAt().getTime(), review.getVcStts(),
						review.getOrderTbMaster().getVcOrdrNmbr());

				rrVOList.add(rrVO);
			}

		} catch (HibernateException hexp) {
			logger.error(String
					.format("Error occured while fetching filtered reviews"
							+ hexp.getMessage()));
			hexp.printStackTrace();
		} catch (ParseException parseexp) {
			logger.error(String
					.format("Error occured while parsing filter_criteria"
							+ parseexp.getMessage()));
			parseexp.printStackTrace();
		}
		return rrVOList;

	}

	@Transactional
	public List<FeedbackVO> getProductsForOrder(SessionInfo sessionInfo,
			Long orderId) {

		List<FeedbackVO> feedbackVoList = new ArrayList<FeedbackVO>();
		try {
			StoreTbMaster storeTbMaster = storeService.findByID(sessionInfo
					.getStoreId());
			HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("storeTbMaster", storeTbMaster);
			propertiesMap.put("nmOrdrId", orderId);
			List<OrderTbMaster> orderList = orderService
					.findAllByProperty(propertiesMap);
			FeedbackVO feedbackVO = null;

			FeedbackProductVO feedbackProductVO = null;

			List<FeedbackProductVO> productList = new ArrayList<>();

			List<ReviewNRatingTbDetail> reviews = null;

			// List<RatingTbDetail> ratings = null;

			for (OrderTbMaster orderTbMaster : orderList) {

				feedbackVO = new FeedbackVO();

				feedbackVO.setOrderId(orderId);

				for (OrderProductTbDetail orderProductTbDetail : orderTbMaster
						.getOrderProductTbDetails()) {

					feedbackProductVO = new FeedbackProductVO();

					feedbackProductVO.setProdId(orderProductTbDetail
							.getProductTbMaster().getNmPrdId());

					feedbackProductVO.setProductName(orderProductTbDetail
							.getProductTbMaster().getVcPrdNm());

					reviews = getReviewForOrderAndProduct(
							orderTbMaster.getNmOrdrId(), orderProductTbDetail
									.getProductTbMaster().getNmPrdId());

					if (null != reviews) {

						if (reviews.size() > 0) {

							feedbackProductVO.setProductReview(reviews.get(0)
									.getVcRvwDscptn());

							feedbackProductVO.setProductRating(reviews.get(0)
									.getNmRtng());
						}

					} else {
						feedbackProductVO.setProductReview(null);
					}

					productList.add(feedbackProductVO);
				}

				feedbackVO.setProductList(productList);

				feedbackVoList.add(feedbackVO);
			}

		} catch (HibernateException e) {
			logger.error("Error occured while fetching products of order. Actual Error :"
					+ e.getMessage());
			e.printStackTrace();
		}

		return feedbackVoList;
	}

	// method to update a review

	@Transactional
	public boolean updateReview(SessionInfo sessionInfo,
			FeedbackForm feedbackForm) {

		long id = Long.parseLong(feedbackForm.getProdId());

		ReviewNRatingTbDetail ReviewNRatingTbDetail = new ReviewNRatingTbDetail();
		ReviewNRatingTbDetail.setDtCrtdAt(DateUtil.getCurrentDateTime());
		ReviewNRatingTbDetail.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		ReviewNRatingTbDetail.setVcStts(REVIEW_STATUS.Pending.toString());
		ReviewNRatingTbDetail.setNmCrtdBy(BigDecimal.valueOf(sessionInfo.getUserId()));

		ProductTbMaster productTbMaster = productService.findByID(id);
		ReviewNRatingTbDetail.setProductTbMaster(productTbMaster);
		ReviewNRatingTbDetail.setOrderTbMaster(orderService.findByID(Long
				.parseLong(feedbackForm.getOrderId())));
		ReviewNRatingTbDetail.setNmStrId(BigDecimal.valueOf(sessionInfo.getStoreId()));
		ReviewNRatingTbDetail
				.setNmUpdtdBy(BigDecimal.valueOf(sessionInfo.getUserId()));
		ReviewNRatingTbDetail.setNmUsrId(BigDecimal.valueOf(sessionInfo.getUserId()));
		ReviewNRatingTbDetail.setVcUsrNm(getUserName(Long.valueOf(sessionInfo
				.getUserId())));
		ReviewNRatingTbDetail.setVcRvwDscptn(feedbackForm.getProductReview());
		if(feedbackForm.getProductRating()!=""){
		ReviewNRatingTbDetail.setNmRtng(BigDecimal.valueOf(Double.valueOf(feedbackForm
				.getProductRating())));
		}else{
			ReviewNRatingTbDetail.setNmRtng(BigDecimal.valueOf(0));
		}
		try {
			dao.saveOrUpdate(ReviewNRatingTbDetail);
		} catch (HibernateException e) {
			logger.error(String
					.format("Error occured while updating the review . Actual Error :"
							+ e.getMessage()));
			e.printStackTrace();
		}

		return true;
	}

	// Method to get reviews on the basis of order and product id

	public List<ReviewNRatingTbDetail> getReviewForOrderAndProduct(Long orderId,
			Long productId) {

		HashMap<String, Object> properties = new HashMap<String, Object>();
		OrderTbMaster order = new OrderTbMaster();
		order.setNmOrdrId(orderId);
		ProductTbMaster product = new ProductTbMaster();
		product.setNmPrdId(productId);

		properties.put("orderTbMaster", order);
		properties.put("productTbMaster", product);
		List<ReviewNRatingTbDetail> reviewsList = null;

		try {
			reviewsList = dao.findAllByProperty(properties);
		} catch (HibernateException e) {
			logger.error(String
					.format("Error occured while fetching review for order and product. Actual Error :"
							+ e.getMessage()));
			e.printStackTrace();
		}
		return reviewsList;

	}

	private String getUserName(Long userId) {

		UmTbUser user = userService.findByID(userId);

		return user.getVcFrstNm() + " " + user.getVcLstNm();
	}

}
