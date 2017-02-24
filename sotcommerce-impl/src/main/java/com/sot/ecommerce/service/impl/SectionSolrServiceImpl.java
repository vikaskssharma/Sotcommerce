/**
 * 
 */
package com.sot.ecommerce.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbsc.fos.store.solr.data.SolrDAO;
import com.sbsc.fos.store.web.vo.Section;

/**
 * @author simanchal.patra
 * 
 */
@Service
public class SectionSolrServiceImpl implements SectionSolrService {

	private SolrDAO<Section> solrDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.store.service.CategorySolrService#setDAO(com.sbsc.fos.store
	 * .solr.data.SolrDAO)
	 */
	@Autowired
	@Override
	public void setDAO(SolrDAO<Section> documentName) {
		solrDAO = documentName;
		solrDAO.setClazz(Section.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sbsc.fos.store.service.SectionService#getAllSections(java.lang.Long)
	 */
	@Override
	public List<Section> getAllSections(Long categoryId) {

		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "Section");

		solrFieldAndConditions.put("sctn_ctgry_id", categoryId);

		return (List<Section>) solrDAO.searchHybrid(solrFieldAndConditions,
				null, 0, 1000);
	}

}
