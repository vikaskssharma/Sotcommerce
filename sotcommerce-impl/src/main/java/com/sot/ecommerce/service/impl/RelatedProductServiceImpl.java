/**
 * 
 */
package com.sot.ecommerce.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.category.service.SectionServiceImpl;
import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.common.dao.SQLDAO;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.persistance.RelatedProductTbDtl;

/**
 ** This class provides the implementation of the Services specific to class
 * RelatedProductTbDtl (RelatedProducts) for all the tasks related to database
 * operations like Create, Read, Update & Delete.

 * @author diksha.rattan
 *
 */
@Service
public class RelatedProductServiceImpl implements RelatedProductService{
	
	
	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(SectionServiceImpl.class);

	/** The Generic DAO instantiated for RelatedProductTbDtl */
	private GenericDAO<RelatedProductTbDtl> dao;

	/** The SQL DAO */
	@Autowired
	private SQLDAO sqlDAO;

	
	/** Instantiating a copy of Generic DAO for RelatedProductTbDtl */
	@Autowired
	public void setDAO(GenericDAO<RelatedProductTbDtl> daoToSet) {
		dao = daoToSet;
		dao.setClazz(RelatedProductTbDtl.class);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.RelatedProductService#create(com.sbsc.fos.persistance.RelatedProductTbDtl)
	 */
	@Override
	@Transactional
	public Long create(RelatedProductTbDtl relatedProduct) {
		try {
			return dao.create(relatedProduct);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while creating a new relatedProduct with name - %s for Category with Id - %s",
							relatedProduct.getNmRltdId()),
					hexp);
		} finally {

		}
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.RelatedProductService#findAll()
	 */
	@Override
	@Transactional
	public List<RelatedProductTbDtl> findAll() {
		try {
			return dao.findAll();
		} catch (HibernateException hexp) {
			logger.error(
					"Error occured while Reading all relatedProducts from System ",
					hexp);
		} finally {

		}
		return null;
	}

	/*
	 * 	@Override(non-Javadoc)
	 * @see com.sbsc.fos.product.service.RelatedProductService#findByID(long)
	 */
	@Transactional
	public RelatedProductTbDtl findByID(long relatedProductId) {
		try {
			return dao.findByID(relatedProductId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a relatedProduct with id - %s",
					String.valueOf(relatedProductId)), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.RelatedProductService#findAllByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public List<RelatedProductTbDtl> findAllByProperty(String propertyName,
			Object value) {
		try {
			return dao.findAllByProperty(propertyName, value);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a relatedProduct having"
							+ " property name- %s and property value - %s",
					propertyName, value.toString()), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.RelatedProductService#findAllByProperty(java.util.HashMap)
	 */
	@Override
	@Transactional
	public List<RelatedProductTbDtl> findAllByProperty(
			HashMap<String, Object> propertiesMap) {

		try {
			return dao.findAllByProperty(propertiesMap);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a relatedProduct having"
							+ " properties - %s", propertiesMap.toString()),
					hexp);
		} finally {

		}
		return null;
	}

	/*
	 * 	@Override(non-Javadoc)
	 * @see com.sbsc.fos.product.service.RelatedProductService#update(com.sbsc.fos.persistance.RelatedProductTbDtl)
	 */
	@Transactional
	public RelatedProductTbDtl update(RelatedProductTbDtl relatedProduct) {

		try {
			return dao.update(relatedProduct);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while updating a relatedProduct having"
									+ " identifier - %s",
							String.valueOf(relatedProduct.getNmRltdId())), hexp);
		} finally {

		}
		return null;
	}

	
	
	/*
	 * 	@Transactional(non-Javadoc)
	 * @see com.sbsc.fos.product.service.RelatedProductService#saveOrUpdate(com.sbsc.fos.persistance.RelatedProductTbDtl)
	 */
	@Override
	public void saveOrUpdate(RelatedProductTbDtl relatedProduct) {

		try {
			dao.saveOrUpdate(relatedProduct);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while saving or updating a relatedProduct having"
							+ " name - %s for Category with Id - %s", relatedProduct
							.getNmRltdId()));
		} finally {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.RelatedProductService#delete(com.sbsc.fos
	 * .persistance.RelatedProductTbDtl)
	 */
	@Override
	public void delete(RelatedProductTbDtl relatedProductToDelete)
			throws BusinessFailureException {
		// TODO Auto-generated method stub
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.RelatedProductService#deleteById(long)
	 */
	@Override
	public void deleteById(long relatedProductId)
			throws BusinessFailureException {
		// TODO Auto-generated method stub
		
	}

}
