/**
 * (c) R Systems International Ltd. 
 */
package com.sot.ecommerce.vo;

/**
 * This class will contain Name and Identifier of a Attribute.
 * 
 * @author simanchal.patra
 * 
 */
public class AttributeVO {

	private Long attributeId;

	private String attributeName;

	public AttributeVO(Long attributeId, String attributeName) {

		this.attributeId = attributeId;

		this.attributeName = attributeName;
	}

	/**
	 * @return the attributeId
	 */
	public long getAttributeId() {
		return attributeId;
	}

	/**
	 * @param attributeId
	 *            the attributeId to set
	 */
	public void setAttributeId(long attributeId) {
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

}
