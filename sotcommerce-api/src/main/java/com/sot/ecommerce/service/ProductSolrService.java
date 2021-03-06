/**
 * 
 */
package com.sot.ecommerce.service;

import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.sbsc.fos.store.solr.data.SolrDAO;
import com.sbsc.fos.store.web.vo.Attribute;
import com.sbsc.fos.store.web.vo.Category;
import com.sbsc.fos.store.web.vo.FacetInput;
import com.sbsc.fos.store.web.vo.FieldStatistics;
import com.sbsc.fos.store.web.vo.Product;
import com.sbsc.fos.store.web.vo.RangeFacetVO;

/**
 * @author simanchal.patra
 * 
 */
public interface ProductSolrService {

	public void setDAO(SolrDAO<Product> documentName);

	public List<Product> getProducts(List<Category> leafCategoryList)
			throws MalformedURLException, SolrServerException;

	public Product getProduct(Long productId, Long variantId)
			throws MalformedURLException, SolrServerException;

	public LinkedHashMap<Category, List<Product>> getProductsOverSearchTerm(
			Long categoryId, String searchTerm) throws MalformedURLException,
			SolrServerException;

	public List<Product> getAllProducts() throws SolrServerException;

	public List<Product> getProducts(Long categoryId)
			throws MalformedURLException, SolrServerException;

	public String getProductDetails(Long productId, String attrMppng);

	public List<Product> getSimilarProducts(Long productId);

	public LinkedHashMap<Category, List<Product>> getProductsOverSearchTerm(
			long categoryId, Map<String, Object> searchTerms);

	public Product getVariantProduct(Long productId, Long variantId);

	public QueryResponse getFacetData(Long categoryid,
			List<Attribute> facetAttributeList,
			List<RangeFacetVO> rangeFacetList, float price_min, float price_max);

	public FieldStatistics getPriceRangeByCategory(List categories,
			String columnName);

	public List<Product> getProductsByPriceRange(
			List<Category> leafCategoryList, float price_min, float price_max,
			int pgno, int size);

	public QueryResponse getFacetFilterData(FacetInput facetInput,
			List<Attribute> attributeList);

}
