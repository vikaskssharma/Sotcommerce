/**
 * 
 */
package com.sot.ecommerce.service;

import java.util.HashMap;
import java.util.List;

import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.persistance.ProductVariantTbDtl;

/**
 * @author diksha.rattan
 *
 */
public interface ProductVariantService {
	
	/**
	 * 
	 * @param productVariantTbDtll
	 */
	public void saveOrUpdate(ProductVariantTbDtl  productVariantTbDtll) ;
	
	/**
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<ProductVariantTbDtl> findAllByProperty(HashMap<String, Object> properties);
	
	
	public ProductVariantTbDtl findByID(final long id) ;
	
	
	public ProductVariantTbDtl update(ProductVariantTbDtl product);
	
	public List<ProductVariantTbDtl> findAllByStringPropertyIgnoreCase(String propertyName,
			String value);
	
	public void setVariantStatus(List<ProductVariantTbDtl> variants, String status);


}
