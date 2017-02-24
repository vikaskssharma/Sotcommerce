package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the TAXATION_TB_DETAIL database table.
 * 
 */
@Entity
@Table(name="TAXATION_TB_DETAIL")
@NamedQuery(name="TaxationTbDetail.findAll", query="SELECT t FROM TaxationTbDetail t")
public class Taxation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAXATION_TB_DETAIL_NMTXTNID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAXATION_TB_DETAIL_NMTXTNID_GENERATOR")
	@Column(name="NM_TXTN_ID")
	private long nmTxtnId;

	@Column(name="IS_DLTD")
	private BigDecimal isDltd;

	@Column(name="NM_TX_PRCNTG")
	private BigDecimal nmTxPrcntg;
	
	// bi-directional many-to-one association to StoreTbMaster
	@ManyToOne
	@JoinColumn(name = "NM_STR_ID")
	private StoreTbMaster storeTbMaster;
	
	//bi-directional many-to-one association to CategoryTbMaster
	@ManyToOne
	@JoinColumn(name="NM_CTGRY_ID")
	private CategoryTbMaster categoryTbMaster;

	//bi-directional many-to-one association to UmTbCntryMstr
	@ManyToOne
	@JoinColumn(name="NM_CNTRY_ID")
	private UmTbCntryMstr umTbCntryMstr;

	public Taxation() {
	}

	public long getNmTxtnId() {
		return this.nmTxtnId;
	}

	public void setNmTxtnId(long nmTxtnId) {
		this.nmTxtnId = nmTxtnId;
	}

	public BigDecimal getIsDltd() {
		return this.isDltd;
	}

	public void setIsDltd(BigDecimal isDltd) {
		this.isDltd = isDltd;
	}

	public BigDecimal getNmTxPrcntg() {
		return this.nmTxPrcntg;
	}

	public void setNmTxPrcntg(BigDecimal nmTxPrcntg) {
		this.nmTxPrcntg = nmTxPrcntg;
	}

	public CategoryTbMaster getCategoryTbMaster() {
		return this.categoryTbMaster;
	}

	public void setCategoryTbMaster(CategoryTbMaster categoryTbMaster) {
		this.categoryTbMaster = categoryTbMaster;
	}

	public UmTbCntryMstr getUmTbCntryMstr() {
		return this.umTbCntryMstr;
	}

	public void setUmTbCntryMstr(UmTbCntryMstr umTbCntryMstr) {
		this.umTbCntryMstr = umTbCntryMstr;
	}

	public StoreTbMaster getStoreTbMaster() {
		return storeTbMaster;
	}

	public void setStoreTbMaster(StoreTbMaster storeTbMaster) {
		this.storeTbMaster = storeTbMaster;
	}

}