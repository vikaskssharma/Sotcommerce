package com.sot.ecommerce.vo;

import java.util.Calendar;
import java.util.Date;

public class NewsLetterManagementVO {

	public long newsLttrID;

	
	public String newsLetterTitle;

	public Date publishDate;

	public Date createdDate;
	
	public String status;
	
	public String newsletterImgPath;
	
	public String newsLetterDescr;
	

	
	public long getNewsLttrID() {
		return newsLttrID;
	}

	public void setNewsLttrID(long newsLttrID) {
		this.newsLttrID = newsLttrID;
	}



	public String getNewsLetterTitle() {
		return newsLetterTitle;
	}

	public void setNewsLetterTitle(String newsLetterTitle) {
		this.newsLetterTitle = newsLetterTitle;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the publishDate
	 */
	public Date getPublishDate() {
		return publishDate;
	}

	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getNewsletterImgPath() {
		return newsletterImgPath;
	}

	public void setNewsletterImgPath(String newsletterImgPath) {
		this.newsletterImgPath = newsletterImgPath;
	}

	public String getNewsLetterDescr() {
		return newsLetterDescr;
	}

	public void setNewsLetterDescr(String newsLetterDescr) {
		this.newsLetterDescr = newsLetterDescr;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	
}
