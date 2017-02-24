package com.sot.ecommerce.service;

/**
 * 
 * @author gaurav.kumar
 */

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;





@Service
public interface TaxationService {
	
	public List<Taxation> findAllByProperty(HashMap<String, Object> propertiesMap);
	
	public boolean updateTaxation(Taxation txn) ;

	
}
