package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the ORDER_PRODUCT_TB_DETAIL database table.
 * 
 */
@Entity
@Table(name="ORDER_PRODUCT_TB_DETAIL")
public class OrderProductTbDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="order_product_seq")
	@SequenceGenerator(name="order_product_seq",sequenceName="ORDER_PRODUCT_TB_DETAIL_SEQ")  	
	@Column(name="NM_ORDR_PRD_DTL_ID")
	private long nmOrdrPrdDtlId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_UPDTD_AT")
	private Calendar dtUpdtdAt;

	@Column(name="NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name="NM_CST")
	private BigDecimal nmCst;

	@Column(name="NM_DSCNT")
	private BigDecimal nmDscnt;

	@Column(name="NM_NT_CST")
	private BigDecimal nmNtCst;

	@Column(name="NM_QNTY")
	private BigDecimal nmQnty;

	@Column(name="NM_TTL_CST")
	private BigDecimal nmTtlCst;

	@Column(name="NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	//bi-directional many-to-one association to OrderTbMaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_ORDR_ID")
	private OrderTbMaster orderTbMaster;

	//bi-directional many-to-one association to ProductTbMaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_PRD_ID")
	private ProductTbMaster productTbMaster;

	//bi-directional many-to-one association to PromotionTbMaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_PRMTN_RL_ID")
	private PromotionTbMaster promotionTbMaster;

	public OrderProductTbDetail() {
	}

	public long getNmOrdrPrdDtlId() {
		return this.nmOrdrPrdDtlId;
	}

	public void setNmOrdrPrdDtlId(long nmOrdrPrdDtlId) {
		this.nmOrdrPrdDtlId = nmOrdrPrdDtlId;
	}

	

	public BigDecimal getNmCrtdBy() {
		return this.nmCrtdBy;
	}

	public void setNmCrtdBy(BigDecimal nmCrtdBy) {
		this.nmCrtdBy = nmCrtdBy;
	}

	public BigDecimal getNmCst() {
		return this.nmCst;
	}

	public void setNmCst(BigDecimal nmCst) {
		this.nmCst = nmCst;
	}

	public BigDecimal getNmDscnt() {
		return this.nmDscnt;
	}

	public void setNmDscnt(BigDecimal nmDscnt) {
		this.nmDscnt = nmDscnt;
	}

	public BigDecimal getNmNtCst() {
		return this.nmNtCst;
	}

	public void setNmNtCst(BigDecimal nmNtCst) {
		this.nmNtCst = nmNtCst;
	}

	public BigDecimal getNmQnty() {
		return this.nmQnty;
	}

	public void setNmQnty(BigDecimal nmQnty) {
		this.nmQnty = nmQnty;
	}

	public BigDecimal getNmTtlCst() {
		return this.nmTtlCst;
	}

	public void setNmTtlCst(BigDecimal nmTtlCst) {
		this.nmTtlCst = nmTtlCst;
	}

	public BigDecimal getNmUpdtdBy() {
		return this.nmUpdtdBy;
	}

	public void setNmUpdtdBy(BigDecimal nmUpdtdBy) {
		this.nmUpdtdBy = nmUpdtdBy;
	}

	public OrderTbMaster getOrderTbMaster() {
		return this.orderTbMaster;
	}

	public void setOrderTbMaster(OrderTbMaster orderTbMaster) {
		this.orderTbMaster = orderTbMaster;
	}

	public ProductTbMaster getProductTbMaster() {
		return this.productTbMaster;
	}

	public void setProductTbMaster(ProductTbMaster productTbMaster) {
		this.productTbMaster = productTbMaster;
	}

	public PromotionTbMaster getPromotionTbMaster() {
		return this.promotionTbMaster;
	}

	public void setPromotionTbMaster(PromotionTbMaster promotionTbMaster) {
		this.promotionTbMaster = promotionTbMaster;
	}

	public void setDtCrtdAt(Calendar dtCrtdAt) {
		this.dtCrtdAt = dtCrtdAt;
	}

	public void setDtUpdtdAt(Calendar dtUpdtdAt) {
		this.dtUpdtdAt = dtUpdtdAt;
	}

}