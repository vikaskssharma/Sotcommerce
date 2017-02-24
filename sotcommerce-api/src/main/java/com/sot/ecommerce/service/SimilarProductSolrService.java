/**
 * 
 */
package com.sot.ecommerce.service;

import java.util.List;

import com.sot.ecommerce.entities.SimilarProduct;


public interface SimilarProductSolrService {

	void setDAO(SolrDAO<SimilarProduct> solrDAO);

	List<SimilarProduct> getSimilarProducts(Long productId);

}
