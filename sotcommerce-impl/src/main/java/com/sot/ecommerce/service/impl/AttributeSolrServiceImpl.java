/**
 * 
 */
package com.sot.ecommerce.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbsc.fos.store.solr.data.SolrDAO;
import com.sbsc.fos.store.web.vo.Attribute;

/**
 * @author simanchal.patra
 * 
 */
@Service
public class AttributeSolrServiceImpl implements AttributeSolrService {

	private SolrDAO<Attribute> solrDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.store.service.CategorySolrService#setDAO(com.sbsc.fos.store
	 * .solr.data.SolrDAO)
	 */
	@Autowired
	@Override
	public void setDAO(SolrDAO<Attribute> documentName) {
		solrDAO = documentName;
		solrDAO.setClazz(Attribute.class);
	}

	@Override
	public List<Attribute> getAllAttributes(Long sectionId) {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Attribute");

		solrFieldAndConditions.put("attr_sctn_id", sectionId);

		return (List<Attribute>) solrDAO.searchHybrid(solrFieldAndConditions,
				null, 0, 1000);
	}

	@Override
	public List<Attribute> getImagableAttribute(Long categoryId) {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Attribute");

		solrFieldAndConditions.put("attr_ctgry_id", categoryId);

		solrFieldAndConditions.put("isImgbl", new Integer(1));

		return (List<Attribute>) solrDAO.searchHybrid(solrFieldAndConditions,
				null, 0, 1000);
	}

	@Override
	public List<Attribute> getFacetAttributesOnCategory(Long categoryId) {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Attribute");

		solrFieldAndConditions.put("attr_ctgry_id", categoryId);
		solrFieldAndConditions.put("isSrchbl", 1);
		// solrFieldAndConditions.put("isVrnt", 1);

		return (List<Attribute>) solrDAO.searchHybrid(solrFieldAndConditions,
				null, 0, 1000);

	}

	@Override
	public Attribute getFacetAttributeNameOnCategory(Long categoryId,
			String attributeMapping) {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Attribute");

		solrFieldAndConditions.put("attr_ctgry_id", categoryId);

		solrFieldAndConditions.put("vcAttrMppng", attributeMapping);

		// solrFieldAndConditions.put("isSrchbl", 1);
		// solrFieldAndConditions.put("isVrnt", 1);

		List<Attribute> attributelist = (List<Attribute>) solrDAO.searchHybrid(
				solrFieldAndConditions, null, 0, 1000);

		if (attributelist.size() > 0) {
			return (Attribute) attributelist.get(0);

		} else {
			return null;
		}

	}

}
