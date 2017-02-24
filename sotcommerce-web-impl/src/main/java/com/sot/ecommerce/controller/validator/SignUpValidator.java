/**
 * 
 */
package com.sot.ecommerce.controller.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



/**
 * @author simanchal.patra
 * 
 */
@Component
public class SignUpValidator implements Validator {

	private static final int USER_FIRST_NAME_MAX_LENGTH = 16;

	private static final int USER_FIRST_NAME_MIN_LENGTH = 1;

	private static final int USER_lAST_NAME_MAX_LENGTH = 16;

	private static final int USER_ZIPCODE_MAX_LENGTH = 8;

	private static final int USER_PASSWORD_MAX_LENGTH = 16;

	private static final int USER_PASSWORD_MIN_LENGTH = 8;

	private static final int ZERO_LENGTH = 0;

	private static final String CITY_PATTERN = "^[a-zA-Z0-9]{0,16}$";

	private static final String STATE_PATTERN = "^[a-zA-Z0-9]{0,16}$";

	private static final String PHONE_PATTERN = "^[0-9]{10}$";
	private static final String ZIPCODE_PATTERN = "^[0-9]{8}$";
	private static final String FIRST_NAME_PATTERN = "^[a-zA-Z0-9]{1,16}$";
	private static final String LAST_NAME_PATTERN = "^[a-zA-Z0-9]{0,16}$";
	private static final String ADDRESS_PATTERN = "^[a-zA-Z0-9]+([ \t-]?[a-zA-Z0-9]+)*$";//"^[a-zA-Z0-9]{8,128}$";

	private static final String EMAIL_ID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public boolean supports(Class<?> clazz) {
		return SignUpValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {

		UserSignUpForm userSignUpForm = (UserSignUpForm) command;

		if (StringUtils.isNotBlank(userSignUpForm.getFirstName())) {

			if (userSignUpForm.getFirstName().length() > USER_FIRST_NAME_MAX_LENGTH) {
				errors.rejectValue("firstName",
						"signup.user.first.name.max.length",
						"First name should not be greater than "
								+ USER_FIRST_NAME_MAX_LENGTH + "characters");
			} else if (userSignUpForm.getFirstName().length() < USER_FIRST_NAME_MIN_LENGTH) {
				errors.rejectValue("firstName",
						"signup.user.first.name.min.length",
						"First name should not be greater than "
								+ USER_FIRST_NAME_MIN_LENGTH + "characters");
			}

			if (!this.validatePattern(userSignUpForm.getFirstName(),
					FIRST_NAME_PATTERN)) {
				errors.rejectValue("firstName",
						"signup.user.first.name.format",
						"First name should only contain aplhanumeric characters.");

			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
					"signup.user.first.name.blank", "First Name required");
		}

		if (userSignUpForm.getLastName().length() > 0) {
			if (userSignUpForm.getLastName().length() > USER_lAST_NAME_MAX_LENGTH) {
				errors.rejectValue("lastName",
						"signup.user.last.name.max.length",
						"Last name should not be greater than "
								+ USER_lAST_NAME_MAX_LENGTH + "characters");
			}

			else if (!this.validatePattern(userSignUpForm.getLastName(),
					LAST_NAME_PATTERN)) {
				errors.rejectValue("userLastName",
						"signup.user.last.name.format",
						"userLastName can be only alphanumeric");

			}
		}

		if (StringUtils.isNotBlank(userSignUpForm.getEmailID())) {

			if (!validateEmailID(userSignUpForm.getEmailID())) {
				errors.rejectValue("emailID", "signup.user.email.ID.format",
						"Email Id is not in correct format.");
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailID",
					"signup.user.email.ID.blank", "Email ID required");
		}

		if (StringUtils.isNotBlank(userSignUpForm.getAddress())) {

			if (!this.validatePattern(userSignUpForm.getAddress(),
					ADDRESS_PATTERN)) {
				errors.rejectValue("address", "signup.user.address.format",
						"Special Characters are not allowed.");
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
					"signup.user.address.blank", "Address required");
		}

		if (StringUtils.isNotBlank(userSignUpForm.getPhone())) {

			if (!this.validatePattern(userSignUpForm.getPhone(),
					PHONE_PATTERN)) {
				errors.rejectValue("phone", "signup.user.phone.format",
						"Only numeric characters are allowed.");
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone",
					"signup.user.phone.blank", "Phone  required");
		}

		if (userSignUpForm.getCity().length() > ZERO_LENGTH) {
			if (!this.validatePattern(userSignUpForm.getCity(), CITY_PATTERN)) {

				errors.rejectValue("city", "signup.state.format",
						"city can be only alphanumeric and max of 16 character");
			}
		}

		if (userSignUpForm.getCity().length() > ZERO_LENGTH) {

			if (!this.validatePattern(userSignUpForm.getState(), STATE_PATTERN)) {

				errors.rejectValue("state", "signup.state.format",
						"state can be only alphanumeric and max of 16 character");

			}

		}

		if (userSignUpForm.getZipcode().length() > ZERO_LENGTH) {
			if (userSignUpForm.getZipcode().length() > USER_ZIPCODE_MAX_LENGTH) {

				errors.rejectValue("zipcode", "signup.zipcode.max.length",
						"Your " + "zipcode must be of  "
								+ USER_ZIPCODE_MAX_LENGTH + " characters.");

			}

			else if (!this.validatePattern(userSignUpForm.getZipcode(),
					ZIPCODE_PATTERN)) {

				errors.rejectValue("zipcode", "signup.zipcode.format",
						"zipcode can be only numeric and exactly 8 numbers");
			}

		}

		if (StringUtils.isNotBlank(userSignUpForm.getPassword())) {

			if (userSignUpForm.getPassword().length() > USER_PASSWORD_MAX_LENGTH) {
				errors.rejectValue("password",
						"signup.user.password.max.length",
						"User name should not be greater than "
								+ USER_PASSWORD_MAX_LENGTH + "characters");
			} else if (userSignUpForm.getPassword().length() < USER_PASSWORD_MIN_LENGTH) {
				errors.rejectValue("password",
						"signup.user.password.min.length",
						"User name should not be greater than "
								+ USER_PASSWORD_MIN_LENGTH + "characters");
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
					"signup.user.password.blank", "Password required");
		}

		if (StringUtils.isNotBlank(userSignUpForm.getCnfPassword())) {

			if (userSignUpForm.getCnfPassword().length() > USER_PASSWORD_MAX_LENGTH) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"cnfPassword", "signup.user.password.max.length",
						"Your password should not be greater than "
								+ USER_PASSWORD_MAX_LENGTH + " characters.");
			} else if (userSignUpForm.getCnfPassword().length() < USER_PASSWORD_MIN_LENGTH) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"cnfPassword", "signup.user.password.min.length",
						"Your password should not be less than "
								+ USER_PASSWORD_MIN_LENGTH + " characters.");
			} else {

				if (!userSignUpForm.getCnfPassword().equals(
						userSignUpForm.getPassword())) {
					errors.rejectValue("cnfPassword",
							"signup.user.password.mismatch",
							"Password and Confirm Password should be same");
				}
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cnfPassword",
					"signup.user.password.confirm.blank",
					"Confirm Password required");
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

	public boolean validatePattern(final String input, String pattern) {
		return Pattern.compile(pattern).matcher(input).matches();

	}

}
