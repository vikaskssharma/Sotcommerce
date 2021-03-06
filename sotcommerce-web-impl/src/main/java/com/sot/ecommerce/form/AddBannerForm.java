/**
 * 
 */
package com.sot.ecommerce.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sbsc.fos.promotion.commons.PromotionRuleCodeType;
import com.sbsc.fos.promotion.web.form.CategoryAjaxForm;
import com.sbsc.fos.promotion.web.form.ProductAjaxForm;

/**
 * @author vaibhav.jain
 * 
 */
@Component
public class AddBannerForm {

	private Long bannerId = null;
	
	private Long storeId = null;

	private String bannerName = null;

	private String bannerDescription = null;
	
	private String bannerApplyTo = "C";

	private String bannerPageName = null;

	private String bannerPagePositionName = null;
	
	private String bannerCategoryId = null;

	private List<CategoryAjaxForm> categoryAjaxForms;
	
	private List<ProductAjaxForm> productAjaxForms;	
	
	private List<BannerCategoryAjaxForm> bannerCategoryAjaxForms;	
	
	private List<String> applyToPages;	
	
	private List<String> applyToPagePositions;	

	private String status = null;
	
	private Map<String, String> allStatuses;

	private String fromDate = null;

	private String toDate = null;

	private String mode = null;
	
	private Long bnrLnkId1;	
	
	private Long bnrLnkId2;
	
	private Long bnrLnkId3;	
	
	private Long bnrLnkId4;
	
	private Long bnrLnkId5;	
	
	private String prodImgPath1  = "";	
	
	private String prodImgPath2 = "";
	
	private String prodImgPath3 = "";	
	
	private String prodImgPath4 = "";
	
	private String prodImgPath5 = "";	
	
	private String noImageError;
	
	private BigDecimal categoryImg1;	
	
	private BigDecimal categoryImg2;
	
	private BigDecimal categoryImg3;	
	
	private BigDecimal categoryImg4;
	
	private BigDecimal categoryImg5;	
	
	private BigDecimal productImg1;	
	
	private BigDecimal productImg2;
	
	private BigDecimal productImg3;	
	
	private BigDecimal productImg4;
	
	private BigDecimal productImg5;	
	
	private BigDecimal categoryImg;
	
	private BigDecimal productImg;	
	
	private String pageMode = "";
	
	private Boolean isFromDateDisabled;
	
	private Boolean isToDateDisabled;
	

	public AddBannerForm() {
	}
	
	/**
	 * @param bannerId
	 * @param storeId
	 * @param bannerName
	 * @param bannerDescription
	 * @param bannerApplyTo
	 * @param bannerPageName
	 * @param bannerPagePositionName
	 * @param bannerCategoryId
	 * @param categoryAjaxForms
	 * @param productAjaxForms
	 * @param bannerCategoryAjaxForms
	 * @param applyToPages
	 * @param applyToPagePositions
	 * @param status
	 * @param allStatuses
	 * @param fromDate
	 * @param toDate
	 * @param mode
	 * @param bnrLnkId1
	 * @param bnrLnkId2
	 * @param bnrLnkId3
	 * @param bnrLnkId4
	 * @param bnrLnkId5
	 * @param prodImgPath1
	 * @param prodImgPath2
	 * @param prodImgPath3
	 * @param prodImgPath4
	 * @param prodImgPath5
	 * @param categoryImg1
	 * @param categoryImg2
	 * @param categoryImg3
	 * @param categoryImg4
	 * @param categoryImg5
	 * @param productImg1
	 * @param productImg2
	 * @param productImg3
	 * @param productImg4
	 * @param productImg5
	 * @param pageMode
	 */
	public AddBannerForm(Long bannerId, Long storeId, String bannerName,
			String bannerDescription, String bannerApplyTo,
			String bannerPageName, String bannerPagePositionName,
			String bannerCategoryId, List<CategoryAjaxForm> categoryAjaxForms,
			List<ProductAjaxForm> productAjaxForms,
			List<BannerCategoryAjaxForm> bannerCategoryAjaxForms,
			List<String> applyToPages, List<String> applyToPagePositions,
			String status, Map<String, String> allStatuses, String fromDate,
			String toDate, String mode, Long bnrLnkId1, Long bnrLnkId2,
			Long bnrLnkId3, Long bnrLnkId4, Long bnrLnkId5,
			String prodImgPath1, String prodImgPath2, String prodImgPath3,
			String prodImgPath4, String prodImgPath5, BigDecimal categoryImg1,
			BigDecimal categoryImg2, BigDecimal categoryImg3,
			BigDecimal categoryImg4, BigDecimal categoryImg5,
			BigDecimal productImg1, BigDecimal productImg2,
			BigDecimal productImg3, BigDecimal productImg4,
			BigDecimal productImg5, String pageMode) {
		super();
		this.bannerId = bannerId;
		this.storeId = storeId;
		this.bannerName = bannerName;
		this.bannerDescription = bannerDescription;
		this.bannerApplyTo = bannerApplyTo;
		this.bannerPageName = bannerPageName;
		this.bannerPagePositionName = bannerPagePositionName;
		this.bannerCategoryId = bannerCategoryId;
		this.categoryAjaxForms = categoryAjaxForms;
		this.productAjaxForms = productAjaxForms;
		this.bannerCategoryAjaxForms = bannerCategoryAjaxForms;
		this.applyToPages = applyToPages;
		this.applyToPagePositions = applyToPagePositions;
		this.status = status;
		this.allStatuses = allStatuses;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.mode = mode;
		this.bnrLnkId1 = bnrLnkId1;
		this.bnrLnkId2 = bnrLnkId2;
		this.bnrLnkId3 = bnrLnkId3;
		this.bnrLnkId4 = bnrLnkId4;
		this.bnrLnkId5 = bnrLnkId5;
		this.prodImgPath1 = prodImgPath1;
		this.prodImgPath2 = prodImgPath2;
		this.prodImgPath3 = prodImgPath3;
		this.prodImgPath4 = prodImgPath4;
		this.prodImgPath5 = prodImgPath5;
		this.categoryImg1 = categoryImg1;
		this.categoryImg2 = categoryImg2;
		this.categoryImg3 = categoryImg3;
		this.categoryImg4 = categoryImg4;
		this.categoryImg5 = categoryImg5;
		this.productImg1 = productImg1;
		this.productImg2 = productImg2;
		this.productImg3 = productImg3;
		this.productImg4 = productImg4;
		this.productImg5 = productImg5;
		this.pageMode = pageMode;
	}

	public AddBannerForm(List<CategoryAjaxForm> categoryAjaxForm,
			List<ProductAjaxForm> productAjaxForms,
			List<BannerCategoryAjaxForm> bannerCategoryAjaxForms,
			List<String> applyToPages, 
			List<String> applyToPagePositions){
		this.setCategoryAjaxForms(categoryAjaxForm);
		this.setProductAjaxForms(productAjaxForms);
		this.applyToPages = applyToPages;
		this.applyToPagePositions = applyToPagePositions;
	}


	/**
	 * @return the bannerId
	 */
	public Long getBannerId() {
		return bannerId;
	}






	/**
	 * @param bannerId the bannerId to set
	 */
	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}


	/**
	 * @return the storeId
	 */
	public Long getStoreId() {
		return storeId;
	}


	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}


	/**
	 * @return the bannerName
	 */
	public String getBannerName() {
		return bannerName;
	}


	/**
	 * @param bannerName the bannerName to set
	 */
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}


	/**
	 * @return the bannerDescription
	 */
	public String getBannerDescription() {
		return bannerDescription;
	}


	/**
	 * @param bannerDescription the bannerDescription to set
	 */
	public void setBannerDescription(String bannerDescription) {
		this.bannerDescription = bannerDescription;
	}


	/**
	 * @return the bannerPageName
	 */
	public String getBannerPageName() {
		return bannerPageName;
	}


	/**
	 * @param bannerPageName the bannerPageName to set
	 */
	public void setBannerPageName(String bannerPageName) {
		this.bannerPageName = bannerPageName;
	}


	/**
	 * @return the categoryAjaxForms
	 */
	public List<CategoryAjaxForm> getCategoryAjaxForms() {
		return categoryAjaxForms;
	}


	/**
	 * @param categoryAjaxForms the categoryAjaxForms to set
	 */
	public void setCategoryAjaxForms(List<CategoryAjaxForm> categoryAjaxForms) {
		this.categoryAjaxForms = categoryAjaxForms;
	}


	/**
	 * @return the productAjaxForms
	 */
	public List<ProductAjaxForm> getProductAjaxForms() {
		return productAjaxForms;
	}


	/**
	 * @param productAjaxForms the productAjaxForms to set
	 */
	public void setProductAjaxForms(List<ProductAjaxForm> productAjaxForms) {
		this.productAjaxForms = productAjaxForms;
	}


	/**
	 * @return the bannerCategoryAjaxForms
	 */
	public List<BannerCategoryAjaxForm> getBannerCategoryAjaxForms() {
		return bannerCategoryAjaxForms;
	}


	/**
	 * @param bannerCategoryAjaxForms the bannerCategoryAjaxForms to set
	 */
	public void setBannerCategoryAjaxForms(
			List<BannerCategoryAjaxForm> bannerCategoryAjaxForms) {
		this.bannerCategoryAjaxForms = bannerCategoryAjaxForms;
	}


	/**
	 * @return the applyToPages
	 */
	public List<String> getApplyToPages() {
		return applyToPages;
	}


	/**
	 * @param applyToPages the applyToPages to set
	 */
	public void setApplyToPages(List<String> applyToPages) {
		this.applyToPages = applyToPages;
	}


	/**
	 * @return the applyToPagePositions
	 */
	public List<String> getApplyToPagePositions() {
		return applyToPagePositions;
	}


	/**
	 * @param applyToPagePositions the applyToPagePositions to set
	 */
	public void setApplyToPagePositions(List<String> applyToPagePositions) {
		this.applyToPagePositions = applyToPagePositions;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the allStatuses
	 */
	public Map<String, String> getAllStatuses() {
		return allStatuses;
	}


	/**
	 * @param allStatuses the allStatuses to set
	 */
	public void setAllStatuses(Map<String, String> allStatuses) {
		this.allStatuses = allStatuses;
	}


	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}


	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}


	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	/**
	 * @return the prodImgPath1
	 */
	public String getProdImgPath1() {
		return prodImgPath1;
	}


	/**
	 * @param prodImgPath1 the prodImgPath1 to set
	 */
	public void setProdImgPath1(String prodImgPath1) {
		this.prodImgPath1 = prodImgPath1;
	}


	/**
	 * @return the prodImgPath2
	 */
	public String getProdImgPath2() {
		return prodImgPath2;
	}


	/**
	 * @param prodImgPath2 the prodImgPath2 to set
	 */
	public void setProdImgPath2(String prodImgPath2) {
		this.prodImgPath2 = prodImgPath2;
	}


	/**
	 * @return the prodImgPath3
	 */
	public String getProdImgPath3() {
		return prodImgPath3;
	}


	/**
	 * @param prodImgPath3 the prodImgPath3 to set
	 */
	public void setProdImgPath3(String prodImgPath3) {
		this.prodImgPath3 = prodImgPath3;
	}


	/**
	 * @return the prodImgPath4
	 */
	public String getProdImgPath4() {
		return prodImgPath4;
	}


	/**
	 * @param prodImgPath4 the prodImgPath4 to set
	 */
	public void setProdImgPath4(String prodImgPath4) {
		this.prodImgPath4 = prodImgPath4;
	}


	/**
	 * @return the prodImgPath5
	 */
	public String getProdImgPath5() {
		return prodImgPath5;
	}


	/**
	 * @param prodImgPath5 the prodImgPath5 to set
	 */
	public void setProdImgPath5(String prodImgPath5) {
		this.prodImgPath5 = prodImgPath5;
	}




	/**
	 * @return the bannerApplyTo
	 */
	public String getBannerApplyTo() {
		return bannerApplyTo;
	}




	/**
	 * @param bannerApplyTo the bannerApplyTo to set
	 */
	public void setBannerApplyTo(String bannerApplyTo) {
		this.bannerApplyTo = bannerApplyTo;
	}




	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}





	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}





	/**
	 * @return the pageMode
	 */
	public String getPageMode() {
		return pageMode;
	}





	/**
	 * @param pageMode the pageMode to set
	 */
	public void setPageMode(String pageMode) {
		this.pageMode = pageMode;
	}





	/**
	 * @return the bannerCategoryId
	 */
	public String getBannerCategoryId() {
		return bannerCategoryId;
	}





	/**
	 * @param bannerCategoryId the bannerCategoryId to set
	 */
	public void setBannerCategoryId(String bannerCategoryId) {
		this.bannerCategoryId = bannerCategoryId;
	}


	/**
	 * @return the categoryImg1
	 */
	public BigDecimal getCategoryImg1() {
		return categoryImg1;
	}


	/**
	 * @param categoryImg1 the categoryImg1 to set
	 */
	public void setCategoryImg1(BigDecimal categoryImg1) {
		this.categoryImg1 = categoryImg1;
	}


	/**
	 * @return the categoryImg2
	 */
	public BigDecimal getCategoryImg2() {
		return categoryImg2;
	}


	/**
	 * @param categoryImg2 the categoryImg2 to set
	 */
	public void setCategoryImg2(BigDecimal categoryImg2) {
		this.categoryImg2 = categoryImg2;
	}


	/**
	 * @return the categoryImg3
	 */
	public BigDecimal getCategoryImg3() {
		return categoryImg3;
	}


	/**
	 * @param categoryImg3 the categoryImg3 to set
	 */
	public void setCategoryImg3(BigDecimal categoryImg3) {
		this.categoryImg3 = categoryImg3;
	}


	/**
	 * @return the categoryImg4
	 */
	public BigDecimal getCategoryImg4() {
		return categoryImg4;
	}


	/**
	 * @param categoryImg4 the categoryImg4 to set
	 */
	public void setCategoryImg4(BigDecimal categoryImg4) {
		this.categoryImg4 = categoryImg4;
	}


	/**
	 * @return the categoryImg5
	 */
	public BigDecimal getCategoryImg5() {
		return categoryImg5;
	}


	/**
	 * @param categoryImg5 the categoryImg5 to set
	 */
	public void setCategoryImg5(BigDecimal categoryImg5) {
		this.categoryImg5 = categoryImg5;
	}


	/**
	 * @return the productImg1
	 */
	public BigDecimal getProductImg1() {
		return productImg1;
	}


	/**
	 * @param productImg1 the productImg1 to set
	 */
	public void setProductImg1(BigDecimal productImg1) {
		this.productImg1 = productImg1;
	}


	/**
	 * @return the productImg2
	 */
	public BigDecimal getProductImg2() {
		return productImg2;
	}


	/**
	 * @param productImg2 the productImg2 to set
	 */
	public void setProductImg2(BigDecimal productImg2) {
		this.productImg2 = productImg2;
	}


	/**
	 * @return the productImg3
	 */
	public BigDecimal getProductImg3() {
		return productImg3;
	}


	/**
	 * @param productImg3 the productImg3 to set
	 */
	public void setProductImg3(BigDecimal productImg3) {
		this.productImg3 = productImg3;
	}


	/**
	 * @return the productImg4
	 */
	public BigDecimal getProductImg4() {
		return productImg4;
	}


	/**
	 * @param productImg4 the productImg4 to set
	 */
	public void setProductImg4(BigDecimal productImg4) {
		this.productImg4 = productImg4;
	}


	/**
	 * @return the productImg5
	 */
	public BigDecimal getProductImg5() {
		return productImg5;
	}


	/**
	 * @param productImg5 the productImg5 to set
	 */
	public void setProductImg5(BigDecimal productImg5) {
		this.productImg5 = productImg5;
	}


	/**
	 * @return the bannerPagePositionName
	 */
	public String getBannerPagePositionName() {
		return bannerPagePositionName;
	}


	/**
	 * @param bannerPagePositionName the bannerPagePositionName to set
	 */
	public void setBannerPagePositionName(String bannerPagePositionName) {
		this.bannerPagePositionName = bannerPagePositionName;
	}



	/**
	 * @return the bnrLnkId1
	 */
	public Long getBnrLnkId1() {
		return bnrLnkId1;
	}



	/**
	 * @param bnrLnkId1 the bnrLnkId1 to set
	 */
	public void setBnrLnkId1(Long bnrLnkId1) {
		this.bnrLnkId1 = bnrLnkId1;
	}



	/**
	 * @return the bnrLnkId2
	 */
	public Long getBnrLnkId2() {
		return bnrLnkId2;
	}



	/**
	 * @param bnrLnkId2 the bnrLnkId2 to set
	 */
	public void setBnrLnkId2(Long bnrLnkId2) {
		this.bnrLnkId2 = bnrLnkId2;
	}



	/**
	 * @return the bnrLnkId3
	 */
	public Long getBnrLnkId3() {
		return bnrLnkId3;
	}



	/**
	 * @param bnrLnkId3 the bnrLnkId3 to set
	 */
	public void setBnrLnkId3(Long bnrLnkId3) {
		this.bnrLnkId3 = bnrLnkId3;
	}



	/**
	 * @return the bnrLnkId4
	 */
	public Long getBnrLnkId4() {
		return bnrLnkId4;
	}



	/**
	 * @param bnrLnkId4 the bnrLnkId4 to set
	 */
	public void setBnrLnkId4(Long bnrLnkId4) {
		this.bnrLnkId4 = bnrLnkId4;
	}



	/**
	 * @return the bnrLnkId5
	 */
	public Long getBnrLnkId5() {
		return bnrLnkId5;
	}



	/**
	 * @param bnrLnkId5 the bnrLnkId5 to set
	 */
	public void setBnrLnkId5(Long bnrLnkId5) {
		this.bnrLnkId5 = bnrLnkId5;
	}

	/**
	 * @return the noImageError
	 */
	public String getNoImageError() {
		return noImageError;
	}

	/**
	 * @param noImageError the noImageError to set
	 */
	public void setNoImageError(String noImageError) {
		this.noImageError = noImageError;
	}

	/**
	 * @return the isFromDateDisabled
	 */
	public Boolean getIsFromDateDisabled() {
		return isFromDateDisabled;
	}

	/**
	 * @param isFromDateDisabled the isFromDateDisabled to set
	 */
	public void setIsFromDateDisabled(Boolean isFromDateDisabled) {
		this.isFromDateDisabled = isFromDateDisabled;
	}

	/**
	 * @return the isToDateDisabled
	 */
	public Boolean getIsToDateDisabled() {
		return isToDateDisabled;
	}

	/**
	 * @param isToDateDisabled the isToDateDisabled to set
	 */
	public void setIsToDateDisabled(Boolean isToDateDisabled) {
		this.isToDateDisabled = isToDateDisabled;
	}

	/**
	 * @return the categoryImg
	 */
	public BigDecimal getCategoryImg() {
		return categoryImg;
	}

	/**
	 * @param categoryImg the categoryImg to set
	 */
	public void setCategoryImg(BigDecimal categoryImg) {
		this.categoryImg = categoryImg;
	}

	/**
	 * @return the productImg
	 */
	public BigDecimal getProductImg() {
		return productImg;
	}

	/**
	 * @param productImg the productImg to set
	 */
	public void setProductImg(BigDecimal productImg) {
		this.productImg = productImg;
	}


}
