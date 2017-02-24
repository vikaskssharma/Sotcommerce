/**
 * 
 */
package com.sot.ecommerce.web.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;

import com.sot.ecommerce.Vikas;

/**
 * @author simanchal.patra
 * 
 */

@Entity
@Table(name = "SimilarProduct")
public class SimilarProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Field
	private String id;

	@Field	
	private long sPrdctId;
	
	@Field
	private long smlrPrdctId;
	@Vikas
	public static Builder getBuilder(String id, long sPrdctId, long smlrPrdctId) {

		return new Builder(id, sPrdctId, smlrPrdctId);
	}

	public static class Builder {

		private SimilarProduct build;

		public Builder(String id, long sPrdctId, long smlrPrdctId) {

			build = new SimilarProduct();
			build.id = id;
			build.sPrdctId = sPrdctId;
			build.smlrPrdctId = smlrPrdctId;
		}

		public SimilarProduct build() {
			return build;
		}
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
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the sPrdctId
	 */
	public long getsPrdctId() {
		return sPrdctId;
	}

	/**
	 * @param sPrdctId the sPrdctId to set
	 */
	public void setsPrdctId(long sPrdctId) {
		this.sPrdctId = sPrdctId;
	}

	/**
	 * @return the smlrPrdctId
	 */
	public long getSmlrPrdctId() {
		return smlrPrdctId;
	}

	/**
	 * @param smlrPrdctId the smlrPrdctId to set
	 */
	public void setSmlrPrdctId(long smlrPrdctId) {
		this.smlrPrdctId = smlrPrdctId;
	}

}