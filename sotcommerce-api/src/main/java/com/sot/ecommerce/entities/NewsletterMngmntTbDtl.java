package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the NEWSLETTER_MNGMNT_TB_DTL database table.
 * 
 */
@Entity
@Table(name="NEWSLETTER_MNGMNT_TB_DTL")
/*@NamedQuery(name="NewsletterMngmntTbDtl.findAll", query="SELECT n FROM NewsletterMngmntTbDtl n")*/
public class NewsletterMngmntTbDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	
	/*@SequenceGenerator(name="NEWSLETTER_MNGMNT_TB_DTL_NMNWSLTTRID_GENERATOR" )*/
	@GeneratedValue(strategy=GenerationType.AUTO, generator="NewsletterMngmntTbDtl_seq")
	@SequenceGenerator(name="NewsletterMngmntTbDtl_seq" ,sequenceName="NEWSLETTER_MNGMNT_TB_DTL_SEQ")
	
	@Column(name="NM_NWS_LTTR_ID")
	private long nmNwsLttrId;

	@Column(name="VC_STTS")
	private String vcStts;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_PBLSH_AT")
	private Date dtPblshAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_UPDTD_AT")
	private Calendar dtUpdtdAt;

	@Column(name="IS_DLTD")
	private BigDecimal isDltd;

	@Column(name="NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name="NM_NWS_LTTR_TTL")
	private String nmNwsLttrTtl;

	@Column(name="NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name="VC_NWS_LTTR_DSCR")
	private String vcNwsLttrDscr;

	@Column(name="VC_NWS_LTTR_PTH")
	private String vcNwsLttrPth;

	//bi-directional many-to-one association to StoreTbMaster
	@ManyToOne
	@JoinColumn(name="NM_STR_ID")
	private Store store;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public NewsletterMngmntTbDtl() {
	}

	public long getNmNwsLttrId() {
		return this.nmNwsLttrId;
	}

	public void setNmNwsLttrId(long nmNwsLttrId) {
		this.nmNwsLttrId = nmNwsLttrId;
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

	public String getNmNwsLttrTtl() {
		return this.nmNwsLttrTtl;
	}

	public void setNmNwsLttrTtl(String nmNwsLttrTtl) {
		this.nmNwsLttrTtl = nmNwsLttrTtl;
	}

	public BigDecimal getNmUpdtdBy() {
		return this.nmUpdtdBy;
	}

	public void setNmUpdtdBy(BigDecimal nmUpdtdBy) {
		this.nmUpdtdBy = nmUpdtdBy;
	}

	public String getVcNwsLttrDscr() {
		return this.vcNwsLttrDscr;
	}

	public void setVcNwsLttrDscr(String vcNwsLttrDscr) {
		this.vcNwsLttrDscr = vcNwsLttrDscr;
	}

	public String getVcNwsLttrPth() {
		return this.vcNwsLttrPth;
	}

	public void setVcNwsLttrPth(String vcNwsLttrPth) {
		this.vcNwsLttrPth = vcNwsLttrPth;
	}

	

	/**
	 * @return the dtCrtdAt
	 */
	public Calendar getDtCrtdAt() {
		return dtCrtdAt;
	}

	/**
	 * @param dtCrtdAt the dtCrtdAt to set
	 */
	public void setDtCrtdAt(Calendar dtCrtdAt) {
		this.dtCrtdAt = dtCrtdAt;
	}

	/**
	 * @return the dtPblshAt
	 */
	public Date getDtPblshAt() {
		return dtPblshAt;
	}

	/**
	 * @param dtPblshAt the dtPblshAt to set
	 */
	public void setDtPblshAt(Date dtPblshAt) {
		this.dtPblshAt = dtPblshAt;
	}

	/**
	 * @return the dtUpdtdAt
	 */
	public Calendar getDtUpdtdAt() {
		return dtUpdtdAt;
	}

	/**
	 * @param dtUpdtdAt the dtUpdtdAt to set
	 */
	public void setDtUpdtdAt(Calendar dtUpdtdAt) {
		this.dtUpdtdAt = dtUpdtdAt;
	}

	public String getVcStts() {
		return vcStts;
	}

	public void setVcStts(String vcStts) {
		this.vcStts = vcStts;
	}

}