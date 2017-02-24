package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the SIMILAR_PRODUCT_TB_DTL database table.
 * 
 */
@Entity
@Table(name="SIMILAR_PRODUCT_TB_DTL")
public class SimilarProductTbDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="similarprod_seq")
    @SequenceGenerator(name="similarprod_seq",sequenceName="SIMILAR_PRODUCT_TB_DTL_SEQ")  
	@Column(name="NM_SMLR_ID")
	private long nmSmlrId;

	@Column(name="IS_DLTD")
	private BigDecimal isDltd;

	//bi-directional many-to-one association to ProductTbMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NM_PRD_ID")
	private ProductTbMaster productTbMaster1;

	//bi-directional many-to-one association to ProductTbMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NM_SMLR_PRD_ID")
	private ProductTbMaster productTbMaster2;

	public SimilarProductTbDtl() {
	}

	public long getNmSmlrId() {
		return this.nmSmlrId;
	}

	public void setNmSmlrId(long nmSmlrId) {
		this.nmSmlrId = nmSmlrId;
	}

	public BigDecimal getIsDltd() {
		return this.isDltd;
	}

	public void setIsDltd(BigDecimal isDltd) {
		this.isDltd = isDltd;
	}

	public ProductTbMaster getProductTbMaster1() {
		return this.productTbMaster1;
	}

	public void setProductTbMaster1(ProductTbMaster productTbMaster1) {
		this.productTbMaster1 = productTbMaster1;
	}

	public ProductTbMaster getProductTbMaster2() {
		return this.productTbMaster2;
	}

	public void setProductTbMaster2(ProductTbMaster productTbMaster2) {
		this.productTbMaster2 = productTbMaster2;
	}

}