/**
 * 
 */
package com.sot.ecommerce.service;

import java.net.MalformedURLException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.sot.ecommerce.dao.SolrDAO;
import com.sot.ecommerce.entities.Category;

/**
 * @author simanchal.patra
 * 
 */
public interface CategorySolrService {

	public void setDAO(SolrDAO<Category> documentName);

	public List<Category> getPrentCategories(Long categoryId);

	public List<Category> getImmidiateChildCategories(Long parentCategoryId);

	public List<Category> getChildCategories(Long parentCategoryId);

	public Category getCategory(Long categoryId);

	public List<Category> getNoNPlaceHolderChildCategories(Long parentCategoryId);

	public List<Category> getAllCategories() throws SolrServerException,
			MalformedURLException;

}
