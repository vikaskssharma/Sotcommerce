/**
 * 
 */
package com.sot.ecommerce.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author diksha.rattan
 *
 */
public class UploadForm {
	
	private String fileName = null;
	private CommonsMultipartFile  fileData = null;
	private String hiddenData = "";
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileData
	 */
	public CommonsMultipartFile  getFileData() {
		return fileData;
	}
	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(CommonsMultipartFile  fileData) {
		this.fileData = fileData;
	}
	public String getHiddenData() {
		return hiddenData;
	}
	public void setHiddenData(String hiddenData) {
		this.hiddenData = hiddenData;
	}
	
	

}
