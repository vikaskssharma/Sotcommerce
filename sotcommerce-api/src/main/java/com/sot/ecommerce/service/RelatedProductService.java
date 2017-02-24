package com.sot.ecommerce.service;

import java.util.HashMap;
import java.util.List;

import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.persistance.RelatedProductTbDtl;



public interface RelatedProductService {
	
	/**
	 * This creates a new RelatedProductTbDtl (RelatedProduct) in the System for a
	 * specific Category.
	 * 
	 * @param relatedProduct
	 *            The RelatedProduct to be created.
	 * @return The Identifier (Id) of the newly created relatedProduct.
	 */
	public Long create(final RelatedProductTbDtl relatedProduct);

	/**
	 * Reads all the relatedProducts present in the system.
	 * 
	 * @return List of all relatedProducts
	 */
	public List<RelatedProductTbDtl> findAll();

	/**
	 * Reads a specific RelatedProductTbDtl (relatedProduct) from the system whose
	 * Identifies is provided.
	 * 
	 * @param id
	 *            Identifier of the relatedProduct to read.
	 * @return The relatedProduct.
	 */
	public RelatedProductTbDtl findByID(final long relatedProductId);

	/**
	 * Reads all the RelatedProductTbDtls (relatedProducts) from the system which
	 * matches the provided property.
	 * 
	 * @param propertyName
	 *            Name of the field present in the persistence class
	 *            RelatedProductTbDtl
	 * 
	 * @param value
	 *            Value for that Property whose name provided in the
	 *            propertyName.
	 * @return List of all the relatedProducts matches the property
	 */
	public List<RelatedProductTbDtl> findAllByProperty(String propertyName,
			Object value);

	/**
	 * Reads all the RelatedProductTbDtls (relatedProducts) from the system which
	 * matches all the provided properties.
	 * 
	 * @param properties
	 *            A HashMap containing the properties and their values.
	 * @return List of all the relatedProducts matches all the properties
	 */
	public List<RelatedProductTbDtl> findAllByProperty(
			HashMap<String, Object> properties);

	/**
	 * Update a existing RelatedProductTbDtl (relatedProduct) in the system with the
	 * newly provided values.
	 * 
	 * @param relatedProduct
	 *            The relatedProduct with updated values
	 * @return The updated RelatedProductTbDtl
	 */
	public RelatedProductTbDtl update(final RelatedProductTbDtl relatedProduct);

	/**
	 * Soft-Deletes a RelatedProductTbDtl (relatedProduct) and all its child Elements
	 * in the system if there is no Product exists for that Category to which
	 * this relatedProduct belongs.
	 * 
	 * @param relatedProductToDelete
	 *            relatedProduct to be deleted
	 * 
	 * @throws BusinessFailureException
	 *             If any Product exists to which this
	 *             relatedProduct belongs
	 */
	public void delete(final RelatedProductTbDtl relatedProductToDelete)
			throws BusinessFailureException;

	/**
	 * Soft-Deletes a RelatedProductTbDtl (relatedProduct) and all its child Elements
	 * in the system if there is no Product exists for that Category to which
	 * this relatedProduct belongs.
	 * 
	 * @param relatedProductId
	 *            Identifier of the relatedProduct to be deleted
	 * 
	 * @throws BusinessFailureException
	 *             If any Product exists to which this
	 *             relatedProduct belongs.
	 */
	public void deleteById(final long relatedProductId)
			throws BusinessFailureException;

	/**
	 * Either save a new RelatedProductTbDtl (relatedProduct) or update an existing
	 * RelatedProductTbDtl (relatedProduct) instance, depending upon resolution of the
	 * unsaved-value checks.
	 * 
	 * @param section
	 *            Instance of the RelatedProductTbDtl (relatedProduct) Saved into or
	 *            Updated in the System
	 */
	public void saveOrUpdate(final RelatedProductTbDtl relatedProduct);


	
}
