/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.form;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

/**
 * This class represents the Spring UI form to Add / Edit a Attribute.
 * 
 * @author simanchal.patra
 * 
 */
@Component
public class AddAttributeForm {

	private Long categoryId;

	private String categoryName;

	private Long sectionId;

	private String sectionName;

	private Long attributeId;

	private String attributeName;

	private Boolean isSearchable;

	private Boolean isCompulsory;

	private Boolean isVariant;

	private Boolean isNumeric;

	private Boolean isImagable;

	private String unitType;

	private String customUnitType;

	private String mode;

	private LinkedHashMap<String, String> unitTypeMap;

	public AddAttributeForm() {
	}

	public AddAttributeForm(Long categoryId, String categoryName,
			Long sectionId, String sectionName, Long attributeId,
			String attributeName, Boolean isSearchable, Boolean isCompulsory,
			Boolean isVariant, String mode, Boolean isNumeric,
			Boolean isImagable, String unitType,
			LinkedHashMap<String, String> unitTypeMap, String customUnitType) {

		this.categoryId = categoryId;

		this.categoryName = categoryName;

		this.sectionId = sectionId;

		this.sectionName = sectionName;

		this.attributeId = attributeId;

		this.attributeName = attributeName;

		this.isSearchable = isSearchable;

		this.isCompulsory = isCompulsory;

		this.isVariant = isVariant;

		this.mode = mode;

		this.isNumeric = isNumeric;

		this.isImagable = isImagable;

		this.unitType = unitType;

		this.customUnitType = customUnitType;

		this.setUnitTypeMap(unitTypeMap);
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
	 * @return the attributeId
	 */
	public Long getAttributeId() {
		return attributeId;
	}

	/**
	 * @param attributeId
	 *            the attributeId to set
	 */
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName
	 *            the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the isSearchable
	 */
	public Boolean getIsSearchable() {
		return isSearchable;
	}

	/**
	 * @param isSearchable
	 *            the isSearchable to set
	 */
	public void setIsSearchable(Boolean isSearchable) {
		this.isSearchable = isSearchable;
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
	 * @return the isCompulsory
	 */
	public Boolean getIsCompulsory() {
		return isCompulsory;
	}

	/**
	 * @param isCompulsory
	 *            the isCompulsory to set
	 */
	public void setIsCompulsory(Boolean isCompulsory) {
		this.isCompulsory = isCompulsory;
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

	/**
	 * @return the isVariant
	 */
	public Boolean getIsVariant() {
		return isVariant;
	}

	/**
	 * @param isVariant
	 *            the isVariant to set
	 */
	public void setIsVariant(Boolean isVariant) {
		this.isVariant = isVariant;
	}

	/**
	 * @return the isNumeric
	 */
	public Boolean getIsNumeric() {
		return isNumeric;
	}

	/**
	 * @param isNumeric
	 *            the isNumeric to set
	 */
	public void setIsNumeric(Boolean isNumeric) {
		this.isNumeric = isNumeric;
	}

	/**
	 * @return the isImagable
	 */
	public Boolean getIsImagable() {
		return isImagable;
	}

	/**
	 * @param isImagable
	 *            the isImagable to set
	 */
	public void setIsImagable(Boolean isImagable) {
		this.isImagable = isImagable;
	}

	/**
	 * @return the unitType
	 */
	public String getUnitType() {
		return unitType;
	}

	/**
	 * @param unitType
	 *            the unitType to set
	 */
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	/**
	 * @return the customUnitType
	 */
	public String getCustomUnitType() {
		return customUnitType;
	}

	/**
	 * @param customUnitType
	 *            the customUnitType to set
	 */
	public void setCustomUnitType(String customUnitType) {
		this.customUnitType = customUnitType;
	}

	/**
	 * @return the unitTypeMap
	 */
	public LinkedHashMap<String, String> getUnitTypeMap() {
		return unitTypeMap;
	}

	/**
	 * @param unitTypeMap
	 *            the unitTypeMap to set
	 */
	public void setUnitTypeMap(LinkedHashMap<String, String> unitTypeMap) {
		this.unitTypeMap = unitTypeMap;
	}

}
