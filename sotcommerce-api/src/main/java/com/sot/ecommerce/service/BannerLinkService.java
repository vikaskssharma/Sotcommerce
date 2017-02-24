/**
 * 
 */
package com.sot.ecommerce.service;

import java.util.List;
import java.util.Map;

import com.sot.ecommerce.entities.BannerLinkDetail;

/**
 * @author vaibhav.jain
 *
 */
public interface BannerLinkService {
	/**
	 * This creates a new Banner in the System for the specific Store.
	 * 
	 * @param BannerLinkDetail
	 *            The Banner to be created.
	 * @return The Identifier (Id) of the newly created Banner.
	 */
	public Long create(final BannerLinkDetail BannerLinkDetail);

	/**
	 * Reads all the Banner for all the Stores present in the system.
	 * 
	 * @return List of all Banner
	 */
	public List<BannerLinkDetail> findAll();

	/**
	 * Reads a specific Banner from the system whose Identifies is provided.
	 * 
	 * @param id
	 *            Identifier of the Banner to read.
	 * @return The Banner.
	 */
	public BannerLinkDetail findByID(final long id);

	/**
	 * Reads all the Banner from the system which matches the provided
	 * property.
	 * 
	 * @param propertyName
	 *            Name of the field present in the persistence class
	 *            BannerLinkDetail (Banner)
	 * @param value
	 *            Value for that Property whose name provided in the
	 *            propertyName.
	 * @return List of all the Banner matches the property
	 */
	public List<BannerLinkDetail> findAllByProperty(final String propertyName,
			final Object value);

	/**
	 * Reads all the Banner from the system which matches all the provided
	 * properties.
	 * 
	 * @param properties
	 *            A HashMap containing the properties and their values.
	 * @return List of all the Banner matches all the properties
	 */
	public List<BannerLinkDetail> findAllByProperty(
			final Map<String, Object> properties);

	/**
	 * Update a existing Banner in the system with the newly provided values.
	 * 
	 * @param BannerLinkDetail
	 *            The Banner with updated values
	 * @return The updated Banner
	 */
	public BannerLinkDetail update(final BannerLinkDetail BannerLinkDetail);

	/**
	 * Soft-Deletes a Banner and all its child Elements in the system.
	 * 
	 * @param BannerLinkDetail
	 *            Banner to be deleted
	 */
	public void delete(final BannerLinkDetail BannerLinkDetail);

	/**
	 * Soft-Deletes a Banner and all its child Elements in the system.
	 * 
	 * @param BannerLinkDetailId
	 *            Identifier of the Banner to be deleted
	 */
	public void deleteById(final long BannerLinkDetailId);

	/**
	 * Either save a new Banner or update an existing Banner instance,
	 * depending upon resolution of the unsaved-value checks.
	 * 
	 * @param BannerLinkDetail
	 *            Instance of the Banner Saved into or Updated in the System
	 */
	public void saveOrUpdate(final BannerLinkDetail BannerLinkDetail);

	/**
	 * Reads all the Banner (Identifier, Name only) for the specified Store.
	 * 
	 * @param storeId
	 *            Identifier of the specified Store.
	 * @return A Map containing the Details of the all the Banner
	 *         (Identifier, Name only) where key is the Name and Value is the
	 *         Identifier of that Category.
	 */
}
