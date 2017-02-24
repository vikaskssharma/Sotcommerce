package com.sot.ecommerce.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface GenericDAO<T> {

	public void setClazz(Class<T> clazz);

	public void create(final T entity);

	public List<T> findAll();

	public T findByID(final BigDecimal id);

	public <T> List<T> findAllByProperty(String propertyName, Object value);

	public <T> List<T> findAllByProperty(HashMap<String, Object> propertiesMap);

	public T update(final T entity);

	public void delete(final T entity);

	public void deleteById(final long entityId);

	List<?> executeSQLQuery(String sqlQuery);
	
	public void saveOrUpdate(T entity);
	
	

	public ResultSet executePreparedStatement(String query, ArrayList listOfParams) throws SQLException;

	T findByID(long id);
}
