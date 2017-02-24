package com.sot.ecommerce.web.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;

@Entity
@Table(name = "Category")
public class Category {

	@Id
	@Field
	@Column(name = "NM_CTGRY_SLR_ID")
	private String id;

	@Field
	@Column(name = "ctgry_id")
	private long ctgry_id;

	@Field
	@Column(name = "ctgry_prnt_id")
	private long ctgry_prnt_id;

	@Field
	@Column(name = "vcCtgryNm")
	private String vcCtgryNm;

	@Field
	@Column(name = "vcCtgryPth")
	private String vcCtgryPth;

	@Field
	@Column(name = "vcCtgryDsc")
	private String vcCtgryDsc;

	@Field
	@Column(name = "isPlchldr")
	private int isPlchldr;

	@Field
	@Column(name = "ctgry_level")
	private int ctgry_level;

	@Field
	@Column(name = "treeIndex")
	private int treeIndex;

	@Field
	@Column(name = "prdctCount")
	private int prdctCount = 0;

	public static class Builder {

		private Category build;

		public Builder(String id, long ctgry_id, long ctgry_prnt_id,
				String vcCtgryNm, String vcCtgryPth, String vcCtgryDsc,
				int isPlchldr, int ctgry_level, int treeIndex, int prdctCount) {

			build = new Category();
			build.id = id;
			build.ctgry_id = ctgry_id;
			build.ctgry_prnt_id = ctgry_prnt_id;
			build.vcCtgryNm = vcCtgryNm;
			build.vcCtgryPth = vcCtgryPth;
			build.vcCtgryDsc = vcCtgryDsc;
			build.isPlchldr = isPlchldr;
			build.ctgry_level = ctgry_level;
			build.treeIndex = treeIndex;
			build.prdctCount = prdctCount;
		}

		public Category build() {
			return build;
		}
	}

	public static Builder getBuilder(String id, long ctgry_id,
			long ctgry_prnt_id, String vcCtgryNm, String vcCtgryPth,
			String vcCtgryDsc, int isPlchldr, int ctgry_level, int treeIndex,
			int prdctCount) {

		return new Builder(id, ctgry_id, ctgry_prnt_id, vcCtgryNm, vcCtgryPth,
				vcCtgryDsc, isPlchldr, ctgry_level, treeIndex, prdctCount);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the ctgry_id
	 */
	public long getCtgry_id() {
		return ctgry_id;
	}

	/**
	 * @param ctgry_id
	 *            the ctgry_id to set
	 */
	public void setCtgry_id(long ctgry_id) {
		this.ctgry_id = ctgry_id;
	}

	/**
	 * @return the ctgry_prnt_id
	 */
	public long getCtgry_prnt_id() {
		return ctgry_prnt_id;
	}

	/**
	 * @param ctgry_prnt_id
	 *            the ctgry_prnt_id to set
	 */
	public void setCtgry_prnt_id(long ctgry_prnt_id) {
		this.ctgry_prnt_id = ctgry_prnt_id;
	}

	/**
	 * @return the vcCtgryNm
	 */
	public String getVcCtgryNm() {
		return vcCtgryNm;
	}

	/**
	 * @param vcCtgryNm
	 *            the vcCtgryNm to set
	 */
	public void setVcCtgryNm(String vcCtgryNm) {
		this.vcCtgryNm = vcCtgryNm;
	}

	/**
	 * @return the vcCtgryPth
	 */
	public String getVcCtgryPth() {
		return vcCtgryPth;
	}

	/**
	 * @param vcCtgryPth
	 *            the vcCtgryPth to set
	 */
	public void setVcCtgryPth(String vcCtgryPth) {
		this.vcCtgryPth = vcCtgryPth;
	}

	/**
	 * @return the vcCtgryDsc
	 */
	public String getVcCtgryDsc() {
		return vcCtgryDsc;
	}

	/**
	 * @param vcCtgryDsc
	 *            the vcCtgryDsc to set
	 */
	public void setVcCtgryDsc(String vcCtgryDsc) {
		this.vcCtgryDsc = vcCtgryDsc;
	}

	/**
	 * @return the isPlchldr
	 */
	public int getIsPlchldr() {
		return isPlchldr;
	}

	/**
	 * @param isPlchldr
	 *            the isPlchldr to set
	 */
	public void setIsPlchldr(int isPlchldr) {
		this.isPlchldr = isPlchldr;
	}

	/**
	 * @return the ctgry_level
	 */
	public int getCtgry_level() {
		return ctgry_level;
	}

	/**
	 * @param ctgry_level
	 *            the ctgry_level to set
	 */
	public void setCtgry_level(int ctgry_level) {
		this.ctgry_level = ctgry_level;
	}

	/**
	 * @return the treeIndex
	 */
	public int getTreeIndex() {
		return treeIndex;
	}

	/**
	 * @param treeIndex
	 *            the treeIndex to set
	 */
	public void setTreeIndex(int treeIndex) {
		this.treeIndex = treeIndex;
	}

	/**
	 * @return the count
	 */
	public int getPrdctCount() {
		return prdctCount;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setPrdctCount(int prdctCount) {
		this.prdctCount = prdctCount;
	}

}
