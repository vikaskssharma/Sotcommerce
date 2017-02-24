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
@Table(name="STORE_TB_MASTER")
public class Store implements Serializable {
	public Store() {
	}
	
	@Column(name="NM_STR_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_STORE_STOREID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_STORE_STOREID_GENERATOR", strategy="native")	
	private java.math.BigDecimal storeId;
	
	@Column(name="VC_STR_NM", nullable=false, length=128)	
	private String storeName;
	
	@Column(name="DT_CRTD_AT", nullable=false)	
	private java.sql.Timestamp createdDate;
	
	@Column(name="DT_UPDTD_AT", nullable=false)	
	private java.sql.Timestamp updatedDate;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_CRTD_BY", referencedColumnName="NM_USR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private User NM_CRTD_BY;
	
	@Column(name="NM_UPDTD_BY", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal updatedBy;
	
	@Column(name="IS_DLTD", nullable=false, length=1)	
	private int isDeleted;
	
	@OneToMany(mappedBy="NM_STR", targetEntity=Category.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set CATEGORY_TB_MASTER = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_STR", targetEntity=Order.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_TB_MASTER = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_STR", targetEntity=Promotion.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set PROMOTION_TB_MASTER = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_STR", targetEntity=User.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set UM_TB_USER = new java.util.HashSet();
	
	public void setStoreId(java.math.BigDecimal value) {
		this.storeId = value;
	}
	
	public java.math.BigDecimal getStoreId() {
		return storeId;
	}
	
	public java.math.BigDecimal getORMID() {
		return getStoreId();
	}
	
	public void setStoreName(String value) {
		this.storeName = value;
	}
	
	public String getStoreName() {
		return storeName;
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
	
	public void setUpdatedBy(java.math.BigDecimal value) {
		this.updatedBy = value;
	}
	
	public java.math.BigDecimal getUpdatedBy() {
		return updatedBy;
	}
	
	public void setIsDeleted(int value) {
		this.isDeleted = value;
	}
	
	public int getIsDeleted() {
		return isDeleted;
	}
	
	public void setNM_CRTD_BY(User value) {
		this.NM_CRTD_BY = value;
	}
	
	public User getNM_CRTD_BY() {
		return NM_CRTD_BY;
	}
	
	public void setCATEGORY_TB_MASTER(java.util.Set value) {
		this.CATEGORY_TB_MASTER = value;
	}
	
	public java.util.Set getCATEGORY_TB_MASTER() {
		return CATEGORY_TB_MASTER;
	}
	
	
	public void setORDER_TB_MASTER(java.util.Set value) {
		this.ORDER_TB_MASTER = value;
	}
	
	public java.util.Set getORDER_TB_MASTER() {
		return ORDER_TB_MASTER;
	}
	
	
	public void setPROMOTION_TB_MASTER(java.util.Set value) {
		this.PROMOTION_TB_MASTER = value;
	}
	
	public java.util.Set getPROMOTION_TB_MASTER() {
		return PROMOTION_TB_MASTER;
	}
	
	
	public void setUM_TB_USER(java.util.Set value) {
		this.UM_TB_USER = value;
	}
	
	public java.util.Set getUM_TB_USER() {
		return UM_TB_USER;
	}
	
	
	public String toString() {
		return String.valueOf(getStoreId());
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
