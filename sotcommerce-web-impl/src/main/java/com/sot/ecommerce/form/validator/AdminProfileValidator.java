package com.sot.ecommerce.form.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sbsc.fos.um.web.form.ProfileForm;
import com.sbsc.fos.um.web.form.UserSignInForm;

@Component
public class AdminProfileValidator implements Validator {

	private static final int USER_NAME_MAX_LENGTH = 16;

	private static final int USER_PHONE_LENGTH_MAX = 10;
	private static final int USER_PHONE_LENGTH_MIN = 7;
	private static final int USER_EMAIL_MAX_LENGTH = 16;

	private static final String EMAIL_ID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String FIRST_NAME_PATTERN = "^[a-zA-Z0-9 ]{1,16}$";

	private static final String LAST_NAME_PATTERN = "^[a-zA-Z0-9 ]{0,16}$";

	private static final String PHONE_PATTERN = "^[0-9]{7,10}$";
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

		ProfileForm adminProfileForm = (ProfileForm) command;

		if (StringUtils.isNotBlank(adminProfileForm.getUserFirstName().trim())) {

			if (adminProfileForm.getUserFirstName().length() > USER_NAME_MAX_LENGTH) {

				errors.rejectValue("userFirstName",
						"profileedit.userFirstName.max.length"
						);

			}

			if (!this.validatePattern(adminProfileForm.getUserFirstName().trim(),
					FIRST_NAME_PATTERN)) {

				errors.rejectValue("userFirstName",
						"profileedit.userFirstName.format");
			}

		}

		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userFirstName",
					"profileedit.userFirstName.blank");

		}
		
		

		
		if (!this.validatePattern(adminProfileForm.getUserLastName().trim(),
				LAST_NAME_PATTERN)) {

			errors.rejectValue("userLastName",
					"profileedit.userLastName.format");
		}
		
		
		

		if (StringUtils.isNotBlank(adminProfileForm.getUserPhone().trim())) {

			if (adminProfileForm.getUserPhone().length()> USER_PHONE_LENGTH_MAX || adminProfileForm.getUserPhone().length()< USER_PHONE_LENGTH_MIN ) {

				errors.rejectValue("userPhone",
						"profileedit.phone.max.length");

			}	

			if (!this.validatePattern(adminProfileForm.getUserPhone().trim(),
					PHONE_PATTERN)) {

				errors.rejectValue("userPhone", "profileedit.userPhone.format");
			}

		}

		else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPhone",
					"profileedit.userPhone.blank");

		}

		if (StringUtils.isNotBlank(adminProfileForm.getUserEmail().trim())) {

			if (!this.validateEmailID(adminProfileForm.getUserEmail().trim())) {
				errors.rejectValue("userEmail", "profileedit.userEmail.format");
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail",
					"profileedit.userEmail.blank");

		}

		if (!this.validatePattern(adminProfileForm.getCity().trim(), COMMON_PATTERN)) {

			errors.rejectValue("city", "profileedit.city.format");
		}

		if (!this.validatePattern(adminProfileForm.getState().trim(), COMMON_PATTERN)) {

			errors.rejectValue("state", "profileedit.state.format");

		}

		if (!this.validatePattern(adminProfileForm.getZipcode().trim(),
				ZIPCODE_PATTERN)) {

			errors.rejectValue("zipcode", "profileedit.zipcode.format");

		}

		
		if(adminProfileForm.getCountryId() == 0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryId",
					"profileedit.countryId.blank");

		}

	}

	public boolean validateEmailID(final String emailID) {
		return Pattern.compile(EMAIL_ID_PATTERN).matcher(emailID).matches();

	}

	public boolean validatePattern(final String input, String pattern) {
		return Pattern.compile(pattern).matcher(input).matches();

	}

}
