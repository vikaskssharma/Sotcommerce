package com.sot.ecommerce.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;


/**
 * The persistent class for the WISHLIST_TB_DTL database table.
 * 
 */
@Entity
@Table(name="WISHLIST_TB_DTL")
@NamedQuery(name="WishlistTbDtl.findAll", query="SELECT w FROM WishlistTbDtl w")
public class WishlistTbDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="WishListTbDtl_seq")
	@SequenceGenerator(name="WishListTbDtl_seq" ,sequenceName="WISHLIST_TB_DTL_SEQ")
	
	@Column(name="NM_WSHLST_ID")
	private long nmWshlstId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_UPDTD_AT")
	private Calendar dtUpdtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_WSHLST_ADD_DT")
	private Calendar dtWshlstAddDt;

	@Column(name="IS_DLTD")
	private BigDecimal isDltd;

	@Column(name="NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name="NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	//bi-directional many-to-one association to ProductTbMaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_PRD_ID")
	private ProductTbMaster productTbMaster;

	//bi-directional many-to-one association to ProductVariantTbDtl
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_VRNT_ID")
	private ProductVariantTbDtl productVariantTbDtl;

	//bi-directional many-to-one association to StoreTbMaster
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NM_STR_ID")
	private Store store;

	//bi-directional many-to-one association to UmTbUser
	@ManyToOne
	@JoinColumn(name="NM_USR_ID")
	private User User;

	public WishlistTbDtl() {
	}

	public long getNmWshlstId() {
		return this.nmWshlstId;
	}

	public void setNmWshlstId(long nmWshlstId) {
		this.nmWshlstId = nmWshlstId;
	}

	public Calendar getDtCrtdAt() {
		return this.dtCrtdAt;
	}

	public void setDtCrtdAt(Calendar dtCrtdAt) {
		this.dtCrtdAt = dtCrtdAt;
	}

	public Calendar getDtUpdtdAt() {
		return this.dtUpdtdAt;
	}

	public void setDtUpdtdAt(Calendar dtUpdtdAt) {
		this.dtUpdtdAt = dtUpdtdAt;
	}

	public Calendar getDtWshlstAddDt() {
		return this.dtWshlstAddDt;
	}

	public void setDtWshlstAddDt(Calendar dtWshlstAddDt) {
		this.dtWshlstAddDt = dtWshlstAddDt;
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

	public ProductTbMaster getProductTbMaster() {
		return this.productTbMaster;
	}

	public void setProductTbMaster(ProductTbMaster productTbMaster) {
		this.productTbMaster = productTbMaster;
	}

	public ProductVariantTbDtl getProductVariantTbDtl() {
		return this.productVariantTbDtl;
	}

	public void setProductVariantTbDtl(ProductVariantTbDtl productVariantTbDtl) {
		this.productVariantTbDtl = productVariantTbDtl;
	}

	

}