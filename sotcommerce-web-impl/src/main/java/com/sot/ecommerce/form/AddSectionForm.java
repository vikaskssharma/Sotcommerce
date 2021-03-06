/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.form;

import org.springframework.stereotype.Component;

/**
 * This class represents the Spring UI form for the Add / Edit a Section.
 * 
 * @author simanchal.patra
 * 
 */
@Component
public class AddSectionForm {

	private Long categoryId;

	private String categoryName;

	private Long sectionId;

	private String sectionName;

	private String mode;

	public AddSectionForm() {
	}

	public AddSectionForm(Long categoryId, String categoryName, Long sectionId,
			String sectionName, String mode) {

		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.sectionName = sectionName;
		this.sectionId = sectionId;
		this.mode = mode;
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
	 * @return the sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * @param sectionName
	 *            the sectionName to set
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * @return the sectionId
	 */
	public Long getSectionId() {
		return sectionId;
	}

	/**
	 * @param sectionId
	 *            the sectionId to set
	 */
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
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
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

}
