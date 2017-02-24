package com.sot.ecommerce.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.*;


/**
 * The persistent class for the PRODUCT_VARIANT_TB_DTL database table.
 * 
 */
/**
 * @author diksha.rattan
 *
 */
@Entity
@Table(name="PRODUCT_VARIANT_TB_DTL")
public class ProductVariantTbDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="prodvariant_seq")
    @SequenceGenerator(name="prodvariant_seq",sequenceName="PRODUCT_VARIANT_TB_DTL_SEQ")  
	@Column(name="NM_VRNT_ID")
	private long nmVrntId;

	@Column(name="VC_VRNT_IMG_1")
	private String vcVrntImg1;

	@Column(name="VC_VRNT_IMG_2")
	private String vcVrntImg2;

	@Column(name="VC_VRNT_IMG_3")
	private String vcVrntImg3;

	@Column(name="VC_VRNT_OPTN_1")
	private String vcVrntOptn1;

	@Column(name="VC_VRNT_OPTN_2")
	private String vcVrntOptn2;

	@Column(name="VC_VRNT_OPTN_3")
	private String vcVrntOptn3;

	@Column(name="VC_VRNT_OPTN_4")
	private String vcVrntOptn4;

	@Column(name="VC_VRNT_OPTN_5")
	private String vcVrntOptn5;
	
	@Column(name="VC_VRNT_NM")
	private String vcVrntNm ;
	
	@Column(name="IS_DLTD")
	private BigDecimal isDltd;
	
	@Column(name="NM_VRNT_OPTN_1")
	private BigDecimal nmVrntOptn1;

	@Column(name="NM_VRNT_OPTN_2")
	private BigDecimal nmVrntOptn2;

	@Column(name="NM_VRNT_OPTN_3")
	private BigDecimal nmVrntOptn3;

	@Column(name="NM_VRNT_OPTN_4")
	private BigDecimal nmVrntOptn4;

	@Column(name="NM_VRNT_OPTN_5")
	private BigDecimal nmVrntOptn5;
	
	@Column(name="NM_DL_PRC")
	private BigDecimal nmDlPrc;
	
	@Column(name="NM_SP")
	private BigDecimal nmSp;
	
	@Column(name="NM_QNTTY")
	private BigDecimal nmQntty;
	
	@Column(name = "VC_STTS")
	private String vcStts;	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_UPDTD_AT")
	private Calendar dtUpdtdAt;
	
	//bi-directional many-to-one association to ProductTbMaster
	
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

	@ManyToOne(fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST })	
	@JoinColumn(name="NM_PRD_ID")
	private ProductTbMaster productTbMaster;

	public ProductVariantTbDtl() {
	}

	public long getNmVrntId() {
		return this.nmVrntId;
	}

	public void setNmVrntId(long nmVrntId) {
		this.nmVrntId = nmVrntId;
	}

	public String getVcVrntImg1() {
		return this.vcVrntImg1;
	}

	public void setVcVrntImg1(String vcVrntImg1) {
		this.vcVrntImg1 = vcVrntImg1;
	}

	public String getVcVrntImg2() {
		return this.vcVrntImg2;
	}

	public void setVcVrntImg2(String vcVrntImg2) {
		this.vcVrntImg2 = vcVrntImg2;
	}

	public String getVcVrntImg3() {
		return this.vcVrntImg3;
	}

	public void setVcVrntImg3(String vcVrntImg3) {
		this.vcVrntImg3 = vcVrntImg3;
	}

	public String getVcVrntOptn1() {
		return this.vcVrntOptn1;
	}

	public void setVcVrntOptn1(String vcVrntOptn1) {
		this.vcVrntOptn1 = vcVrntOptn1;
	}

	public String getVcVrntOptn2() {
		return this.vcVrntOptn2;
	}

	public void setVcVrntOptn2(String vcVrntOptn2) {
		this.vcVrntOptn2 = vcVrntOptn2;
	}

	public String getVcVrntOptn3() {
		return this.vcVrntOptn3;
	}

	public void setVcVrntOptn3(String vcVrntOptn3) {
		this.vcVrntOptn3 = vcVrntOptn3;
	}

	public String getVcVrntOptn4() {
		return this.vcVrntOptn4;
	}

	public void setVcVrntOptn4(String vcVrntOptn4) {
		this.vcVrntOptn4 = vcVrntOptn4;
	}

	public String getVcVrntOptn5() {
		return this.vcVrntOptn5;
	}

	public void setVcVrntOptn5(String vcVrntOptn5) {
		this.vcVrntOptn5 = vcVrntOptn5;
	}

	public ProductTbMaster getProductTbMaster() {
		return this.productTbMaster;
	}

	public void setProductTbMaster(ProductTbMaster productTbMaster) {
		this.productTbMaster = productTbMaster;
	}

	public String getVcVrntNm() {
		return vcVrntNm;
	}

	public void setVcVrntNm(String vcVrntNm) {
		this.vcVrntNm = vcVrntNm;
	}

	/**
	 * @return the isDltd
	 */
	public BigDecimal getIsDltd() {
		return isDltd;
	}

	/**
	 * @param isDltd the isDltd to set
	 */
	public void setIsDltd(BigDecimal isDltd) {
		this.isDltd = isDltd;
	}

	/**
	 * @return the nmVrntOptn1
	 */
	public BigDecimal getNmVrntOptn1() {
		return nmVrntOptn1;
	}

	/**
	 * @param nmVrntOptn1 the nmVrntOptn1 to set
	 */
	public void setNmVrntOptn1(BigDecimal nmVrntOptn1) {
		this.nmVrntOptn1 = nmVrntOptn1;
	}

	/**
	 * @return the nmVrntOptn2
	 */
	public BigDecimal getNmVrntOptn2() {
		return nmVrntOptn2;
	}

	/**
	 * @param nmVrntOptn2 the nmVrntOptn2 to set
	 */
	public void setNmVrntOptn2(BigDecimal nmVrntOptn2) {
		this.nmVrntOptn2 = nmVrntOptn2;
	}

	/**
	 * @return the nmVrntOptn3
	 */
	public BigDecimal getNmVrntOptn3() {
		return nmVrntOptn3;
	}

	/**
	 * @param nmVrntOptn3 the nmVrntOptn3 to set
	 */
	public void setNmVrntOptn3(BigDecimal nmVrntOptn3) {
		this.nmVrntOptn3 = nmVrntOptn3;
	}

	/**
	 * @return the nmVrntOptn4
	 */
	public BigDecimal getNmVrntOptn4() {
		return nmVrntOptn4;
	}

	/**
	 * @param nmVrntOptn4 the nmVrntOptn4 to set
	 */
	public void setNmVrntOptn4(BigDecimal nmVrntOptn4) {
		this.nmVrntOptn4 = nmVrntOptn4;
	}

	/**
	 * @return the nmVrntOptn5
	 */
	public BigDecimal getNmVrntOptn5() {
		return nmVrntOptn5;
	}

	/**
	 * @param nmVrntOptn5 the nmVrntOptn5 to set
	 */
	public void setNmVrntOptn5(BigDecimal nmVrntOptn5) {
		this.nmVrntOptn5 = nmVrntOptn5;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	/**
	 * @return the nmSp
	 */
	public BigDecimal getNmSp() {
		return nmSp;
	}

	/**
	 * @param nmSp the nmSp to set
	 */
	public void setNmSp(BigDecimal nmSp) {
		this.nmSp = nmSp;
	}

	/**
	 * @return the nmQntty
	 */
	public BigDecimal getNmQntty() {
		return nmQntty;
	}

	/**
	 * @param nmQntty the nmQntty to set
	 */
	public void setNmQntty(BigDecimal nmQntty) {
		this.nmQntty = nmQntty;
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