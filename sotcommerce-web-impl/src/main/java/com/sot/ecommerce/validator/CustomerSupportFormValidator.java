package com.sot.ecommerce.validator;

/**
 * 
 */

import java.util.Date;
import java.util.regex.Pattern;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sbsc.fos.customersupport.form.CustomerSupportForm;
import com.sbsc.fos.customersupport.web.handler.CustomerSupportHandler;
import com.sbsc.fos.um.web.form.ProfileForm;
import com.sbsc.fos.um.web.form.UserSignInForm;


@Component
public class CustomerSupportFormValidator implements Validator {

	private static final int USER_NAME_MAX_LENGTH = 16;
	private static final int ORDER_NO_MAX_LENGTH = 32;
	private static final int USER_PHONE_MAX_LENGTH = 10;
	private static final int USER_PHONE_MIN_LENGTH = 7;
	private static final int USER_EMAIL_MAX_LENGTH = 32;
	private static final int  MESSAGE_MAX_LENGTH=1024;

	private static final String EMAIL_ID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String FIRST_NAME_PATTERN = "^[a-zA-Z0-9]{1,16}$";
	private static final String ORDER_NO_NAME_PATTERN = "^[a-zA-Z0-9-]{1,16}$";//[a-zA-Z0-9#\",'& .()%*-]*$

	private static final String LAST_NAME_PATTERN = "^[a-zA-Z0-9]{0,16}$";

	private static final String PHONE_PATTERN = "^[0-9]{7,10}$";
	
	@Autowired
	private CustomerSupportHandler cutomerSupportHandler;
   
	public CustomerSupportHandler getCutomerSupportHandler() {
		return cutomerSupportHandler;
	}

	public void setCutomerSupportHandler(
			CustomerSupportHandler cutomerSupportHandler) {
		this.cutomerSupportHandler = cutomerSupportHandler;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object command, Errors errors) {

		CustomerSupportForm custsupportForm = (CustomerSupportForm) command;
		// FIRST NAME

		if (StringUtils.isNotBlank(custsupportForm.getFirstname())) {

			if (custsupportForm.getFirstname().length() > USER_NAME_MAX_LENGTH) {

				errors.rejectValue("firstname",
						"customersupport.firstname.max.length");

			}

			if (!StringUtils
					.isAlphanumericSpace(custsupportForm.getFirstname())
					&& !StringUtils.isAlphanumeric(custsupportForm
							.getFirstname())) {

				errors.rejectValue("firstname",
						"customersupport.firstname.format");
			}

		}

		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname",
					"customersupport.firstname.blank");

		}

		// LASTNAME
		if (StringUtils.isNotBlank(custsupportForm.getLastname())) {

			if (custsupportForm.getLastname().length() > USER_NAME_MAX_LENGTH) {

				errors.rejectValue("lastname","customersupport.lastname.max.length");

			}

			if (!StringUtils.isAlphanumericSpace(custsupportForm.getLastname())
					&& !StringUtils.isAlphanumeric(custsupportForm
							.getLastname())) {

				errors.rejectValue("lastname","customersupport.lastname.format");
			}

		}

		

		// ORDER NO
		if (StringUtils.isNotBlank(custsupportForm.getOrderNumber())) {

			if (custsupportForm.getOrderNumber().length() > ORDER_NO_MAX_LENGTH) {

				errors.rejectValue("orderNumber","customersupport.orderNumber.max.length");

			} else {

				if (!this.validatePattern(custsupportForm.getOrderNumber(),
						ORDER_NO_NAME_PATTERN)) {

					errors.rejectValue("orderNumber","customersupport.orderNumber.format");
				} else {
					
					String ordernumber = custsupportForm
							.getOrdernumberfromdatabase();
					String emailaddress=cutomerSupportHandler.getValidateEmailAddressc(custsupportForm.getEmailaddress());
					if(emailaddress!=null){
					if (!(custsupportForm.getOrderNumber().equals(ordernumber))) {
						errors.rejectValue("orderNumber",
								"customersupport.orderNumber.validity");
					  }
					}
				}
			}

		}

		
//issues status
		if (StringUtils.isBlank(custsupportForm.getIssueStatus())){
			errors.rejectValue("issueStatus",
					"customersupport.issueStatus.blank");
		}
		
		// phone number
		if (StringUtils.isNotBlank(custsupportForm.getPhonenumber())) {

			if (custsupportForm.getPhonenumber().length()< USER_PHONE_MIN_LENGTH && custsupportForm.getPhonenumber().length() > USER_PHONE_MAX_LENGTH) {

				errors.rejectValue("phonenumber","customersupport.phonenumber.length");

			}

			if (!this.validatePattern(custsupportForm.getPhonenumber(),
					PHONE_PATTERN)) {

				errors.rejectValue("phonenumber","customersupport.phonenumber.format");
			}

		}

		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phonenumber",
					"customersupport.phonenumber.blank");

		}

		// email address
		if (StringUtils.isNotBlank(custsupportForm.getEmailaddress())) {

			if (custsupportForm.getEmailaddress().length() > USER_EMAIL_MAX_LENGTH) {
				errors.rejectValue("emailaddress","signup.user.email.max.length");
			}
			if (!this.validateEmailID(custsupportForm.getEmailaddress())) {
				errors.rejectValue("emailaddress","customersupport.emailaddress.format"
						);
			
			}else{
				String emailaddress=cutomerSupportHandler.getValidateEmailAddressc(custsupportForm.getEmailaddress());
					if(emailaddress==null && custsupportForm.getOrderNumber()!=""){
						errors.rejectValue("emailaddress","customersupport.emailaddress.valid");
					}
				}
			    

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailaddress",
					"customersupport.emailaddress.blank", "Email required");

		}

		
		if (!(StringUtils.isNotBlank(custsupportForm.getQuestion()))) {

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "question",
					"customersupport.question.blank", "Please provide the query");

		}else
		{
			if (custsupportForm.getQuestion().length() > MESSAGE_MAX_LENGTH) {
				errors.rejectValue("question",
						"question.max.length",
						"Question must not be greater than 1024  characters characters");
			}
			
		}
		
		
	}
	
	

	public boolean validateEmailID(final String emailID) {
		return Pattern.compile(EMAIL_ID_PATTERN).matcher(emailID).matches();

	}

	public boolean validatePattern(final String input, String pattern) {
		return Pattern.compile(pattern).matcher(input).matches();

	}

}
