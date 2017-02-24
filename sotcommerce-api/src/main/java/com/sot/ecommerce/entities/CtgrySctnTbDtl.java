package com.sot.ecommerce.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the CTGRY_SCTN_TB_DTL database table.
 * 
 */
@Entity
@Table(name = "CTGRY_SCTN_TB_DTL")
public class CtgrySctnTbDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="catsec_seq")
    @SequenceGenerator(name="catsec_seq",sequenceName="CTGRY_SCTN_TB_DTL_SEQ")  
	@Column(name = "NM_SCTN_ID")
	private long nmSctnId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_UPDTD_AT")
	private Calendar dtUpdtdAt;

	@Column(name = "IS_DLTD")
	private BigDecimal isDltd;

	@Column(name = "NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name = "NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name = "VC_SCTN_NM")
	private String vcSctnNm;

	// bi-directional many-to-one association to CtgrySctnAttrTbDtl
	@OneToMany(mappedBy = "ctgrySctnTbDtl", cascade = { CascadeType.MERGE,
			CascadeType.REMOVE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	private List<CtgrySctnAttrTbDtl> ctgrySctnAttrTbDtls;

	// bi-directional many-to-one association to CategoryTbMaster
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NM_CTGRY_ID")
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CtgrySctnTbDtl() {
	}

	public long getNmSctnId() {
		return this.nmSctnId;
	}

	public void setNmSctnId(long nmSctnId) {
		this.nmSctnId = nmSctnId;
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

	public String getVcSctnNm() {
		return this.vcSctnNm;
	}

	public void setVcSctnNm(String vcSctnNm) {
		this.vcSctnNm = vcSctnNm;
	}

	public List<CtgrySctnAttrTbDtl> getCtgrySctnAttrTbDtls() {
		return this.ctgrySctnAttrTbDtls;
	}

	public void setCtgrySctnAttrTbDtls(
			List<CtgrySctnAttrTbDtl> ctgrySctnAttrTbDtls) {
		this.ctgrySctnAttrTbDtls = ctgrySctnAttrTbDtls;
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
	
	

}