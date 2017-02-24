package com.sot.ecommerce.web.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;

@Entity
@Table(name = "Section")
public class Section {

	@Id
	@Field
	@Column(name = "NM_SCTN_SLR_ID")
	private String id;

	@Field
	@Column(name = "sctn_id")
	private long sctn_id;

	@Field
	@Column(name = "sctn_ctgry_id")
	private long sctn_ctgry_id;

	@Field
	@Column(name = "vcSctnNm")
	private String vcSctnNm;

	public static Builder getBuilder(String id, long sctn_id, long sctn_ctgry_id,
			String vcSctnNm) {
		return new Builder(id, sctn_id, sctn_ctgry_id, vcSctnNm);
	}

	public static class Builder {
		private Section build;

		public Builder(String id, long sctn_id, long sctn_ctgry_id,
				String vcSctnNm) {
			build = new Section();
			build.id = id;
			build.sctn_id = sctn_id;
			build.sctn_ctgry_id = sctn_ctgry_id;
			build.vcSctnNm = vcSctnNm;

		}

		public Section build() {
			return build;
		}
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
	 * @return the sctn_id
	 */
	public long getSctn_id() {
		return sctn_id;
	}

	/**
	 * @param sctn_id
	 *            the sctn_id to set
	 */
	public void setSctn_id(long sctn_id) {
		this.sctn_id = sctn_id;
	}

	/**
	 * @return the sctn_ctgry_id
	 */
	public long getSctn_ctgry_id() {
		return sctn_ctgry_id;
	}

	/**
	 * @param sctn_ctgry_id
	 *            the sctn_ctgry_id to set
	 */
	public void setSctn_ctgry_id(long sctn_ctgry_id) {
		this.sctn_ctgry_id = sctn_ctgry_id;
	}

	/**
	 * @return the vcSctnNm
	 */
	public String getVcSctnNm() {
		return vcSctnNm;
	}

	/**
	 * @param vcSctnNm
	 *            the vcSctnNm to set
	 */
	public void setVcSctnNm(String vcSctnNm) {
		this.vcSctnNm = vcSctnNm;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
