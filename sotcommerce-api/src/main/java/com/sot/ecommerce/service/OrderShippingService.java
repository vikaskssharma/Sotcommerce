package com.sot.ecommerce.service;


import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.persistance.OrderShippingTbDtl;
import com.sbsc.fos.persistance.OrderTbMaster;



@Service
public interface OrderShippingService {


	public void create(final OrderShippingTbDtl entity);

	
	
}

