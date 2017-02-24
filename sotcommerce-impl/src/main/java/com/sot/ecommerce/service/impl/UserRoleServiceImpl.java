package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.persistance.UmTbUserRole;
import com.sot.ecommerce.um.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	
	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(UserRoleServiceImpl.class);

	private static final String USER_EMAIL_FIELD_NAME = "vcEml";
	private static final String Store = "storeTbMaster";
	private GenericDAO<UmTbUserRole> dao;

	@Autowired
	public void setDAO(GenericDAO<UmTbUserRole> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(UmTbUserRole.class);
	}
	
	
	
	@Transactional
	public void create(UmTbUserRole userRole) {
		dao.create(userRole);
		
	}

	@Transactional
	public List<UmTbUserRole> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Transactional
	public UmTbUserRole findByID(long id) {
		// TODO Auto-generated method stub
		return dao.findByID(id);
	}

	@Transactional
	public List<UmTbUserRole> findAllByProperty(String propertyName,
			Object value) {
		// TODO Auto-generated method stub
		return dao.findAllByProperty(propertyName, value);
	}

	@Transactional
	public List<UmTbUserRole> findAllByProperty(
			HashMap<String, Object> properties) {
		// TODO Auto-generated method stub
		return dao.findAllByProperty(properties);
	}
	
	

}
