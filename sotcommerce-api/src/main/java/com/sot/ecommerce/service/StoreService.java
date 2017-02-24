package com.sot.ecommerce.service;

import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.sot.ecommerce.entities.Category;
import com.sot.ecommerce.entities.Product;
import com.sot.ecommerce.exception.BusinessFailureException;
import com.sot.ecommerce.exception.GenericFailureException;




public interface StoreService {

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

	public Map<String, List<ProductImagableVariantData>> getImagableVariantData(
			Long productId, Map<String, String> imagableVariant);

	public Map<String, Map<String, Object>> getProductFeatures(Long productId,
			Long variantId);

	public List<Review> getProductReviews(Long productId);

	public List<Product> getSimilarProducts(Long productId);

	public FieldStatistics getProductRating(Long productId);

	List<Category> getAllCategoriesInCategoryPath(Long categoryId)
			throws SolrServerException, MalformedURLException;

	public Map<Long, String> getNonImagableVariantData(Long productId,
			Map<String, String> nonImagableVariant,
			String imagableVariantMapping, Object imagableVariantValue);

	public LinkedHashMap<Category, List<Product>> searchProducts(
			long categoryId, Map<String, Object> searchTerms);

	public Product getVariantProduct(Long productId, Long variantId);

}
