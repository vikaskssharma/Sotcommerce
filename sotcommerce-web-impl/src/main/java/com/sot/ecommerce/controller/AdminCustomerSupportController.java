package com.sot.ecommerce.controller;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.activemq.command.SessionInfo;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sot.ecommerce.exception.BusinessFailureException;
import com.sot.ecommerce.exception.GenericFailureException;
import com.sot.ecommerce.handler.CustomerSupportHandler;
import com.sot.ecommerce.validator.CustomerAdminResponseValidator;
import com.sot.ecommerce.validator.FormSearchValidator;

@Controller

public class AdminCustomerSupportController {

	@Autowired
	private CustomerSupportHandler customerSupportHandler;
	@Autowired
	private CustomerAdminResponseValidator customerSupportFormValidator;
	@Autowired
	FormSearchValidator formSearchValidator;
	

	/** Logger instance. **/
	private static final Logger logger = LoggerFactory.getLogger(AdminCustomerSupportController.class);
	
	/**
	 * this method is used for listing the admin form
	 * @param customerSupportForm
	 * @param locale
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/custsupportadminlist.htm", method = RequestMethod.GET)
	public ModelAndView customersupportlist(
			@ModelAttribute("customerSupportForm") CustomerSupportForm customerSupportForm,Model model, HttpServletRequest request) {
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		FormSearch formSearch = new FormSearch();
		model.addAttribute("formSearch", formSearch);
		List<CustomerSupportForm> customerSupportform = new ArrayList<CustomerSupportForm>();
		try {
			customerSupportform = customerSupportHandler.findAllforAdmin(customerSupportform,sessionInfo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("Error occured while listing  the request details " +e);
		}
		model.addAttribute("allStatuses",getAllStatus());
		model.addAttribute("customerSupportList", customerSupportform);

		return new ModelAndView("/customersupport/custsupoortadminlist");

	}
	/**
	 * this method is used for listing the open the form for response
	 * @param customerSupportForm
	 * @param locale
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/admincustomersupport.htm", method = RequestMethod.GET)
	public ModelAndView customersupport(
			@ModelAttribute("customerSupportForm") CustomerSupportForm customerSupportForm,
			Locale locale, Model model, HttpServletRequest request) {
			FormSearch formSearch = new FormSearch();
		model.addAttribute("formSearch", formSearch);
		model.addAttribute("allStatuses",getAllStatus());
		model.addAttribute("customerSupportForm", customerSupportForm);
		model.addAttribute("issuetypelist", customerSupportHandler.findAllissues());
		return new ModelAndView("/customersupport/customer_support_admin");

	}
	/**
	 * this method is used for submit the form
	 * @param customerSupportForm
	 * @param locale
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/customeradminsubmit.htm", method = RequestMethod.POST)
	public ModelAndView customesubmit(
			@ModelAttribute("customerSupportForm") CustomerSupportForm customerSupportForm,
			BindingResult result,Locale locale, Model model, HttpServletRequest request) {
		String requestnumber = request.getParameter("req_number");
		SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
		List<CustomerSupportForm> customerSupportlist = new ArrayList<CustomerSupportForm>();
		String message ="";
		customerSupportFormValidator.validate(customerSupportForm, result);
		FormSearch formSearch = new FormSearch();
		model.addAttribute("formSearch", formSearch);
		if (result.hasErrors()) {
		model.addAttribute("issuetypelist",
					customerSupportHandler.findAllissues());
			Long reqno = Long.parseLong(requestnumber);
			customerSupportForm = customerSupportHandler.findById(
					customerSupportForm, reqno);
			
			model.addAttribute("requestNumber",requestnumber);
			model.addAttribute("customerSupportForm", customerSupportForm);
			return new ModelAndView("/customersupport/customer_support_admin");
		}
		  long req_no=Long.parseLong(requestnumber);
		  if(customerSupportForm.getStatus().equals("Pending")){
	         message=customerSupportHandler.adminupdateCustomerQuery(customerSupportForm, req_no,sessionInfo);
		  }
	     
	     try {
	    	 customerSupportlist = customerSupportHandler.findAllforAdmin(customerSupportlist,sessionInfo);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				logger.error("Error occured while submit  the request details " +e);
			}
	     customerSupportForm.setViewmode("viewmde");
	    
		model.addAttribute("message", message);
		model.addAttribute("customerSupportList", customerSupportlist);
		model.addAttribute("allStatuses",getAllStatus());
		return new ModelAndView("/customersupport/custsupoortadminlist");

	}

	/**
	 * this method is used for to get in edit mode
	 * @param customerSupportForm
	 * @param locale
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/editadminRequest.htm", method = RequestMethod.GET)
	public ModelAndView editrequest(
			@ModelAttribute("customerSupportForm") CustomerSupportForm customerSupportForm,
			Locale locale, Model model, HttpServletRequest request) {

		
		String requestnumber = request.getParameter("req_number");
		Long reqno = Long.parseLong(requestnumber);
		
		customerSupportForm = customerSupportHandler.findById(
				customerSupportForm, reqno);
		customerSupportForm.setViewmode("viewmode");
		System.out.println("customersupportform value:::"
				+ customerSupportForm.getFirstname());
		model.addAttribute("issuetypelist",  customerSupportHandler.findAllissues());
		model.addAttribute("requestNumber",requestnumber);
		model.addAttribute("customerSupportForm", customerSupportForm);
		return new ModelAndView("/customersupport/customer_support_admin");

	}
	/**
	 * this method is used for filtering the request
	 * @param customerSupportForm
	 * @param locale
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/adminfilterCustomerSupport.htm", method = RequestMethod.POST)
	public ModelAndView AllOrdersHistoryFilter(@ModelAttribute("formSearch") FormSearch formSearch, Model model,
			BindingResult bindingResult, Locale locale, HttpServletRequest request) throws GenericFailureException,
			BusinessFailureException, ParseException {
		List<CustomerSupportForm> customerSupportlist = new ArrayList<CustomerSupportForm>();
	
		Map<String, Object> filter_criteria =  new HashMap<String, Object>();
		formSearchValidator.validate(formSearch, bindingResult);
		try {
			SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
			if (bindingResult.hasErrors()) {
				logger.info("Invalid Type for filter:" + filter_criteria);
				model.addAttribute("formSearch", formSearch);
				model.addAttribute("allStatuses",getAllStatus());
				try {
					customerSupportlist = customerSupportHandler.findAllforAdmin(customerSupportlist, sessionInfo);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new ModelAndView("/customersupport/custsupoortadminlist");
			}else{
				model.addAttribute("allStatuses",getAllStatus());
				filter_criteria = FormSearchDataExtract.extractFormData(formSearch);
				//filter_criteria.get("filter.CustomerSupport.vcStts")
				/*for (Map.Entry<String, Object> entry : filter_criteria.entrySet()) 
				{ 
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
				if(entry.getValue().equals("Pending")){
					entry.setValue("P");
				}
				else
				{
					entry.setValue("A");
				}
				}*/
				String selectedStatus = formSearch.getFilters().get(2).getValue();
				model.addAttribute("selectedStatus",selectedStatus);
				customerSupportlist = customerSupportHandler
						.findAllQueriesByFilters(customerSupportlist,filter_criteria, sessionInfo);
				model.addAttribute("customerSupportList", customerSupportlist);
				return new ModelAndView("/customersupport/custsupoortadminlist");
				
			}
		} catch (HibernateException hexp) {
			logger.error("Error occured while fetching filtered details " +hexp);
		}
		return null;
	}


	
	
	
	private HashMap<String, String> getAllStatus() {

		HashMap<String, String> allStatus = new HashMap<String, String>();

		allStatus.put(CUSTOMER_SUPPORT_STATUS.Pending.toString(), CUSTOMER_SUPPORT_STATUS.Pending.toString());

		allStatus.put(CUSTOMER_SUPPORT_STATUS.Answered.toString(), CUSTOMER_SUPPORT_STATUS.Answered.toString());
			
		return allStatus;
	}
	

}
