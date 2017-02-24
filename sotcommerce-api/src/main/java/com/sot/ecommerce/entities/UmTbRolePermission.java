package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the UM_TB_ROLE_PERMISSION database table.
 * 
 */
@Entity
@Table(name="UM_TB_ROLE_PERMISSION")
public class UmTbRolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="role_permission_seq")
	@SequenceGenerator(name="role_permission_seq",sequenceName="UM_TB_ROLE_PERMISSION_SEQ")  
	@Column(name="NM_PRMSN_ID")
	private long nmPrmsnId;

	@Column(name="NM_RL_ID")
	private BigDecimal nmRlId;

	//bi-directional many-to-one association to UmTbMenu
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_MN_ID")
	private UmTbMenu umTbMenu;

	public UmTbRolePermission() {
	}

	public long getNmPrmsnId() {
		return this.nmPrmsnId;
	}

	public void setNmPrmsnId(long nmPrmsnId) {
		this.nmPrmsnId = nmPrmsnId;
	}

	public BigDecimal getNmRlId() {
		return this.nmRlId;
	}

	public void setNmRlId(BigDecimal nmRlId) {
		this.nmRlId = nmRlId;
	}

	public UmTbMenu getUmTbMenu() {
		return this.umTbMenu;
	}

	public void setUmTbMenu(UmTbMenu umTbMenu) {
		this.umTbMenu = umTbMenu;
	}

}