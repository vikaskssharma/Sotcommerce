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
import java.util.Set;
import java.util.HashSet;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="CATEGORY_SECTION_DETAILS")
public class CategorySection implements Serializable {
	public CategorySection() {
	}
	
	@Column(name="NM_SCTN_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_CATEGORYSECTION_SECTIONID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_CATEGORYSECTION_SECTIONID_GENERATOR", strategy="native")	
	private java.math.BigDecimal sectionID;
	
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_CTGRY_ID", referencedColumnName="NM_CTGRY_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Category NM_CTGRY;
	
	@Column(name="VC_SCTN_NM", nullable=true, length=64)	
	private String sectionName;
	
	@Column(name="DT_CRTD_AT", nullable=false)	
	private java.sql.Timestamp createdDate;
	
	@Column(name="DT_UPDTD_AT", nullable=false)	
	private java.sql.Timestamp updatedDate;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_CRTD_BY", referencedColumnName="NM_USR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private User NM_CRTD_BY;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_UPDTD_BY", referencedColumnName="NM_USR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private User NM_UPDTD_BY;
	
	@Column(name="IS_DLTD", nullable=false, length=1)	
	private int isDeleted;
	
	@OneToMany(mappedBy="NM_SCTN", targetEntity=CategorySectionAttributes.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private  Set CTGRY_SCTN_ATTR_TB_DTL = new HashSet();
	
	public void setSectionID(java.math.BigDecimal value) {
		this.sectionID = value;
	}
	
	public java.math.BigDecimal getSectionID() {
		return sectionID;
	}
	
	public java.math.BigDecimal getORMID() {
		return getSectionID();
	}
	
	public void setSectionName(String value) {
		this.sectionName = value;
	}
	
	public String getSectionName() {
		return sectionName;
	}
	
	public void setCreatedDate(java.sql.Timestamp value) {
		this.createdDate = value;
	}
	
	public java.sql.Timestamp getCreatedDate() {
		return createdDate;
	}
	
	public void setUpdatedDate(java.sql.Timestamp value) {
		this.updatedDate = value;
	}
	
	public java.sql.Timestamp getUpdatedDate() {
		return updatedDate;
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
	
	public void setNM_CRTD_BY(User value) {
		this.NM_CRTD_BY = value;
	}
	
	public User getNM_CRTD_BY() {
		return NM_CRTD_BY;
	}
	
	public void setNM_UPDTD_BY(User value) {
		this.NM_UPDTD_BY = value;
	}
	
	public User getNM_UPDTD_BY() {
		return NM_UPDTD_BY;
	}
	
	public void setCTGRY_SCTN_ATTR_TB_DTL(java.util.Set value) {
		this.CTGRY_SCTN_ATTR_TB_DTL = value;
	}
	
	public java.util.Set getCTGRY_SCTN_ATTR_TB_DTL() {
		return CTGRY_SCTN_ATTR_TB_DTL;
	}
	
	
	public String toString() {
		return String.valueOf(getSectionID());
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
