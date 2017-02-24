/**
 * 
 */
package com.sot.ecommerce.form;

/**
 * @author vaibhav.jain
 *
 */
public class BannerCategoryAjaxForm {
	
	private String bannerCategoryName = "";
	
	private long bannerCategoryID = 0;

	/**
	 * @param bannerCategoryName
	 * @param bannerCategoryID
	 */
	public BannerCategoryAjaxForm(String bannerCategoryName,
			long bannerCategoryID) {
		super();
		this.bannerCategoryName = bannerCategoryName;
		this.bannerCategoryID = bannerCategoryID;
	}

	/**
	 * @return the bannerCategoryName
	 */
	public String getBannerCategoryName() {
		return bannerCategoryName;
	}

	/**
	 * @param bannerCategoryName the bannerCategoryName to set
	 */
	public void setBannerCategoryName(String bannerCategoryName) {
		this.bannerCategoryName = bannerCategoryName;
	}

	/**
	 * @return the bannerCategoryID
	 */
	public long getBannerCategoryID() {
		return bannerCategoryID;
	}

	/**
	 * @param bannerCategoryID the bannerCategoryID to set
	 */
	public void setBannerCategoryID(long bannerCategoryID) {
		this.bannerCategoryID = bannerCategoryID;
	}	


	
}
