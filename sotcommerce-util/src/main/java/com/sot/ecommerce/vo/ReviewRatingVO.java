package com.sot.ecommerce.vo;

/**
 * @author gaurav.kumar
 */

import java.math.BigDecimal;
import java.util.Date;

public class ReviewRatingVO {

	private long id;

	private long productId;

	private String productName;

	private String reviewDesc;

	private BigDecimal productRating;

	private Date createdOn;

	private String isApproved;

	private String orderNumber;

	public ReviewRatingVO(long id, long productId, String productName,
			String reviewDesc, BigDecimal productRating, Date createdOn,
			String isApproved, String orderNumber) {

		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.reviewDesc = reviewDesc;
		this.productRating = productRating;
		this.createdOn = createdOn;
		this.isApproved = isApproved;
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
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

	public String getReviewDesc() {
		return reviewDesc;
	}

	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}

	/**
	 * @return the productRating
	 */
	public BigDecimal getProductRating() {
		return productRating;
	}

	/**
	 * @param productRating
	 *            the productRating to set
	 */
	public void setProductRating(BigDecimal productRating) {
		this.productRating = productRating;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isReviewApproved) {
		this.isApproved = isReviewApproved;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn
	 *            the createdOn to set
	 */
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
