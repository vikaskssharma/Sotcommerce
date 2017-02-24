package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the PAYMENT_TB_MASTER database table.
 * 
 */
@Entity
@Table(name="PAYMENT_TB_MASTER")
public class PaymentTbMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="payment_seq")
	@SequenceGenerator(name="payment_seq",sequenceName="PAYMENT_TB_MASTER_SEQ")  	
	@Column(name="NM_PYMNT_MD_ID")
	private long nmPymntMdId;

	@Column(name="VC_PYMNT_DSCR")
	private String vcPymntDscr;

	@Column(name="VC_PYMNT_MD")
	private String vcPymntMd;

	//bi-directional many-to-one association to OrderTbMaster
	@OneToMany(mappedBy="paymentTbMaster")
	private List<OrderTbMaster> orderTbMasters;

	public PaymentTbMaster() {
	}

	public long getNmPymntMdId() {
		return this.nmPymntMdId;
	}

	public void setNmPymntMdId(long nmPymntMdId) {
		this.nmPymntMdId = nmPymntMdId;
	}

	public String getVcPymntDscr() {
		return this.vcPymntDscr;
	}

	public void setVcPymntDscr(String vcPymntDscr) {
		this.vcPymntDscr = vcPymntDscr;
	}

	public String getVcPymntMd() {
		return this.vcPymntMd;
	}

	public void setVcPymntMd(String vcPymntMd) {
		this.vcPymntMd = vcPymntMd;
	}

	public List<OrderTbMaster> getOrderTbMasters() {
		return this.orderTbMasters;
	}

	public void setOrderTbMasters(List<OrderTbMaster> orderTbMasters) {
		this.orderTbMasters = orderTbMasters;
	}

}