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
@Table(name="CATEGORY_TABLE")
public class Category implements Serializable {
	public Category() {
	}
	
	@Column(name="NM_CTGRY_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_CATEGORY_CATEGORYID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_CATEGORY_CATEGORYID_GENERATOR", strategy="native")	
	private java.math.BigDecimal categoryId;
	
	@Column(name="VC_CTGRY_NM", nullable=false, length=64)	
	private String categoryName;
	
	@Column(name="VC_CTGRY_DSC", nullable=true, length=128)	
	private String categoryDesc;
	
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_PRNT_CTGRY_ID", referencedColumnName="NM_CTGRY_ID") })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Category NM_PRNT_CTGRY;
	
	@ManyToOne(targetEntity=Store.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_STR_ID", referencedColumnName="NM_STR_ID") })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Store NM_STR;
	
	@Column(name="IS_ACTV", nullable=false, length=1)	
	private int isActive;
	
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
	
	@Column(name="IS_GLBL", nullable=false, length=1)	
	private int isGlobal;
	
	@OneToMany(mappedBy="NM_PRNT_CTGRY", targetEntity=Category.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private Set CATEGORY_TB_MASTER = new HashSet();
	
	@OneToMany(mappedBy="NM_CTGRY", targetEntity=CategorySectionAttributes.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private Set CTGRY_SCTN_ATTR_TB_DTL = new HashSet();
	
	@OneToMany(mappedBy="NM_CTGRY", targetEntity=CategorySection.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private Set CTGRY_SCTN_TB_DTL = new HashSet();
	
	@OneToMany(mappedBy="NM_CTGRY", targetEntity=Product.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private Set PRODUCT_TB_MASTER = new HashSet();
	
	@OneToMany(mappedBy="NM_CTGRY", targetEntity=Promotion.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private Set PROMOTION_TB_MASTER = new HashSet();
	
	public void setCategoryId(java.math.BigDecimal value) {
		this.categoryId = value;
	}
	
	public java.math.BigDecimal getCategoryId() {
		return categoryId;
	}
	
	public java.math.BigDecimal getORMID() {
		return getCategoryId();
	}
	
	public void setCategoryName(String value) {
		this.categoryName = value;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryDesc(String value) {
		this.categoryDesc = value;
	}
	
	public String getCategoryDesc() {
		return categoryDesc;
	}
	
	public void setIsActive(int value) {
		this.isActive = value;
	}
	
	public int getIsActive() {
		return isActive;
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
	
	public void setIsGlobal(int value) {
		this.isGlobal = value;
	}
	
	public int getIsGlobal() {
		return isGlobal;
	}
	
	public void setNM_PRNT_CTGRY(Category value) {
		this.NM_PRNT_CTGRY = value;
	}
	
	public Category getNM_PRNT_CTGRY() {
		return NM_PRNT_CTGRY;
	}
	
	public void setNM_STR(Store value) {
		this.NM_STR = value;
	}
	
	public Store getNM_STR() {
		return NM_STR;
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
	
	public void setCATEGORY_TB_MASTER(java.util.Set value) {
		this.CATEGORY_TB_MASTER = value;
	}
	
	public java.util.Set getCATEGORY_TB_MASTER() {
		return CATEGORY_TB_MASTER;
	}
	
	
	public void setCTGRY_SCTN_ATTR_TB_DTL(java.util.Set value) {
		this.CTGRY_SCTN_ATTR_TB_DTL = value;
	}
	
	public java.util.Set getCTGRY_SCTN_ATTR_TB_DTL() {
		return CTGRY_SCTN_ATTR_TB_DTL;
	}
	
	
	public void setCTGRY_SCTN_TB_DTL(java.util.Set value) {
		this.CTGRY_SCTN_TB_DTL = value;
	}
	
	public java.util.Set getCTGRY_SCTN_TB_DTL() {
		return CTGRY_SCTN_TB_DTL;
	}
	
	
	public void setPRODUCT_TB_MASTER(java.util.Set value) {
		this.PRODUCT_TB_MASTER = value;
	}
	
	public java.util.Set getPRODUCT_TB_MASTER() {
		return PRODUCT_TB_MASTER;
	}
	
	
	public void setPROMOTION_TB_MASTER(java.util.Set value) {
		this.PROMOTION_TB_MASTER = value;
	}
	
	public java.util.Set getPROMOTION_TB_MASTER() {
		return PROMOTION_TB_MASTER;
	}
	
	
	public String toString() {
		return String.valueOf(getCategoryId());
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
