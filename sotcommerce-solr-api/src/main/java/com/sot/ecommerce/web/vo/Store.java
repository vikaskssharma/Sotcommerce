package com.sot.ecommerce.web.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;

@Entity
@Table(name = "Store")
public class Store {

	@Id
	@Field
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Field
	@Column(name = "store_id")
	private long store_id;

	@Field
	@Column(name = "vcStrNm")
	private String vcStrNm;

	public static class Builder {
		private Store build;

		public Builder(long id, long store_id, String vcStrNm) {
			build = new Store();
			build.id = id;
			build.store_id = store_id;
			build.vcStrNm = vcStrNm;
		}

		public Store build() {
			return build;
		}
	}

	public static Builder getBuilder(long id, long store_id, String vcStrNm) {
		return new Builder(id, store_id, vcStrNm);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the store_id
	 */
	public long getStore_id() {
		return store_id;
	}

	/**
	 * @param store_id
	 *            the store_id to set
	 */
	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}

	/**
	 * @return the vcStrNm
	 */
	public String getVcStrNm() {
		return vcStrNm;
	}

	/**
	 * @param vcStrNm
	 *            the vcStrNm to set
	 */
	public void setVcStrNm(String vcStrNm) {
		this.vcStrNm = vcStrNm;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
