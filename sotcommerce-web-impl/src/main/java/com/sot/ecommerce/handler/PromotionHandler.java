
package com.sot.ecommerce.handler;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.category.service.CategoryService;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.PromotionTbMaster;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sbsc.fos.product.service.ProductService;
import com.sbsc.fos.promotion.commons.PromotionVO;
import com.sbsc.fos.promotion.service.PromotionService;
import com.sbsc.fos.promotion.web.form.AddPromotionForm;
import com.sbsc.fos.promotion.web.form.CategoryAjaxForm;
import com.sbsc.fos.promotion.web.form.ProductAjaxForm;
import com.sbsc.fos.utils.DateUtil;
import com.sbsc.fos.utils.SBSConstants;

@Component
public class PromotionHandler {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(PromotionHandler.class);

	SessionInfo sessionInfo;

	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private ProductService productService;	
	
	
	@Autowired
	private CategoryService categoryService;		
	

	/**
	 * Finds promoiton List by store
	 * 
	 * 
	 * @param sessionInfo
	 * @return
	 */
	public List<PromotionVO> getAllPromotionList(SessionInfo sessionInfo) {
		
		
		return promotionService.findPromotion(sessionInfo);
	}

	/**
	 * Finds promoiton List by property
	 * 
	 * 
	 * @param sessionInfo
	 * @return
	 */
	public List<PromotionTbMaster> findAllByStringPropertyIgnoreCase(String propertyName, String value){
		return promotionService.findAllByStringPropertyIgnoreCase(propertyName, value);
	}
	
	/**
	 * Create New Promotion.
	 * 
	 * @param AddPromotionForm
	 * @param SessionInfo
	 * @return Long
	 */
	public Long createNewPromotion(AddPromotionForm addPromotionForm,
			SessionInfo sessionInfo) throws BusinessFailureException,
			GenericFailureException, ParseException {
		
		PromotionTbMaster newPromotion = new PromotionTbMaster();
		
		StoreTbMaster storeTbMaster = new StoreTbMaster();
		
		newPromotion.setNmCrtdBy(new BigDecimal(sessionInfo.getUserId()));

		storeTbMaster.setNmStrId(sessionInfo.getStoreId());
		
		newPromotion.setStoreTbMaster(storeTbMaster);		
		
		newPromotion.setVcRlNm(addPromotionForm.getPromotionRuleName());
		
		newPromotion.setVcRlCd(addPromotionForm.getPromotionRuleCode());
		
		newPromotion.setVcRlDscr(addPromotionForm.getPromotionDescription());
		
		//Only mannual type is supported.
		newPromotion.setVcRlCdTyp('M');
		
		
		/*addPromotionForm.setPromotionLimitPerOrderCheckBox(addPromotionForm.getPromotionLimitPerOrderCheckBox() == null ? Boolean.FALSE 
				: Boolean.TRUE);*/
		if(addPromotionForm.getPromotionLimitPerOrderCheckBox() == null)
			addPromotionForm.setPromotionLimitPerOrderCheckBox(Boolean.FALSE);
		
		if(addPromotionForm.getPromotionLimitPerOrderCheckBox().equals(Boolean.TRUE))
			newPromotion.setNmLmtPrOrdr(BigDecimal.valueOf(-1));
		else
			newPromotion.setNmLmtPrOrdr(BigDecimal.valueOf(Long.valueOf(addPromotionForm.getPromotionLimitPerOrder())));
		
		/*addPromotionForm.setPromotionLimitPerCustomerCheckBox(addPromotionForm.getPromotionLimitPerCustomerCheckBox() == null ? Boolean.FALSE 
				: Boolean.TRUE);*/
		if(addPromotionForm.getPromotionLimitPerCustomerCheckBox() == null)
			addPromotionForm.setPromotionLimitPerCustomerCheckBox(Boolean.FALSE);
		
		if(addPromotionForm.getPromotionLimitPerCustomerCheckBox().equals(Boolean.TRUE))
			newPromotion.setNmLmtPrCstmr(BigDecimal.valueOf(-1));
		else
			newPromotion.setNmLmtPrCstmr(BigDecimal.valueOf(Long.valueOf(addPromotionForm.getPromotionLimitPerCustomer())));
		
		/*addPromotionForm.setPromotionLimitPerPromotionCheckBox(addPromotionForm.getPromotionLimitPerPromotionCheckBox() == null ? Boolean.FALSE 
				: Boolean.TRUE);*/
		if(addPromotionForm.getPromotionLimitPerPromotionCheckBox() == null)
			addPromotionForm.setPromotionLimitPerPromotionCheckBox(Boolean.FALSE);
		
		if(addPromotionForm.getPromotionLimitPerPromotionCheckBox().equals(Boolean.TRUE))
			newPromotion.setNmLmtPrPrmtn(BigDecimal.valueOf(-1));
		else
			newPromotion.setNmLmtPrPrmtn(BigDecimal.valueOf(Long.valueOf(addPromotionForm.getPromotionLimitPerPromotion())));
		
		newPromotion.setVcStts(addPromotionForm.getStatus());
		
		newPromotion.setDtPrmtnFrm(DateUtil.getDbFormatDateTime(addPromotionForm.getFromDate()));
		
		newPromotion.setDtPrmtnTo(DateUtil.getDbFormatDateTime(addPromotionForm.getToDate()));		
		
		newPromotion.setNmDscntTyp(addPromotionForm.getDiscountType());
		
		newPromotion.setNmDscntVl(BigDecimal.valueOf(Double.valueOf(addPromotionForm.getDiscountValue())));
		
		newPromotion.setNmPrmtnScp(addPromotionForm.getMode());
		
		if(addPromotionForm.getMode().intValue() != SBSConstants.promotion_mode_all_orders){
			
			if(addPromotionForm.getMode().intValue() == SBSConstants.promotion_mode_order_over)
				newPromotion.setNmOrdrOvr(BigDecimal.valueOf(Double.valueOf(addPromotionForm.getOrderOver())));
			
			if(addPromotionForm.getMode().intValue() == SBSConstants.promotion_mode_specific_product){
				newPromotion.setCategoryTbMaster(categoryService.findByID(Long.valueOf(addPromotionForm.getCategoryId())));
				newPromotion.setProductTbMaster(productService.findByID(Long.valueOf(addPromotionForm.getProductId())));
			}
			else{
				newPromotion.setCategoryTbMaster(null);
				newPromotion.setProductTbMaster(null);
			}
		}
		
		newPromotion.setDtCrtdAt(DateUtil.getCurrentDateTime());
		
		newPromotion.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		
		newPromotion.setNmUpdtdBy(new BigDecimal(sessionInfo.getUserId()));
		
		newPromotion.setIsDltd(new BigDecimal(0));
		
		return promotionService.create(newPromotion);
	}	
	
	/**
	 * Edit existing Promotion.
	 * @param addPromotionForm
	 * @param SessionInfo
	 * @throws ParseException 
	 */
	public void updatePromotion(AddPromotionForm addPromotionForm, SessionInfo sessionInfo) throws ParseException {
	    
		PromotionTbMaster promotionToEdit = getPromotion(addPromotionForm.getPromotionId());
		
		//Update Promotion Rule Name
		promotionToEdit.setVcRlNm(addPromotionForm.getPromotionRuleName());
		//Update Rule Code
		promotionToEdit.setVcRlCd(addPromotionForm.getPromotionRuleCode());
		//Update Description
		promotionToEdit.setVcRlDscr(addPromotionForm.getPromotionDescription());
		
		//Update Limit Per Order
/*		addPromotionForm.setPromotionLimitPerOrderCheckBox(addPromotionForm.getPromotionLimitPerOrderCheckBox() == null ? Boolean.FALSE 
				: Boolean.TRUE);*/
		if(addPromotionForm.getPromotionLimitPerOrderCheckBox() == null)
			addPromotionForm.setPromotionLimitPerOrderCheckBox(Boolean.FALSE);
					
		if(addPromotionForm.getPromotionLimitPerOrderCheckBox().equals(Boolean.TRUE))
			promotionToEdit.setNmLmtPrOrdr(BigDecimal.valueOf(-1));
		else
			promotionToEdit.setNmLmtPrOrdr(BigDecimal.valueOf(Long.valueOf(addPromotionForm.getPromotionLimitPerOrder())));
		//}
		
		//Update Limit Per Customer
		/*addPromotionForm.setPromotionLimitPerCustomerCheckBox(addPromotionForm.getPromotionLimitPerCustomerCheckBox() == null ? Boolean.FALSE 
				: Boolean.TRUE);*/
		//if(addPromotionForm.getPromotionLimitPerCustomerCheckBox() != null){
		if(addPromotionForm.getPromotionLimitPerCustomerCheckBox() == null)
			addPromotionForm.setPromotionLimitPerCustomerCheckBox(Boolean.FALSE);
		
		if(addPromotionForm.getPromotionLimitPerCustomerCheckBox().equals(Boolean.TRUE))
			promotionToEdit.setNmLmtPrCstmr(BigDecimal.valueOf(-1));
		else
			promotionToEdit.setNmLmtPrCstmr(BigDecimal.valueOf(Long.valueOf(addPromotionForm.getPromotionLimitPerCustomer())));
		//}
		
		//Update Limit Per Promotion
		/*addPromotionForm.setPromotionLimitPerPromotionCheckBox(addPromotionForm.getPromotionLimitPerPromotionCheckBox() == null ? Boolean.FALSE 
				: Boolean.TRUE);*/
		//if(addPromotionForm.getPromotionLimitPerPromotionCheckBox() != null){
		if(addPromotionForm.getPromotionLimitPerPromotionCheckBox() == null)
			addPromotionForm.setPromotionLimitPerPromotionCheckBox(Boolean.FALSE);
		
		if(addPromotionForm.getPromotionLimitPerPromotionCheckBox().equals(Boolean.TRUE))
			promotionToEdit.setNmLmtPrPrmtn(BigDecimal.valueOf(-1));
		else
			promotionToEdit.setNmLmtPrPrmtn(BigDecimal.valueOf(Long.valueOf(addPromotionForm.getPromotionLimitPerPromotion())));	
		
		//Update status
		promotionToEdit.setVcStts(addPromotionForm.getStatus());
		//Update From Date
		promotionToEdit.setDtPrmtnFrm(DateUtil.getDbFormatDateTime(addPromotionForm.getFromDate()));
		
		promotionToEdit.setDtPrmtnTo(DateUtil.getDbFormatDateTime(addPromotionForm.getToDate()));		
		
		//Update Discount Type
		promotionToEdit.setNmDscntTyp(addPromotionForm.getDiscountType());
		//Update Discount Value
		promotionToEdit.setNmDscntVl(BigDecimal.valueOf(Double.valueOf(addPromotionForm.getDiscountValue())));
		//Update Mode
		promotionToEdit.setNmPrmtnScp(addPromotionForm.getMode());
		
		//Update All Order
		if(addPromotionForm.getMode().intValue() == SBSConstants.promotion_mode_all_orders){
			promotionToEdit.setNmOrdrOvr(null);
			promotionToEdit.setCategoryTbMaster(null);
			promotionToEdit.setProductTbMaster(null);
		}
		
		//Update Order Over Value
		if(addPromotionForm.getMode().intValue() == SBSConstants.promotion_mode_order_over){
			promotionToEdit.setNmOrdrOvr(BigDecimal.valueOf(Double.valueOf(addPromotionForm.getOrderOver())));
			promotionToEdit.setCategoryTbMaster(null);
			promotionToEdit.setProductTbMaster(null);
		}
		
		//Update Specifuc Product
		if(addPromotionForm.getMode().intValue() == SBSConstants.promotion_mode_specific_product){
			promotionToEdit.setNmOrdrOvr(null);
			promotionToEdit.setCategoryTbMaster(categoryService.findByID(Long.valueOf(addPromotionForm.getCategoryId())));
			promotionToEdit.setProductTbMaster(productService.findByID(Long.valueOf(addPromotionForm.getProductId())));
		}
			
		
		//Update the Updated Date
		promotionToEdit.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		//Update the User, who updated this promotion
		promotionToEdit.setNmUpdtdBy(new BigDecimal(sessionInfo.getUserId()));
		//Update the deleted status
		promotionToEdit.setIsDltd(new BigDecimal(0));
		
		promotionService.saveOrUpdate(promotionToEdit);
	}

	public PromotionTbMaster getPromotion(Long promotionId) {
		return promotionService.findByID(promotionId);
	}
	
	
	/**
	 * find promotion by promotionId.
	 * 
	 * @param id
	 * @return PromotionTbMaster object
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public PromotionTbMaster viewPromotionById(long id)
			throws BusinessFailureException, GenericFailureException {

		PromotionTbMaster promotion = promotionService.findByID(id);

		return promotion;

	}	
	
	
	/**
	 * populate AddPromotionForm from PromotionTbMaster object.
	 * 
	 * @param addPromotionForm
	 * @param promotion
	 * @return populated addPromotionForm from PromotionTbMaster object.
	 * @throws ParseException 
	 */
	public AddPromotionForm setPromotionVO(AddPromotionForm addPromotionForm,
			long promotionId, SessionInfo sessionInfo) throws BusinessFailureException,
			GenericFailureException, ParseException {
		
		PromotionTbMaster promotion = promotionService.findByID(promotionId);
		
		addPromotionForm.setPromotionId(promotion.getNmPrmtnRlId());
		
		addPromotionForm.setPromotionRuleName(promotion.getVcRlNm());
		
		addPromotionForm.setPromotionRuleCode(promotion.getVcRlCd());
		
		addPromotionForm.setPromotionDescription(promotion.getVcRlDscr());
		
		if(promotion.getNmLmtPrOrdr().toString().equals("-1"))
			addPromotionForm.setPromotionLimitPerOrderCheckBox(Boolean.TRUE);
		else
			addPromotionForm.setPromotionLimitPerOrder(promotion.getNmLmtPrOrdr().toString());
		
		if(promotion.getNmLmtPrCstmr().toString().equals("-1"))
			addPromotionForm.setPromotionLimitPerCustomerCheckBox(Boolean.TRUE);
		else
			addPromotionForm.setPromotionLimitPerCustomer(promotion.getNmLmtPrCstmr().toString());
		
		if(promotion.getNmLmtPrPrmtn().toString().equals("-1"))
			addPromotionForm.setPromotionLimitPerPromotionCheckBox(Boolean.TRUE);
		else
			addPromotionForm.setPromotionLimitPerPromotion(promotion.getNmLmtPrPrmtn().toString());
		
		addPromotionForm.setStatus(promotion.getVcStts());
		
		addPromotionForm.setFromDate(DateUtil.getFormattedDateWithTime(promotion.getDtPrmtnFrm()));
		
		addPromotionForm.setToDate(DateUtil.getFormattedDateWithTime(promotion.getDtPrmtnTo()));
		
		addPromotionForm.setIsFromDateDisabled(!DateUtil.isGTCurrentDate(promotion.getDtPrmtnFrm().getTime()));
		
		addPromotionForm.setIsToDateDisabled(!DateUtil.isGTCurrentDate(promotion.getDtPrmtnTo().getTime()));
		
		addPromotionForm.setDiscountType(promotion.getNmDscntTyp());

		addPromotionForm.setDiscountValue(promotion.getNmDscntVl().toString());
		
		addPromotionForm.setMode(promotion.getNmPrmtnScp());
		
		if(addPromotionForm.getMode().intValue() == SBSConstants.promotion_mode_order_over){
			addPromotionForm.setOrderOver(promotion.getNmOrdrOvr().toString());
		}
		
		if(addPromotionForm.getMode().intValue() == SBSConstants.promotion_mode_specific_product){
			addPromotionForm.setCategoryId(String.valueOf(promotion.getCategoryTbMaster().getNmCtgryId()));
			addPromotionForm.setProductId(String.valueOf(promotion.getProductTbMaster().getNmPrdId()));
		}
		
		
		if(promotion.getCategoryTbMaster() != null && promotion.getProductTbMaster() != null){
			addPromotionForm.setCategoryId(String.valueOf(promotion.getCategoryTbMaster().getNmCtgryId()));
			
			addPromotionForm.setProductId(String.valueOf(promotion.getProductTbMaster().getNmPrdId()));
		
			addPromotionForm.setProductAjaxForms(this.getAllProducts(promotion.getCategoryTbMaster().getNmCtgryId()));
			
			addPromotionForm.setCategoryAjaxForms(this.getAllCategories(sessionInfo));
		}
		
		
		return addPromotionForm;
	}	
	
	
	/**
	 * 
	 * @param filter_criteria
	 * @param sessionInfo
	 * @return
	 * @throws ParseException
	 */
	public List<PromotionVO> findAllPromotionListByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo)
					throws ParseException {
		
		List<PromotionVO> detailList = new ArrayList<PromotionVO>();
		//Add Deleted record criteria.
		filter_criteria.put("filter.PromotionTbMaster.isDltd", BigDecimal.valueOf(0));
		//Add desc and asc in key and the pojo field in value to get filtered 
		//result in sorted order.
		filter_criteria.put("desc","dtCrtdAt");

		detailList = promotionService.findAllPromotionListByFilters(filter_criteria,
				sessionInfo);
	
		return detailList;
	}
	
	
	public List<PromotionVO> deletePromotion(long promoId , SessionInfo sessionInfo) 
			throws BusinessFailureException
	{
		
		System.out.println("Promo ID to be deleted in Handler--->" + promoId);
		List<PromotionVO> detailList = new ArrayList<PromotionVO>();
		
		detailList = promotionService.deleteById(promoId , sessionInfo);
		
		return detailList;
	}
	
	public List<CategoryAjaxForm> getAllCategories(SessionInfo sessionInfo) 
			throws BusinessFailureException, ParseException
	{
		List<CategoryAjaxForm> categoriesList = promotionService.getAllCategories(sessionInfo.getStoreId());
		return categoriesList;
	}
	
	public List<ProductAjaxForm> getAllProducts(long catid) 
			throws BusinessFailureException, ParseException
	{
		List<ProductAjaxForm> productList = promotionService.getAllProducts(catid);
		return productList;
	}	
	
	
}
