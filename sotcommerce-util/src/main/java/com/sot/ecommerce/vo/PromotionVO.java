package com.sot.ecommerce.vo;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionVO {
	
	private Long promoId;

	private Long storeId;

	private String promotionRuleName;

	private String promotionRuleCode;

	private String promotionDescription;

	private String promotionRuleCodeType;

	private String promotionLimitPerOrder;

	private String promotionLimitPerCustomer;

	private String promotionLimitPerPromotion;

	private String status;

	private Date fromDate;

	private Date toDate;

	private String discountType;

	private String discountValue;

	private String orderOver;

	private String categoryName;

	private String categoryVO;
	
	private BigDecimal isDel;

	public String getPromotionRuleName() {
		return promotionRuleName;
	}

	public void setPromotionRuleName(String promotionRuleName) {
		this.promotionRuleName = promotionRuleName;
	}

	public String getPromotionRuleCode() {
		return promotionRuleCode;
	}

	public void setPromotionRuleCode(String promotionRuleCode) {
		this.promotionRuleCode = promotionRuleCode;
	}

	public String getPromotionDescription() {
		return promotionDescription;
	}

	public void setPromotionDescription(String promotionDescription) {
		this.promotionDescription = promotionDescription;
	}

	public String getPromotionLimitPerOrder() {
		return promotionLimitPerOrder;
	}

	public void setPromotionLimitPerOrder(String promotionLimitPerOrder) {
		this.promotionLimitPerOrder = promotionLimitPerOrder;
	}

	public String getPromotionLimitPerCustomer() {
		return promotionLimitPerCustomer;
	}

	public void setPromotionLimitPerCustomer(String promotionLimitPerCustomer) {
		this.promotionLimitPerCustomer = promotionLimitPerCustomer;
	}

	public String getPromotionLimitPerPromotion() {
		return promotionLimitPerPromotion;
	}

	public void setPromotionLimitPerPromotion(String promotionLimitPerPromotion) {
		this.promotionLimitPerPromotion = promotionLimitPerPromotion;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getPromotionRuleCodeType() {
		return promotionRuleCodeType;
	}

	public void setPromotionRuleCodeType(String promotionRuleCodeType) {
		this.promotionRuleCodeType = promotionRuleCodeType;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}


	public String getOrderOver() {
		return orderOver;
	}

	public void setOrderOver(String orderOver) {
		this.orderOver = orderOver;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryVO() {
		return categoryVO;
	}

	public void setCategoryVO(String categoryVO) {
		this.categoryVO = categoryVO;
	}

	public Long getPromoId() {
		return promoId;
	}

	public void setPromoId(Long promoId) {
		this.promoId = promoId;
	}

	public BigDecimal getIsDel() {
		return isDel;
	}

	public void setIsDel(BigDecimal isDel) {
		this.isDel = isDel;
	}

	public String getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}



}
