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
 * The persistent class for the REVIEW_TB_DETAIL database table.
 * 
 * @author gaurav.kumar
 */
@Entity
@Table(name = "REVIEW_N_RATING_TB_DETAIL")
public class ReviewNRatingTbDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "review_n_rating_seq")
	@SequenceGenerator(name = "review_n_rating_seq", sequenceName = "REVIEW_N_RATING_TB_DETAIL_SEQ")
	@Column(name = "NM_RVW_ID")
	private long nmRvwId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_UPDTD_AT")
	private Calendar dtUpdtdAt;

	@Column(name = "VC_STTS")
	private String vcStts;

	@Column(name = "NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name = "NM_STR_ID")
	private BigDecimal nmStrId;

	@Column(name = "NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name = "NM_USR_ID")
	private BigDecimal nmUsrId;

	@Column(name = "VC_RVW_DSCPTN")
	private String vcRvwDscptn;

	@Column(name = "NM_RTNG")
	private BigDecimal nmRtng;
	
	@Column(name="VC_USR_NM")
	private String vcUsrNm;

	// bi-directional many-to-one association to ProductTbMaster
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NM_PRD_ID")
	private ProductTbMaster productTbMaster;

	// bi-directional many-to-one association to OrderTbMaster
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NM_ORDR_ID")
	private OrderTbMaster orderTbMaster;

	public ReviewNRatingTbDetail() {
	}

	public long getNmRvwId() {
		return this.nmRvwId;
	}

	public void setNmRvwId(long nmRvwId) {
		this.nmRvwId = nmRvwId;
	}

	public BigDecimal getNmCrtdBy() {
		return this.nmCrtdBy;
	}

	public void setNmCrtdBy(BigDecimal nmCrtdBy) {
		this.nmCrtdBy = nmCrtdBy;
	}

	public BigDecimal getNmStrId() {
		return this.nmStrId;
	}

	public void setNmStrId(BigDecimal nmStrId) {
		this.nmStrId = nmStrId;
	}

	public BigDecimal getNmUpdtdBy() {
		return this.nmUpdtdBy;
	}

	public void setNmUpdtdBy(BigDecimal nmUpdtdBy) {
		this.nmUpdtdBy = nmUpdtdBy;
	}

	public BigDecimal getNmUsrId() {
		return this.nmUsrId;
	}

	public void setNmUsrId(BigDecimal nmUsrId) {
		this.nmUsrId = nmUsrId;
	}

	public String getVcRvwDscptn() {
		return this.vcRvwDscptn;
	}

	public void setVcRvwDscptn(String vcRvwDscptn) {
		this.vcRvwDscptn = vcRvwDscptn;
	}

	public OrderTbMaster getOrderTbMaster() {
		return this.orderTbMaster;
	}

	public void setOrderTbMaster(OrderTbMaster orderTbMaster) {
		this.orderTbMaster = orderTbMaster;
	}

	public Calendar getDtCrtdAt() {
		return dtCrtdAt;
	}

	public void setDtCrtdAt(Calendar dtCrtdAt) {
		this.dtCrtdAt = dtCrtdAt;
	}

	public ProductTbMaster getProductTbMaster() {
		return productTbMaster;
	}

	public void setProductTbMaster(ProductTbMaster productTbMaster) {
		this.productTbMaster = productTbMaster;
	}

	public Calendar getDtUpdtdAt() {
		return dtUpdtdAt;
	}

	public void setDtUpdtdAt(Calendar dtUpdtdAt) {
		this.dtUpdtdAt = dtUpdtdAt;
	}

	/**
	 * @return the vcStts
	 */
	public String getVcStts() {
		return vcStts;
	}

	/**
	 * @param vcStts
	 *            the vcStts to set
	 */
	public void setVcStts(String vcStts) {
		this.vcStts = vcStts;
	}

	public BigDecimal getNmRtng() {
		return this.nmRtng;
	}

	public void setNmRtng(BigDecimal nmRtng) {
		this.nmRtng = nmRtng;
	}

	/**
	 * @return the vcUsrNm
	 */
	public String getVcUsrNm() {
		return vcUsrNm;
	}

	/**
	 * @param vcUsrNm the vcUsrNm to set
	 */
	public void setVcUsrNm(String vcUsrNm) {
		this.vcUsrNm = vcUsrNm;
	}

}