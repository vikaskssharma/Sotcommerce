/**
 * 
 */
package com.sot.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.QueryParser;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import com.sbsc.fos.store.solr.data.SolrDAO;
import com.sbsc.fos.store.web.vo.ProductImagableVariantData;
import com.sbsc.fos.store.web.vo.ProductVariant;

/**
 * @author simanchal.patra
 * 
 */
@Service
public class ProductVariantSolrServiceImpl implements ProductVariantSolrService {

	private SolrDAO<ProductVariant> solrDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.store.service.CategorySolrService#setDAO(com.sbsc.fos.store
	 * .solr.data.SolrDAO)
	 */
	@Autowired
	@Override
	public void setDAO(SolrDAO<ProductVariant> documentName) {
		solrDAO = documentName;
		solrDAO.setClazz(ProductVariant.class);
	}

	@Override
	public List<ProductVariant> getProductVariants(Long productId) {

		LinkedHashMap<String, Object> searchTermMap = new LinkedHashMap<>();

		searchTermMap.put("id", "Product");

		searchTermMap.put("prdct_id", productId);

		return solrDAO.searchHybrid(searchTermMap, null, 0, 20);
	}

	@Override
	public Map<Long, String> getNonImagableVariantData(Long productId,
			Map<String, String> nonImagableVariant,
			String imagableVariantMapping, Object imagableVariantValue) {

		Map<Long, String> variants = new HashMap<Long, String>();

		SimpleQuery query = new SimpleQuery(
				new Criteria("id").contains("Product"));

		query.addFilterQuery(new SimpleQuery(new Criteria("prdct_id")
				.is(productId)));

		if (imagableVariantMapping != null && imagableVariantValue != null) {
			if (imagableVariantValue instanceof String) {

				query.addFilterQuery(new SimpleQuery(new Criteria(
						imagableVariantMapping)
						.contains((String) imagableVariantValue)));

			} else if (imagableVariantValue instanceof Float) {

				query.addFilterQuery(new SimpleQuery(new Criteria(
						imagableVariantMapping)
						.is((Float) imagableVariantValue)));
			} else {

			}
		}

		for (String fieldName : nonImagableVariant.values()) {
			query.addProjectionOnField(fieldName);
		}

		QueryResponse variantResponse = solrDAO.execute(new QueryParser()
				.constructSolrQuery(query));

		for (String attrMapping : nonImagableVariant.values()) {

			for (SolrDocument document : variantResponse.getResults()) {
				variants.put((Long) document.get("nmVrntId"), (String) document
						.get(attrMapping).toString());
			}
		}

		return variants;
	}

	@Override
	public Map<String, List<ProductImagableVariantData>> getImagableVariantData(
			Long productId, Map<String, String> imagableVariant) {

		SimpleQuery query = new SimpleQuery(
				new Criteria("id").contains("Product"));

		query.addFilterQuery(new SimpleQuery(new Criteria("prdct_id")
				.is(productId)));

		for (String fieldName : imagableVariant.values()) {
			query.addProjectionOnField(fieldName);
		}

		query.addProjectionOnField("id");

		query.addProjectionOnField("nmVrntId");

		query.addProjectionOnField("VC_VRNT_IMG_1");

		query.addProjectionOnField("VC_VRNT_IMG_2");

		query.addProjectionOnField("VC_VRNT_IMG_3");

		QueryResponse response = solrDAO.execute(new QueryParser()
				.constructSolrQuery(query));

		Map<String, List<ProductImagableVariantData>> variantsData = new HashMap<String, List<ProductImagableVariantData>>();

		List<ProductImagableVariantData> imageableVariants = new ArrayList<ProductImagableVariantData>();

		for (Entry<String, String> attribute : imagableVariant.entrySet()) {

			for (SolrDocument document : response.getResults()) {

				imageableVariants.add(new ProductImagableVariantData(
						(String) document.get("id"), (Long) document
								.get("nmVrntId"), document.get(attribute
								.getValue()), (String) document
								.get("VC_VRNT_IMG_1"), (String) document
								.get("VC_VRNT_IMG_2"), (String) document
								.get("VC_VRNT_IMG_3"), attribute.getValue()));
			}

			variantsData.put(attribute.getKey(), imageableVariants);
		}

		System.out.println("variantsData : " + variantsData);

		return variantsData;
	}
}
