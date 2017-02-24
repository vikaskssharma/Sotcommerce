package com.sot.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.persistance.OrderTbMaster;
import com.sbsc.fos.persistance.ProductTbMaster;

/**
 * This class provides the Services specific to class OrderTbMaster (Order) for
 * all the tasks related to database operations like Create, Read, Update &
 * Delete.
 * 
 * @author vaibhav.jain
 * 
 */

@Service
public interface OrderService {

	/**
	 * This creates a new Order in the System for the specific Store.
	 * 
	 * @param OrderTbMaster
	 *            The Order to be created.
	 * @return Nothing.
	 */
	public void create(final OrderTbMaster entity);

	/**
	 * Reads all the Orders for specific Store present in the system.
	 * 
	 * @return List of all Orders.
	 */
	public List<OrdersVO> findOrder(SessionInfo sessionInfo);

	/**
	 * Reads all the Orders for specific Store present filtered by property in
	 * the system.
	 * 
	 * @return List of all Orders.
	 */
	public List<OrdersVO> findOrdersbyProperty(String propertyName, Object value);

	/**
	 * Reads all Orders by ID.
	 * 
	 * @return OrderTbMaster object.
	 */
	public OrderTbMaster findByID(final long id);

	/**
	 * Update all Orders by ID.
	 * 
	 * @return OrderTbMaster object.
	 */
	public OrderTbMaster update(final OrderTbMaster entity);

	/**
	 * Delete all the Orders by ID.
	 * 
	 * @return Nothing.
	 */
	public void delete(final OrderTbMaster entity);

	/**
	 * Delete by Id.
	 * 
	 * @return Nothing.
	 */
	public void deleteById(final long entityId);

	/**
	 * Reads all the Orders present in the system.
	 * 
	 * @return List of all Orders.
	 */
	List<OrderTbMaster> findAll();

	/**
	 * Reads all the Orders by Filters for specific Store present in the system.
	 * 
	 * @return List of all Orders VO.
	 */
	public List<OrdersVO> findAllOrderByFilters(
			Map<String, Object> filter_criteria);

	/**
	 * Reads all the Order History by Filters for specific Store present in the
	 * system.
	 * 
	 * @return List of all Orders VO.
	 */
	public List<OrdersVO> findAllOrderHistoryByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo);

	/**
	 * Track all the Orders by Filters.
	 * 
	 * @return List of all Orders VO.
	 */
	public List<OrdersVO> trackOrder(HashMap<String, Object> properties);

	/**
	 * Reads all the Order History.
	 * 
	 * @return List of all Orders VO.
	 */
	public List<OrdersVO> findOrderHistory(SessionInfo sessionInfo);
	
	
	public List<OrderTbMaster> findAllByProperty(HashMap<String, Object> propertiesMap);
	
	
	public List<OrderTbMaster> findOrdersbyProperties(Map<String, Object>propertiesMap); 
	
	public List<OrderTbMaster> findAllByPropertyDateandCount(HashMap<String, Object> propertiesMap);
	
	/**
	 * Reads all the Order status from ORDER_STATUS_TB_MASTER.
	 * 
	 * @return List of all Orders status.
	 */
	
	public List<String> getAllStatus();

}
