package com.sot.ecommerce.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.sot.ecommerce.entities.Role;


public interface RoleService {

	public void create(final Role entity);

	public List<Role> findAll();

	public Role findByID(final long id);
	
	public Role findByID(final BigDecimal id);

	public List<Role> findAllByProperty(String propertyName, Object value);

	public List<Role> findAllByProperty(HashMap<String, Object> properties);
}
