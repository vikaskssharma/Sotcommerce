package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the ORDER_BILLING_TB_DTL database table.
 * 
 */
@Entity
@Table(name="ORDER_BILLING_TB_DTL")
public class OrderBillingTbDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="order_billing_seq")
	@SequenceGenerator(name="order_billing_seq",sequenceName="ORDER_BILLING_TB_DTL_SEQ")  	
	@Column(name="NM_BLNG_ID")
	private long nmBlngId;

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

	@Column(name="NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name="VC_BLNG_ADDR")
	private String vcBlngAddr;

	@Column(name="VC_BLNG_FRST_NM")
	private String vcBlngFrstNm;

	@Column(name="VC_BLNG_LST_NM")
	private String vcBlngLstNm;

	@Column(name="VC_CTY")
	private String vcCty;

	@Column(name="VC_PHN")
	private String vcPhn;

	@Column(name="VC_ST")
	private String vcSt;

	@Column(name="VC_ZPCD")
	private String vcZpcd;

	//bi-directional many-to-one association to OrderTbMaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_ORDR_ID")
	private Order order;

	//bi-directional many-to-one association to UmTbCntryMstr
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_CNTRY_ID")
	private UmTbCntryMstr umTbCntryMstr;

	public OrderBillingTbDtl() {
	}

	public long getNmBlngId() {
		return this.nmBlngId;
	}

	public void setNmBlngId(long nmBlngId) {
		this.nmBlngId = nmBlngId;
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

	public BigDecimal getNmUpdtdBy() {
		return this.nmUpdtdBy;
	}

	public void setNmUpdtdBy(BigDecimal nmUpdtdBy) {
		this.nmUpdtdBy = nmUpdtdBy;
	}

	public String getVcBlngAddr() {
		return this.vcBlngAddr;
	}

	public void setVcBlngAddr(String vcBlngAddr) {
		this.vcBlngAddr = vcBlngAddr;
	}

	public String getVcBlngFrstNm() {
		return this.vcBlngFrstNm;
	}

	public void setVcBlngFrstNm(String vcBlngFrstNm) {
		this.vcBlngFrstNm = vcBlngFrstNm;
	}

	public String getVcBlngLstNm() {
		return this.vcBlngLstNm;
	}

	public void setVcBlngLstNm(String vcBlngLstNm) {
		this.vcBlngLstNm = vcBlngLstNm;
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

	

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Calendar getDtCrtdAt() {
		return dtCrtdAt;
	}

	public Calendar getDtUpdtdAt() {
		return dtUpdtdAt;
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