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
@Table(name="ORDER_TB_MASTER")
public class Order implements Serializable {
	public Order() {
	}
	
	@Column(name="NM_ORDR_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_ORDER_ORDERID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_ORDER_ORDERID_GENERATOR", strategy="native")	
	private java.math.BigDecimal orderID;
	
	@Column(name="NM_ORDR_NMBR", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal orderNumber;
	
	@ManyToOne(targetEntity=Store.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_STR_ID", referencedColumnName="NM_STR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Store NM_STR;
	
	@Column(name="DT_ORDR_DT", nullable=false)	
	private java.sql.Timestamp orderDate;
	
	@ManyToOne(targetEntity=Promotion.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_PRMTN_RL_ID", referencedColumnName="NM_PRMTN_RL_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Promotion NM_PRMTN_RL;
	
	@Column(name="NM_TTL_CST", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal totalCost;
	
	@Column(name="NM_DSCT", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal discount;
	
	@Column(name="NM_TTL_NT_CST", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal costAfterDiscount;
	
	@Column(name="NM_ORDR_STS", nullable=false, length=32)	
	private String orderStatus;
	
	@Column(name="VC_PYMNT_STS", nullable=false, length=32)	
	private String paymentStatus;
	
	@Column(name="VC_SHPMNT_STS", nullable=false, length=32)	
	private String shippingStatus;
	
	@ManyToOne(targetEntity=Payment.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_PYMNT_MD_ID", referencedColumnName="NM_PYMNT_MD_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Payment NM_PYMNT_MD;
	
	@Column(name="VC_PYMNT_GTWY_ID", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal paymentGatewayID;
	
	@Column(name="DT_PYMNT_AT", nullable=false)	
	private java.sql.Timestamp paymentDate;
	
	@Column(name="DT_CRTD_AT", nullable=false)	
	private java.sql.Timestamp createdDate;
	
	@Column(name="DT_UPDTD_AT", nullable=false)	
	private java.sql.Timestamp updatedDate;
	
	@Column(name="NM_CRTD_BY", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal createdBy;
	
	@Column(name="NM_UPDTD_BY", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal updatedBy;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_USR_ID", referencedColumnName="NM_USR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private User NM_USR;
	
	@OneToMany(mappedBy="NM_ORDR", targetEntity=OrderBillingDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_BILLING_TB_DTL = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_ORDR", targetEntity=OrderProductDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_PRODUCT_TB_DETAIL = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_ORDR", targetEntity=OrderShippingDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_SHIPPING_TB_DTL = new java.util.HashSet();
	
	public void setOrderID(java.math.BigDecimal value) {
		this.orderID = value;
	}
	
	public java.math.BigDecimal getOrderID() {
		return orderID;
	}
	
	public java.math.BigDecimal getORMID() {
		return getOrderID();
	}
	
	public void setOrderNumber(java.math.BigDecimal value) {
		this.orderNumber = value;
	}
	
	public java.math.BigDecimal getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderDate(java.sql.Timestamp value) {
		this.orderDate = value;
	}
	
	public java.sql.Timestamp getOrderDate() {
		return orderDate;
	}
	
	public void setTotalCost(java.math.BigDecimal value) {
		this.totalCost = value;
	}
	
	public java.math.BigDecimal getTotalCost() {
		return totalCost;
	}
	
	public void setDiscount(java.math.BigDecimal value) {
		this.discount = value;
	}
	
	public java.math.BigDecimal getDiscount() {
		return discount;
	}
	
	public void setCostAfterDiscount(java.math.BigDecimal value) {
		this.costAfterDiscount = value;
	}
	
	public java.math.BigDecimal getCostAfterDiscount() {
		return costAfterDiscount;
	}
	
	public void setOrderStatus(String value) {
		this.orderStatus = value;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setPaymentStatus(String value) {
		this.paymentStatus = value;
	}
	
	public String getPaymentStatus() {
		return paymentStatus;
	}
	
	public void setShippingStatus(String value) {
		this.shippingStatus = value;
	}
	
	public String getShippingStatus() {
		return shippingStatus;
	}
	
	public void setPaymentGatewayID(java.math.BigDecimal value) {
		this.paymentGatewayID = value;
	}
	
	public java.math.BigDecimal getPaymentGatewayID() {
		return paymentGatewayID;
	}
	
	public void setPaymentDate(java.sql.Timestamp value) {
		this.paymentDate = value;
	}
	
	public java.sql.Timestamp getPaymentDate() {
		return paymentDate;
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
	
	public void setCreatedBy(java.math.BigDecimal value) {
		this.createdBy = value;
	}
	
	public java.math.BigDecimal getCreatedBy() {
		return createdBy;
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
	
	public void setNM_PRMTN_RL(Promotion value) {
		this.NM_PRMTN_RL = value;
	}
	
	public Promotion getNM_PRMTN_RL() {
		return NM_PRMTN_RL;
	}
	
	public void setNM_PYMNT_MD(Payment value) {
		this.NM_PYMNT_MD = value;
	}
	
	public Payment getNM_PYMNT_MD() {
		return NM_PYMNT_MD;
	}
	
	public void setNM_USR(User value) {
		this.NM_USR = value;
	}
	
	public User getNM_USR() {
		return NM_USR;
	}
	
	public void setORDER_BILLING_TB_DTL(java.util.Set value) {
		this.ORDER_BILLING_TB_DTL = value;
	}
	
	public java.util.Set getORDER_BILLING_TB_DTL() {
		return ORDER_BILLING_TB_DTL;
	}
	
	
	public void setORDER_PRODUCT_TB_DETAIL(java.util.Set value) {
		this.ORDER_PRODUCT_TB_DETAIL = value;
	}
	
	public java.util.Set getORDER_PRODUCT_TB_DETAIL() {
		return ORDER_PRODUCT_TB_DETAIL;
	}
	
	
	public void setORDER_SHIPPING_TB_DTL(java.util.Set value) {
		this.ORDER_SHIPPING_TB_DTL = value;
	}
	
	public java.util.Set getORDER_SHIPPING_TB_DTL() {
		return ORDER_SHIPPING_TB_DTL;
	}
	
	
	public String toString() {
		return String.valueOf(getOrderID());
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
