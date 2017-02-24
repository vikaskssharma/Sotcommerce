/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.vo;

/**
 * This class will contain Name and Identifier of a Section.
 * 
 * @author simanchal.patra
 * 
 */
public class SectionVO {

	private Long sectionId;

	private String sectionName;

	public SectionVO(Long sectionId, String sectionName) {

		this.sectionId = sectionId;

		this.sectionName = sectionName;
	}

	/**
	 * @return the sectionId
	 */
	public long getSectionId() {
		return sectionId;
	}

	/**
	 * @param sectionId
	 *            the sectionId to set
	 */
	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
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

}
