package com.sot.ecommerce.form;

import org.springframework.stereotype.Component;

/**
 * 
 * @author gaurav.kumar
 * 
 */

@Component
public class FeedbackForm {

	private String orderId = "";

	private String prodId = "";

	private String productRating = "";

	private String productReview = "";

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProductRating() {
		return productRating;
	}

	public void setProductRating(String productRating) {
		this.productRating = productRating;
	}

	public String getProductReview() {
		return productReview;
	}

	public void setProductReview(String productReview) {
		this.productReview = productReview;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
