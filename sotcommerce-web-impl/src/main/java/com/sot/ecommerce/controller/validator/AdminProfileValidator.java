package com.sot.ecommerce.controller.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



@Component
public class AdminProfileValidator implements Validator {

	private static final int USER_NAME_MAX_LENGTH = 16;

	private static final int USER_PHONE_MAX_LENGTH = 10;
	private static final int USER_EMAIL_MAX_LENGTH = 16;

	private static final String EMAIL_ID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String FIRST_NAME_PATTERN = "^[a-zA-Z0-9]{1,16}$";

	private static final String LAST_NAME_PATTERN = "^[a-zA-Z0-9]{0,16}$";

	private static final String PHONE_PATTERN = "^[0-9]{10}$";
	private static final String ZIPCODE_PATTERN = "^[0-9]{8}$";

	// common pattern for alphnumeric
	private static final String COMMON_PATTERN = "^[a-zA-Z0-9]{0,16}$";

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object command, Errors errors) {

		AdminProfileForm adminProfileForm = (AdminProfileForm) command;

		if (StringUtils.isNotBlank(adminProfileForm.getUserFirstName())) {

			if (adminProfileForm.getUserFirstName().length() > USER_NAME_MAX_LENGTH) {

				errors.rejectValue("userFirstName",
						"profileedit.userFirstName.max.length",
						"Your first name should not be greater than"
								+ USER_NAME_MAX_LENGTH + " characters.");

			}

			if (!this.validatePattern(adminProfileForm.getUserFirstName(),
					FIRST_NAME_PATTERN)) {

				errors.rejectValue("userFirstName",
						"profileedit.userFirstName.format",
						"userFirstName can be only alphanumeric");
			}

		}

		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userFirstName",
					"profileedit.userFirstName.blank", "First Name required");

		}
		
		

		/*if (adminProfileForm.getUserLastName().length() > USER_NAME_MAX_LENGTH) {

			errors.rejectValue("userLastName",
					"profileedit.userLastName.max.length",
					"Your last name should not be greater than "
							+ USER_NAME_MAX_LENGTH + " characters.");

		}*/

		if (!this.validatePattern(adminProfileForm.getUserLastName(),
				LAST_NAME_PATTERN)) {

			errors.rejectValue("userLastName",
					"profileedit.userLastName.format",
					"userLastName can be only alphanumeric");
		}
		
		
		

		if (StringUtils.isNotBlank(adminProfileForm.getUserPhone())) {

			if (adminProfileForm.getUserPhone().length() > USER_PHONE_MAX_LENGTH) {

				errors.rejectValue("userPhone",
						"profileedit.userPhone.max.length", "Your "
								+ "phone should not be greater than "
								+ USER_PHONE_MAX_LENGTH + " characters.");

			}

			if (!this.validatePattern(adminProfileForm.getUserPhone(),
					PHONE_PATTERN)) {

				errors.rejectValue("userPhone", "profileedit.userPhone.format",
						"userPhone can be only numeric and exactly 10 numbers");
			}

		}

		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPhone",
					"profileedit.userPhone.blank", "Phone number required");

		}

		if (StringUtils.isNotBlank(adminProfileForm.getUserEmail())) {

			if (adminProfileForm.getUserEmail().length() > USER_EMAIL_MAX_LENGTH) {

				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail",
						"profileedit.userEmail.max.length", "Your "
								+ "email should not be greater than "
								+ USER_EMAIL_MAX_LENGTH + " characters.");

				errors.rejectValue("userEmail",
						"profileedit.userEmail.max.length", "Your "
								+ "email should not be greater than "
								+ USER_EMAIL_MAX_LENGTH + " characters.");

			}

			if (!this.validateEmailID(adminProfileForm.getUserEmail())) {
				errors.rejectValue("userEmail", "profileedit.userEmail.format",
						"email id is not in correct format.");
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail",
					"profileedit.userEmail.blank", "Email required");

		}

		if (!this.validatePattern(adminProfileForm.getCity(), COMMON_PATTERN)) {

			errors.rejectValue("city", "profileedit.city.format",
					"city can be only alphanumeric");
		}

		if (!this.validatePattern(adminProfileForm.getState(), COMMON_PATTERN)) {

			errors.rejectValue("street", "profileedit.street.format",
					"street can be only alphanumeric");

		}

		if (!this.validatePattern(adminProfileForm.getZipcode(),
				ZIPCODE_PATTERN)) {

			errors.rejectValue("zipcode", "profileedit.zipcode.format",
					"zipcode can be only numeric");

		}

		
		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryId",
					"profileedit.countryId.blank", "Country ID required");

		}

	}

	public boolean validateEmailID(final String emailID) {
		return Pattern.compile(EMAIL_ID_PATTERN).matcher(emailID).matches();

	}

	public boolean validatePattern(final String input, String pattern) {
		return Pattern.compile(pattern).matcher(input).matches();

	}

}
