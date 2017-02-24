package com.sot.ecommerce.vo;


/**
 * This class serves the purpose to represent a Category (Name, Identifier)
 * to show in a Category drop box
 * format.
 * 
 * @author vaibhav.jain
 * 
 */
public class PromotionCategoryVO {

	private String categoryName;

	private Long categoryId;

	public PromotionCategoryVO() {
	}

	public PromotionCategoryVO(Long categoryId, String categoryName) {
		this.categoryId = categoryId;

		this.categoryName = categoryName;

	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


}


