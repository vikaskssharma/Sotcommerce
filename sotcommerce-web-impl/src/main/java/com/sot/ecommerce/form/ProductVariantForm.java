/**
 * 
 */
package com.sot.ecommerce.form;

import java.math.BigDecimal;

import javax.persistence.Column;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author diksha.rattan
 * 
 */
@Component
@Scope("prototype")
public class ProductVariantForm {

	private long nmVrntId;

	private String vcVrntImg1;

	private String vcVrntImg2;

	private String vcVrntImg3;

	private String vcVrntOptn1;

	private String vcVrntOptn2;

	private String vcVrntOptn3;

	private String vcVrntOptn4;

	private String vcVrntOptn5;

	private String vcVrntNm = "";

	private long productId = 0;;

	private String mode = "";

	private long isDltd = 0;	
	
	private String vcStts;	
	
	
	String errorMessage = "";
	
	private Boolean isAllImageDeleted;

	private String nmDlPrc = "";
	private String nmSp = "";
	private String nmVrntOptn1;
	private String nmVrntOptn2;
	private String nmVrntOptn3;
	private String nmVrntOptn4;
	private String nmVrntOptn5;
	private String attrUnitValue1 = "";
	private String attrUnitValue2 = "";
	private String attrUnitValue3 = "";
	private String attrUnitValue4 = "";
	private String attrUnitValue5 = "";
	private String nmQntty = "";

	/**
	 * @return the nmVrntId
	 */
	public long getNmVrntId() {
		return nmVrntId;
	}

	/**
	 * @param nmVrntId
	 *            the nmVrntId to set
	 */
	public void setNmVrntId(long nmVrntId) {
		this.nmVrntId = nmVrntId;
	}

	/**
	 * @return the vcVrntImg1
	 */
	public String getVcVrntImg1() {
		return vcVrntImg1;
	}

	/**
	 * @param vcVrntImg1
	 *            the vcVrntImg1 to set
	 */
	public void setVcVrntImg1(String vcVrntImg1) {
		this.vcVrntImg1 = vcVrntImg1;
	}

	/**
	 * @return the vcVrntImg2
	 */
	public String getVcVrntImg2() {
		return vcVrntImg2;
	}

	/**
	 * @param vcVrntImg2
	 *            the vcVrntImg2 to set
	 */
	public void setVcVrntImg2(String vcVrntImg2) {
		this.vcVrntImg2 = vcVrntImg2;
	}

	/**
	 * @return the vcVrntImg3
	 */
	public String getVcVrntImg3() {
		return vcVrntImg3;
	}

	/**
	 * @param vcVrntImg3
	 *            the vcVrntImg3 to set
	 */
	public void setVcVrntImg3(String vcVrntImg3) {
		this.vcVrntImg3 = vcVrntImg3;
	}

	/**
	 * @return the vcVrntOptn1
	 */
	public String getVcVrntOptn1() {
		return vcVrntOptn1;
	}

	/**
	 * @param vcVrntOptn1
	 *            the vcVrntOptn1 to set
	 */
	public void setVcVrntOptn1(String vcVrntOptn1) {
		this.vcVrntOptn1 = vcVrntOptn1;
	}

	/**
	 * @return the vcVrntOptn2
	 */
	public String getVcVrntOptn2() {
		return vcVrntOptn2;
	}

	/**
	 * @param vcVrntOptn2
	 *            the vcVrntOptn2 to set
	 */
	public void setVcVrntOptn2(String vcVrntOptn2) {
		this.vcVrntOptn2 = vcVrntOptn2;
	}

	/**
	 * @return the vcVrntOptn3
	 */
	public String getVcVrntOptn3() {
		return vcVrntOptn3;
	}

	/**
	 * @param vcVrntOptn3
	 *            the vcVrntOptn3 to set
	 */
	public void setVcVrntOptn3(String vcVrntOptn3) {
		this.vcVrntOptn3 = vcVrntOptn3;
	}

	/**
	 * @return the vcVrntOptn4
	 */
	public String getVcVrntOptn4() {
		return vcVrntOptn4;
	}

	/**
	 * @param vcVrntOptn4
	 *            the vcVrntOptn4 to set
	 */
	public void setVcVrntOptn4(String vcVrntOptn4) {
		this.vcVrntOptn4 = vcVrntOptn4;
	}

	/**
	 * @return the vcVrntOptn5
	 */
	public String getVcVrntOptn5() {
		return vcVrntOptn5;
	}

	/**
	 * @param vcVrntOptn5
	 *            the vcVrntOptn5 to set
	 */
	public void setVcVrntOptn5(String vcVrntOptn5) {
		this.vcVrntOptn5 = vcVrntOptn5;
	}

	/**
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getVcVrntNm() {
		return vcVrntNm;
	}

	public void setVcVrntNm(String vcVrntNm) {
		this.vcVrntNm = vcVrntNm;
	}

	/**
	 * @return the isDltd
	 */
	public long getIsDltd() {
		return isDltd;
	}

	/**
	 * @param isDltd
	 *            the isDltd to set
	 */
	public void setIsDltd(long isDltd) {
		this.isDltd = isDltd;
	}

	/**
	 * @return the nmDlPrc
	 */
	public String getNmDlPrc() {
		return nmDlPrc;
	}

	/**
	 * @param nmDlPrc
	 *            the nmDlPrc to set
	 */
	public void setNmDlPrc(String nmDlPrc) {
		this.nmDlPrc = nmDlPrc;
	}

	/**
	 * @return the nmSp
	 */
	public String getNmSp() {
		return nmSp;
	}

	/**
	 * @param nmSp
	 *            the nmSp to set
	 */
	public void setNmSp(String nmSp) {
		this.nmSp = nmSp;
	}

	/**
	 * @return the nmVrntOptn1
	 */
	public String getNmVrntOptn1() {
		return nmVrntOptn1;
	}

	/**
	 * @param nmVrntOptn1
	 *            the nmVrntOptn1 to set
	 */
	public void setNmVrntOptn1(String nmVrntOptn1) {
		this.nmVrntOptn1 = nmVrntOptn1;
	}

	/**
	 * @return the nmVrntOptn2
	 */
	public String getNmVrntOptn2() {
		return nmVrntOptn2;
	}

	/**
	 * @param nmVrntOptn2
	 *            the nmVrntOptn2 to set
	 */
	public void setNmVrntOptn2(String nmVrntOptn2) {
		this.nmVrntOptn2 = nmVrntOptn2;
	}

	/**
	 * @return the nmVrntOptn3
	 */
	public String getNmVrntOptn3() {
		return nmVrntOptn3;
	}

	/**
	 * @param nmVrntOptn3
	 *            the nmVrntOptn3 to set
	 */
	public void setNmVrntOptn3(String nmVrntOptn3) {
		this.nmVrntOptn3 = nmVrntOptn3;
	}

	/**
	 * @return the nmVrntOptn4
	 */
	public String getNmVrntOptn4() {
		return nmVrntOptn4;
	}

	/**
	 * @param nmVrntOptn4
	 *            the nmVrntOptn4 to set
	 */
	public void setNmVrntOptn4(String nmVrntOptn4) {
		this.nmVrntOptn4 = nmVrntOptn4;
	}

	/**
	 * @return the nmVrntOptn5
	 */
	public String getNmVrntOptn5() {
		return nmVrntOptn5;
	}

	/**
	 * @param nmVrntOptn5
	 *            the nmVrntOptn5 to set
	 */
	public void setNmVrntOptn5(String nmVrntOptn5) {
		this.nmVrntOptn5 = nmVrntOptn5;
	}

	/**
	 * @return the attrUnitValue1
	 */
	public String getAttrUnitValue1() {
		return attrUnitValue1;
	}

	/**
	 * @param attrUnitValue1
	 *            the attrUnitValue1 to set
	 */
	public void setAttrUnitValue1(String attrUnitValue1) {
		this.attrUnitValue1 = attrUnitValue1;
	}

	/**
	 * @return the attrUnitValue2
	 */
	public String getAttrUnitValue2() {
		return attrUnitValue2;
	}

	/**
	 * @param attrUnitValue2
	 *            the attrUnitValue2 to set
	 */
	public void setAttrUnitValue2(String attrUnitValue2) {
		this.attrUnitValue2 = attrUnitValue2;
	}

	/**
	 * @return the attrUnitValue3
	 */
	public String getAttrUnitValue3() {
		return attrUnitValue3;
	}

	/**
	 * @param attrUnitValue3
	 *            the attrUnitValue3 to set
	 */
	public void setAttrUnitValue3(String attrUnitValue3) {
		this.attrUnitValue3 = attrUnitValue3;
	}

	/**
	 * @return the attrUnitValue4
	 */
	public String getAttrUnitValue4() {
		return attrUnitValue4;
	}

	/**
	 * @param attrUnitValue4
	 *            the attrUnitValue4 to set
	 */
	public void setAttrUnitValue4(String attrUnitValue4) {
		this.attrUnitValue4 = attrUnitValue4;
	}

	/**
	 * @return the attrUnitValue5
	 */
	public String getAttrUnitValue5() {
		return attrUnitValue5;
	}

	/**
	 * @param attrUnitValue5
	 *            the attrUnitValue5 to set
	 */
	public void setAttrUnitValue5(String attrUnitValue5) {
		this.attrUnitValue5 = attrUnitValue5;
	}

	/**
	 * @return the nmQntty
	 */
	public String getNmQntty() {
		return nmQntty;
	}

	/**
	 * @param nmQntty the nmQntty to set
	 */
	public void setNmQntty(String nmQntty) {
		this.nmQntty = nmQntty;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the vcStts
	 */
	public String getVcStts() {
		return vcStts;
	}

	/**
	 * @param vcStts the vcStts to set
	 */
	public void setVcStts(String vcStts) {
		this.vcStts = vcStts;
	}

	/**
	 * @return the isAllImageDeleted
	 */
	public Boolean getIsAllImageDeleted() {
		return isAllImageDeleted;
	}

	/**
	 * @param isAllImageDeleted the isAllImageDeleted to set
	 */
	public void setIsAllImageDeleted(Boolean isAllImageDeleted) {
		this.isAllImageDeleted = isAllImageDeleted;
	}
	
	
	
	

}
