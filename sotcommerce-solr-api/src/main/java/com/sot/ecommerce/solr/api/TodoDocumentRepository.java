package com.sot.ecommerce.solr.api;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.sot.ecommerce.web.vo.Product;


public interface TodoDocumentRepository<T> extends SolrCrudRepository<Product, String> {
	
	
}