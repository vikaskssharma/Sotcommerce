package com.sot.ecommerce.service;



import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.persistance.CustomerSupport;



public interface CustomerSupportService {
	String message = null;
	public Long saveCustomerQuery(CustomerSupport customerSupport);
	public List<CustomerSupport> saveCustomerIssueFindAll();
	public List<CustomerSupport> findAllByPropertyMap(HashMap<String, Object> propertiesMap) ;
	public List<CustomerSupport> findAllByProperty(String propertyName, Object value);
	public String deletebyrequestnumber(String requestnumber,SessionInfo sessionInfo);
	public String updateCustomerSupport(CustomerSupport custSupport);
	public CustomerSupport findbyId(long requestnumber);
	public List<CustomerSupport> findAllCustomerQueriesByFilters(
			Map<String, Object> filter_criteria) throws ParseException;
	
}
