package com.sot.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.persistance.PaymentStatusTbMaster;



@Service
public interface PaymentStatusService {
	
	@Transactional
	public PaymentStatusTbMaster findByID(final long l);

}
