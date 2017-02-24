package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the UM_TB_USER_ROLE database table.
 * 
 */
@Entity
@Table(name="UM_TB_USER_ROLE")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="user_role_seq")
	@SequenceGenerator(name="user_role_seq",sequenceName="UM_TB_USER_ROLE_SEQ")  	
	@Column(name="NM_USR_RL_ID")
	private long nmUsrRlId;

	//bi-directional many-to-one association to UmTbRole
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NM_RL_ID")
	private UmTbRole umTbRole;

	//bi-directional many-to-one association to UmTbUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NM_USR_ID")
	private UmTbUser umTbUser;

	public UmTbUserRole() {
	}

	public long getNmUsrRlId() {
		return this.nmUsrRlId;
	}

	public void setNmUsrRlId(long nmUsrRlId) {
		this.nmUsrRlId = nmUsrRlId;
	}

	public UmTbRole getUmTbRole() {
		return this.umTbRole;
	}

	public void setUmTbRole(UmTbRole umTbRole) {
		this.umTbRole = umTbRole;
	}

	public UmTbUser getUmTbUser() {
		return this.umTbUser;
	}

	public void setUmTbUser(UmTbUser umTbUser) {
		this.umTbUser = umTbUser;
	}

}