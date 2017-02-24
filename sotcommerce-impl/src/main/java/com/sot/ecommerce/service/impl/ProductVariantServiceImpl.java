/**
 * 
 */
package com.sot.ecommerce.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.persistance.ProductVariantTbDtl;
import com.sbsc.fos.product.service.ProductVariantService;

/**
 * @author diksha.rattan
 *
 */
@Service
public class ProductVariantServiceImpl implements ProductVariantService{
	
	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(ProductVariantServiceImpl.class);

	
	/*
	 * 
	 */
	private GenericDAO<ProductVariantTbDtl> dao;

	/*
	 * 
	 */
	@Autowired
	public void setDAO(GenericDAO<ProductVariantTbDtl> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(ProductVariantTbDtl.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.ProductVariantService#saveOrUpdate(com.sbsc.fos.persistance.ProductVariantTbDtl)
	 */
	@Transactional
	public void saveOrUpdate(ProductVariantTbDtl  productVariantTbDtll) {
		// TODO Auto-generated method stub
		 dao.saveOrUpdate(productVariantTbDtll);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.ProductVariantService#findAllByProperty(java.util.HashMap)
	 */
	@Transactional
	public List<ProductVariantTbDtl> findAllByProperty(HashMap<String, Object> properties){
		List<ProductVariantTbDtl> list = dao.findAllByProperty(properties);
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.ProductVariantService#findByID(long)
	 */
	@Transactional
	public ProductVariantTbDtl findByID(final long id) {
		return dao.findByID(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.ProductVariantService#update(com.sbsc.fos.persistance.ProductVariantTbDtl)
	 */
	@Transactional
	public ProductVariantTbDtl update(ProductVariantTbDtl product) {
		// TODO Auto-generated method stub
		ProductVariantTbDtl prod = dao.update(product);
		return prod;
	}
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.ProductVariantService#findAllByStringPropertyIgnoreCase(java.lang.String, java.lang.String)
	 */
	@Transactional
	public List<ProductVariantTbDtl> findAllByStringPropertyIgnoreCase(String propertyName,
			String value){
		
		List<ProductVariantTbDtl> list = dao.findAllByStringPropertyIgnoreCase(propertyName, value);
		return list;
		
		
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.ProductVariantService#setVariantStatus(java.util.List, java.lang.String)
	 */
	@Transactional
	public void setVariantStatus(List<ProductVariantTbDtl> varaints, String status) {

		for (ProductVariantTbDtl variant : varaints) {

			variant.setVcStts(status);
			dao.saveOrUpdate(variant);
		}
	}
	
	
	

}
