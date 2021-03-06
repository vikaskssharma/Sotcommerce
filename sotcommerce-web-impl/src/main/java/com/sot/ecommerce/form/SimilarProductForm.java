/**
 * 
 */
package com.sot.ecommerce.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author diksha.rattan
 *
 */
@Component
public class SimilarProductForm {
	
	
	private long similarId = 0; 
	
	
	private long productId = 0;
	
	
	private long similarProductId = 0;
	
	
	private String similarProductName = "";
	
	
	private int isDeleted = 0;
	
	private String mode = "";

	/**
	 * @return the similarId
	 */
	public long getSimilarId() {
		return similarId;
	}

	/**
	 * @param similarId the similarId to set
	 */
	public void setSimilarId(long similarId) {
		this.similarId = similarId;
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
	 * @return the similarProductId
	 */
	public long getSimilarProductId() {
		return similarProductId;
	}

	/**
	 * @param similarProductId the similarProductId to set
	 */
	public void setSimilarProductId(long similarProductId) {
		this.similarProductId = similarProductId;
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
	 * @return the similarProductName
	 */
	public String getSimilarProductName() {
		return similarProductName;
	}

	/**
	 * @param similarProductName the similarProductName to set
	 */
	public void setSimilarProductName(String similarProductName) {
		this.similarProductName = similarProductName;
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
