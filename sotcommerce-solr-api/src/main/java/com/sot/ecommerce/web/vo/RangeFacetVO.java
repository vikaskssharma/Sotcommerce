package com.sot.ecommerce.web.vo;

import java.util.List;

public class RangeFacetVO {

	String attributeKey = null;

	List<String> selectedValues = null;

	List<String> unselectedValues = null;

	public String getAttributeKey() {
		return attributeKey;
	}

	public void setAttributeKey(String attributeKey) {
		this.attributeKey = attributeKey;
	}

	public List<String> getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(List<String> selectedValues) {
		this.selectedValues = selectedValues;
	}

	public List<String> getUnselectedValues() {
		return unselectedValues;
	}

	public void setUnselectedValues(List<String> unselectedValues) {
		this.unselectedValues = unselectedValues;
	}

}
