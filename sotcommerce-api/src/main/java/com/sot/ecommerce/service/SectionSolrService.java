/**
 * 
 */
package com.sot.ecommerce.service;

import java.util.List;

import com.sbsc.fos.store.solr.data.SolrDAO;
import com.sbsc.fos.store.web.vo.Section;

/**
 * @author simanchal.patra
 * 
 */
public interface SectionSolrService {

	public List<Section> getAllSections(Long categoryId);

	public void setDAO(SolrDAO<Section> documentName);

}
