package com.sot.ecommerce.form;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class AdminProfileForm {
	
	
	@NotNull
	@Max(16)	
	private String userFirstName="";
	
	@Max(16)
	private String userLastName="";
	
	@NotNull
	@Max(10)	
	private String userPhone="";
	
	@NotNull
	@Max(16)
	private String userEmail="";
	
	@Max(16)
	private String city="";
	
	private String phone="";
	
	private String address="";
	
	@Max(8)
	private String state="";
	
	@Max(8)
	private String zipcode="";
	
	private long countryId=0;
	
	private String pagetype;
	
	
	/*private BigDecimal selectedCountryId=new BigDecimal(0);*/
	
		
	private String errorMessage;
	
	
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(long id) {
		this.countryId = id;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPagetype() {
		return pagetype;
	}
	public void setPagetype(String pagetype) {
		this.pagetype = pagetype;
	}
	
	

}
