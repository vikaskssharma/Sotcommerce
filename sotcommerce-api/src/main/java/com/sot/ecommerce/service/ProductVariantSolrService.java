/**
 * 
 */
package com.sot.ecommerce.service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.sbsc.fos.store.solr.data.SolrDAO;
import com.sbsc.fos.store.web.vo.ProductVariant;
import com.sbsc.fos.store.web.vo.ProductImagableVariantData;

/**
 * @author simanchal.patra
 * 
 */
public interface ProductVariantSolrService {

	void setDAO(SolrDAO<ProductVariant> documentName);

	List<ProductVariant> getProductVariants(Long productId)
			throws MalformedURLException, SolrServerException;

	Map<Long, String> getNonImagableVariantData(Long productId,
			Map<String, String> nonImagableVariant,
			String imagableVariantMapping, Object imagableVariantValue);

	Map<String, List<ProductImagableVariantData>> getImagableVariantData(
			Long productId, Map<String, String> imagableVariant);

}
