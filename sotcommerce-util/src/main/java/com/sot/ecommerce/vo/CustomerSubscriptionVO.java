package com.sot.ecommerce.vo;

import java.util.Date;

public class CustomerSubscriptionVO {
	
	
	
	public long subId;

	public String subEmail;

	public String status;

	public long getSubId() {
		return subId;
	}

	public void setSubId(long subId) {
		this.subId = subId;
	}

	public String getSubEmail() {
		return subEmail;
	}

	public void setSubEmail(String subEmail) {
		this.subEmail = subEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
