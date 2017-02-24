package com.sot.ecommerce.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbsc.fos.exception.BusinessFailureException;
import com.sbsc.fos.exception.GenericFailureException;
import com.sbsc.fos.store.web.vo.Attribute;
import com.sbsc.fos.store.web.vo.Category;
import com.sbsc.fos.store.web.vo.FieldStatistics;
import com.sbsc.fos.store.web.vo.Product;
import com.sbsc.fos.store.web.vo.ProductVariant;
import com.sbsc.fos.store.web.vo.Review;
import com.sbsc.fos.store.web.vo.Section;

/**
 * @author simanchal.patra
 */
@Service
public class StoreSolrServiceImpl implements StoreSolrService {

	private static final Logger logger = Logger
			.getLogger(StoreSolrServiceImpl.class);

	@Autowired
	ProductSolrService productSolrService;

	@Autowired
	CategorySolrService categorySolrService;

	@Autowired
	SectionSolrService sectionSolrService;

	@Autowired
	AttributeSolrService attributeSolrService;

	@Autowired
	ProductVariantSolrService productVariantSolrService;

	@Autowired
	ReviewsSolrService reviewSolrService;

	@Override
	public Product getProduct(Long productId, Long variantId)
			throws SolrServerException, MalformedURLException {

		return productSolrService.getProduct(productId, variantId);
	}

	@Override
	public List<Product> getAllProducts() throws SolrServerException {

		return productSolrService.getAllProducts();
	}

	/*
	 * 
	 * Get Home Page menu
	 */
	@Override
	public List<Category> getAllCategories() throws SolrServerException,
			MalformedURLException {

		return categorySolrService.getAllCategories();

	}

	@Override
	public List<Category> getAllCategoriesInCategoryPath(Long categoryId)
			throws SolrServerException, MalformedURLException {

		return categorySolrService.getPrentCategories(categoryId);
	}

	@Override
	public LinkedHashMap<Category, List<Product>> searchProducts(
			Long categoryId, String searchTerm)
			throws BusinessFailureException, GenericFailureException,
			MalformedURLException, SolrServerException {

		return productSolrService.getProductsOverSearchTerm(categoryId,
				searchTerm);
	}

	@Override
	public List<Category> getLeftMenuCategoryTree(Long categoryId)
			throws MalformedURLException, SolrServerException {

		List<Category> leftMenuCategoryTree = new ArrayList<>();

		List<Category> relatedCategoriesList = new ArrayList<>();

		List<Category> childCategoriesList = new ArrayList<>();

		List<Category> parentCategoriesList = null;

		List<Product> productList = null;

		if (categoryId != 0L) {

			parentCategoriesList = categorySolrService
					.getPrentCategories(categoryId);

			if (parentCategoriesList.size() > 0) {

				leftMenuCategoryTree.addAll(parentCategoriesList);

				relatedCategoriesList.addAll(parentCategoriesList);
			}

			childCategoriesList = categorySolrService
					.getImmidiateChildCategories(categoryId);

			if (childCategoriesList.size() > 0) {

				leftMenuCategoryTree.addAll(childCategoriesList);

				relatedCategoriesList.addAll(categorySolrService
						.getChildCategories(categoryId));
			}

		} else {

			try {

				leftMenuCategoryTree.addAll(getAllCategories());

				relatedCategoriesList.addAll(leftMenuCategoryTree);

			} catch (MalformedURLException | SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("leftMenuCategoryTree :: " + leftMenuCategoryTree);

		System.out.println("relatedCategoriesList :: " + relatedCategoriesList);

		for (Category category : relatedCategoriesList) {

			try {

				productList = productSolrService
						.getProducts(categorySolrService
								.getPrentCategories(category.getCtgry_id()));

				category.setPrdctCount(productList.size());

				for (Category parentCategory : leftMenuCategoryTree) {

					System.out.println(" category - " + category.getVcCtgryNm()
							+ " ::: parentCategory - "
							+ parentCategory.getVcCtgryNm());

					if ((category.getVcCtgryPth().contains(String
							.valueOf(parentCategory.getVcCtgryNm())))
							&& (parentCategory.getCtgry_id() != category
									.getCtgry_id())) {

						if (parentCategory.getPrdctCount() > 0) {

							parentCategory.setPrdctCount(parentCategory
									.getPrdctCount() + productList.size());
						} else {
							parentCategory.setPrdctCount(productList.size());
						}
					}
				}

			} catch (GenericFailureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// productsPerCategory.put(category, productList);
		}

		return leftMenuCategoryTree;
	}

	@Override
	public List<Product> getProducts(Long selectedCategoryId)
			throws MalformedURLException, SolrServerException {

		return productSolrService.getProducts(categorySolrService
				.getNoNPlaceHolderChildCategories(selectedCategoryId));
	}

	@Override
	public Category getCategory(Long categoryId) {
		return categorySolrService.getCategory(categoryId);
	}

	@Override
	public List<Section> getAllSections(Long categoryId) {
		return sectionSolrService.getAllSections(categoryId);
	}

	@Override
	public List<Attribute> getAllAttributes(Long sectionId) {

		return attributeSolrService.getAllAttributes(sectionId);
	}

	@Override
	public List<String> getProductVariantDetails(Long productId,
			String attrMppng) {

		List<ProductVariant> productVariants = null;

		Field field = null;

		List<String> values = new ArrayList<String>();

		String value = null;

		try {

			productVariants = productVariantSolrService
					.getProductVariants(productId);

			for (ProductVariant productVariant : productVariants) {

				for (Method method : productVariant.getClass().getMethods()) {

					if (method.getName().toLowerCase().contains("get")
							&& method.getName().toLowerCase()
									.contains(attrMppng.toLowerCase())) {

						value = (String) method.invoke(productVariant);

						if (value != null) {
							values.add(value);
						}
					}
				}

			}

		} catch (MalformedURLException | SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return values;

	}

	@Override
	public String getProductDetails(Long productId, String attrMppng) {

		Product product = null;

		String value = null;

		try {

			product = getProduct(productId, null);

			for (Method method : product.getClass().getDeclaredMethods()) {

				if (method.getName().toLowerCase().contains("get")
						&& method.getName().toLowerCase()
								.contains(attrMppng.toLowerCase())) {

					value = (String) method.invoke(product);
				}
			}

		} catch (MalformedURLException | SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
	}

	@Override
	public List<Review> getProductReviews(Long productId) {
		return reviewSolrService.getProductReviews(productId);
	}

	@Override
	public List<Product> getSimilarProducts(Long productId) {
		return productSolrService.getSimilarProducts(productId);
	}

	@Override
	public FieldStatistics getProductRating(Long productId) {
		return reviewSolrService.getProductRating(productId);
	}

}
