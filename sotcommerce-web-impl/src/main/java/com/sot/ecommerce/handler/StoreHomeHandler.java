package com.sot.ecommerce.handler;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONException;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.store.service.AttributeSolrService;
import com.sbsc.fos.store.service.CategorySolrService;
import com.sbsc.fos.store.service.ProductSolrService;
import com.sbsc.fos.store.service.StoreService;
import com.sbsc.fos.store.web.vo.AdvanceSearchForm;
import com.sbsc.fos.store.web.vo.Attribute;
import com.sbsc.fos.store.web.vo.Category;
import com.sbsc.fos.store.web.vo.FacetInput;
import com.sbsc.fos.store.web.vo.FacetOutput;
import com.sbsc.fos.store.web.vo.FacetVO;
import com.sbsc.fos.store.web.vo.FieldStatistics;
import com.sbsc.fos.store.web.vo.Product;
import com.sbsc.fos.store.web.vo.ProductImagableVariantData;
import com.sbsc.fos.store.web.vo.RangeFacetVO;
import com.sbsc.fos.store.web.vo.Review;
import com.sbsc.fos.store.web.vo.Section;
import com.sbsc.fos.utils.ConvertJson;

@Component
public class StoreHomeHandler {

	@Autowired
	StoreService storeService;
	@Autowired
	AttributeSolrService attributeSolrService;
	@Autowired
	ProductSolrService productSolrService;
	@Autowired
	CategorySolrService categorySolrService;

	public List<Product> getAllProducts() throws BusinessFailureException,
			GenericFailureException, MalformedURLException, SolrServerException {

		return storeService.getAllProducts();
	}

	public Product getProduct(Long productId, Long variantId)
			throws BusinessFailureException, GenericFailureException,
			MalformedURLException, SolrServerException {

		return storeService.getProduct(productId, variantId);
	}

	public List<Category> getAllCategoriesInCategoryPath(Long categoryId)
			throws BusinessFailureException, GenericFailureException,
			MalformedURLException, SolrServerException {

		return storeService.getAllCategoriesInCategoryPath(categoryId);
	}

	public List<Category> getAllCategories() throws BusinessFailureException,
			GenericFailureException, MalformedURLException {

		List<Category> categoryList = null;

		try {
			categoryList = storeService.getAllCategories();
		} catch (SolrServerException e) {

			e.printStackTrace();
		}
		return categoryList;
	}

	// Getting Tree list on the basis of category selected from portal
	public List<Category> getLeftMenuCategoryTree(long categoryId)
			throws MalformedURLException, SolrServerException {

		return storeService.getLeftMenuCategoryTree(categoryId);
	}

	// To get product list of selected category from portal
	public List<Product> getProductList(long sectedCategoryId)
			throws BusinessFailureException, GenericFailureException,
			MalformedURLException {

		List<Product> productList = null;

		try {

			// Getting product list on the basis of leaf categories list
			productList = storeService.getProducts(sectedCategoryId);

		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productList;
	}

	public LinkedHashMap<Category, List<Product>> getProductsOverSearchTerm(
			long categoryId, String searchTerm)
			throws BusinessFailureException, GenericFailureException,
			MalformedURLException {

		LinkedHashMap<Category, List<Product>> searchResult = null;

		try {

			// Getting product list on the basis of leaf categories list
			searchResult = storeService.searchProducts(categoryId, searchTerm);

		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return searchResult;
	}

	public LinkedHashMap<Category, List<Product>> getProductsOverAdvanceSearchTerms(
			long categoryId, AdvanceSearchForm advanceSearchForm)
			throws BusinessFailureException, GenericFailureException,
			MalformedURLException {

		Float priceLow = null;

		Float priceHigh = null;

		List<Float> priceRange = new ArrayList<Float>();

		LinkedHashMap<Category, List<Product>> searchResult = null;

		Map<String, Object> searchTerms = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(advanceSearchForm.getKeyWords())) {
			searchTerms.put("keyWords", advanceSearchForm.getKeyWords());
		}

		if (StringUtils.isNotBlank(advanceSearchForm.getTextOrder())) {
			searchTerms.put("textOrder", advanceSearchForm.getTextOrder());
		}

		if (StringUtils.isNotBlank(advanceSearchForm.getExcludeWords())) {
			searchTerms
					.put("excludeWords", advanceSearchForm.getExcludeWords());
		}

		if (advanceSearchForm.getCategoryId() != null) {
			searchTerms.put("category", advanceSearchForm.getCategoryId());
		}

		if (StringUtils.isNotBlank(advanceSearchForm.getPriceLow())
				|| StringUtils.isNotBlank(advanceSearchForm.getPriceHigh())) {

			if (advanceSearchForm.getPriceLow() != null) {

				try {
					priceLow = Float.valueOf(advanceSearchForm.getPriceLow());

					priceRange.add(priceLow);
				} catch (NumberFormatException e) {

				}
			} else {
				priceRange.add(new Float(-1));
			}

			if (advanceSearchForm.getPriceHigh() != null) {

				try {
					priceHigh = Float.valueOf(advanceSearchForm.getPriceHigh());

					priceRange.add(priceHigh);
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
			} else {
				priceRange.add(new Float(-1));
			}

			if (priceRange.size() > 0) {

				searchTerms.put("price", priceRange);
			}
		}

		if (advanceSearchForm.getIsFreeShipping()) {
			searchTerms.put("isFreeShipping",
					advanceSearchForm.getIsFreeShipping());
		}

		if (advanceSearchForm.getIsCOD()) {
			searchTerms.put("isCOD", advanceSearchForm.getIsCOD());
		}

		// Getting product list on the basis of leaf categories list
		searchResult = storeService.searchProducts(categoryId, searchTerms);

		return searchResult;
	}

	public Category getCategory(Long categoryId) {
		return storeService.getCategory(categoryId);
	}

	public List<Section> getAllSections(Long categoryId) {
		return storeService.getAllSections(categoryId);
	}

	public List<Attribute> getAllAttributes(Long sectionId) {
		return storeService.getAllAttributes(sectionId);
	}

	public Map<String, List<ProductImagableVariantData>> getImagableVariantData(
			Long productId, Map<String, String> imagableVariant) {

		return storeService.getImagableVariantData(productId, imagableVariant);
	}

	public Map<String, Map<String, Object>> getProductFeatures(Long productId,
			Long variantId) {
		return storeService.getProductFeatures(productId, variantId);
	}

	public FieldStatistics getProductRating(Long productId) {
		return storeService.getProductRating(productId);
	}

	public List<Review> getProductReviews(Long productId) {
		return storeService.getProductReviews(productId);
	}

	public List<Product> getSimilarProducts(Long productId) {
		return storeService.getSimilarProducts(productId);
	}

	public Map<Long, String> getNonImagableVariantData(Long productId,
			Map<String, String> nonImagableVariant,
			String imagableVariantMapping, Object imagableVariantValue) {

		return storeService.getNonImagableVariantData(productId,
				nonImagableVariant, imagableVariantMapping,
				imagableVariantValue);
	}

	public Product getVariantProduct(Long productId, Long variantId) {

		return storeService.getVariantProduct(productId, variantId);
	}

	public FacetOutput getAllFacets(long sectedCategoryId, String jsonStrng)
			throws BusinessFailureException, GenericFailureException,
			MalformedURLException, SolrServerException {
		// Getting searchable Attributes list for selected Category
		float price_min = 0;
		float price_max = 0;
		List<FacetVO> facetVOs = new ArrayList<>();
		List productList = null;
		QueryResponse response = null;
		List<Attribute> attributeList = null;
		FacetOutput facetOutput = new FacetOutput();

		FacetInput facetInput = null;
		List<RangeFacetVO> rangeFacetList = null;
		if (jsonStrng != null) {
			ConvertJson convertJson = new ConvertJson();
			try {
				facetInput = convertJson.convertJsontoObject(jsonStrng);
				sectedCategoryId = Long.parseLong(facetInput.getCat_id());
				rangeFacetList = facetInput.getRangevolist();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.json.JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (jsonStrng != null) {
			attributeList = attributeSolrService
					.getFacetAttributesOnCategory(Long.parseLong(facetInput
							.getCat_id()));
		} else {
			attributeList = attributeSolrService
					.getFacetAttributesOnCategory(sectedCategoryId);
		}

		if (attributeList.size() > 0) {
			List categories = new ArrayList<>();
			// facetFields = productSolrService.getFacetData(sectedCategoryId,
			// attributeList);

			// Getting all facet Data on the basis of facet attributes and
			// selected category id
			if (jsonStrng != null) {
				response = productSolrService.getFacetFilterData(facetInput,
						attributeList);
				price_min = Float.parseFloat(facetInput.getPriceMap().get(
						"Price_Min"));
				price_max = Float.parseFloat(facetInput.getPriceMap().get(
						"Price_Max"));

			} else {
				rangeFacetList = getRangeForNumericAttributes(sectedCategoryId,
						attributeList);

				// For Price Facet
				categories.add(sectedCategoryId);
				String[] price_split = getPriceRange(categories).split("#");
				price_min = Float.parseFloat(price_split[0]);
				price_max = Float.parseFloat(price_split[1]);

				response = productSolrService.getFacetData(sectedCategoryId,
						attributeList, rangeFacetList, price_min, price_max);

			}

			FacetVO facetVOPrice = new FacetVO();
			facetVOPrice.setFacetKey("nmDlPrc");
			facetVOPrice.setFacetAttributeName("Price");
			LinkedHashMap priceMap = new LinkedHashMap();
			priceMap.put("Price_Min", price_min);
			priceMap.put("Price_Max", price_max);
			facetVOPrice.setFacetmap(priceMap);
			facetVOs.add(facetVOPrice);
			// Getting facet list except price and numeric data
			facetVOs = getFacetList(facetVOs, response, sectedCategoryId);

			// Getting Numeric Facet Ranges
			facetVOs = getFacetListFromQueries(facetVOs, response,
					sectedCategoryId, rangeFacetList);

			facetOutput.setFacetVOList(facetVOs);
			// Getting product List
			productList = getProductListFromFacets(response, sectedCategoryId);
			facetOutput.setProductList(productList);

			// getCategoryCount()
		} else {

			List<Category> categories = null;
			List leafCategoryList = new ArrayList();
			categories = categorySolrService
					.getNoNPlaceHolderChildCategories(sectedCategoryId);
			for (Category category : categories) {
				leafCategoryList.add(category.getCtgry_id());
			}
			if (jsonStrng != null) {

				price_min = Float.parseFloat(facetInput.getPriceMap().get(
						"Price_Min"));
				price_max = Float.parseFloat(facetInput.getPriceMap().get(
						"Price_Max"));

			} else {
				String[] price_split = getPriceRange(leafCategoryList).split(
						"#");
				price_min = Float.parseFloat(price_split[0]);
				price_max = Float.parseFloat(price_split[1]);

			}

			FacetVO facetVO = new FacetVO();
			facetVO.setFacetKey("nmDlPrc");
			facetVO.setFacetAttributeName("Price");
			LinkedHashMap lhm = new LinkedHashMap();
			lhm.put("Price_Min", price_min);
			lhm.put("Price_Max", price_max);
			facetVO.setFacetmap(lhm);
			facetVOs.add(facetVO);

			// Getting Product List
			productList = productSolrService.getProductsByPriceRange(
					leafCategoryList, price_min, price_max, 0, 25);
			facetOutput.setProductList(productList);
			facetOutput.setFacetVOList(facetVOs);
		}
		// Creating Hash Map

		LinkedHashMap categorymap = new LinkedHashMap<>();
		categorymap.put(sectedCategoryId, productList.size());
		facetOutput.setCategorymap(categorymap);

		/*
		 * if(attributeList!=null){ for (Attribute attribute : attributeList) {
		 * 
		 * 
		 * attribute.getVcAttrNm(); attribute.get
		 * 
		 * } attributeList.get(index) }
		 */
		// lhm.size();

		return facetOutput;
	}

	public List<RangeFacetVO> getRangeForNumericAttributes(long categoryid,
			List<Attribute> attributeList) {
		List<RangeFacetVO> rangeattributeList = new ArrayList<>();
		RangeFacetVO rangeFacetVO = null;
		for (Attribute attribute : attributeList) {

			if (attribute.getIsNmrc() == 1) {
				rangeFacetVO = new RangeFacetVO();
				rangeFacetVO.setAttributeKey(attribute.getVcAttrMppng());
				List<String> rangeList = getNumericRangeByCategory(categoryid,
						attribute.getVcAttrMppng());
				rangeFacetVO.setUnselectedValues(rangeList);
				rangeattributeList.add(rangeFacetVO);
			}
		}

		return rangeattributeList;
	}

	public List<String> getNumericRangeByCategory(Long categoryid,
			String attributemapping) {
		List ranges = new ArrayList<>();
		List categories = new ArrayList<>();
		categories.add(categoryid);
		FieldStatistics fs = productSolrService.getPriceRangeByCategory(
				categories, attributemapping);
		double diff = fs.getMax() - fs.getMin();
		diff = diff / 5;
		double min = fs.getMin();
		for (double d = fs.getMin(); d <= fs.getMax(); d = d + diff) {
			ranges.add("[" + String.valueOf((int) d) + " TO "
					+ String.valueOf((int) (d + diff)) + "]");
		}
		return ranges;
	}

	public List<FacetVO> getFacetListFromQueries(List facetVOs,
			QueryResponse response, long categoryid,
			List<RangeFacetVO> rangeFacetList) {

		Map<String, Integer> facetQueries = response.getFacetQuery();
		FacetVO facetVO = null;
		LinkedHashMap lhm = null;
		if (null != rangeFacetList && rangeFacetList.size() > 0) {
			for (RangeFacetVO rangeFacetVO : rangeFacetList) {
				facetVO = null;
				lhm = null;
				// For Selected List
				if (null != rangeFacetVO.getSelectedValues()
						&& rangeFacetVO.getSelectedValues().size() > 0) {
					for (String selectedValue : rangeFacetVO
							.getSelectedValues()) {
						if (facetQueries.get(rangeFacetVO.getAttributeKey()
								+ ":" + selectedValue) > 0) {
							if (facetVO == null) {
								facetVO = new FacetVO();
							}
							if (lhm == null) {
								lhm = new LinkedHashMap<>();
							}
							lhm.put(selectedValue, facetQueries
									.get(rangeFacetVO.getAttributeKey() + ":"
											+ selectedValue));
						}
					}

				}
				// For UnSelected List
				if (null != rangeFacetVO.getUnselectedValues()
						&& rangeFacetVO.getUnselectedValues().size() > 0) {
					for (String unselectedValue : rangeFacetVO
							.getUnselectedValues()) {
						if (facetQueries.get(rangeFacetVO.getAttributeKey()
								+ ":" + unselectedValue) > 0) {
							if (facetVO == null) {
								facetVO = new FacetVO();
							}

							if (lhm == null) {
								lhm = new LinkedHashMap<>();
							}
							lhm.put(unselectedValue, facetQueries
									.get(rangeFacetVO.getAttributeKey() + ":"
											+ unselectedValue));
						}
					}

				}
				if (null != facetVO
						&& (null != rangeFacetVO.getSelectedValues() || null != rangeFacetVO
								.getUnselectedValues())) {
					facetVO.setFacetKey(rangeFacetVO.getAttributeKey());
					Attribute att = getAttributeName(categoryid,
							rangeFacetVO.getAttributeKey());
					facetVO.setFacetAttributeName(att.getVcAttrNm());
					facetVO.setIsNumeric(att.getIsNmrc());
					facetVO.setFacetmap(lhm);
					facetVOs.add(facetVO);
				}

			}
		}

		/*
		 * Entry<String, Integer> facetqueriesEntry = null;
		 * 
		 * Iterator<Entry<String, Integer>> itr = facetQueries.entrySet()
		 * .iterator();
		 * 
		 * while (itr.hasNext()) {
		 * 
		 * String[] facetKeyString=null; String facetKey=null; Integer
		 * facetCount;
		 * 
		 * facetqueriesEntry = itr.next();
		 * facetKeyString=facetqueriesEntry.getKey().split(":");
		 * facetKey=facetKeyString[0]; facetCount=facetqueriesEntry.getValue();
		 * 
		 * System.out.println("facetKey------------------->>>"+facetKey+
		 * "facetValue-------->>"+facetCount);
		 */

		// }
		return facetVOs;
	}

	public List<FacetVO> getFacetList(List facetVOs, QueryResponse response,
			long categoryid) {

		List<FacetField> facetFields = response.getFacetFields();
		// List facetVOs=new ArrayList<>();

		if (facetFields != null) {
			// categories.add(sectedCategoryId);

			// String[] price_split=getPriceRange(categories).split("#");

			/*
			 * FacetVO facetVO=new FacetVO(); facetVO.setFacetKey("Price");
			 * 
			 * LinkedHashMap lhm =new LinkedHashMap(); lhm.put("Price_Min",
			 * price_split[0]); lhm.put("Price_Max", price_split[1]);
			 * facetVO.setFacetmap(lhm); facetVOs.add(facetVO);
			 */

			Attribute attribute = null;

			for (FacetField facetField : facetFields) {
				FacetVO facetVO = new FacetVO();
				facetVO.setFacetKey(facetField.getName());

				attribute = getAttributeName(categoryid, facetField.getName());
				facetVO.setFacetAttributeName(attribute.getVcAttrNm());
				facetVO.setUnitType(attribute.getVcUntTypMppng());
				facetVO.setIsNumeric(attribute.getIsNmrc());

				if (facetField.getValueCount() != 0) {
					LinkedHashMap lhm1 = new LinkedHashMap();
					List values = facetField.getValues();
					Iterator itr = values.iterator();
					while (itr.hasNext()) {
						FacetField.Count ff = (FacetField.Count) itr.next();
						lhm1.put(ff.getName(), ff.getCount());
					}
					facetVO.setFacetmap(lhm1);
					facetVOs.add(facetVO);
					// attributeSolrService.
					// lhm1.put(facetField.getName(),facetField.getValues());
				}
			}
		}

		return facetVOs;
	}

	public List<Product> getProductListFromFacets(QueryResponse response,
			long categoryid) {

		SolrDocumentList solrList = response.getResults();
		List<Product> productList = new ArrayList<Product>();
		for (SolrDocument solrDocument : solrList) {

			// solrDocument.getFieldNames();
			Map solrMap = solrDocument.getFieldValueMap();
			Product product = new Product();
			product.setPrdct_id((Long) (solrMap.get("prdct_id")));

			if (solrMap.get("nmVrntId") != ""
					&& solrMap.get("nmVrntId") != null) {
				
				product.setNmVrntId((Long) (solrMap.get("nmVrntId")));
				product.setVC_VRNT_IMG_1((String) (solrMap.get("VC_VRNT_IMG_1")));
				product.setVC_VRNT_IMG_2((String) (solrMap.get("VC_VRNT_IMG_2")));
				product.setVC_VRNT_IMG_3((String) (solrMap.get("VC_VRNT_IMG_3")));
				product.setVcPrdNm((String) (solrMap.get("vcVrntNm")));
			} else {
				product.setVcPrdImg1Pth((String) (solrMap.get("vcPrdImg1Pth")));
				product.setVcPrdImg2Pth((String) (solrMap.get("vcPrdImg2Pth")));
				product.setVcPrdImg3Pth((String) (solrMap.get("vcPrdImg3Pth")));
				product.setVcPrdImg4Pth((String) (solrMap.get("vcPrdImg4Pth")));
				product.setVcPrdNm((String) (solrMap.get("vcPrdNm")));
			}
			product.setId((String)solrMap.get("id"));
			
			if (solrMap.get("nmSp") != null && solrMap.get("nmSp") != "") {
				product.setNmSp((Float) (solrMap.get("nmSp")));
			}
			if (solrMap.get("nmDlPrc") != null && solrMap.get("nmDlPrc") != "") {
				product.setNmDlPrc((Float) (solrMap.get("nmDlPrc")));
			}
			productList.add(product);
		}

		return productList;

	}

	public Attribute getAttributeName(long categoryid, String attributemapping) {
		Attribute attribute = attributeSolrService
				.getFacetAttributeNameOnCategory(categoryid, attributemapping);
		return attribute;
	}

	public String getPriceRange(List categories) {

		FieldStatistics simpleFieldStatistics = productSolrService
				.getPriceRangeByCategory(categories, "nmDlPrc");
		if (null != simpleFieldStatistics) {
			return String.valueOf(simpleFieldStatistics.getMin()) + "#"
					+ String.valueOf(simpleFieldStatistics.getMax());
		} else {
			return null;
		}

	}

}
