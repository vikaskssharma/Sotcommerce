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
import com.sbsc.fos.persistance.SimilarProductTbDtl;

/**
 ** This class provides the implementation of the Services specific to class
 * SimilarProductTbDtl (similarProducts) for all the tasks related to database
 * operations like Create, Read, Update & Delete.

 * @author diksha.rattan
 *
 */
@Service
public class SimilarProductServiceImpl implements SimilarProductService{
	
	
	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(SectionServiceImpl.class);

	/** The Generic DAO instantiated for SimilarProductTbDtl */
	private GenericDAO<SimilarProductTbDtl> dao;

	/** The SQL DAO */
	@Autowired
	private SQLDAO sqlDAO;

	
	/** Instantiating a copy of Generic DAO for SimilarProductTbDtl */
	@Autowired
	public void setDAO(GenericDAO<SimilarProductTbDtl> daoToSet) {
		dao = daoToSet;
		dao.setClazz(SimilarProductTbDtl.class);
	}
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.similarProductService#create(com.sbsc.fos.persistance.SimilarProductTbDtl)
	 */
	@Override
	@Transactional
	public Long create(SimilarProductTbDtl similarProduct) {
		try {
			return dao.create(similarProduct);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while creating a new similarProduct with name - %s for Category with Id - %s",
							similarProduct.getNmSmlrId()),
					hexp);
		} finally {

		}
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.similarProductService#findAll()
	 */
	@Override
	@Transactional
	public List<SimilarProductTbDtl> findAll() {
		try {
			return dao.findAll();
		} catch (HibernateException hexp) {
			logger.error(
					"Error occured while Reading all similarProducts from System ",
					hexp);
		} finally {

		}
		return null;
	}

	/*
	 * 	@Override(non-Javadoc)
	 * @see com.sbsc.fos.product.service.similarProductService#findByID(long)
	 */
	@Transactional
	public SimilarProductTbDtl findByID(long similarProductId) {
		try {
			return dao.findByID(similarProductId);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a similarProduct with id - %s",
					String.valueOf(similarProductId)), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.similarProductService#findAllByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	@Transactional
	public List<SimilarProductTbDtl> findAllByProperty(String propertyName,
			Object value) {
		try {
			return dao.findAllByProperty(propertyName, value);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a similarProduct having"
							+ " property name- %s and property value - %s",
					propertyName, value.toString()), hexp);
		} finally {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.similarProductService#findAllByProperty(java.util.HashMap)
	 */
	@Override
	@Transactional
	public List<SimilarProductTbDtl> findAllByProperty(
			HashMap<String, Object> propertiesMap) {

		try {
			return dao.findAllByProperty(propertiesMap);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a similarProduct having"
							+ " properties - %s", propertiesMap.toString()),
					hexp);
		} finally {

		}
		return null;
	}

	/*
	 * 	@Override(non-Javadoc)
	 * @see com.sbsc.fos.product.service.similarProductService#update(com.sbsc.fos.persistance.SimilarProductTbDtl)
	 */
	@Transactional
	public SimilarProductTbDtl update(SimilarProductTbDtl similarProduct) {

		try {
			return dao.update(similarProduct);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while updating a similarProduct having"
									+ " identifier - %s",
							String.valueOf(similarProduct.getNmSmlrId())), hexp);
		} finally {

		}
		return null;
	}

	
	
	/*
	 * 	@Transactional(non-Javadoc)
	 * @see com.sbsc.fos.product.service.similarProductService#saveOrUpdate(com.sbsc.fos.persistance.SimilarProductTbDtl)
	 */
	@Override
	public void saveOrUpdate(SimilarProductTbDtl similarProduct) {

		try {
			dao.saveOrUpdate(similarProduct);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while saving or updating a similarProduct having"
							+ " name - %s for Category with Id - %s", similarProduct
							.getNmSmlrId()));
		} finally {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.similarProductService#delete(com.sbsc.fos
	 * .persistance.SimilarProductTbDtl)
	 */
	@Override
	public void delete(SimilarProductTbDtl similarProductToDelete)
			throws BusinessFailureException {
		// TODO Auto-generated method stub
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.sbsc.fos.product.service.similarProductService#deleteById(long)
	 */
	@Override
	public void deleteById(long similarProductId)
			throws BusinessFailureException {
		// TODO Auto-generated method stub
		
	}

}
