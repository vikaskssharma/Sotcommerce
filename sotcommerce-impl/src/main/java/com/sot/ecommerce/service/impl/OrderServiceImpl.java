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
import com.sbsc.fos.common.dao.SQLDAO;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.order.common.BillingVO;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.order.common.ShippingVO;
import com.sbsc.fos.persistance.OrderBillingTbDtl;
import com.sbsc.fos.persistance.OrderShippingTbDtl;
import com.sbsc.fos.persistance.OrderTbMaster;
import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sot.ecommerce.um.service.StoreService;
import com.sot.ecommerce.um.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

	private GenericDAO<OrderTbMaster> dao;
	
	/** The SQL DAO */
	@Autowired
	private SQLDAO sqlDAO;

	private long usrID;

	private long storeID;

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(OrderServiceImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	@Autowired
	public void setDAO(GenericDAO<OrderTbMaster> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(OrderTbMaster.class);
	}

	@Transactional
	public OrderTbMaster update(OrderTbMaster orders) {

		return dao.update(orders);
	}

	@Transactional
	public void delete(OrderTbMaster orders) {

	}

	@Transactional
	public void deleteById(long userId) {

	}

	@Transactional
	public void create(OrderTbMaster order) {
		dao.saveOrUpdate(order);

	}

	@Transactional
	public List<OrderTbMaster> findAllByProperty(
			HashMap<String, Object> propertiesMap) {
		return dao.findAllByProperty(propertiesMap);
	}

	@Transactional
	public List<OrderTbMaster> findAll() {
		// TODO Auto-generated method stub

		List<OrderTbMaster> order = dao.findAll();
		return order;
	}

	@Override
	@Transactional
	public OrderTbMaster findByID(long id) {
		// TODO Auto-generated method stub
		return dao.findByID(id);
	}

	@Transactional
	public List<OrdersVO> findOrder(SessionInfo sessionInfo) {
		List<OrdersVO> detailList = new ArrayList<OrdersVO>();
		List<OrderTbMaster> orderList = null;
		try {
			StoreTbMaster storeTbMaster = storeService.findByID(sessionInfo
					.getStoreId());
			HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("storeTbMaster", storeTbMaster);
			propertiesMap.put("desc", "dtOrdrDt");
			orderList = dao.findAllByProperty(propertiesMap);
			SimpleDateFormat db_date_format = new SimpleDateFormat("yyyy-MM-dd");
		
			Date orderDate;
			int count = 0;

			for (OrderTbMaster orderTbMaster : orderList) {
				OrdersVO ordersVO = new OrdersVO();
				ShippingVO shippingVO = new ShippingVO();
				BillingVO billingVO = new BillingVO();
				ordersVO.setShippingVO(shippingVO);
				ordersVO.setBillingVO(billingVO);
				ordersVO.setOrderNumber(orderTbMaster.getVcOrdrNmbr());
				ordersVO.setStoreName(orderTbMaster.getStoreTbMaster()
						.getVcStrNm());
				orderDate = db_date_format.parse(orderTbMaster.getDtOrdrDt()
						.toString());

				ordersVO.setOrderDate(DateUtil.parseDate(orderDate.toString()));
				for (OrderBillingTbDtl orderBillingTbDtl : orderTbMaster
						.getOrderBillingTbDtls()) {
					ordersVO.getBillingVO().setBillingFirstName(
							orderBillingTbDtl.getVcBlngFrstNm());
				}
				for (OrderShippingTbDtl orderShippingTbDtl : orderTbMaster
						.getOrderShippingTbDtls()) {
					ordersVO.getShippingVO().setShippingFirstName(
							orderShippingTbDtl.getVcShpFrstNm());
				}

				ordersVO.setTotalCost(orderTbMaster.getNmTtlCst());
				ordersVO.setTotalPromoCost(orderTbMaster.getNmTtlNtCst());
				ordersVO.setOrderStatus(orderTbMaster.getOrderStatusTbMaster().getVcSttsDesc());
				detailList.add(count, ordersVO);
				count++;
			}

		} catch (Exception e) {
			logger.error(String
					.format("Error occured while reading or converting a orders to"
							+ " OrderVO . Actual Error :" + e.getMessage()));
			e.printStackTrace();
		}

		return detailList;
	}

	@Transactional
	public List<OrdersVO> findOrdersbyProperty(String propertyName, Object value) {

		List<OrdersVO> detailList = new ArrayList<OrdersVO>();
		List<OrderTbMaster> orderList = null;
		try {
			orderList = dao.findAllByProperty(propertyName, value);
			SimpleDateFormat db_date_format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat view_date_format = new SimpleDateFormat(
					"MM-dd-yyyy");
			Date orderDate;
			int count = 0;
			if (orderList.size() > 0) {
				for (OrderTbMaster orderTbMaster : orderList) {
					OrdersVO ordersVO = new OrdersVO();
					ShippingVO shippingVO = new ShippingVO();
					BillingVO billingVO = new BillingVO();
					ordersVO.setShippingVO(shippingVO);
					ordersVO.setBillingVO(billingVO);
					ordersVO.setOrderNumber(orderTbMaster.getVcOrdrNmbr());
					ordersVO.setStoreName(orderTbMaster.getStoreTbMaster()
							.getVcStrNm());
					orderDate = db_date_format.parse(orderTbMaster
							.getDtOrdrDt().toString());
					ordersVO.setOrderDate(DateUtil.parseDate(orderDate.toString()));
					for (OrderBillingTbDtl orderBillingTbDtl : orderTbMaster
							.getOrderBillingTbDtls()) {
						ordersVO.getBillingVO().setBillingFirstName(
								orderBillingTbDtl.getVcBlngFrstNm());
					}
					for (OrderShippingTbDtl orderShippingTbDtl : orderTbMaster
							.getOrderShippingTbDtls()) {
						ordersVO.getShippingVO().setShippingFirstName(
								orderShippingTbDtl.getVcShpFrstNm());
					}
					ordersVO.setTotalCost(orderTbMaster.getNmTtlCst());
					ordersVO.setTotalPromoCost(orderTbMaster.getNmTtlNtCst());
					ordersVO.setOrderStatus(orderTbMaster.getOrderStatusTbMaster().getVcSttsDesc());
					detailList.add(count, ordersVO);
					count++;
				}
			}

		} catch (Exception e) {
			logger.error(String
					.format("Error occured while reading or converting a orders to"
							+ " OrderVO . Actual Error :" + e.getMessage()));
			e.printStackTrace();
		}

		return detailList;
	}

	@Override
	@Transactional
	public List<OrdersVO> findAllOrderByFilters(
			Map<String, Object> filter_criteria) {
		List<OrdersVO> detailList_order_filter = new ArrayList<OrdersVO>();
		List<OrderTbMaster> orderList = null;
		try {
			orderList = dao.findAllByFilters(filter_criteria);
			SimpleDateFormat db_date_format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat view_date_format = new SimpleDateFormat(
					"MM-dd-yyyy");
			Date orderDate;
			int count = 0;
			for (OrderTbMaster orderTbMaster : orderList) {
				OrdersVO ordersVO = new OrdersVO();
				ShippingVO shippingVO = new ShippingVO();
				BillingVO billingVO = new BillingVO();
				ordersVO.setShippingVO(shippingVO);
				ordersVO.setBillingVO(billingVO);
				ordersVO.setOrderNumber(orderTbMaster.getVcOrdrNmbr());
				ordersVO.setStoreName(orderTbMaster.getStoreTbMaster()
						.getVcStrNm());
				orderDate = db_date_format.parse(orderTbMaster.getDtOrdrDt()
						.toString());
				ordersVO.setOrderDate(DateUtil.parseDate(orderDate.toString()));
				for (OrderBillingTbDtl orderBillingTbDtl : orderTbMaster
						.getOrderBillingTbDtls()) {
					ordersVO.getBillingVO().setBillingFirstName(
							orderBillingTbDtl.getVcBlngFrstNm());
				}
				for (OrderShippingTbDtl orderShippingTbDtl : orderTbMaster
						.getOrderShippingTbDtls()) {
					ordersVO.getShippingVO().setShippingFirstName(
							orderShippingTbDtl.getVcShpFrstNm());
				}

				ordersVO.setTotalCost(orderTbMaster.getNmTtlCst());
				ordersVO.setTotalPromoCost(orderTbMaster.getNmTtlNtCst());
				ordersVO.setOrderStatus(orderTbMaster.getOrderStatusTbMaster().getVcSttsDesc());
				detailList_order_filter.add(count, ordersVO);
				count++;
			}
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while reading or converting a orders to"
							+ " OrderVO . Actual Error :" + e.getMessage()));
			e.printStackTrace();
		}
		return detailList_order_filter;
	}

	@Transactional
	public List<OrdersVO> trackOrder(HashMap<String, Object> properties) {

		List<OrderTbMaster> orderList = null;
		List<OrdersVO> productList = new ArrayList<OrdersVO>();
		try {
			orderList = dao.findAllByProperty(properties);

			int i = 0;
			do {
				OrdersVO ordervo = new OrdersVO();
				ShippingVO shippingVO = new ShippingVO();
				BillingVO billingVO = new BillingVO();
				ordervo.setShippingVO(shippingVO);
				ordervo.setBillingVO(billingVO);
				ordervo.setOrderNumber(orderList.get(0).getVcOrdrNmbr());
				SimpleDateFormat db_date_format = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat view_date_format = new SimpleDateFormat("MM-dd-yyyy");
				
				Date formatdate = db_date_format.parse(orderList.get(0).getDtOrdrDt()
						.toString());
				ordervo.setOrderHistoryDate(view_date_format.format(formatdate));
				ordervo.setTotalCost(orderList.get(0).getNmTtlCst());

				if (orderList.get(0).getOrderProductTbDetails() != null
						&& orderList.get(0).getOrderProductTbDetails().size() > 0) {

					ordervo.setProductDescription(orderList.get(0)
							.getOrderProductTbDetails().get(i)
							.getProductTbMaster().getVcPrdNm());
					ordervo.setQuantity(orderList.get(0)
							.getOrderProductTbDetails().get(i).getNmQnty());
				}

				ordervo.getShippingVO().setShippingCharges(
						orderList.get(0).getOrderShippingTbDtls().get(0)
								.getNmShpAmt());

				ordervo.getBillingVO().setBillingFirstName(
						orderList.get(0).getOrderBillingTbDtls().get(0)
								.getVcBlngFrstNm());
				ordervo.getBillingVO().setBillingLastName(
						orderList.get(0).getOrderBillingTbDtls().get(0)
								.getVcBlngLstNm());
				ordervo.getBillingVO().setBillingCity(
						orderList.get(0).getOrderBillingTbDtls().get(0)
								.getVcCty());
				ordervo.getBillingVO().setBillingState(
						orderList.get(0).getOrderBillingTbDtls().get(0)
								.getVcSt());
				ordervo.getBillingVO().setBillingZipcode(
						orderList.get(0).getOrderBillingTbDtls().get(0)
								.getVcZpcd());
				ordervo.getBillingVO().setBillingPhone(
						orderList.get(0).getOrderBillingTbDtls().get(0)
								.getVcPhn());

				ordervo.getShippingVO().setShippingFirstName(
						orderList.get(0).getOrderShippingTbDtls().get(0)
								.getVcShpFrstNm());
				ordervo.getShippingVO().setShippingLastName(
						orderList.get(0).getOrderShippingTbDtls().get(0)
								.getVcShpLstNm());
				ordervo.getShippingVO().setShippingCity(
						orderList.get(0).getOrderShippingTbDtls().get(0)
								.getVcCty());
				ordervo.getShippingVO().setShippingState(
						orderList.get(0).getOrderShippingTbDtls().get(0)
								.getVcSt());
				ordervo.getShippingVO().setShippingZipcode(
						orderList.get(0).getOrderShippingTbDtls().get(0)
								.getVcZpcd());
				ordervo.getShippingVO().setShippingPhone(
						orderList.get(0).getOrderShippingTbDtls().get(0)
								.getVcPhn());
				ordervo.setOrderStatus(orderList.get(0).getOrderStatusTbMaster().getVcSttsDesc());
				if(orderList.get(0).getPaymentTbMaster()!=null){
					ordervo.setPaymentMethod(orderList.get(0).getPaymentTbMaster()
							.getVcPymntMd());
				}
				if(orderList.get(0).getPromotionTbMaster()!=null){
					ordervo.setGvCode(orderList.get(0).getPromotionTbMaster()
							.getVcRlCd());
					ordervo.setGvDiscount(orderList.get(0).getPromotionTbMaster()
							.getNmDscntVl());
				}
				productList.add(i, ordervo);
				i++;
			} while (i < orderList.get(0).getOrderProductTbDetails().size());
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while reading or converting a orders to"
							+ " OrderVO . Actual Error :" + e.getMessage()));
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	@Transactional
	public List<OrdersVO> findAllOrderHistoryByFilters(
			Map<String, Object> filter_criteria, SessionInfo sessionInfo) {
		List<OrdersVO> detailList = new ArrayList<OrdersVO>();
		List<OrderTbMaster> orderList = null;
		try {
			usrID = sessionInfo.getUserId();
			storeID = sessionInfo.getStoreId();
			filter_criteria.put("filter.OrderTbMaster.umTbUser",
					userService.findByID(usrID));
			filter_criteria.put("filter.OrderTbMaster.storeTbMaster",
					storeService.findByID(storeID));

			orderList = dao.findAllByFilters(filter_criteria);

			for (int i = 0; i < orderList.size(); i++) {
				OrdersVO ordersVO = new OrdersVO();
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat format2 = new SimpleDateFormat("MM-dd-yyyy");
				Date date = format1.parse(orderList.get(i).getDtOrdrDt()
						.toString());
				ordersVO.setOrderHistoryDate(format2.format(date));
				ordersVO.setOrderId(String.valueOf(orderList.get(i)
						.getNmOrdrId()));
				ordersVO.setOrderNumber(orderList.get(i).getVcOrdrNmbr());
				for (int j = 0; j < orderList.get(i).getOrderProductTbDetails()
						.size(); j++) {
					ordersVO.setProductName(orderList.get(i)
							.getOrderProductTbDetails().get(0)
							.getProductTbMaster().getVcPrdNm());
				}
				ordersVO.setTotalPromoCost(orderList.get(i).getNmTtlNtCst());
				ordersVO.setOrderStatus(orderList.get(i).getOrderStatusTbMaster().getVcSttsDesc());

				detailList.add(i, ordersVO);

			}

		} catch (Exception e) {
			logger.error(String
					.format("Error occured while reading or converting a orders to"
							+ " OrderVO . Actual Error :" + e.getMessage()));
			e.printStackTrace();
		}

		return detailList;

	}

	@Transactional
	public List<OrdersVO> findOrderHistory(SessionInfo sessionInfo) {
		List<OrdersVO> detailList = new ArrayList<OrdersVO>();
		List<OrderTbMaster> orderList = null;
		try {
			HashMap<String, Object> userMap = getUserMap(sessionInfo);
			userMap.put("desc", "dtOrdrDt");
			orderList = dao.findAllByProperty(userMap);

			for (int i = 0; i < orderList.size(); i++) {
				OrdersVO ordersVO = new OrdersVO();

				ordersVO.setOrderId(String.valueOf(orderList.get(i)
						.getNmOrdrId()));
				ordersVO.setOrderNumber(orderList.get(i).getVcOrdrNmbr());
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat format2 = new SimpleDateFormat("MM-dd-yyyy");
				Date date = format1.parse(orderList.get(i).getDtOrdrDt()
						.toString());
				ordersVO.setOrderHistoryDate(format2.format(date));
				ordersVO.setTotalPromoCost(orderList.get(i).getNmTtlNtCst());
				ordersVO.setOrderStatus(orderList.get(i).getOrderStatusTbMaster().getVcSttsDesc());
				for (int j = 0; j < orderList.get(i).getOrderProductTbDetails()
						.size(); j++) {
					ordersVO.setProductName(orderList.get(i)
							.getOrderProductTbDetails().get(0)
							.getProductTbMaster().getVcPrdNm());
				}
				detailList.add(i, ordersVO);

			}
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while reading or converting a orders to"
							+ " OrderVO . Actual Error :" + e.getMessage()));
			e.printStackTrace();
		}
		return detailList;
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
	@Override
	public List<OrderTbMaster> findOrdersbyProperties(
			Map<String, Object> propertiesMap) {

		try {

			return dao.findAllByProperty(propertiesMap);
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while reading or converting a orders to"
							+ " OrderVO . Actual Error :" + e.getMessage()));
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	@Transactional	
	public List<OrderTbMaster> findAllByPropertyDateandCount(
			HashMap<String, Object> propertiesMap) {
		return dao.findAllByPropertyCountandDate(propertiesMap);
	}

	@Transactional
	@Override
	public List<String> getAllStatus() {
		return sqlDAO.findAllOrderStatus();
	}

}
