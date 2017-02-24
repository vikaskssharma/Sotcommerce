/**
 * (c) R Systems International Ltd.
 *  
 */
package com.sot.ecommerce.handler;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.category.service.CategoryService;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.CategoryTbMaster;
import com.sbsc.fos.persistance.CtgrySctnAttrTbDtl;
import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.persistance.ProductVariantTbDtl;
import com.sbsc.fos.persistance.RelatedProductTbDtl;
import com.sbsc.fos.persistance.SimilarProductTbDtl;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sbsc.fos.product.common.CategorySectionAttributeVo;
import com.sbsc.fos.product.service.ProductService;
import com.sbsc.fos.product.service.ProductVariantService;
import com.sbsc.fos.product.web.form.ProductBasicForm;
import com.sbsc.fos.product.web.form.ProductVariantForm;
import com.sbsc.fos.product.web.form.RelatedProductForm;
import com.sbsc.fos.product.web.form.SimilarProductForm;
import com.sbsc.fos.utils.DateUtil;
import com.sbsc.fos.utils.SBSConstants;

@Component
public class ProductHandler implements SBSConstants {

	/** Logger instance. **/
	private static final Logger logger = Logger.getLogger(ProductHandler.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductVariantService productVariantService;

	public List<CategoryTbMaster> viewAllCategory()
			throws BusinessFailureException, GenericFailureException {

		List<CategoryTbMaster> list = new ArrayList<CategoryTbMaster>();

		list = categoryService.findAll();

		return list;

	}

	public List<ProductBasicForm> findAllProductsByFilters(
			Map<String, Object> filter_criteria)
			throws BusinessFailureException, GenericFailureException {
		List<ProductBasicForm> detailList = productService
				.findAllProductsByFilters(filter_criteria);
		return detailList;
	}

	/**
	 * Provides all the Attributes category wise and returns list of
	 * CategorySectionAttributeVo.
	 * 
	 * @param propertyName
	 * @param value
	 * @param variantValue
	 * @return list of CategorySectionAttributeVo
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public List<CategorySectionAttributeVo> viewAllAttributeByCategoryId(
			String propertyName, Object value, String variantValue)
			throws BusinessFailureException, GenericFailureException {

		HashMap<String, Object> properties = new HashMap<String, Object>();

		properties.put("categoryTbMaster", value);

		properties.put("isVrnt", new BigDecimal(variantValue));

		properties.put("isDltd", new BigDecimal(0));

		List<CtgrySctnAttrTbDtl> list = productService
				.findAllAttributeByCategory(properties);

		List<CategorySectionAttributeVo> catSectionAttlist = new ArrayList<CategorySectionAttributeVo>();

		for (CtgrySctnAttrTbDtl catSectionAtt : list) {
			CategorySectionAttributeVo attribute = new CategorySectionAttributeVo();
			attribute.setAttributeId(catSectionAtt.getNmAttrId());
			attribute.setCategoryId(catSectionAtt.getCategoryTbMaster()
					.getNmCtgryId());
			attribute.setSectionId(catSectionAtt.getCtgrySctnTbDtl()
					.getNmSctnId());
			attribute.setSectionName(catSectionAtt.getCtgrySctnTbDtl()
					.getVcSctnNm());
			attribute.setAttributeName(catSectionAtt.getVcAttrNm());
			attribute.setAttributeMapping(catSectionAtt.getVcAttrMppng());
			attribute.setIsVariant(catSectionAtt.getIsVrnt().longValue());
			attribute.setIsMandatory(catSectionAtt.getIsMndtry().longValue());
			attribute.setIsSearchable(catSectionAtt.getIsSrchbl().longValue());
			attribute.setIsDeleted(catSectionAtt.getIsDltd().longValue());
			attribute.setIsNumeric(catSectionAtt.getIsRngSpprt().longValue());
			attribute.setIsImagable(catSectionAtt.getIsImgbl().longValue());
			attribute.setUnitType(catSectionAtt.getVcUntTyp());
			attribute.setUntTypeMppng(catSectionAtt.getVcUntTypMppng());
			catSectionAttlist.add(attribute);

		}

		return catSectionAttlist;

	}

	/**
	 * Handles delete action on product and do soft delete.
	 * 
	 * @param productTbMaster
	 * @return updated ProductTbMaster object
	 */
	public ProductTbMaster deleteProduct(ProductTbMaster productTbMaster)
			throws BusinessFailureException, GenericFailureException {

		productService.delete(productTbMaster);
		productTbMaster = productService.update(productTbMaster);
		return productTbMaster;

	}

	/**
	 * save or update basic product details.
	 * 
	 * @param productBasicForm
	 * @param product
	 * @return updated ProductTbMaster object
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public ProductTbMaster registerProduct(ProductBasicForm productBasicForm,
			ProductTbMaster product) throws BusinessFailureException,
			GenericFailureException, ParseException {

		product.setVcPrdNm(productBasicForm.getProductName());
		product.setVcPrdDsc(productBasicForm.getProductDesc());
		product.setVcStts(productBasicForm.getStatus());
		product.setNmQnty(StringUtils.isBlank(productBasicForm.getQuantity()) ? new BigDecimal(
				"0") : new BigDecimal(productBasicForm.getQuantity()));
		product.setNmDlPrc(StringUtils.isBlank(productBasicForm.getDealPrice()) ? null
				: new BigDecimal(productBasicForm.getDealPrice()));
		product.setNmDlPrc(StringUtils.isBlank(productBasicForm.getDealPrice()) ? new BigDecimal(
				"-1") : new BigDecimal(productBasicForm.getDealPrice()));
		product.setNmSp(StringUtils.isBlank(productBasicForm.getSalePrice()) ? new BigDecimal(
				"0") : new BigDecimal(productBasicForm.getSalePrice()));
		product.setIsCod(productBasicForm.getIsCod() ? new BigDecimal("1")
				: new BigDecimal("0"));
		product.setIsFtrd(productBasicForm.getIsFtrd() ? new BigDecimal("1")
				: new BigDecimal("0"));

		product.setIsFrShpng(productBasicForm.getFreeShiping() ? new BigDecimal(
				"1") : new BigDecimal("0"));
		product.setIsVrnt(productBasicForm.getIsVariant() ? new BigDecimal("1")
				: new BigDecimal("0"));
		product.setIsHtdl(new BigDecimal(0));
		product.setIsFtrd(productBasicForm.getIsFtrd() ? new BigDecimal("1")
				: new BigDecimal("0"));
		if (StringUtils.equalsIgnoreCase(productBasicForm.getMode(), "Edit")) {
			product.setNmUpdtdBy(new BigDecimal(productBasicForm.getUpdatedBy()));
			product.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		} else {
			product.setDtCrtdAt(DateUtil.getCurrentDateTime());
			product.setNmCrtdBy(new BigDecimal(productBasicForm.getCreatedBy()));
			product.setNmUpdtdBy(new BigDecimal(productBasicForm.getUpdatedBy()));
			product.setDtUpdtdAt(DateUtil.getCurrentDateTime());
			product.setVcCmpltnStts("D");
			StoreTbMaster strTb = new StoreTbMaster();
			strTb.setNmStrId(productBasicForm.getStoreId());
			product.setStoreTbMaster(strTb);

		}
		product.setIsDltd(new BigDecimal(0));
		CategoryTbMaster category = new CategoryTbMaster();
		category.setNmCtgryId(Long.parseLong(productBasicForm.getCategoryId()));
		product.setCategoryTbMaster(category);
		productService.saveOrUpdate(product);

		return product;

	}

	/**
	 * save product attribute and image details.
	 * 
	 * @param product
	 * @param productBasicForm
	 * @return updated ProductTbMaster object
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */

	public ProductTbMaster registerProductAttributes(ProductTbMaster product,
			ProductBasicForm productBasicForm) throws BusinessFailureException,
			GenericFailureException, ParseException {

		product.setVcCmpltnStts(productBasicForm.getIsCompletionStatus());
		product.setVcAttr1Value(productBasicForm.getAttribute1Val());
		product.setVcAttr2Value(productBasicForm.getAttribute2Val());
		product.setVcAttr3Value(productBasicForm.getAttribute3Val());
		product.setVcAttr4Value(productBasicForm.getAttribute4Val());
		product.setVcAttr5Value(productBasicForm.getAttribute5Val());
		product.setVcAttr6Value(productBasicForm.getAttribute6Val());
		product.setVcAttr7Value(productBasicForm.getAttribute7Val());
		product.setVcAttr8Value(productBasicForm.getAttribute8Val());
		product.setVcAttr9Value(productBasicForm.getAttribute9Val());
		product.setVcAttr10Value(productBasicForm.getAttribute10Val());
		product.setVcAttr11Value(productBasicForm.getAttribute11Val());
		product.setVcAttr12Value(productBasicForm.getAttribute12Val());
		product.setVcAttr13Value(productBasicForm.getAttribute13Val());
		product.setVcAttr14Value(productBasicForm.getAttribute14Val());
		product.setVcAttr15Value(productBasicForm.getAttribute15Val());
		product.setVcAttr16Value(productBasicForm.getAttribute16Val());
		product.setVcAttr17Value(productBasicForm.getAttribute17Val());
		product.setVcAttr18Value(productBasicForm.getAttribute18Val());
		product.setVcAttr19Value(productBasicForm.getAttribute19Val());
		product.setVcAttr20Value(productBasicForm.getAttribute20Val());
		product.setVcAttr21Value(productBasicForm.getAttribute21Val());
		product.setVcAttr22Value(productBasicForm.getAttribute22Val());
		product.setVcAttr23Value(productBasicForm.getAttribute23Val());
		product.setVcAttr24Value(productBasicForm.getAttribute24Val());
		product.setVcAttr25Value(productBasicForm.getAttribute25Val());
		product.setVcPrdImg1Pth(productBasicForm.getProdImgPath1());
		product.setVcPrdImg2Pth(productBasicForm.getProdImgPath2());
		product.setVcPrdImg3Pth(productBasicForm.getProdImgPath3());
		product.setVcPrdImg4Pth(productBasicForm.getProdImgPath4());
		product.setNmAttr1Value((StringUtils.equalsIgnoreCase(
				productBasicForm.getNmAttrValue1(), "") ? null
				: new BigDecimal(productBasicForm.getNmAttrValue1())));
		product.setNmAttr2Value((StringUtils.equalsIgnoreCase(
				productBasicForm.getNmAttrValue2(), "") ? null
				: new BigDecimal(productBasicForm.getNmAttrValue2())));
		product.setNmAttr3Value((StringUtils.equalsIgnoreCase(
				productBasicForm.getNmAttrValue3(), "") ? null
				: new BigDecimal(productBasicForm.getNmAttrValue3())));
		product.setNmAttr4Value((StringUtils.equalsIgnoreCase(
				productBasicForm.getNmAttrValue4(), "") ? null
				: new BigDecimal(productBasicForm.getNmAttrValue4())));
		product.setNmAttr5Value((StringUtils.equalsIgnoreCase(
				productBasicForm.getNmAttrValue5(), "") ? null
				: new BigDecimal(productBasicForm.getNmAttrValue5())));
		// product.setVcAttr1UntValue(productBasicForm.getAttrUnitValue1());
		// product.setVcAttr2UntValue(productBasicForm.getAttrUnitValue2());
		// product.setVcAttr3UntValue(productBasicForm.getAttrUnitValue3());
		// product.setVcAttr4UntValue(productBasicForm.getAttrUnitValue4());
		// product.setVcAttr5UntValue(productBasicForm.getAttrUnitValue5());
		if (StringUtils.equalsIgnoreCase(productBasicForm.getMode(), "Edit")) {
			product.setNmUpdtdBy(new BigDecimal(productBasicForm.getUpdatedBy()));
			product.setDtUpdtdAt(DateUtil.getCurrentDateTime());

		} else {
			// product.setDtCrtdAt(DateUtil.getCurrentDateTime());
			product.setNmCrtdBy(new BigDecimal(productBasicForm.getCreatedBy()));
			product.setNmUpdtdBy(new BigDecimal(productBasicForm.getUpdatedBy()));
			// product.setDtUpdtdAt(DateUtil.getCurrentDateTime());

		}

		productService.saveOrUpdate(product);

		return product;

	}

	/**
	 * save or update Related Products.
	 * 
	 * @param relatedProductForm
	 * @return updated RelatedProductTbDtl object
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public RelatedProductTbDtl registerRelatedProducts(
			RelatedProductForm relatedProductForm)
			throws BusinessFailureException, GenericFailureException {
		RelatedProductTbDtl relatedProduct = new RelatedProductTbDtl();
		ProductTbMaster prod = new ProductTbMaster();
		prod.setNmPrdId(relatedProductForm.getProductId());
		relatedProduct.setProductTbMaster1(prod);
		ProductTbMaster prodRelatedId = new ProductTbMaster();
		prodRelatedId.setNmPrdId(relatedProductForm.getRelatedProductId());
		relatedProduct.setIsDltd(new BigDecimal("0"));
		relatedProduct.setProductTbMaster2(prodRelatedId);
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("productTbMaster1", prod);
		properties.put("productTbMaster2", prodRelatedId);
		properties.put("isDltd", new BigDecimal(0));
		List<RelatedProductTbDtl> relatedProductExisted = productService
				.findAllRelatedProductsByProduct(properties);
		if (relatedProductExisted.size() == 0) {
			productService.saveOrUpdate(relatedProduct);

		}

		return relatedProduct;

	}

	/**
	 * Manage related products for a particular products.
	 * 
	 * @param arr
	 * @param product
	 */
	public void manageRelatedProduts(String arr[], ProductTbMaster product)
			throws BusinessFailureException, GenericFailureException {

		HashMap<String, Object> properties = new HashMap<String, Object>();

		properties.put("productTbMaster1", product);

		properties.put("isDltd", new BigDecimal(0));
		List<RelatedProductTbDtl> relatedProductExisted = productService
				.findAllRelatedProductsByProduct(properties);

		for (RelatedProductTbDtl relatedProd : relatedProductExisted) {
			boolean flag = true;
			for (int i = 0; i < arr.length; i++) {
				long id = Long.parseLong(arr[i]);
				if (relatedProd.getProductTbMaster2().getNmPrdId() == id) {
					flag = false;
				}

			}
			if (flag == true) {
				relatedProd.setIsDltd(new BigDecimal("1"));
				productService.saveOrUpdate(relatedProd);
			}
		}

	}

	/**
	 * save or update similar products.
	 * 
	 * @param similarProductForm
	 * @return updated SimilarProductTbDtl object
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public SimilarProductTbDtl registerSimilarProducts(
			SimilarProductForm similarProductForm)
			throws BusinessFailureException, GenericFailureException {
		SimilarProductTbDtl similarProduct = new SimilarProductTbDtl();
		ProductTbMaster prod1 = new ProductTbMaster();
		prod1.setNmPrdId(similarProductForm.getProductId());
		similarProduct.setProductTbMaster1(prod1);
		similarProduct.setIsDltd(new BigDecimal("0"));
		ProductTbMaster prod2 = new ProductTbMaster();
		prod2.setNmPrdId(similarProductForm.getSimilarProductId());
		similarProduct.setProductTbMaster2(prod2);
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("productTbMaster1", prod1);
		properties.put("productTbMaster2", prod2);
		properties.put("isDltd", new BigDecimal(0));
		List<SimilarProductTbDtl> similarProductExisted = productService
				.findAllSimilarProductByProperty(properties);
		if (similarProductExisted.size() == 0) {
			productService.saveOrUpdate(similarProduct);

		}

		return similarProduct;

	}

	/**
	 * Manage Similar products and soft deletes similar products not choosen by
	 * the User on UI.
	 * 
	 * @param arr
	 * @param product
	 */
	public void manageSimilarProduts(String arr[], ProductTbMaster product)
			throws BusinessFailureException, GenericFailureException {

		HashMap<String, Object> properties = new HashMap<String, Object>();

		properties.put("productTbMaster1", product);

		properties.put("isDltd", new BigDecimal(0));
		List<SimilarProductTbDtl> similarProductExisted = productService
				.findAllSimilarProductByProperty(properties);

		for (SimilarProductTbDtl similarProd : similarProductExisted) {
			boolean flag = true;
			for (int i = 0; i < arr.length; i++) {
				long id = Long.parseLong(arr[i]);
				if (similarProd.getProductTbMaster2().getNmPrdId() == id) {
					flag = false;
				}

			}
			if (flag == true) {
				similarProd.setIsDltd(new BigDecimal("1"));
				productService.saveOrUpdate(similarProd);
			}
		}

	}

	/**
	 * Provides category tree along with levels.
	 * 
	 * @param sessionInfo
	 *            Session information of the user signed-in
	 * @return
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public Map<Long, String> getCategoriesHierarchyTree(SessionInfo sessionInfo)
			throws BusinessFailureException, GenericFailureException {

		Map<Long, String> treeMap = productService
				.getCategoriesHierarchyTree(sessionInfo);

		return treeMap;

	}

	/**
	 * view all products category wise
	 * 
	 * @param propertyName
	 *            Field name of table as criteria
	 * @param value
	 *            value of criteria
	 * @return list of all the products
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public List<ProductBasicForm> viewAllProductByCategoryId(
			CategoryTbMaster cat, SessionInfo session, long productId)
			throws BusinessFailureException, GenericFailureException {

		HashMap<String, Object> properties = new HashMap<String, Object>();

		properties.put("categoryTbMaster", cat);

		properties.put("isDltd", new BigDecimal(0));

		StoreTbMaster store = new StoreTbMaster();
		store.setNmStrId(session.getStoreId());

		properties.put("storeTbMaster", store);

		List<ProductTbMaster> products = productService
				.findAllProductsByCategory(properties);

		List<ProductBasicForm> list = new ArrayList<ProductBasicForm>();

		if (null != products) {

			for (ProductTbMaster prod : products) {
				if (productId != 0) {
					if (productId != prod.getNmPrdId()
							&& StringUtils.equalsIgnoreCase(
									prod.getVcCmpltnStts(), "S")) {
						ProductBasicForm productBasicFrm = new ProductBasicForm();
						productBasicFrm.setProductID(prod.getNmPrdId());
						productBasicFrm.setProductName(prod.getVcPrdNm());
						productBasicFrm.setCategoryId(prod
								.getCategoryTbMaster().getNmCtgryId() + "");
						productBasicFrm.setDealPrice(prod.getNmDlPrc() + "");
						list.add(productBasicFrm);
					}
				} else { // else part will when view button is clicked from
							// category listing page
					ProductBasicForm productBasicFrm = new ProductBasicForm();
					productBasicFrm.setProductID(prod.getNmPrdId());
					productBasicFrm.setProductName(prod.getVcPrdNm());
					productBasicFrm.setCategoryId(prod.getCategoryTbMaster()
							.getNmCtgryId() + "");
					productBasicFrm.setDealPrice(prod.getNmDlPrc() + "");
					list.add(productBasicFrm);
				}

			}
		}

		return list;

	}

	/**
	 * view all related product by product wise.
	 * 
	 * @param productTbMaster
	 * @return list of Related Products
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public List<RelatedProductForm> viewAllRelatedProductByProduct(
			ProductTbMaster productTbMaster) throws BusinessFailureException,
			GenericFailureException {

		HashMap<String, Object> properties = new HashMap<String, Object>();

		properties.put("productTbMaster1", productTbMaster);

		properties.put("isDltd", new BigDecimal(0));

		List<RelatedProductTbDtl> list = productService
				.findAllRelatedProductsByProduct(properties);

		List<RelatedProductForm> realtedList = new ArrayList<RelatedProductForm>();

		if (null != list) {

			for (RelatedProductTbDtl related : list) {
				long isDeleted = related.getProductTbMaster2().getIsDltd()
						.longValue();
				if (isDeleted == 0) {
					RelatedProductForm RelatedProductFrm = new RelatedProductForm();
					RelatedProductFrm.setRelatedProductId(related
							.getProductTbMaster2().getNmPrdId());
					RelatedProductFrm.setRelatedProductName(related
							.getProductTbMaster2().getVcPrdNm());
					realtedList.add(RelatedProductFrm);
				}
			}

		}

		return realtedList;

	}

	/**
	 * view all similar products product wise.
	 * 
	 * @param productTbMaster
	 * @return list of Similar Products
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public List<SimilarProductForm> viewAllSimilarProductByProductId(
			ProductTbMaster productTbMaster) throws BusinessFailureException,
			GenericFailureException {

		HashMap<String, Object> properties = new HashMap<String, Object>();

		properties.put("productTbMaster1", productTbMaster);

		properties.put("isDltd", new BigDecimal(0));

		List<SimilarProductTbDtl> list = productService
				.findAllSimilarProductByProperty(properties);

		List<SimilarProductForm> similarList = new ArrayList<SimilarProductForm>();

		if (null != list) {

			for (SimilarProductTbDtl similar : list) {
				long isDeleted = similar.getProductTbMaster2().getIsDltd()
						.longValue();
				if (isDeleted == 0) {
					SimilarProductForm similarProductForm = new SimilarProductForm();
					similarProductForm.setSimilarProductId(similar
							.getProductTbMaster2().getNmPrdId());
					similarProductForm.setSimilarProductName(similar
							.getProductTbMaster2().getVcPrdNm());
					similarList.add(similarProductForm);
				}
			}

		}

		return similarList;

	}

	/**
	 * find product by productId.
	 * 
	 * @param id
	 * @return ProductTbMaster object
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public ProductTbMaster viewProductById(long id)
			throws BusinessFailureException, GenericFailureException {

		ProductTbMaster product = productService.findByID(id);

		return product;

	}

	/**
	 * populate ProductBasicForm from ProductTbMaster object.
	 * 
	 * @param productBasicForm
	 * @param product
	 * @return populated productBasicForm from ProductTbMaster object.
	 */
	public ProductBasicForm setProductVO(ProductBasicForm productBasicForm,
			ProductTbMaster product) throws BusinessFailureException,
			GenericFailureException {

		productBasicForm.setProductID(product.getNmPrdId());
		productBasicForm.setProductName(product.getVcPrdNm());
		productBasicForm.setProductDesc(product.getVcPrdDsc());
		productBasicForm.setStatus(product.getVcStts());
		productBasicForm.setIsCompletionStatus(product.getVcCmpltnStts());
		productBasicForm.setCategoryId(String.valueOf(product
				.getCategoryTbMaster().getNmCtgryId()));
		productBasicForm.setStoreId(product.getStoreTbMaster().getNmStrId());
		productBasicForm.setDealPrice(product.getNmDlPrc() + "");
		productBasicForm.setSalePrice(String.valueOf(product.getNmSp()));
		productBasicForm.setCategoryId(String.valueOf(product
				.getCategoryTbMaster().getNmCtgryId()));
		productBasicForm.setIsCod(product.getIsCod()
				.equals(new BigDecimal("1")) ? true : false);
		productBasicForm.setIsFtrd(product.getIsFtrd().equals(
				new BigDecimal("1")) ? true : false);

		productBasicForm.setFreeShiping(product.getIsFrShpng().equals(
				new BigDecimal("1")) ? true : false);
		productBasicForm.setIsVariant(product.getIsVrnt().equals(
				new BigDecimal("1")) ? true : false);
		productBasicForm.setAttribute1Val(product.getVcAttr1Value());
		productBasicForm.setAttribute2Val(product.getVcAttr2Value());
		productBasicForm.setAttribute3Val(product.getVcAttr3Value());
		productBasicForm.setAttribute4Val(product.getVcAttr4Value());
		productBasicForm.setAttribute5Val(product.getVcAttr5Value());
		productBasicForm.setAttribute6Val(product.getVcAttr6Value());
		productBasicForm.setAttribute7Val(product.getVcAttr7Value());
		productBasicForm.setAttribute8Val(product.getVcAttr8Value());
		productBasicForm.setAttribute9Val(product.getVcAttr9Value());
		productBasicForm.setAttribute10Val(product.getVcAttr10Value());
		productBasicForm.setAttribute11Val(product.getVcAttr11Value());
		productBasicForm.setAttribute12Val(product.getVcAttr12Value());
		productBasicForm.setAttribute13Val(product.getVcAttr13Value());
		productBasicForm.setAttribute14Val(product.getVcAttr14Value());
		productBasicForm.setAttribute15Val(product.getVcAttr15Value());
		productBasicForm.setAttribute16Val(product.getVcAttr16Value());
		productBasicForm.setAttribute17Val(product.getVcAttr17Value());
		productBasicForm.setAttribute18Val(product.getVcAttr18Value());
		productBasicForm.setAttribute19Val(product.getVcAttr19Value());
		productBasicForm.setAttribute20Val(product.getVcAttr20Value());
		productBasicForm.setAttribute21Val(product.getVcAttr21Value());
		productBasicForm.setAttribute22Val(product.getVcAttr22Value());
		productBasicForm.setAttribute23Val(product.getVcAttr23Value());
		productBasicForm.setAttribute24Val(product.getVcAttr24Value());
		productBasicForm.setAttribute25Val(product.getVcAttr25Value());
		productBasicForm.setProdImgPath1(product.getVcPrdImg1Pth());
		productBasicForm.setProdImgPath2(product.getVcPrdImg2Pth());
		productBasicForm.setProdImgPath3(product.getVcPrdImg3Pth());
		productBasicForm.setProdImgPath4(product.getVcPrdImg4Pth());
		productBasicForm.setCreatedAt(product.getDtCrtdAt().getTime());
		productBasicForm
				.setNmAttrValue1((product.getNmAttr1Value() == null ? ""
						: String.valueOf(product.getNmAttr1Value())));
		productBasicForm
				.setNmAttrValue2((product.getNmAttr2Value() == null ? ""
						: String.valueOf(product.getNmAttr2Value())));
		productBasicForm
				.setNmAttrValue3((product.getNmAttr3Value() == null ? ""
						: String.valueOf(product.getNmAttr3Value())));
		productBasicForm
				.setNmAttrValue4((product.getNmAttr4Value() == null ? ""
						: String.valueOf(product.getNmAttr4Value())));
		productBasicForm
				.setNmAttrValue5((product.getNmAttr5Value() == null ? ""
						: String.valueOf(product.getNmAttr5Value())));
		productBasicForm.setQuantity((product.getNmQnty() == null ? "" : String
				.valueOf(product.getNmQnty())));

		if (productBasicForm.getProductID() > 0) {
			ProductTbMaster prod = new ProductTbMaster();
			prod.setNmPrdId(productBasicForm.getProductID());
			List<ProductVariantForm> list = viewAllVariantAttributes(prod);
			if (list.size() > 0) {
				productBasicForm.setIsVariantAdded(true);
			} else {
				productBasicForm.setIsVariantAdded(false);
			}
		}

		return productBasicForm;
	}

	/**
	 * 
	 * @param tabVal
	 * @return
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public List<ProductBasicForm> viewAllProducts(String tabVal,
			SessionInfo sessionInfo) throws BusinessFailureException,
			GenericFailureException {

		HashMap<String, Object> properties = new HashMap<String, Object>();

		properties.put("isDltd", new BigDecimal("0"));
		properties.put("asc", "vcPrdNm");
		StoreTbMaster strTb = new StoreTbMaster();
		strTb.setNmStrId(sessionInfo.getStoreId());
		properties.put("storeTbMaster", strTb);

		List<ProductTbMaster> products = productService
				.findAllByProperty(properties);

		List<ProductBasicForm> list = new ArrayList<ProductBasicForm>();

		if (null != products) {
			for (ProductTbMaster prod : products) {

				ProductBasicForm productBasicFrm = new ProductBasicForm();
				productBasicFrm.setProductID(prod.getNmPrdId());
				productBasicFrm.setCategoryId(prod.getCategoryTbMaster()
						.getNmCtgryId() + "");
				productBasicFrm.setProductName(prod.getVcPrdNm());
				productBasicFrm.setDealPrice(prod.getNmDlPrc() + "");
				productBasicFrm.setStatus(prod.getVcStts());
				productBasicFrm.setCreatedAt(prod.getDtCrtdAt().getTime());
				productBasicFrm.setQuantity(prod.getNmQnty() + "");
				productBasicFrm.setIsVariant(prod.getIsVrnt().longValue() == 1?true:false);
				
				List<ProductVariantTbDtl> listVariant = prod
						.getProductVariantTbDtls();

				for (ProductVariantTbDtl variant : listVariant) {

					long qty = variant.getNmQntty().longValue();

					if (qty == 0) {
						productBasicFrm.setIsVariantOutOfStock(true);
						break;
					} else {
						productBasicFrm.setIsVariantOutOfStock(false);
					}
				}

				for (ProductVariantTbDtl variant : listVariant) {

					if (StringUtils.equalsIgnoreCase(variant.getVcStts(),
							PRODUCT_STATUS.InActive.name())) {

						productBasicFrm.setIsVariantInActive(true);
						break;
					} else {
						productBasicFrm.setIsVariantInActive(false);
					}
				}

				list.add(productBasicFrm);

			}
		}

		return list;

	}

	public ProductTbMaster viewAllProductsByName(
			ProductBasicForm producBasicForm) throws BusinessFailureException,
			GenericFailureException {

		/*
		 * HashMap<String, Object> properties = new HashMap<String, Object>();
		 * 
		 * properties.put("isDltd", new BigDecimal("0"));
		 * 
		 * properties.put("vcPrdNm", producBasicForm.getProductName());
		 * 
		 * CategoryTbMaster tbMaster = new CategoryTbMaster();
		 * 
		 * tbMaster.setNmCtgryId(Long.parseLong(producBasicForm.getCategoryId()))
		 * ;
		 * 
		 * properties.put("categoryTbMaster", tbMaster);
		 */
		List<ProductTbMaster> products = productService
				.findAllByStringPropertyIgnoreCase("vcPrdNm",
						producBasicForm.getProductName());

		ProductTbMaster product = null;

		for (ProductTbMaster prod : products) {
			long isDeleted = prod.getIsDltd().longValue();
			if (prod.getCategoryTbMaster().getNmCtgryId() == Long
					.parseLong(producBasicForm.getCategoryId())
					&& isDeleted == 0) {
				product = prod;
			}
		}

		return product;

	}

	public List<ProductVariantTbDtl> viewAllVariantsByName(
			ProductVariantForm producVariantForm)
			throws BusinessFailureException, GenericFailureException {

		List<ProductVariantTbDtl> products = productVariantService
				.findAllByStringPropertyIgnoreCase("vcVrntNm",
						producVariantForm.getVcVrntNm());

		List<ProductVariantTbDtl> variants = new ArrayList<ProductVariantTbDtl>();

		for (ProductVariantTbDtl prodVar : products) {
			long isDeleted = prodVar.getIsDltd().longValue();
			if (prodVar.getProductTbMaster().getNmPrdId() == producVariantForm
					.getProductId() && isDeleted == 0) {

				variants.add(prodVar);

			}
		}

		return variants;

	}

	public ProductVariantForm addVariantAttributes(
			ProductVariantForm productVariantForm)
			throws BusinessFailureException, GenericFailureException {

		ProductVariantTbDtl productVariantTbDtl = new ProductVariantTbDtl();
		String ignoreProperties[] = { "productTbMaster", "isDltd", "nmDlPrc",
				"nmSp", "vcVrntOptn1Unt", "vcVrntOptn2Unt", "vcVrntOptn3Unt",
				"vcVrntOptn4Unt", "vcVrntOptn5Unt", "nmVrntOptn1",
				"nmVrntOptn2", "nmVrntOptn3", "nmVrntOptn4", "nmVrntOptn5",
				"nmQntty" };

		BeanUtils.copyProperties(productVariantForm, productVariantTbDtl,
				ignoreProperties);
		ProductTbMaster prod = new ProductTbMaster();
		prod.setNmPrdId(productVariantForm.getProductId());
		productVariantTbDtl.setProductTbMaster(prod);

		productVariantTbDtl.setIsDltd(new BigDecimal(productVariantForm
				.getIsDltd()));

		productVariantTbDtl.setNmDlPrc((StringUtils.isBlank(productVariantForm
				.getNmDlPrc()) ? new BigDecimal("0") : new BigDecimal(
				productVariantForm.getNmDlPrc())));
		productVariantTbDtl.setNmSp((StringUtils.isBlank(productVariantForm
				.getNmSp()) ? new BigDecimal("0") : new BigDecimal(
				productVariantForm.getNmSp())));
		productVariantTbDtl.setNmVrntOptn1((StringUtils
				.isBlank(productVariantForm.getNmVrntOptn1()) ? null
				: new BigDecimal(productVariantForm.getNmVrntOptn1())));
		productVariantTbDtl.setNmVrntOptn2((StringUtils
				.isBlank(productVariantForm.getNmVrntOptn2()) ? null
				: new BigDecimal(productVariantForm.getNmVrntOptn2())));

		productVariantTbDtl.setNmVrntOptn3((StringUtils
				.isBlank(productVariantForm.getNmVrntOptn3()) ? null
				: new BigDecimal(productVariantForm.getNmVrntOptn3())));

		productVariantTbDtl.setNmVrntOptn4((StringUtils
				.isBlank(productVariantForm.getNmVrntOptn4()) ? null
				: new BigDecimal(productVariantForm.getNmVrntOptn4())));

		productVariantTbDtl.setNmVrntOptn5((StringUtils
				.isBlank(productVariantForm.getNmVrntOptn5()) ? null
				: new BigDecimal(productVariantForm.getNmVrntOptn5())));

		productVariantTbDtl.setVcStts(productVariantForm.getVcStts());
		productVariantTbDtl.setNmQntty((StringUtils.isBlank(productVariantForm
				.getNmQntty()) ? new BigDecimal("0") : new BigDecimal(
				productVariantForm.getNmQntty())));

		if (StringUtils
				.equalsIgnoreCase(productVariantForm.getMode(), ADD_MODE)) {
			productVariantTbDtl.setDtCrtdAt(DateUtil.getCurrentDateTime());
			productVariantTbDtl.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		} else {
			productVariantTbDtl.setDtUpdtdAt(DateUtil.getCurrentDateTime());
		}

		productVariantService.saveOrUpdate(productVariantTbDtl);

		/*
		 * ProductTbMaster productForSave =
		 * productService.getProductById(productVariantForm.getProductId());
		 * productForSave.setVcStts("Active");
		 * productService.saveOrUpdate(productForSave);
		 */

		return productVariantForm;

	}

	/**
	 * Provides Variant Details by ID and populates ProductVariantForm object
	 * from productVariantTbDtl objects.
	 * 
	 * @param id
	 * @return populated ProductVariantForm object from productVariantTbDtl
	 *         objects.
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public ProductVariantForm getVariantById(long id)
			throws BusinessFailureException, GenericFailureException {

		ProductVariantTbDtl productVariantTbDtl = productVariantService
				.findByID(id);
		ProductVariantForm frm = null;
		if (null != productVariantTbDtl) {
			frm = new ProductVariantForm();
			String ignoreProperties[] = { "productTbMaster", "isDltd",
					"nmDlPrc", "nmSp", "vcVrntOptn1Unt", "vcVrntOptn2Unt",
					"vcVrntOptn3Unt", "vcVrntOptn4Unt", "vcVrntOptn5Unt",
					"nmVrntOptn1", "nmVrntOptn2", "nmVrntOptn3", "nmVrntOptn4",
					"nmVrntOptn5", "nmQntty" };
			BeanUtils
					.copyProperties(productVariantTbDtl, frm, ignoreProperties);
			frm.setProductId(productVariantTbDtl.getProductTbMaster()
					.getNmPrdId());
			frm.setNmDlPrc(productVariantTbDtl.getNmDlPrc() + "");
			frm.setNmSp(productVariantTbDtl.getNmSp() + "");
			frm.setNmVrntOptn1(productVariantTbDtl.getNmVrntOptn1() == null ? ""
					: productVariantTbDtl.getNmVrntOptn1() + "");
			frm.setNmVrntOptn2(productVariantTbDtl.getNmVrntOptn2() == null ? ""
					: productVariantTbDtl.getNmVrntOptn2() + "");
			frm.setNmVrntOptn3(productVariantTbDtl.getNmVrntOptn3() == null ? ""
					: productVariantTbDtl.getNmVrntOptn3() + "");
			frm.setNmVrntOptn4(productVariantTbDtl.getNmVrntOptn4() == null ? ""
					: productVariantTbDtl.getNmVrntOptn4() + "");
			frm.setNmVrntOptn5(productVariantTbDtl.getNmVrntOptn5() == null ? ""
					: productVariantTbDtl.getNmVrntOptn5() + "");
			frm.setNmQntty(productVariantTbDtl.getNmQntty() == null ? ""
					: productVariantTbDtl.getNmQntty() + "");
		}
		return frm;

	}

	/**
	 * View all the Variants product wise.
	 * 
	 * @param productTbMaster
	 * @return populated list of ProductVariantForm objects .
	 * @throws BusinessFailureException
	 * @throws GenericFailureException
	 */
	public List<ProductVariantForm> viewAllVariantAttributes(
			ProductTbMaster productTbMaster) throws BusinessFailureException,
			GenericFailureException {

		HashMap<String, Object> properties = new HashMap<String, Object>();

		properties.put("productTbMaster", productTbMaster);

		properties.put("isDltd", new BigDecimal(0));

		List<ProductVariantTbDtl> variants = productVariantService
				.findAllByProperty(properties);

		List<ProductVariantForm> list = new ArrayList<ProductVariantForm>();

		if (null != variants) {
			for (ProductVariantTbDtl attribute : variants) {

				ProductVariantForm variantFrm = new ProductVariantForm();
				variantFrm.setProductId(productTbMaster.getNmPrdId());
				variantFrm.setNmVrntId(attribute.getNmVrntId());
				variantFrm.setVcVrntOptn1(attribute.getVcVrntOptn1());
				variantFrm.setVcVrntOptn2(attribute.getVcVrntOptn2());
				variantFrm.setVcVrntOptn3(attribute.getVcVrntOptn3());
				variantFrm.setVcVrntOptn4(attribute.getVcVrntOptn4());
				variantFrm.setVcVrntOptn5(attribute.getVcVrntOptn5());
				variantFrm.setVcVrntImg1(attribute.getVcVrntImg1());
				variantFrm.setVcVrntImg2(attribute.getVcVrntImg2());
				variantFrm.setVcVrntImg3(attribute.getVcVrntImg3());
				variantFrm.setVcVrntNm(attribute.getVcVrntNm());
				variantFrm.setNmDlPrc(attribute.getNmDlPrc() + "");
				variantFrm.setNmSp(attribute.getNmSp() + "");
				variantFrm.setNmVrntOptn1(attribute.getNmVrntOptn1() + "");
				variantFrm.setNmVrntOptn2(attribute.getNmVrntOptn2() + "");
				variantFrm.setNmVrntOptn3(attribute.getNmVrntOptn3() + "");
				variantFrm.setNmVrntOptn4(attribute.getNmVrntOptn4() + "");
				variantFrm.setNmVrntOptn5(attribute.getNmVrntOptn5() + "");
				// variantFrm.setAttrUnitValue1(attribute.getVcVrntOptn1Unt());
				// variantFrm.setAttrUnitValue2(attribute.getVcVrntOptn2Unt());
				// variantFrm.setAttrUnitValue3(attribute.getVcVrntOptn3Unt());
				// variantFrm.setAttrUnitValue4(attribute.getVcVrntOptn4Unt());
				// variantFrm.setAttrUnitValue5(attribute.getVcVrntOptn5Unt());
				variantFrm.setNmQntty(attribute.getNmQntty() == null ? ""
						: attribute.getNmQntty() + "");
				variantFrm.setVcStts(attribute.getVcStts());
				list.add(variantFrm);

			}
		}

		return list;

	}

	public ProductTbMaster getProductById(long id) {
		ProductTbMaster product = productService.findByID(id);
		return product;
	}

	public void setProductStatus(List<ProductTbMaster> products, String status) {
		productService.setProductStatus(products, status);
	}

	public void setVariantStatus(List<ProductVariantTbDtl> variants,
			String status) throws BusinessFailureException,
			GenericFailureException {
		productVariantService.setVariantStatus(variants, status);
	}

	public ProductVariantTbDtl getVarinatById(long id)
			throws BusinessFailureException, GenericFailureException {
		ProductVariantTbDtl variant = productVariantService.findByID(id);
		return variant;
	}

}
