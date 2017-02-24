/**
 * (c) R Systems International Ltd.
 */
/**
 * Contains all the Handler Mapping for Products related Action.
 */
package com.sot.ecommerce.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sbsc.fos.category.web.handler.CategoryHandler;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.CategoryTbMaster;
import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.persistance.ProductVariantTbDtl;
import com.sbsc.fos.persistance.RelatedProductTbDtl;
import com.sbsc.fos.product.common.CategorySectionAttributeVo;
import com.sbsc.fos.product.web.form.ProductBasicForm;
import com.sbsc.fos.product.web.form.ProductVariantForm;
import com.sbsc.fos.product.web.form.RelatedProductForm;
import com.sbsc.fos.product.web.form.SimilarProductForm;
import com.sbsc.fos.product.web.form.validator.ProductValidator;
import com.sbsc.fos.product.web.form.validator.ProductVariantValidator;
import com.sbsc.fos.product.web.form.validator.SelectCategoryValidator;
import com.sbsc.fos.product.web.handler.ProductHandler;
import com.sbsc.fos.utils.CommonUtil;
import com.sbsc.fos.utils.FormSearchDataExtract;
import com.sbsc.fos.utils.NumericAttributeUnits;
import com.sbsc.fos.utils.SBSConstants;
import com.sbsc.fos.web.form.FormSearch;
import com.sbsc.fos.web.form.validator.FormSearchValidator;

/**
 * @author diksha.rattan
 * 
 */
@Controller
public class ProductController implements SBSConstants, NumericAttributeUnits {

	/** Logger instance. **/
	private static final Logger LOG = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductHandler productHandler;

	@Autowired
	private CategoryHandler categoryHandler;

	@Autowired
	ProductBasicForm productBasicForm;

	@Autowired
	private ProductValidator pValidator;

	@Autowired
	private SelectCategoryValidator sValidator;

	@Autowired
	private ProductVariantValidator pVValidator;

	@Autowired
	FormSearchValidator formSearchValidator;

	private RelatedProductTbDtl relatedProduct;
	
	@Autowired
	private MessageSource messageSource;

	

	/**
	 * Provides all the products as per selected filter on UI
	 * 
	 * @param productBasicForm
	 * @param locale
	 * @param request
	 * @return Model and view attributes as
	 * @throws GenericFailureException
	 *             ModelAndView object
	 * @throws BusinessFailureException
	 * @throws ParseException
	 */
	@RequestMapping(value = "admin/manageProducts.htm", method = RequestMethod.POST)
	public ModelAndView allProductsByFilter(
			@ModelAttribute("formSearch") FormSearch formSearch, Locale locale,
			BindingResult bindingResult, HttpServletRequest request)
			throws GenericFailureException, BusinessFailureException,
			ParseException {

		String errorMessage = null;
		ModelAndView modelAndView = null;
		try {
			List<ProductBasicForm> productList = null;
			List<ProductBasicForm> productListOutOfStock = null;
			List<ProductBasicForm> productListInActive = null;
			Map<String, Object> filter_criteria = null;
		
			formSearchValidator.validate(formSearch, bindingResult);
			modelAndView = new ModelAndView("/product/view_products_t1");

			if (bindingResult.hasErrors()) {
				LOG.info("Invalid Type for filter:" + filter_criteria);

				modelAndView.addObject("productList", productList);
				if (productList == null) {
					modelAndView.addObject("listError", FILTER_PRODUCT);
				}

				String selectedStatus = formSearch.getFilters().get(2)
						.getValue();

				modelAndView.addObject("allStatuses", getAllStatus());
				modelAndView.addObject("selectedStatus", selectedStatus);
				modelAndView.addObject("formSearch", formSearch);
				return modelAndView;
			} else {

				productList = productHandler
						.findAllProductsByFilters(FormSearchDataExtract
								.extractFormData(formSearch));
				productListOutOfStock = new ArrayList<ProductBasicForm>();
				productListInActive = new ArrayList<ProductBasicForm>();
				if (formSearch.getFilters().get(5).getName().equals("filter.ProductTbMaster.nmQnty.BigDecimal")) {
					
				
					for(ProductBasicForm frm:productList){
						  
						
						if(null != frm.getIsVariantOutOfStock()){
							if(frm.getIsVariantOutOfStock()){
							productListOutOfStock.add(frm);
							}
						}else if(StringUtils.equalsIgnoreCase(frm.getQuantity(),"0") && frm.getIsVariant() == false){
							productListOutOfStock.add(frm);
						}
						
						
							
					}
					modelAndView.addObject("productList", productListOutOfStock);
				}else if (formSearch.getFilters().get(5).getName().equals("filter.ProductTbMaster.vcStts.String")) {
					
				
					for(ProductBasicForm frm:productList){
						  
						
						if(null != frm.getIsVariantInActive()){
							if(frm.getIsVariantInActive()){
								productListInActive.add(frm);
							}
						}else if(StringUtils.equalsIgnoreCase(frm.getStatus(),PRODUCT_STATUS.InActive.name()) && frm.getIsVariant() == false){
							productListInActive.add(frm);
						}
							
					}
					modelAndView.addObject("productList", productListInActive);
				}else{
					
					modelAndView.addObject("productList", productList);
					
				}
			

							
				
				if (productList.size() == 0 || productListOutOfStock.size() == 0 || productListInActive.size() == 0) {
					modelAndView.addObject("listError", FILTER_PRODUCT);
				}

				String selectedStatus = formSearch.getFilters().get(2)
						.getValue();

				int hiddenStatus = 0;

				/**
				 * 
				 * for hidden div of status filter on hidden tab on product list
				 * page
				 * 
				 * status is taken 0 for hiding tab
				 */
				if (formSearch.getFilters().get(4).getValue().equals("1")) {
					hiddenStatus = 1;
				}

				modelAndView.addObject("allStatuses", getAllStatus());
				modelAndView.addObject("hiddenStatus", hiddenStatus);

				modelAndView.addObject("selectedStatus", selectedStatus);

				return modelAndView;
			}
		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String.format(
					"Error occured while getting the Product listing - %s ",
					exp));
		}

		return modelAndView;

	}

	/**
	 * Go to Product Listing Page with all the products details
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/manageProducts.htm", method = RequestMethod.GET)
	public ModelAndView viewProductAdmin(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			HttpServletRequest request) {

		LOG.debug(MENTRY + "public ModelAndView viewProductAdmin()");
		String errorMessage = null;
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("/product/view_products_t1");
			modelAndView.addObject("productBasicForm", new ProductBasicForm());
			List<ProductBasicForm> productList = productHandler
					.viewAllProducts(productBasicForm.getTabVal(),
							CommonUtil.getSessionInfo(request));
			modelAndView.addObject("productList", productList);
			if (productList.size() == 0) {
				modelAndView.addObject("listError",
						"No product is created yet.");
			}

			/**
			 * 
			 * for hidden div of status filter on hidden tab on product list
			 * page
			 * 
			 * status is taken 0 for hiding tab
			 */

			int hiddenStatus = 1;

			modelAndView.addObject("hiddenStatus", hiddenStatus);

			modelAndView.addObject("allStatuses", getAllStatus());
			modelAndView.addObject("formSearch", new FormSearch());
			modelAndView.addObject("productBasicForm", productBasicForm);
		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String.format(
					"Error occured while getting the Product listing - %s ",
					exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * Go to category listing page
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/selectCategory.htm", method = RequestMethod.GET)
	public ModelAndView selectCategory(HttpServletRequest request) {

		LOG.debug(MENTRY + "public ModelAndView selectCategory()");
		String errorMessage = null;
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("/product/select_category");
			SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
			List<CategoryTbMaster> list = null;
			Map<Long, String> categoryTree = productHandler
					.getCategoriesHierarchyTree(sessionInfo);
			modelAndView.addObject("treeMap", categoryTree);
			modelAndView.addObject("categoryList", list);
		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String.format(
					"Error occured while selecting category - %s ", exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * Ajax call handler to get all the products category wise
	 * 
	 * @param request
	 * @return Ajax response as List<ProductAjaxForm>
	 */
	@RequestMapping(value = "admin/getProducts.htm", method = RequestMethod.GET)
	public @ResponseBody
	List<ProductBasicForm> getProducts(HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "List<ProductAjaxForm> getProducts(HttpServletRequest request)");
		String catId = request.getParameter("categoryId");
		String productId = request.getParameter("productId");
		List<ProductBasicForm> ajaxList = new ArrayList<ProductBasicForm>();
		try {

			if (null != catId) {

				CategoryTbMaster category = new CategoryTbMaster();
				category.setNmCtgryId(Long.parseLong(catId));
				ajaxList = productHandler.viewAllProductByCategoryId(category,
						CommonUtil.getSessionInfo(request), Long.parseLong(productId));

			}

		} catch (GenericFailureException | BusinessFailureException exp) {

			LOG.error(String
					.format("Error occured on Ajax call to get the Products category wise- %s ",
							exp));
		}

		LOG.debug(MEXIT);
		return ajaxList;
	}

	/**
	 * Go to Product Listing Page with all the products details categorywise
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/addOrEditProductPage.htm", method = RequestMethod.POST, params = "viewProductsByCategory")
	public ModelAndView viewProductByCategory(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			HttpServletRequest request) {

		LOG.debug(MENTRY + "public ModelAndView viewProductAdmin()");
		String errorMessage = null;
		ModelAndView modelAndView = null;
		String categoryId = request.getParameter("categoryId");
		try {
			modelAndView = new ModelAndView("/product/view_products_t1");
			modelAndView.addObject("productBasicForm", new ProductBasicForm());
			CategoryTbMaster cat = categoryHandler.getCategory(Long
					.parseLong(categoryId));
			List<ProductBasicForm> productList = productHandler
					.viewAllProductByCategoryId(cat, CommonUtil.getSessionInfo(request), 0);
			modelAndView.addObject("productList", productList);
			modelAndView.addObject("formSearch", new FormSearch());
			modelAndView.addObject("productBasicForm", productBasicForm);
			modelAndView.addObject("allStatuses", getAllStatus());
			modelAndView.addObject("hiddenStatus", "1");

			if (productList.size() == 0) {
				modelAndView.addObject("listError", NO_PRODUCT);
			}
		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String.format(
					"Error occured while getting the Product listing - %s ",
					exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * View Add / Edit Product page
	 * 
	 * @param productBasicForm
	 * @param bindingResult
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/addOrEditProductPage.htm", method = RequestMethod.POST, params = "addOrUpdateBasicProduct")
	public ModelAndView addProductPage(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug(MENTRY + "");
		String errorMessage = null;
		String catId = request.getParameter("categoryId");
		String catName = null;

		ModelAndView modelAndView = null;
		try {

			sValidator.validate(productBasicForm, bindingResult);

			if (bindingResult.hasErrors()) {

				LOG.debug("An user has not selected category - validation error."
						+ productBasicForm);

				SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
				Map<Long, String> categoryTree = productHandler
						.getCategoriesHierarchyTree(sessionInfo);
				modelAndView = new ModelAndView("/product/select_category");
				modelAndView.addObject("treeMap", categoryTree);
				modelAndView.addObject("productBasicForm", productBasicForm);

				return modelAndView;

			}
			catName = categoryHandler.getCategory(Long.parseLong(catId))
					.getVcCtgryNm();
			CategoryTbMaster cat = new CategoryTbMaster();
			cat.setNmCtgryId(Long.parseLong(productBasicForm.getCategoryId()));
			List<CategorySectionAttributeVo> listVariant = productHandler
					.viewAllAttributeByCategoryId("categoryTbMaster", cat, "1");

			List<CategorySectionAttributeVo> listProduct = productHandler
					.viewAllAttributeByCategoryId("categoryTbMaster", cat, "0");
			if (listProduct.size() < 1 && listVariant.size() < 1) {

				SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
				Map<Long, String> categoryTree = productHandler
						.getCategoriesHierarchyTree(sessionInfo);
				if (StringUtils.equalsIgnoreCase(productBasicForm.getMode(),
						CATEGORY_MODE)) {

					modelAndView = new ModelAndView("/product/select_category");
				} else {

					modelAndView = new ModelAndView("/product/select_category");
				}
				modelAndView.addObject("treeMap", categoryTree);
				modelAndView.addObject("productBasicForm", productBasicForm);
				modelAndView.addObject("errorMessage", NO_PRODUCT_ATT_MSG);
				return modelAndView;

			}
			modelAndView = new ModelAndView("/product/add_product");
			modelAndView.addObject("weightList", WEIGHT.values());
			modelAndView.addObject("heightList", HEIGHT.values());
			modelAndView.addObject("widthList", WIDTH.values());
			modelAndView.addObject("lengthList", LENGTH.values());
			modelAndView.addObject("liquidList", LIQUID.values());
			modelAndView.addObject("sizeList", SIZE.values());
			modelAndView.addObject("allStatuses", getAllStatus());
			productBasicForm.setCategoryId(catId);
			modelAndView.addObject("productBasicForm", productBasicForm);
			modelAndView.addObject("catName", catName);
			modelAndView.addObject("listVariant", listVariant);
		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while getting the Product Basic Details with category : - %s ",
							catName, exp));
		}
		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * view Attribute management page and also save data of Add Product page.
	 * 
	 * @param productBasicForm
	 * @param bindingResult
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/addOrEditProductPage.htm", method = RequestMethod.POST, params = "addOrUpdateProductAttributes")
	public ModelAndView registerProductPage1(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "public ModelAndView registerProductPage1("
				+ "@ModelAttribute('productBasicForm') ProductBasicForm productBasicForm,"
				+ "Model model, BindingResult bindingResult, HttpServletRequest request)");

		ModelAndView modelAndView = null;
		List<CategorySectionAttributeVo> list = null;

		Map<Long, List<CategorySectionAttributeVo>> catSectionAttributeTree = null;

		String catName = request.getParameter("categoryName");

		String errorMessage = null;
		ProductTbMaster product = null;
		try {
			product = productHandler.getProductById(productBasicForm
					.getProductID());
			if (StringUtils.equalsIgnoreCase(productBasicForm.getMode(),
					ADD_MODE)) {

				ProductTbMaster prod = productHandler
						.viewAllProductsByName(productBasicForm);
				productBasicForm.setIsVariantAdded(false);
				if (null != prod) {
					productBasicForm.setErrorMessage(EXISTS);
				}
			}

			CategoryTbMaster cat = new CategoryTbMaster();
			cat.setNmCtgryId(Long.parseLong(productBasicForm.getCategoryId()));
			List<CategorySectionAttributeVo> listVariant = productHandler
					.viewAllAttributeByCategoryId("categoryTbMaster", cat, "1");
			if (listVariant.size() > 0) {
				productBasicForm.setSalePrice("10");
				productBasicForm.setDealPrice("10");
				productBasicForm.setQuantity("10");

			}
			if (StringUtils.equalsIgnoreCase(productBasicForm.getMode(),
					EDIT_MODE)) {

				if (StringUtils.isBlank(product.getVcPrdImg1Pth())
						&& StringUtils.isBlank(product.getVcPrdImg1Pth())
						&& StringUtils.isBlank(product.getVcPrdImg1Pth())
						&& StringUtils.isBlank(product.getVcPrdImg1Pth())) {
					productBasicForm.setIsAllImageDeleted(true);
				}else{
					productBasicForm.setIsAllImageDeleted(false);
				}
			}else{
				productBasicForm.setIsAllImageDeleted(false);
			}

			pValidator.validate(productBasicForm, bindingResult);

			if (bindingResult.hasErrors()) {

				LOG.debug("An user is registering Product , has validation errors."
						+ productBasicForm);

				modelAndView = new ModelAndView("/product/add_product");
				modelAndView.addObject("catName", catName);
				modelAndView.addObject("productBasicForm", productBasicForm);
				modelAndView.addObject("listVariant", listVariant);
				modelAndView.addObject("allStatuses", getAllStatus());
				return modelAndView;

			}
			if (listVariant.size() > 0) {
				productBasicForm.setSalePrice(BLANK);
				productBasicForm.setDealPrice(BLANK);
				productBasicForm.setQuantity(BLANK);
				productBasicForm.setStatus("");
			}
			if (StringUtils.equalsIgnoreCase(productBasicForm.getMode(),
					ADD_MODE)) {
				productBasicForm.setStoreId(CommonUtil.getSessionInfo(request)
						.getStoreId());
				productBasicForm.setCreatedBy(CommonUtil.getSessionInfo(request)
						.getUserId());
				product = productHandler.registerProduct(productBasicForm,
						new ProductTbMaster());

				productBasicForm = productHandler.setProductVO(
						productBasicForm, product);
			}

			modelAndView = new ModelAndView("/product/add_attribute_management");

			if (StringUtils.equalsIgnoreCase(productBasicForm.getMode(),
					EDIT_MODE)) {

				productBasicForm.setUpdatedBy(CommonUtil.getSessionInfo(request)
						.getUserId());
				product = productHandler.registerProduct(productBasicForm,
						product);
				productBasicForm = productHandler.setProductVO(
						productBasicForm, product);

			}
			list = productHandler.viewAllAttributeByCategoryId(
					"categoryTbMaster", product.getCategoryTbMaster(), "0");

			if (null != list) {

				catSectionAttributeTree = new HashMap<Long, List<CategorySectionAttributeVo>>();
				for (CategorySectionAttributeVo categorySectionAttribute : list) {

					List<CategorySectionAttributeVo> childCatSectionList = computeCategoriesSection(
							categorySectionAttribute, list);

					if (childCatSectionList.size() > 0) {
						catSectionAttributeTree.put(new Long(
								categorySectionAttribute.getSectionId()),
								childCatSectionList);
					} else {
						catSectionAttributeTree.put(new Long(
								categorySectionAttribute.getSectionId()), null);
					}

				}
			}

			modelAndView.addObject("weightList", WEIGHT.values());
			modelAndView.addObject("heightList", HEIGHT.values());
			modelAndView.addObject("widthList", WIDTH.values());
			modelAndView.addObject("lengthList", LENGTH.values());
			modelAndView.addObject("liquidList", LIQUID.values());
			modelAndView.addObject("sizeList", SIZE.values());

			modelAndView.addObject("catSectionAttributeTree",
					catSectionAttributeTree);
			modelAndView.addObject("productBasicForm", productBasicForm);

		} catch (GenericFailureException | BusinessFailureException
				| ParseException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while saving the Product Basic Details with category : - %s ",
							catName, exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * view Attribute management page and also save data of Add Product page
	 * while skipping submit on Add Product page.
	 * 
	 * @param productBasicForm
	 * @param bindingResult
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/addOrEditProductPage.htm", method = RequestMethod.POST, params = "viewProductAttributesOnEdit")
	public ModelAndView viewProductAttributesOnEdit(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			BindingResult bindingResult, HttpServletRequest request) {

		ModelAndView modelAndView = null;
		List<CategorySectionAttributeVo> list = null;

		Map<Long, List<CategorySectionAttributeVo>> catSectionAttributeTree = null;

		String catName = request.getParameter("categoryName");

		String errorMessage = null;
		ProductTbMaster product = null;
		try {
			product = productHandler.getProductById(productBasicForm
					.getProductID());
			productBasicForm = productHandler.setProductVO(productBasicForm,
					product);
			CategoryTbMaster cat = new CategoryTbMaster();
			cat.setNmCtgryId(Long.parseLong(productBasicForm.getCategoryId()));
			List<CategorySectionAttributeVo> listVariant = productHandler
					.viewAllAttributeByCategoryId("categoryTbMaster", cat, "1");

			modelAndView = new ModelAndView("/product/add_attribute_management");

			list = productHandler.viewAllAttributeByCategoryId(
					"categoryTbMaster", product.getCategoryTbMaster(), "0");

			if (null != list) {

				catSectionAttributeTree = new HashMap<Long, List<CategorySectionAttributeVo>>();
				for (CategorySectionAttributeVo categorySectionAttribute : list) {

					List<CategorySectionAttributeVo> childCatSectionList = computeCategoriesSection(
							categorySectionAttribute, list);

					if (childCatSectionList.size() > 0) {
						catSectionAttributeTree.put(new Long(
								categorySectionAttribute.getSectionId()),
								childCatSectionList);
					} else {
						catSectionAttributeTree.put(new Long(
								categorySectionAttribute.getSectionId()), null);
					}

				}
			}

			modelAndView.addObject("weightList", WEIGHT.values());
			modelAndView.addObject("heightList", HEIGHT.values());
			modelAndView.addObject("widthList", WIDTH.values());
			modelAndView.addObject("lengthList", LENGTH.values());
			modelAndView.addObject("liquidList", LIQUID.values());
			modelAndView.addObject("sizeList", SIZE.values());

			modelAndView.addObject("catSectionAttributeTree",
					catSectionAttributeTree);
			modelAndView.addObject("productBasicForm", productBasicForm);

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while saving the Product Basic Details with category : - %s ",
							catName, exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * view Add related Products page and also save data of Attribute management
	 * page.
	 * 
	 * @param productBasicForm
	 * @param model
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "admin/addOrEditProductPage.htm", method = RequestMethod.POST, params = "addOrUpdateRelatedProducts")
	public ModelAndView registerProductPage2(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			Model model, BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "public ModelAndView registerProductPage2("
				+ "@ModelAttribute('productBasicForm') ProductBasicForm productBasicForm,"
				+ "Model model, BindingResult bindingResult, HttpServletRequest request)");

		ModelAndView modelAndView = null;
		String errorMessage = null;
		try {
			String allImageDeleted = request.getParameter("allImageDeleted");
			SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
			Map<Long, String> categoryTree = productHandler
					.getCategoriesHierarchyTree(sessionInfo);
			ProductTbMaster product = productHandler
					.getProductById(productBasicForm.getProductID());
			List<RelatedProductForm> relatedProductList = productHandler
					.viewAllRelatedProductByProduct(product);
			productBasicForm.setIsCompletionStatus("S");
			productBasicForm.setCreatedBy(sessionInfo.getUserId());
			productBasicForm.setUpdatedBy(sessionInfo.getUserId());
			
			product = productHandler.registerProductAttributes(product,
					productBasicForm);

			productHandler.setProductVO(productBasicForm, product);
			modelAndView = new ModelAndView("/product/add_related_product");
			modelAndView.addObject("productBasicForm", productBasicForm);
			RelatedProductForm relatedProductForm = new RelatedProductForm();
			relatedProductForm.setProductId(productBasicForm.getProductID());
			relatedProductForm.setMode(productBasicForm.getMode());
			modelAndView.addObject("relatedProductList", relatedProductList);
			modelAndView.addObject("treeMap", categoryTree);
			modelAndView.addObject("relatedProductForm", relatedProductForm);

		} catch (GenericFailureException | BusinessFailureException
				| ParseException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while saving the Product Attributes  Details or fetching related products with productid : - %s ",
							productBasicForm.getProductID(), exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * view Add related Products page while skipping submit on Add Attribute
	 * Management page page.
	 * 
	 * @param productBasicForm
	 * @param model
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "admin/addOrEditProductPage.htm", method = RequestMethod.POST, params = "viewRelatedProductsOnEdit")
	public ModelAndView viewRelatedProductsOnEdit(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			Model model, BindingResult bindingResult, HttpServletRequest request) {

		ModelAndView modelAndView = null;
		String errorMessage = null;
		try {
			SessionInfo sessionInfo = CommonUtil.getSessionInfo(request);
			Map<Long, String> categoryTree = productHandler
					.getCategoriesHierarchyTree(sessionInfo);
			ProductTbMaster product = productHandler
					.getProductById(productBasicForm.getProductID());
			List<RelatedProductForm> relatedProductList = productHandler
					.viewAllRelatedProductByProduct(product);

			productHandler.setProductVO(productBasicForm, product);
			modelAndView = new ModelAndView("/product/add_related_product");
			modelAndView.addObject("productBasicForm", productBasicForm);
			RelatedProductForm relatedProductForm = new RelatedProductForm();
			relatedProductForm.setProductId(productBasicForm.getProductID());
			relatedProductForm.setMode(productBasicForm.getMode());
			modelAndView.addObject("relatedProductList", relatedProductList);
			modelAndView.addObject("treeMap", categoryTree);
			modelAndView.addObject("relatedProductForm", relatedProductForm);

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while saving the Product Attributes  Details or fetching related products with productid : - %s ",
							productBasicForm.getProductID(), exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * view Add Similar Product page and also save data of Add related products
	 * page.
	 * 
	 * @param relatedProductForm
	 * 
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/addOrEditProductPage.htm", method = RequestMethod.POST, params = "addOrUpdateSimilarProducts")
	public ModelAndView registerProductPage3(
			@ModelAttribute("relatedProductForm") RelatedProductForm relatedProductForm,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "public ModelAndView registerProductPage3("
				+ "@ModelAttribute('relatedProductForm') RelatedProductForm relatedProductForm,"
				+ "Model model, BindingResult bindingResult, HttpServletRequest request)");

		ModelAndView modelAndView = null;
		ProductTbMaster product = null;
		String errorMessage = null;
		List<ProductBasicForm> productList = null;
		String allSelected = request.getParameter("allRelatedProducts");
		try {

			product = productHandler.getProductById(relatedProductForm
					.getProductId());

			if (StringUtils.isNotBlank(allSelected)) {

				String allRelatedProdValues[] = allSelected.split(",");
				for (int i = 0; i < allRelatedProdValues.length; i++) {
					RelatedProductForm relatedProductFrm = new RelatedProductForm();
					relatedProductFrm.setProductId(relatedProductForm
							.getProductId());
					relatedProductFrm.setRelatedProductId(Long
							.parseLong(allRelatedProdValues[i]));
					productHandler.registerRelatedProducts(relatedProductFrm);
				}

				productHandler.manageRelatedProduts(allRelatedProdValues,
						product);
			}
			CategoryTbMaster category = new CategoryTbMaster();
			category.setNmCtgryId(product.getCategoryTbMaster().getNmCtgryId());
			productList = productHandler.viewAllProductByCategoryId(category,
					CommonUtil.getSessionInfo(request), relatedProductForm.getProductId());

			ProductTbMaster productTbMaster1 = new ProductTbMaster();
			productTbMaster1.setNmPrdId(relatedProductForm.getProductId());
			List<SimilarProductForm> similarProductList = productHandler
					.viewAllSimilarProductByProductId(productTbMaster1);

			modelAndView = new ModelAndView("/product/add_similar_product");
			SimilarProductForm similarProductForm = new SimilarProductForm();
			similarProductForm.setProductId(relatedProductForm.getProductId());
			similarProductForm.setMode(relatedProductForm.getMode());
			modelAndView.addObject("similarProductForm", similarProductForm);
			modelAndView.addObject("productList", productList);
			modelAndView.addObject("similarProductList", similarProductList);

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while saving the Related Products s or fetching similar products with productid : - %s ",
							product.getNmPrdId(), exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * view Add Similar Product page while skipping submit on Related Products
	 * page page.
	 * 
	 * @param relatedProductForm
	 * 
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/addOrEditProductPage.htm", method = RequestMethod.POST, params = "viewSimilarProductsOnEdit")
	public ModelAndView viewSimilarProductsOnEdit(
			@ModelAttribute("relatedProductForm") RelatedProductForm relatedProductForm,
			BindingResult bindingResult, HttpServletRequest request) {

		ModelAndView modelAndView = null;
		ProductTbMaster product = null;
		String errorMessage = null;
		List<ProductBasicForm> productList = null;

		try {

			product = productHandler.getProductById(relatedProductForm
					.getProductId());

			CategoryTbMaster category = new CategoryTbMaster();
			category.setNmCtgryId(product.getCategoryTbMaster().getNmCtgryId());
			productList = productHandler.viewAllProductByCategoryId(category,
					CommonUtil.getSessionInfo(request), relatedProductForm.getProductId());

			ProductTbMaster productTbMaster1 = new ProductTbMaster();
			productTbMaster1.setNmPrdId(relatedProductForm.getProductId());
			List<SimilarProductForm> similarProductList = productHandler
					.viewAllSimilarProductByProductId(productTbMaster1);

			modelAndView = new ModelAndView("/product/add_similar_product");
			SimilarProductForm similarProductForm = new SimilarProductForm();
			similarProductForm.setProductId(relatedProductForm.getProductId());
			modelAndView.addObject("similarProductForm", similarProductForm);
			modelAndView.addObject("productList", productList);
			modelAndView.addObject("similarProductList", similarProductList);

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while saving the Related Products s or fetching similar products with productid : - %s ",
							product.getNmPrdId(), exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * View Product listing page and also save data of Similar Products page.
	 * 
	 * @param similarProductForm
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/addOrEditProductPage.htm", method = RequestMethod.POST, params = "goToListing")
	public ModelAndView registerProductPage4(
			@ModelAttribute("similarProductForm") SimilarProductForm similarProductForm,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "public ModelAndView registerProductPage4("
				+ "@ModelAttribute('similarProductForm') SimilarProductForm similarProductForm,"
				+ "Model model, BindingResult bindingResult, HttpServletRequest request)");

		ModelAndView modelAndView = null;
		String errorMessage = null;
		ProductTbMaster product = null;
		try {

			product = productHandler.getProductById(similarProductForm
					.getProductId());
			String allSelected = request.getParameter("allSimilarProducts");

			if (StringUtils.isNotBlank(allSelected)) {
				String allSimilarProdValues[] = allSelected.split(",");
				for (int i = 0; i < allSimilarProdValues.length; i++) {
					SimilarProductForm similarProductFrm = new SimilarProductForm();
					similarProductFrm.setProductId(similarProductForm
							.getProductId());
					similarProductFrm.setSimilarProductId(Long
							.parseLong(allSimilarProdValues[i]));
					productHandler.registerSimilarProducts(similarProductFrm);
				}

				productHandler.manageSimilarProduts(allSimilarProdValues,
						product);
			}

			modelAndView = goToProductListingPage(modelAndView, request);

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while saving the Similar Products s or fetching product listing with productid : - %s ",
							product.getNmPrdId(), exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * Handles all cancel operations for Add / Edit products
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/addOrEditProductPage.htm", method = RequestMethod.POST, params = "cancelProduct")
	public ModelAndView cancelProductAddOrEdit(HttpServletRequest request) {

		LOG.debug(MENTRY + "public ModelAndView cancelProductAddOrEdit()");

		ModelAndView modelAndView = null;
		String errorMessage = null;
		try {

			modelAndView = goToProductListingPage(modelAndView, request);
			// modelAndView = new
			// ModelAndView("redirect:manageProducts.htm?filterProducts");

		} catch (GenericFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while fetching products listing after cancel action with productid : - %s ",
							exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * Handles soft Delete operation on product
	 * 
	 * @param productBasicForm
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "admin/manageProducts.htm", method = RequestMethod.POST, params = "deleteProduct")
	public ModelAndView deleteProduct(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "public ModelAndView deleteProduct( "
				+ " @ModelAttribute('productBasicForm') ProductBasicForm productBasicForm,"
				+ "Model model, BindingResult bindingResult,HttpServletRequest request)");

		ModelAndView modelAndView = null;
		String errorMessage = null;
		try {
			productBasicForm.setMode(DELETE_MODE);
			ProductTbMaster product = productHandler
					.viewProductById(productBasicForm.getProductID());
			productHandler.deleteProduct(product);

			/* if list is empty then control should go to product listing page */
			modelAndView = goToProductListingPage(modelAndView, request);
			modelAndView.addObject("deleteProduct", DELETE_PRODUCT);
			modelAndView.addObject("allStatuses", getAllStatus());

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while deleting  product with productid : - %s ",
							productBasicForm.getProductID(), exp));
		}

		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * Returns Category Attributes Section Wise
	 * 
	 * @param parentCatSection
	 * @param categorySectionAttributeList
	 * @return
	 */
	private List<CategorySectionAttributeVo> computeCategoriesSection(
			CategorySectionAttributeVo parentCatSection,
			List<CategorySectionAttributeVo> categorySectionAttributeList) {

		LOG.debug(MENTRY
				+ "private List<CategorySectionAttributeVo> computeCategoriesSection("
				+ "CategorySectionAttributeVo parentCatSection,"
				+ "List<CategorySectionAttributeVo> categorySectionAttributeList)");

		List<CategorySectionAttributeVo> childCatSectionList = new ArrayList<>();

		for (CategorySectionAttributeVo categorySection : categorySectionAttributeList) {
			if (parentCatSection.getSectionId() == categorySection
					.getSectionId()) {
				childCatSectionList.add(categorySection);
			}
		}

		LOG.debug(MEXIT);
		return childCatSectionList;

	}

	/**
	 * View Edit product page
	 * 
	 * @param productBasicForm
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "admin/manageProducts.htm", method = RequestMethod.POST, params = "editProduct")
	public ModelAndView editProductPage(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "public ModelAndView editProductPage( "
				+ " @ModelAttribute('productBasicForm') ProductBasicForm productBasicForm,"
				+ "Model model, BindingResult bindingResult)");

		ModelAndView modelAndView = null;
		String errorMessage = null;

		try {
			long productId = Long.parseLong(request.getParameter("productID"));
			modelAndView = new ModelAndView("/product/add_product");
			modelAndView.addObject("productVariantForm",
					new ProductVariantForm());
			productBasicForm.setMode(EDIT_MODE);
			ProductTbMaster product = productHandler.viewProductById(productId);
			String catName = product.getCategoryTbMaster().getVcCtgryNm();
			CategoryTbMaster cat = new CategoryTbMaster();
			cat.setNmCtgryId(product.getCategoryTbMaster().getNmCtgryId());
			List<CategorySectionAttributeVo> listVariant = productHandler
					.viewAllAttributeByCategoryId("categoryTbMaster", cat, "1");
			List<ProductVariantTbDtl> productVariantTbDtls = product
					.getProductVariantTbDtls();
			productBasicForm = productHandler.setProductVO(productBasicForm,
					product);
			modelAndView.addObject("allStatuses", getAllStatus());
			modelAndView.addObject("productBasicForm", productBasicForm);
			modelAndView.addObject("catName", catName);
			modelAndView.addObject("listVariant", listVariant);
			modelAndView
					.addObject("productVariantTbDtls", productVariantTbDtls);
			modelAndView.addObject("allStatuses", getAllStatus());
		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while updating  product with productid : - %s ",
							productBasicForm.getProductID(), exp));
		}
		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * Shows list of variants for the selected product.
	 * 
	 * @param productBasicForm
	 * @param model
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "admin/manageProducts.htm", method = RequestMethod.POST, params = "showVariantList")
	public ModelAndView manageVariantAttributes(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "public ModelAndView manageVariantAttributes( "
				+ " @ModelAttribute('productBasicForm') ProductBasicForm productBasicForm,"
				+ "Model model, BindingResult bindingResult)");

		ModelAndView modelAndView = null;
		String errorMessage = null;
		List<CategorySectionAttributeVo> attributeList = null;
		long productId = Long.parseLong(request.getParameter("productID"));

		try {
			modelAndView = new ModelAndView("/product/managevariants");
			ProductTbMaster product = productHandler.viewProductById(productId);

			CategoryTbMaster category = product.getCategoryTbMaster();
			attributeList = productHandler.viewAllAttributeByCategoryId(
					"categoryTbMaster", category, "1");
			if (attributeList.size() < 1) {

				/*
				 * if list is empty then control should go to product listing
				 * page
				 */
				modelAndView = goToProductListingPage(modelAndView, request);
				modelAndView.addObject("errorMessage", NO_VARIANT_ATT_MSG);
				return modelAndView;

			}
			ProductBasicForm frm = new ProductBasicForm();
			frm = productHandler.setProductVO(frm, product);
			List<ProductVariantForm> variantList = productHandler
					.viewAllVariantAttributes(product);

			ProductVariantForm productVariantForm = new ProductVariantForm();
			productVariantForm.setProductId(frm.getProductID());
			modelAndView.addObject("variantList", variantList);
			modelAndView.addObject("attributeList", attributeList);
			modelAndView.addObject("productVariantForm", productVariantForm);
			modelAndView.addObject("allStatuses", getAllStatus());

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while showing variant listing with productid : - %s ",
							productBasicForm.getProductID(), exp));
		}
		LOG.debug(MEXIT);
		return modelAndView;
	}

	/**
	 * Ajax Call This method deletes stored image path of a image attribute in
	 * productVariantTbDtl table . If image is deleted on UI without saving
	 * Variant details.
	 * 
	 * @param request
	 * @return Updated ProductVariantForm.
	 */
	@RequestMapping(value = "admin/deleteImageData.htm", method = RequestMethod.GET)
	public @ResponseBody
	ProductVariantForm deleteImageData(HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "public @ResponseBody ProductVariantForm deleteImageData(HttpServletRequest request)");

		String id = request.getParameter("imgNum");
		String variantId = request.getParameter("id");
		ProductVariantForm frm = null;
		ProductVariantForm frmAfterDelete = null;
		try {
			frm = productHandler.getVariantById(Long.parseLong(variantId));
			if (null != frm) {
				frm.setNmVrntId(Long.parseLong(variantId));
				if (StringUtils.equals(id, "1")) {
					frm.setVcVrntImg1("");
				} else if (StringUtils.equals(id, "2")) {
					frm.setVcVrntImg2("");
				} else if (StringUtils.equals(id, "3")) {
					frm.setVcVrntImg3("");
				}

				frm.setMode(EDIT_MODE);

				productHandler.addVariantAttributes(frm);
				
				
				
				frmAfterDelete = productHandler.getVariantById(Long.parseLong(variantId));
				frmAfterDelete.setMode(EDIT_MODE);
				
				if (StringUtils.equalsIgnoreCase(frm.getMode(), EDIT_MODE) && StringUtils.isBlank(frmAfterDelete.getVcVrntImg1())
						&& StringUtils.isBlank(frmAfterDelete.getVcVrntImg2())
						&& StringUtils.isBlank(frmAfterDelete.getVcVrntImg3())
					) {
					
					frmAfterDelete.setVcStts(PRODUCT_STATUS.InActive.name());
					
					productHandler.addVariantAttributes(frmAfterDelete);
				}
				
				
				
			}
		} catch (GenericFailureException | BusinessFailureException exp) {

			LOG.error(String
					.format("Error occured while updating variant image attributes value on database if image is deleted from UI with productid : - %s ",
							exp));
		}
		LOG.debug(MEXIT);
		return frm;
	}

	/**
	 * Ajax Call This method deletes stored image path of a image attribute in
	 * productTbmaster table . If image is deleted on UI without saving products
	 * details.
	 * 
	 * @param request
	 * @return updated ProductBasicForm
	 */
	@RequestMapping(value = "admin/deleteImageDataForProduct.htm", method = RequestMethod.GET)
	public @ResponseBody
	ProductBasicForm deleteImageDataForProduct(HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "public @ResponseBody ProductBasicForm deleteImageDataForProduct(HttpServletRequest request)");

		String id = request.getParameter("imgNum");
		String productId = request.getParameter("id");
		ProductBasicForm frm = new ProductBasicForm();
		String errorMessage = null;
		try {

			ProductTbMaster product = productHandler.viewProductById(Long
					.parseLong(productId));
			productHandler.setProductVO(frm, product);
			if (StringUtils.equals(id, "1")) {
				frm.setProdImgPath1("");
			} else if (StringUtils.equals(id, "2")) {
				frm.setProdImgPath2("");
			} else if (StringUtils.equals(id, "3")) {
				frm.setProdImgPath3("");
			} else if (StringUtils.equals(id, "4")) {
				frm.setProdImgPath4("");
			}
			frm.setMode(EDIT_MODE);
			
			
			ProductTbMaster productAfterImageDelete = productHandler
					.registerProductAttributes(product, frm);
			
			ProductTbMaster productAfterDelete = productHandler.viewProductById(Long
					.parseLong(productId)); 
			
			if (StringUtils.equalsIgnoreCase(frm.getMode(), EDIT_MODE) && StringUtils.isBlank(productAfterDelete.getVcPrdImg1Pth())
					&& StringUtils.isBlank(productAfterDelete.getVcPrdImg1Pth())
					&& StringUtils.isBlank(productAfterDelete.getVcPrdImg1Pth())
					&& StringUtils.isBlank(productAfterDelete.getVcPrdImg1Pth())) {
				
				
				List<ProductTbMaster> prod = new ArrayList<ProductTbMaster>();
				prod.add(productAfterDelete);
				productHandler.setProductStatus(prod, PRODUCT_STATUS.InActive.name());
			}
			
			frm.setProdImgPath1(productAfterImageDelete.getVcPrdImg1Pth());
			frm.setProdImgPath2(productAfterImageDelete.getVcPrdImg2Pth());
			frm.setProdImgPath3(productAfterImageDelete.getVcPrdImg3Pth());
			frm.setProdImgPath4(productAfterImageDelete.getVcPrdImg4Pth());
		} catch (GenericFailureException | BusinessFailureException
				| ParseException exp) {

			LOG.error(String
					.format("Error occured while updating product image attributes value on database if image is deleted from UI with productid : - %s ",
							exp));
		}
		LOG.debug(MEXIT);
		return null;
	}

	/**
	 * Handles Edit Variant Screen.
	 * 
	 * @param productVariantForm
	 * @param bindingResult
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/manageVariants.htm", method = RequestMethod.POST, params = "editVariants")
	public ModelAndView editVariants(
			@ModelAttribute("productVariantForm") ProductVariantForm productVariantForm,
			BindingResult bindingResult) {

		LOG.debug(MENTRY
				+ "public ModelAndView editVariants( "
				+ "@ModelAttribute('ProductVariantForm') ProductVariantForm productVariantForm,"
				+ "Model model, BindingResult bindingResult)");

		ModelAndView modelAndView = null;
		String errorMessage = null;
		ProductTbMaster product = null;
		try {
			product = productHandler.getProductById(productVariantForm
					.getProductId());
			modelAndView = new ModelAndView("/product/managevariants");
			long varaintId = productVariantForm.getNmVrntId();
			productVariantForm = productHandler.getVariantById(varaintId);
			List<ProductVariantForm> variantList = productHandler
					.viewAllVariantAttributes(product);

			CategoryTbMaster category = product.getCategoryTbMaster();
			List<CategorySectionAttributeVo> attributeList = productHandler
					.viewAllAttributeByCategoryId("categoryTbMaster", category,
							"1");

			productVariantForm.setMode(EDIT_MODE);
			modelAndView.addObject("weightList", WEIGHT.values());
			modelAndView.addObject("heightList", HEIGHT.values());
			modelAndView.addObject("widthList", WIDTH.values());
			modelAndView.addObject("lengthList", LENGTH.values());
			modelAndView.addObject("liquidList", LIQUID.values());
			modelAndView.addObject("sizeList", SIZE.values());
			modelAndView.addObject("productVariantForm", productVariantForm);
			modelAndView.addObject("variantList", variantList);
			modelAndView.addObject("attributeList", attributeList);
			modelAndView.addObject("allStatuses", getAllStatus());
			modelAndView.addObject("storeId", product.getStoreTbMaster()

			.getNmStrId());

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while updating variants  with productid : - %s ",
							product.getNmPrdId(), exp));
		}
		LOG.debug(MEXIT);
		return modelAndView;

	}

	/**
	 * Handles Add Variant Action for a specific product .
	 * 
	 * @param productVariantForm
	 * @param bindingResult
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/manageVariants.htm", method = RequestMethod.POST, params = "addVariants")
	public ModelAndView addVariants(
			@ModelAttribute("productVariantForm") ProductVariantForm productVariantForm,
			BindingResult bindingResult) {

		LOG.debug(MENTRY
				+ "public ModelAndView addVariants( "
				+ "@ModelAttribute('ProductVariantForm') ProductVariantForm productVariantForm,"
				+ "Model model, BindingResult bindingResult)");

		ModelAndView modelAndView = null;
		String errorMessage = null;
		ProductTbMaster product = null;
		try {
			product = productHandler.getProductById(productVariantForm
					.getProductId());
			modelAndView = new ModelAndView("/product/managevariants");

			List<ProductVariantForm> variantList = productHandler
					.viewAllVariantAttributes(product);

			CategoryTbMaster category = product.getCategoryTbMaster();
			List<CategorySectionAttributeVo> attributeList = productHandler
					.viewAllAttributeByCategoryId("categoryTbMaster", category,
							"1");

			modelAndView = new ModelAndView("/product/managevariants");
			ProductVariantForm frm = new ProductVariantForm();
			frm.setProductId(product.getNmPrdId());
			frm.setMode(ADD_MODE);
			modelAndView.addObject("weightList", WEIGHT.values());
			modelAndView.addObject("heightList", HEIGHT.values());
			modelAndView.addObject("widthList", WIDTH.values());
			modelAndView.addObject("lengthList", LENGTH.values());
			modelAndView.addObject("liquidList", LIQUID.values());
			modelAndView.addObject("sizeList", SIZE.values());
			modelAndView.addObject("productVariantForm", frm);
			modelAndView.addObject("variantList", variantList);
			modelAndView.addObject("attributeList", attributeList);
			modelAndView.addObject("allStatuses", getAllStatus());
			modelAndView.addObject("storeId", product.getStoreTbMaster().getNmStrId());

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while inserting variants with productid : - %s ",
							product.getNmPrdId(), exp));
		}
		LOG.debug(MEXIT);
		return modelAndView;

	}

	/**
	 * Handles Cancel Variant Action for a specific product .
	 * 
	 * @param productVariantForm
	 * @param bindingResult
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/manageVariants.htm", method = RequestMethod.POST, params = "cancelVariants")
	public ModelAndView cancelVariants(
			@ModelAttribute("productVariantForm") ProductVariantForm productVariantForm,
			BindingResult bindingResult, HttpServletRequest request) {

		LOG.debug(MENTRY
				+ "public ModelAndView cancelVariants( "
				+ "@ModelAttribute('ProductVariantForm') ProductVariantForm productVariantForm,"
				+ "Model model, BindingResult bindingResult)");

		ModelAndView modelAndView = null;
		String errorMessage = null;
		try {

			modelAndView = goToProductListingPage(modelAndView, request);

		} catch (GenericFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while cancelling  variants  details  with productid : - %s ",
							exp));
		}
		LOG.debug(MEXIT);
		return modelAndView;

	}

	/**
	 * Handles Delete Variant Action for a specific product .
	 * 
	 * @param productVariantForm
	 * @param model
	 * @param bindingResult
	 * @return ModelAndView
	 */
	@RequestMapping(value = "admin/manageVariants.htm", method = RequestMethod.POST, params = "deleteVariants")
	public ModelAndView deleteVariants(
			@ModelAttribute("productVariantForm") ProductVariantForm productVariantForm,
			Model model, BindingResult bindingResult) {

		LOG.debug(MENTRY
				+ "public ModelAndView deleteVariants( "
				+ "@ModelAttribute('ProductVariantForm') ProductVariantForm productVariantForm,"
				+ "Model model, BindingResult bindingResult)");

		ModelAndView modelAndView = null;
		String errorMessage = null;
		ProductTbMaster product = null;
		try {
			product = productHandler.getProductById(productVariantForm
					.getProductId());
			modelAndView = new ModelAndView("/product/managevariants");
			long varaintId = productVariantForm.getNmVrntId();
			productVariantForm = productHandler.getVariantById(varaintId);
			productVariantForm.setIsDltd(1);
			productHandler.addVariantAttributes(productVariantForm);

			List<ProductVariantForm> variantList = productHandler
					.viewAllVariantAttributes(product);
			CategoryTbMaster category = product.getCategoryTbMaster();
			List<CategorySectionAttributeVo> attributeList = productHandler
					.viewAllAttributeByCategoryId("categoryTbMaster", category,
							"1");
			/*
			 * if(variantList.size() == 0){
			 * 
			 * List<ProductTbMaster> products = new
			 * ArrayList<ProductTbMaster>(); products.add(product);
			 * productHandler
			 * .setProductStatus(products,PRODUCT_STATUS.InActive.name());
			 * 
			 * }
			 */
			modelAndView.addObject("productVariantForm", productVariantForm);
			modelAndView.addObject("variantList", variantList);
			modelAndView.addObject("attributeList", attributeList);

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while deleteing variant with productid : - %s ",
							product.getNmPrdId(), exp));
		}
		LOG.debug(MEXIT);
		return modelAndView;

	}

	/**
	 * Handles Add/Edit on database for Variant for a specific product .
	 * 
	 * @param productVariantForm
	 * @param model
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "admin/manageVariants.htm", method = RequestMethod.POST, params = "addOrEditVariants")
	public ModelAndView addOrEditVariantAttributes(
			@ModelAttribute("productVariantForm") ProductVariantForm productVariantForm,
			Model model, BindingResult bindingResult) {

		LOG.debug(MENTRY
				+ "public ModelAndView addOrEditVariantAttributes( "
				+ "@ModelAttribute('ProductVariantForm') ProductVariantForm productVariantForm,"
				+ "Model model, BindingResult bindingResult)");

		ModelAndView modelAndView = null;
		List<CategorySectionAttributeVo> attributeList = null;
		String errorMessage = null;
		List<ProductVariantForm> variantList = null;
		ProductTbMaster product = null;
		try {
			String mode = productVariantForm.getMode();
			product = productHandler.getProductById(productVariantForm
					.getProductId());
			CategoryTbMaster category = product.getCategoryTbMaster();

			List<ProductVariantTbDtl> prodVars = productHandler
					.viewAllVariantsByName(productVariantForm);

			if (StringUtils.equalsIgnoreCase(mode, ADD_MODE)) {
				if (prodVars.size() > 0) {
					productVariantForm.setErrorMessage(EXISTS);
				}
				productVariantForm.setIsAllImageDeleted(false);
			} else if (StringUtils.equalsIgnoreCase(mode, EDIT_MODE)) {
				if (prodVars.size() == 1) {
					if (prodVars.get(0).getNmVrntId() != productVariantForm
							.getNmVrntId()) {
						productVariantForm.setErrorMessage(EXISTS);
					}
				}
				
				ProductVariantForm vfrm = productHandler.getVariantById(productVariantForm.getNmVrntId());
				
				if (StringUtils.isBlank(vfrm.getVcVrntImg1())
						&& StringUtils.isBlank(vfrm.getVcVrntImg2())
						&& StringUtils.isBlank(vfrm.getVcVrntImg3())) {
					productVariantForm.setIsAllImageDeleted(true);
				}else{
					productVariantForm.setIsAllImageDeleted(false);
				}
			}
			
			
			modelAndView = new ModelAndView("/product/managevariants");
			pVValidator.validate(productVariantForm, bindingResult);

			if (bindingResult.hasErrors()) {

				LOG.debug("An user has not selected category - validation error."
						+ productVariantForm);

				modelAndView
						.addObject("productVariantForm", productVariantForm);
				variantList = productHandler.viewAllVariantAttributes(product);

				attributeList = productHandler.viewAllAttributeByCategoryId(
						"categoryTbMaster", category, "1");

				modelAndView.addObject("variantList", variantList);
				modelAndView.addObject("attributeList", attributeList);
				modelAndView.addObject("storeId", product.getStoreTbMaster()
						.getNmStrId());
				modelAndView.addObject("weightList", WEIGHT.values());
				modelAndView.addObject("heightList", HEIGHT.values());
				modelAndView.addObject("widthList", WIDTH.values());
				modelAndView.addObject("lengthList", LENGTH.values());
				modelAndView.addObject("liquidList", LIQUID.values());
				modelAndView.addObject("sizeList", SIZE.values());
				modelAndView.addObject("allStatuses", getAllStatus());

				return modelAndView;

			}

			productVariantForm.setIsDltd(0);
			if (StringUtils.equalsIgnoreCase(mode, ADD_MODE)) {
				
				productVariantForm = productHandler
						.addVariantAttributes(productVariantForm);
			} else if (StringUtils.equalsIgnoreCase(mode, EDIT_MODE)) {

				productVariantForm = productHandler
						.addVariantAttributes(productVariantForm);
			}
			productVariantForm.setMode("");
			attributeList = productHandler.viewAllAttributeByCategoryId(
					"categoryTbMaster", category, "1");
			ProductBasicForm frm = new ProductBasicForm();
			frm = productHandler.setProductVO(frm, product);
			variantList = productHandler.viewAllVariantAttributes(product);

			modelAndView.addObject("productVariantForm", productVariantForm);
			modelAndView.addObject("attributeList", attributeList);
			modelAndView.addObject("productBasicForm", frm);
			modelAndView.addObject("variantList", variantList);
			modelAndView.addObject("allStatuses", getAllStatus());

		} catch (GenericFailureException | BusinessFailureException exp) {

			errorMessage = "Error occured : " + exp.getMessage();
			modelAndView.addObject("errorMessage", errorMessage);
			LOG.error(String
					.format("Error occured while adding/updating variants details in database with productid : - %s ",
							product.getNmPrdId(), exp));
		}
		LOG.debug(MEXIT);
		
		return modelAndView;
	}
	
	
	/*
	 * Activate the product
	 */
	@RequestMapping(value = "admin/manageProducts.htm", method = RequestMethod.POST, params = "activateProduct")
	public ModelAndView activateProduct(
			 @ModelAttribute("formSearch") FormSearch formSearch, Locale locale,
				BindingResult bindingResult, HttpServletRequest request){
		String errorMessage = null;
		ModelAndView modelAndView = null;
		try{
		long productId = Long.parseLong(request.getParameter("productID"));
		ProductTbMaster product = productHandler
				.viewProductById(productId);
		if (StringUtils.isBlank(product.getVcPrdImg1Pth())
				&& StringUtils.isBlank(product.getVcPrdImg1Pth())
				&& StringUtils.isBlank(product.getVcPrdImg1Pth())
				&& StringUtils.isBlank(product.getVcPrdImg1Pth())){
			
			errorMessage = messageSource.getMessage("ProductBasicForm.product.noimageadded", null,
					null);
			
		}else{
		List<ProductTbMaster> products = new ArrayList<ProductTbMaster>();
		products.add(product);
		productHandler.setProductStatus(products, PRODUCT_STATUS.Active.name());
		}		
		modelAndView = goToProductListingPage(modelAndView, request);
		modelAndView.addObject("errorMessage", errorMessage);
		}catch(BusinessFailureException exp){
			errorMessage = "Error occured : " + exp.getMessage();
		
			LOG.error(String
					.format("Error occured while changing product status to active :" + errorMessage, exp));
		}
		
		return modelAndView;
	}
	
	
	
	/*
	 * De Activate the product
	 */
	@RequestMapping(value = "admin/manageProducts.htm", method = RequestMethod.POST, params = "deActivateProduct")
	public ModelAndView deActivateProduct(
			@ModelAttribute("productBasicForm") ProductBasicForm productBasicForm,
			BindingResult bindingResult, HttpServletRequest request) {
	
	
		String errorMessage = null;
		ModelAndView modelAndView = null;
		try{
		long productId = Long.parseLong(request.getParameter("productID"));
		ProductTbMaster product = productHandler
				.viewProductById(productId);
		List<ProductTbMaster> products = new ArrayList<ProductTbMaster>();
		products.add(product);
		productHandler.setProductStatus(products, PRODUCT_STATUS.InActive.name());		
		modelAndView = goToProductListingPage(modelAndView, request);
		}catch(BusinessFailureException exp){
			errorMessage = "Error occured : " + exp.getMessage();
		
			LOG.error(String
					.format("Error occured while changing product status to inactive :" + errorMessage, exp));
		}
		
		return modelAndView;
	}
	
	
	
	/*
	 * Activate the product
	 */
	@RequestMapping(value = "admin/manageVariants.htm", method = RequestMethod.POST, params = "activateProduct")
	public ModelAndView activateProduct(
			@ModelAttribute("productVariantForm") ProductVariantForm productVariantForm,
			BindingResult bindingResult) {
		String msgForActive = null;
		ModelAndView modelAndView = null;
		try{
		
		long varaintId = productVariantForm.getNmVrntId();
		ProductVariantTbDtl variant = productHandler.getVarinatById(varaintId);
	
			if (StringUtils.isBlank(variant.getVcVrntImg1())
				&& StringUtils.isBlank(variant.getVcVrntImg2())
				&& StringUtils.isBlank(variant.getVcVrntImg3())){
							
				msgForActive = messageSource.getMessage("ProductVariantForm.variant.noimageadded", null,
					null);
			
		}else{
		List<ProductVariantTbDtl> variants = new ArrayList<ProductVariantTbDtl>();
		variants.add(variant);
		productHandler.setVariantStatus(variants, PRODUCT_STATUS.Active.name());
		}		
	
		productVariantForm = productHandler.getVariantById(varaintId);
		
		ProductTbMaster product = productHandler.getProductById(productVariantForm
				.getProductId());
		List<ProductVariantForm> variantList = productHandler
				.viewAllVariantAttributes(product);

		CategoryTbMaster category = product.getCategoryTbMaster();
		List<CategorySectionAttributeVo> attributeList = productHandler
				.viewAllAttributeByCategoryId("categoryTbMaster", category,
						"1");
		/*modelAndView.addObject("weightList", WEIGHT.values());
		modelAndView.addObject("heightList", HEIGHT.values());
		modelAndView.addObject("widthList", WIDTH.values());
		modelAndView.addObject("lengthList", LENGTH.values());
		modelAndView.addObject("liquidList", LIQUID.values());
		modelAndView.addObject("sizeList", SIZE.values());		
		 */	
		modelAndView = new ModelAndView("/product/managevariants");
		modelAndView.addObject("productVariantForm", productVariantForm);
		modelAndView.addObject("variantList", variantList);
		modelAndView.addObject("attributeList", attributeList);
		modelAndView.addObject("allStatuses", getAllStatus());
		modelAndView.addObject("storeId", product.getStoreTbMaster().getNmStrId());
		modelAndView.addObject("msgForActive", msgForActive);
		}catch(BusinessFailureException exp){
			msgForActive = "Error occured : " + exp.getMessage();
		
			LOG.error(String
					.format("Error occured while changing Variant status to active :" + msgForActive, exp));
		}
		
		return modelAndView;
	}
	
	
	
	/*
	 * De Activate the product  
	 */
	/*
	 * Activate the product
	 */
	@RequestMapping(value = "admin/manageVariants.htm", method = RequestMethod.POST, params = "deActivateProduct")
	public ModelAndView deActivateProduct(
			@ModelAttribute("productVariantForm") ProductVariantForm productVariantForm,
			BindingResult bindingResult,HttpServletRequest request) {
		String errorMessage = null;
		ModelAndView modelAndView = null;
		try{
		
		long varaintId = productVariantForm.getNmVrntId();
		ProductVariantTbDtl variant = productHandler.getVarinatById(varaintId);
		List<ProductVariantTbDtl> variants = new ArrayList<ProductVariantTbDtl>();
		variants.add(variant);
		productHandler.setVariantStatus(variants, PRODUCT_STATUS.InActive.name());
		productVariantForm = productHandler.getVariantById(varaintId);
		ProductTbMaster product = productHandler.getProductById(productVariantForm
				.getProductId());
		List<ProductVariantForm> variantList = productHandler
				.viewAllVariantAttributes(product);

		CategoryTbMaster category = product.getCategoryTbMaster();
		List<CategorySectionAttributeVo> attributeList = productHandler
				.viewAllAttributeByCategoryId("categoryTbMaster", category,
						"1");
		/*modelAndView.addObject("weightList", WEIGHT.values());
		modelAndView.addObject("heightList", HEIGHT.values());
		modelAndView.addObject("widthList", WIDTH.values());
		modelAndView.addObject("lengthList", LENGTH.values());
		modelAndView.addObject("liquidList", LIQUID.values());
		modelAndView.addObject("sizeList", SIZE.values());		
		 */	
		modelAndView = new ModelAndView("/product/managevariants");
		modelAndView.addObject("productVariantForm", productVariantForm);
		modelAndView.addObject("variantList", variantList);
		modelAndView.addObject("attributeList", attributeList);
		modelAndView.addObject("allStatuses", getAllStatus());
		modelAndView.addObject("storeId", product.getStoreTbMaster().getNmStrId());
		modelAndView.addObject("errorMessage", errorMessage);
		}catch(BusinessFailureException exp){
			errorMessage = "Error occured : " + exp.getMessage();
		
			LOG.error(String
					.format("Error occured while changing product status to active :" + errorMessage, exp));
		}
		
		return modelAndView;
	}
		

	private ModelAndView goToProductListingPage(ModelAndView modelAndView,
			HttpServletRequest request) {

		try {

			modelAndView = new ModelAndView("/product/view_products_t1");
			modelAndView.addObject("productBasicForm", new ProductBasicForm());
			modelAndView.addObject("formSearch", new FormSearch());
			List<ProductBasicForm> productList = productHandler
					.viewAllProducts(BLANK, CommonUtil.getSessionInfo(request));
			modelAndView.addObject("productList", productList);
			modelAndView.addObject("allStatuses", getAllStatus());
			modelAndView.addObject("hiddenStatus", "1");
			if (productList.size() == 0) {
				modelAndView.addObject("listError", NO_PRODUCT);
			}

		} catch (Exception exception) {
			LOG.info("Exception::" + exception.getMessage());
		}

		return modelAndView;

	}
	
	
	



	/**
	 * @return the relatedProduct
	 */
	public RelatedProductTbDtl getRelatedProduct() {
		return relatedProduct;
	}

	/**
	 * @param relatedProduct
	 *            the relatedProduct to set
	 */
	public void setRelatedProduct(RelatedProductTbDtl relatedProduct) {
		this.relatedProduct = relatedProduct;
	}

	private HashMap<String, String> getAllStatus() {

		HashMap<String, String> allStatus = new HashMap<String, String>();

		allStatus.put(PRODUCT_STATUS.Active.toString(),
				PRODUCT_STATUS.Active.toString());

		allStatus.put(PRODUCT_STATUS.InActive.toString(),
				PRODUCT_STATUS.InActive.toString());

		return allStatus;
	}

}
