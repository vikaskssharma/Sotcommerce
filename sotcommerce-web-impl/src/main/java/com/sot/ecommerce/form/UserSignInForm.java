/**
 * 
 */
package com.sot.ecommerce.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

/**
 * @author simanchal.patra
 * 
 */
@Component
public class UserSignInForm {

	@NotNull
	@Max(32)
	private String userEmail;

	@NotNull
	@Max(16)
	@Min(8)
	private String userPassword;
	
	private String rolename;
	
	private long storeId;
	
	private long rememberMe;
	

	private String errorMessage;
	
	

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(long rememberMe) {
		this.rememberMe = rememberMe;
	}

	

}
