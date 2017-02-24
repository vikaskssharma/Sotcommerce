package com.sot.ecommerce.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sot.ecommerce.entities.BannerTbMaster;
import com.sot.ecommerce.vo.CategoryPlaceHolderTreeVO;

public interface BannerService {

	/**
	 * This creates a new Banner in the System for the specific Store.
	 * 
	 * @param BannerTbMaster
	 *            The Banner to be created.
	 * @return The Identifier (Id) of the newly created Banner.
	 */
	public Long create(final BannerTbMaster BannerTbMaster);

	/**
	 * Reads all the Categories for all the Stores present in the system.
	 * 
	 * @return List of all Banner
	 */
	public List<BannerTbMaster> findAll();

	public List<BannerVO> findBanner(SessionInfo sessionInfo);

	/**
	 * Reads a specific Banner from the system whose Identifies is provided.
	 * 
	 * @param id
	 *            Identifier of the Banner to read.
	 * @return The Banner.
	 */
	public BannerTbMaster findByID(final long id);

	/**
	 * Reads all the Categories from the system which matches the provided
	 * property.
	 * 
	 * @param propertyName
	 *            Name of the field present in the persistence class
	 *            BannerTbMaster (Banner)
	 * @param value
	 *            Value for that Property whose name provided in the
	 *            propertyName.
	 * @return List of all the Categories matches the property
	 */
	public List<BannerTbMaster> findAllByProperty(final String propertyName,
			final Object value);

	/**
	 * Reads all the Categories from the system which matches all the provided
	 * properties.
	 * 
	 * @param properties
	 *            A HashMap containing the properties and their values.
	 * @return List of all the Categories matches all the properties
	 */
	public List<BannerTbMaster> findAllByProperty(
			final HashMap<String, Object> properties);

	public List<BannerVO> findAllBannerByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo);

	/**
	 * Update a existing Banner in the system with the newly provided values.
	 * 
	 * @param BannerTbMaster
	 *            The Banner with updated values
	 * @return The updated Banner
	 */
	public BannerTbMaster update(final BannerTbMaster BannerTbMaster);

	/**
	 * Soft-Deletes a Banner and all its child Elements in the system.
	 * 
	 * @param BannerTbMaster
	 *            Banner to be deleted
	 */
	public void delete(final BannerTbMaster BannerTbMaster);

	/**
	 * Soft-Deletes a Banner and all its child Elements in the system.
	 * 
	 * @param BannerTbMasterId
	 *            Identifier of the Banner to be deleted
	 */
	// public void deleteById(final long BannerTbMasterId);
	public boolean deleteById(final long entityId);

	/**
	 * Provides all the Categories (Name, Identifier) which are place holders in
	 * in Hierarchical order and Tree format for the specified store.
	 * 
	 * @param storeId
	 *            Identifier of the Store whose Place holder Categories
	 *            required.
	 * @return List of place holder Categories (Name, Identifier) in
	 *         Hierarchical order and Tree format.
	 */

	/**
	 * Reads all the Categories (Identifier, Name, Identifier of Parent Banner,
	 * Status only) for the specified Store.
	 * 
	 * @param storeId
	 *            Identifier of the specified Store.
	 * @return List of All the Categories belong to the specified store.
	 */
	// public List<BannerVO> getAllCategories(final Long storeId);

	/**
	 * Either save a new Banner or update an existing Banner instance, depending
	 * upon resolution of the unsaved-value checks.
	 * 
	 * @param BannerTbMaster
	 *            Instance of the Banner Saved into or Updated in the System
	 */
	public void saveOrUpdate(final BannerTbMaster BannerTbMaster);

	/**
	 * Reads all the Pages
	 * 
	 * @return A List containing the Page names.
	 */
	public List<String> getAllPages();

	/**
	 * Reads all the Page Position
	 * 
	 * @return A List containing the Page position names.
	 */
	public List<String> getAllPagePosition();

	public List<CategoryPlaceHolderTreeVO> getAllCategories(long storeId)
			throws ParseException;
}
