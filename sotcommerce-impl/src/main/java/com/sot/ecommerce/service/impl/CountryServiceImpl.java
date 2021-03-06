package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.persistance.UmTbCntryMstr;
import com.sot.ecommerce.um.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(CountryServiceImpl.class);

	

	private GenericDAO<UmTbCntryMstr> dao;
	
	@Autowired
	public void setDAO(GenericDAO<UmTbCntryMstr> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(UmTbCntryMstr.class);
	}
	
	
	@Transactional
	public List<UmTbCntryMstr> findAll() {
		return dao.findAll();
	}

	@Transactional
	public UmTbCntryMstr findByID(long id) {
		// TODO Auto-generated method stub
		return dao.findByID(id);
	}

}
