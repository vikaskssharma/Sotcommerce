/**
 * 
 */
package com.sot.ecommerce.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbsc.fos.category.service.AttributeService;
import com.sbsc.fos.common.dao.GenericDAO;
import com.sbsc.fos.common.vo.SessionInfo;
import com.sbsc.fos.persistance.CtgrySctnAttrTbDtl;
import com.sbsc.fos.persistance.ProductTbMaster;
import com.sbsc.fos.persistance.ProductVariantTbDtl;
import com.sbsc.fos.persistance.RelatedProductTbDtl;
import com.sbsc.fos.persistance.SimilarProductTbDtl;
import com.sbsc.fos.product.web.form.ProductBasicForm;
import com.sot.ecommerce.utils.SBSConstants.PRODUCT_STATUS;

/**
 * @author diksha.rattan
 * 
 */
@Service
public class ProductServiceImpl implements ProductService {

	/** Logger instance. **/
	private static final Logger logger = Logger
			.getLogger(ProductServiceImpl.class);

	private final String indent = "&nbsp;";

	private final String SQL_GET_CATEGORY_HIERARCHY_TREE = "SELECT NM_CTGRY_ID, VC_CTGRY_NM, NM_PRNT_CTGRY_ID, LEVEL "
			+ "FROM CATEGORY_TB_MASTER "
			+ "WHERE NM_STR_ID = %d AND NM_STR_ID IS Not Null AND IS_DLTD = 0"
			+ "START WITH NM_PRNT_CTGRY_ID is NULL "
			+ "CONNECT BY PRIOR NM_CTGRY_ID = NM_PRNT_CTGRY_ID";

	private GenericDAO<ProductTbMaster> dao;

	@Autowired
	ProductVariantService productVariantService;

	@Autowired
	AttributeService attributeService;

	@Autowired
	RelatedProductService relatedProductService;

	@Autowired
	SimilarProductService similarProductService;

	@Autowired
	public void setDAO(GenericDAO<ProductTbMaster> daoToSet) {
		System.out.println("daoToSet " + daoToSet);
		dao = daoToSet;
		dao.setClazz(ProductTbMaster.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#create(com.sbsc.fos.persistance
	 * .ProductTbMaster)
	 */
	@Transactional
	public void create(ProductTbMaster product) {
		dao.create(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.product.service.ProductService#findAll()
	 */
	@Transactional
	public List<ProductTbMaster> findAll() {
		return dao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.product.service.ProductService#findByID(long)
	 */
	@Transactional
	public ProductTbMaster findByID(final long id) {
		return dao.findByID(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#update(com.sbsc.fos.persistance
	 * .ProductTbMaster)
	 */
	@Transactional
	public ProductTbMaster update(ProductTbMaster product) {
		// TODO Auto-generated method stub
		ProductTbMaster prod = dao.update(product);
		return prod;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#saveOrUpdate(com.sbsc.fos
	 * .persistance.ProductTbMaster)
	 */
	@Transactional
	public void saveOrUpdate(ProductTbMaster product) {
		// TODO Auto-generated method stub
		dao.saveOrUpdate(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#saveOrUpdate(com.sbsc.fos
	 * .persistance.RelatedProductTbDtl)
	 */
	@Transactional
	public void saveOrUpdate(RelatedProductTbDtl relatedProduct) {
		// TODO Auto-generated method stub
		relatedProductService.saveOrUpdate(relatedProduct);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#saveOrUpdate(com.sbsc.fos
	 * .persistance.SimilarProductTbDtl)
	 */
	@Transactional
	public void saveOrUpdate(SimilarProductTbDtl similarProduct) {
		// TODO Auto-generated method stub
		similarProductService.saveOrUpdate(similarProduct);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#delete(com.sbsc.fos.persistance
	 * .ProductTbMaster)
	 */
	@Transactional
	public void delete(ProductTbMaster productTbMaster) {

		productTbMaster.setIsDltd(new BigDecimal("1"));
		List<ProductVariantTbDtl> variantExisted = productTbMaster
				.getProductVariantTbDtls();
		List<RelatedProductTbDtl> relatedProductExisted = productTbMaster
				.getRelatedProductTbDtls1();
		List<SimilarProductTbDtl> similarProductExisted = productTbMaster
				.getSimilarProductTbDtls1();
		for (ProductVariantTbDtl variant : variantExisted) {
			variant.setIsDltd(new BigDecimal("1"));

		}
		for (RelatedProductTbDtl relatedProduct : relatedProductExisted) {
			relatedProduct.setIsDltd(new BigDecimal("1"));

		}
		for (SimilarProductTbDtl similarProduct : similarProductExisted) {
			similarProduct.setIsDltd(new BigDecimal("1"));

		}
		dao.update(productTbMaster);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.product.service.ProductService#deleteById(long)
	 */
	@Transactional
	public void deleteById(long productId) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#findAllByProperty(java.lang
	 * .String, java.lang.Object)
	 */
	@Transactional
	public List<ProductTbMaster> findAllByProperty(String propertyName,
			Object value) {
		return dao.findAllByProperty(propertyName, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#findAllByStringPropertyIgnoreCase
	 * (java.lang.String, java.lang.String)
	 */
	@Transactional
	public List<ProductTbMaster> findAllByStringPropertyIgnoreCase(
			String propertyName, String value) {

		return dao.findAllByStringPropertyIgnoreCase(propertyName, value);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#findAllByProperty(java.util
	 * .HashMap)
	 */
	@Transactional
	public List<ProductTbMaster> findAllByProperty(
			HashMap<String, Object> propertiesMap) {
		return dao.findAllByProperty(propertiesMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#findAllAttributeByCategory
	 * (java.util.HashMap)
	 */
	@Transactional
	public List<CtgrySctnAttrTbDtl> findAllAttributeByCategory(
			HashMap<String, Object> properties) {

		return attributeService.findAllByProperty(properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#findAllProductsByCategory
	 * (java.lang.String, java.lang.Object)
	 */
	@Transactional
	public List<ProductTbMaster> findAllProductsByCategory(
			HashMap<String, Object> properties) {
		return dao.findAllByProperty(properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#findAllRelatedProductsByProduct
	 * (java.util.HashMap)
	 */
	@Transactional
	public List<RelatedProductTbDtl> findAllRelatedProductsByProduct(
			HashMap<String, Object> properties) {
		return relatedProductService.findAllByProperty(properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#findAllSimilarProductByProperty
	 * (java.util.HashMap)
	 */
	@Transactional
	public List<SimilarProductTbDtl> findAllSimilarProductByProperty(
			HashMap<String, Object> properties) {
		return similarProductService.findAllByProperty(properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#getCategoriesHierarchyTree
	 * (java.lang.Long)
	 */
	@Transactional
	public Map<Long, String> getCategoriesHierarchyTree(SessionInfo sessionInfo) {

		LinkedHashMap<String, Type> scalarMapping = new LinkedHashMap<String, Type>();
		scalarMapping.put("NM_CTGRY_ID", StandardBasicTypes.LONG);
		scalarMapping.put("VC_CTGRY_NM", StandardBasicTypes.STRING);
		scalarMapping.put("NM_PRNT_CTGRY_ID", StandardBasicTypes.LONG);
		scalarMapping.put("LEVEL", StandardBasicTypes.LONG);

		String sqlFomatted = String.format(SQL_GET_CATEGORY_HIERARCHY_TREE,
				sessionInfo.getStoreId());

		Iterator<?> results = dao.executeSQLQuery(sqlFomatted, scalarMapping)
				.iterator();

		Map<Long, String> categoryTree = new LinkedHashMap<Long, String>();

		int level = -1;

		Object[] rows = null;

		StringBuffer catNameLabel = null;

		while (results.hasNext()) {

			catNameLabel = new StringBuffer(5);

			rows = (Object[]) results.next();

			level = Integer.parseInt(rows[3].toString());

			System.out.println("CATEGORY_HIERARCHY_TREE level " + level);

			if (level > 1) {
				for (int i = 0; i < level; i++) {
					catNameLabel.append(indent + indent);
				}
			}

			catNameLabel.append((String) rows[1]);

			categoryTree.put((Long) rows[0], catNameLabel.toString());

		}

		for (Long catId : categoryTree.keySet()) {
			System.out.println(catId + "-" + categoryTree.get(catId));
		}

		return categoryTree;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#findAllProductsByFilters(
	 * java.util.Map)
	 */
	@Override
	@Transactional
	public List<ProductBasicForm> findAllProductsByFilters(
			Map<String, Object> filter_criteria) {
		List<ProductBasicForm> detailList_product_filter = new ArrayList<ProductBasicForm>();
		List<ProductTbMaster> productList = null;
		try {
			productList = dao.findAllByFilters(filter_criteria);
			for (int i = 0; i < productList.size(); i++) {
				ProductBasicForm productBasicForm = new ProductBasicForm();
				productBasicForm.setProductID(productList.get(i).getNmPrdId());
				productBasicForm.setCategoryId(String.valueOf(productList
						.get(i).getCategoryTbMaster().getNmCtgryId()));
				productBasicForm
						.setProductName(productList.get(i).getVcPrdNm());
				productBasicForm.setDealPrice(productList.get(i).getNmDlPrc().toString());
				productBasicForm.setStatus(productList.get(i).getVcStts());
				productBasicForm.setCreatedAt(productList.get(i).getDtCrtdAt()
						.getTime());
				long isVrnt = productList.get(i).getIsVrnt().longValue();
				productBasicForm.setIsVariant(isVrnt == 1 ? true : false);
			
				
				List<ProductVariantTbDtl> listVariant = productList.get(i)
						.getProductVariantTbDtls();

				for (ProductVariantTbDtl variant : listVariant) {

					long qty = variant.getNmQntty().longValue();

					if (qty == 0) {
						productBasicForm.setIsVariantOutOfStock(true);
						break;
					} else {
						productBasicForm.setIsVariantOutOfStock(false);
					}
				}

				for (ProductVariantTbDtl variant : listVariant) {

					if (StringUtils.equalsIgnoreCase(variant.getVcStts(),
							PRODUCT_STATUS.InActive.name())) {

						productBasicForm.setIsVariantInActive(true);
						break;
					} else {
						productBasicForm.setIsVariantInActive(false);
					}
				}


				detailList_product_filter.add(i, productBasicForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailList_product_filter;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sbsc.fos.product.service.ProductService#getProductById(long)
	 */
	@Override
	@Transactional
	public ProductTbMaster getProductById(long id) {
		ProductTbMaster product = dao.findByID(id);
		return product;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.product.service.ProductService#setProductStatus(java.util
	 * .List, java.lang.String)
	 */
	@Transactional
	public void setProductStatus(List<ProductTbMaster> products, String status) {

		for (ProductTbMaster product : products) {

			product.setVcStts(status);
			dao.saveOrUpdate(product);
		}
	}

}
