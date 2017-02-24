package com.sot.ecommerce.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.order.common.BillingVO;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.order.common.ShippingVO;
import com.sbsc.fos.persistance.OrderBillingTbDtl;
import com.sbsc.fos.persistance.OrderShippingTbDtl;
import com.sbsc.fos.persistance.OrderTbMaster;
import com.sbsc.fos.persistance.ReviewNRatingTbDetail;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sbsc.fos.persistance.TaxationTbDetail;
import com.sot.ecommerce.um.service.StoreService;
import com.sot.ecommerce.um.service.UserService;

@Service
public class TaxationServiceImpl implements TaxationService {

	private GenericDAO<TaxationTbDetail> dao;

	private long usrID;

	private long storeID;

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(TaxationServiceImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	@Autowired
	public void setDAO(GenericDAO<TaxationTbDetail> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(TaxationTbDetail.class);
	}

	@Transactional
	public List<TaxationTbDetail> findAllByProperty(
			HashMap<String, Object> propertiesMap) {
		return dao.findAllByProperty(propertiesMap);
	}

	


	/*
	 * returns a Map containing user info from session object
	 * 
	 * 
	 * @param sessionInfo
	 * 
	 * @return
	 */
	public HashMap<String, Object> getUserMap(SessionInfo sessionInfo) {
		usrID = sessionInfo.getUserId();
		storeID = sessionInfo.getStoreId();
		HashMap<String, Object> userMap = new HashMap<>();
		userMap.put("umTbUser", userService.findByID(usrID));
		userMap.put("storeTbMaster", storeService.findByID(storeID));
		return userMap;
	}

	@Transactional
	public boolean updateTaxation(TaxationTbDetail txn) {
		 dao.update(txn);
		 return true;
	}
	
	
	

}
