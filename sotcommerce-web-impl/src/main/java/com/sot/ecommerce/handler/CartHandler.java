package com.sot.ecommerce.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.order.service.PaymentStatusService;
import com.sbsc.fos.persistance.PaymentStatusTbMaster;



@Component
public class CartHandler {
	
	@Autowired
	private PaymentStatusService  paymentStatusService;
	
	
	public PaymentStatusTbMaster getPaymentStatusById(long id) {

		PaymentStatusTbMaster paymentStatus = paymentStatusService.findByID(id);

		return paymentStatus;
	}

}
