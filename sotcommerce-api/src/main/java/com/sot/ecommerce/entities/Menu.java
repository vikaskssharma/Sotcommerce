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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="UM_TB_MENU")
public class Menu implements Serializable {
	public Menu() {
	}
	
	@Column(name="NM_MN_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SBSC_FOS_PERSISTANCE_MENU_MENUID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SBSC_FOS_PERSISTANCE_MENU_MENUID_GENERATOR", strategy="native")	
	private java.math.BigDecimal menuId;
	
	@Column(name="VC_MN_NM", nullable=true, length=32)	
	private String name;
	
	@Column(name="VC_MN_LNK", nullable=false, length=128)	
	private String link;
	
	@Column(name="DT_CRTD_AT", nullable=false)	
	private java.sql.Timestamp createdDate;
	
	@Column(name="DT_UPDTD_AT", nullable=false)	
	private java.sql.Timestamp updatedDate;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_CRTD_BY", referencedColumnName="NM_USR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private User NM_CRTD_BY;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_UPDTD_BY", referencedColumnName="NM_USR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private User NM_UPDTD_BY;
	
	@Column(name="IS_DLTD", nullable=false, length=1)	
	private int isDeleted;
	
	@OneToMany(mappedBy="NM_MN", targetEntity=Permission.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private Set UM_TB_ROLE_PERMISSION = new HashSet();
	
	public void setMenuId(java.math.BigDecimal value) {
		this.menuId = value;
	}
	
	public java.math.BigDecimal getMenuId() {
		return menuId;
	}
	
	public java.math.BigDecimal getORMID() {
		return getMenuId();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLink(String value) {
		this.link = value;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setCreatedDate(java.sql.Timestamp value) {
		this.createdDate = value;
	}
	
	public java.sql.Timestamp getCreatedDate() {
		return createdDate;
	}
	
	public void setUpdatedDate(java.sql.Timestamp value) {
		this.updatedDate = value;
	}
	
	public java.sql.Timestamp getUpdatedDate() {
		return updatedDate;
	}
	
	public void setIsDeleted(int value) {
		this.isDeleted = value;
	}
	
	public int getIsDeleted() {
		return isDeleted;
	}
	
	public void setNM_CRTD_BY(User value) {
		this.NM_CRTD_BY = value;
	}
	
	public User getNM_CRTD_BY() {
		return NM_CRTD_BY;
	}
	
	public void setNM_UPDTD_BY(User value) {
		this.NM_UPDTD_BY = value;
	}
	
	public User getNM_UPDTD_BY() {
		return NM_UPDTD_BY;
	}
	
	public void setUM_TB_ROLE_PERMISSION(java.util.Set value) {
		this.UM_TB_ROLE_PERMISSION = value;
	}
	
	public java.util.Set getUM_TB_ROLE_PERMISSION() {
		return UM_TB_ROLE_PERMISSION;
	}
	
	
	public String toString() {
		return String.valueOf(getMenuId());
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
