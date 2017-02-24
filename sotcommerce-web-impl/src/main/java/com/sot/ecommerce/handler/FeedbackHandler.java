package com.sot.ecommerce.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.feedback.common.FeedbackVO;
import com.sbsc.fos.feedback.web.form.FeedbackForm;
import com.sbsc.fos.rr.service.ReviewService;

/**
 * The Handler is class which is a bridge between the Web tier and Service tier.
 * This class handles all the communication between ProductOrdersController to
 * it's Service layer
 * 
 * @author gaurav.kumar
 */

@Component
public class FeedbackHandler {

	@Autowired
	private ReviewService reviewService;

	public ReviewService getFeedbackService() {
		return reviewService;
	}

	public void setFeedbackService(ReviewService feedbackService) {
		this.reviewService = feedbackService;
	}

	public List<FeedbackVO> getProductsForOrder(SessionInfo sessionInfo,
			Long orderId) {
		return reviewService.getProductsForOrder(sessionInfo, orderId);
	}

	public boolean updateReviewAndRating(SessionInfo sessionInfo,
			FeedbackForm feedbackForm) {
		if (reviewService.updateReview(sessionInfo, feedbackForm)) {
			return true;
		} else {
			return false;
		}
	}

}
