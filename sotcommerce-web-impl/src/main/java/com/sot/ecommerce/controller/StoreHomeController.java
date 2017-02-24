package com.sot.ecommerce.controller;

import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sbsc.fos.category.web.form.AjaxResponse;
import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.persistance.StoreTbMaster;
import com.sbsc.fos.store.web.handler.StoreHomeHandler;
import com.sbsc.fos.store.web.vo.AdvanceSearchForm;
import com.sbsc.fos.store.web.vo.Attribute;
import com.sbsc.fos.store.web.vo.Category;
import com.sbsc.fos.store.web.vo.FacetOutput;
import com.sbsc.fos.store.web.vo.FieldStatistics;
import com.sbsc.fos.store.web.vo.Product;
import com.sbsc.fos.store.web.vo.ProductImagableVariantData;
import com.sbsc.fos.store.web.vo.Review;
import com.sbsc.fos.store.web.vo.Section;
import com.sbsc.fos.store.web.vo.VariantProductData;
import com.sbsc.fos.um.service.StoreService;
import com.sbsc.fos.wishlist.service.WishListService;
import com.sbsc.fos.wishlist.web.form.RedirectForm;

/**
 * Handles requests for the application login page.
 */
@Controller
public class StoreHomeController {

	private static final Logger logger = Logger
			.getLogger(StoreHomeController.class);

	@Autowired
	private StoreHomeHandler storeHomeHandler;

	@Autowired
	private StoreService storeService;

	@Autowired
	private WishListService wishListService;

	@RequestMapping(value = "store/storehome.htm", method = RequestMethod.GET)
	public ModelAndView storeHome(Model model, HttpServletRequest request)
			throws MalformedURLException, SolrServerException {

		saveStoreInfo(request);

		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<Product> hotDealsList = null;

		List<Category> categoryList = null;

		try {

			hotDealsList = storeHomeHandler.getAllProducts();

			categoryList = storeHomeHandler.getAllCategories();

		} catch (GenericFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelMap.put("hotDealsList", hotDealsList);

		modelMap.put("categoryList", categoryList);

		return new ModelAndView("store/store_home", modelMap);
	}

	private void saveStoreInfo(HttpServletRequest request) {
		List<StoreTbMaster> stores = storeService.findAll();
		String url = request.getRequestURI();
		Iterator<StoreTbMaster> storeItr = stores.iterator();
		while (storeItr.hasNext()) {
			StoreTbMaster store = storeItr.next();
			String vcUrl = store.getVcUrl();

			if (url.contains(vcUrl)) {
				long storeId = store.getNmStrId();
				request.getSession().setAttribute("storeId", storeId);

			}

		}
	}

	@RequestMapping(value = "store/filterProductByFacet.htm")
	public @ResponseBody
	AjaxResponse filterProductByFacetResult(HttpServletRequest request)
			throws GenericFailureException, MalformedURLException,
			BusinessFailureException, SolrServerException {

		System.out.println("data---->>>>" + request.getParameter("data"));
		String data = request.getParameter("data");
		FacetOutput output = storeHomeHandler.getAllFacets(0L, data);
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setModel(output);

		return ajaxResponse;

	}

	@RequestMapping(value = "store/prodbycat.htm", method = RequestMethod.GET)
	public ModelAndView viewProductByCategoryOnLeftMenu(Model model,
			HttpServletRequest req) throws MalformedURLException,
			SolrServerException {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<Category> categoryList = new ArrayList<Category>();

		List<Category> leftMenuCategoryTreeList = new ArrayList<Category>();

		List<Product> productList = new ArrayList<Product>();

		List<Category> breadCrumbCategoryList = null;

		Long categoryId = null;

		LinkedHashMap facetMap = null;

		String searchRef = "menu";

		FacetOutput facetOutput = null;

		try {

			categoryId = Long.valueOf(req.getParameter("cat_id").toString());

			breadCrumbCategoryList = storeHomeHandler
					.getAllCategoriesInCategoryPath(categoryId);

			// Get tree for selected Category from solr
			leftMenuCategoryTreeList = storeHomeHandler
					.getLeftMenuCategoryTree(categoryId);

			// Added code for facets
			facetOutput = storeHomeHandler.getAllFacets(categoryId, null);

			// Get Product list for selected category
			productList = storeHomeHandler.getProductList(categoryId);
			// Top menu
			categoryList = storeHomeHandler.getAllCategories();

		} catch (GenericFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelMap.put("leftMenuCategoryTreeList", leftMenuCategoryTreeList);

		modelMap.put("breadCrumbCategoryList", breadCrumbCategoryList);

		modelMap.put("categoryList", categoryList);

		modelMap.put("productList", facetOutput.getProductList());

		modelMap.put("searchRef", searchRef);

		modelMap.put("facetVOs", facetOutput.getFacetVOList());

		return new ModelAndView("store/products_list_by_search_result",
				modelMap);
	}

	@RequestMapping(value = "store/searchProduct.htm", method = RequestMethod.GET)
	public ModelAndView searchProduct(Model model, HttpServletRequest req)
			throws MalformedURLException, GenericFailureException,
			BusinessFailureException, SolrServerException {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		LinkedHashMap<Category, List<Product>> searchResult = null;

		List<Category> leftMenuCategoryTreeList = null;

		List<Category> categoryList = null;

		List<Category> breadCrumbCategoryList = null;

		List<Product> productList = null;

		String searchRef = null;

		String searchTerm = null;

		if (req.getParameter("searchbox") != null) {
			searchTerm = req.getParameter("searchbox");
			searchRef = "searchbox";
		} else {
			searchTerm = req.getParameter("search");
			searchRef = "menu";
		}

		String categoryName = req.getParameter("categoryName");

		Long categoryId = null;

		if (!req.getParameter("categoryId").equals("0")
				&& !req.getParameter("categoryId").toLowerCase()
						.contains("all")) {
			categoryId = Long.valueOf(req.getParameter("categoryId"));
		} else {
			categoryId = 0L;
			categoryName = "all";
		}

		try {

			categoryList = storeHomeHandler.getAllCategories();

			// Get tree for selected Category from solr
			leftMenuCategoryTreeList = storeHomeHandler
					.getLeftMenuCategoryTree(categoryId);

			if (categoryId != 0L) {

				breadCrumbCategoryList = storeHomeHandler
						.getAllCategoriesInCategoryPath(categoryId);
			}

			// Get Product list for selected category
			searchResult = storeHomeHandler.getProductsOverSearchTerm(
					categoryId, searchTerm);

			if (searchResult != null && searchResult.size() > 0) {
				productList = searchResult.values().iterator().next();
			}

		} catch (GenericFailureException e) {
			e.printStackTrace();
		} catch (BusinessFailureException e) {
			e.printStackTrace();
		}

		modelMap.put("searchResult", searchResult);

		modelMap.put("productList", productList);

		modelMap.put("productList", searchResult.values().iterator().next());

		modelMap.put("leftMenuCategoryTreeList", leftMenuCategoryTreeList);

		modelMap.put("categoryList", categoryList);

		modelMap.put("searchFor", searchTerm);

		modelMap.put("searchRef", searchRef);

		modelMap.put("categoryName", categoryName);

		modelMap.put("breadCrumbCategoryList", breadCrumbCategoryList);

		return new ModelAndView("store/products_list_by_search_result",
				modelMap);
	}

	@RequestMapping(value = "store/productdetails.htm", method = RequestMethod.GET)
	public ModelAndView viewProductDetails(
			@ModelAttribute("redirectForm") RedirectForm redirectForm,
			Model model, HttpServletRequest req) throws MalformedURLException {

		Long productId = null;

		Long wishId = null;

		int variantCount = 0;

		Map<String, Object> modelMap = new HashMap<String, Object>();

		long quantity = 1;

		List<Category> categoryList = null;

		List<Category> breadCrumbCategoryList = null;

		List<Product> similarProducts = null;

		FieldStatistics ratingInfo = null;

		Product product = null;

		List<Review> productReviews = null;

		String imagableVariantName = null;

		Map<String, String> imagableVariant = new HashMap<String, String>();

		Map<String, List<ProductImagableVariantData>> imagableVariantsData = new HashMap<String, List<ProductImagableVariantData>>();

		Map<String, Map<String, Object>> sectionAttribMap = null;

		DecimalFormat formatter = new DecimalFormat("###.##");

		String productDiscountPercentage = "0";

		String id = null;

		Long variantId = null;

		if (StringUtils.isNotBlank(req.getParameter("productid"))) {

			id = req.getParameter("productid");

			if (id != null && id.split("\\_").length == 2) {

				productId = Long.valueOf(id.split("\\_")[1]);

			} else if (id != null && id.split("\\_").length == 3) {

				productId = Long.valueOf(id.split("\\_")[1]);

				variantId = Long.valueOf(id.split("\\_")[2]);

			} else {

			}

		} else if (StringUtils.isNotBlank(req.getParameter("wishListID"))) {

			wishId = Long.valueOf(req.getParameter("wishListID"));

			productId = wishListService.findProductByWishID(wishId);

		} else if (redirectForm.getProdId()!=null) {

			 id = redirectForm.getProdId();
			
			

			if (id != null && id.split("\\_").length == 2) {

				productId = Long.valueOf(id.split("\\_")[1]);

			} else if (id != null && id.split("\\_").length == 3) {

				productId = Long.valueOf(id.split("\\_")[1]);

				variantId = Long.valueOf(id.split("\\_")[2]);

			} else {

			}
			
			
		}

		try {

			categoryList = storeHomeHandler.getAllCategories();

			product = storeHomeHandler.getProduct(productId, variantId);

			breadCrumbCategoryList = storeHomeHandler
					.getAllCategoriesInCategoryPath(product.getPrdct_ctgry_id());

			for (Section section : storeHomeHandler.getAllSections(product
					.getPrdct_ctgry_id())) {

				for (Attribute attribute : storeHomeHandler
						.getAllAttributes(section.getSctn_id())) {

					if (attribute.getIsVrnt() == 1
							&& attribute.getIsImgbl() == 1) {

						imagableVariant.put(attribute.getVcAttrNm(),
								attribute.getVcAttrMppng());

						imagableVariantName = attribute.getVcAttrNm();

					}
				}
			}

			if (imagableVariantName != null) {

				imagableVariantsData = storeHomeHandler.getImagableVariantData(
						productId, imagableVariant);
			}

			sectionAttribMap = storeHomeHandler.getProductFeatures(productId,
					variantId);

			ratingInfo = storeHomeHandler.getProductRating(productId);

			productReviews = storeHomeHandler.getProductReviews(productId);

			similarProducts = storeHomeHandler.getSimilarProducts(productId);

			if (product.getNmSp() != 0) {

				productDiscountPercentage = formatter
						.format(((product.getNmSp() - product.getNmDlPrc()) / product
								.getNmSp()) * 100);
			}

			if (product.getNmSp() > 0 && product.getNmDlPrc() == 0) {

				productDiscountPercentage = "100";
			}

		} catch (GenericFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelMap.put("product", product);

		modelMap.put("breadCrumbCategoryList", breadCrumbCategoryList);

		modelMap.put("sectionAttribMap", sectionAttribMap);

		modelMap.put("productVariantsMap", imagableVariant);

		modelMap.put("categoryList", categoryList);

		modelMap.put("productReviews", productReviews);

		modelMap.put("similarProducts", similarProducts);

		modelMap.put("ratingInfo", ratingInfo);

		model.addAttribute("quantity", quantity);

		model.addAttribute("imagableVariantsData", imagableVariantsData);

		model.addAttribute("discountPercentage", productDiscountPercentage);

		return new ModelAndView("store/product_details", modelMap);
	}

	@RequestMapping(value = "store/getvariantproduct.htm", method = RequestMethod.POST)
	public @ResponseBody
	AjaxResponse getProduct(HttpServletRequest request) {

		Long productId = null;

		Long variantId = null;

		String id = null;

		Map<String, Map<String, Object>> sectionAttribMap = null;

		Product variantProduct = null;

		String errorMessage = null;

		boolean success = false;

		VariantProductData variantProductData = null;

		if (StringUtils.isNotBlank(request.getParameter("productid"))) {

			id = request.getParameter("productid");

			if (id != null && id.split("\\_").length == 2) {

				productId = Long.valueOf(id.split("\\_")[1]);

			} else if (id != null && id.split("\\_").length == 3) {

				productId = Long.valueOf(id.split("\\_")[1]);

				variantId = Long.valueOf(id.split("\\_")[2]);

			} else {

			}

		}

		try {

			variantProduct = storeHomeHandler.getVariantProduct(productId,
					variantId);

			sectionAttribMap = storeHomeHandler.getProductFeatures(productId,
					variantId);

			variantProductData = new VariantProductData(variantProduct,
					sectionAttribMap);

			success = true;

		} catch (NumberFormatException | GenericFailureException exp) {

			success = false;

			errorMessage = "Error occured, reason : " + exp.getMessage();
		}

		return new AjaxResponse(variantProductData, success, errorMessage);
	}

	@RequestMapping(value = "store/searchFacet.htm", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView searchFacetResult(Model model, @RequestParam String color_str)
			throws MalformedURLException, SolrServerException,
			GenericFailureException, BusinessFailureException {

		return new ModelAndView("store/prodbycat");
	}

	@RequestMapping(value = "store/rateProduct.htm")
	public @ResponseBody
	ModelAndView rateProduct(
			@ModelAttribute("redirectForm") RedirectForm redirectForm,
			BindingResult bindingResult, Model model, HttpServletRequest req,
			final RedirectAttributes redirectAttributes) {

		String loggedIn = (String) req.getSession().getAttribute("loggedIn");
		String url = redirectForm.getUrl();
		if ("true".equals(loggedIn)) {
			return new ModelAndView("redirect:" + url);
		} else {
			redirectAttributes.addFlashAttribute("redirectForm", redirectForm);

			return new ModelAndView("redirect:storelogin.htm");
		}

	}

	

	@RequestMapping(value = "store/advancesearchform.htm", method = RequestMethod.GET)
	public ModelAndView advnceSearch(Model model, HttpServletRequest req)
			throws MalformedURLException {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<Category> categoryList = null;

		try {
			categoryList = storeHomeHandler.getAllCategories();
		} catch (GenericFailureException | BusinessFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("categoryList " + categoryList.size() + " "
				+ categoryList);

		modelMap.put("categoryList", categoryList);

		modelMap.put("advanceSearchForm", new AdvanceSearchForm());

		return new ModelAndView("store/advance_search", modelMap);
	}

	@RequestMapping(value = "store/advancesearchresult.htm", method = RequestMethod.GET, params = "advanceSearch")
	public ModelAndView getProductsOveradvnceSearch(
			@ModelAttribute("advanceSearchForm") AdvanceSearchForm advanceSearchForm,
			Model model, HttpServletRequest req) throws MalformedURLException {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<Category> categoryList = null;

		String searchRef = null;

		String searchTerm = advanceSearchForm.getKeyWords();

		String categoryName = "ALL";

		Long categoryId = null;

		List<Category> leftMenuCategoryTreeList = null;

		List<Category> breadCrumbCategoryList = null;

		LinkedHashMap<Category, List<Product>> searchResult = null;

		List<Product> productList = null;

		try {

			if (req.getParameter("searchRef") == null) {
				searchRef = "advanced";
			} else {
				searchRef = "menu";
			}

			if (advanceSearchForm.getCategoryId() != null) {
				categoryId = advanceSearchForm.getCategoryId();
				if (categoryId == 0L) {
					categoryName = "All";
				}
			}

			// Get Product list for selected category
			searchResult = storeHomeHandler.getProductsOverAdvanceSearchTerms(
					categoryId, advanceSearchForm);

			if (searchResult != null && searchResult.size() > 0) {
				productList = searchResult.values().iterator().next();
			}

			categoryList = storeHomeHandler.getAllCategories();

			// Get tree for selected Category from solr
			leftMenuCategoryTreeList = storeHomeHandler
					.getLeftMenuCategoryTree(categoryId);

			if (categoryId != 0L) {

				breadCrumbCategoryList = storeHomeHandler
						.getAllCategoriesInCategoryPath(categoryId);
			}

		} catch (GenericFailureException | BusinessFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelMap.put("searchResult", searchResult);

		modelMap.put("productList", productList);

		modelMap.put("leftMenuCategoryTreeList", leftMenuCategoryTreeList);

		modelMap.put("categoryList", categoryList);

		modelMap.put("searchFor", searchTerm);

		modelMap.put("searchRef", searchRef);

		modelMap.put("categoryName", categoryName);

		modelMap.put("breadCrumbCategoryList", breadCrumbCategoryList);

		return new ModelAndView("store/products_list_by_search_result",
				modelMap);
	}

}
