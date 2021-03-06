/**
 * 
 */
package com.sot.ecommerce.form.validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sbsc.fos.category.service.AttributeService;
import com.sbsc.fos.category.service.CategoryService;
import com.sbsc.fos.category.web.form.AddAttributeForm;
import com.sbsc.fos.persistance.CtgrySctnAttrTbDtl;

/**
 * @author simanchal.patra
 * 
 */
@Component
public class AddAttributeValidator implements Validator {

	private static final int ATTRIBUTE_NAME_MAX_LENGTH = 32;

	private static final int CUSTOM_NUMERIC_DATA_TYPE_MAX_LENGTH = 16;

	private static final String REGEX = "[A-Za-z0-9\\.\\s]*";

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private CategoryService categoryService;

	@Override
	public boolean supports(Class<?> clazz) {
		return AddAttributeValidator.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {

		AddAttributeForm addAttributeForm = (AddAttributeForm) command;

		if (StringUtils.isNotBlank(addAttributeForm.getAttributeName())) {

			if (addAttributeForm.getAttributeName().length() > ATTRIBUTE_NAME_MAX_LENGTH) {
				errors.rejectValue("attributeName",
						"add.attribute.name.max.length");
			}

			if (!StringUtils.isAlphanumericSpace(addAttributeForm
					.getAttributeName())
					&& !StringUtils.isAlphanumeric(addAttributeForm
							.getAttributeName())) {
				errors.rejectValue("attributeName",
						"add.attribute.name.alpha.numeric");
			}

			HashMap<String, Long> nameIdMap = attributeService
					.findAllNameId(addAttributeForm.getCategoryId());

			if (addAttributeForm.getAttributeId() == null) {
				// Add

				if (nameIdMap.get(addAttributeForm.getAttributeName().trim()
						.toLowerCase()) != null) {
					errors.rejectValue("attributeName",
							"add.attribute.name.already.exists.for.category");
				}

			} else if (nameIdMap.get(addAttributeForm.getAttributeName().trim()
					.toLowerCase()) != null) {

				// Edit

				if (((Long) nameIdMap.get(addAttributeForm.getAttributeName()
						.trim().toLowerCase())).longValue() != addAttributeForm
						.getAttributeId()) {
					errors.rejectValue("attributeName",
							"add.attribute.name.already.exists.for.category");
				}
			}

		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "attributeName",
					"add.attribute.name.blank");
		}

		if (addAttributeForm.getIsCompulsory()
				&& !addAttributeForm.getIsVariant()) {

			if (addAttributeForm.getAttributeId() == null) {

				if (categoryService.getProductCount(addAttributeForm
						.getCategoryId()) > 0) {

					errors.rejectValue("isCompulsory",
							"add.compulsory.attribute.not.permitted.if.product.exists");
				}

			} else {

				HashMap<String, Object> criteria = new HashMap<>();
				criteria.put("isDltd", new BigDecimal(0));
				criteria.put("isMndtry", new BigDecimal(1));
				criteria.put("nmAttrId", addAttributeForm.getAttributeId());

				List<CtgrySctnAttrTbDtl> attributeList = attributeService
						.findAllByProperty(criteria);

				if (attributeList.size() == 0) {

					if (categoryService.getProductCount(addAttributeForm
							.getCategoryId()) > 0) {

						errors.rejectValue("isCompulsory",
								"add.compulsory.attribute.not.permitted.if.product.exists");
					}

				}
			}
		}

		if (addAttributeForm.getIsNumeric()) {

			if (StringUtils.isNotBlank(addAttributeForm.getUnitType())
					&& !addAttributeForm.getUnitType().contains("NONE")) {

				int numericCount = attributeService
						.getNumericAttributeMappingCount(addAttributeForm
								.getCategoryId());

				if (numericCount == 5) {

					if (addAttributeForm.getAttributeId() == null) {

						// Add mode

						errors.rejectValue("isNumeric",
								"add.numeric.attribute.exceeding.max.limit");
					} else {

						// Edit mode
						// Checks if editing a existing Numeric or not

						HashMap<String, Object> criteria = new HashMap<>();
						criteria.put("isDltd", new BigDecimal(0));
						criteria.put("isNmrc", new BigDecimal(1));
						criteria.put("nmAttrId",
								addAttributeForm.getAttributeId());

						List<CtgrySctnAttrTbDtl> attributeList = attributeService
								.findAllByProperty(criteria);

						if (attributeList.size() == 0) {

							errors.rejectValue("isNumeric",
									"add.numeric.attribute.exceeding.max.limit");
						}

					}
				}

				if (categoryService.getProductCount(addAttributeForm
						.getCategoryId()) > 0) {

					if (addAttributeForm.getAttributeId() == null) {

						// errors.rejectValue(
						// "isNumeric",
						// "add.numeric.attribute.not.permitted.if.product.exists",
						// "No more Addition of Numeric attribute permitted as "
						// + "Product exists for this Category");
					} else {

						HashMap<String, Object> criteria = new HashMap<>();
						criteria.put("isDltd", new BigDecimal(0));
						criteria.put("isNmrc", new BigDecimal(1));
						criteria.put("nmAttrId",
								addAttributeForm.getAttributeId());

						List<CtgrySctnAttrTbDtl> attributeList = attributeService
								.findAllByProperty(criteria);

						if (attributeList.size() > 0) {

							if (!(attributeList.get(0).getVcUntTyp()
									.equals(addAttributeForm.getUnitType()))) {

								errors.rejectValue("isNumeric",
										"edit.attribute.numeric.unit.type.change.not.permitted.if.product.exists");

							}
						} else {

							criteria = new HashMap<>();
							criteria.put("isDltd", new BigDecimal(0));
							criteria.put("isNmrc", new BigDecimal(0));
							criteria.put("nmAttrId",
									addAttributeForm.getAttributeId());

							attributeList = attributeService
									.findAllByProperty(criteria);

							if (attributeList.size() > 0) {

								errors.rejectValue("isNumeric",
										"edit.attribute.change.normal.to.numeric.not.permitted.if.product.exists");
							}
						}

					}
				}

				if (addAttributeForm.getUnitType().contains("Custom")) {

					if (StringUtils.isBlank(addAttributeForm
							.getCustomUnitType())) {

						errors.rejectValue("unitType",
								"add.attribute.numeric.custom.data.type.blank");
					} else {

						if (addAttributeForm.getCustomUnitType().trim()
								.length() > 16) {

							errors.rejectValue("unitType",
									"add.attribute.numeric.custom.data.type.max.length");
						} else {
							if (!this.validateCustomUnitType(addAttributeForm
									.getCustomUnitType())) {
								errors.rejectValue("unitType",
										"add.attribute.numeric.custom.data.type.invalid.character");
							}
						}
					}
				} else {

				}

			} else {
				errors.rejectValue("unitType",
						"add.attribute.numeric.unit.type.blank");
			}
		} else {

			if (!addAttributeForm.getUnitType().contains("NONE")) {

				errors.rejectValue("unitType",
						"add.attribute.numeric.unit.type.only.allowed.if.numeric.type");
			}

			if (addAttributeForm.getAttributeId() != null) {

				HashMap<String, Object> criteria = new HashMap<>();
				criteria.put("isDltd", new BigDecimal(0));
				criteria.put("isNmrc", new BigDecimal(1));
				criteria.put("nmAttrId", addAttributeForm.getAttributeId());

				List<CtgrySctnAttrTbDtl> attributeList = attributeService
						.findAllByProperty(criteria);

				if (attributeList.size() > 0) {
					if (attributeList.get(0).getIsRngSpprt()
							.equals(new BigDecimal(1))
							&& !addAttributeForm.getIsNumeric()) {

						if (categoryService.getProductCount(addAttributeForm
								.getCategoryId()) > 0) {

							errors.rejectValue("isNumeric",
									"edit.attribute.remove.numeric.not.permitted.if.product.exists");
						}

					}
				}
			}

		}

		if (addAttributeForm.getIsVariant()) {

			if (!addAttributeForm.getIsCompulsory()) {
				errors.rejectValue("isCompulsory",
						"add.attribute.compulsory.if.variant");
			}

			if (addAttributeForm.getAttributeId() == null) {

				// Add mode

				if (categoryService.getProductCount(addAttributeForm
						.getCategoryId()) > 0) {

					errors.rejectValue("isVariant",
							"add.variant.attribute.not.permitted.if.product.exists");
				} else {

					int variantCount = attributeService
							.getVariantAttributeMappingCount(addAttributeForm
									.getCategoryId());

					if (variantCount == 5) {

						errors.rejectValue("isVariant",
								"add.variant.attribute.exceeding.max.limit");
					}
				}

			} else {

				// Edit mode
				// Checks if editing a existing variant or not

				HashMap<String, Object> criteria = new HashMap<>();
				criteria.put("isDltd", new BigDecimal(0));
				criteria.put("isVrnt", new BigDecimal(1));
				criteria.put("nmAttrId", addAttributeForm.getAttributeId());

				List<CtgrySctnAttrTbDtl> attributeList = attributeService
						.findAllByProperty(criteria);

				if (attributeList.size() == 0) {

					if (categoryService.getProductCount(addAttributeForm
							.getCategoryId()) > 0) {

						errors.rejectValue("isVariant",
								"add.variant.attribute.not.permitted.if.product.exists");
					} else {

						int variantCount = attributeService
								.getVariantAttributeMappingCount(addAttributeForm
										.getCategoryId());

						if (variantCount == 5) {

							errors.rejectValue("isVariant",
									"add.variant.attribute.exceeding.max.limit");
						}
					}
				}

			}

		} else {

			int attributeCount = attributeService
					.getAttributeMappingCount(addAttributeForm.getCategoryId());

			if (addAttributeForm.getAttributeId() != null) {

				HashMap<String, Object> criteria = new HashMap<>();
				criteria.put("isDltd", new BigDecimal(0));
				criteria.put("isVrnt", new BigDecimal(1));
				criteria.put("nmAttrId", addAttributeForm.getAttributeId());

				List<CtgrySctnAttrTbDtl> attributeList = attributeService
						.findAllByProperty(criteria);

				if (attributeList.size() > 0) {

					if (categoryService.getProductCount(addAttributeForm
							.getCategoryId()) > 0) {

						errors.rejectValue("isVariant",
								"edit.attribute.removal.variant.not.permitted.if.product.exists");
					}
				}
			}

			if (attributeCount == 25) {

				if (addAttributeForm.getAttributeId() == null) {

					// Add mode

					errors.rejectValue("attributeName",
							"add.attribute.exceeding.max.limit");
				} else {

					// Edit mode
					// Checks if editing a existing variant or not

					HashMap<String, Object> criteria = new HashMap<>();
					criteria.put("isDltd", new BigDecimal(0));
					criteria.put("isVrnt", new BigDecimal(0));
					criteria.put("isNmrc", new BigDecimal(0));
					criteria.put("nmAttrId", addAttributeForm.getAttributeId());

					List<CtgrySctnAttrTbDtl> attributeList = attributeService
							.findAllByProperty(criteria);

					if (attributeList.size() == 0) {

						errors.rejectValue("attributeName",
								"add.attribute.exceeding.max.limit");
					}

				}

			}

		}

		if (addAttributeForm.getIsImagable()) {

			if (!addAttributeForm.getIsVariant()) {

				errors.rejectValue("isImagable",
						"add.imagable.attribute.not.permitted.if.not.variant.product");
			} else {

				HashMap<String, Object> criteria = new HashMap<>();
				criteria.put("isDltd", new BigDecimal(0));
				criteria.put("isImgbl", new BigDecimal(1));
				criteria.put("categoryTbMaster", categoryService
						.findByID(addAttributeForm.getCategoryId()));

				List<CtgrySctnAttrTbDtl> attributeList = attributeService
						.findAllByProperty(criteria);

				if (addAttributeForm.getAttributeId() == null
						&& (attributeList.size() + 1) > 1) {

					errors.rejectValue("isImagable",
							"add.imagable.attribute.exceeding.max.limit");

				} else if (addAttributeForm.getAttributeId() != null
						&& attributeList.size() > 0
						&& (attributeList.get(0).getNmAttrId() != addAttributeForm
								.getAttributeId().longValue())) {

					errors.rejectValue("isImagable",
							"add.imagable.attribute.exceeding.max.limit");

				} else {

				}
			}
		}

	}

	public boolean validateCustomUnitType(final String customUnitType) {
		return Pattern.compile(REGEX).matcher(customUnitType).matches();

	}
}
