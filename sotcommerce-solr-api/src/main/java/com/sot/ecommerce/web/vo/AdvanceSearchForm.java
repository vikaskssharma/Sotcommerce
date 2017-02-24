/**
 * 
 */
package com.sot.ecommerce.web.vo;

/**
 * @author simanchal.patra
 * 
 */
public class AdvanceSearchForm {

	private String keyWords;

	private String excludeWords;

	private String textOrder;

	private Long categoryId;

	private String priceLow;

	private String priceHigh;

	private Boolean isFreeShipping;

	private Boolean isCOD;

	/**
	 * @return the keyWords
	 */
	public String getKeyWords() {
		return keyWords;
	}

	/**
	 * @param keyWords
	 *            the keyWords to set
	 */
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	/**
	 * @return the excludeWords
	 */
	public String getExcludeWords() {
		return excludeWords;
	}

	/**
	 * @param excludeWords
	 *            the excludeWords to set
	 */
	public void setExcludeWords(String excludeWords) {
		this.excludeWords = excludeWords;
	}

	/**
	 * @return the textOrder
	 */
	public String getTextOrder() {
		return textOrder;
	}

	/**
	 * @param textOrder
	 *            the textOrder to set
	 */
	public void setTextOrder(String textOrder) {
		this.textOrder = textOrder;
	}

	/**
	 * @return the category
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the isFreeShipping
	 */
	public Boolean getIsFreeShipping() {
		return isFreeShipping;
	}

	/**
	 * @param isFreeShipping
	 *            the isFreeShipping to set
	 */
	public void setIsFreeShipping(Boolean isFreeShipping) {
		this.isFreeShipping = isFreeShipping;
	}

	/**
	 * @return the isCOD
	 */
	public Boolean getIsCOD() {
		return isCOD;
	}

	/**
	 * @param isCOD
	 *            the isCOD to set
	 */
	public void setIsCOD(Boolean isCOD) {
		this.isCOD = isCOD;
	}

	/**
	 * @return the priceLow
	 */
	public String getPriceLow() {
		return priceLow;
	}

	/**
	 * @param priceLow
	 *            the priceLow to set
	 */
	public void setPriceLow(String priceLow) {
		this.priceLow = priceLow;
	}

	/**
	 * @return the priceHigh
	 */
	public String getPriceHigh() {
		return priceHigh;
	}

	/**
	 * @param priceHigh
	 *            the priceHigh to set
	 */
	public void setPriceHigh(String priceHigh) {
		this.priceHigh = priceHigh;
	}

}
