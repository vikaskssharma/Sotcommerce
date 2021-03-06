package com.sot.ecommerce.service.impl;


import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.persistance.OrderBillingTbDtl;
import com.sbsc.fos.persistance.OrderTbMaster;



@Service
public class OrderBillingServiceImpl implements OrderBillingService {

	private GenericDAO<OrderBillingTbDtl> dao;

	@Autowired
	public void setDAO(GenericDAO<OrderBillingTbDtl> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(OrderBillingTbDtl.class);
	}

	@Transactional
	public void create(final OrderBillingTbDtl entity)
	{
		dao.saveOrUpdate(entity);
		
	}

	
	
}

