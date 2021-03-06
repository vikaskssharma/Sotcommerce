/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.vo;

/**
 * This class serves the purpose to represent a Category (Name, Identifier,
 * Identifier of it's Parent and it's Status).
 * 
 * @author simanchal.patra
 */
public class CategoryVO {

	private String categoryName;

	private Long categoryId;

	private Long parentCategoryId;

	private String status;

	private Boolean isPlaceHolder;
	
	private Boolean isAtributeAvaiable;

	public CategoryVO() {
	}

	public CategoryVO(Long categoryId, String categoryName,
			Long parentCategoryId, String status, Boolean isPlaceHolder, Boolean isAtributeAvaiable) {

		this.categoryId = categoryId;

		this.categoryName = categoryName;

		this.parentCategoryId = parentCategoryId;

		this.status = status;

		this.isPlaceHolder = isPlaceHolder;
		
		this.isAtributeAvaiable = isAtributeAvaiable;
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

	/**
	 * @return the parentCategoryId
	 */
	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	/**
	 * @param parentCategoryId
	 *            the parentCategoryId to set
	 */
	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the isPlaceHolder
	 */
	public Boolean getIsPlaceHolder() {
		return isPlaceHolder;
	}

	/**
	 * @param isPlaceHolder the isPlaceHolder to set
	 */
	public void setIsPlaceHolder(Boolean isPlaceHolder) {
		this.isPlaceHolder = isPlaceHolder;
	}

	/**
	 * @return the isAtributeAvaiable
	 */
	public Boolean getIsAtributeAvaiable() {
		return isAtributeAvaiable;
	}

	/**
	 * @param isAtributeAvaiable the isAtributeAvaiable to set
	 */
	public void setIsAtributeAvaiable(Boolean isAtributeAvaiable) {
		this.isAtributeAvaiable = isAtributeAvaiable;
	}
	
	

}
