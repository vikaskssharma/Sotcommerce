/**
 * 
 */
package com.sot.ecommerce.form;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

/**
 * @author simanchal.patra
 * 
 */
@Component
public class UserSignUpForm {

	private String firstName="";

	private String lastName="";
	
	private String emailID="";
	
	private String phone="";
	
	private String address="";
	private String city="";
	private String state="";
	private String zipcode="";

	
	private String password="";

	private String cnfPassword="";
	
    private String rolename="";
	
	private long storeId=0;
	 
	 
	 
	 private long countryId=0;

	private String errorMessage;

	private String successMessage;

	private String signUperrorMessage;
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}

	/**
	 * @param emailID
	 *            the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the cnfPassword
	 */
	public String getCnfPassword() {
		return cnfPassword;
	}

	/**
	 * @param cnfPassword
	 *            the cnfPassword to set
	 */
	public void setCnfPassword(String cnfPassword) {
		this.cnfPassword = cnfPassword;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the successMessage
	 */
	public String getSuccessMessage() {
		return successMessage;
	}

	/**
	 * @param successMessage
	 *            the successMessage to set
	 */
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getSignUperrorMessage() {
		return signUperrorMessage;
	}

	public void setSignUperrorMessage(String signUperrorMessage) {
		this.signUperrorMessage = signUperrorMessage;
	}

	

}
