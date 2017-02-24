package com.sot.ecommerce.form;

public class ForgetPasswordForm {
	
	
	private String email="";
	
	private String errorMessage="";
	
	private String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public void clear()
	{
		this.email ="";
		
		this.role="";
		this.errorMessage="";
	}

}
