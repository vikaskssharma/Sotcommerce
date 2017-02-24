package com.sot.ecommerce.form;

import org.springframework.stereotype.Component;


@Component
public class ChangePasswordForm {
	
	private String oldPassword="";
	private String password="";
	private String confirmPassword="";
	private String errorMessage;
    private String userPassword="";
    private String email="";
  
	
    public void clear()
    {
    	this.confirmPassword="";
    	this.password="";
    	this.oldPassword="";
    }
    
    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
