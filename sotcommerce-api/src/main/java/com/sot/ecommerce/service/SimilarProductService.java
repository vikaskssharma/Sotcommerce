package com.sot.ecommerce.service;

import java.util.HashMap;
import java.util.List;

import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.persistance.SimilarProductTbDtl;

public interface SimilarProductService {
	
	
	/**
	 * This creates a new SimilarProductTbDtl (similarProduct) in the System for a
	 * specific Category.
	 * 
	 * @param similarProduct
	 *            The similarProduct to be created.
	 * @return The Identifier (Id) of the newly created similarProduct.
	 */
	public Long create(final SimilarProductTbDtl similarProduct);

	/**
	 * Reads all the similarProducts present in the system.
	 * 
	 * @return List of all similarProducts
	 */
	public List<SimilarProductTbDtl> findAll();

	/**
	 * Reads a specific SimilarProductTbDtl (similarProduct) from the system whose
	 * Identifies is provided.
	 * 
	 * @param id
	 *            Identifier of the similarProduct to read.
	 * @return The similarProduct.
	 */
	public SimilarProductTbDtl findByID(final long similarProductId);

	/**
	 * Reads all the SimilarProductTbDtls (similarProducts) from the system which
	 * matches the provided property.
	 * 
	 * @param propertyName
	 *            Name of the field present in the persistence class
	 *            SimilarProductTbDtl
	 * 
	 * @param value
	 *            Value for that Property whose name provided in the
	 *            propertyName.
	 * @return List of all the similarProducts matches the property
	 */
	public List<SimilarProductTbDtl> findAllByProperty(String propertyName,
			Object value);

	/**
	 * Reads all the SimilarProductTbDtls (similarProducts) from the system which
	 * matches all the provided properties.
	 * 
	 * @param properties
	 *            A HashMap containing the properties and their values.
	 * @return List of all the similarProducts matches all the properties
	 */
	public List<SimilarProductTbDtl> findAllByProperty(
			HashMap<String, Object> properties);

	/**
	 * Update a existing SimilarProductTbDtl (similarProduct) in the system with the
	 * newly provided values.
	 * 
	 * @param similarProduct
	 *            The similarProduct with updated values
	 * @return The updated SimilarProductTbDtl
	 */
	public SimilarProductTbDtl update(final SimilarProductTbDtl similarProduct);

	/**
	 * Soft-Deletes a SimilarProductTbDtl (similarProduct) and all its child Elements
	 * in the system if there is no Product exists to which
	 * this similarProduct belongs.
	 * 
	 * @param similarProductToDelete
	 *            similarProduct to be deleted
	 * 
	 * @throws BusinessFailureException
	 *             If any Product exists for that SimilarProductTbDtl to which this
	 *             similarProduct belongs
	 */
	public void delete(final SimilarProductTbDtl similarProductToDelete)
			throws BusinessFailureException;

	/**
	 * Soft-Deletes a SimilarProductTbDtl (similarProduct) and all its child Elements
	 * in the system if there is no Product exists  to which
	 * this similarProduct belongs.
	 * 
	 * @param SimilarProductTbDtl
	 *            Identifier of the similarProduct to be deleted
	 * 
	 * @throws BusinessFailureException
	 *             If any Product exists  to which this
	 *             similarProduct belongs.
	 */
	public void deleteById(final long similarProductId)
			throws BusinessFailureException;

	/**
	 * Either save a new SimilarProductTbDtl (similarProduct) or update an existing
	 * SimilarProductTbDtl (similarProduct) instance, depending upon resolution of the
	 * unsaved-value checks.
	 * 
	 * @param section
	 *            Instance of the SimilarProductTbDtl (similarProduct) Saved into or
	 *            Updated in the System
	 */
	public void saveOrUpdate(final SimilarProductTbDtl similarProduct);



}
