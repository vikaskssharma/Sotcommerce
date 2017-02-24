package com.sot.ecommerce.solr.service;

import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.sot.ecommerce.domain.Category;
import com.sot.ecommerce.exception.BusinessFailureException;
import com.sot.ecommerce.exception.GenericFailureException;
import com.sot.ecommerce.web.vo.Attribute;
import com.sot.ecommerce.web.vo.FieldStatistics;
import com.sot.ecommerce.web.vo.Product;
import com.sot.ecommerce.web.vo.Review;
import com.sot.ecommerce.web.vo.Section;




public interface StoreSolrService {

	public List<Product> getAllProducts() throws SolrServerException;

	public List<Category> getAllCategories() throws SolrServerException,
			MalformedURLException;

	public List<Product> getProducts(Long selectedCategoryId)
			throws MalformedURLException, SolrServerException;

	public LinkedHashMap<Category, List<Product>> searchProducts(
			Long categoryId, String searchTerm)
			throws BusinessFailureException, GenericFailureException,
			MalformedURLException, SolrServerException;

	public List<Category> getLeftMenuCategoryTree(Long categoryId)
			throws MalformedURLException, SolrServerException;

	public Product getProduct(Long productId, Long variantId)
			throws MalformedURLException, SolrServerException;

	public Category getCategory(Long categoryId);

	public List<Section> getAllSections(Long categoryId);

	public List<Attribute> getAllAttributes(Long sectionId);

	public List<String> getProductVariantDetails(Long productId,
			String attrMppng);

	public String getProductDetails(Long productId, String attrMppng);

	public List<Review> getProductReviews(Long productId);

	public List<Product> getSimilarProducts(Long productId);

	public FieldStatistics getProductRating(Long productId);

	List<Category> getAllCategoriesInCategoryPath(Long categoryId)
			throws SolrServerException, MalformedURLException;

}
