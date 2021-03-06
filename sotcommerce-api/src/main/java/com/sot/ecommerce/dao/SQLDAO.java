/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.dao;

import java.util.HashMap;
import java.util.List;

import com.sot.ecommerce.vo.CategoryPlaceHolderTreeVO;
import com.sot.ecommerce.vo.CategoryVO;

/**
 * This Interface allows to add methods specific to modules which requires
 * execution SQL queries to achieve the functionality and provides Hibernate
 * interfaces to execute those SQL queries.
 * 
 * @author simanchal.patra
 * 
 */
public interface SQLDAO {

	/**
	 * Provides the name of the next Attribute-mapping (Column name is Product
	 * table) available for the specified Category.
	 * 
	 * @param categoryId
	 *            Identifier of the specified Category.
	 * @return The name of the Attribute-mapping
	 */
	public String getNextAvailableAttributeMapping(Long categoryId);

	/**
	 * Provides the name of the next Variant Attribute-mapping (Column name is
	 * Product-Variant table) available for the specified Category.
	 * 
	 * @param categoryId
	 *            Identifier of the specified Category.
	 * 
	 * @return The name of the Variant Attribute-mapping
	 */
	public String getNextAvailableVariantAttributeMapping(Long categoryId);

	/**
	 * Provides the total count of Variant Attribute-mappings present in the
	 * system for the specified Category.
	 * 
	 * @param categoryId
	 *            Identifier of the specified Category.
	 * 
	 * @return Total count of Variant Attribute-mappings
	 */
	public int getVariantAttributeMappingCount(Long categoryId);

	/**
	 * Provides all the Categories (Name, Identifier) which are place holders in
	 * a tree format for the specified store.
	 * 
	 * @param storeId
	 *            Identifier of the Store whose Place holder Categories
	 *            required.
	 * @return List Categories (Name, Identifier) in the Order they exists in
	 *         the Tree.
	 */
	public List<CategoryPlaceHolderTreeVO> getPlaceHolderHierarchyTree(
			Long storeId);
	
	/**
	 * Provides all the Categories (Name, Identifier) which are place holders in
	 * a tree format for the specified store.
	 * 
	 * @param storeId
	 *            Identifier of the Store whose Place holder Categories
	 *            required.
	 * @return List Categories (Name, Identifier) in the Order they exists in
	 *         the Tree.
	 */
	public List<CategoryPlaceHolderTreeVO> getCategoryHierarchyTree(
			Long storeId);

	/**
	 * Reads all the Categories (Identifier, Name, Identifier of Parent
	 * Category, Status only) for the specified Store.
	 * 
	 * @param storeId
	 *            Identifier of the specified Store.
	 * @return List of All the Categories belong to the specified store.
	 */
	public List<CategoryVO> getAllCategories(Long storeId);

	/**
	 * Reads all the Categories (Identifier, Name only) for the specified Store.
	 * 
	 * @param storeId
	 *            Identifier of the specified Store.
	 * @return A Map containing the Details of the all the Categories
	 *         (Identifier, Name only) where key is the Name and Value is the
	 *         Identifier of that Category.
	 */
	public HashMap<String, Long> findAllCategoryNameId(Long storeId);

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
	public HashMap<String, Long> findAllSectionNameId(Long categoryId);

	/**
	 * Reads all the CtgrySctnAttrTbDtl (Identifier and Name only) present for
	 * the specified Category.
	 * 
	 * @param categoryId
	 *            Identifier of the specified Category.
	 * 
	 * @return A Map containing the Details of the all the Attributes
	 *         (Identifier and Name only) where key is the Name and Value is the
	 *         Identifier of that Attribute.
	 */
	public HashMap<String, Long> findAllAttributeNameID(Long categoryId);

	/**
	 * Provides the total count of Attribute-mappings present in the system for
	 * the specified Category.
	 * 
	 * @param categoryId
	 *            Identifier of the specified Category.
	 * 
	 * @return Total count of Variant Attribute-mappings
	 */
	public Integer getAttributeMappingCount(Long categoryId);

	/**
	 * Provides the name of the next Numeric Attribute-mapping (Column name is
	 * Product table) available for the specified Category.
	 * 
	 * @param categoryId
	 *            Identifier of the specified Category.
	 * @return The name of the Attribute-mapping
	 */
	public String getNextAvailableNumericAttributeMapping(Long categoryId);

	/**
	 * Provides the name of the next Variant Numeric Attribute-mapping (Column
	 * name is Product-Variant table) available for the specified Category.
	 * 
	 * @param categoryId
	 *            Identifier of the specified Category.
	 * 
	 * @return The name of the Variant Attribute-mapping
	 */
	public String getNextAvailableVariantNumericAttributeMapping(Long categoryId);

	/**
	 * Provides the total count of Imagable Attribute-mappings present in the
	 * system for the specified Category.
	 * 
	 * @param categoryId
	 *            Identifier of the specified Category.
	 * 
	 * @return Total count of Imagable Variant Attribute-mappings
	 */
	public Integer getImagableAttributeMappingCount(Long categoryId);

	public String getNextAvailableNumericUnitAttributeMapping(Long categoryId);

	public String getNextAvailableNumericUnitVariantAttributeMapping(
			Long categoryId);

	public Integer getNumericAttributeMappingCount(Long categoryId);
	
	
	/**
	 * Provides all pages names available.
	 * 
	 * @return All Pages names, which are not deleted.
	 */
	public List<String> findAllPages();
	
	/**
	 * Provides all order status available.
	 * 
	 * @return All order status from ORDER_STATUS_TB_MASTER table.
	 */
	public List<String> findAllOrderStatus();
	
	/**
	 * Provides all pages position names available.
	 * 
	 * @return All Pages position names.
	 */
	public List<String> findAllPagePosition();

}
