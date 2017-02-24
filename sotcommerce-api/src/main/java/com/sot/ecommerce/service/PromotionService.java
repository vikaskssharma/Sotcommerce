package com.sot.ecommerce.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.persistance.PromotionTbMaster;
import com.sbsc.fos.promotion.commons.PromotionVO;
import com.sbsc.fos.promotion.web.form.CategoryAjaxForm;
import com.sbsc.fos.promotion.web.form.ProductAjaxForm;

/**
 * @author vaibhav.jain
 * 
 */
public interface PromotionService {

	public Long create(final PromotionTbMaster PromotionTbMaster);

	public List<PromotionVO> findPromotion(SessionInfo sessionInfo);
	


	public PromotionTbMaster findByID(final long id);

	/**
	 * Reads all Promotion from the system which matches the provided
	 * property.
	 * 
	 * @param propertyName
	 *            Name of the field present in the persistence class
	 *            PromotionTbMaster (Product)
	 * @param value
	 *            Value for that Property whose name provided in the
	 *            propertyName.
	 * @return List of all the Promotion matches the property
	 */
	public List<PromotionTbMaster> findAllByStringPropertyIgnoreCase(String propertyName, String value);

	public List<PromotionTbMaster> findAllByProperty(HashMap<String, Object> properties);

	public PromotionTbMaster update(final PromotionTbMaster PromotionTbMaster);



	public List<PromotionVO> deleteById(final long promoID , SessionInfo sessionInfo) throws BusinessFailureException;

	public List<PromotionVO>  findAllPromotionListByFilters(Map<String, Object> filter_criteria, SessionInfo sessionInfo) throws ParseException;
	public List<PromotionTbMaster> findAllByPropertyDateandCount(HashMap<String, Object> propertiesMap);
	public void saveOrUpdate(final PromotionTbMaster PromotionTbMaster);

	public List<CategoryAjaxForm> getAllCategories(long storeId) throws ParseException;
	
	public List<ProductAjaxForm> getAllProducts(long catid) throws ParseException;
	
	
	
}
