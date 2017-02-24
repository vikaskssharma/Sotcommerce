/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package com.sot.ecommerce.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="CTGRY_SECTION_ATTRIBUTE")
public class CategorySectionAttributes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategorySectionAttributes() {
	}
	
	@Column(name="NM_ATTR_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_CATEGORYSECTIONATTRIBUTES_ATTRIBUTEID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_CATEGORYSECTIONATTRIBUTES_ATTRIBUTEID_GENERATOR", strategy="native")	
	private java.math.BigDecimal attributeId;
	
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_CTGRY_ID", referencedColumnName="NM_CTGRY_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Category NM_CTGRY;
	
	@ManyToOne(targetEntity=CategorySection.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_SCTN_ID", referencedColumnName="NM_SCTN_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private CategorySection NM_SCTN;
	
	@Column(name="VC_ATTR_NM", nullable=false, length=64)	
	private String attributeName;
	
	@Column(name="VC_ATTR_TYP", nullable=false, length=16)	
	private String attributeType;
	
	@Column(name="VC_ATTR_MPPNG", nullable=true, length=64)	
	private String attributeColumnMapping;
	
	@Column(name="IS_SRCHBL", nullable=false, length=1)	
	private int isSearchable;
	
	@Column(name="IS_IMGBL", nullable=false, length=1)	
	private int isImageable;
	
	@Column(name="VC_ATTR_IMG_MPPNG", nullable=true, length=64)	
	private String attributeImageMapping;
	
	@Column(name="IS_DLTD", nullable=false, length=1)	
	private int isDeleted;
	
	public void setAttributeId(java.math.BigDecimal value) {
		this.attributeId = value;
	}
	
	public java.math.BigDecimal getAttributeId() {
		return attributeId;
	}
	
	public java.math.BigDecimal getORMID() {
		return getAttributeId();
	}
	
	public void setAttributeName(String value) {
		this.attributeName = value;
	}
	
	public String getAttributeName() {
		return attributeName;
	}
	
	public void setAttributeType(String value) {
		this.attributeType = value;
	}
	
	public String getAttributeType() {
		return attributeType;
	}
	
	public void setAttributeColumnMapping(String value) {
		this.attributeColumnMapping = value;
	}
	
	public String getAttributeColumnMapping() {
		return attributeColumnMapping;
	}
	
	public void setIsSearchable(int value) {
		this.isSearchable = value;
	}
	
	public int getIsSearchable() {
		return isSearchable;
	}
	
	public void setIsImageable(int value) {
		this.isImageable = value;
	}
	
	public int getIsImageable() {
		return isImageable;
	}
	
	public void setAttributeImageMapping(String value) {
		this.attributeImageMapping = value;
	}
	
	public String getAttributeImageMapping() {
		return attributeImageMapping;
	}
	
	public void setIsDeleted(int value) {
		this.isDeleted = value;
	}
	
	public int getIsDeleted() {
		return isDeleted;
	}
	
	public void setNM_CTGRY(Category value) {
		this.NM_CTGRY = value;
	}
	
	public Category getNM_CTGRY() {
		return NM_CTGRY;
	}
	
	public void setNM_SCTN(CategorySection value) {
		this.NM_SCTN = value;
	}
	
	public CategorySection getNM_SCTN() {
		return NM_SCTN;
	}
	
	public String toString() {
		return String.valueOf(getAttributeId());
	}
	
	@Transient	
	private boolean _saved = false;
	
	public void onSave() {
		_saved=true;
	}
	
	
	public void onLoad() {
		_saved=true;
	}
	
	
	public boolean isSaved() {
		return _saved;
	}
	
	
}
