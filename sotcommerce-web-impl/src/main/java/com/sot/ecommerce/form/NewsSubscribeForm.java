package com.sot.ecommerce.form;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Email;

public class NewsSubscribeForm {
	
	
	@Email
	private String email;
	
	private long storeId;
	
	private long newssubId;
	
	private Date createdAt;
	
	private BigDecimal isDel;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getNewssubId() {
		return newssubId;
	}

	public void setNewssubId(long newssubId) {
		this.newssubId = newssubId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public BigDecimal getIsDel() {
		return isDel;
	}

	public void setIsDel(BigDecimal isDel) {
		this.isDel = isDel;
	}

}
