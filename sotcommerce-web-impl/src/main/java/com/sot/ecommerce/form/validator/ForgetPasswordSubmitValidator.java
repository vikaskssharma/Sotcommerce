package com.sot.ecommerce.form.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sbsc.fos.um.web.form.ChangePasswordForm;

@Component
public class ForgetPasswordSubmitValidator implements Validator {

	
	private static final int USER_PASSWORD_MAX_LENGTH = 16;
	private static final int USER_PASSWORD_MIN_LENGTH = 8;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ForgetPasswordSubmitValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		
		
		ChangePasswordForm changePasswordForm = (ChangePasswordForm) command;		

		if (StringUtils.isNotBlank(changePasswordForm.getPassword())) {

			
			if (changePasswordForm.getPassword().length()>USER_PASSWORD_MAX_LENGTH || changePasswordForm.getPassword().length()<USER_PASSWORD_MIN_LENGTH) {

				errors.rejectValue("password",
						"passwordupdate.password.length");
				return;
				
			}			
			else if(changePasswordForm.getPassword().equals(changePasswordForm.getOldPassword()))
			{
				errors.rejectValue("password",
						"passwordupdate.password.equaloldpassword");
				return;
			}
			
			
			
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
					"passwordupdate.password.required");
			
			return;

		}
		
		
		
		
		if (StringUtils.isNotBlank(changePasswordForm.getConfirmPassword())) {
			
		  if(!changePasswordForm.getPassword().equals(changePasswordForm.getConfirmPassword()))
			{
				errors.rejectValue("confirmPassword",
						"passwordupdate.mismatch");
				return;
			}
		}
			
		else
		{
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
					"passwordupdate.confirmPassword.required");
			
		
			
		}
		
		
	}

	public boolean validatePattern(final String input, String pattern) {
		return Pattern.compile(pattern).matcher(input).matches();

	}
		
	

}
