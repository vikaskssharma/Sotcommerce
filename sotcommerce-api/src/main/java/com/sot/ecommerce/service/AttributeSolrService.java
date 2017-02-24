/**
 * 
 */
package com.sot.ecommerce.service;

import java.util.List;

import com.sot.ecommerce.dao.SolrDAO;




/**
 * @author simanchal.patra
 * 
 */
public interface AttributeSolrService {

	List<Attribute> getAllAttributes(Long sectionId);

	List<Attribute> getImagableAttribute(Long categoryId);

	void setDAO(SolrDAO<Attribute> documentName);

	public List<Attribute> getFacetAttributesOnCategory(Long categoryId);

	public Attribute getFacetAttributeNameOnCategory(Long categoryId,
			String attributeName);
}
