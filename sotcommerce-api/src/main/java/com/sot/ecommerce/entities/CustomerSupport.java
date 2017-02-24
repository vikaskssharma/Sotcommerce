package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the CUSTOMER_SUPPORT_TB_DETAIL database table.
 * 
 */
@Entity
@Table(name="CUSTOMER_SUPPORT_TB_DETAIL")
@NamedQuery(name="CustomerSupport.findAll", query="SELECT c FROM CustomerSupport c")
public class CustomerSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="CustomerSupport_SEQ")
	@SequenceGenerator(name="CustomerSupport_SEQ",sequenceName="CUSTOMER_SUPPORT_TB_DETAIL_SEQ")  
	@Column(name="NM_CST_SPPRT_ID")
	private long nmCstSpprtId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRTD_AT")
	private Date dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_UPDTD_AT")
	private Date dtUpdtdAt;

	@Column(name="IS_DLTD")
	private Long isDltd;

	@Column(name="NM_CRTD_BY")
	private Long nmCrtdBy;
	
	@Column(name="NM_USR_ID")
	private Long nmUsrId;

	

	@Column(name="NM_ORDR_NUMBER")
	private String nmOrdrNumber;

	@Column(name="NM_STR_ID")
	private Long nmStrId;

	@Column(name="NM_UPDTD_BY")
	private Long nmUpdtdBy;

	@Column(name="VC_EML")
	private String vcEml;

	@Column(name="VC_FRST_NM")
	private String vcFrstNm;

	@Column(name="VC_LST_NM")
	private String vcLstNm;

	@Column(name="VC_PHN")
	private String vcPhn;

	@Column(name="VC_QSTN")
	private String vcQstn;

	@Column(name="VC_RQST_NMBR")
	private String vcRqstNmbr;

	@Column(name="VC_RSPNS")
	private String vcRspns;

	@Column(name="VC_STTS")
	private String vcStts;

    
	@Column(name="NM_ISS_RSN_ID")
	private long issueReason;

	public Long getNmUsrId() {
		return nmUsrId;
	}

	public void setNmUsrId(Long nmUsrId) {
		this.nmUsrId = nmUsrId;
	}
	
	public long getIssueReason() {
		return issueReason;
	}

	public void setIssueReason(long issueReason) {
		this.issueReason = issueReason;
	}

	public CustomerSupport() {
	}

	public long getNmCstSpprtId() {
		return this.nmCstSpprtId;
	}

	public void setNmCstSpprtId(long nmCstSpprtId) {
		this.nmCstSpprtId = nmCstSpprtId;
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

	public Long getIsDltd() {
		return this.isDltd;
	}

	public void setIsDltd(Long isDltd) {
		this.isDltd = isDltd;
	}

	public Long getNmCrtdBy() {
		return this.nmCrtdBy;
	}

	public void setNmCrtdBy(Long nmCrtdBy) {
		this.nmCrtdBy = nmCrtdBy;
	}

	public String getNmOrdrNumber() {
		return this.nmOrdrNumber;
	}

	public void setNmOrdrNumber(String nmOrdrNumber) {
		this.nmOrdrNumber = nmOrdrNumber;
	}

	public Long getNmStrId() {
		return this.nmStrId;
	}

	public void setNmStrId(Long nmStrId) {
		this.nmStrId = nmStrId;
	}

	public Long getNmUpdtdBy() {
		return this.nmUpdtdBy;
	}

	public void setNmUpdtdBy(Long nmUpdtdBy) {
		this.nmUpdtdBy = nmUpdtdBy;
	}

	public String getVcEml() {
		return this.vcEml;
	}

	public void setVcEml(String vcEml) {
		this.vcEml = vcEml;
	}

	public String getVcFrstNm() {
		return this.vcFrstNm;
	}

	public void setVcFrstNm(String vcFrstNm) {
		this.vcFrstNm = vcFrstNm;
	}

	public String getVcLstNm() {
		return this.vcLstNm;
	}

	public void setVcLstNm(String vcLstNm) {
		this.vcLstNm = vcLstNm;
	}

	public String getVcPhn() {
		return this.vcPhn;
	}

	public void setVcPhn(String vcPhn) {
		this.vcPhn = vcPhn;
	}

	public String getVcQstn() {
		return this.vcQstn;
	}

	public void setVcQstn(String vcQstn) {
		this.vcQstn = vcQstn;
	}

	public String getVcRqstNmbr() {
		return this.vcRqstNmbr;
	}

	public void setVcRqstNmbr(String vcRqstNmbr) {
		this.vcRqstNmbr = vcRqstNmbr;
	}

	public String getVcRspns() {
		return this.vcRspns;
	}

	public void setVcRspns(String vcRspns) {
		this.vcRspns = vcRspns;
	}

	public String getVcStts() {
		return this.vcStts;
	}

	public void setVcStts(String vcStts) {
		this.vcStts = vcStts;
	}

	

}