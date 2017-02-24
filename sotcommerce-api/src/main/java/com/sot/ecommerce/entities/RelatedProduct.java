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
@Table(name="RELATED_PRODUCT_TB_DTL")
public class RelatedProduct implements Serializable {
	public RelatedProduct() {
	}
	
	@Column(name="NM_RLTD_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_RELATEDPRODUCT_RELEATEDPRODUCTID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_RELATEDPRODUCT_RELEATEDPRODUCTID_GENERATOR", strategy="native")	
	private java.math.BigDecimal releatedProductId;
	
	@ManyToOne(targetEntity=Product.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_PRD_ID", referencedColumnName="NM_PRD_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Product NM_PRD;
	
	@ManyToOne(targetEntity=Product.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_RLTD_PRD_ID", referencedColumnName="NM_PRD_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Product NM_RLTD_PRD;
	
	@Column(name="IS_DLTD", nullable=false, length=1)	
	private int isDeleted;
	
	public void setReleatedProductId(java.math.BigDecimal value) {
		this.releatedProductId = value;
	}
	
	public java.math.BigDecimal getReleatedProductId() {
		return releatedProductId;
	}
	
	public java.math.BigDecimal getORMID() {
		return getReleatedProductId();
	}
	
	public void setIsDeleted(int value) {
		this.isDeleted = value;
	}
	
	public int getIsDeleted() {
		return isDeleted;
	}
	
	public void setNM_PRD(Product value) {
		this.NM_PRD = value;
	}
	
	public Product getNM_PRD() {
		return NM_PRD;
	}
	
	public void setNM_RLTD_PRD(Product value) {
		this.NM_RLTD_PRD = value;
	}
	
	public Product getNM_RLTD_PRD() {
		return NM_RLTD_PRD;
	}
	
	public String toString() {
		return String.valueOf(getReleatedProductId());
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
