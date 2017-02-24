package com.sot.ecommerce.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.category.commons.AttributeVO;
import com.sbsc.fos.category.commons.CategoryVO;
import com.sbsc.fos.category.commons.SectionVO;
import com.sbsc.fos.category.service.AttributeService;
import com.sbsc.fos.category.service.CategoryService;
import com.sbsc.fos.category.service.SectionService;
import com.sbsc.fos.category.web.form.AddAttributeForm;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.CategoryTbMaster;
import com.sbsc.fos.persistance.CtgrySctnAttrTbDtl;
import com.sbsc.fos.persistance.CtgrySctnTbDtl;
import com.sbsc.fos.utils.DateUtil;

@Component
public class AttributeHandler {

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private SectionService sectionService;

	@Autowired
	private CategoryService categoryService;

	public CtgrySctnAttrTbDtl getAttribute(long attributeId)
			throws BusinessFailureException, GenericFailureException {
		return attributeService.findByID(attributeId);
	}

	/**
	 * Provides information about all the Attributes of the specified Section.
	 * 
	 * @param sectionId
	 *            Identifier of the Section.
	 * 
	 * @return Specific information about all the Attributes
	 */
	public List<AttributeVO> getAllAttributes(Long sectionId)
			throws BusinessFailureException, GenericFailureException {

		HashMap<String, Object> properties = new HashMap<String, Object>();

		List<AttributeVO> attributeVOList = new ArrayList<AttributeVO>();

		properties.put("ctgrySctnTbDtl", getSection(sectionId));

		properties.put("isDltd", new BigDecimal(0));

		List<CtgrySctnAttrTbDtl> allAttributesList = attributeService
				.findAllByProperty(properties);

		if (allAttributesList != null && !allAttributesList.isEmpty()) {

			for (CtgrySctnAttrTbDtl ctgrySctnAttrTbDtl : allAttributesList) {
				attributeVOList.add(new AttributeVO(ctgrySctnAttrTbDtl
						.getNmAttrId(), ctgrySctnAttrTbDtl.getVcAttrNm()));
			}
		}

		return attributeVOList;

	}

	/**
	 * Provides all the Categories for a specific Store to which the user is
	 * signed in.
	 * 
	 * @param sessionInfo
	 *            Session information of the user signed-in.
	 * 
	 * @return List of CategoryVO containing the Category information (Id, Name,
	 *         Parent Category, Status).
	 */
	public List<CategoryVO> getAllCategories(SessionInfo sessionInfo) {

		return categoryService.getAllCategories(sessionInfo.getStoreId());
	}

	/**
	 * Provides information about the all the Sections of the specified
	 * Category.
	 * 
	 * @param categoryId
	 *            Identifier of the Category.
	 * 
	 * @return List of Sections of the Category
	 */
	public List<SectionVO> getAllSection(long categoryId)
			throws BusinessFailureException, GenericFailureException {

		HashMap<String, Object> properties = new HashMap<String, Object>();

		List<SectionVO> sectionVoList = new ArrayList<SectionVO>();

		properties.put("categoryTbMaster", getCategory(categoryId));

		properties.put("isDltd", new BigDecimal(0));

		List<CtgrySctnTbDtl> allSectionsList = sectionService
				.findAllByProperty(properties);

		if (allSectionsList != null && !allSectionsList.isEmpty()) {

			for (CtgrySctnTbDtl ctgrySctnTbDtl : allSectionsList) {

				sectionVoList.add(new SectionVO(ctgrySctnTbDtl.getNmSctnId(),
						ctgrySctnTbDtl.getVcSctnNm()));
			}

		}

		return sectionVoList;
	}

	/**
	 * Soft-deletes the specified Section and it's child elements from the
	 * system if no Product exists for the Category to which this section
	 * belongs.
	 * 
	 * @param sectionId
	 *            Identifier of the Section.
	 * 
	 * @throws BusinessFailureException
	 *             If no Product exists for the Category to which this section
	 *             belongs
	 */
	public void deleteAttribute(Long attributeId)
			throws BusinessFailureException, GenericFailureException {
		attributeService.deleteById(attributeId);
	}

	/**
	 * Either save a new CtgrySctnAttrTbDtl (Attribute) or update an existing
	 * CtgrySctnAttrTbDtl (Attribute) instance, depending upon resolution of the
	 * unsaved-value checks.
	 * 
	 * @param addAttributeForm
	 *            Information about the Attribute provided by the user.
	 * 
	 * @param sessionInfo
	 *            Session information of the user signed-in.
	 */
	public void saveOrUpdateAttribute(AddAttributeForm addAttributeForm,
			SessionInfo sessionInfo) {

		CtgrySctnAttrTbDtl attribute = null;

		if (addAttributeForm.getAttributeId() != null) {
			attribute = attributeService.findByID(addAttributeForm
					.getAttributeId());

			attribute.setNmUpdtdBy(new BigDecimal(sessionInfo.getUserId()));

			attribute.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		} else {
			attribute = new CtgrySctnAttrTbDtl();

			attribute.setDtCrtdAt(DateUtil.getCurrentDateTime());

			attribute.setNmCrtdBy(new BigDecimal(sessionInfo.getUserId()));

			attribute.setNmUpdtdBy(new BigDecimal(sessionInfo.getUserId()));

			attribute.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		}

		attribute.setVcAttrNm(addAttributeForm.getAttributeName().trim());

		attribute.setCategoryTbMaster(getCategory(addAttributeForm
				.getCategoryId()));

		attribute.setIsDltd(new BigDecimal(0));

		attribute
				.setIsSrchbl(addAttributeForm.getIsSearchable() ? new BigDecimal(
						1) : new BigDecimal(0));

		attribute
				.setIsMndtry(addAttributeForm.getIsCompulsory() ? new BigDecimal(
						1) : new BigDecimal(0));

		attribute.setIsImgbl(addAttributeForm.getIsImagable() ? new BigDecimal(
				1) : new BigDecimal(0));

		attribute
				.setCtgrySctnTbDtl(getSection(addAttributeForm.getSectionId()));

		if (addAttributeForm.getIsVariant()) {

			if (addAttributeForm.getIsNumeric()) {

				if ((attribute.getIsRngSpprt() == null && attribute.getIsVrnt() == null)
						|| !(attribute.getIsRngSpprt()
								.equals(new BigDecimal(1)) && attribute
								.getIsVrnt().equals(new BigDecimal(1)))) {

					attribute
							.setVcAttrMppng(attributeService
									.getNextAvailableVariantNumericAttributeMapping(addAttributeForm
											.getCategoryId()));
					attribute
							.setVcUntTypMppng(attributeService
									.getNextAvailableNumericUnitVariantAttributeMapping(addAttributeForm
											.getCategoryId()));
				}

				attribute.setVcUntTyp(addAttributeForm.getUnitType());

				attribute.setIsRngSpprt(new BigDecimal(1));

			} else {

				if (attribute.getIsVrnt() == null
						|| attribute.getIsRngSpprt().equals(new BigDecimal(1))
						|| attribute.getIsVrnt().equals(new BigDecimal(0))) {

					attribute
							.setVcAttrMppng(attributeService
									.getNextAvailableVariantAttributeMapping(addAttributeForm
											.getCategoryId()));
				}

				attribute.setVcUntTyp(null);

				attribute.setVcUntTypMppng(null);

				attribute.setIsRngSpprt(new BigDecimal(0));
			}

			attribute.setIsVrnt(new BigDecimal(1));

			attribute.setIsMndtry(new BigDecimal(1));

		} else {

			if (addAttributeForm.getIsNumeric()) {

				if (attribute.getIsRngSpprt() == null
						|| attribute.getIsVrnt().equals(new BigDecimal(1))
						|| attribute.getIsRngSpprt().equals(new BigDecimal(0))) {

					attribute
							.setVcAttrMppng(attributeService
									.getNextAvailableNumericAttributeMapping(addAttributeForm
											.getCategoryId()));

					attribute
							.setVcUntTypMppng(attributeService
									.getNextAvailableNumericUnitAttributeMapping(addAttributeForm
											.getCategoryId()));
				}

				attribute.setVcUntTyp(addAttributeForm.getUnitType());

				attribute.setIsRngSpprt(new BigDecimal(1));

			} else {

				if (StringUtils.isBlank(attribute.getVcAttrMppng())
						|| (attribute.getIsRngSpprt().equals(new BigDecimal(1)) || attribute
								.getIsVrnt().equals(new BigDecimal(1)))) {

					attribute.setVcAttrMppng(attributeService
							.getNextAvailableAttributeMapping(addAttributeForm
									.getCategoryId()));
				}

				attribute.setVcUntTyp(null);

				attribute.setVcUntTypMppng(null);

				attribute.setIsRngSpprt(new BigDecimal(0));
			}

			attribute.setIsVrnt(new BigDecimal(0));
		}

		if (StringUtils.isNotBlank(addAttributeForm.getCustomUnitType())
				&& StringUtils.isNotEmpty(addAttributeForm.getCustomUnitType())) {

			attribute.setVcUntTyp(addAttributeForm.getUnitType().concat(",")
					.concat(addAttributeForm.getCustomUnitType()));
		}

		attributeService.saveOrUpdate(attribute);

	}

	/**
	 * Provides information about the specified Section.
	 * 
	 * @param sectionId
	 *            Identifier of the Section provided.
	 * 
	 * @return The CtgrySctnTbDtl
	 */
	public CtgrySctnTbDtl getSection(long sectionId) {
		return sectionService.findByID(sectionId);
	}

	/**
	 * Reads information about the specified Category from the System.
	 * 
	 * @param categoryId
	 *            Identifier of the Category whose information needed.
	 * 
	 * @return The CategoryTbMaster
	 */
	public CategoryTbMaster getCategory(long categoryId) {
		return categoryService.findByID(categoryId);
	}

}
