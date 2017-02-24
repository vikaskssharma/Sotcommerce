package com.sot.ecommerce.form;

public class UnsubscribeNewsletterForm {

	long subId;
	
	String unsubRsn;
	
	String email;
	
	String status;

	public long getSubId() {
		return subId;
	}

	public void setSubId(long subId) {
		this.subId = subId;
	}

	public String getUnsubRsn() {
		return unsubRsn;
	}

	public void setUnsubRsn(String unsubRsn) {
		this.unsubRsn = unsubRsn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
