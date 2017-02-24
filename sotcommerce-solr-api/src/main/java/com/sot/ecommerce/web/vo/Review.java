/**
 * 
 */
package com.sot.ecommerce.web.vo;

/**
 * @author simanchal.patra
 *
 */

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "REVIEW")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Field
	private String id;

	@Field
	@Column(name = "nmRvwId")
	private long nmRvwId;

	@Field
	@Column(name = "rvwPrdId")
	private long rvwPrdId;

	@Field
	@Column(name = "rvwUsrId")
	private long rvwUsrId;

	@Field
	@Column(name = "vcRvwDscptn")
	private String vcRvwDscptn;

	@Field
	@Column(name = "rvwCrtdAt")
	private Date rvwCrtdAt;

	@Field
	@Column(name = "rvwUsrNm")
	private String rvwUsrNm;

	@Field
	@Column(name = "nmRtng")
	private Integer nmRtng;

	public static class Builder {

		private Review build;

		public Builder(String id, long nmRvwId, long rvwPrdId, long rvwUsrId,
				String vcRvwDscptn, Date rvwCrtdAt, String rvwUsrNm,
				Integer nmRtng) {

			build = new Review();
			build.id = id;
			build.nmRvwId = nmRvwId;
			build.rvwPrdId = rvwPrdId;
			build.rvwUsrId = rvwUsrId;
			build.vcRvwDscptn = vcRvwDscptn;
			build.rvwCrtdAt = rvwCrtdAt;
			build.rvwUsrNm = rvwUsrNm;
			build.nmRtng = nmRtng;
		}

		public Review build() {
			return build;
		}
	}

	public static Builder getBuilder(String id, long nmRvwId, long rvwPrdId,
			long rvwUsrId, String vcRvwDscptn, Date rvwCrtdAt, String rvwUsrNm,
			Integer nmRtng) {

		return new Builder(id, nmRvwId, rvwPrdId, rvwUsrId, vcRvwDscptn,
				rvwCrtdAt, rvwUsrNm, nmRtng);
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
	 * @return the nmRvwId
	 */
	public long getNmRvwId() {
		return nmRvwId;
	}

	/**
	 * @param nmRvwId
	 *            the nmRvwId to set
	 */
	public void setNmRvwId(long nmRvwId) {
		this.nmRvwId = nmRvwId;
	}

	/**
	 * @return the rvwPrdId
	 */
	public long getRvwPrdId() {
		return rvwPrdId;
	}

	/**
	 * @param rvwPrdId
	 *            the rvwPrdId to set
	 */
	public void setRvwPrdId(long rvwPrdId) {
		this.rvwPrdId = rvwPrdId;
	}

	/**
	 * @return the vcRvwDscptn
	 */
	public String getVcRvwDscptn() {
		return vcRvwDscptn;
	}

	/**
	 * @param vcRvwDscptn
	 *            the vcRvwDscptn to set
	 */
	public void setVcRvwDscptn(String vcRvwDscptn) {
		this.vcRvwDscptn = vcRvwDscptn;
	}

	/**
	 * @return the rvwCrtdAT
	 */
	public Date getRvwCrtdAt() {
		return rvwCrtdAt;
	}

	/**
	 * @param rvwCrtdAT
	 *            the rvwCrtdAT to set
	 */
	public void setRvwCrtdAt(Date rvwCrtdAt) {
		this.rvwCrtdAt = rvwCrtdAt;
	}

	/**
	 * @return the rvwUsrId
	 */
	public long getRvwUsrId() {
		return rvwUsrId;
	}

	/**
	 * @param rvwUsrId
	 *            the rvwUsrId to set
	 */
	public void setRvwUsrId(long rvwUsrId) {
		this.rvwUsrId = rvwUsrId;
	}

	/**
	 * @return the rvwUsrNm
	 */
	public String getRvwUsrNm() {
		return rvwUsrNm;
	}

	/**
	 * @param rvwUsrNm
	 *            the rvwUsrNm to set
	 */
	public void setRvwUsrNm(String rvwUsrNm) {
		this.rvwUsrNm = rvwUsrNm;
	}

	/**
	 * @return the nmRtng
	 */
	public Integer getNmRtng() {
		return nmRtng;
	}

	/**
	 * @param nmRtng
	 *            the nmRtng to set
	 */
	public void setNmRtng(Integer nmRtng) {
		this.nmRtng = nmRtng;
	}

}