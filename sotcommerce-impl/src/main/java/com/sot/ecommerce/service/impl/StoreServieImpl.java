package com.sot.ecommerce.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.persistance.StoreTbMaster;

@Service
public class StoreServieImpl implements StoreService{

	private static final Logger logger = Logger
			.getLogger(StoreServieImpl.class);
	
	private GenericDAO<StoreTbMaster> dao;
	
	@Autowired
	public void setDAO(GenericDAO<StoreTbMaster> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(StoreTbMaster.class);
	}

	
	@Transactional
	public void create(StoreTbMaster entity) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public List<StoreTbMaster> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Transactional
	public StoreTbMaster findByID(long id) {
		// TODO Auto-generated method stub
		return dao.findByID(id);
	}

	@Transactional
	public List<StoreTbMaster> findAllByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<StoreTbMaster> findAllByProperty(HashMap<String, Object> properties) {
		// TODO Auto-generated method stub
		return null;
	}

}
