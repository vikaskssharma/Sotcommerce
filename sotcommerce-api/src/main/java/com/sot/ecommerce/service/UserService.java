package com.sot.ecommerce.service;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import com.sot.ecommerce.entities.User;
import com.sot.ecommerce.exception.BusinessFailureException;
import com.sot.ecommerce.exception.GenericFailureException;


public interface UserService {

	public void create(final User entity);

	public List<User> findAll();

	public User findByID(final long id);
	
	public User findByID(final BigDecimal id);

	public List<User> findAllByProperty(String propertyName, Object value);

	public List<User> findAllByProperty(HashMap<String, Object> properties);

	public void update(final User entity);

	public void delete(final User entity);

	public void deleteById(final long entityId);

	public void signIn(User user, String usrRoleName) throws BusinessFailureException,
			GenericFailureException, NoSuchAlgorithmException, NoSuchPaddingException;

	public void signUp(User user, String usrRoleName) throws BusinessFailureException,
			GenericFailureException;

	

}
