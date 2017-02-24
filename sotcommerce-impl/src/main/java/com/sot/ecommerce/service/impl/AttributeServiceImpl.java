package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sot.ecommerce.dao.GenericDAO;
import com.sot.ecommerce.dao.SQLDAO;
import com.sot.ecommerce.entitities.CtgrySctnAttrTbDtl;
import com.sot.ecommerce.exception.BusinessFailureException;
import com.sot.ecommerce.service.AttributeService;
import com.sot.ecommerce.service.ProductService;

/**
 * This class provides the implementation of the Services specific to class
 * CtgrySctnAttrTbDtl (Attribute) for all the tasks related to database
 * operations like Create, Read, Update & Delete.
 * 
 * @author simanchal.patra
 * 
 */
@Service
public class AttributeServiceImpl implements AttributeService {

	/** Logger instance. **/
	private static final Logger logger = LoggerFactory
			.getLogger(SectionServiceImpl.class);

	/** The Generic DAO instantiated for CtgrySctnAttrTbDtl */
	private GenericDAO<CtgrySctnAttrTbDtl> dao;

	/** The SQL DAO */
	@Autowired
	private SQLDAO sqlDAO;

	/** The Product Service */
	@Autowired
	private ProductService productService;

	/** Instantiating a copy of Generic DAO for CtgrySctnAttrTbDtl */
	@Autowired
	public void setDAO(GenericDAO<CtgrySctnAttrTbDtl> daoToSet) {
		dao = daoToSet;
		dao.setClazz(CtgrySctnAttrTbDtl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.AttributeService#create(com.sbsc.fos.
	 * persistance.CtgrySctnAttrTbDtl)
	 */
	@Override
	@Transactional
	public Long create(CtgrySctnAttrTbDtl attribute) {
		try {
			return dao.create(attribute);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while creating a new Attribute with name - %s for Category with Id - %s",
							attribute.getVcAttrNm(), attribute
									.getCategoryTbMaster().getNmCtgryId()),
					hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.AttributeService#findAll()
	 */
	@Override
	@Transactional
	public List<CtgrySctnAttrTbDtl> findAll() {
		try {
			return dao.findAll();
		} catch (HibernateException hexp) {
			logger.error(
					"Error occured while Reading all Attributes from System ",
					hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.AttributeService#findByID(long)
	 */
	@Override
	@Transactional
	public CtgrySctnAttrTbDtl findByID(long attributeId) {
		try {
			return dao.findByID(attributeId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Attribute with id - %s",
					String.valueOf(attributeId)), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.AttributeService#findAllByProperty(java
	 * .lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public List<CtgrySctnAttrTbDtl> findAllByProperty(String propertyName,
			Object value) {
		try {
			return dao.findAllByProperty(propertyName, value);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Attribute having"
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
	 * com.sbsc.fos.category.service.AttributeService#findAllByProperty(java
	 * .util.HashMap)
	 */
	@Override
	@Transactional
	public List<CtgrySctnAttrTbDtl> findAllByProperty(
			HashMap<String, Object> propertiesMap) {

		try {
			return dao.findAllByProperty(propertiesMap);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Attribute having"
							+ " properties - %s", propertiesMap.toString()),
					hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.AttributeService#update(com.sbsc.fos.
	 * persistance.CtgrySctnAttrTbDtl)
	 */
	@Override
	@Transactional
	public CtgrySctnAttrTbDtl update(CtgrySctnAttrTbDtl attribute) {

		try {
			return dao.update(attribute);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while updating a Attribute having"
									+ " identifier - %s",
							String.valueOf(attribute.getNmAttrId())), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.AttributeService#delete(com.sbsc.fos.
	 * persistance.CtgrySctnAttrTbDtl)
	 */
	@Override
	@Transactional
	public void delete(CtgrySctnAttrTbDtl attributeToDelete)
			throws BusinessFailureException {

		HashMap<String, Object> criteria = new HashMap<>();

		criteria.put("categoryTbMaster",
				attributeToDelete.getCategoryTbMaster());

		criteria.put("isDltd", new BigDecimal(0));

		try {

			if (productService.findAllByProperty(criteria).size() > 0) {

				throw new BusinessFailureException("unable.to.delete.attribute");
			} else {

				attributeToDelete.setIsDltd(new BigDecimal("1"));
				dao.update(attributeToDelete);
			}
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while deleting a Attribute having"
									+ " identifier - %s",
							String.valueOf(attributeToDelete.getNmAttrId())),
					hexp);
		} finally {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.AttributeService#deleteById(long)
	 */
	@Override
	@Transactional
	public void deleteById(long attributeId) throws BusinessFailureException {

		CtgrySctnAttrTbDtl attributeToDelete = null;

		try {

			attributeToDelete = dao.findByID(attributeId);

			delete(attributeToDelete);

		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while deleting a Attribute having"
									+ " identifier - %s",
							String.valueOf(attributeToDelete.getNmAttrId())),
					hexp);
		} finally {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.AttributeService#saveOrUpdate(com.sbsc.
	 * fos.persistance.CtgrySctnAttrTbDtl)
	 */
	@Transactional
	@Override
	public void saveOrUpdate(CtgrySctnAttrTbDtl attribute) {

		try {
			dao.saveOrUpdate(attribute);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while saving or updating a Attribute having"
							+ " name - %s for Category with Id - %s", attribute
							.getVcAttrNm(), attribute.getCategoryTbMaster()
							.getNmCtgryId()), hexp);
		} finally {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.AttributeService#
	 * getNextAvailableAttributeMapping(java.lang.Long)
	 */
	@Transactional
	@Override
	public String getNextAvailableAttributeMapping(Long categoryId) {

		try {
			return sqlDAO.getNextAvailableAttributeMapping(categoryId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading next Attribute-mapping available"
							+ " for Category with Id - %s", categoryId), hexp);
		} finally {

		}
		return null;
	}

	@Transactional
	@Override
	public String getNextAvailableNumericAttributeMapping(Long categoryId) {

		try {
			return sqlDAO.getNextAvailableNumericAttributeMapping(categoryId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading next Attribute-mapping available"
							+ " for Category with Id - %s", categoryId), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.category.service.AttributeService#
	 * getNextAvailableVariantAttributeMapping(java.lang.Long)
	 */
	@Transactional
	@Override
	public String getNextAvailableVariantAttributeMapping(Long categoryId) {

		try {
			return sqlDAO.getNextAvailableVariantAttributeMapping(categoryId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading next Variant Attribute-mapping"
							+ " available for Category with Id - %s",
					categoryId), hexp);
		} finally {

		}
		return null;
	}

	@Transactional
	@Override
	public String getNextAvailableVariantNumericAttributeMapping(Long categoryId) {

		try {
			return sqlDAO
					.getNextAvailableVariantNumericAttributeMapping(categoryId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading next Numeic Variant"
							+ " Attribute-mapping available for"
							+ " Category with Id - %s", categoryId), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.AttributeService#findAllNameId(java.lang
	 * .Long)
	 */
	@Transactional
	@Override
	public HashMap<String, Long> findAllNameId(Long categoryId) {

		try {
			return sqlDAO.findAllAttributeNameID(categoryId);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while reading the Name and Id of"
									+ " all Attributes for the specified"
									+ " Category having Id - %s",
							String.valueOf(categoryId)), hexp);
		} finally {

		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.AttributeService#getVariantAttributeCount
	 * (java.lang.Long)
	 */
	@Transactional
	@Override
	public Integer getVariantAttributeMappingCount(Long categoryId) {

		try {
			return sqlDAO.getVariantAttributeMappingCount(categoryId);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while reading the count of the total"
									+ " Variant Attributes for the specified"
									+ " Category having Id - %s",
							String.valueOf(categoryId)), hexp);
		} finally {

		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.category.service.AttributeService#getAttributeMappingCount
	 * (java.lang.Long)
	 */
	@Transactional
	@Override
	public Integer getAttributeMappingCount(Long categoryId) {

		try {
			return sqlDAO.getAttributeMappingCount(categoryId);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while reading the count of the total"
									+ " Attributes for the specified"
									+ " Category having Id - %s",
							String.valueOf(categoryId)), hexp);
		} finally {

		}

		return null;
	}

	@Transactional
	@Override
	public Integer getNumericAttributeMappingCount(Long categoryId) {

		try {
			return sqlDAO.getNumericAttributeMappingCount(categoryId);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while reading the count of the total"
									+ " Numeric Attributes for the specified"
									+ " Category having Id - %s",
							String.valueOf(categoryId)), hexp);
		} finally {

		}

		return null;
	}

	@Transactional
	@Override
	public Integer getImagableAttributeMappingCount(Long categoryId) {

		try {
			return sqlDAO.getImagableAttributeMappingCount(categoryId);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while reading the count of the total"
									+ " Attributes for the specified"
									+ " Category having Id - %s",
							String.valueOf(categoryId)), hexp);
		} finally {

		}

		return null;
	}

	@Transactional
	@Override
	public String getNextAvailableNumericUnitAttributeMapping(Long categoryId) {
		try {
			return sqlDAO
					.getNextAvailableNumericUnitAttributeMapping(categoryId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading next Numeic Unit"
							+ " Attribute-mapping available for"
							+ " Category with Id - %s", categoryId), hexp);
		} finally {

		}
		return null;
	}

	@Transactional
	@Override
	public String getNextAvailableNumericUnitVariantAttributeMapping(
			Long categoryId) {
		try {
			return sqlDAO
					.getNextAvailableNumericUnitVariantAttributeMapping(categoryId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading next Numeic Unit"
							+ " mapping for Variant Attribute available for"
							+ " Category with Id - %s", categoryId), hexp);
		} finally {

		}
		return null;
	}

}
