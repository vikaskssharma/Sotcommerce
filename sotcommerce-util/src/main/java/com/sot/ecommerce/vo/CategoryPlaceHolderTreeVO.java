/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.vo;

/**
 * This class serves the purpose to represent a Category (Name, Identifier,
 * level in the Tree to it's immediate Parent) to show in a Hierarchical Tree
 * format.
 * 
 * @author simanchal.patra
 * 
 */
public class CategoryPlaceHolderTreeVO {

	private String categoryName;

	private Long categoryId;

	private short level;

	public CategoryPlaceHolderTreeVO() {
	}

	public CategoryPlaceHolderTreeVO(Long categoryId, String categoryName,
			short level) {
		this.categoryId = categoryId;

		this.categoryName = categoryName;

		this.level = level;
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
	 * @return the level
	 */
	public short getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(short level) {
		this.level = level;
	}

}
