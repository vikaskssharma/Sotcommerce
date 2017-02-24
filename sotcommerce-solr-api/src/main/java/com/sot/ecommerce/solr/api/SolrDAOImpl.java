package com.sot.ecommerce.solr.api;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.QueryParser;
import org.springframework.data.solr.core.SolrCallback;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sot.ecommerce.web.vo.Attribute;
import com.sot.ecommerce.web.vo.FacetInput;
import com.sot.ecommerce.web.vo.FieldStatistics;
import com.sot.ecommerce.web.vo.Product;
import com.sot.ecommerce.web.vo.RangeFacetVO;



@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SolrDAOImpl<T> implements SolrDAO<T> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SolrDAOImpl.class);

	private Class<T> clazz;

	/*
	 * @Autowired private TodoDocumentRepository repository;
	 */
	@Resource
	private SolrTemplate solrTemplate;

	@Resource
	private HttpSolrServer solrServer;

	public final void setClazz(Class<T> clazzToSet) {
		System.out.println("Solr clazzToSet : " + clazzToSet);
		this.clazz = clazzToSet;
	}

	private static QueryParser defaultQueryParser = new QueryParser();

	@Transactional
	public void addToIndex(Product document) {
		// LOGGER.debug("Saving a todo entry with information: {}", todoEntry);
		// SolrFacetVO document =
		// SolrFacetVO.getBuilder(todoEntry.getId(),todoEntry.getName(),todoEntry.getDescription(),todoEntry.getCat(),todoEntry.getPrice()).build();

		// LOGGER.debug("Saving document with information: {}", todoEntry);

		// repository.save(document);

	}


	public List<T> searchHybrid(
			LinkedHashMap<String, Object> criteriaConditions,
			LinkedHashMap<String, Object> criteriaNegativeConditions, int pgno,
			int size) {

		SimpleQuery query = null;

		Entry<String, Object> fieldAndCondition = null;

		Iterator<Entry<String, Object>> itr = criteriaConditions.entrySet()
				.iterator();

		while (itr.hasNext()) {

			fieldAndCondition = itr.next();

			if (query != null) {
				query.addFilterQuery(new SimpleQuery(createCriteria(
						fieldAndCondition.getKey(),
						fieldAndCondition.getValue())));
			} else {
				query = new SimpleQuery(createCriteria(
						fieldAndCondition.getKey(),
						fieldAndCondition.getValue()));
			}
		}

		if (criteriaNegativeConditions != null) {

			itr = criteriaNegativeConditions.entrySet().iterator();

			while (itr.hasNext()) {

				fieldAndCondition = itr.next();

				if (query != null) {
					query.addFilterQuery(new SimpleQuery(
							createNegativeCriteria(fieldAndCondition.getKey(),
									fieldAndCondition.getValue())));
				} else {
					query = new SimpleQuery(createNegativeCriteria(
							fieldAndCondition.getKey(),
							fieldAndCondition.getValue()));
				}
			}
		}

		if (query != null && size > 0) {
			query = query.setPageRequest(new PageRequest(pgno, size));

			return solrTemplate.queryForPage(query, clazz).getContent();
		} else {
			return null;
		}

	}

	public Criteria createCriteria(String field, Object value) {

		Assert.notNull(field, "Field cannot be null");

		Assert.notNull(value, "Value cannot be null");

		Criteria criteria = null;

		if (value instanceof String) {

			if (((String) value).contains(" ")) {

				criteria = new Criteria(field).expression((String) value);

			} else {

				criteria = new Criteria(field).contains((String) value);
			}

		} else if (value instanceof List<?>) {

			List<?> list = (List<?>) value;

			if (list.size() > 0) {

				if (list.size() == 2
						&& list.get(0).getClass().equals(Float.class)) {

					criteria = new Criteria(field).between((Float) list.get(0),
							(Float) list.get(1));

				} else {
					criteria = new Criteria(field).in((List<?>) value);

				}
			}

		} else if (value instanceof Integer) {

			criteria = new Criteria(field).is((Integer) value);

		} else if (value instanceof Long) {

			criteria = new Criteria(field).is((Long) value);

		} else if (value instanceof Float) {

			criteria = new Criteria(field).is((Float) value);

		} else {

		}

		return criteria;
	}

	
	public Criteria createNegativeCriteria(String field, Object value) {

		Assert.notNull(field, "Field cannot be null");

		Assert.notNull(value, "Value cannot be null");

		Criteria criteria = null;

		if (value instanceof String) {

			if (((String) value).contains(" ")) {

				criteria = new Criteria(field).not().expression((String) value);

			} else {

				criteria = new Criteria(field).not().contains((String) value);
			}

		} else if (value instanceof List<?>) {

			List<?> list = (List<?>) value;

			if (list.size() > 0) {

				criteria = new Criteria(field).not().in((List<?>) value);
			}

		} else if (value instanceof Integer) {

			criteria = new Criteria(field).not().is((Integer) value);

		} else if (value instanceof Long) {

			criteria = new Criteria(field).not().is((Long) value);

		} else if (value instanceof Float) {

			criteria = new Criteria(field).not().is((Float) value);

		} else {

		}

		return criteria;
	}

	
	public List<T> searchInAndNotIn(Map<String, ?> in, Map<String, ?> notIn,
			int pgno, int size) {

		String solrField = null;

		Iterator<String> itr = null;

		SimpleQuery query = null;

		if (in != null && in.size() > 0) {

			itr = in.keySet().iterator();

			while (itr.hasNext()) {

				solrField = itr.next();

				if (query != null) {
					query.addFilterQuery(new SimpleQuery(createCriteria(
							solrField, in.get(solrField))));
				} else {
					query = new SimpleQuery(createCriteria(solrField,
							in.get(solrField)));
				}

			}
		}

		if (notIn != null && notIn.size() > 0) {

			itr = notIn.keySet().iterator();

			while (itr.hasNext()) {

				solrField = itr.next();

				if (query != null) {
					query.addFilterQuery(new SimpleQuery(
							createNegativeCriteria(solrField,
									notIn.get(solrField))));
				} else {
					query = new SimpleQuery(createNegativeCriteria(solrField,
							notIn.get(solrField)));
				}
			}
		}

		if (in != null || notIn != null) {

			query.setPageRequest(new PageRequest(pgno, size));

			return solrTemplate.queryForPage(query, clazz).getContent();
		} else {

			Assert.notNull(in, "Both map can't be null");
		}

		return null;
	}

	@Override
	public FieldStatistics getStatistics(Map<String, Object> criteria,
			String fieldName) {

		Iterator<String> itr = criteria.keySet().iterator();

		FieldStatistics fieldStatistics = null;

		Criteria condition = null;

		Criteria finalCriteria = null;

		String solrField = null;

		SimpleQuery query = null;

		FacetOptions fo = null;

		while (itr.hasNext()) {

			solrField = itr.next();

			if (criteria.get(solrField) instanceof String) {

				if (((String) criteria.get(solrField)).contains(" ")) {

					condition = new Criteria(solrField)
							.expression((String) criteria.get(solrField));

				} else {

					condition = new Criteria(solrField)
							.contains((String) criteria.get(solrField));
				}

				if (finalCriteria != null) {
					finalCriteria.and(condition);
				} else {
					finalCriteria = condition;
				}

			} else if (criteria.get(solrField) instanceof Long) {

				condition = new Criteria(solrField).is((Long) criteria
						.get(solrField));

				if (finalCriteria != null) {
					finalCriteria.and(condition);
				} else {
					finalCriteria = condition;
				}
			} else {
				condition = new Criteria(solrField).in(criteria.get(solrField));

				if (finalCriteria != null) {
					finalCriteria.and(condition);
				} else {
					finalCriteria = condition;
				}
			}
		}

		query = new SimpleQuery(finalCriteria);

		SolrQuery squery = defaultQueryParser.constructSolrQuery(query);

		squery.setGetFieldStatistics(fieldName);

		QueryResponse response = execute(squery);

		if (response.getFieldStatsInfo().get(fieldName) != null) {
			fieldStatistics = new FieldStatistics(response.getFieldStatsInfo()
					.get(fieldName));
		} else {
			fieldStatistics = new FieldStatistics();
		}
		return fieldStatistics;
	}

	@Override
	public QueryResponse execute(final SolrQuery solrQuery) {
		return solrTemplate.execute(new SolrCallback<QueryResponse>() {
			@Override
			public QueryResponse doInSolr(SolrServer solrServer)
					throws SolrServerException, IOException {
				return solrServer.query(solrQuery);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> advanceSearch(
			LinkedHashMap<String, Object> criteriaConditions,
			LinkedHashMap<String, Object> criteriaNegativeConditions,
			String textOrder, int pgno, int size) {

		Iterator<Entry<String, Object>> itr = criteriaConditions.entrySet()
				.iterator();

		Entry<String, Object> fieldAndCondition = null;

		Criteria keyWordCriteria = null;

		Criteria negativeCriteria = null;

		Criteria priceCriteria = null;

		SimpleQuery query = null;

		SimpleQuery filterQuery = null;

		List<Float> priceRange = null;

		while (itr.hasNext()) {

			fieldAndCondition = itr.next();

			if (fieldAndCondition.getKey().equals("vcPrdNm")
					|| fieldAndCondition.getKey().equals("vcPrdDsc")) {

				if (textOrder.equals("any")) {

					for (String searchWord : (List<String>) fieldAndCondition
							.getValue()) {

						if (keyWordCriteria != null) {

							keyWordCriteria = keyWordCriteria.or(
									fieldAndCondition.getKey()).contains(
									searchWord);
						} else {

							keyWordCriteria = new Criteria(
									fieldAndCondition.getKey())
									.contains(searchWord);
						}

					}

				} else if (textOrder.equals("all")) {

					for (String searchWord : (List<String>) fieldAndCondition
							.getValue()) {

						if (keyWordCriteria != null) {

							keyWordCriteria = keyWordCriteria.and(
									fieldAndCondition.getKey()).contains(
									searchWord);
						} else {

							keyWordCriteria = new Criteria(
									fieldAndCondition.getKey())
									.contains(searchWord);
						}

					}

				} else if (textOrder.equals("exact")) {
					keyWordCriteria = new Criteria(fieldAndCondition.getKey())
							.expression((String) fieldAndCondition.getValue());
				} else {

				}

				if (query == null) {

					query = new SimpleQuery(keyWordCriteria);
				} else {

					query.getCriteria().or(keyWordCriteria);
				}

				keyWordCriteria = null;

			} else if (fieldAndCondition.getKey().equals("nmDlPrc")) {

				priceRange = (List<Float>) fieldAndCondition.getValue();

				if (priceRange.get(0).equals(new Float(-1))) {

					priceCriteria = new Criteria(fieldAndCondition.getKey())
							.lessThanEqual(priceRange.get(1));

				} else if (priceRange.get(1).equals(new Float(-1))) {

					priceCriteria = new Criteria(fieldAndCondition.getKey())
							.greaterThan(priceRange.get(0));
				} else {

					priceCriteria = createCriteria(fieldAndCondition.getKey(),
							fieldAndCondition.getValue());
				}

				if (filterQuery != null) {

					filterQuery.addCriteria(priceCriteria);
				} else {

					filterQuery = new SimpleQuery(priceCriteria);
				}

			} else {

				if (filterQuery != null) {

					filterQuery.addCriteria(createCriteria(
							fieldAndCondition.getKey(),
							fieldAndCondition.getValue()));
				} else {

					filterQuery = new SimpleQuery(createCriteria(
							fieldAndCondition.getKey(),
							fieldAndCondition.getValue()));
				}
			}
		}

		if (query != null) {

			if (filterQuery != null) {
				query.addFilterQuery(filterQuery);
			}
		} else {

			if (filterQuery != null) {
				query = filterQuery;
			}
		}

		if (criteriaNegativeConditions != null
				&& criteriaNegativeConditions.size() > 0) {

			fieldAndCondition = null;

			itr = criteriaNegativeConditions.entrySet().iterator();

			while (itr.hasNext()) {

				fieldAndCondition = itr.next();

				if (fieldAndCondition.getValue() instanceof List) {

					if (((List<?>) fieldAndCondition.getValue()).get(0) instanceof String) {

						System.out.println(" fieldAndCondition.getKey() "
								+ fieldAndCondition.getKey());

						for (String searchWord : (List<String>) fieldAndCondition
								.getValue()) {

							if (negativeCriteria != null) {

								negativeCriteria = negativeCriteria
										.or((String) fieldAndCondition.getKey())
										.not().contains(searchWord);
							} else {

								negativeCriteria = new Criteria(
										(String) fieldAndCondition.getKey())
										.not().contains(searchWord);
							}

						}
					}
				}
			}

			if (query != null) {

				query.addFilterQuery(new SimpleQuery(negativeCriteria));
			} else {

				query = new SimpleQuery(negativeCriteria);
			}

		}

		query.setPageRequest(new PageRequest(pgno, size));

		return solrTemplate.queryForPage(query, clazz).getContent();
	}

	@Override
	public QueryResponse getFacetData(Long categoryid,
			List<Attribute> facetAttributeList,
			List<RangeFacetVO> rangeFacetList, float price_min, float price_max) {

		QueryResponse response = null;

		List<FacetField> facetList = null;
		Criteria condition = new Criteria("prdct_ctgry_id").expression(String
				.valueOf(categoryid));
		Criteria priceCondition = new Criteria("nmDlPrc").between(price_min,
				price_max);
		condition.and(priceCondition);
		FacetQuery query = new SimpleFacetQuery(condition);
		PageRequest page = new PageRequest(0, 25);
		query.setPageRequest(page);

		FacetOptions fo = new FacetOptions();

		for (Attribute attribute : facetAttributeList) {
			if (attribute.getIsNmrc() == 0) {
				fo.addFacetOnField(attribute.getVcAttrMppng());
			}
		}

		/*
		 * fo.addFacetOnField("nmDlPrc"); SolrDataQuery solrDataQuery=new
		 * SimpleFacetQuery(); Criteria conditionrange = new
		 * Criteria("nmDlPrc").between(0, 10000);
		 * solrDataQuery.addCriteria(conditionrange);
		 * fo.addFacetQuery(solrDataQuery);
		 */

		query.setFacetOptions(fo);
		// solrTemplate.queryForFacetPage(query, Product.class);
		SolrQuery squery = new QueryParser().constructSolrQuery(query);
		// Applying Range Facet
		squery = applyRangeFacet(rangeFacetList, squery);

		/*
		 * if(null!=rangeFacetList && rangeFacetList.size()>0){ for(RangeFacetVO
		 * rangeFacetVO:rangeFacetList){
		 * 
		 * if(null != rangeFacetVO.getUnselectedValues() &&
		 * rangeFacetVO.getUnselectedValues().size()>0){
		 * 
		 * for(String s:rangeFacetVO.getUnselectedValues()){
		 * squery.addFacetQuery(rangeFacetVO.getAttributeKey()+":"+s); } //
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 */

		squery.setFacetLimit(5000);
		// squery.addFacetQuery("nmDlPrc:1.0 TO 100.0");
		// squery.addFacetQuery("nmDlPrc:100.0 TO 200.0");
		// squery.addFacetQuery("nmDlPrc:200.0 TO 300.0");
		// squery.addFacetQuery("nmDlPrc:[* TO 2]");
		// squery.addFacetQuery("nmDlPrc:[2 TO 4]");
		// squery.addFacetQuery("nmDlPrc:[4 TO *]");
		// squery.addFacetField("nmDlPrc");
		System.out.println("getFacetData---------------->>" + squery);
		response = execute(squery);
		facetList = response.getFacetFields();
		// response.getFacetRanges();

		return response;
	}

	@Override
	public QueryResponse getFacetFilterData(FacetInput facetInput,
			List<Attribute> attributeList) {

		Criteria conditions = null;
		QueryResponse response = null;
		// Condition for Category
		conditions = new Criteria("prdct_ctgry_id").expression(String
				.valueOf(facetInput.getCat_id()));
		// Condition for Price
		if (null != facetInput.getPriceMap()
				&& facetInput.getPriceMap().size() > 0) {
			float price_min = Float.parseFloat(facetInput.getPriceMap().get(
					"Price_Min"));
			float price_max = Float.parseFloat(facetInput.getPriceMap().get(
					"Price_Max"));
			Criteria priceCondition = new Criteria("nmDlPrc").between(
					price_min, price_max);
			conditions.and(priceCondition);
		}

		// Condition for String attributes
		Map criteriaMap = facetInput.getCriteriaMap();
		Iterator<String> itr = null;
		String solrField = null;
		if (null != criteriaMap && criteriaMap.size() > 0) {
			itr = criteriaMap.keySet().iterator();
			while (itr.hasNext()) {
				solrField = itr.next();
				conditions = conditions.and(new Criteria(solrField)
						.in(criteriaMap.get(solrField)));
			}
		}

		// Condition for range facets
		List<RangeFacetVO> rangeList = facetInput.getRangevolist();
		Criteria consolidatedCondtion = null;
		Criteria betweenCondition = null;
		FacetQuery query = new SimpleFacetQuery(conditions);
		if (null != rangeList && rangeList.size() > 0) {
			for (RangeFacetVO rangeFacetVO : rangeList) {
				// conditions = conditions.and(new
				// Criteria(rangeFacetVO.getAttributeKey()).in(rangeFacetVO.getSelectedValues()));
				if (null != rangeFacetVO.getSelectedValues()
						&& rangeFacetVO.getSelectedValues().size() > 0) {
					// Need to write code here
					for (String selectedValue : rangeFacetVO
							.getSelectedValues()) {
						if (null != selectedValue) {
							selectedValue = selectedValue.replace("[", "");
							selectedValue = selectedValue.replace("]", "");
							String[] selectedValues = selectedValue
									.split(" TO ");
							if (betweenCondition == null) {
								betweenCondition = new Criteria(
										rangeFacetVO.getAttributeKey())
										.between(selectedValues[0],
												selectedValues[1], true, true);
							} else {
								betweenCondition = betweenCondition
										.or(new Criteria(rangeFacetVO
												.getAttributeKey()).between(
												selectedValues[0],
												selectedValues[1], true, true));
							}
							// conditions = conditions.and(new
							// Criteria(rangeFacetVO.getAttributeKey()).between(selectedValues[0],selectedValues[1],true,true));

						}
					}
					if (null == consolidatedCondtion
							&& betweenCondition != null) {
						consolidatedCondtion = betweenCondition;
					} else {
						consolidatedCondtion.and(betweenCondition);
					}
					// conditions = conditions.and(betweenCondition);
					betweenCondition = null;

					//
				}

			}
		}
		// Creating facet Query

		if (null != consolidatedCondtion) {
			query.addFilterQuery(new SimpleQuery(consolidatedCondtion));
		}

		PageRequest page = new PageRequest(0, 25);
		query.setPageRequest(page);
		FacetOptions fo = new FacetOptions();
		for (Attribute attribute : attributeList) {
			if (attribute.getIsNmrc() == 0) {
				fo.addFacetOnField(attribute.getVcAttrMppng());
			}
		}
		query.setFacetOptions(fo);

		if(facetInput.getSorting()!=""){
			if(facetInput.getSorting().equals("Price_Ascending")){
				query.addSort(new Sort(Sort.Direction.ASC, "nmDlPrc"));
			}else if(facetInput.getSorting().equals("Price_Desending")){
				query.addSort(new Sort(Sort.Direction.DESC, "nmDlPrc"));
			}else if(facetInput.getSorting().equals("Name_Ascending")){
				query.addSort(new Sort(Sort.Direction.ASC, "vcPrdNm"));
			}else if(facetInput.getSorting().equals("Name_Desending")){
				query.addSort(new Sort(Sort.Direction.DESC, "vcPrdNm"));
			}
		}
		
		//query.addSort(new Sort(Sort.Direction.DESC, "id"));

		SolrQuery squery = new QueryParser().constructSolrQuery(query);
		squery.setFacetLimit(5000);// Need to change

		squery = applyRangeFacet(rangeList, squery);

		System.out.println("filter facet query--->" + squery);

		response = execute(squery);

		/*
		 * List<FacetField> facetList=null; Criteria condition = new
		 * Criteria("prdct_ctgry_id").expression(String.valueOf(categoryid));
		 * FacetQuery query = new SimpleFacetQuery(condition); //PageRequest
		 * page =new PageRequest(0, 1000); //query.setPageRequest(page);
		 * FacetOptions fo= new FacetOptions();
		 * 
		 * for (Attribute attribute: facetAttributeList) {
		 * fo.addFacetOnField(attribute.getVcAttrMppng()); }
		 * 
		 * fo.addFacetOnField("nmDlPrc"); SolrDataQuery solrDataQuery=new
		 * SimpleFacetQuery(); Criteria conditionrange = new
		 * Criteria("nmDlPrc").between(0, 10000);
		 * solrDataQuery.addCriteria(conditionrange);
		 * fo.addFacetQuery(solrDataQuery);
		 * 
		 * query.setFacetOptions(fo); //solrTemplate.queryForFacetPage(query,
		 * Product.class); SolrQuery squery = new
		 * QueryParser().constructSolrQuery(query);
		 * //squery.addFacetQuery("nmDlPrc:[* TO 2]");
		 * //squery.addFacetQuery("nmDlPrc:[2 TO 4]");
		 * //squery.addFacetQuery("nmDlPrc:[4 TO *]");
		 * //squery.addFacetField("nmDlPrc"); response = execute(squery);
		 * facetList=response.getFacetFields(); //response.getFacetRanges();
		 */
		return response;

	}

	private SolrQuery applyRangeFacet(List<RangeFacetVO> rangeFacetList,
			SolrQuery squery) {

		if (null != rangeFacetList && rangeFacetList.size() > 0) {
			for (RangeFacetVO rangeFacetVO : rangeFacetList) {

				if (null != rangeFacetVO.getUnselectedValues()
						&& rangeFacetVO.getUnselectedValues().size() > 0) {

					for (String unselectedValue : rangeFacetVO
							.getUnselectedValues()) {
						squery.addFacetQuery(rangeFacetVO.getAttributeKey()
								+ ":" + unselectedValue);
					}
					//

				}

			}

		}

		if (null != rangeFacetList && rangeFacetList.size() > 0) {
			for (RangeFacetVO rangeFacetVO : rangeFacetList) {

				if (null != rangeFacetVO.getSelectedValues()
						&& rangeFacetVO.getSelectedValues().size() > 0) {

					for (String selectedValue : rangeFacetVO
							.getSelectedValues()) {
						squery.addFacetQuery(rangeFacetVO.getAttributeKey()
								+ ":" + selectedValue);
					}
					//

				}

			}

		}
		return squery;
	}

}
