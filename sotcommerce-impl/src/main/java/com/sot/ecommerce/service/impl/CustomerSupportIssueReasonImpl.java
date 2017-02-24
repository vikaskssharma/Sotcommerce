package com.sot.ecommerce.service.impl;



import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.persistance.CategoryTbMaster;
import com.sbsc.fos.persistance.IssueReasonTbMaster;


@Service
public class CustomerSupportIssueReasonImpl implements CustomerSupportIssueReason {
	private static final Logger logger = Logger
			.getLogger(CustomerSupportIssueReasonImpl.class);
	
	
	private GenericDAO<IssueReason> dao;
	
	

	public GenericDAO<IssueReason> getDao() {
		return dao;
	}

	@Autowired
	public void setDao(GenericDAO<IssueReason> daoToSet) {
		dao = daoToSet;
		dao.setClazz(IssueReason.class);
	}

	@Transactional
	@Override
	public Long saveCustomerQuery(IssueReason customerSupportIssueReason) {
		try {
			return dao.create(customerSupportIssueReason);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while creating a new Cateegory with name - %s for Store with Id - %s",
							customerSupportIssueReason.getVcIssRsnNm()) );
		} finally {

		}
		return null;
	}
	
	@Transactional
	@Override
	public List<IssueReason> IssueFindAll() {
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
	
	@Transactional
	@Override
	public List<IssueReason> findAllByProperty(String propertyName,Object value) {
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
	@Transactional
	@Override
	public IssueReason findById(String string, long reasonid) {
		 try {
       	  return dao.findByID(reasonid);
 			  
         } catch (HibernateException hexp) {
 			logger.error(String.format(
 					"Error occured while reading a Cateegory having"+ " properties - %s", reasonid),
 					hexp);
 		} finally {

 		}
 		return null;
	}
	
}
