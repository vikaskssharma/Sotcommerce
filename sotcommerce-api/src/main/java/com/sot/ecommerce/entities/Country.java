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
@Table(name="COUNTRY_MASTER")
public class Country implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Country() {
	}
	
	@Column(name="NM_CNTRY_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SBSC_FOS_PERSISTANCE_COUNTRY_COUNTRYID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SBSC_FOS_PERSISTANCE_COUNTRY_COUNTRYID_GENERATOR", strategy="native")	
	private java.math.BigDecimal countryID;
	
	@Column(name="VC_CNTRY_NM", nullable=false, length=32)	
	private String name;
	
	@Column(name="VC_CNTRY_CD", nullable=false, length=8)	
	private String countryCode;
	
	@OneToMany(mappedBy="NM_CNTRY", targetEntity=OrderBillingDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private  Set ORDER_BILLING_TB_DTL = new  HashSet();
	
	@OneToMany(mappedBy="NM_CNTRY", targetEntity=OrderShippingDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private Set ORDER_SHIPPING_TB_DTL = new HashSet();
	
	@OneToMany(mappedBy="NM_CNTRY", targetEntity=User.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private Set UM_TB_USER = new HashSet();
	
	public void setCountryID(java.math.BigDecimal value) {
		this.countryID = value;
	}
	
	public java.math.BigDecimal getCountryID() {
		return countryID;
	}
	
	public java.math.BigDecimal getORMID() {
		return getCountryID();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCountryCode(String value) {
		this.countryCode = value;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setORDER_BILLING_TB_DTL(java.util.Set value) {
		this.ORDER_BILLING_TB_DTL = value;
	}
	
	public java.util.Set getORDER_BILLING_TB_DTL() {
		return ORDER_BILLING_TB_DTL;
	}
	
	
	public void setORDER_SHIPPING_TB_DTL(java.util.Set value) {
		this.ORDER_SHIPPING_TB_DTL = value;
	}
	
	public java.util.Set getORDER_SHIPPING_TB_DTL() {
		return ORDER_SHIPPING_TB_DTL;
	}
	
	
	public void setUM_TB_USER(java.util.Set value) {
		this.UM_TB_USER = value;
	}
	
	public java.util.Set getUM_TB_USER() {
		return UM_TB_USER;
	}
	
	
	public String toString() {
		return String.valueOf(getCountryID());
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
