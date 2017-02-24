package com.sot.ecommerce.form.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sbsc.fos.feedback.web.form.FeedbackForm;

/**
 * 
 * @author gaurav.kumar
 * 
 */

@Component
public class FeedbackFormValidator implements Validator {


	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object command, Errors errors) {

		FeedbackForm feedbackForm = (FeedbackForm) command;

		if (
				!StringUtils.isNotBlank(feedbackForm.getProductReview()) &&
		(	(feedbackForm.getProductRating().equals("0") || (feedbackForm.getProductRating().equals("")) )  ) 
			
				) {
			errors.rejectValue("productRating",
					"feedback.reviewRating.empty");	
		}
	}

	
}
