package com.sot.ecommerce.form.validator;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sbsc.fos.feedback.web.form.FeedbackForm;
import com.sbsc.fos.taxation.web.form.TaxationForm;

/**
 * 
 * @author gaurav.kumar
 * 
 */

@Component
public class TaxationFormValidator implements Validator {


	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private static final String TAX_PERCENTAGE_PATTERN = "[0-9]+([.][0-9]{1,2})?";
	@Override
	public void validate(Object command, Errors errors) {

		TaxationForm taxationForm = (TaxationForm) command;
		
		if(taxationForm.getCountryId()=="" || taxationForm.getCountryId().equals("-1")){
			errors.rejectValue("countryName",
					"taxation.country.empty");	
		}
		
		if (
				!StringUtils.isNotBlank(taxationForm.getTaxPercentage()) ||
				taxationForm.getTaxPercentage().trim().equals(".")
				) {
			errors.rejectValue("taxPercentage",
					"taxation.value.nonNumeric");	
		}else if(!this.validatePattern(taxationForm.getTaxPercentage(), TAX_PERCENTAGE_PATTERN) ){
			errors.rejectValue("taxPercentage",
					"taxation.value.decimaltwoplaces");
		}else{
		
			BigDecimal taxPctg = new BigDecimal(taxationForm.getTaxPercentage());
			BigDecimal limit = new BigDecimal(100);
			int result = taxPctg.compareTo(limit);
			if(result==1){
				errors.rejectValue("taxPercentage",
						"taxation.value.less.than.hundred");
			}
		}
		
	}


	
	 public boolean validatePattern(final String input, String pattern) {
			return Pattern.compile(pattern).matcher(input).matches();

		}
	
}
