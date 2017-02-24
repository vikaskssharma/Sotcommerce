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
@Table(name="ORDER_PRODUCT_TB_DETAIL")
public class OrderProductDetail implements Serializable {
	public OrderProductDetail() {
	}
	
	@Column(name="NM_ORDR_PRD_DTL_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_ORDERPRODUCTDETAIL_PRODUCTDETAILID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_ORDERPRODUCTDETAIL_PRODUCTDETAILID_GENERATOR", strategy="native")	
	private java.math.BigDecimal productDetailId;
	
	@ManyToOne(targetEntity=Order.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_ORDR_ID", referencedColumnName="NM_ORDR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Order NM_ORDR;
	
	@ManyToOne(targetEntity=Product.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_PRD_ID", referencedColumnName="NM_PRD_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Product NM_PRD;
	
	@Column(name="NM_QNTY", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal prodQuantity;
	
	@Column(name="NM_CST", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal cost;
	
	@Column(name="NM_DSCNT", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal discount;
	
	@Column(name="NM_NT_CST", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal costAfterDiscount;
	
	@Column(name="NM_TTL_CST", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal totalCost;
	
	@ManyToOne(targetEntity=Promotion.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_PRMTN_RL_ID", referencedColumnName="NM_PRMTN_RL_ID") })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Promotion NM_PRMTN_RL;
	
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
	
	public void setProductDetailId(java.math.BigDecimal value) {
		this.productDetailId = value;
	}
	
	public java.math.BigDecimal getProductDetailId() {
		return productDetailId;
	}
	
	public java.math.BigDecimal getORMID() {
		return getProductDetailId();
	}
	
	public void setProdQuantity(java.math.BigDecimal value) {
		this.prodQuantity = value;
	}
	
	public java.math.BigDecimal getProdQuantity() {
		return prodQuantity;
	}
	
	public void setCost(java.math.BigDecimal value) {
		this.cost = value;
	}
	
	public java.math.BigDecimal getCost() {
		return cost;
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
	
	public void setTotalCost(java.math.BigDecimal value) {
		this.totalCost = value;
	}
	
	public java.math.BigDecimal getTotalCost() {
		return totalCost;
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
	
	public void setNM_ORDR(Order value) {
		this.NM_ORDR = value;
	}
	
	public Order getNM_ORDR() {
		return NM_ORDR;
	}
	
	public void setNM_PRD(Product value) {
		this.NM_PRD = value;
	}
	
	public Product getNM_PRD() {
		return NM_PRD;
	}
	
	public void setNM_PRMTN_RL(Promotion value) {
		this.NM_PRMTN_RL = value;
	}
	
	public Promotion getNM_PRMTN_RL() {
		return NM_PRMTN_RL;
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
		return String.valueOf(getProductDetailId());
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
