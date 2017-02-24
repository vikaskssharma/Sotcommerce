/**
 * 
 */
package com.sot.ecommerce.controller.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author simanchal.patra
 * 
 */
@Component
public class SignInValidator implements Validator {

	private static final int USER_PASSWORD_MAX_LENGTH = 16;

	private static final int USER_PASSWORD_MIN_LENGTH = 8;

	private static final String EMAIL_ID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public boolean supports(Class<?> clazz) {
		return SignInValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {

		UserSignInForm userSignInForm = (UserSignInForm) command;

		if (StringUtils.isNotBlank(userSignInForm.getUserEmail())) {

			if (!this.validateEmailID(userSignInForm.getUserEmail())) {
				errors.rejectValue("userEmail", "signin.loginid.format",
						"login Id is not in correct format.");
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail",
					"signin.loginid.blank", "Login ID required");
		}

		if (StringUtils.isNotBlank(userSignInForm.getUserPassword())) {

			if (userSignInForm.getUserPassword().length() > USER_PASSWORD_MAX_LENGTH) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"userPassword", "signin.password.max.length",
						"Your password should not be greater than "
								+ USER_PASSWORD_MAX_LENGTH + " characters.");
			} else if (userSignInForm.getUserPassword().length() < USER_PASSWORD_MIN_LENGTH) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"userPassword", "signin.password.min.length",
						"Your password should not be less than "
								+ USER_PASSWORD_MIN_LENGTH + " characters.");
			} else {

			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword",
					"signin.password.blank", "Password required");
		}

	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validateEmailID(final String emailID) {
		return Pattern.compile(EMAIL_ID_PATTERN).matcher(emailID).matches();

	}

}
