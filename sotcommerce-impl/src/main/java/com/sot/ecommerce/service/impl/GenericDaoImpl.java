/**
 * (c) R Systems International Ltd.
 */
package com.sot.ecommerce.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.sbsc.fos.persistance.ProductTbMaster;
import com.sot.ecommerce.utils.DateUtil;
import com.sot.ecommerce.utils.SBSConstants;

/**
 * The purpose of using a Generic DAO is the CRUD operations that can be
 * performed on each entity.
 * 
 * @author simanchal.patra
 */
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericDaoImpl<T> implements GenericDAO<T> {

	/** Logger instance. **/
	private static final Logger logger = Logger.getLogger(GenericDaoImpl.class);

	/** The Class represents the Entity Class */
	private Class<T> clazz;

	/** The Hibernate Session Factory */
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.common.dao.GenericDAO#setClazz(java.lang.Class)
	 */
	@Override
	public final void setClazz(Class<T> clazzToSet) {

		this.clazz = clazzToSet;

		logger.info(String.format(
				"Generic DAO insantiated for peristence class - %s",
				clazzToSet.toString()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.common.dao.GenericDAO#create(java.lang.Object)
	 */
	@Override
	public Long create(T entity) throws HibernateException {
		return (Long) getCurrentSession().save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.common.dao.GenericDAO#findAll()
	 */
	@Override
	public List<T> findAll() throws HibernateException {
		return getCurrentSession().createQuery("from " + clazz.getName())
				.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.common.dao.GenericDAO#findByID(long)
	 */
	@Override
	public T findByID(long id) throws HibernateException {
		return (T) getCurrentSession().get(clazz, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.common.dao.GenericDAO#findAllByProperty(java.lang.String,
	 * java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByProperty(String propertyName, Object value)
			throws HibernateException {
		Criteria crit = getCurrentSession().createCriteria(clazz.getName());
		crit.add(Restrictions.eq(propertyName, value));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByStringPropertyIgnoreCase(String propertyName,
			String value) throws HibernateException {
		Criteria crit = getCurrentSession().createCriteria(clazz.getName());
		value = StringUtils.trim(value);
		crit.add(Restrictions.eq(propertyName, value).ignoreCase());
		return crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.common.dao.GenericDAO#findAllByProperty(java.util.HashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByProperty(Map<String, Object> propertiesMap)
			throws HibernateException {

		Criteria crit = getCurrentSession().createCriteria(clazz);
		for (String propertyName : propertiesMap.keySet()) {
			if (propertyName.equalsIgnoreCase("desc"))
				crit.addOrder(Order.desc(propertiesMap.get(propertyName)
						.toString()));
			else if (propertyName.equalsIgnoreCase("asc"))
				crit.addOrder(Order.asc(propertiesMap.get(propertyName)
						.toString()));
			else if (propertyName.equalsIgnoreCase("count"))
				crit.setMaxResults((int) propertiesMap.get(propertyName));
			else
				crit.add(Restrictions.eq(propertyName,
						propertiesMap.get(propertyName)));
		}
		return crit.list();
	}
/*
 * (non-Javadoc) This method is used when date type is calendar.
 * @see com.sbsc.fos.common.dao.GenericDAO#findAllByPropertyCountandDate(java.util.Map)
 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllByPropertyCountandDate(
			Map<String, Object> propertiesMap) throws HibernateException {

		Criteria crit = getCurrentSession().createCriteria(clazz);
		for (String propertyName : propertiesMap.keySet()) {
			if (propertyName.equalsIgnoreCase("desc"))
				crit.addOrder(Order.desc(propertiesMap.get(propertyName)
						.toString()));
			else if (propertyName.equalsIgnoreCase("asc"))
				crit.addOrder(Order.asc(propertiesMap.get(propertyName)
						.toString()));
			else if (propertyName.equalsIgnoreCase("count"))
				crit.setMaxResults((int) propertiesMap.get(propertyName));
			else if (propertyName.contains("Cal_")) {

				try {
					DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
					// get current date time with Date()
					Date date = new Date();
					String datetype = dateFormat.format(date);
					System.out.println();
					Date date1 = dateFormat.parse(datetype);
					// get current date time with Calendar()
					Calendar todaystartdate = Calendar.getInstance();
					todaystartdate.setTimeInMillis(date1.getTime());
					todaystartdate.add(Calendar.HOUR, 00);
					todaystartdate.add(Calendar.MINUTE, 00);
					todaystartdate.add(Calendar.SECOND, 00);
					Calendar todayenddate = Calendar.getInstance();
					todayenddate.setTimeInMillis(date1.getTime());
					todayenddate.add(Calendar.HOUR, 23);
					todayenddate.add(Calendar.MINUTE, 59);
					todayenddate.add(Calendar.SECOND, 59);
							System.out.println("start date and end date::::"
							+ todaystartdate + " " + todayenddate);

					crit.add(Restrictions.between(propertyName.substring(4), todaystartdate,
							todayenddate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else
				crit.add(Restrictions.eq(propertyName,
						propertiesMap.get(propertyName)));
		}

		return crit.list();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.common.dao.GenericDAO#update(java.lang.Object)
	 */
	@Override
	public T update(T entity) throws HibernateException {
		return (T) getCurrentSession().merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.common.dao.GenericDAO#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public void saveOrUpdate(T entity) throws HibernateException {

		getCurrentSession().saveOrUpdate(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.common.dao.GenericDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(T entity) throws HibernateException {
		getCurrentSession().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.common.dao.GenericDAO#deleteById(long)
	 */
	@Override
	public void deleteById(long entityId) throws HibernateException {
		T entity = findByID(entityId);
		delete(entity);

	}

	/**
	 * Provides the current Hibernate Session.
	 * 
	 * @return
	 * @throws HibernateException
	 */
	protected final Session getCurrentSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<T> findAllByFilters(Map<String, Object> filter_criteria)
			throws ParseException {

		// Create Criteria with Parent table.
		Criteria criteria = getCurrentSession().createCriteria(clazz.getName());
		// Add Sorted order as per the last value of filter_criteria
		if (filter_criteria.keySet().contains("desc")) {
			criteria.addOrder(Order
					.desc(filter_criteria.get("desc").toString()));
			filter_criteria.remove("desc");
		} else if (filter_criteria.keySet().contains("asc")) {
			criteria.addOrder(Order.asc(filter_criteria.get("asc").toString()));
			filter_criteria.remove("asc");
		}

		Object[] tableMapping = filter_criteria.keySet().toArray();
		String baseTable = null;
		Object[] filterValue = filter_criteria.values().toArray();
		List<String> trackAlias = new ArrayList<String>();

		// Loop to add Restriction at runtime.
		for (int offset = 0; offset < filter_criteria.size(); offset++) {
			// baseTable = (String) tableMapping.get(offset);
			int start = tableMapping[offset].toString().indexOf('.');
			int limit = tableMapping[offset].toString().lastIndexOf('.');
			baseTable = (String) tableMapping[offset].toString().substring(
					start + 1, limit);

			// If property is not Null.
			if (filterValue[offset] != null) {
				String param = filter_criteria.keySet().toArray()[offset]
						.toString();
				// If Property belong to nested table.
				if (baseTable.indexOf(".") != -1) {
					String[] tableHierarchy = baseTable.split("\\.");
					// I Consider that the way to reach to specific Pojo is same
					// from parent Pojo.
					String alias = null;
					if (trackAlias
							.contains(tableHierarchy[tableHierarchy.length - 1])) {
						alias = tableHierarchy[tableHierarchy.length - 1]
								+ "_alias.";
					} else {
						for (int i = 1; i < tableHierarchy.length; i++) {
							if (i == 1) {
								criteria.createAlias(tableHierarchy[i],
										tableHierarchy[i] + "_alias");
								alias = tableHierarchy[i] + "_alias.";
							} else {
								criteria.createAlias(alias + tableHierarchy[i],
										tableHierarchy[i] + "_alias");
								alias = tableHierarchy[i] + "_alias.";
							}
							trackAlias.add(tableHierarchy[i]);
						}
					}
					// If type is Calendar.
					if (filterValue[offset].toString().contains("Calendar_")) {
						Calendar order_start_date = Calendar.getInstance();
						Calendar order_end_date = Calendar.getInstance();
						order_start_date.setTimeInMillis(Long
								.valueOf(filterValue[offset].toString()
										.substring(9)));
						order_end_date.setTimeInMillis(Long
								.valueOf(filterValue[offset].toString()
										.substring(9)));
						order_end_date.add(Calendar.HOUR, 23);
						order_end_date.add(Calendar.MINUTE, 59);
						order_end_date.add(Calendar.SECOND, 59);
						criteria.add(Restrictions.between(param.substring(
								param.lastIndexOf('.') + 1, param.length()),
								order_start_date, order_end_date));
					}
					// If type is Date.
					else if (filterValue[offset].toString().contains("Date_")) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						Date order_start_date = sdf.parse(filterValue[offset]
								.toString().substring(5) + " 00:00:00");
						Date order_end_date = sdf.parse(filterValue[offset]
								.toString().substring(5) + " 23:59:59");
						criteria.add(Restrictions.between(param.substring(
								param.lastIndexOf('.') + 1, param.length()),
								order_start_date, order_end_date));
					}// If type is String.
					else if (filterValue[offset].getClass()
							.equals(String.class)) {

						if (param.substring(param.lastIndexOf('.') + 1,
								param.length()).equalsIgnoreCase("vcstts")) {

							criteria.add(Restrictions.eq(
									alias
											+ param.substring(
													param.lastIndexOf('.') + 1,
													param.length()),
									filterValue[offset]).ignoreCase());

						} else {
							String like_param = "%" + filterValue[offset] + "%";
							criteria.add(Restrictions.ilike(
									alias
											+ param.substring(
													param.lastIndexOf('.') + 1,
													param.length()), like_param));

						}

					} else
						criteria.add(Restrictions.eq(
								alias
										+ param.substring(
												param.lastIndexOf('.') + 1,
												param.length()),
								filterValue[offset]));
				}
				// If Property belong to parent/base table.
				else {
					// If type is Calendar.
					if (filterValue[offset].toString().contains("Calendar_")) {
						Calendar order_start_date = Calendar.getInstance();
						Calendar order_end_date = Calendar.getInstance();

						order_start_date.setTimeInMillis(Long
								.valueOf(filterValue[offset].toString()
										.substring(9)));
						order_end_date.setTimeInMillis(Long
								.valueOf(filterValue[offset].toString()
										.substring(9)));
						order_end_date.add(Calendar.HOUR, 23);
						order_end_date.add(Calendar.MINUTE, 59);
						order_end_date.add(Calendar.SECOND, 59);
						criteria.add(Restrictions.between(param.substring(
								param.lastIndexOf('.') + 1, param.length()),
								order_start_date, order_end_date));
					}
					// If type is Date.
					else if (filterValue[offset].toString().contains("Date_")) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						Date order_start_date = sdf.parse(filterValue[offset]
								.toString().substring(5) + " 00:00:00");
						Date order_end_date = sdf.parse(filterValue[offset]
								.toString().substring(5) + " 23:59:59");
						criteria.add(Restrictions.between(param.substring(
								param.lastIndexOf('.') + 1, param.length()),
								order_start_date, order_end_date));
					} // If type is String.
					else if (filterValue[offset].getClass()
							.equals(String.class)) {

						// Modified for Status filter
						if (param.substring(param.lastIndexOf('.') + 1,
								param.length()).equalsIgnoreCase("vcstts")) {
							criteria.add(Restrictions.eq(
									param.substring(param.lastIndexOf('.') + 1,
											param.length()),
									filterValue[offset]).ignoreCase());
						} else {
							String like_param = "%" + filterValue[offset] + "%";
							criteria.add(Restrictions.ilike(
									param.substring(param.lastIndexOf('.') + 1,
											param.length()), like_param));
						}

					} else {
						criteria.add(Restrictions.eq(param.substring(
								param.lastIndexOf('.') + 1, param.length()),
								filterValue[offset]));
					}
				}
			}
		}

		return criteria.list();
	}

	@Override
	public List<ProductTbMaster> executeSQLQuery(String sqlQuery,
			LinkedHashMap<String, Type> scalarMapping)
			throws HibernateException {
		// throw new UnsupportedOperationException();

		SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);

		for (String columnName : scalarMapping.keySet()) {

			query.addScalar(columnName, scalarMapping.get(columnName));
		}

		System.out.println("query  ===> " + query);

		return query.list();

	}

	@Override
	public List<?> executeSQLQuery(String sqlQuery) throws HibernateException {

		Query query = getCurrentSession().createSQLQuery(sqlQuery);

		return query.list();
	}

}
