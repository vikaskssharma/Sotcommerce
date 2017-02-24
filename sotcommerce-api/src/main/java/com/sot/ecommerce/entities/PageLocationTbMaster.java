package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PAGE_LOCATION_TB_MASTER database table.
 * 
 */
@Entity
@Table(name="PAGE_LOCATION_TB_MASTER")
public class PageLocationTbMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="page_location_seq")
	@SequenceGenerator(name="page_location_seq",sequenceName="PAGE_LOCATION_TB_MASTER_SEQ") 
	@Column(name="NM_PG_LCTN_ID")
	private long nmPgLctnId;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_CRTD_AT")
	private Date dtCrtdAt;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_UPDTD_AT")
	private Date dtUpdtdAt;

	@Column(name="IS_DLTD")
	private BigDecimal isDltd;

	@Column(name="NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name="NM_PG_LCTN")
	private BigDecimal nmPgLctn;

	@Column(name="NM_PG_NMBR")
	private BigDecimal nmPgNmbr;

	@Column(name="NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name="VC_PG_URL")
	private String vcPgUrl;

	public PageLocationTbMaster() {
	}

	public long getNmPgLctnId() {
		return this.nmPgLctnId;
	}

	public void setNmPgLctnId(long nmPgLctnId) {
		this.nmPgLctnId = nmPgLctnId;
	}

	public Date getDtCrtdAt() {
		return this.dtCrtdAt;
	}

	public void setDtCrtdAt(Date dtCrtdAt) {
		this.dtCrtdAt = dtCrtdAt;
	}

	public Date getDtUpdtdAt() {
		return this.dtUpdtdAt;
	}

	public void setDtUpdtdAt(Date dtUpdtdAt) {
		this.dtUpdtdAt = dtUpdtdAt;
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

	public BigDecimal getNmPgLctn() {
		return this.nmPgLctn;
	}

	public void setNmPgLctn(BigDecimal nmPgLctn) {
		this.nmPgLctn = nmPgLctn;
	}

	public BigDecimal getNmPgNmbr() {
		return this.nmPgNmbr;
	}

	public void setNmPgNmbr(BigDecimal nmPgNmbr) {
		this.nmPgNmbr = nmPgNmbr;
	}

	public BigDecimal getNmUpdtdBy() {
		return this.nmUpdtdBy;
	}

	public void setNmUpdtdBy(BigDecimal nmUpdtdBy) {
		this.nmUpdtdBy = nmUpdtdBy;
	}

	public String getVcPgUrl() {
		return this.vcPgUrl;
	}

	public void setVcPgUrl(String vcPgUrl) {
		this.vcPgUrl = vcPgUrl;
	}

}