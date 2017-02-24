package com.sot.ecommerce.web.vo;



import java.util.List;

import org.apache.solr.client.solrj.response.FacetField;

public class FacetAttribute {
	
	String facetname;
	List<FacetField.Count> facetValue;
	
	public List<FacetField.Count> getFacetValue() {
		return facetValue;
	}
	public void setFacetValue(List<FacetField.Count> facetValue) {
		this.facetValue = facetValue;
	}
	public String getFacetname() {
		return facetname;
	}
	public void setFacetname(String facetname) {
		this.facetname = facetname;
	}
	
	

}
