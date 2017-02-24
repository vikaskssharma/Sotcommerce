package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the ORDER_SHIPPING_TB_DTL database table.
 * 
 */
@Entity
@Table(name="ORDER_SHIPPING_TB_DTL")
public class OrderShippingTbDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="order_shipping_seq")
	@SequenceGenerator(name="order_shipping_seq",sequenceName="ORDER_SHIPPING_TB_DTL_SEQ")  	
	@Column(name="NM_SHP_ID")
	private long nmShpId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_UPDTD_AT")
	private Calendar dtUpdtdAt;

	@Column(name="IS_DLTD")
	private BigDecimal isDltd;

	@Column(name="NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name="NM_SHP_AMT")
	private BigDecimal nmShpAmt;

	@Column(name="NM_SHP_RL_ID")
	private BigDecimal nmShpRlId;

	@Column(name="NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name="VC_CTY")
	private String vcCty;

	@Column(name="VC_PHN")
	private String vcPhn;

	@Column(name="VC_SHP_ADDR")
	private String vcShpAddr;

	@Column(name="VC_SHP_FRST_NM")
	private String vcShpFrstNm;

	@Column(name="VC_SHP_LST_NM")
	private String vcShpLstNm;

	@Column(name="VC_ST")
	private String vcSt;

	@Column(name="VC_ZPCD")
	private String vcZpcd;

	//bi-directional many-to-one association to OrderTbMaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_ORDR_ID")
	private OrderTbMaster orderTbMaster;

	//bi-directional many-to-one association to UmTbCntryMstr
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_CNTRY_ID")
	private UmTbCntryMstr umTbCntryMstr;

	public OrderShippingTbDtl() {
	}

	public long getNmShpId() {
		return this.nmShpId;
	}

	public void setNmShpId(long nmShpId) {
		this.nmShpId = nmShpId;
	}

	

	public BigDecimal getIsDltd() {
		return this.isDltd;
	}

	public void setIsDltd(BigDecimal isDltd) {
		this.isDltd = isDltd;
	}

	public BigDecimal getNmCrtdBy() {
		return this.nmCrtdBy;
	}

	public void setNmCrtdBy(BigDecimal nmCrtdBy) {
		this.nmCrtdBy = nmCrtdBy;
	}

	public BigDecimal getNmShpAmt() {
		return this.nmShpAmt;
	}

	public void setNmShpAmt(BigDecimal nmShpAmt) {
		this.nmShpAmt = nmShpAmt;
	}

	public BigDecimal getNmShpRlId() {
		return this.nmShpRlId;
	}

	public void setNmShpRlId(BigDecimal nmShpRlId) {
		this.nmShpRlId = nmShpRlId;
	}

	public BigDecimal getNmUpdtdBy() {
		return this.nmUpdtdBy;
	}

	public void setNmUpdtdBy(BigDecimal nmUpdtdBy) {
		this.nmUpdtdBy = nmUpdtdBy;
	}

	public String getVcCty() {
		return this.vcCty;
	}

	public void setVcCty(String vcCty) {
		this.vcCty = vcCty;
	}

	public String getVcPhn() {
		return this.vcPhn;
	}

	public void setVcPhn(String vcPhn) {
		this.vcPhn = vcPhn;
	}

	public String getVcShpAddr() {
		return this.vcShpAddr;
	}

	public void setVcShpAddr(String vcShpAddr) {
		this.vcShpAddr = vcShpAddr;
	}

	public String getVcShpFrstNm() {
		return this.vcShpFrstNm;
	}

	public void setVcShpFrstNm(String vcShpFrstNm) {
		this.vcShpFrstNm = vcShpFrstNm;
	}

	public String getVcShpLstNm() {
		return this.vcShpLstNm;
	}

	public void setVcShpLstNm(String vcShpLstNm) {
		this.vcShpLstNm = vcShpLstNm;
	}

	public String getVcSt() {
		return this.vcSt;
	}

	public void setVcSt(String vcSt) {
		this.vcSt = vcSt;
	}

	public String getVcZpcd() {
		return this.vcZpcd;
	}

	public void setVcZpcd(String vcZpcd) {
		this.vcZpcd = vcZpcd;
	}

	public OrderTbMaster getOrderTbMaster() {
		return this.orderTbMaster;
	}

	public void setOrderTbMaster(OrderTbMaster orderTbMaster) {
		this.orderTbMaster = orderTbMaster;
	}

	public UmTbCntryMstr getUmTbCntryMstr() {
		return this.umTbCntryMstr;
	}

	public void setUmTbCntryMstr(UmTbCntryMstr umTbCntryMstr) {
		this.umTbCntryMstr = umTbCntryMstr;
	}

	public void setDtCrtdAt(Calendar dtCrtdAt) {
		this.dtCrtdAt = dtCrtdAt;
	}

	public void setDtUpdtdAt(Calendar dtUpdtdAt) {
		this.dtUpdtdAt = dtUpdtdAt;
	}

}