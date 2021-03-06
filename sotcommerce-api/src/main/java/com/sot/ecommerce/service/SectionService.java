/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.service;

import java.util.HashMap;
import java.util.List;

import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.persistance.CtgrySctnTbDtl;

/**
 * This class provides the Services specific to class CtgrySctnTbDtl (Section)
 * for all the tasks related to database operations like Create, Read, Update &
 * Delete.
 * 
 * @author simanchal.patra
 * 
 */
public interface SectionService {

	/**
	 * This creates a new Section in the System for a specific Category.
	 * 
	 * @param CtgrySctnTbDtl
	 *            The Section to be created.
	 * @return The Identifier (Id) of the newly created Section.
	 */
	public Long create(final CtgrySctnTbDtl section);

	/**
	 * Reads all the Sections for all the Categories present in the system.
	 * 
	 * @return List of all Sections
	 */
	public List<CtgrySctnTbDtl> findAll();

	/**
	 * Reads a specific Section from the system whose Identifies is provided.
	 * 
	 * @param id
	 *            Identifier of the Section to read.
	 * @return The Section.
	 */
	public CtgrySctnTbDtl findByID(final long id);

	/**
	 * Reads all the Section from the system which matches the provided
	 * property.
	 * 
	 * @param propertyName
	 *            Name of the field present in the persistence class
	 *            CtgrySctnTbDtl
	 * 
	 * @param value
	 *            Value for that Property whose name provided in the
	 *            propertyName.
	 * @return List of all the Sections matches the property
	 */
	public List<CtgrySctnTbDtl> findAllByProperty(String propertyName,
			Object value);

	/**
	 * Reads all the Sections from the system which matches all the provided
	 * properties.
	 * 
	 * @param properties
	 *            A HashMap containing the properties and their values.
	 * @return List of all the Sections matches all the properties
	 */
	public List<CtgrySctnTbDtl> findAllByProperty(
			HashMap<String, Object> propertiesMap);

	/**
	 * Update a existing CtgrySctnTbDtl (Section) in the system with the newly
	 * provided values.
	 * 
	 * @param section
	 *            The section with updated values
	 * @return The updated CtgrySctnTbDtl
	 */
	public CtgrySctnTbDtl update(final CtgrySctnTbDtl section);

	/**
	 * Soft-Deletes a CtgrySctnTbDtl (Section) and all its child Elements in the
	 * system if there is no Product exists for that Category to which this
	 * Section belongs.
	 * 
	 * @param section
	 *            Section to be deleted
	 * 
	 * @throws BusinessFailureException
	 *             If any Product exists for that Category to which this Section
	 *             belongs.
	 */
	public void delete(final CtgrySctnTbDtl section)
			throws BusinessFailureException;

	/**
	 * Soft-Deletes a CtgrySctnTbDtl (Section) and all its child Elements in the
	 * system if there is no Product exists for that Category to which this
	 * Section belongs.
	 * 
	 * @param section
	 *            Identifier of the Section to be deleted
	 * 
	 * @throws BusinessFailureException
	 *             If any Product exists for that Category to which this Section
	 *             belongs.
	 */
	public void deleteById(final long sectionId)
			throws BusinessFailureException;

	/**
	 * Either save a new Section or update an existing Section instance,
	 * depending upon resolution of the unsaved-value checks.
	 * 
	 * @param section
	 *            Instance of the Section Saved into or Updated in the System
	 */
	public void saveOrUpdate(final CtgrySctnTbDtl section);

	/**
	 * Reads all the Sections (Identifier and Name only) for the specified
	 * Category.
	 * 
	 * @param categoryId
	 *            Identifier of the specified Category.
	 * 
	 * @return A Map containing the Details of the all the Categories
	 *         (Identifier and Name only) where key is the Name and Value is the
	 *         Identifier of that Section.
	 */
	public HashMap<String, Long> findAllNameId(Long categoryId);
}
