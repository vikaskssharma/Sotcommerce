package com.sot.ecommerce.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.order.service.OrderService;
import com.sbsc.fos.order.web.handler.OrderHandler;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.FormSearchDataExtract;
import com.sbsc.fos.web.form.FormSearch;
import com.sbsc.fos.web.form.validator.FormSearchValidator;

/**
 * Handles requests for the application Merchant Admin Product Orders Page.
 */

@Controller
public class ProductOrdersController {

	private static final Logger logger = Logger
			.getLogger(ProductOrdersController.class);

	@Autowired
	FormSearchValidator formSearchValidator;

	@Autowired
	private OrderHandler orderHandler;

	@Autowired
	OrderService orderService;

	/**
	 * Provides all the Orders as per selected filter on UI
	 * 
	 * @param formSearch
	 * @param locale
	 * @param request
	 * @return Model and view attributes as
	 * @throws GenericFailureException
	 *             ModelAndView object
	 * @throws BusinessFailureException
	 * @throws ParseException
	 */
	@RequestMapping(value = "admin/order_filter.htm", method = RequestMethod.POST)
	public ModelAndView AllOrdersByFilter(
			@ModelAttribute("formSearch") FormSearch formSearch, Model model,
			BindingResult bindingResult, HttpServletRequest request) {

		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> filter_criteria = null;
		String errorMessage = null;
		List<OrdersVO> orderList = null;
		formSearchValidator.validate(formSearch, bindingResult);
		if (bindingResult.hasErrors()) {
			logger.info("Invalid Type for filter:" + filter_criteria);
			modelMap.put("formSearch", formSearch);
			modelMap.put("detailList",
					orderHandler.allOrderHandle(CommonUtil.getSessionInfo(request)));
			modelMap.put("allStatuses", getAllStatus());
			return new ModelAndView("/order/order_list", modelMap);
		} else {
			String tab = formSearch.getFilters().get(8).getValue();
			if (tab.equals("All Orders") || tab.isEmpty()) {
				try {
					orderList = orderHandler.findAllOrderByFilters(
							FormSearchDataExtract.extractFormData(formSearch),
							CommonUtil.getSessionInfo(request));
				} catch (ParseException e) {
					logger.error(
							String.format(
									"Error occured while fetching orders"
											+ "for filters"));
					errorMessage = "Error occured : " + e.getMessage();
					modelMap.put("errorMessage", errorMessage);
				}
			} else {
				try {
					orderList = orderHandler.allOrdersbyProperty(
							FormSearchDataExtract.extractFormData(formSearch),
							CommonUtil.getSessionInfo(request));
				} catch (ParseException e) {
					logger.error(
							String.format(
									"Error occured while fetching orders"
											+ "for property."));
					errorMessage = "Error occured : " + e.getMessage();
					modelMap.put("errorMessage", errorMessage);
				}
			}

			modelMap.put("formSearch", formSearch);
			modelMap.put("orderList", orderList);
			modelMap.put("allStatuses", getAllStatus());
			return new ModelAndView("/order/order_list", modelMap);
		}

	}

	/**
	 * 
	 * @param locale
	 * @param model
	 * @param error
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/orderList.htm", method = RequestMethod.GET)
	public ModelAndView AllOrders(Locale locale, Model model, Error error,
			BindingResult bindingResult, HttpServletRequest request) {

		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<OrdersVO> orderList = null;
		String errorMessage = null;

		try {
			orderList = orderHandler.allOrderHandle(sessionInfo);
		} catch (Exception e) {
			logger.error(
					String.format(
							"Error occured while fetching orders"
									+ "for all orders for a specific store"));
			errorMessage = "Error occured : " + e.getMessage();
			modelMap.put("errorMessage", errorMessage);
		}

		modelMap.put("formSearch", new FormSearch());
		modelMap.put("orderList", orderList);
		modelMap.put("allStatuses", getAllStatus());
		return new ModelAndView("/order/order_list", modelMap);
	}

	
	
	/**
	 * Provides the order statuses in the form of Map from the table ORDER_STATUS_TB_MASTER
	 * which a order can have.
	 * 
	 * @return A HashMap
	 */
	private HashMap<String, String> getAllStatus() {

		return orderHandler.getAllStatus();
	}

}
