package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PROMOTION_TB_MASTER database table.
 * 
 */
@Entity
@Table(name="PROMOTION_TB_MASTER")
public class PromotionTbMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="promo_seq")
	@SequenceGenerator(name="promo_seq",sequenceName="PROMOTION_TB_MASTER_SEQ")  
	@Column(name="NM_PRMTN_RL_ID")
	private long nmPrmtnRlId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_PRMTN_FRM")
	private Calendar dtPrmtnFrm;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_PRMTN_TO")
	private Calendar dtPrmtnTo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_UPDTD_AT")
	private Calendar dtUpdtdAt;

	@Column(name="VC_STTS")
	private String vcStts;	

	@Column(name="IS_DLTD")
	private BigDecimal isDltd;

	@Column(name="NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name="NM_DSCNT_VL")
	private BigDecimal nmDscntVl;

	@Column(name="NM_LMT_PR_CSTMR")
	private BigDecimal nmLmtPrCstmr;

	@Column(name="NM_LMT_PR_ORDR")
	private BigDecimal nmLmtPrOrdr;

	@Column(name="NM_LMT_PR_PRMTN")
	private BigDecimal nmLmtPrPrmtn;

	@Column(name="NM_ORDR_OVR")
	private BigDecimal nmOrdrOvr;

	@Column(name="NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name="NM_DSCNT_TYP")
	private BigDecimal nmDscntTyp;

	@Column(name="NM_PRMTN_SCP")
	private BigDecimal nmPrmtnScp;

	@Column(name="VC_RL_CD")
	private String vcRlCd;

	@Column(name="VC_RL_CD_TYP")
	private char vcRlCdTyp;

	@Column(name="VC_RL_DSCR")
	private String vcRlDscr;

	@Column(name="VC_RL_NM")
	private String vcRlNm;

	//bi-directional many-to-one association to OrderProductTbDetail
	@OneToMany(mappedBy="promotionTbMaster")
	private List<OrderProductTbDetail> orderProductTbDetails;

	//bi-directional many-to-one association to OrderTbMaster
	@OneToMany(mappedBy="promotionTbMaster")
	private List<OrderTbMaster> orderTbMasters;

	//bi-directional many-to-one association to CategoryTbMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NM_CTGRY_ID")
	private CategoryTbMaster categoryTbMaster;

	//bi-directional many-to-one association to ProductTbMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NM_PRD_ID")
	private ProductTbMaster productTbMaster;

	//bi-directional many-to-one association to StoreTbMaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_STR_ID")
	private StoreTbMaster storeTbMaster;

	public PromotionTbMaster() {
	}

	public long getNmPrmtnRlId() {
		return this.nmPrmtnRlId;
	}

	public void setNmPrmtnRlId(long nmPrmtnRlId) {
		this.nmPrmtnRlId = nmPrmtnRlId;
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

	public BigDecimal getNmDscntVl() {
		return this.nmDscntVl;
	}

	public void setNmDscntVl(BigDecimal nmDscntVl) {
		this.nmDscntVl = nmDscntVl;
	}

	public BigDecimal getNmLmtPrCstmr() {
		return this.nmLmtPrCstmr;
	}

	public void setNmLmtPrCstmr(BigDecimal nmLmtPrCstmr) {
		this.nmLmtPrCstmr = nmLmtPrCstmr;
	}

	public BigDecimal getNmLmtPrOrdr() {
		return this.nmLmtPrOrdr;
	}

	public void setNmLmtPrOrdr(BigDecimal nmLmtPrOrdr) {
		this.nmLmtPrOrdr = nmLmtPrOrdr;
	}

	public BigDecimal getNmLmtPrPrmtn() {
		return this.nmLmtPrPrmtn;
	}

	public void setNmLmtPrPrmtn(BigDecimal nmLmtPrPrmtn) {
		this.nmLmtPrPrmtn = nmLmtPrPrmtn;
	}

	public BigDecimal getNmOrdrOvr() {
		return this.nmOrdrOvr;
	}

	public void setNmOrdrOvr(BigDecimal nmOrdrOvr) {
		this.nmOrdrOvr = nmOrdrOvr;
	}

	public BigDecimal getNmUpdtdBy() {
		return this.nmUpdtdBy;
	}

	public void setNmUpdtdBy(BigDecimal nmUpdtdBy) {
		this.nmUpdtdBy = nmUpdtdBy;
	}



	public String getVcRlCd() {
		return this.vcRlCd;
	}

	public void setVcRlCd(String vcRlCd) {
		this.vcRlCd = vcRlCd;
	}

	public char getVcRlCdTyp() {
		return this.vcRlCdTyp;
	}

	public void setVcRlCdTyp(char vcRlCdTyp) {
		this.vcRlCdTyp = vcRlCdTyp;
	}

	public String getVcRlDscr() {
		return this.vcRlDscr;
	}

	public void setVcRlDscr(String vcRlDscr) {
		this.vcRlDscr = vcRlDscr;
	}

	public String getVcRlNm() {
		return this.vcRlNm;
	}

	public void setVcRlNm(String vcRlNm) {
		this.vcRlNm = vcRlNm;
	}

	public List<OrderProductTbDetail> getOrderProductTbDetails() {
		return this.orderProductTbDetails;
	}

	public void setOrderProductTbDetails(List<OrderProductTbDetail> orderProductTbDetails) {
		this.orderProductTbDetails = orderProductTbDetails;
	}

	public List<OrderTbMaster> getOrderTbMasters() {
		return this.orderTbMasters;
	}

	public void setOrderTbMasters(List<OrderTbMaster> orderTbMasters) {
		this.orderTbMasters = orderTbMasters;
	}

	public CategoryTbMaster getCategoryTbMaster() {
		return this.categoryTbMaster;
	}

	public void setCategoryTbMaster(CategoryTbMaster categoryTbMaster) {
		this.categoryTbMaster = categoryTbMaster;
	}

	public ProductTbMaster getProductTbMaster() {
		return this.productTbMaster;
	}

	public void setProductTbMaster(ProductTbMaster productTbMaster) {
		this.productTbMaster = productTbMaster;
	}

	public StoreTbMaster getStoreTbMaster() {
		return this.storeTbMaster;
	}

	public void setStoreTbMaster(StoreTbMaster storeTbMaster) {
		this.storeTbMaster = storeTbMaster;
	}

	public BigDecimal getNmPrmtnScp() {
		return nmPrmtnScp;
	}

	public void setNmPrmtnScp(BigDecimal nmPrmtnScp) {
		this.nmPrmtnScp = nmPrmtnScp;
	}

	public BigDecimal getNmDscntTyp() {
		return nmDscntTyp;
	}

	public void setNmDscntTyp(BigDecimal nmDscntTyp) {
		this.nmDscntTyp = nmDscntTyp;
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
	 * @return the dtPrmtnFrm
	 */
	public Calendar getDtPrmtnFrm() {
		return dtPrmtnFrm;
	}

	/**
	 * @param dtPrmtnFrm the dtPrmtnFrm to set
	 */
	public void setDtPrmtnFrm(Calendar dtPrmtnFrm) {
		this.dtPrmtnFrm = dtPrmtnFrm;
	}

	/**
	 * @return the dtPrmtnTo
	 */
	public Calendar getDtPrmtnTo() {
		return dtPrmtnTo;
	}

	/**
	 * @param dtPrmtnTo the dtPrmtnTo to set
	 */
	public void setDtPrmtnTo(Calendar dtPrmtnTo) {
		this.dtPrmtnTo = dtPrmtnTo;
	}


}