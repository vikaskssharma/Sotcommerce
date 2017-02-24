package com.sot.ecommerce.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * The persistent class for the CTGRY_SCTN_ATTR_TB_DTL database table.
 * 
 */
@Entity
@Table(name = "CTGRY_SCTN_ATTR_TB_DTL")
public class CtgrySctnAttrTbDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "catsecatt_seq")
	@SequenceGenerator(name = "catsecatt_seq", sequenceName = "CTGRY_SCTN_ATTR_TB_DTL_SEQ")
	@Column(name = "NM_ATTR_ID")
	private long nmAttrId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_UPDTD_AT")
	private Calendar dtUpdtdAt;
	
	@Column(name="IS_DCML")
	private BigDecimal isDcml;

	@Column(name = "IS_DLTD")
	private BigDecimal isDltd;

	@Column(name = "IS_IMGBL")
	private BigDecimal isImgbl;

	@Column(name = "IS_MNDTRY")
	private BigDecimal isMndtry;

	@Column(name = "IS_RNGSPPRT")
	private BigDecimal isRngspprt;

	@Column(name = "IS_SRCHBL")
	private BigDecimal isSrchbl;

	@Column(name = "IS_VRNT")
	private BigDecimal isVrnt;

	@Column(name = "NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name = "NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name = "VC_ATTR_MPPNG")
	private String vcAttrMppng;

	@Column(name = "VC_ATTR_NM")
	private String vcAttrNm;

	@Column(name = "VC_UNT_TYP")
	private String vcUntTyp;

	@Column(name = "VC_UNT_TYP_MPPNG")
	private String vcUntTypMppng;
	
	@Column(name="VC_UNT_VL")
	private String vcUntVl;

	// bi-directional many-to-one association to CategoryTbMaster
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NM_CTGRY_ID")
	private Category category;

	// bi-directional many-to-one association to CtgrySctnTbDtl
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NM_SCTN_ID")
	private CtgrySctnTbDtl ctgrySctnTbDtl;

	public CtgrySctnAttrTbDtl() {
	}

	public long getNmAttrId() {
		return this.nmAttrId;
	}

	public void setNmAttrId(long nmAttrId) {
		this.nmAttrId = nmAttrId;
	}

	public BigDecimal getIsDltd() {
		return this.isDltd;
	}

	public void setIsDltd(BigDecimal isDltd) {
		this.isDltd = isDltd;
	}

	public BigDecimal getIsImgbl() {
		return this.isImgbl;
	}

	public void setIsImgbl(BigDecimal isImgbl) {
		this.isImgbl = isImgbl;
	}

	public BigDecimal getIsMndtry() {
		return this.isMndtry;
	}

	public void setIsMndtry(BigDecimal isMndtry) {
		this.isMndtry = isMndtry;
	}

	public BigDecimal getIsRngSpprt() {
		return this.isRngspprt;
	}

	public void setIsRngSpprt(BigDecimal isRngspprt) {
		this.isRngspprt = isRngspprt;
	}

	public BigDecimal getIsSrchbl() {
		return this.isSrchbl;
	}

	public void setIsSrchbl(BigDecimal isSrchbl) {
		this.isSrchbl = isSrchbl;
	}

	public BigDecimal getIsVrnt() {
		return this.isVrnt;
	}

	public void setIsVrnt(BigDecimal isVrnt) {
		this.isVrnt = isVrnt;
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

	public String getVcAttrMppng() {
		return this.vcAttrMppng;
	}

	public void setVcAttrMppng(String vcAttrMppng) {
		this.vcAttrMppng = vcAttrMppng;
	}

	public String getVcAttrNm() {
		return this.vcAttrNm;
	}

	public void setVcAttrNm(String vcAttrNm) {
		this.vcAttrNm = vcAttrNm;
	}

	public String getVcUntTyp() {
		return this.vcUntTyp;
	}

	public void setVcUntTyp(String vcUntTyp) {
		this.vcUntTyp = vcUntTyp;
	}

	public String getVcUntTypMppng() {
		return this.vcUntTypMppng;
	}

	public void setVcUntTypMppng(String vcUntTypMppng) {
		this.vcUntTypMppng = vcUntTypMppng;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategoryTbMaster(Category category) {
		this.category = category;
	}

	public CtgrySctnTbDtl getCtgrySctnTbDtl() {
		return this.ctgrySctnTbDtl;
	}

	public void setCtgrySctnTbDtl(CtgrySctnTbDtl ctgrySctnTbDtl) {
		this.ctgrySctnTbDtl = ctgrySctnTbDtl;
	}

	/**
	 * @return the dtCrtdAt
	 */
	public Calendar getDtCrtdAt() {
		return dtCrtdAt;
	}

	/**
	 * @param dtCrtdAt
	 *            the dtCrtdAt to set
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
	 * @param dtUpdtdAt
	 *            the dtUpdtdAt to set
	 */
	public void setDtUpdtdAt(Calendar dtUpdtdAt) {
		this.dtUpdtdAt = dtUpdtdAt;
	}
	
	public String getVcUntVl() {
		return this.vcUntVl;
	}

	public void setVcUntVl(String vcUntVl) {
		this.vcUntVl = vcUntVl;
	}

}