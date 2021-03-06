/**
 * 
 */
package com.sot.ecommerce.form.validator;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sbsc.fos.category.service.SectionService;
import com.sbsc.fos.category.web.form.AddSectionForm;

/**
 * @author simanchal.patra
 * 
 */
@Component
public class AddSectionValidator implements Validator {

	private static final int SECTION_NAME_MAX_LENGTH = 32;

	@Autowired
	private SectionService sectionService;

	@Override
	public boolean supports(Class<?> clazz) {
		return AddSectionValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {

		AddSectionForm addSectionForm = (AddSectionForm) command;

		if (StringUtils.isNotBlank(addSectionForm.getSectionName())) {

			if (addSectionForm.getSectionName().length() > SECTION_NAME_MAX_LENGTH) {
				errors.rejectValue("sectionName", "add.section.name.max.length");
			}

			if (!StringUtils.isAlphanumericSpace(addSectionForm
					.getSectionName())
					&& !StringUtils.isAlphanumeric(addSectionForm
							.getSectionName().toLowerCase())) {
				errors.rejectValue("sectionName",
						"add.section.name.alpha.numeric");
			}

			HashMap<String, Long> nameIdMap = sectionService
					.findAllNameId(addSectionForm.getCategoryId());

			if (addSectionForm.getSectionId() == null) {

				if (nameIdMap.get(addSectionForm.getSectionName().trim()
						.toLowerCase()) != null) {
					errors.rejectValue("sectionName",
							"add.section.name.already.exists.for.category");
				}
			} else if (nameIdMap.get(addSectionForm.getSectionName().trim()
					.toLowerCase()) != null) {

				if (((Long) nameIdMap.get(addSectionForm.getSectionName()
						.trim().toLowerCase())).longValue() != addSectionForm
						.getSectionId()) {
					errors.rejectValue("sectionName",
							"add.section.name.already.exists.for.category");
				}
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sectionName",
					"add.section.name.blank");
		}
	}
}
