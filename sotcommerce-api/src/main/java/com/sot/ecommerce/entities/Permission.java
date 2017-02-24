/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package com.sot.ecommerce.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="UM_TB_ROLE_PERMISSION")
public class Permission implements Serializable {
	public Permission() {
	}
	
	@Column(name="NM_PRMSN_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_PERMISSION_PERMISSIONID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_PERMISSION_PERMISSIONID_GENERATOR", strategy="native")	
	private java.math.BigDecimal permissionId;
	
	@ManyToOne(targetEntity=Menu.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_MN_ID", referencedColumnName="NM_MN_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Menu NM_MN;
	
	@Column(name="NM_RL_ID", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal toleId;
	
	public void setPermissionId(java.math.BigDecimal value) {
		this.permissionId = value;
	}
	
	public java.math.BigDecimal getPermissionId() {
		return permissionId;
	}
	
	public java.math.BigDecimal getORMID() {
		return getPermissionId();
	}
	
	public void setToleId(java.math.BigDecimal value) {
		this.toleId = value;
	}
	
	public java.math.BigDecimal getToleId() {
		return toleId;
	}
	
	public void setNM_MN(Menu value) {
		this.NM_MN = value;
	}
	
	public Menu getNM_MN() {
		return NM_MN;
	}
	
	public String toString() {
		return String.valueOf(getPermissionId());
	}
	
	@Transient	
	private boolean _saved = false;
	
	public void onSave() {
		_saved=true;
	}
	
	
	public void onLoad() {
		_saved=true;
	}
	
	
	public boolean isSaved() {
		return _saved;
	}
	
	
}
