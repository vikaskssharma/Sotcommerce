/**
 * 
 */
package com.sot.ecommerce.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author vaibhav.jain
 * 
 */
@Component
public class AddPromotionForm {

	private Long promotionId = null;
	
	private Long storeId = null;

	private String promotionRuleName = null;

	private String promotionRuleCode = null;

	private String promotionDescription = null;

	private char promotionRequiredCode = 'M';

	private String promotionLimitPerOrder = null;
	
	private Boolean promotionLimitPerOrderCheckBox = null;

	private String promotionLimitPerCustomer = null;
	
	private Boolean promotionLimitPerCustomerCheckBox = null;

	private String promotionLimitPerPromotion = null;
	
	private Boolean promotionLimitPerPromotionCheckBox = null;

	private String status;

	private Map<String, String> allStatuses;

	private List<PromotionRuleCodeType> promotionListRuleCodeType;

	private String fromDate = null;

	private String toDate = null;

	private BigDecimal mode = null;

	private BigDecimal discountType = null;

	private String discountValue = null;

	private String orderOver = null;	
	
	private String categoryId = null;
	
	private String productId = null;
	
	private String categorySelected = null;
	
	private List<CategoryAjaxForm> categoryAjaxForms;
	
	private List<ProductAjaxForm> productAjaxForms;
	
	private String errorDiscount = null;
	
	private String pageMode;
	
	private Boolean isFromDateDisabled;
	
	private Boolean isToDateDisabled;
	
	public AddPromotionForm() {
	}
	
	
	
	public AddPromotionForm(Long promotionId, Long storeId,
			String promotionRuleName, String promotionRuleCode,
			String promotionDescription, char promotionRequiredCode,
			String promotionLimitPerOrder,
			Boolean promotionLimitPerOrderCheckBox,
			String promotionLimitPerCustomer,
			Boolean promotionLimitPerCustomerCheckBox,
			String promotionLimitPerPromotion,
			Boolean promotionLimitPerPromotionCheckBox, String status,
			Map<String, String> allStatuses,
			List<PromotionRuleCodeType> promotionListRuleCodeType,
			String fromDate, String toDate, BigDecimal mode,
			BigDecimal discountType, String discountValue, String orderOver,
			String categoryId, String productId, String categorySelected,
			List<CategoryAjaxForm> categoryAjaxForm,
			List<ProductAjaxForm> productAjaxForms, String errorDiscount,
			String pageMode) {
		super();
		this.promotionId = promotionId;
		this.storeId = storeId;
		this.promotionRuleName = promotionRuleName;
		this.promotionRuleCode = promotionRuleCode;
		this.promotionDescription = promotionDescription;
		this.promotionRequiredCode = promotionRequiredCode;
		this.promotionLimitPerOrder = promotionLimitPerOrder;
		this.promotionLimitPerOrderCheckBox = promotionLimitPerOrderCheckBox;
		this.promotionLimitPerCustomer = promotionLimitPerCustomer;
		this.promotionLimitPerCustomerCheckBox = promotionLimitPerCustomerCheckBox;
		this.promotionLimitPerPromotion = promotionLimitPerPromotion;
		this.promotionLimitPerPromotionCheckBox = promotionLimitPerPromotionCheckBox;
		this.status = status;
		this.allStatuses = allStatuses;
		this.promotionListRuleCodeType = promotionListRuleCodeType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.mode = mode;
		this.discountType = discountType;
		this.discountValue = discountValue;
		this.orderOver = orderOver;
		this.categoryId = categoryId;
		this.productId = productId;
		this.categorySelected = categorySelected;
		this.setCategoryAjaxForms(categoryAjaxForm);
		this.setProductAjaxForms(productAjaxForms);
		this.errorDiscount = errorDiscount;
		this.setPageMode(pageMode);
	}
	
	public AddPromotionForm(List<CategoryAjaxForm> categoryAjaxForm,
			List<ProductAjaxForm> productAjaxForms){
		this.setCategoryAjaxForms(categoryAjaxForm);
		this.setProductAjaxForms(productAjaxForms);
	}




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

	public Map<String, String> getAllStatuses() {
		return allStatuses;
	}

	public void setAllStatuses(Map<String, String> allStatuses) {
		this.allStatuses = allStatuses;
	}


	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public List<PromotionRuleCodeType> getPromotionListRuleCodeType() {
		return promotionListRuleCodeType;
	}

	public void setPromotionListRuleCodeType(
			List<PromotionRuleCodeType> promotionListRuleCodeType) {
		this.promotionListRuleCodeType = promotionListRuleCodeType;
	}

	public BigDecimal getMode() {
		return mode;
	}

	public void setMode(BigDecimal mode) {
		this.mode = mode;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public char getPromotionRequiredCode() {
		return promotionRequiredCode;
	}

	public void setPromotionRequiredCode(char promotionRequiredCode) {
		this.promotionRequiredCode = promotionRequiredCode;
	}

	public BigDecimal getDiscountType() {
		return discountType;
	}

	public void setDiscountType(BigDecimal discountType) {
		this.discountType = discountType;
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

	public String getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(String discountValue) {
		this.discountValue = discountValue;
	}

	public String getErrorDiscount() {
		return errorDiscount;
	}

	public void setErrorDiscount(String errorDiscount) {
		this.errorDiscount = errorDiscount;
	}

	public String getOrderOver() {
		return orderOver;
	}

	public void setOrderOver(String orderOver) {
		this.orderOver = orderOver;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}


	public Boolean getPromotionLimitPerOrderCheckBox() {
		return promotionLimitPerOrderCheckBox;
	}

	public void setPromotionLimitPerOrderCheckBox(
			Boolean promotionLimitPerOrderCheckBox) {
		this.promotionLimitPerOrderCheckBox = promotionLimitPerOrderCheckBox;
	}

	public Boolean getPromotionLimitPerCustomerCheckBox() {
		return promotionLimitPerCustomerCheckBox;
	}

	public void setPromotionLimitPerCustomerCheckBox(
			Boolean promotionLimitPerCustomerCheckBox) {
		this.promotionLimitPerCustomerCheckBox = promotionLimitPerCustomerCheckBox;
	}

	public Boolean getPromotionLimitPerPromotionCheckBox() {
		return promotionLimitPerPromotionCheckBox;
	}

	public void setPromotionLimitPerPromotionCheckBox(
			Boolean promotionLimitPerPromotionCheckBox) {
		this.promotionLimitPerPromotionCheckBox = promotionLimitPerPromotionCheckBox;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategorySelected() {
		return categorySelected;
	}

	public void setCategorySelected(String categorySelected) {
		this.categorySelected = categorySelected;
	}



	public List<CategoryAjaxForm> getCategoryAjaxForms() {
		return categoryAjaxForms;
	}



	public void setCategoryAjaxForms(List<CategoryAjaxForm> categoryAjaxForms) {
		this.categoryAjaxForms = categoryAjaxForms;
	}



	public List<ProductAjaxForm> getProductAjaxForms() {
		return productAjaxForms;
	}



	public void setProductAjaxForms(List<ProductAjaxForm> productAjaxForms) {
		this.productAjaxForms = productAjaxForms;
	}



	public String getPageMode() {
		return pageMode;
	}



	public void setPageMode(String pageMode) {
		this.pageMode = pageMode;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}



	/**
	 * @return the isFromDateDisabled
	 */
	public Boolean getIsFromDateDisabled() {
		return isFromDateDisabled;
	}



	/**
	 * @param isFromDateDisabled the isFromDateDisabled to set
	 */
	public void setIsFromDateDisabled(Boolean isFromDateDisabled) {
		this.isFromDateDisabled = isFromDateDisabled;
	}



	/**
	 * @return the isToDateDisabled
	 */
	public Boolean getIsToDateDisabled() {
		return isToDateDisabled;
	}



	/**
	 * @param isToDateDisabled the isToDateDisabled to set
	 */
	public void setIsToDateDisabled(Boolean isToDateDisabled) {
		this.isToDateDisabled = isToDateDisabled;
	}




}
