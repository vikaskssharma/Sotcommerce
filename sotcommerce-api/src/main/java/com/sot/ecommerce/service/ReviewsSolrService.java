/**
 * 
 */
package com.sot.ecommerce.service;

import java.util.List;

import com.sbsc.fos.store.solr.data.SolrDAO;
import com.sbsc.fos.store.web.vo.FieldStatistics;
import com.sbsc.fos.store.web.vo.Review;

/**
 * @author simanchal.patra
 *
 */
public interface ReviewsSolrService {

	List<Review> getProductReviews(Long productId);

	void setDAO(SolrDAO<Review> solrDAO);

	FieldStatistics getProductRating(Long productId);

}
