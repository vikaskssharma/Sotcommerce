/**
 * 
 */
package com.sot.ecommerce.validator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sbsc.fos.utils.SBSConstants;
import com.sbsc.fos.web.form.FormSearch;
import com.sbsc.fos.web.form.Filter;

/**
 * @author simanchal.patra
 * 
 */
@Component
public class FormSearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FormSearchValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {

		FormSearch formSearch = (FormSearch) command;
		
		SimpleDateFormat view_date_format = new SimpleDateFormat(
				SBSConstants.DATE_MM_DD_YYYY);

		for (Filter filter : formSearch.getFilters()) {

			if (StringUtils.isNotBlank(filter.getValue())) {

				String[] dat = filter.getName().split("\\.");

				String type = dat[dat.length - 1];

				System.out.println("type : " + type);

				switch (type) {
				case "BigDecimal":
					try {
						BigDecimal.valueOf(Double.valueOf(
								filter.getValue()).doubleValue());
					} catch (Exception e) {
						errors.rejectValue("filters",
								"search.form.not.number",
								"Values provided in text field \""
										+ filter.getLabel()
										+ "\" should be Numbers only");
					}
					break;
				case "Long":
					if (!StringUtils.isNumeric(filter.getValue())) {
						errors.rejectValue("filters",
								"search.form.not.number",
								"Values provided in text field \""
										+ filter.getLabel()
										+ "\" should be Numbers only");
					}
					break;
				case "Date":
					SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
					try {
						format.setLenient(false);
						format.parse(filter.getValue());
					} catch (ParseException e) {
						errors.rejectValue("filters",
								"search.form.date.type.not.correct",
								"Values provided in date field \""
										+ filter.getLabel()
										+ "\" should be in format MM-DD-YYYY");
					}
					break;
				case "Calendar":

					try {

						view_date_format.parse(filter.getValue());

					} catch (ParseException e) {

						errors.rejectValue(
								"filters",
								"search.form.date.type.not.correct",
								"Values provided in date field \""
										+ filter.getLabel()
										+ "\" should be in format MM-DD-YYYY");

					}

					break;
					
				default:
					break;
				}

			}

		}

	}
}
