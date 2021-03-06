package com.sot.ecommerce.web.vo;

import java.util.LinkedHashMap;
import java.util.List;

public class FacetOutput {

	List<FacetVO> facetVOList;
	List<Product> productList;
	LinkedHashMap categorymap;
	String sorting;

	public List<FacetVO> getFacetVOList() {
		return facetVOList;
	}

	public void setFacetVOList(List<FacetVO> facetVOList) {
		this.facetVOList = facetVOList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public LinkedHashMap getCategorymap() {
		return categorymap;
	}

	public void setCategorymap(LinkedHashMap categorymap) {
		this.categorymap = categorymap;
	}

	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}

}
