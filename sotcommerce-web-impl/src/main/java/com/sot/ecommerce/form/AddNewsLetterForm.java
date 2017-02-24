package com.sot.ecommerce.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class AddNewsLetterForm {
	
	private long newsLttrId;
	
	private Integer mode;
	
	private String nwsLttrTtl;
	
	private String nwsLttrDescr;
	
	private String nwsLttrPath;
	
	private String publishDate;
	
	private String filename;
	
	private String status;
	
	private CommonsMultipartFile fileData;
	
	public long getNewsLttrId() {
		return newsLttrId;
	}

	public void setNewsLttrId(long newsLttrId) {
		this.newsLttrId = newsLttrId;
	}


	public String getNwsLttrTtl() {
		return nwsLttrTtl;
	}

	public void setNwsLttrTtl(String nwsLttrTtl) {
		this.nwsLttrTtl = nwsLttrTtl;
	}

	public String getNwsLttrDescr() {
		return nwsLttrDescr;
	}

	public void setNwsLttrDescr(String nwsLttrDescr) {
		this.nwsLttrDescr = nwsLttrDescr;
	}

	public String getNwsLttrPath() {
		return nwsLttrPath;
	}

	public void setNwsLttrPath(String nwsLttrPath) {
		this.nwsLttrPath = nwsLttrPath;
	}

	

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	

}
