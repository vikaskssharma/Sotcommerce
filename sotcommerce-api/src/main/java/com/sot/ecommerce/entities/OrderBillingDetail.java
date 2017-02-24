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
@Table(name="ORDER_BILLING_TB_DTL")
public class OrderBillingDetail implements Serializable {
	public OrderBillingDetail() {
	}
	
	@Column(name="NM_BLNG_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_ORDERBILLINGDETAIL_BILINGID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_ORDERBILLINGDETAIL_BILINGID_GENERATOR", strategy="native")	
	private java.math.BigDecimal bilingId;
	
	@ManyToOne(targetEntity=Order.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_ORDR_ID", referencedColumnName="NM_ORDR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Order NM_ORDR;
	
	@Column(name="VC_BLNG_ADDR", nullable=false, length=128)	
	private String address;
	
	@Column(name="VC_CTY", nullable=false, length=32)	
	private String city;
	
	@Column(name="VC_ST", nullable=false, length=16)	
	private String state;
	
	@Column(name="VC_PHN", nullable=false, length=16)	
	private String phone;
	
	@Column(name="VC_ZPCD", nullable=false, length=16)	
	private String zip;
	
	@ManyToOne(targetEntity=Country.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_CNTRY_ID", referencedColumnName="NM_CNTRY_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Country NM_CNTRY;
	
	@Column(name="VC_BLNG_FRST_NM", nullable=false, length=32)	
	private String firstName;
	
	@Column(name="VC_BLNG_LST_NM", nullable=false, length=32)	
	private String lastName;
	
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
	
	public void setBilingId(java.math.BigDecimal value) {
		this.bilingId = value;
	}
	
	public java.math.BigDecimal getBilingId() {
		return bilingId;
	}
	
	public java.math.BigDecimal getORMID() {
		return getBilingId();
	}
	
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setCity(String value) {
		this.city = value;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setState(String value) {
		this.state = value;
	}
	
	public String getState() {
		return state;
	}
	
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setZip(String value) {
		this.zip = value;
	}
	
	public String getZip() {
		return zip;
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
	
	public void setNM_ORDR(Order value) {
		this.NM_ORDR = value;
	}
	
	public Order getNM_ORDR() {
		return NM_ORDR;
	}
	
	public void setNM_CNTRY(Country value) {
		this.NM_CNTRY = value;
	}
	
	public Country getNM_CNTRY() {
		return NM_CNTRY;
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
	
	public String toString() {
		return String.valueOf(getBilingId());
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
