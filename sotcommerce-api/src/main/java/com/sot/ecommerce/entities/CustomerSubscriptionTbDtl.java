package com.sot.ecommerce.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the CUSTOMER_SUBSCRIPTION_TB_DTL database table.
 * 
 */
@Entity
@Table(name="CUSTOMER_SUBSCRIPTION_TB_DTL")
/*@NamedQuery(name="CustomerSubscriptionTbDtl.findAll", query="SELECT c FROM CustomerSubscription c")*/
public class CustomerSubscriptionTbDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id    
	@GeneratedValue(strategy=GenerationType.AUTO, generator="CustomerSubscriptionTbDtl_seq")
	@SequenceGenerator(name="CustomerSubscriptionTbDtl_seq" ,sequenceName="CUSTOMER_SBSCRIPTN_TB_DTL_SEQ")
	@Column(name="NM_CST_SBSCRPTN_ID")
	private long nmCstSbscrptnId;

	@Column(name="VC_EML")
	private String vcEml;
	
	@Column(name="VC_STTS")
	private String vcStts;
	
	@Column(name="IS_DLTD")
	private BigDecimal isDltd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRTD_AT")
	private Calendar dtCrtdAt;
	
	@Column(name="VC_UNSBSCRPTN_RSN")
	private String vcUnsbscrptnRsn;
	

	//bi-directional many-to-one association to StoreTbMaster
	@ManyToOne
	@JoinColumn(name="NM_STR_ID")
	private StoreTbMaster storeTbMaster;

	public CustomerSubscriptionTbDtl() {
	}

	public long getNmCstSbscrptnId() {
		return this.nmCstSbscrptnId;
	}

	public void setNmCstSbscrptnId(long nmCstSbscrptnId) {
		this.nmCstSbscrptnId = nmCstSbscrptnId;
	}

	public String getVcEml() {
		return this.vcEml;
	}

	public void setVcEml(String vcEml) {
		this.vcEml = vcEml;
	}

	public StoreTbMaster getStoreTbMaster() {
		return this.storeTbMaster;
	}

	public void setStoreTbMaster(StoreTbMaster storeTbMaster) {
		this.storeTbMaster = storeTbMaster;
	}

	public BigDecimal getIsDltd() {
		return isDltd;
	}

	public void setIsDltd(BigDecimal isDltd) {
		this.isDltd = isDltd;
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

	public String getVcStts() {
		return vcStts;
	}

	public void setVcStts(String vcStts) {
		this.vcStts = vcStts;
	}

	public String getVcUnsbscrptnRsn() {
		return vcUnsbscrptnRsn;
	}

	public void setVcUnsbscrptnRsn(String vcUnsbscrptnRsn) {
		this.vcUnsbscrptnRsn = vcUnsbscrptnRsn;
	}


}