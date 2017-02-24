package com.sot.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;

import com.sot.ecommerce.entities.Country;

public interface CountryService {

	
	

	public List<Country> findAll();

	public Country findByID(final BigDecimal id);
	
	
	
	
}
