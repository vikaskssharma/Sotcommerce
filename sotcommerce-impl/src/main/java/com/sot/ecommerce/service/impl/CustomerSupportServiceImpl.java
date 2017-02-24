package com.sot.ecommerce.service.impl;


import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.category.commons.CategoryVO;
import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.persistance.CategoryTbMaster;
import com.sbsc.fos.persistance.CustomerSupport;






@Service

public class CustomerSupportServiceImpl implements CustomerSupportService {

	private static final Logger logger = Logger
			.getLogger(CustomerSupportServiceImpl.class);
	
	
	private GenericDAO<CustomerSupport> dao;
	
	

	public GenericDAO<CustomerSupport> getDao() {
		return dao;
	}

	@Autowired
	public void setDao(GenericDAO<CustomerSupport> daoToSet) {
		dao = daoToSet;
		dao.setClazz(CustomerSupport.class);
	}

	@Transactional
	@Override
	public Long saveCustomerQuery(CustomerSupport customerSupport) {
		try {
			return dao.create(customerSupport);
		} catch (HibernateException hexp) {
			logger.error(
					String.format(
							"Error occured while creating a new Cateegory with name - %s for Store with Id - %s",
							customerSupport.getNmCrtdBy())) ;
		} finally {

		}
		return null;
	}
	
	@Transactional
	@Override
	public List<CustomerSupport> saveCustomerIssueFindAll() {
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
	public List<CustomerSupport> findAllByProperty(
			String propertyName, Object value) {
		// TODO Auto-generated method stub
		try {
			return dao.findAllByProperty(propertyName,value);
		} catch (HibernateException hexp) {
			logger.error(String.format(
					"Error occured while reading a Cateegory having"
							+ " properties - %s", propertyName),
					hexp);
		} finally {

		}
		return null;
	}
	
	@Transactional
	@Override
	public String deletebyrequestnumber(String requestnumber,
			SessionInfo sessionInfo) {
		String message="record is deleted";
   List<CustomerSupport> customersuport=dao.findAllByProperty("vcRqstNmbr",requestnumber);
              CustomerSupport custSupportObj=customersuport.get(0);
              try {
      			  dao.delete(custSupportObj);
              } catch (HibernateException hexp) {
      			logger.error(String.format(
      					"Error occured while reading a Cateegory having"
      							+ " properties - %s", requestnumber),
      					hexp);
      		} finally {

      		}
      		return message;
		
	}
	
	@Transactional
	@Override
	public String updateCustomerSupport(CustomerSupport custSupport) {
		String message="";
              try {
      			  dao.update(custSupport);
      			  message="value is inserted";
              } catch (HibernateException hexp) {
      			logger.error(String.format(
      					"Error occured while reading a Cateegory having"+ " properties - %s", custSupport),
      					hexp);
      		} finally {

      		}
      		return message;
		
	}
	
	@Transactional
	@Override
	public CustomerSupport findbyId(long requestnumber) {
		
              try {
            	  return dao.findByID(requestnumber);
      			  
              } catch (HibernateException hexp) {
      			logger.error(String.format(
      					"Error occured while reading a Cateegory having"+ " properties - %s", requestnumber),
      					hexp);
      		} finally {

      		}
      		return null;
		
	}
	
	@Transactional
	@Override
	public List<CustomerSupport> findAllByPropertyMap(
			HashMap<String, Object> propertiesMap) {
			try {
			return dao.findAllByProperty(propertiesMap);
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
	public List<CustomerSupport> findAllCustomerQueriesByFilters(Map<String, Object> filter_criteria) throws ParseException {
		try {
		return dao.findAllByFilters(filter_criteria);
		} catch (HibernateException hexp) {
			logger.error(
					"Error occured while Reading all Cateegories from System ",
					hexp);
		} finally {

		}
		return null;
	
	
	}

	
 
}
