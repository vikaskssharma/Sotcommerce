/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.form;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sbsc.fos.category.commons.CategoryPlaceHolderTreeVO;

/**
 * This class represents the Spring UI form for the Add / Edit a Category.
 * 
 * @author simanchal.patra
 */
@Component
public class AddCategoryForm {

	private Long categoryId;

	private String categoryName;

	private String parentCategoryId;

	private String parentCategoryName;

	private String description;

	private String status;

	private Boolean isPlaceHolder;

	private Map<String, String> allStatuses;

	private List<CategoryPlaceHolderTreeVO> categoriesHierarchyTree;

	private String mode;

	public AddCategoryForm() {
	}

	public AddCategoryForm(Long categoryId, String categoryName,
			String parentCategoryId, String parentCategoryName,
			String description, String status, Boolean isPlaceHolder,
			Map<String, String> allStatuses,
			List<CategoryPlaceHolderTreeVO> categoriesHierarchyTree, String mode) {

		this.categoryId = categoryId;

		this.categoryName = categoryName;

		this.parentCategoryId = parentCategoryId;

		this.parentCategoryName = parentCategoryName;

		this.description = description;

		this.status = status;

		this.isPlaceHolder = isPlaceHolder;

		this.allStatuses = allStatuses;

		this.categoriesHierarchyTree = categoriesHierarchyTree;

		this.mode = mode;
	}

	/**
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setCategoryName(String name) {
		this.categoryName = name;
	}

	/**
	 * @return the parentCategoryId
	 */
	public String getParentCategoryId() {
		return parentCategoryId;
	}

	/**
	 * @param parentCategoryId
	 *            the parentCategoryId to set
	 */
	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
	/**
	 * @return the categoriesHierarchyTree
	 */
	public List<CategoryPlaceHolderTreeVO> getCategoriesHierarchyTree() {
		return categoriesHierarchyTree;
	}

	/**
	 * @param categoriesHierarchyTree
	 *            the categoriesHierarchyTree to set
	 */
	public void setCategoriesHierarchyTree(
			List<CategoryPlaceHolderTreeVO> categoriesHierarchyTree) {
		this.categoriesHierarchyTree = categoriesHierarchyTree;
	}

	/**
	 * @return the parentCategoryName
	 */
	public String getParentCategoryName() {
		return parentCategoryName;
	}

	/**
	 * @param parentCategoryName
	 *            the parentCategoryName to set
	 */
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	/**
	 * @return the isPlaceHolder
	 */
	public Boolean getIsPlaceHolder() {
		return isPlaceHolder;
	}

	/**
	 * @param isPlaceHolder
	 *            the isPlaceHolder to set
	 */
	public void setIsPlaceHolder(Boolean isPlaceHolder) {
		this.isPlaceHolder = isPlaceHolder;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the allStatuses
	 */
	public Map<String, String> getAllStatuses() {
		return allStatuses;
	}

	/**
	 * @param allStatuses the allStatuses to set
	 */
	public void setAllStatuses(Map<String, String> allStatuses) {
		this.allStatuses = allStatuses;
	}
	
	

}
