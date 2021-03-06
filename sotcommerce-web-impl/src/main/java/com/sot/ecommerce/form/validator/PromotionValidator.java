/**
 * 
 */
package com.sot.ecommerce.form.validator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sbsc.fos.persistance.PromotionTbMaster;
import com.sbsc.fos.promotion.web.form.AddPromotionForm;
import com.sbsc.fos.promotion.web.handler.PromotionHandler;
import com.sbsc.fos.utils.SBSConstants;

/**
 * @author vaibhav.jain
 *
 */
@Component
public class PromotionValidator implements Validator{
	
	private static final int RULE_CODE_MIN_LENGTH = 5;

	private static final int RULE_CODE_MAX_LENGTH = 9;
	
	private static final int DISCOUNT_MAX_VALUE = 100;
	
	private static final String RULE_CODE_PATTERN_SPECIAL_CHARACTER = "^[a-zA-Z0-9]+([ \t-]?[a-zA-Z0-9]+)*$";
	
	private static final String RULE_CODE_PATTERN_ALPHANUMERIC = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$";
	
	private static final String ORDER_OVER_PATTERN = "[0-9]+([.][0-9]{1,2})?";
	
	
	@Autowired
	private PromotionHandler promotionHandler;

	private Object taxationForm;
	
	
	@Override 
	public boolean supports(Class<?> clazz) {
		return PromotionValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {

		AddPromotionForm addPromotionForm = (AddPromotionForm) command;
		
		SimpleDateFormat view_date_format = new SimpleDateFormat(SBSConstants.DATE_MM_DD_YYYY_TIME);
		
		Calendar cal = Calendar.getInstance();  
		
		int flag = 0;
		
				
		if (StringUtils.isBlank(addPromotionForm.getPromotionRuleName())) {
			
			errors.rejectValue("promotionRuleName",
					"add.promotion.ruleName.blank"/*,
					"Please enter Rule Name."*/);
		}
		
		//==========Promotion Rule Code Validation==========
		if (StringUtils.isBlank(addPromotionForm.getPromotionRuleCode())) {
			
			errors.rejectValue("promotionRuleCode",
					"add.promotion.ruleCode.blank"/*,
					"Please enter Rule Code."*/);
		}
		
		if (StringUtils.isNotBlank(addPromotionForm.getPromotionRuleCode())) {
			
			List<PromotionTbMaster> promotionTbMasters = promotionHandler.findAllByStringPropertyIgnoreCase("vcRlCd", addPromotionForm.getPromotionRuleCode());
			
			
			if(addPromotionForm.getPageMode().equals("Add")){
				if(!promotionTbMasters.isEmpty()){
					errors.rejectValue("promotionRuleCode",
							"add.promotion.ruleCode.duplicate"/*,
							"Rule Code already exist"*/);
				}
			}
			else{
				if(!promotionTbMasters.isEmpty() && !(addPromotionForm.getPromotionId() == promotionTbMasters.get(0).getNmPrmtnRlId())){
					errors.rejectValue("promotionRuleCode",
							"add.promotion.ruleCode.duplicate"/*,
							"Rule Code already exist"*/);
				}
			}

			
			
			if (!this.validatePattern(addPromotionForm.getPromotionRuleCode(),
					RULE_CODE_PATTERN_SPECIAL_CHARACTER)) {
				errors.rejectValue("promotionRuleCode", 
						"add.promotion.ruleCode.format"/*,
						"Special Characters are not allowed."*/);
			}else if (!this.validatePattern(addPromotionForm.getPromotionRuleCode(),
					RULE_CODE_PATTERN_ALPHANUMERIC)) {
				errors.rejectValue("promotionRuleCode",
						"add.promotion.ruleCode.alpha.numeric"/*,
						"Rule Code must have Alphabets and Numbers only"*/);
			}
			else
			{
				if (addPromotionForm.getPromotionRuleCode().length() < RULE_CODE_MIN_LENGTH) {
					errors.rejectValue("promotionRuleCode",
							"add.promotion.ruleCode.min.length"/*,
							"Rule Code should be greater than "
									+ RULE_CODE_MIN_LENGTH + " characters"*/);
				}

				if (addPromotionForm.getPromotionRuleCode().length() > RULE_CODE_MAX_LENGTH) {
					errors.rejectValue("promotionRuleCode",
							"add.promotion.ruleCode.max.length"/*,
							"Rule Code should not be greater than "
									+ RULE_CODE_MAX_LENGTH+ " characters"*/);
				}
			}	
			
			
			
		}
		

		//==========Promotion Limit Per Order Validation==========
		
		
		if(addPromotionForm.getPromotionLimitPerOrderCheckBox() != null){
			if (StringUtils.isBlank(addPromotionForm.getPromotionLimitPerOrder())) {
				
				if(addPromotionForm.getPromotionLimitPerOrderCheckBox().equals(Boolean.FALSE))
					errors.rejectValue("promotionLimitPerOrder",
							"add.promotion.limitPerOrder.blank"/*,
							"Please enter Limit Per Order"*/);
			}else if(!StringUtils.isNumeric(addPromotionForm.getPromotionLimitPerOrder())){
				errors.rejectValue("promotionLimitPerOrder",
						"add.promotion.limitPerOrder.nonnumeric"/*,
						"Please enter numeric value"*/);
				
			}else if(Long.parseLong(addPromotionForm.getPromotionLimitPerOrder())<1)
			{
				if(addPromotionForm.getPromotionLimitPerOrderCheckBox().equals(Boolean.FALSE))
				{
					errors.rejectValue("promotionLimitPerOrder",
							"add.promotion.limitPerOrder.nonzero"/*,
							"Please enter Limit greater than 0"*/);
				}
				else
				{
					errors.rejectValue("promotionLimitPerOrder",
							"add.promotion.limitPerOrder.checkBox"/*,
							"Either CheckBox 'infinite' OR 'Limit Per Order' value is allowed"*/);
				}	
			}
			else{
				
				if(addPromotionForm.getPromotionLimitPerOrderCheckBox().equals(Boolean.TRUE))
					errors.rejectValue("promotionLimitPerOrder",
						"add.promotion.limitPerOrder.checkBox"/*,
						"Either CheckBox 'infinite' OR 'Limit Per Order' value is allowed"*/);
				
				if (!StringUtils.isNumeric(addPromotionForm.getPromotionLimitPerOrder())
						&& !StringUtils.isNumericSpace(addPromotionForm.getPromotionLimitPerOrder().toString())) {
					errors.rejectValue("promotionLimitPerOrder",
							"add.promotion.not.numeric"/*,
							"Please enter digits only"*/);	
				}
			}
		}
		
		//==========Promotion Limit Per Customer Validation==========
		
		
		if(addPromotionForm.getPromotionLimitPerCustomerCheckBox() != null){
			if (StringUtils.isBlank(addPromotionForm.getPromotionLimitPerCustomer())) {
				
				if(addPromotionForm.getPromotionLimitPerCustomerCheckBox().equals(Boolean.FALSE))
					errors.rejectValue("promotionLimitPerCustomer",
						"add.promotion.limitPerCustomer.blank"/*,
						"Please enter Limit Per Customer"*/);
			}else if(!StringUtils.isNumeric(addPromotionForm.getPromotionLimitPerCustomer())){
				errors.rejectValue("promotionLimitPerCustomer",
						"add.promotion.limitPerCustomer.nonnumeric"/*,
						"Please enter numeric value"*/);
			}else if(Long.parseLong(addPromotionForm.getPromotionLimitPerCustomer())<1)
			{
				if(addPromotionForm.getPromotionLimitPerCustomerCheckBox().equals(Boolean.FALSE))
				{		
					errors.rejectValue("promotionLimitPerCustomer",
							"add.promotion.limitPerCustomer.nonzero"/*,
							"Please enter Limit greater than 0"*/);
				}
				else
				{
					errors.rejectValue("promotionLimitPerCustomer",
							"add.promotion.limitPerCustomer.checkBox"/*,
							"Either CheckBox 'infinite' OR 'Limit Per Customer' value is allowed"*/);
				}
			}
			else{
				
				if(addPromotionForm.getPromotionLimitPerCustomerCheckBox().equals(Boolean.TRUE))
					errors.rejectValue("promotionLimitPerCustomer",
						"add.promotion.limitPerCustomer.checkBox"/*,
						"Either CheckBox 'infinite' OR 'Limit Per Customer' value is allowed"*/);
				
				if (!StringUtils.isNumeric(addPromotionForm.getPromotionLimitPerCustomer())
						&& !StringUtils.isNumericSpace(addPromotionForm.getPromotionLimitPerCustomer().toString())) {
					errors.rejectValue("promotionLimitPerCustomer",
							"add.promotion.not.numeric"/*,
							"Please enter digits only"*/);	
				}
			}
		}

		//==========Promotion Limit Per Promotion Validation==========
		
		
		if(addPromotionForm.getPromotionLimitPerPromotionCheckBox() != null){
			if (StringUtils.isBlank(addPromotionForm.getPromotionLimitPerPromotion())) {
				
				if(addPromotionForm.getPromotionLimitPerPromotionCheckBox().equals(Boolean.FALSE))
					errors.rejectValue("promotionLimitPerPromotion",
						"add.promotion.limitPerPromotion.blank"/*,
						"Please enter Limit Per Promotion"*/);
			}else if(!StringUtils.isNumeric(addPromotionForm.getPromotionLimitPerPromotion())){
				errors.rejectValue("promotionLimitPerPromotion",
						"add.promotion.limitPerPromotion.nonnumeric"/*,
						"Please enter numeric value"*/);
			}else if(Long.parseLong(addPromotionForm.getPromotionLimitPerPromotion())<1)
			{
				if(addPromotionForm.getPromotionLimitPerPromotionCheckBox().equals(Boolean.FALSE))
				{
					errors.rejectValue("promotionLimitPerPromotion",
							"add.promotion.limitPerPromotion.nonzero"/*,
							"Please enter Limit greater than 0"*/);
				}
				else
				{
					errors.rejectValue("promotionLimitPerPromotion",
							"add.promotion.limitPerPromotion.checkBox"/*,
							"Either CheckBox 'infinite' OR 'Limit Per Promotion' value is allowed"*/);
				}
			}
			else{
				
				if(addPromotionForm.getPromotionLimitPerPromotionCheckBox().equals(Boolean.TRUE))
					errors.rejectValue("promotionLimitPerPromotion",
						"add.promotion.limitPerPromotion.checkBox"/*,
						"Either CheckBox 'infinite' OR 'Limit Per Promotion' value is allowed"*/);
				
				
				if (!StringUtils.isNumeric(addPromotionForm.getPromotionLimitPerPromotion())
						&& !StringUtils.isNumericSpace(addPromotionForm.getPromotionLimitPerPromotion().toString())) {
					errors.rejectValue("promotionLimitPerPromotion",
							"add.promotion.not.numeric"/*,
							"Please enter digits only"*/);	
				}
			}		
		}
		
		
		//==========Promotion Status Validation==========
		if (!addPromotionForm.getStatus().equals("InActive") && !addPromotionForm.getStatus().equals("Active")) {
			
			errors.rejectValue("status",
					"add.promotion.status.not.selected"/*,
					"Please select Status"*/);
		}
		
		
		//==========From Date Validation==========
		if (StringUtils.isBlank(addPromotionForm.getFromDate())) {
			
			errors.rejectValue("fromDate",
					"add.promotion.fromDate.blank"/*,
					"Please enter From Date"*/);
		}
		else{
			Date fromDate = null;
			Date toDate = null;
			try {
				fromDate = view_date_format.parse(addPromotionForm.getFromDate());
				Calendar now = Calendar.getInstance();
				now.setTime(fromDate);
				now.add(Calendar.MINUTE, 30);
				Date fromDate_temp = now.getTime();
				if(fromDate_temp.compareTo(new Date()) < 0 && addPromotionForm.getPageMode().equals("Add"))
				//if(fromDate_temp.compareTo(new Date()) < 0)
					errors.rejectValue("fromDate",
							"add.promotion.fromDate.before.current.date"/*,
							"'From Date' should be equal Or after current date/time"*/);
			}catch (ParseException e) {
				errors.rejectValue("fromDate",
						"add.promotion.fromDate.incorrect.format"/*,
						"'From Date' format should be 'MM-DD-YYYY HH:mm:ss'"*/);
			}
			if(!addPromotionForm.getToDate().isEmpty()){
				try {
				toDate = view_date_format.parse(addPromotionForm.getToDate());
				//if(toDate.compareTo(new Date()) < 0 && addPromotionForm.getPageMode().equals("Add"))
				if(toDate.compareTo(new Date()) < 0)
					errors.rejectValue("toDate",
							"add.promotion.toDate.before.current.date"/*,
							"'To Date' should be after current date/time"*/);
				else if(fromDate != null){
					if(fromDate.compareTo(toDate) > 0 )
						errors.rejectValue("fromDate",
								"add.promotion.fromDate.before.toDate"/*,
								"'From Date' should be equal Or after 'To Date'"*/);
				}
				}catch (ParseException e) {
					errors.rejectValue("toDate",
							"add.promotion.toDate.incorrect.format"/*,
							"'To Date' format should be 'MM-DD-YYYY HH:mm:ss'"*/);
				}
			}
				
			 
		}
		
		//==========To Date Validation==========
		if (StringUtils.isBlank(addPromotionForm.getToDate())) {
			
			errors.rejectValue("toDate",
					"add.promotion.toDate.blank"/*,
					"Please enter To Date"*/);
		}
		else if(addPromotionForm.getToDate().compareTo(addPromotionForm.getFromDate()) == 0)
		{
			errors.rejectValue("toDate",
					"add.promotion.toDate.equalToFromDate"/*,
					"'From Date' date and time can not be equal to 'To Date' date and time"*/);
		}
		//==========Discount Type Validation==========
		if (addPromotionForm.getDiscountType().toString().equals("0")) {
			
			errors.rejectValue("errorDiscount",
					"add.promotion.discount.blank"/*,
					"Select Discount Type"*/);
			flag = 1;
		}
		//==========Discount/Cash Value Validation==========
		else {
			if (StringUtils.isBlank(addPromotionForm.getDiscountValue())) {
				errors.rejectValue("errorDiscount",
					"add.promotion.take.blank"/*,
					"Please enter Take value"*/);
				flag = 1;
			}else{
				try{
					Float.parseFloat(addPromotionForm.getDiscountValue());	
				}catch (NumberFormatException e) {
					errors.rejectValue("errorDiscount",
							"add.promotion.take.not.numeric"/*,
							"Value entered for 'Take' should be Numeric"*/);
						flag = 1;
			    }
				if(flag != 1){
					if (((new BigDecimal(addPromotionForm.getDiscountValue())).compareTo(new BigDecimal(0)))==0){
						errors.rejectValue("errorDiscount",
								"add.promotion.take.not.numeric"/*,
								"Please enter non-negative value for 'Take' and upto two decimal places"*/);
					
					}else if(!this.validatePattern(addPromotionForm.getDiscountValue(), ORDER_OVER_PATTERN)){
						errors.rejectValue("errorDiscount",
								"add.promotion.take.negative"/*,
								"Please enter non-negative value for 'Take' and upto two decimal places"*/);
							flag = 1;
					
					}
				}
				
				
			}
			
/*		float discountValue = 0;
		if(addPromotionForm.getDiscountType().toString().equals("2")){
			try{
				 discountValue = Float.parseFloat(addPromotionForm.getDiscountValue().isEmpty() ? "0" : addPromotionForm.getDiscountValue());
				 flag = 1;
			}catch(Exception exception){
				errors.rejectValue("errorDiscount",
						"add.promotion.take.not.numeric",
						"Discount value should not greater than 100");
					flag = 1;
			}
			if(flag != 1 && discountValue < DISCOUNT_MAX_VALUE){
				errors.rejectValue("errorDiscount",
						"add.promotion.discount.value.greator.than.hundred",
						"Discount value should not greater than 100");
					flag = 1;
			}
		}*/
		if(flag == 0){
			//==========Mode Validation==========
			if (addPromotionForm.getMode().toString().equals("0")) {
				
				errors.rejectValue("errorDiscount",
						"add.promotion.mode.blank"/*,
						"Select Mode"*/);
			}
			else{
				
				if (addPromotionForm.getMode().toString().equals("2")) {
					if(StringUtils.isBlank(addPromotionForm.getOrderOver())){
						errors.rejectValue("errorDiscount",
								"add.promotion.orderover.blank"/*,
								"Please enter Order Over value"*/);
					
					}else{
					
						try{
							Float.parseFloat(addPromotionForm.getOrderOver());	
						}catch (NumberFormatException e) {
							errors.rejectValue("errorDiscount",
									"add.promotion.orderover.not.numeric"/*,
									"Value entered for 'Order Over' should be Numeric"*/);
								flag = 1;
					    }
						
						if(flag != 1){
							if (((new BigDecimal(addPromotionForm.getOrderOver())).compareTo(new BigDecimal(0)))< 0){
								errors.rejectValue("errorDiscount",
										"add.promotion.orderover.negative"/*,
										"Please enter non-negative value for 'Order over'"*/);
										
								
								
							}
						
							else if(!this.validatePattern(addPromotionForm.getOrderOver(), ORDER_OVER_PATTERN)){
								errors.rejectValue("errorDiscount",
										"add.promotion.orderover.not.numeric"/*,
										"Please enter non-negative value for 'Order over' and upto two decimal places"*/);
								flag = 1;
								
							}
						}
					}
				}
				else if (addPromotionForm.getMode().toString().equals("3")) {
					if(addPromotionForm.getCategoryId().equals("0"))
					{
						errors.rejectValue("errorDiscount",
								"add.promotion.category.blank"/*,
								"Select Category"*/);
					}
					else if(addPromotionForm.getProductId().equals("0"))
					{
						errors.rejectValue("errorDiscount",
								"add.promotion.product.blank"/*,
								"Select Product"*/);
					}	
				}
			}
		}
	}
		
		
	}
	
	public boolean validatePattern(final String input, String pattern) {
		return Pattern.compile(pattern).matcher(input).matches();

	}	


}
