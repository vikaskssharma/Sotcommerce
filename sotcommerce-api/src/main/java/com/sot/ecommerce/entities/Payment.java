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
@Table(name="PAYMENT_TB_MASTER")
public class Payment implements Serializable {
	public Payment() {
	}
	
	@Column(name="NM_PYMNT_MD_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_PAYMENT_PAYMENTMODEID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_PAYMENT_PAYMENTMODEID_GENERATOR", strategy="native")	
	private java.math.BigDecimal paymentModeID;
	
	@Column(name="VC_PYMNT_MD", nullable=false, length=32)	
	private String paymentMode;
	
	@Column(name="VC_PYMNT_DSCR", nullable=true, length=256)	
	private String paymentDesc;
	
	@OneToMany(mappedBy="NM_PYMNT_MD", targetEntity=Order.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_TB_MASTER = new java.util.HashSet();
	
	public void setPaymentModeID(java.math.BigDecimal value) {
		this.paymentModeID = value;
	}
	
	public java.math.BigDecimal getPaymentModeID() {
		return paymentModeID;
	}
	
	public java.math.BigDecimal getORMID() {
		return getPaymentModeID();
	}
	
	public void setPaymentMode(String value) {
		this.paymentMode = value;
	}
	
	public String getPaymentMode() {
		return paymentMode;
	}
	
	public void setPaymentDesc(String value) {
		this.paymentDesc = value;
	}
	
	public String getPaymentDesc() {
		return paymentDesc;
	}
	
	public void setORDER_TB_MASTER(java.util.Set value) {
		this.ORDER_TB_MASTER = value;
	}
	
	public java.util.Set getORDER_TB_MASTER() {
		return ORDER_TB_MASTER;
	}
	
	
	public String toString() {
		return String.valueOf(getPaymentModeID());
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
