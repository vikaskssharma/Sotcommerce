package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


/**
 * The persistent class for the BANNER_LINK_DETAIL database table.
 * 
 */
@Entity
@Table(name="BANNER_LINK_DETAIL")
public class BannerLinkDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="banner_link_seq")
	@SequenceGenerator(name="banner_link_seq",sequenceName="BANNER_LINK_DETAIL_SEQ")
	@Column(name="NM_BNR_LNK_ID")
	private long nmBnrLnkId;

	@Column(name="BNNR_LNK_CTGRY_ID")
	private BigDecimal bnnrLnkCtgryId;

	@Column(name="BNNR_LNK_PRD_ID")
	private BigDecimal bnnrLnkPrdId;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_UPDTD_AT")
	private Calendar dtUpdtdAt;

	@Column(name="IS_DLTD")
	private BigDecimal isDltd;

	@Column(name="NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name="NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name="VC_BNR_IMG_PATH")
	private String vcBnrImgPath;

	//bi-directional many-to-one association to BannerTbMaster
	@ManyToOne
	@JoinColumn(name="NM_BNR_ID")
	private Banner banner;

	public BannerLinkDetail() {
	}
	
	/**
	 * @param nmBnrLnkId
	 * @param bnnrLnkCtgryId
	 * @param bnnrLnkPrdId
	 * @param dtCrtdAt
	 * @param dtUpdtdAt
	 * @param isDltd
	 * @param nmCrtdBy
	 * @param nmUpdtdBy
	 * @param vcBnrImgPath
	 * @param banner
	 */
	public BannerLinkDetail(BigDecimal bnnrLnkCtgryId,
			BigDecimal bnnrLnkPrdId, Calendar dtCrtdAt, Calendar dtUpdtdAt,
			BigDecimal isDltd, BigDecimal nmCrtdBy, BigDecimal nmUpdtdBy,
			String vcBnrImgPath, Banner banner) {
		super();
		this.bnnrLnkCtgryId = bnnrLnkCtgryId;
		this.bnnrLnkPrdId = bnnrLnkPrdId;
		this.dtCrtdAt = dtCrtdAt;
		this.dtUpdtdAt = dtUpdtdAt;
		this.isDltd = isDltd;
		this.nmCrtdBy = nmCrtdBy;
		this.nmUpdtdBy = nmUpdtdBy;
		this.vcBnrImgPath = vcBnrImgPath;
		this.banner = banner;
	}

	

/**
	 * @param bnnrLnkCtgryId
	 * @param bnnrLnkPrdId
	 * @param dtUpdtdAt
	 * @param isDltd
	 * @param nmUpdtdBy
	 * @param vcBnrImgPath
	 * @param banner
	 */
	public BannerLinkDetail(BigDecimal bnnrLnkCtgryId, BigDecimal bnnrLnkPrdId,
			Calendar dtUpdtdAt, BigDecimal isDltd, BigDecimal nmUpdtdBy,
			String vcBnrImgPath, Banner banner) {
		super();
		this.bnnrLnkCtgryId = bnnrLnkCtgryId;
		this.bnnrLnkPrdId = bnnrLnkPrdId;
		this.dtUpdtdAt = dtUpdtdAt;
		this.isDltd = isDltd;
		this.nmUpdtdBy = nmUpdtdBy;
		this.vcBnrImgPath = vcBnrImgPath;
		this.banner = banner;
	}

/*	*//**
	 * @param bnnrLnkCtgryId
	 * @param bnnrLnkPrdId
	 * @param dtUpdtdAt
	 * @param nmUpdtdBy
	 * @param vcBnrImgPath
	 *//*
	public BannerLinkDetail(BigDecimal bnnrLnkCtgryId, BigDecimal bnnrLnkPrdId,
			Calendar dtUpdtdAt, BigDecimal nmUpdtdBy, String vcBnrImgPath) {
		super();
		this.bnnrLnkCtgryId = bnnrLnkCtgryId;
		this.bnnrLnkPrdId = bnnrLnkPrdId;
		this.dtUpdtdAt = dtUpdtdAt;
		this.nmUpdtdBy = nmUpdtdBy;
		this.vcBnrImgPath = vcBnrImgPath;
	}
*/
	public long getNmBnrLnkId() {
		return this.nmBnrLnkId;
	}

	public void setNmBnrLnkId(long nmBnrLnkId) {
		this.nmBnrLnkId = nmBnrLnkId;
	}

	public BigDecimal getBnnrLnkCtgryId() {
		return this.bnnrLnkCtgryId;
	}

	public void setBnnrLnkCtgryId(BigDecimal bnnrLnkCtgryId) {
		this.bnnrLnkCtgryId = bnnrLnkCtgryId;
	}

	public BigDecimal getBnnrLnkPrdId() {
		return this.bnnrLnkPrdId;
	}

	public void setBnnrLnkPrdId(BigDecimal bnnrLnkPrdId) {
		this.bnnrLnkPrdId = bnnrLnkPrdId;
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

	public String getVcBnrImgPath() {
		return this.vcBnrImgPath;
	}

	public void setVcBnrImgPath(String vcBnrImgPath) {
		this.vcBnrImgPath = vcBnrImgPath;
	}

	

	public Calendar getDtCrtdAt() {
		return dtCrtdAt;
	}

	public void setDtCrtdAt(Calendar dtCrtdAt) {
		this.dtCrtdAt = dtCrtdAt;
	}

	public Calendar getDtUpdtdAt() {
		return dtUpdtdAt;
	}

	public void setDtUpdtdAt(Calendar dtUpdtdAt) {
		this.dtUpdtdAt = dtUpdtdAt;
	}

}