/**
 * 
 */
package com.sot.ecommerce.web.vo;

import org.apache.solr.client.solrj.response.FieldStatsInfo;


public class FieldStatistics {

	private String fieldName;

	private Long count;

	private Double max;

	private Double min;

	private Double sum;

	private Double mean;

	public FieldStatistics() {
		this.fieldName = null;
		this.count = 0L;
		this.max = 0d;
		this.min = 0d;
		this.sum = 0d;
		this.mean = 0d;
	}

	public FieldStatistics(FieldStatsInfo fsi) {
		this.fieldName = fsi.getName();
		this.count = fsi.getCount();
		this.max = (Double) fsi.getMax();
		this.min = (Double) fsi.getMin();
		this.sum = (Double) fsi.getSum();
		this.mean = roundUp((Double) fsi.getMean());
	}

	public Double roundUp(Double value) {
		Double newValue = null;

		if (value.floatValue() == 0.0) {

			newValue = value;
		} else if (value.compareTo(new Double(0.5)) < 0) {

			newValue = new Double(0.5);
		} else {

			if ((value.floatValue() % 1) != 0) {

				if ((value % 1) < .5) {
					newValue = Math.floor(value) + 0.5;
				} else if ((value % 1) > .5) {
					newValue = Math.ceil(value);
				} else {
					newValue = value;
				}
			}
		}
		return newValue;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * @return the max
	 */
	public Double getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(Double max) {
		this.max = max;
	}

	/**
	 * @return the min
	 */
	public Double getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(Double min) {
		this.min = min;
	}

	/**
	 * @return the sum
	 */
	public Double getSum() {
		return sum;
	}

	/**
	 * @param sum
	 *            the sum to set
	 */
	public void setSum(Double sum) {
		this.sum = sum;
	}

	/**
	 * @return the mean
	 */
	public Double getMean() {
		return mean;
	}

	/**
	 * @param mean
	 *            the mean to set
	 */
	public void setMean(Double mean) {
		this.mean = mean;
	}

}
