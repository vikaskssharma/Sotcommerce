package com.sot.ecommerce.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sbsc.fos.customersupport.form.CustomerSupportForm;

@Component
public class CustomerAdminResponseValidator  implements Validator {
	private static final int  RESPONSE_MAX_LENGTH=1024;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object command, Errors errors) {
		// TODO Auto-generated method stub
		CustomerSupportForm custsupportForm = (CustomerSupportForm) command;
		
		if (!(StringUtils.isNotBlank(custsupportForm.getResponse()))) {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "response",
					"customersupport.response.blank", "Please provide Answer");

		}
		
			if (custsupportForm.getResponse().length() > RESPONSE_MAX_LENGTH) {
				errors.rejectValue("response",
						"response.max.length",
						"Response must not be greater than 1024  characters characters");
			}
			
		
	}

}
