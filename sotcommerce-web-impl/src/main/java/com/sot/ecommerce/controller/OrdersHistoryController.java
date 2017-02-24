package com.sot.ecommerce.controller;


/**
 * Handles requests for orders history for customers
 * 
 * @author gaurav.kumar
 */

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.order.common.OrdersVO;
import com.sbsc.fos.order.service.OrderService;
import com.sbsc.fos.order.web.form.validator.TrackOrderForm;
import com.sbsc.fos.order.web.form.validator.TrackOrderFormValidator;
import com.sbsc.fos.order.web.handler.OrderHandler;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.FormSearchDataExtract;
import com.sbsc.fos.web.form.FormSearch;
import com.sbsc.fos.web.form.validator.FormSearchValidator;




@Controller
public class OrdersHistoryController {
	
	@Autowired
	private OrderHandler orderHandler;
	
	@Autowired
	FormSearchValidator formSearchValidator;
	
	@Autowired
	TrackOrderFormValidator trackOrderFormValidator;
	
	@Resource
	OrderService orderService;
	
	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(OrdersHistoryController.class);
	
	
	/*
	 * This works as a controller to show all the orders for a customer
	 */
	@RequestMapping(value = "store/order_history.htm", method = RequestMethod.GET)
	public ModelAndView OrderHistory(Locale locale, Model model,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException {
		try{
			Map<String, Object> modelMap = new HashMap<String, Object>();
			SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
			List<OrdersVO> detailList = orderHandler.orderHistoryHandle(sessionInfo);
			FormSearch formSearch = new FormSearch();
			modelMap.put("formSearch", formSearch);
			modelMap.put("detailList", detailList);
			modelMap.put("allStatuses", getAllStatus());
			return new ModelAndView("/order/order_history", modelMap);
			
		} catch (HibernateException hexp) {
			logger.error("Error occured while fetching order history " +hexp);
		}
		return null;
	}

	/*
	 * This works as a controller to show all the details of a particular order
	 * number
	 */
	@RequestMapping(value = "store/track_order.htm", method = RequestMethod.GET)
	public ModelAndView TrackOrder(Locale locale, Model model,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException {
	
		String orderNumber = request.getParameter("orderid");
		if(orderNumber==null){
			orderNumber = request.getParameter("orderNumber");
		}	
		try{
			Map<String, Object> modelMap = new HashMap<String, Object>();
			SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
			List<OrdersVO> ordervo = orderHandler.trackOrderHandle(orderNumber, sessionInfo);
			FormSearch formSearch = new FormSearch();
			modelMap.put("formSearch", formSearch);
			modelMap.put("orderNumber", orderNumber);
			if(ordervo.size()>0){
				model.addAttribute("ordervo", ordervo);
				return new ModelAndView("/order/track_order", modelMap);
			}else{
				modelMap.put("allStatuses", getAllStatus());
				return new ModelAndView("/order/order_history", modelMap);
			}
		} catch (HibernateException hexp) {
			logger.error("Error occured while fetching details for order number "+orderNumber+" " +hexp);
		}
		return null;

	}
	
	/*
	 * This works as a controller to show all the details of a particular order
	 * when user has not logged in to the system
	 */
/*	@RequestMapping(value = "store/filterOrderHistory.htm", method = RequestMethod.POST)
	public ModelAndView AllOrdersHistoryFilter(@ModelAttribute("formSearch") FormSearch formSearch, Model model,
			BindingResult bindingResult, Locale locale, HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException, ParseException {*/
	@RequestMapping(value = "store/track_order_from_homepage.htm", method = RequestMethod.POST)
	public ModelAndView TrackOrderFromHomepage(@ModelAttribute("trackOrderForm") TrackOrderForm trackOrderForm,
			Model model,BindingResult bindingResult, Locale locale, HttpServletRequest request,final RedirectAttributes redirectAttributes) 
					throws GenericFailureException,	BusinessFailureException {
		String orderNumber = trackOrderForm.getOrderNumber();
		String email = trackOrderForm.getEmail();
		try{
			Map<String, Object> modelMap = new HashMap<String, Object>();
			
			
			SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
			trackOrderForm.setOrderNumValidatedForEmail(orderHandler.getOrderNumValidatedForEmail(email, orderNumber));

			trackOrderFormValidator.validate(trackOrderForm, bindingResult);
			
			if (bindingResult.hasErrors()) {
				logger.info("Track order from home has validation errors."+ trackOrderForm);
				modelMap.put("trackOrderForm", trackOrderForm);
				return new ModelAndView("/usermanagement/track-order-dialog");
			}
			List<OrdersVO> ordervo = orderHandler.trackOrderHandle(orderNumber, sessionInfo);
			model.addAttribute("orderNumber", orderNumber);
			model.addAttribute("trackOrderForm", trackOrderForm);
			if(ordervo.size()>0){
				model.addAttribute("ordervo", ordervo);
				//return new ModelAndView("/order/track_order", modelMap);
				return new ModelAndView("redirect:/store/track_order.htm?orderid="+orderNumber);
			}else{
				modelMap.put("allStatuses", getAllStatus());
				return new ModelAndView("/order/order_history", modelMap);
			}
		} catch (HibernateException hexp) {
			logger.error("Error occured while fetching details for order number "+orderNumber+" " +hexp);
		}
		return null;

	}
	
	
	
	
	

	@RequestMapping(value = "store/trackOrderError.htm", method = RequestMethod.GET)
	public Map trackOrderError(@ModelAttribute("trackOrderForm") TrackOrderForm trackOrderForm,
			Locale locale, Model model,BindingResult bindingResult,
			HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException {
		
		HashMap error =new HashMap<>();
		
		error.put("email","email doesn't exist");
		error.put("orderID","Order No. Invalid");
		
		
		return error;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * This works as a controller to show all the filtered orders for a customer
	 * 
	 * @param locale 
	 * @param request 
	 * @return 
	 * @throws GenericFailureException 
	 * @throws BusinessFailureException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "store/filterOrderHistory.htm", method = RequestMethod.POST)
	public ModelAndView AllOrdersHistoryFilter(@ModelAttribute("formSearch") FormSearch formSearch, Model model,
			BindingResult bindingResult, Locale locale, HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException, ParseException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> filter_criteria = null;
		formSearchValidator.validate(formSearch, bindingResult);
		try {
			SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
			if (bindingResult.hasErrors()) {
				logger.info("Invalid Type for filter:" + filter_criteria);
				modelMap.put("formSearch", formSearch);
				//modelMap.put("detailList", orderHandler.orderHistoryHandle(sessionInfo));
				modelMap.put("allStatuses", getAllStatus());
				return new ModelAndView("/order/order_history", modelMap);
			}else{
				List<OrdersVO> detailList = orderHandler
						.findAllOrderHistoryByFilters(FormSearchDataExtract
								.extractFormData(formSearch), sessionInfo);
				modelMap.put("detailList", detailList);
				modelMap.put("allStatuses", getAllStatus());
				return new ModelAndView("/order/order_history", modelMap);
				
			}
		} catch (HibernateException hexp) {
			logger.error("Error occured while fetching filtered details " +hexp);
		}
		return null;
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
	
	
	/*@RequestMapping(value = "store/track_order_from_homepage.htm", method = RequestMethod.POST)
	public ModelAndView TrackOrderFromHomepage(@ModelAttribute("trackOrderForm") TrackOrderForm trackOrderForm,
			Model model,BindingResult bindingResult, Locale locale, HttpServletRequest request) 
					throws GenericFailureException,	BusinessFailureException*/
	@RequestMapping(value = "store/openTrackMyOrderDialog.htm", method = RequestMethod.GET)
	public ModelAndView OpenTrackMyOrderDialog(@ModelAttribute("trackOrderForm") TrackOrderForm trackOrderForm,
			Model model,BindingResult bindingResult, Locale locale,HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("trackOrderForm", trackOrderForm);
		logger.info(" track my order dialog starts ");
		return new ModelAndView("/usermanagement/track-order-dialog");
		
	}
	
}
