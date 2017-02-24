package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the UM_TB_MENU database table.
 * 
 */
@Entity
@Table(name="UM_TB_MENU")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="menu_seq")
	@SequenceGenerator(name="menu_seq",sequenceName="UM_TB_MENU_SEQ") 	
	@Column(name="NM_MN_ID")
	private long nmMnId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRTD_AT")
	private Calendar dtCrtdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_UPDTD_AT")
	private Calendar dtUpdtdAt;

	@Column(name="IS_DLTD")
	private BigDecimal isDltd;

	@Column(name="NM_CRTD_BY")
	private BigDecimal nmCrtdBy;

	@Column(name="NM_UPDTD_BY")
	private BigDecimal nmUpdtdBy;

	@Column(name="VC_MN_LNK")
	private String vcMnLnk;

	@Column(name="VC_MN_NM")
	private String vcMnNm;

	//bi-directional many-to-one association to UmTbRolePermission
	@OneToMany(mappedBy="umTbMenu")
	private List<UmTbRolePermission> umTbRolePermissions;

	public UmTbMenu() {
	}

	public long getNmMnId() {
		return this.nmMnId;
	}

	public void setNmMnId(long nmMnId) {
		this.nmMnId = nmMnId;
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

	public String getVcMnLnk() {
		return this.vcMnLnk;
	}

	public void setVcMnLnk(String vcMnLnk) {
		this.vcMnLnk = vcMnLnk;
	}

	public String getVcMnNm() {
		return this.vcMnNm;
	}

	public void setVcMnNm(String vcMnNm) {
		this.vcMnNm = vcMnNm;
	}

	public List<UmTbRolePermission> getUmTbRolePermissions() {
		return this.umTbRolePermissions;
	}

	public void setUmTbRolePermissions(List<UmTbRolePermission> umTbRolePermissions) {
		this.umTbRolePermissions = umTbRolePermissions;
	}

	public void setDtCrtdAt(Calendar dtCrtdAt) {
		this.dtCrtdAt = dtCrtdAt;
	}

	public void setDtUpdtdAt(Calendar dtUpdtdAt) {
		this.dtUpdtdAt = dtUpdtdAt;
	}

}