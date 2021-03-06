package com.sot.ecommerce.web.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;

@Entity
@Table(name = "Attribute")
public class Attribute {

	@Id
	@Field
	@Column(name = "NM_ATTR_SLR_ID")
	private String id;

	@Field
	@Column(name = "attr_id")
	private long attr_id;

	@Field
	@Column(name = "attr_ctgry_id")
	private long attr_ctgry_id;

	@Field
	@Column(name = "attr_sctn_id")
	private long attr_sctn_id;

	@Field
	@Column(name = "vcAttrNm")
	private String vcAttrNm;

	@Field
	@Column(name = "isSrchbl")
	private int isSrchbl;

	@Field
	@Column(name = "isVrnt")
	private int isVrnt;

	@Field
	@Column(name = "vcAttrMppng")
	private String vcAttrMppng;

	@Field
	@Column(name = "IS_IMGBL")
	private int isImgbl;

	@Field
	@Column(name = "isNmrc")
	private int isNmrc;

	@Field
	@Column(name = "vcUntTyp")
	private String vcUntTyp;

	@Field
	@Column(name = "vcUntTypMppng")
	private String vcUntTypMppng;

	public static class Builder {

		private Attribute build;

		public Builder(String id, long attr_id, long attr_ctgry_id,
				long attr_sctn_id, String vcAttrNm, int isSrchbl, int isVrnt,
				String vcAttrMppng, int isImgbl, int isNmrc, String vcUntTyp,
				String vcUntTypMppng) {

			build = new Attribute();
			build.id = id;
			build.attr_id = attr_id;
			build.attr_ctgry_id = attr_ctgry_id;
			build.attr_sctn_id = attr_sctn_id;
			build.vcAttrNm = vcAttrNm;
			build.isSrchbl = isSrchbl;
			build.isVrnt = isVrnt;
			build.vcAttrMppng = vcAttrMppng;
			build.isNmrc = isNmrc;
			build.vcUntTyp = vcUntTyp;
			build.vcUntTypMppng = vcUntTypMppng;
			build.isImgbl = isImgbl;
		}

		public Attribute build() {
			return build;
		}
	}

	public static Builder getBuilder(String id, long attr_id,
			long attr_ctgry_id, long attr_sctn_id, String vcAttrNm,
			int isSrchbl, int isVrnt, String vcAttrMppng, int isImgbl,
			int isNmrc, String vcUntTyp, String vcUntTypMppng) {
		return new Builder(id, attr_id, attr_ctgry_id, attr_sctn_id, vcAttrNm,
				isSrchbl, isVrnt, vcAttrMppng, isImgbl, isNmrc, vcUntTyp,
				vcUntTypMppng);
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
	 * @return the attr_id
	 */
	public long getAttr_id() {
		return attr_id;
	}

	/**
	 * @param attr_id
	 *            the attr_id to set
	 */
	public void setAttr_id(long attr_id) {
		this.attr_id = attr_id;
	}

	/**
	 * @return the attr_ctgry_id
	 */
	public long getAttr_ctgry_id() {
		return attr_ctgry_id;
	}

	/**
	 * @param attr_ctgry_id
	 *            the attr_ctgry_id to set
	 */
	public void setAttr_ctgry_id(long attr_ctgry_id) {
		this.attr_ctgry_id = attr_ctgry_id;
	}

	/**
	 * @return the attr_sctn_id
	 */
	public long getAttr_sctn_id() {
		return attr_sctn_id;
	}

	/**
	 * @param attr_sctn_id
	 *            the attr_sctn_id to set
	 */
	public void setAttr_sctn_id(long attr_sctn_id) {
		this.attr_sctn_id = attr_sctn_id;
	}

	/**
	 * @return the vcAttrNm
	 */
	public String getVcAttrNm() {
		return vcAttrNm;
	}

	/**
	 * @param vcAttrNm
	 *            the vcAttrNm to set
	 */
	public void setVcAttrNm(String vcAttrNm) {
		this.vcAttrNm = vcAttrNm;
	}

	/**
	 * @return the isSrchbl
	 */
	public int getIsSrchbl() {
		return isSrchbl;
	}

	/**
	 * @param isSrchbl
	 *            the isSrchbl to set
	 */
	public void setIsSrchbl(int isSrchbl) {
		this.isSrchbl = isSrchbl;
	}

	/**
	 * @return the isVrnt
	 */
	public int getIsVrnt() {
		return isVrnt;
	}

	/**
	 * @param isVrnt
	 *            the isVrnt to set
	 */
	public void setIsVrnt(int isVrnt) {
		this.isVrnt = isVrnt;
	}

	/**
	 * @return the vcAttrMppng
	 */
	public String getVcAttrMppng() {
		return vcAttrMppng;
	}

	/**
	 * @param vcAttrMppng
	 *            the vcAttrMppng to set
	 */
	public void setVcAttrMppng(String vcAttrMppng) {
		this.vcAttrMppng = vcAttrMppng;
	}

	/**
	 * @return the isImgbl
	 */
	public int getIsImgbl() {
		return isImgbl;
	}

	/**
	 * @param isImgbl
	 *            the isImgbl to set
	 */
	public void setIsImgbl(int isImgbl) {
		this.isImgbl = isImgbl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the isNmrc
	 */
	public int getIsNmrc() {
		return isNmrc;
	}

	/**
	 * @param isNmrc
	 *            the isNmrc to set
	 */
	public void setIsNmrc(int isNmrc) {
		this.isNmrc = isNmrc;
	}

	/**
	 * @return the vcUntTyp
	 */
	public String getVcUntTyp() {
		return vcUntTyp;
	}

	/**
	 * @param vcUntTyp
	 *            the vcUntTyp to set
	 */
	public void setVcUntTyp(String vcUntTyp) {
		this.vcUntTyp = vcUntTyp;
	}

	/**
	 * @return the vcUntTypMppng
	 */
	public String getVcUntTypMppng() {
		return vcUntTypMppng;
	}

	/**
	 * @param vcUntTypMppng
	 *            the vcUntTypMppng to set
	 */
	public void setVcUntTypMppng(String vcUntTypMppng) {
		this.vcUntTypMppng = vcUntTypMppng;
	}

}
