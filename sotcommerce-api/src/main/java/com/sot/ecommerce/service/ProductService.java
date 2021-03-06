package com.sot.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.persistance.CtgrySctnAttrTbDtl;
import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.persistance.RelatedProductTbDtl;
import com.sbsc.fos.persistance.SimilarProductTbDtl;
import com.sbsc.fos.product.web.form.ProductBasicForm;

public interface ProductService {

	/**
	 * This creates a new Product in the System for the specific Store.
	 * 
	 * @param ProductTbMaster
	 *            The Product to be created.
	 * @return The Identifier (Id) of the newly created Product.
	 */
	public void create(final ProductTbMaster entity);

	/**
	 * Reads all the Products for all the Stores present in the system.
	 * 
	 * @return List of all Product
	 */
	public List<ProductTbMaster> findAll();

	/**
	 * Reads a specific Product from the system whose Identifies is provided.
	 * 
	 * @param id
	 *            Identifier of the Product to read.
	 * @return The Product.
	 */
	public ProductTbMaster findByID(final long id);

	/**
	 * Reads all the Products from the system which matches the provided
	 * property.
	 * 
	 * @param propertyName
	 *            Name of the field present in the persistence class
	 *            ProductTbMaster (Product)
	 * @param value
	 *            Value for that Property whose name provided in the
	 *            propertyName.
	 * @return List of all the Products matches the property
	 */
	public List<ProductTbMaster> findAllByProperty(String propertyName,
			Object value);

	/**
	 * Reads all the Products from the system which matches all the provided
	 * properties.
	 * 
	 * @param properties
	 *            A HashMap containing the properties and their values.
	 * @return List of all the Products matches all the properties
	 */
	public List<ProductTbMaster> findAllByProperty(
			HashMap<String, Object> properties);

	
	/**
	 * Update a existing Product in the system with the newly provided values.
	 * 
	 * @param ProductTbMaster
	 *            The Product with updated values
	 * @return The updated product
	 */
	public ProductTbMaster update(final ProductTbMaster entity);

	
	/**
	 * Soft-Deletes a Product and all its child Elements in the system.
	 * 
	 * @param ProductTbMaster
	 *            Product to be deleted
	 */
	public void delete(final ProductTbMaster entity);

	
	/**
	 * Soft-Deletes a Product and all its child Elements in the system.
	 * 
	 * @param ProductTbMasterId
	 *            Identifier of the Product to be deleted
	 */

	public void deleteById(final long entityId);

	
	/**
	 * Finds All the category attributes section wise 
	 * @param properties
	 * @return
	 */
	public List<CtgrySctnAttrTbDtl> findAllAttributeByCategory(
			HashMap<String, Object> properties);

	/**
	 * Finds all the products by category wise
	 * @param propertyName
	 * Name of the field present in the persistence class
	 *            ProductTbMaster (Product)
	 * @param value
	 * Value for that Property whose name provided in the
	 *            propertyName.
	 * @return list of ProductTbMaster object
	 */
	public List<ProductTbMaster> findAllProductsByCategory(HashMap<String, Object> properties);

	/**
	 * Either save a new Product or update an existing Product instance,
	 * depending upon resolution of the unsaved-value checks.
	 * 
	 * @param ProductTbMaster
	 *            Instance of the Product Saved into or Updated in the System
	 */
	public void saveOrUpdate(ProductTbMaster product);

	
	/**
	 * Either save a new Related Product or update an existing Product instance,
	 * depending upon resolution of the unsaved-value checks.
	 * 
	 * @param RelatedProductTbDtl
	 *            Instance of the Related Product Saved into or Updated in the System
	 */
	public void saveOrUpdate(RelatedProductTbDtl relatedProduct);

	/**
	 * Either save a new Similar Product or update an existing Product instance,
	 * depending upon resolution of the unsaved-value checks.
	 * 
	 * @param SimilarProductTbDtl
	 *            Instance of the Simliar Product Saved into or Updated in the System
	 */
	public void saveOrUpdate(SimilarProductTbDtl similarProduct);

	/**
	 * Provides all the Categories (Name, Identifier) in Hierarchical order and Tree format for the specified store.
	 * 
	 * @param sessionInfo
	 *            Identifier of the Store whose Place holder Categories from session
	 *            required
	 * @return List of place holder Categories (Name, Identifier) in
	 *         Hierarchical order and Tree format.
	 */
	public Map<Long, String> getCategoriesHierarchyTree(SessionInfo sessionInfo);

	
	/**
	 * Finds all the Related Products product wise
	 * @param properties
	 * 				A HashMap containing the properties and their values.
	 * @return list of RelatedProductTbDtl objects
	 */
	public List<RelatedProductTbDtl> findAllRelatedProductsByProduct(
			HashMap<String, Object> properties);
	
	/**
	 * Finds all the Similar Products product wise
	 * @param properties
	 * 				A HashMap containing the properties and their values.
	 * @return list of SimilarProductTbDtl objects
	 */

	public List<SimilarProductTbDtl> findAllSimilarProductByProperty(HashMap<String, Object> properties);

	/**
	 * 
	 * @param filter_criteria
	 * @return
	 */
	public List<ProductBasicForm> findAllProductsByFilters(
			Map<String, Object> filter_criteria);
	
	/**
	 * Find product details on the basis of product
	 * @param id
	 * @return
	 */
	public ProductTbMaster getProductById(long id);
	
	
	
	/**
	 * Reads all the Products from the system which matches the provided
	 * property.
	 * 
	 * @param propertyName
	 *            Name of the field present in the persistence class
	 *            ProductTbMaster (Product)
	 * @param value
	 *            Value for that Property whose name provided in the
	 *            propertyName.
	 * @return List of all the Products matches the property
	 */
	public List<ProductTbMaster> findAllByStringPropertyIgnoreCase(String propertyName,
			String value);
	
	
	public void setProductStatus(List<ProductTbMaster> products,String status);
	
	

}
