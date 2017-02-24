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
@Table(name="UM_TB_USER")
public class User implements Serializable {
	public User() {
	}
	
	@Column(name="NM_USR_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_USER_USERID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_USER_USERID_GENERATOR", strategy="native")	
	private long userId;
	
	@ManyToOne(targetEntity=Store.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_STR_ID", referencedColumnName="NM_STR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Store NM_STR;
	
	@Column(name="VC_PSWRD", nullable=false, length=32)	
	private String password;
	
	@Column(name="VC_FRST_NM", nullable=false, length=32)	
	private String firstName;
	
	@Column(name="VC_LST_NM", nullable=true, length=32)	
	private String lastName;
	
	@Column(name="VC_PHN", nullable=false, length=16)	
	private String phone;
	
	@Column(name="VC_EML", nullable=false, length=32)	
	private String email;
	
	@Column(name="VC_ADDR", nullable=false, length=256)	
	private String address;
	
	@Column(name="DT_CRTD_AT", nullable=false)	
	private java.sql.Timestamp createdDate;
	
	@Column(name="NM_CRTD_BY", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal createdBy;
	
	@Column(name="IS_DLTD", nullable=false, length=1)	
	private int isDeleted;
	
	@Column(name="VC_USR_GNDR", nullable=true, length=1)	
	private String userGender;
	
	@Column(name="VC_CTY", nullable=true, length=32)	
	private String city;
	
	@Column(name="VC_ST", nullable=true, length=16)	
	private String state;
	
	@Column(name="VC_ZPCD", nullable=true, length=16)	
	private String zip;
	
	@ManyToOne(targetEntity=Country.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_CNTRY_ID", referencedColumnName="NM_CNTRY_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Country NM_CNTRY;
	
	@Column(name="DT_UPDTD_AT", nullable=false)	
	private java.sql.Timestamp updatedDate;
	
	@Column(name="NM_UPDTD_BY", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal updatedBy;
	
	@ManyToMany(mappedBy="NM_USR", targetEntity=Role.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set NM_RL = new java.util.HashSet();
	
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=Banner.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set BANNER_TB_MASTER = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_UPDTD_BY", targetEntity=Banner.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set BANNER_TB_MASTER1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=Category.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set CATEGORY_TB_MASTER = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_UPDTD_BY", targetEntity=Category.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set CATEGORY_TB_MASTER1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=CategorySection.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set CTGRY_SCTN_TB_DTL = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_UPDTD_BY", targetEntity=CategorySection.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set CTGRY_SCTN_TB_DTL1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=OrderBillingDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_BILLING_TB_DTL = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_UPDTD_BY", targetEntity=OrderBillingDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_BILLING_TB_DTL1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=OrderProductDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_PRODUCT_TB_DETAIL = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_UPDTD_BY", targetEntity=OrderProductDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_PRODUCT_TB_DETAIL1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=OrderShippingDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_SHIPPING_TB_DTL = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_UPDTD_BY", targetEntity=OrderShippingDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_SHIPPING_TB_DTL1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_USR", targetEntity=Order.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_TB_MASTER = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=Product.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set PRODUCT_TB_MASTER = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_UPDTD_BY", targetEntity=Product.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set PRODUCT_TB_MASTER1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=Promotion.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set PROMOTION_TB_MASTER = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_UPDTD_BY", targetEntity=Promotion.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set PROMOTION_TB_MASTER1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=Store.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set STORE_TB_MASTER = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=Menu.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set UM_TB_MENU = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_UPDTD_BY", targetEntity=Menu.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set UM_TB_MENU1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_CRTD_BY", targetEntity=Role.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set UM_TB_ROLE = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_UPDTD_BY", targetEntity=Role.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set UM_TB_ROLE1 = new java.util.HashSet();
	
	
	
	public void setUserId(long value) {
		this.userId = value;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public long getORMID() {
		return getUserId();
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setFirstName(String value) {
		this.firstName = value;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String value) {
		this.lastName = value;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setCreatedDate(java.sql.Timestamp value) {
		this.createdDate = value;
	}
	
	public java.sql.Timestamp getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedBy(java.math.BigDecimal value) {
		this.createdBy = value;
	}
	
	public java.math.BigDecimal getCreatedBy() {
		return createdBy;
	}
	
	public void setIsDeleted(int value) {
		this.isDeleted = value;
	}
	
	public int getIsDeleted() {
		return isDeleted;
	}
	
	public void setUserGender(String value) {
		this.userGender = value;
	}
	
	public String getUserGender() {
		return userGender;
	}
	
	public void setCity(String value) {
		this.city = value;
	}
	
	public String getCity() {
		return city;
	}
	
	
	
	public void setZip(String value) {
		this.zip = value;
	}
	
	public String getZip() {
		return zip;
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
	
	public void setNM_STR(Store value) {
		this.NM_STR = value;
	}
	
	public Store getNM_STR() {
		return NM_STR;
	}
	
	public void setNM_CNTRY(Country value) {
		this.NM_CNTRY = value;
	}
	
	public Country getNM_CNTRY() {
		return NM_CNTRY;
	}
	
	public void setNM_RL(java.util.Set value) {
		this.NM_RL = value;
	}
	
	public java.util.Set getNM_RL() {
		return NM_RL;
	}
	
	
	public void setBANNER_TB_MASTER(java.util.Set value) {
		this.BANNER_TB_MASTER = value;
	}
	
	public java.util.Set getBANNER_TB_MASTER() {
		return BANNER_TB_MASTER;
	}
	
	
	public void setBANNER_TB_MASTER1(java.util.Set value) {
		this.BANNER_TB_MASTER1 = value;
	}
	
	public java.util.Set getBANNER_TB_MASTER1() {
		return BANNER_TB_MASTER1;
	}
	
	
	public void setCATEGORY_TB_MASTER(java.util.Set value) {
		this.CATEGORY_TB_MASTER = value;
	}
	
	public java.util.Set getCATEGORY_TB_MASTER() {
		return CATEGORY_TB_MASTER;
	}
	
	
	public void setCATEGORY_TB_MASTER1(java.util.Set value) {
		this.CATEGORY_TB_MASTER1 = value;
	}
	
	public java.util.Set getCATEGORY_TB_MASTER1() {
		return CATEGORY_TB_MASTER1;
	}
	
	
	public void setCTGRY_SCTN_TB_DTL(java.util.Set value) {
		this.CTGRY_SCTN_TB_DTL = value;
	}
	
	public java.util.Set getCTGRY_SCTN_TB_DTL() {
		return CTGRY_SCTN_TB_DTL;
	}
	
	
	public void setCTGRY_SCTN_TB_DTL1(java.util.Set value) {
		this.CTGRY_SCTN_TB_DTL1 = value;
	}
	
	public java.util.Set getCTGRY_SCTN_TB_DTL1() {
		return CTGRY_SCTN_TB_DTL1;
	}
	
	
	public void setORDER_BILLING_TB_DTL(java.util.Set value) {
		this.ORDER_BILLING_TB_DTL = value;
	}
	
	public java.util.Set getORDER_BILLING_TB_DTL() {
		return ORDER_BILLING_TB_DTL;
	}
	
	
	public void setORDER_BILLING_TB_DTL1(java.util.Set value) {
		this.ORDER_BILLING_TB_DTL1 = value;
	}
	
	public java.util.Set getORDER_BILLING_TB_DTL1() {
		return ORDER_BILLING_TB_DTL1;
	}
	
	
	public void setORDER_PRODUCT_TB_DETAIL(java.util.Set value) {
		this.ORDER_PRODUCT_TB_DETAIL = value;
	}
	
	public java.util.Set getORDER_PRODUCT_TB_DETAIL() {
		return ORDER_PRODUCT_TB_DETAIL;
	}
	
	
	public void setORDER_PRODUCT_TB_DETAIL1(java.util.Set value) {
		this.ORDER_PRODUCT_TB_DETAIL1 = value;
	}
	
	public java.util.Set getORDER_PRODUCT_TB_DETAIL1() {
		return ORDER_PRODUCT_TB_DETAIL1;
	}
	
	
	public void setORDER_SHIPPING_TB_DTL(java.util.Set value) {
		this.ORDER_SHIPPING_TB_DTL = value;
	}
	
	public java.util.Set getORDER_SHIPPING_TB_DTL() {
		return ORDER_SHIPPING_TB_DTL;
	}
	
	
	public void setORDER_SHIPPING_TB_DTL1(java.util.Set value) {
		this.ORDER_SHIPPING_TB_DTL1 = value;
	}
	
	public java.util.Set getORDER_SHIPPING_TB_DTL1() {
		return ORDER_SHIPPING_TB_DTL1;
	}
	
	
	public void setORDER_TB_MASTER(java.util.Set value) {
		this.ORDER_TB_MASTER = value;
	}
	
	public java.util.Set getORDER_TB_MASTER() {
		return ORDER_TB_MASTER;
	}
	
	
	public void setPRODUCT_TB_MASTER(java.util.Set value) {
		this.PRODUCT_TB_MASTER = value;
	}
	
	public java.util.Set getPRODUCT_TB_MASTER() {
		return PRODUCT_TB_MASTER;
	}
	
	
	public void setPRODUCT_TB_MASTER1(java.util.Set value) {
		this.PRODUCT_TB_MASTER1 = value;
	}
	
	public java.util.Set getPRODUCT_TB_MASTER1() {
		return PRODUCT_TB_MASTER1;
	}
	
	
	public void setPROMOTION_TB_MASTER(java.util.Set value) {
		this.PROMOTION_TB_MASTER = value;
	}
	
	public java.util.Set getPROMOTION_TB_MASTER() {
		return PROMOTION_TB_MASTER;
	}
	
	
	public void setPROMOTION_TB_MASTER1(java.util.Set value) {
		this.PROMOTION_TB_MASTER1 = value;
	}
	
	public java.util.Set getPROMOTION_TB_MASTER1() {
		return PROMOTION_TB_MASTER1;
	}
	
	
	public void setSTORE_TB_MASTER(java.util.Set value) {
		this.STORE_TB_MASTER = value;
	}
	
	public java.util.Set getSTORE_TB_MASTER() {
		return STORE_TB_MASTER;
	}
	
	
	public void setUM_TB_MENU(java.util.Set value) {
		this.UM_TB_MENU = value;
	}
	
	public java.util.Set getUM_TB_MENU() {
		return UM_TB_MENU;
	}
	
	
	public void setUM_TB_MENU1(java.util.Set value) {
		this.UM_TB_MENU1 = value;
	}
	
	public java.util.Set getUM_TB_MENU1() {
		return UM_TB_MENU1;
	}
	
	
	public void setUM_TB_ROLE(java.util.Set value) {
		this.UM_TB_ROLE = value;
	}
	
	public java.util.Set getUM_TB_ROLE() {
		return UM_TB_ROLE;
	}
	
	
	public void setUM_TB_ROLE1(java.util.Set value) {
		this.UM_TB_ROLE1 = value;
	}
	
	public java.util.Set getUM_TB_ROLE1() {
		return UM_TB_ROLE1;
	}
	
	
	public String toString() {
		return String.valueOf(getUserId());
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	
}
