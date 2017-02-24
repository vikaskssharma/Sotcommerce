/**
 * 
 */
package com.sot.ecommerce.service.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbsc.fos.store.solr.data.SolrDAO;
import com.sbsc.fos.store.web.vo.Attribute;
import com.sbsc.fos.store.web.vo.Category;
import com.sbsc.fos.store.web.vo.FacetInput;
import com.sbsc.fos.store.web.vo.FieldStatistics;
import com.sbsc.fos.store.web.vo.Product;
import com.sbsc.fos.store.web.vo.RangeFacetVO;
import com.sbsc.fos.store.web.vo.SimilarProduct;

/**
 * @author simanchal.patra
 * 
 */
@Service
public class ProductSolrServiceImpl implements ProductSolrService {

	private static final Logger logger = Logger
			.getLogger(ProductSolrServiceImpl.class);

	private SolrDAO<Product> solrDAO;

	@Autowired
	private CategorySolrService categorySolrService;

	@Autowired
	private AttributeSolrService attributeSolrService;

	@Autowired
	SimilarProductSolrService similarProductSolrService;

	@Autowired
	@Override
	public void setDAO(SolrDAO<Product> solrDAO) {
		this.solrDAO = solrDAO;
		this.solrDAO.setClazz(Product.class);
	}

	/**
	 * Getting Products for all Leaf Categories
	 * 
	 * @param leafCategoryList
	 * @return
	 * @throws MalformedURLException
	 * @throws SolrServerException
	 */
	@Override
	public List<Product> getProducts(List<Category> categoryList)
			throws MalformedURLException, SolrServerException {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		ArrayList<Long> prentCategoriesId = new ArrayList<>();

		List<Product> productList = null;

		if (categoryList.size() > 0) {

			for (Category categoryTree : categoryList) {
				prentCategoriesId.add(categoryTree.getCtgry_id());
			}

			solrFieldAndConditions.put("id", "Product");

			solrFieldAndConditions.put("prdct_ctgry_id", prentCategoriesId);

			productList = solrDAO.searchHybrid(solrFieldAndConditions, null, 0,
					3000);
		} else {
			productList = new ArrayList<Product>();
		}

		return productList;
	}

	@Override
	public List<Product> getProducts(Long categoryId)
			throws MalformedURLException, SolrServerException {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Product");

		solrFieldAndConditions.put("prdct_ctgry_id", categoryId);

		return solrDAO.searchHybrid(solrFieldAndConditions, null, 0, 3000);
	}

	@Override
	public List<Product> getAllProducts() throws SolrServerException {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Product");

		List<Product> productList = solrDAO.searchHybrid(
				solrFieldAndConditions, null, 0, 20);

		return productList;
	}

	@Override
	public LinkedHashMap<Category, List<Product>> getProductsOverSearchTerm(
			Long categoryId, String searchTerm) throws MalformedURLException,
			SolrServerException {

		LinkedHashMap<String, Object> searchTermMap = null;

		LinkedHashMap<Category, List<Product>> searchResult = new LinkedHashMap<>();

		List<Product> productList = null;

		List<Product> consolidatedProductList = new ArrayList<Product>();

		int totalCount = 0;

		for (Category categoryTree : categorySolrService
				.getNoNPlaceHolderChildCategories(categoryId)) {

			logger.debug("Searching data for Category : "
					+ categoryTree.getVcCtgryNm());

			searchTermMap = new LinkedHashMap<>();

			// searchTermMap.put("vcPrdDsc", searchTerm);

			searchTermMap.put("prdct_ctgry_id", categoryTree.getCtgry_id());

			if (searchTerm.split("\\s").length > 1) {

				searchTermMap.put("vcPrdNm",
						Arrays.asList(searchTerm.split("\\s")));

			} else {

				searchTermMap.put("vcPrdNm", searchTerm);
			}

			// Getting Product List of all Leaf Categories
			productList = solrDAO.searchHybrid(searchTermMap, null, 0, 20);

			if (productList.size() > 0) {

				totalCount = totalCount + productList.size();

				categoryTree.setPrdctCount(productList.size());

				consolidatedProductList.addAll(productList);

				searchResult.put(categoryTree, productList);
			}

			productList = null;
		}

		return searchResult;
	}

	@Override
	public Product getProduct(Long productId, Long variantId)
			throws MalformedURLException, SolrServerException {

		LinkedHashMap<String, Object> searchTermMap = new LinkedHashMap<>();

		Product product = null;

		searchTermMap.put("id", "Product");

		searchTermMap.put("prdct_id", productId);

		if (variantId != null) {
			searchTermMap.put("nmVrntId", variantId);
		}

		List<Product> products = solrDAO.searchHybrid(searchTermMap, null, 0,
				20);

		if (products.size() > 0) {
			product = products.get(0);
		}

		return product;

	}

	@Override
	public List<Product> getSimilarProducts(Long productId) {

		List<Product> similarProducts = new ArrayList<Product>();

		for (SimilarProduct sp : similarProductSolrService
				.getSimilarProducts(productId)) {

			try {
				similarProducts.add(this.getProduct(sp.getSmlrPrdctId(), null));
			} catch (MalformedURLException | SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return similarProducts;
	}

	@Override
	public String getProductDetails(Long productId, String attrMppng) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashMap<Category, List<Product>> getProductsOverSearchTerm(
			long categoryId, Map<String, Object> searchForm) {

		LinkedHashMap<String, Object> criteriaConditions = new LinkedHashMap<>();

		LinkedHashMap<String, Object> criteriaNegativeConditions = new LinkedHashMap<>();

		Iterator<String> itr = searchForm.keySet().iterator();

		LinkedHashMap<Category, List<Product>> searchResult = new LinkedHashMap<>();

		List<Product> productList = null;

		List<Product> consolidatedProductList = new ArrayList<Product>();

		int totalCount = 0;

		String field = null;

		String textOrder = null;

		while (itr.hasNext()) {

			field = itr.next();

			if (field.equals("keyWords")) {

				if (!((String) searchForm.get("textOrder")).equals("exact")) {

					criteriaConditions.put("vcPrdNm",
							getStringAsList((String) searchForm.get(field)));

					criteriaConditions.put("vcPrdDsc",
							getStringAsList((String) searchForm.get(field)));
				} else {

					criteriaConditions.put("vcPrdNm", searchForm.get(field));

					criteriaConditions.put("vcPrdDsc", searchForm.get(field));
				}

			} else if (field.equals("excludeWords")) {

				criteriaNegativeConditions.put("vcPrdNm",
						getStringAsList((String) searchForm.get(field)));

				criteriaNegativeConditions.put("vcPrdDsc",
						getStringAsList((String) searchForm.get(field)));

			} else if (field.equals("price")) {

				criteriaConditions.put("nmDlPrc", searchForm.get(field));

			} else if (field.equals("isFreeShipping")) {

				criteriaConditions.put("isFrShpng", searchForm.get(field)
						.equals(new Boolean(true)) ? new Integer(1)
						: new Integer(0));

			} else if (field.equals("isCod")) {

				criteriaConditions
						.put("isCOD",
								searchForm.get(field).equals(new Boolean(true)) ? new Integer(
										1) : new Integer(0));

			} else if (field.equals("textOrder")) {

				textOrder = (String) searchForm.get(field);
			}

		}

		criteriaConditions.put("id", "Product");

		for (Category categoryTree : categorySolrService
				.getNoNPlaceHolderChildCategories(categoryId)) {

			criteriaConditions
					.put("prdct_ctgry_id", categoryTree.getCtgry_id());

			productList = solrDAO.advanceSearch(criteriaConditions,
					criteriaNegativeConditions, textOrder, 0, 100);

			if (productList.size() > 0) {

				totalCount = totalCount + productList.size();

				categoryTree.setPrdctCount(productList.size());

				consolidatedProductList.addAll(productList);

				searchResult.put(categoryTree, productList);
			}

			productList = null;
		}

		return searchResult;
	}

	private List<String> getStringAsList(String wordWithSpaes) {

		List<String> strList = new ArrayList<String>();

		String[] strArray = wordWithSpaes.split("\\s");

		for (String word : strArray) {
			if (StringUtils.isNotBlank(word)) {
				strList.add(word);
			}
		}

		return strList;

	}

	@Override
	public Product getVariantProduct(Long productId, Long variantId) {

		LinkedHashMap<String, Object> searchTermMap = new LinkedHashMap<>();

		searchTermMap.put("id", "Product");

		searchTermMap.put("prdct_id", productId);

		searchTermMap.put("nmVrntId", variantId);

		List<Product> products = solrDAO.searchHybrid(searchTermMap, null, 0,
				20);

		return products.get(0);
	}

	@Override
	public QueryResponse getFacetData(Long categoryid,
			List<Attribute> facetAttributeList,
			List<RangeFacetVO> rangeFacetList, float price_min, float price_max) {
		// TODO Auto-generated method stub

		return solrDAO.getFacetData(categoryid, facetAttributeList,
				rangeFacetList, price_min, price_max);

	}

	@Override
	public QueryResponse getFacetFilterData(FacetInput facetInput,
			List<Attribute> attributeList) {
		// TODO Auto-generated method stub

		return solrDAO.getFacetFilterData(facetInput, attributeList);

	}

	@Override
	public FieldStatistics getPriceRangeByCategory(List categories,
			String columnName) {

		LinkedHashMap<String, Object> criteriaConditions = new LinkedHashMap<>();

		criteriaConditions.put("prdct_ctgry_id", categories);

		return solrDAO.getStatistics(criteriaConditions, columnName);
	}

	@Override
	public List<Product> getProductsByPriceRange(
			List<Category> leafCategoryList, float price_min, float price_max,
			int pgno, int size) {

		List<Float> priceRange = new ArrayList<Float>();
		priceRange.add(price_min);
		priceRange.add(price_max);

		LinkedHashMap<String, Object> criteriaConditions = new LinkedHashMap<>();
		criteriaConditions.put("id", "Product");
		criteriaConditions.put("prdct_ctgry_id", leafCategoryList);
		criteriaConditions.put("nmDlPrc", priceRange);

		List<Product> productList = solrDAO.searchHybrid(criteriaConditions,
				null, pgno, size);

		return productList;
	}
}
