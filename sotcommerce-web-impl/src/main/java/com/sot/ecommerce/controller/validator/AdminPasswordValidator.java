package com.sot.ecommerce.controller.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



@Component
public class AdminPasswordValidator implements Validator {

	private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{1,16}$";

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object command, Errors errors) {

		ChangePasswordForm changePasswordForm = (ChangePasswordForm) command;

		if (StringUtils.isNotBlank(changePasswordForm.getOldPassword())) {

			if (!changePasswordForm.getUserPassword().equals(changePasswordForm.getOldPassword())) {
				errors.rejectValue("oldPassword",
						"passwordupdate.oldpassword.notmatch",
						"old password is not correct");
				return;

			}
			
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword",
					"required.oldPassword", "Field name is required.");

		}
		
		
		
		

		if (StringUtils.isNotBlank(changePasswordForm.getPassword())) {

			if (!this.validatePattern(changePasswordForm.getPassword(),
					PASSWORD_PATTERN)) {

				errors.rejectValue("password", "passwordupdate.password.format",
						"password can be only alphanumeric");

			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
					"required.password", "password is required.");
			
			

		}
		
		if (StringUtils.isNotBlank(changePasswordForm.getConfirmPassword())) {
			
			if (!this.validatePattern(changePasswordForm.getConfirmPassword(),
					PASSWORD_PATTERN)) {

				errors.rejectValue("confirmPassword", "passwordupdate.newpassword.format",
						"confirmPassword can be only alphanumeric");
			}
			
		}
		
		else
		{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
					"required.confirmPassword", "new password is required.");
			
		
			
		}
	

	}

	public boolean validatePattern(final String input, String pattern) {
		return Pattern.compile(pattern).matcher(input).matches();

	}

}
