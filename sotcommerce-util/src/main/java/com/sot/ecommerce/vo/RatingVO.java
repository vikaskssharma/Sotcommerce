package com.sot.ecommerce.vo;

/**
 * @author gaurav.kumar
 */

import java.math.BigDecimal;
import java.util.Date;

public class RatingVO {
	
	private String orderNumber;
	
	private long ratingId;
	
	private long productId;
	
	private String productName;
	
	private BigDecimal rating;
	
	private Date createdOn;
	
	private String isRatingApproved;

	public long getRatingId() {
		return ratingId;
	}

	public void setRatingId(long ratingId) {
		this.ratingId = ratingId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public String getIsRatingApproved() {
		return isRatingApproved;
	}

	public void setIsRatingApproved(String isRatingApproved) {
		this.isRatingApproved = isRatingApproved;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
