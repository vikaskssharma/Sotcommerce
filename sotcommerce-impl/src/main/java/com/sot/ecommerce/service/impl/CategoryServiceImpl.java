/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.category.commons.CategoryPlaceHolderTreeVO;
import com.sbsc.fos.category.commons.CategoryVO;
import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.common.dao.SQLDAO;
import com.sbsc.fos.persistance.CategoryTbMaster;
import com.sbsc.fos.persistance.CtgrySctnAttrTbDtl;
import com.sbsc.fos.persistance.CtgrySctnTbDtl;
import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.product.service.ProductService;

/**
 * This class provides the Implementation of the Services specific to class
 * CategoryTbMaster (Category) for all the tasks related to database operations
 * like Create, Read, Update & Delete.
 * 
 * @author simanchal.patra
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(CategoryServiceImpl.class);

	/** The Generic DAO instantiated for CategoryTbMaster */
	private GenericDAO<CategoryTbMaster> dao;

	/** The SQL DAO */
	@Autowired
	private SQLDAO sqlDAO;

	/** The Section Service */
	@Autowired
	private SectionService sectionService;

	/** The Product Service */
	@Autowired
	private ProductService productService;

	/** Instantiating a copy of Generic DAO for CategoryTbMaster */
	@Autowired
	public void setDAO(GenericDAO<CategoryTbMaster> daoToSet) {
		dao = daoToSet;
		dao.setClazz(CategoryTbMaster.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.CategoryService#create(com.sbsc.fos.persistance
	 * .CategoryTbMaster)
	 */
	@Transactional
	@Override
	public Long create(CategoryTbMaster categoryTbMaster) {

		try {
			return dao.create(categoryTbMaster);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while creating a new Cateegory with name - %s for Store with Id - %s",
							categoryTbMaster.getVcCtgryNm(), categoryTbMaster
									.getStoreTbMaster().getNmStrId()), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.CategoryService#findAll()
	 */
	@Transactional
	@Override
	public List<CategoryTbMaster> findAll() {
		try {
			return dao.findAll();
		} catch (HibernateException hexp) {
			logger.error(
					"Error occured while Reading all Cateegories from System ",
					hexp);
		} finally {

		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.CategoryService#findByID(long)
	 */
	@Transactional
	@Override
	public CategoryTbMaster findByID(final long categoryId) {
		try {
			return dao.findByID(categoryId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Cateegory with id - %s",
					String.valueOf(categoryId)), hexp);
		} finally {

		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.CategoryService#update(com.sbsc.fos.persistance
	 * .CategoryTbMaster)
	 */
	@Transactional
	@Override
	public CategoryTbMaster update(CategoryTbMaster categoryTbMaster) {

		try {
			return dao.update(categoryTbMaster);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while updating a Category with"
							+ " name - %s, Id - %s for Store with Id - %s",
					categoryTbMaster.getVcCtgryNm(), categoryTbMaster
							.getNmCtgryId(), categoryTbMaster
							.getStoreTbMaster().getNmStrId()), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.CategoryService#delete(com.sbsc.fos.persistance
	 * .CategoryTbMaster)
	 */
	@Transactional
	@Override
	public void delete(CategoryTbMaster CategoryTbMaster) {

		try {
			CategoryTbMaster categoryTbMasterToDelete = dao
					.findByID(CategoryTbMaster.getNmCtgryId());

			deleteCategory(categoryTbMasterToDelete);

			dao.update(categoryTbMasterToDelete);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while deleting a Cateegory with id - %s",
					String.valueOf(CategoryTbMaster.getNmCtgryId())), hexp);
		} finally {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.CategoryService#deleteById(long)
	 */
	@Transactional
	@Override
	public void deleteById(long categoryTbMasterId) {

		CategoryTbMaster categoryTbMasterToDelete = null;
		try {
			categoryTbMasterToDelete = dao.findByID(categoryTbMasterId);

			deleteCategory(categoryTbMasterToDelete);

			// dao.update(categoryTbMasterToDelete);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while deleting a Cateegory with id - %s",
					String.valueOf(categoryTbMasterId)), hexp);
		} finally {

		}
	}

	/**
	 * Soft-deletes the Category and its child Elements recursively if there is
	 * no product exists in any level of that Category.
	 * 
	 * @param categoryTbMasterToDelete
	 *            The Category to be deleted.
	 */
	@Transactional
	private void deleteCategory(CategoryTbMaster categoryTbMasterToDelete) {

		if (categoryTbMasterToDelete.getCategoryTbMasters().size() > 0) {

			for (CategoryTbMaster categoryTbMaster : categoryTbMasterToDelete
					.getCategoryTbMasters()) {
				deleteCategory(categoryTbMaster);
			}
		} else {

			if (!(categoryTbMasterToDelete.getIsPlchldr().equals(
					new BigDecimal(1)) ? true : false)) {

				for (ProductTbMaster product : categoryTbMasterToDelete
						.getProductTbMasters()) {
					productService.delete(product);
				}

				for (CtgrySctnAttrTbDtl ctgrySctnAttrTbDtl : categoryTbMasterToDelete
						.getCtgrySctnAttrTbDtls()) {
					ctgrySctnAttrTbDtl.setIsDltd(new BigDecimal("1"));
				}

				for (CtgrySctnTbDtl ctgrySctnTbDtl : categoryTbMasterToDelete
						.getCtgrySctnTbDtls()) {
					ctgrySctnTbDtl.setIsDltd(new BigDecimal("1"));
				}

			}
		}

		System.out.println("Deleting Category : "
				+ categoryTbMasterToDelete.getNmCtgryId() + " "
				+ categoryTbMasterToDelete.getVcCtgryNm());

		categoryTbMasterToDelete.setIsDltd(new BigDecimal("1"));
		dao.update(categoryTbMasterToDelete);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.CategoryService#findAllByProperty(java.
	 * lang.String, java.lang.Object)
	 */
	@Transactional
	@Override
	public List<CategoryTbMaster> findAllByProperty(String propertyName,
			Object value) {
		try {
			return dao.findAllByProperty(propertyName, value);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Cateegory having"
							+ " property name- %s and property value - %s",
					propertyName, value.toString()), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.CategoryService#findAllByProperty(java.
	 * util.HashMap)
	 */
	@Transactional
	@Override
	public List<CategoryTbMaster> findAllByProperty(
			HashMap<String, Object> propertiesMap) {

		try {
			return dao.findAllByProperty(propertiesMap);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Cateegory having"
							+ " properties - %s", propertiesMap.toString()),
					hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.CategoryService#getPlaceHolderHierarchyTree
	 * (java.lang.Long)
	 */
	@Transactional
	@Override
	public List<CategoryPlaceHolderTreeVO> getPlaceHolderHierarchyTree(
			Long storeId) {

		try {
			return sqlDAO.getPlaceHolderHierarchyTree(storeId);
		} catch (HibernateException hexp) {
			logger.error(String.format("Error occured while reading a Category"
					+ " Place holder Tree for Store with Id - %s", storeId),
					hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.CategoryService#getAllCategories(java.lang
	 * .Long)
	 */
	@Transactional
	@Override
	public List<CategoryVO> getAllCategories(Long storeId) {

		try {
			return sqlDAO.getAllCategories(storeId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading all Categories"
							+ " for Store with Id - %s", storeId), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.CategoryService#saveOrUpdate(com.sbsc.fos
	 * .persistance.CategoryTbMaster)
	 */
	@Transactional
	@Override
	public void saveOrUpdate(CategoryTbMaster category) {

		try {
			// if (dao.findByID(category.getNmCtgryId()).getIsActv() != category
			// .getIsActv()) {
			// updateCategoryStatus(category, category.getIsActv());
			// }
			dao.saveOrUpdate(category);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while saving or updating a Category having"
							+ " name - %s for Store with Id - %s", category
							.getVcCtgryNm(), category.getStoreTbMaster()
							.getNmStrId()), hexp);
		} finally {

		}
	}

	/**
	 * Updates the Status of the specified Category and all its child Categories
	 * recursively.
	 * 
	 * @param categoryToUpdate
	 *            The Category to be Updated.
	 * @param status
	 *            Status to be updated.
	 */
	@Transactional
	private void updateCategoryStatus(CategoryTbMaster categoryToUpdate,
			String status) {

		if (categoryToUpdate.getCategoryTbMasters().size() > 0) {

			for (CategoryTbMaster categoryTbMaster : categoryToUpdate
					.getCategoryTbMasters()) {

				if (!categoryTbMaster.getVcStts().equals(status)) {
					updateCategoryStatus(categoryTbMaster, status);
				} else {
					continue;
				}
			}
		}

		System.out.println("Updating Category status : "
				+ categoryToUpdate.getNmCtgryId() + " "
				+ categoryToUpdate.getVcCtgryNm());

		categoryToUpdate.setVcStts(status);
		dao.update(categoryToUpdate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.CategoryService#findAllNameId(java.lang
	 * .Long)
	 */
	@Transactional
	@Override
	public HashMap<String, Long> findAllNameId(Long storeId) {

		try {
			return sqlDAO.findAllCategoryNameId(storeId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading the Name and Id of"
							+ " all Categories for the specified Store having"
							+ " Id - %s", String.valueOf(storeId)), hexp);
		} finally {

		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.CategoryService#getProductCount(java.lang
	 * .Long)
	 */
	@Transactional
	@Override
	public Integer getProductCount(Long categoryId) {

		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("isDltd", new BigDecimal(0));
		criteria.put("categoryTbMaster", dao.findByID(categoryId));

		try {
			return productService.findAllByProperty(criteria).size();
		} catch (HibernateException hexp) {
			logger.error(
					String.format("Error occured while reading the Products of"
							+ " the specified Category having Id - %s",
							String.valueOf(categoryId)), hexp);
		} finally {

		}
		return null;
	}
}
