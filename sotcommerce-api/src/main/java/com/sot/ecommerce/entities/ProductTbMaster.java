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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the PRODUCT_TB_MASTER database table.
 * 
 */
@Entity
@Table(name = "PRODUCT_TB_MASTER")
public class ProductTbMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "prod_seq")
	@SequenceGenerator(name = "prod_seq", sequenceName = "PRODUCT_TB_MASTER_SEQ")
	@Column(name = "NM_PRD_ID")
	private long nmPrdId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_UPDTD_AT")
	private Calendar dtUpdtdAt;

	@Column(name = "IS_COD")
	private BigDecimal isCod;

	@Column(name = "IS_DLTD")
	private BigDecimal isDltd;

	@Column(name = "IS_FR_SHPNG")
	private BigDecimal isFrShpng;

	@Column(name = "IS_FTRD")
	private BigDecimal isFtrd;

	@Column(name = "IS_HTDL")
	private BigDecimal isHtdl;

	
	@Column(name = "IS_VRNT")
	private BigDecimal isVrnt;

	@Column(name = "NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name = "NM_EXPTD_DLVRY_DYS")
	private BigDecimal nmExptdDlvryDys;

	@Column(name="NM_DL_PRC")
	private BigDecimal nmDlPrc;


	@Column(name = "NM_QNTY")
	private BigDecimal nmQnty;

	@Column(name = "NM_SP")
	private BigDecimal nmSp;

	// bi-directional many-to-one association to StoreTbMaster
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NM_STR_ID")
	private StoreTbMaster storeTbMaster;

	@Column(name = "NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name = "VC_ATTR1_VALUE")
	private String vcAttr1Value;

	@Column(name = "VC_ATTR10_VALUE")
	private String vcAttr10Value;

	@Column(name = "VC_ATTR11_VALUE")
	private String vcAttr11Value;

	@Column(name = "VC_ATTR12_VALUE")
	private String vcAttr12Value;

	@Column(name = "VC_ATTR13_VALUE")
	private String vcAttr13Value;

	@Column(name = "VC_ATTR14_VALUE")
	private String vcAttr14Value;

	@Column(name = "VC_ATTR15_VALUE")
	private String vcAttr15Value;

	@Column(name = "VC_ATTR16_VALUE")
	private String vcAttr16Value;

	@Column(name = "VC_ATTR17_VALUE")
	private String vcAttr17Value;

	@Column(name = "VC_ATTR18_VALUE")
	private String vcAttr18Value;

	@Column(name = "VC_ATTR19_VALUE")
	private String vcAttr19Value;

	@Column(name = "VC_ATTR2_VALUE")
	private String vcAttr2Value;

	@Column(name = "VC_ATTR20_VALUE")
	private String vcAttr20Value;

	@Column(name = "VC_ATTR21_VALUE")
	private String vcAttr21Value;

	@Column(name = "VC_ATTR22_VALUE")
	private String vcAttr22Value;

	@Column(name = "VC_ATTR23_VALUE")
	private String vcAttr23Value;

	@Column(name = "VC_ATTR24_VALUE")
	private String vcAttr24Value;

	@Column(name = "VC_ATTR25_VALUE")
	private String vcAttr25Value;

	@Column(name = "VC_ATTR3_VALUE")
	private String vcAttr3Value;

	@Column(name = "VC_ATTR4_VALUE")
	private String vcAttr4Value;

	@Column(name = "VC_ATTR5_VALUE")
	private String vcAttr5Value;

	@Column(name = "VC_ATTR6_VALUE")
	private String vcAttr6Value;

	@Column(name = "VC_ATTR7_VALUE")
	private String vcAttr7Value;

	@Column(name = "VC_ATTR8_VALUE")
	private String vcAttr8Value;

	@Column(name = "VC_ATTR9_VALUE")
	private String vcAttr9Value;

	@Column(name = "VC_CMPLTN_STTS")
	private String vcCmpltnStts;

	@Column(name = "VC_PRD_DSC")
	private String vcPrdDsc;

	@Column(name = "VC_PRD_IMG1_PTH")
	private String vcPrdImg1Pth;

	@Column(name = "VC_PRD_IMG2_PTH")
	private String vcPrdImg2Pth;

	@Column(name = "VC_PRD_IMG3_PTH")
	private String vcPrdImg3Pth;

	@Column(name = "VC_PRD_IMG4_PTH")
	private String vcPrdImg4Pth;

	@Column(name = "VC_PRD_NM")
	private String vcPrdNm;

	@Column(name = "VC_STTS")
	private String vcStts;	
	
	@Column(name="NM_ATTR1_VALUE")
	private BigDecimal nmAttr1Value;

	@Column(name="NM_ATTR2_VALUE")
	private BigDecimal nmAttr2Value;

	@Column(name="NM_ATTR3_VALUE")
	private BigDecimal nmAttr3Value;

	@Column(name="NM_ATTR4_VALUE")
	private BigDecimal nmAttr4Value;

	@Column(name="NM_ATTR5_VALUE")
	private BigDecimal nmAttr5Value;	
	
	
	// bi-directional many-to-one association to OrderProductTbDetail
	@OneToMany(mappedBy = "productTbMaster")
	private List<OrderProductTbDetail> orderProductTbDetails;

	// bi-directional many-to-one association to CategoryTbMaster
	@ManyToOne(fetch = FetchType.EAGER)
	// (fetch=FetchType.LAZY)
	@JoinColumn(name = "NM_CTGRY_ID")
	private CategoryTbMaster categoryTbMaster;

	// bi-directional many-to-one association to ProductVariantTbDtl
	@OneToMany(mappedBy = "productTbMaster", fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ProductVariantTbDtl> productVariantTbDtls;

	// bi-directional many-to-one association to PromotionTbMaster
	@OneToMany(mappedBy = "productTbMaster", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<PromotionTbMaster> promotionTbMasters;

	// bi-directional many-to-one association to RelatedProductTbDtl
	@OneToMany(mappedBy = "productTbMaster1", fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<RelatedProductTbDtl> relatedProductTbDtls1;

	// bi-directional many-to-one association to RelatedProductTbDtl
	@OneToMany(mappedBy = "productTbMaster2")
	private List<RelatedProductTbDtl> relatedProductTbDtls2;

	// bi-directional many-to-one association to SimilarProductTbDtl
	@OneToMany(mappedBy = "productTbMaster1", fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<SimilarProductTbDtl> similarProductTbDtls1;

	// bi-directional many-to-one association to SimilarProductTbDtl
	@OneToMany(mappedBy = "productTbMaster2")
	private List<SimilarProductTbDtl> similarProductTbDtls2;

	// bi-directional many-to-one association to WishlistTbDtl
	@OneToMany(mappedBy = "productTbMaster")
	private List<WishlistTbDtl> wishlistTbDtls;

	public ProductTbMaster() {
	}

	public long getNmPrdId() {
		return this.nmPrdId;
	}

	public void setNmPrdId(long nmPrdId) {
		this.nmPrdId = nmPrdId;
	}

	public BigDecimal getIsCod() {
		return this.isCod;
	}

	public void setIsCod(BigDecimal isCod) {
		this.isCod = isCod;
	}

	public BigDecimal getIsDltd() {
		return this.isDltd;
	}

	public void setIsDltd(BigDecimal isDltd) {
		this.isDltd = isDltd;
	}

	public BigDecimal getIsFrShpng() {
		return this.isFrShpng;
	}

	public void setIsFrShpng(BigDecimal isFrShpng) {
		this.isFrShpng = isFrShpng;
	}

	public BigDecimal getIsFtrd() {
		return this.isFtrd;
	}

	public void setIsFtrd(BigDecimal isFtrd) {
		this.isFtrd = isFtrd;
	}

	public BigDecimal getIsHtdl() {
		return this.isHtdl;
	}

	public void setIsHtdl(BigDecimal isHtdl) {
		this.isHtdl = isHtdl;
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

	public BigDecimal getNmExptdDlvryDys() {
		return this.nmExptdDlvryDys;
	}

	public void setNmExptdDlvryDys(BigDecimal nmExptdDlvryDys) {
		this.nmExptdDlvryDys = nmExptdDlvryDys;
	}

	

	/**
	 * @return the nmDlPrc
	 */
	public BigDecimal getNmDlPrc() {
		return nmDlPrc;
	}

	/**
	 * @param nmDlPrc the nmDlPrc to set
	 */
	public void setNmDlPrc(BigDecimal nmDlPrc) {
		this.nmDlPrc = nmDlPrc;
	}

	public BigDecimal getNmQnty() {
		return this.nmQnty;
	}

	public void setNmQnty(BigDecimal nmQnty) {
		this.nmQnty = nmQnty;
	}

	public BigDecimal getNmSp() {
		return this.nmSp;
	}

	public void setNmSp(BigDecimal nmSp) {
		this.nmSp = nmSp;
	}

	public BigDecimal getNmUpdtdBy() {
		return this.nmUpdtdBy;
	}

	public void setNmUpdtdBy(BigDecimal nmUpdtdBy) {
		this.nmUpdtdBy = nmUpdtdBy;
	}

	public String getVcAttr1Value() {
		return this.vcAttr1Value;
	}

	public void setVcAttr1Value(String vcAttr1Value) {
		this.vcAttr1Value = vcAttr1Value;
	}

	public String getVcAttr10Value() {
		return this.vcAttr10Value;
	}

	public void setVcAttr10Value(String vcAttr10Value) {
		this.vcAttr10Value = vcAttr10Value;
	}

	public String getVcAttr11Value() {
		return this.vcAttr11Value;
	}

	public void setVcAttr11Value(String vcAttr11Value) {
		this.vcAttr11Value = vcAttr11Value;
	}

	public String getVcAttr12Value() {
		return this.vcAttr12Value;
	}

	public void setVcAttr12Value(String vcAttr12Value) {
		this.vcAttr12Value = vcAttr12Value;
	}

	public String getVcAttr13Value() {
		return this.vcAttr13Value;
	}

	public void setVcAttr13Value(String vcAttr13Value) {
		this.vcAttr13Value = vcAttr13Value;
	}

	public String getVcAttr14Value() {
		return this.vcAttr14Value;
	}

	public void setVcAttr14Value(String vcAttr14Value) {
		this.vcAttr14Value = vcAttr14Value;
	}

	public String getVcAttr15Value() {
		return this.vcAttr15Value;
	}

	public void setVcAttr15Value(String vcAttr15Value) {
		this.vcAttr15Value = vcAttr15Value;
	}

	public String getVcAttr16Value() {
		return this.vcAttr16Value;
	}

	public void setVcAttr16Value(String vcAttr16Value) {
		this.vcAttr16Value = vcAttr16Value;
	}

	public String getVcAttr17Value() {
		return this.vcAttr17Value;
	}

	public void setVcAttr17Value(String vcAttr17Value) {
		this.vcAttr17Value = vcAttr17Value;
	}

	public String getVcAttr18Value() {
		return this.vcAttr18Value;
	}

	public void setVcAttr18Value(String vcAttr18Value) {
		this.vcAttr18Value = vcAttr18Value;
	}

	public String getVcAttr19Value() {
		return this.vcAttr19Value;
	}

	public void setVcAttr19Value(String vcAttr19Value) {
		this.vcAttr19Value = vcAttr19Value;
	}

	public String getVcAttr2Value() {
		return this.vcAttr2Value;
	}

	public void setVcAttr2Value(String vcAttr2Value) {
		this.vcAttr2Value = vcAttr2Value;
	}

	public String getVcAttr20Value() {
		return this.vcAttr20Value;
	}

	public void setVcAttr20Value(String vcAttr20Value) {
		this.vcAttr20Value = vcAttr20Value;
	}

	public String getVcAttr21Value() {
		return this.vcAttr21Value;
	}

	public void setVcAttr21Value(String vcAttr21Value) {
		this.vcAttr21Value = vcAttr21Value;
	}

	public String getVcAttr22Value() {
		return this.vcAttr22Value;
	}

	public void setVcAttr22Value(String vcAttr22Value) {
		this.vcAttr22Value = vcAttr22Value;
	}

	public String getVcAttr23Value() {
		return this.vcAttr23Value;
	}

	public void setVcAttr23Value(String vcAttr23Value) {
		this.vcAttr23Value = vcAttr23Value;
	}

	public String getVcAttr24Value() {
		return this.vcAttr24Value;
	}

	public void setVcAttr24Value(String vcAttr24Value) {
		this.vcAttr24Value = vcAttr24Value;
	}

	public String getVcAttr25Value() {
		return this.vcAttr25Value;
	}

	public void setVcAttr25Value(String vcAttr25Value) {
		this.vcAttr25Value = vcAttr25Value;
	}

	public String getVcAttr3Value() {
		return this.vcAttr3Value;
	}

	public void setVcAttr3Value(String vcAttr3Value) {
		this.vcAttr3Value = vcAttr3Value;
	}

	public String getVcAttr4Value() {
		return this.vcAttr4Value;
	}

	public void setVcAttr4Value(String vcAttr4Value) {
		this.vcAttr4Value = vcAttr4Value;
	}

	public String getVcAttr5Value() {
		return this.vcAttr5Value;
	}

	public void setVcAttr5Value(String vcAttr5Value) {
		this.vcAttr5Value = vcAttr5Value;
	}

	public String getVcAttr6Value() {
		return this.vcAttr6Value;
	}

	public void setVcAttr6Value(String vcAttr6Value) {
		this.vcAttr6Value = vcAttr6Value;
	}

	public String getVcAttr7Value() {
		return this.vcAttr7Value;
	}

	public void setVcAttr7Value(String vcAttr7Value) {
		this.vcAttr7Value = vcAttr7Value;
	}

	public String getVcAttr8Value() {
		return this.vcAttr8Value;
	}

	public void setVcAttr8Value(String vcAttr8Value) {
		this.vcAttr8Value = vcAttr8Value;
	}

	public String getVcAttr9Value() {
		return this.vcAttr9Value;
	}

	public void setVcAttr9Value(String vcAttr9Value) {
		this.vcAttr9Value = vcAttr9Value;
	}

	public String getVcCmpltnStts() {
		return this.vcCmpltnStts;
	}

	public void setVcCmpltnStts(String vcCmpltnStts) {
		this.vcCmpltnStts = vcCmpltnStts;
	}

	public String getVcPrdDsc() {
		return this.vcPrdDsc;
	}

	public void setVcPrdDsc(String vcPrdDsc) {
		this.vcPrdDsc = vcPrdDsc;
	}

	public String getVcPrdImg1Pth() {
		return this.vcPrdImg1Pth;
	}

	public void setVcPrdImg1Pth(String vcPrdImg1Pth) {
		this.vcPrdImg1Pth = vcPrdImg1Pth;
	}

	public String getVcPrdImg2Pth() {
		return this.vcPrdImg2Pth;
	}

	public void setVcPrdImg2Pth(String vcPrdImg2Pth) {
		this.vcPrdImg2Pth = vcPrdImg2Pth;
	}

	public String getVcPrdImg3Pth() {
		return this.vcPrdImg3Pth;
	}

	public void setVcPrdImg3Pth(String vcPrdImg3Pth) {
		this.vcPrdImg3Pth = vcPrdImg3Pth;
	}

	public String getVcPrdImg4Pth() {
		return this.vcPrdImg4Pth;
	}

	public void setVcPrdImg4Pth(String vcPrdImg4Pth) {
		this.vcPrdImg4Pth = vcPrdImg4Pth;
	}

	public String getVcPrdNm() {
		return this.vcPrdNm;
	}

	public void setVcPrdNm(String vcPrdNm) {
		this.vcPrdNm = vcPrdNm;
	}

	public List<OrderProductTbDetail> getOrderProductTbDetails() {
		return this.orderProductTbDetails;
	}

	public void setOrderProductTbDetails(
			List<OrderProductTbDetail> orderProductTbDetails) {
		this.orderProductTbDetails = orderProductTbDetails;
	}

	public CategoryTbMaster getCategoryTbMaster() {
		return this.categoryTbMaster;
	}

	public void setCategoryTbMaster(CategoryTbMaster categoryTbMaster) {
		this.categoryTbMaster = categoryTbMaster;
	}

	public List<ProductVariantTbDtl> getProductVariantTbDtls() {
		return this.productVariantTbDtls;
	}

	public void setProductVariantTbDtls(
			List<ProductVariantTbDtl> productVariantTbDtls) {
		this.productVariantTbDtls = productVariantTbDtls;
	}

	public List<PromotionTbMaster> getPromotionTbMasters() {
		return this.promotionTbMasters;
	}

	public void setPromotionTbMasters(List<PromotionTbMaster> promotionTbMasters) {
		this.promotionTbMasters = promotionTbMasters;
	}

	public List<RelatedProductTbDtl> getRelatedProductTbDtls1() {
		return this.relatedProductTbDtls1;
	}

	public void setRelatedProductTbDtls1(
			List<RelatedProductTbDtl> relatedProductTbDtls1) {
		this.relatedProductTbDtls1 = relatedProductTbDtls1;
	}

	public List<RelatedProductTbDtl> getRelatedProductTbDtls2() {
		return this.relatedProductTbDtls2;
	}

	public void setRelatedProductTbDtls2(
			List<RelatedProductTbDtl> relatedProductTbDtls2) {
		this.relatedProductTbDtls2 = relatedProductTbDtls2;
	}

	public List<SimilarProductTbDtl> getSimilarProductTbDtls1() {
		return this.similarProductTbDtls1;
	}

	public void setSimilarProductTbDtls1(
			List<SimilarProductTbDtl> similarProductTbDtls1) {
		this.similarProductTbDtls1 = similarProductTbDtls1;
	}

	public List<SimilarProductTbDtl> getSimilarProductTbDtls2() {
		return this.similarProductTbDtls2;
	}

	public void setSimilarProductTbDtls2(
			List<SimilarProductTbDtl> similarProductTbDtls2) {
		this.similarProductTbDtls2 = similarProductTbDtls2;
	}

	public List<WishlistTbDtl> getWishlistTbDtls() {
		return this.wishlistTbDtls;
	}

	public void setWishlistTbDtls(List<WishlistTbDtl> wishlistTbDtls) {
		this.wishlistTbDtls = wishlistTbDtls;
	}

	/**
	 * @return the storeTbMaster
	 */
	public StoreTbMaster getStoreTbMaster() {
		return storeTbMaster;
	}

	/**
	 * @param storeTbMaster
	 *            the storeTbMaster to set
	 */
	public void setStoreTbMaster(StoreTbMaster storeTbMaster) {
		this.storeTbMaster = storeTbMaster;
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

	/**
	 * @return the vcStts
	 */
	public String getVcStts() {
		return vcStts;
	}

	/**
	 * @param vcStts the vcStts to set
	 */
	public void setVcStts(String vcStts) {
		this.vcStts = vcStts;
	}

	/**
	 * @return the nmAttr1Value
	 */
	public BigDecimal getNmAttr1Value() {
		return nmAttr1Value;
	}

	/**
	 * @param nmAttr1Value the nmAttr1Value to set
	 */
	public void setNmAttr1Value(BigDecimal nmAttr1Value) {
		this.nmAttr1Value = nmAttr1Value;
	}

	/**
	 * @return the nmAttr2Value
	 */
	public BigDecimal getNmAttr2Value() {
		return nmAttr2Value;
	}

	/**
	 * @param nmAttr2Value the nmAttr2Value to set
	 */
	public void setNmAttr2Value(BigDecimal nmAttr2Value) {
		this.nmAttr2Value = nmAttr2Value;
	}

	/**
	 * @return the nmAttr3Value
	 */
	public BigDecimal getNmAttr3Value() {
		return nmAttr3Value;
	}

	/**
	 * @param nmAttr3Value the nmAttr3Value to set
	 */
	public void setNmAttr3Value(BigDecimal nmAttr3Value) {
		this.nmAttr3Value = nmAttr3Value;
	}

	/**
	 * @return the nmAttr4Value
	 */
	public BigDecimal getNmAttr4Value() {
		return nmAttr4Value;
	}

	/**
	 * @param nmAttr4Value the nmAttr4Value to set
	 */
	public void setNmAttr4Value(BigDecimal nmAttr4Value) {
		this.nmAttr4Value = nmAttr4Value;
	}

	/**
	 * @return the nmAttr5Value
	 */
	public BigDecimal getNmAttr5Value() {
		return nmAttr5Value;
	}

	/**
	 * @param nmAttr5Value the nmAttr5Value to set
	 */
	public void setNmAttr5Value(BigDecimal nmAttr5Value) {
		this.nmAttr5Value = nmAttr5Value;
	}

		
	
	
	

}