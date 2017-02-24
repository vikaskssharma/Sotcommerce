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
@Table(name="SIMILAR_PRODUCT_TB_DTL")
public class SimilarProduct implements Serializable {
	public SimilarProduct() {
	}
	
	@Column(name="NM_SMLR_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_SIMILARPRODUCT_SIMILARPRODUCTID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_SIMILARPRODUCT_SIMILARPRODUCTID_GENERATOR", strategy="native")	
	private java.math.BigDecimal similarProductId;
	
	@ManyToOne(targetEntity=Product.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_PRD_ID", referencedColumnName="NM_PRD_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Product NM_PRD;
	
	@ManyToOne(targetEntity=Product.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_SMLR_PRD_ID", referencedColumnName="NM_PRD_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Product NM_SMLR_PRD;
	
	@Column(name="IS_DLTD", nullable=false, length=1)	
	private int isDeleted;
	
	public void setSimilarProductId(java.math.BigDecimal value) {
		this.similarProductId = value;
	}
	
	public java.math.BigDecimal getSimilarProductId() {
		return similarProductId;
	}
	
	public java.math.BigDecimal getORMID() {
		return getSimilarProductId();
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
	
	public void setNM_SMLR_PRD(Product value) {
		this.NM_SMLR_PRD = value;
	}
	
	public Product getNM_SMLR_PRD() {
		return NM_SMLR_PRD;
	}
	
	public String toString() {
		return String.valueOf(getSimilarProductId());
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
