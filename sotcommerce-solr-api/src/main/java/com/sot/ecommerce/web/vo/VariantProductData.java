/**
 * 
 */
package com.sot.ecommerce.web.vo;

import java.util.Map;

/**
 * @author simanchal.patra
 * 
 */
public class VariantProductData {

	Product variantProduct;

	Map<String, Map<String, Object>> sectionAttributeDataMap;

	public VariantProductData(Product variantProduct,
			Map<String, Map<String, Object>> sectionAttributeDataMap) {

		this.variantProduct = variantProduct;

		this.sectionAttributeDataMap = sectionAttributeDataMap;
	}

	/**
	 * @return the variantProduct
	 */
	public Product getVariantProduct() {
		return variantProduct;
	}

	/**
	 * @param variantProduct
	 *            the variantProduct to set
	 */
	public void setVariantProduct(Product variantProduct) {
		this.variantProduct = variantProduct;
	}

	/**
	 * @return the sectionAttributeDataMap
	 */
	public Map<String, Map<String, Object>> getSectionAttributeDataMap() {
		return sectionAttributeDataMap;
	}

	/**
	 * @param sectionAttributeDataMap
	 *            the sectionAttributeDataMap to set
	 */
	public void setSectionAttributeDataMap(
			Map<String, Map<String, Object>> sectionAttributeDataMap) {
		this.sectionAttributeDataMap = sectionAttributeDataMap;
	}

}
