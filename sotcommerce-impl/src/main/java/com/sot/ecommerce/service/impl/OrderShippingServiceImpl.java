package com.sot.ecommerce.service.impl;


import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.persistance.OrderProductTbDetail;
import com.sbsc.fos.persistance.OrderShippingTbDtl;
import com.sbsc.fos.persistance.OrderTbMaster;



@Service
public class OrderShippingServiceImpl implements OrderShippingService {

	private GenericDAO<OrderShippingTbDtl> dao;

	@Autowired
	public void setDAO(GenericDAO<OrderShippingTbDtl> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(OrderShippingTbDtl.class);
	}
	

	@Transactional
	public void create(OrderShippingTbDtl entity) {
		
		dao.saveOrUpdate(entity);
		
	}

	
	
	
}

