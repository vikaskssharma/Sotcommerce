package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the UM_TB_CNTRY_MSTR database table.
 * 
 */
@Entity
@Table(name="UM_TB_CNTRY_MSTR")
public class UmTbCntryMstr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="country_seq")
	@SequenceGenerator(name="country_seq",sequenceName="UM_TB_CNTRY_MSTR_SEQ")  
	@Column(name="NM_CNTRY_ID")
	private long nmCntryId;

	@Column(name="VC_CNTRY_CD")
	private String vcCntryCd;

	@Column(name="VC_CNTRY_NM")
	private String vcCntryNm;

	//bi-directional many-to-one association to OrderBillingTbDtl
	@OneToMany(mappedBy="umTbCntryMstr")
	private List<OrderBillingTbDtl> orderBillingTbDtls;

	//bi-directional many-to-one association to OrderShippingTbDtl
	@OneToMany(mappedBy="umTbCntryMstr")
	private List<OrderShippingTbDtl> orderShippingTbDtls;

	//bi-directional many-to-one association to UmTbUser
	@OneToMany(mappedBy="umTbCntryMstr")
	private List<UmTbUser> umTbUsers;
	
	//bi-directional many-to-one association to TaxationTbDetail
	@OneToMany(mappedBy="umTbCntryMstr")
	private List<Taxation> taxationTbDetails;
	
	public UmTbCntryMstr() {
	}

	public long getNmCntryId() {
		return this.nmCntryId;
	}

	public void setNmCntryId(long nmCntryId) {
		this.nmCntryId = nmCntryId;
	}

	public String getVcCntryCd() {
		return this.vcCntryCd;
	}

	public void setVcCntryCd(String vcCntryCd) {
		this.vcCntryCd = vcCntryCd;
	}

	public String getVcCntryNm() {
		return this.vcCntryNm;
	}

	public void setVcCntryNm(String vcCntryNm) {
		this.vcCntryNm = vcCntryNm;
	}

	public List<OrderBillingTbDtl> getOrderBillingTbDtls() {
		return this.orderBillingTbDtls;
	}

	public void setOrderBillingTbDtls(List<OrderBillingTbDtl> orderBillingTbDtls) {
		this.orderBillingTbDtls = orderBillingTbDtls;
	}

	public List<OrderShippingTbDtl> getOrderShippingTbDtls() {
		return this.orderShippingTbDtls;
	}

	public void setOrderShippingTbDtls(List<OrderShippingTbDtl> orderShippingTbDtls) {
		this.orderShippingTbDtls = orderShippingTbDtls;
	}

	public List<UmTbUser> getUmTbUsers() {
		return this.umTbUsers;
	}

	public void setUmTbUsers(List<UmTbUser> umTbUsers) {
		this.umTbUsers = umTbUsers;
	}
	
		public List<Taxation> getTaxationTbDetails() {
		return this.taxationTbDetails;
	}

	public void setTaxationTbDetails(List<Taxation> taxationTbDetails) {
		this.taxationTbDetails = taxationTbDetails;
	}

	public Taxation addTaxationTbDetail(Taxation taxationTbDetail) {
		getTaxationTbDetails().add(taxationTbDetail);
		taxationTbDetail.setUmTbCntryMstr(this);

		return taxationTbDetail;
	}

	public Taxation removeTaxationTbDetail(Taxation taxationTbDetail) {
		getTaxationTbDetails().remove(taxationTbDetail);
		taxationTbDetail.setUmTbCntryMstr(null);

		return taxationTbDetail;
	}
}