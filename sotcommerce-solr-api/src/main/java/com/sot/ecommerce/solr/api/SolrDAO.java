package com.sot.ecommerce.solr.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.data.solr.core.query.Criteria;

import com.sot.ecommerce.web.vo.Attribute;
import com.sot.ecommerce.web.vo.FacetInput;
import com.sot.ecommerce.web.vo.FieldStatistics;
import com.sot.ecommerce.web.vo.Product;
import com.sot.ecommerce.web.vo.RangeFacetVO;

public interface SolrDAO<T> {

	public void setClazz(Class<T> clazzToSet);

	public void addToIndex(Product entity);

	// public void deleteFromIndex(Long id);

	public List<T> searchInAndNotIn(Map<String, ?> in, Map<String, ?> notIn,
			int pgno, int size);

	public List<T> searchHybrid(
			LinkedHashMap<String, Object> criteriaConditions,
			LinkedHashMap<String, Object> criteriaNegativeConditions, int pgno,
			int size);

	FieldStatistics getStatistics(Map<String, Object> criteria, String fieldName);

	QueryResponse execute(SolrQuery solrQuery);

	Criteria createCriteria(String field, Object value);

	Criteria createNegativeCriteria(String field, Object value);

	List<T> advanceSearch(LinkedHashMap<String, Object> criteriaConditions,
			LinkedHashMap<String, Object> criteriaNegativeConditions,
			String textOrder, int pgno, int size);

	public QueryResponse getFacetData(Long categoryid,
			List<Attribute> facetAttributeList,
			List<RangeFacetVO> rangeFacetList, float price_min, float price_max);

	public QueryResponse getFacetFilterData(FacetInput facetInput,
			List<Attribute> attributeList);
}