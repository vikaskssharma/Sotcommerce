/**
 * 
 */
package com.sot.ecommerce.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbsc.fos.store.solr.data.SolrDAO;
import com.sbsc.fos.store.web.vo.SimilarProduct;

/**
 * @author simanchal.patra
 *
 */
@Service
public class SimilarProductSolrServiceImpl implements SimilarProductSolrService {
	
	private SolrDAO<SimilarProduct> solrDAO;

	@Autowired
	@Override
	public void setDAO(SolrDAO<SimilarProduct> solrDAO) {
		this.solrDAO = solrDAO;
		this.solrDAO.setClazz(SimilarProduct.class);
	}
	
	@Override
	public List<SimilarProduct> getSimilarProducts (Long productId){
		
		LinkedHashMap<String, Object> solrFieldAndConditions = new LinkedHashMap<>();

		solrFieldAndConditions.put("id", "SmlrProduct");

		solrFieldAndConditions.put("sPrdctId", productId);

		return solrDAO.searchHybrid(solrFieldAndConditions, null, 0, 20);
	}

}
