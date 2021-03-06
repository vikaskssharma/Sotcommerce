package com.sot.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.persistance.PaymentStatusTbMaster;

@Service
public class PaymentServiceImpl implements PaymentStatusService{

	private GenericDAO<PaymentStatusTbMaster> dao;
	
	@Autowired
	public void setDAO(GenericDAO<PaymentStatusTbMaster> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(PaymentStatusTbMaster.class);
	}

	
	@Override
	@Transactional
	public PaymentStatusTbMaster findByID(long id) {
		return dao.findByID(id);
	}

}
