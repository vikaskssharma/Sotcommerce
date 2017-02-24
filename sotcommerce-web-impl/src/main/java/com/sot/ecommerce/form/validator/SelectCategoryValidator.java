/**
 * 
 */
package com.sot.ecommerce.form.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sbsc.fos.product.web.form.ProductBasicForm;

/**
 * @author diksha.rattan
 *
 */
@Component
public class SelectCategoryValidator implements Validator{
	
	
	@Override 
	public boolean supports(Class<?> clazz) {
		return ProductValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {

		ProductBasicForm productBasicForm = (ProductBasicForm) command;

		
		if (StringUtils.isBlank(productBasicForm.getCategoryId())) {
			
			errors.rejectValue("categoryId",
					"ProductBasicForm.category.blank",
					"Please Select Category");
		}

	}


}
