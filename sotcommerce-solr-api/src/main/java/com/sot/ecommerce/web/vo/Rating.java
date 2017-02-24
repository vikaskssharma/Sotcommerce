/**
 * 
 */
package com.sot.ecommerce.web.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;

/**
 * @author simanchal.patra
 * 
 */
@Entity
@Table(name = "Rating")
public class Rating implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Field
	private String id;

	@Id
	@Field
	@Column(name = "NM_RTNG_ID")
	private long nmRtngId;

	@Field
	@Column(name = "NM_RTNG")
	private long nmRtng;

	@Field
	@Column(name = "rtngPrdctId")
	private long rtngPrdctId;

	@Field
	@Column(name = "rtngUsrId")
	private long rtngUsrId;

	public static Builder getBuilder(String id, long nmRtngId, long rtngPrdctId,
			long rtngUsrId) {

		return new Builder(id, nmRtngId, rtngPrdctId, rtngUsrId);
	}

	public static class Builder {

		private Rating build;

		public Builder(String id, long nmRtngId, long rtngPrdctId, long rtngUsrId) {

			build = new Rating();
			build.nmRtngId = nmRtngId;
			build.rtngPrdctId = rtngPrdctId;
			build.rtngUsrId = rtngUsrId;
		}

		public Rating build() {
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
	 * @return the nmRtngId
	 */
	public long getNmRtngId() {
		return nmRtngId;
	}

	/**
	 * @param nmRtngId the nmRtngId to set
	 */
	public void setNmRtngId(long nmRtngId) {
		this.nmRtngId = nmRtngId;
	}

	/**
	 * @return the nmRtng
	 */
	public long getNmRtng() {
		return nmRtng;
	}

	/**
	 * @param nmRtng the nmRtng to set
	 */
	public void setNmRtng(long nmRtng) {
		this.nmRtng = nmRtng;
	}

	/**
	 * @return the rtngPrdctId
	 */
	public long getRtngPrdctId() {
		return rtngPrdctId;
	}

	/**
	 * @param rtngPrdctId the rtngPrdctId to set
	 */
	public void setRtngPrdctId(long rtngPrdctId) {
		this.rtngPrdctId = rtngPrdctId;
	}

	/**
	 * @return the rtngUsrId
	 */
	public long getRtngUsrId() {
		return rtngUsrId;
	}

	/**
	 * @param rtngUsrId the rtngUsrId to set
	 */
	public void setRtngUsrId(long rtngUsrId) {
		this.rtngUsrId = rtngUsrId;
	}
}
