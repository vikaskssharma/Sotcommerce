package com.sot.ecommerce.web.vo;

import java.util.LinkedHashMap;

public class FacetVO {

	String facetKey;

	String facetAttributeName;

	String unitType;

	int isNumeric;

	LinkedHashMap facetmap;

	public int getIsNumeric() {
		return isNumeric;
	}

	public void setIsNumeric(int isNumeric) {
		this.isNumeric = isNumeric;
	}

	public String getFacetAttributeName() {
		return facetAttributeName;
	}

	public void setFacetAttributeName(String facetAttributeName) {
		this.facetAttributeName = facetAttributeName;
	}

	public String getFacetKey() {
		return facetKey;
	}

	public void setFacetKey(String facetKey) {
		this.facetKey = facetKey;
	}

	public LinkedHashMap getFacetmap() {
		return facetmap;
	}

	public void setFacetmap(LinkedHashMap facetmap) {
		this.facetmap = facetmap;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

}
