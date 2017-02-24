/**
 * 
 */
package com.sot.ecommerce.form.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sbsc.fos.product.web.form.ProductVariantForm;
import com.sbsc.fos.utils.SBSConstants;
import com.sbsc.fos.utils.SBSConstants.PRODUCT_STATUS;

/**
 * @author diksha.rattan
 * 
 */
@Component
public class ProductVariantValidator implements Validator, SBSConstants {

	private static final String VARIANT_NAME_PATTERN = "^[a-zA-Z0-9 -]*$";// "^[A-Za-z0-9._()%+-]*$";

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductVariantValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {

		ProductVariantForm productVariantForm = (ProductVariantForm) command;

		if (StringUtils.isNotBlank(productVariantForm.getVcVrntNm())) {

			
			if (StringUtils.equalsIgnoreCase(
					productVariantForm.getErrorMessage(), EXISTS)) {

				errors.rejectValue("vcVrntNm",
						"ProductVariantForm.variant.exists");

			} 
			else if (!this.validatePattern(productVariantForm.getVcVrntNm(),
					VARIANT_NAME_PATTERN)) {
				errors.rejectValue(
						"vcVrntNm",
						"ProductVariantForm.variant.format",
						"Variant Name should contain only alphanumeric characters.");

			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vcVrntNm",
					"ProductVariantForm.blank");
		}
		
		
		if (StringUtils.isNotBlank(productVariantForm.getNmDlPrc().toString())) {

			if (!StringUtils.isNumeric(productVariantForm.getNmDlPrc().toString())) {

				errors.rejectValue("nmDlPrc",
						"ProductBasicForm.marketPrice.numeric",
						"Deal Price should be numeric");

			}
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nmDlPrc",
					"ProductBasicForm.marketPrice.blank");
		}

		if (StringUtils.isNotBlank(productVariantForm.getNmSp())) {

			if (!StringUtils.isNumeric(productVariantForm.getNmSp())) {

				errors.rejectValue("nmSp",
						"ProductBasicForm.salePrice.numeric",
						"Maximum Sell Price should be numeric");

			} else {

				if (StringUtils.isNumeric(productVariantForm.getNmDlPrc())) {
					long salePrice = Long.parseLong(productVariantForm
							.getNmSp().toString());
					long dealPrice = productVariantForm.getNmDlPrc() == null ? 0
							: Long.parseLong(productVariantForm.getNmDlPrc());

					if (dealPrice > salePrice) {

						errors.rejectValue("nmDlPrc",
								"ProductBasicForm.productDesc.MarketPrice",
								"Deal Price should not be greater than Maximum Selling Price.");

					}
				}
			}
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nmSp",
					"ProductBasicForm.salePrice.blank");
		}
		
		
		if (StringUtils.isNotBlank(productVariantForm.getNmQntty())) {

			if (!StringUtils.isNumeric(productVariantForm.getNmQntty())) {

				errors.rejectValue("nmQntty",
						"ProductBasicForm.quantity.numeric",
						"Quantity can only be numeric");

			} 
		}else 
		
			{
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nmQntty",
					"ProductBasicForm.Quantity.blank");
			
		}
		
		
		if (StringUtils.isBlank(productVariantForm.getVcStts())) {

			errors.rejectValue("vcStts", "ProductBasicForm.status.valid",
					"Select status");
		}
		
		
		/*if(StringUtils.equalsIgnoreCase(productVariantForm.getVcStts() , PRODUCT_STATUS.Active.name()) && productVariantForm.getIsAllImageDeleted()){
			
			errors.rejectValue("vcStts",
					"ProductVariantForm.product.noimageadded",
					"Variant Product can only be activated after adding at least a image");
		}*/


	}

	public boolean validatePattern(final String input, String pattern) {
		return Pattern.compile(pattern).matcher(input).matches();

	}

}
