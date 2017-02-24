/**
 * 
 */
package com.sot.ecommerce.form;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

/**
 * @author diksha.rattan
 *
 */
@Component
public class RelatedProductForm {
	
	
	private long relatedId = 0; 
	
	
	private long productId = 0;
	
	private String productName = "";	
	
	private long relatedProductId = 0;	
	
	private String relatedProductName = "";	
	
	private int isDeleted = 0;
	
	private String mode = "";


	/**
	 * @return the relatedId
	 */
	public long getRelatedId() {
		return relatedId;
	}


	/**
	 * @param relatedId the relatedId to set
	 */
	public void setRelatedId(long relatedId) {
		this.relatedId = relatedId;
	}


	/**
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}


	/**
	 * @param productId the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}


	/**
	 * @return the relatedProductId
	 */
	public long getRelatedProductId() {
		return relatedProductId;
	}


	/**
	 * @param relatedProductId the relatedProductId to set
	 */
	public void setRelatedProductId(long relatedProductId) {
		this.relatedProductId = relatedProductId;
	}


	/**
	 * @return the isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}


	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}


	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}


	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}


	/**
	 * @return the relatedProductName
	 */
	public String getRelatedProductName() {
		return relatedProductName;
	}


	/**
	 * @param relatedProductName the relatedProductName to set
	 */
	public void setRelatedProductName(String relatedProductName) {
		this.relatedProductName = relatedProductName;
	}


	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}


	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	

}
