package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.persistance.UmTbRole;
import com.sot.ecommerce.um.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(RoleServiceImpl.class);

	private GenericDAO<UmTbRole> dao;

	@Autowired
	public void setDAO(GenericDAO<UmTbRole> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(UmTbRole.class);
	}

	@Transactional
	public void create(UmTbRole entity) {
		 dao.create(entity);

	}

	@Transactional
	public List<UmTbRole> findAll() {
		
		return dao.findAll();
	}

	@Transactional
	public UmTbRole findByID(long id) {
		// TODO Auto-generated method stub
		return dao.findByID(id);
	}

	@Transactional
	public UmTbRole findByID(BigDecimal id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<UmTbRole> findAllByProperty(String propertyName, Object value) {
		return dao.findAllByProperty(propertyName, value);
	}

	@Transactional
	public List<UmTbRole> findAllByProperty(HashMap<String, Object> properties) {
		
		return dao.findAllByProperty(properties);
	}

}
