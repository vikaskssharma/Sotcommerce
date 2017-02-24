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
@Table(name="PROMOTION_TB_MASTER")
public class Promotion implements Serializable {
	public Promotion() {
	}
	
	@Column(name="NM_PRMTN_RL_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_PROMOTION_PROMOTIONID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_PROMOTION_PROMOTIONID_GENERATOR", strategy="native")	
	private java.math.BigDecimal promotionID;
	
	@ManyToOne(targetEntity=Store.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_STR_ID", referencedColumnName="NM_STR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Store NM_STR;
	
	@Column(name="VC_RL_NM", nullable=false, length=32)	
	private String promotionName;
	
	@Column(name="VC_RL_CD", nullable=false, length=16)	
	private String code;
	
	@Column(name="VC_RL_DSCR", nullable=true, length=128)	
	private String desc;
	
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_CTGRY_ID", referencedColumnName="NM_CTGRY_ID") })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Category NM_CTGRY;
	
	@Column(name="VC_RL_CD_TYP", nullable=false, length=128)	
	private String codeType;
	
	@Column(name="NM_LMT_PR_ORDR", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal limitPerOrder;
	
	@Column(name="NM_LMT_PR_PRMTN", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal limitPerPromotion;
	
	@Column(name="NM_LMT_PR_CSTMR", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal limitPerCustomer;
	
	@Column(name="DT_PRMTN_FRM", nullable=false)	
	private java.sql.Timestamp promotionStartDate;
	
	@Column(name="DT_PRMTN_TO", nullable=false)	
	private java.sql.Timestamp promotionEndDate;
	
	@Column(name="VC_DSCNT_TYP", nullable=true, length=32)	
	private String discountType;
	
	@Column(name="NM_DSCNT_VL", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal discountValue;
	
	@Column(name="VC_PRMTN_SCP", nullable=true, length=64)	
	private String scp;
	
	@Column(name="NM_ORDR_OVR", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal orderOver;
	
	@ManyToOne(targetEntity=Product.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_PRD_ID", referencedColumnName="NM_PRD_ID") })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Product NM_PRD;
	
	@Column(name="IS_ACT", nullable=false, length=1)	
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
	
	@OneToMany(mappedBy="NM_PRMTN_RL", targetEntity=OrderProductDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_PRODUCT_TB_DETAIL = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_PRMTN_RL", targetEntity=Order.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_TB_MASTER = new java.util.HashSet();
	
	public void setPromotionID(java.math.BigDecimal value) {
		this.promotionID = value;
	}
	
	public java.math.BigDecimal getPromotionID() {
		return promotionID;
	}
	
	public java.math.BigDecimal getORMID() {
		return getPromotionID();
	}
	
	public void setPromotionName(String value) {
		this.promotionName = value;
	}
	
	public String getPromotionName() {
		return promotionName;
	}
	
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setDesc(String value) {
		this.desc = value;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setCodeType(String value) {
		this.codeType = value;
	}
	
	public String getCodeType() {
		return codeType;
	}
	
	public void setLimitPerOrder(java.math.BigDecimal value) {
		this.limitPerOrder = value;
	}
	
	public java.math.BigDecimal getLimitPerOrder() {
		return limitPerOrder;
	}
	
	public void setLimitPerPromotion(java.math.BigDecimal value) {
		this.limitPerPromotion = value;
	}
	
	public java.math.BigDecimal getLimitPerPromotion() {
		return limitPerPromotion;
	}
	
	public void setLimitPerCustomer(java.math.BigDecimal value) {
		this.limitPerCustomer = value;
	}
	
	public java.math.BigDecimal getLimitPerCustomer() {
		return limitPerCustomer;
	}
	
	public void setPromotionStartDate(java.sql.Timestamp value) {
		this.promotionStartDate = value;
	}
	
	public java.sql.Timestamp getPromotionStartDate() {
		return promotionStartDate;
	}
	
	public void setPromotionEndDate(java.sql.Timestamp value) {
		this.promotionEndDate = value;
	}
	
	public java.sql.Timestamp getPromotionEndDate() {
		return promotionEndDate;
	}
	
	public void setDiscountType(String value) {
		this.discountType = value;
	}
	
	public String getDiscountType() {
		return discountType;
	}
	
	public void setDiscountValue(java.math.BigDecimal value) {
		this.discountValue = value;
	}
	
	public java.math.BigDecimal getDiscountValue() {
		return discountValue;
	}
	
	public void setScp(String value) {
		this.scp = value;
	}
	
	public String getScp() {
		return scp;
	}
	
	public void setOrderOver(java.math.BigDecimal value) {
		this.orderOver = value;
	}
	
	public java.math.BigDecimal getOrderOver() {
		return orderOver;
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
	
	public void setNM_STR(Store value) {
		this.NM_STR = value;
	}
	
	public Store getNM_STR() {
		return NM_STR;
	}
	
	public void setNM_CTGRY(Category value) {
		this.NM_CTGRY = value;
	}
	
	public Category getNM_CTGRY() {
		return NM_CTGRY;
	}
	
	public void setNM_PRD(Product value) {
		this.NM_PRD = value;
	}
	
	public Product getNM_PRD() {
		return NM_PRD;
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
	
	public void setORDER_PRODUCT_TB_DETAIL(java.util.Set value) {
		this.ORDER_PRODUCT_TB_DETAIL = value;
	}
	
	public java.util.Set getORDER_PRODUCT_TB_DETAIL() {
		return ORDER_PRODUCT_TB_DETAIL;
	}
	
	
	public void setORDER_TB_MASTER(java.util.Set value) {
		this.ORDER_TB_MASTER = value;
	}
	
	public java.util.Set getORDER_TB_MASTER() {
		return ORDER_TB_MASTER;
	}
	
	
	public String toString() {
		return String.valueOf(getPromotionID());
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
