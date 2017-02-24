package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the RELATED_PRODUCT_TB_DTL database table.
 * 
 */
@Entity
@Table(name="RELATED_PRODUCT_TB_DTL")
public class RelatedProductTbDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="relatedprod_seq")
    @SequenceGenerator(name="relatedprod_seq",sequenceName="RELATED_PRODUCT_TB_DTL_SEQ")  
	@Column(name="NM_RLTD_ID")
	private long nmRltdId;

	@Column(name="IS_DLTD")
	private BigDecimal isDltd;

	//bi-directional many-to-one association to ProductTbMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NM_PRD_ID")
	private ProductTbMaster productTbMaster1;

	//bi-directional many-to-one association to ProductTbMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NM_RLTD_PRD_ID")
	private ProductTbMaster productTbMaster2;

	public RelatedProductTbDtl() {
	}

	public long getNmRltdId() {
		return this.nmRltdId;
	}

	public void setNmRltdId(long nmRltdId) {
		this.nmRltdId = nmRltdId;
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