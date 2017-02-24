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
@Table(name="BANNER_TABLE")
public class Banner implements Serializable {
	public Banner() {
	}
	
	@Column(name="NM_BNR_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_BANNER_BANNERID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_BANNER_BANNERID_GENERATOR", strategy="native")	
	private java.math.BigDecimal bannerID;
	
	@Column(name="VC_BNR_NM", nullable=false, length=64)	
	private String bannerName;
	
	@Column(name="VC_BNR_DSCR", nullable=true, length=256)	
	private String bannerDesc;
	
	@Column(name="NM_BNR_PG", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal bannerPage;
	
	@Column(name="VC_PG_PSTN", nullable=false, length=64)	
	private String bannerLocation;
	
	@Column(name="VB_BNR_IMG_PATH1", nullable=false, length=256)	
	private String bannerImagPath1;
	
	@Column(name="VB_BNR_IMG_PATH2", nullable=true, length=256)	
	private String bannerImagPath2;
	
	@Column(name="VB_BNR_IMG_PATH3", nullable=true, length=256)	
	private String bannerImagPath3;
	
	@Column(name="VB_BNR_IMG_PATH4", nullable=true, length=256)	
	private String bannerImagPath4;
	
	@Column(name="VB_BNR_IMG_PATH5", nullable=true, length=256)	
	private String bannerImagPath5;
	
	@Column(name="DT_BNR_ACTVTD_FRM", nullable=true)	
	private java.sql.Timestamp bannerActFromDate;
	
	@Column(name="IS_ACT", nullable=false, length=1)	
	private int isActive;
	
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
	
	public void setBannerID(java.math.BigDecimal value) {
		this.bannerID = value;
	}
	
	public java.math.BigDecimal getBannerID() {
		return bannerID;
	}
	
	public java.math.BigDecimal getORMID() {
		return getBannerID();
	}
	
	public void setBannerName(String value) {
		this.bannerName = value;
	}
	
	public String getBannerName() {
		return bannerName;
	}
	
	public void setBannerDesc(String value) {
		this.bannerDesc = value;
	}
	
	public String getBannerDesc() {
		return bannerDesc;
	}
	
	public void setBannerPage(java.math.BigDecimal value) {
		this.bannerPage = value;
	}
	
	public java.math.BigDecimal getBannerPage() {
		return bannerPage;
	}
	
	public void setBannerLocation(String value) {
		this.bannerLocation = value;
	}
	
	public String getBannerLocation() {
		return bannerLocation;
	}
	
	public void setBannerImagPath1(String value) {
		this.bannerImagPath1 = value;
	}
	
	public String getBannerImagPath1() {
		return bannerImagPath1;
	}
	
	public void setBannerImagPath2(String value) {
		this.bannerImagPath2 = value;
	}
	
	public String getBannerImagPath2() {
		return bannerImagPath2;
	}
	
	public void setBannerImagPath3(String value) {
		this.bannerImagPath3 = value;
	}
	
	public String getBannerImagPath3() {
		return bannerImagPath3;
	}
	
	public void setBannerImagPath4(String value) {
		this.bannerImagPath4 = value;
	}
	
	public String getBannerImagPath4() {
		return bannerImagPath4;
	}
	
	public void setBannerImagPath5(String value) {
		this.bannerImagPath5 = value;
	}
	
	public String getBannerImagPath5() {
		return bannerImagPath5;
	}
	
	public void setBannerActFromDate(java.sql.Timestamp value) {
		this.bannerActFromDate = value;
	}
	
	public java.sql.Timestamp getBannerActFromDate() {
		return bannerActFromDate;
	}
	
	public void setIsActive(int value) {
		this.isActive = value;
	}
	
	public int getIsActive() {
		return isActive;
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
	
	public String toString() {
		return String.valueOf(getBannerID());
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
