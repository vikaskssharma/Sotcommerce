package com.sot.ecommerce.form.validator;

import org.springframework.stereotype.Component;

@Component
public class TrackOrderForm {

	String orderId;
	
	String orderNumber;

	String email;
	
	Boolean orderNumValidatedForEmail;
	
	String invalidOrderNumber;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	

	public String getInvalidOrderNumber() {
		return invalidOrderNumber;
	}

	public void setInvalidOrderNumber(String invalidOrderNumber) {
		this.invalidOrderNumber = invalidOrderNumber;
	}

	public Boolean getOrderNumValidatedForEmail() {
		return orderNumValidatedForEmail;
	}

	public void setOrderNumValidatedForEmail(Boolean orderNumValidatedForEmail) {
		this.orderNumValidatedForEmail = orderNumValidatedForEmail;
	}

	
}
