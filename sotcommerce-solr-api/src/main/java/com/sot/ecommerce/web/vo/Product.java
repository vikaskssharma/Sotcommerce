package com.sot.ecommerce.web.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.solr.client.solrj.beans.Field;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@Field
	@Column(name = "id")
	private String id;

	@Field
	@Column(name = "prdct_id")
	private long prdct_id;

	@Field
	@Column(name = "prdct_ctgry_id")
	private long prdct_ctgry_id;

	@Field
	@Column(name = "nmVrntId")
	private long nmVrntId;

	@Field
	@Column(name = "vcPrdNm")
	private String vcPrdNm;

	@Field
	@Column(name = "vcVrntNm")
	private String vcVrntNm;

	@Field
	@Column(name = "vcPrdImg1Pth")
	private String vcPrdImg1Pth;

	@Field
	@Column(name = "vcPrdImg2Pth")
	private String vcPrdImg2Pth;

	@Field
	@Column(name = "vcPrdImg3Pth")
	private String vcPrdImg3Pth;

	@Field
	@Column(name = "vcPrdImg4Pth")
	private String vcPrdImg4Pth;

	@Field
	@Column(name = "isCod")
	private int isCod;

	@Field
	@Column(name = "isFrShpng")
	private int isFrShpng;

	@Field
	@Column(name = "isFtrd")
	private int isFtrd;

	@Field
	@Column(name = "isHtdl")
	private int isHtdl;

	@Field
	@Column(name = "isPrdctVrnt")
	private int isPrdctVrnt;

	@Field
	@Column(name = "vcPrdDsc")
	private String vcPrdDsc;

	@Field
	@Column(name = "nmDlPrc")
	private float nmDlPrc;

	@Field
	@Column(name = "nmSp")
	private float nmSp;

	@Field
	@Column(name = "nmQntty")
	private int nmQntty;

	@Field
	@Column(name = "VC_ATTR1_VALUE")
	private String VC_ATTR1_VALUE;

	@Field
	@Column(name = "VC_ATTR10_VALUE")
	private String VC_ATTR10_VALUE;

	@Field
	@Column(name = "VC_ATTR11_VALUE")
	private String VC_ATTR11_VALUE;

	@Field
	@Column(name = "VC_ATTR12_VALUE")
	private String VC_ATTR12_VALUE;

	@Field
	@Column(name = "VC_ATTR13_VALUE")
	private String VC_ATTR13_VALUE;

	@Field
	@Column(name = "VC_ATTR14_VALUE")
	private String VC_ATTR14_VALUE;

	@Field
	@Column(name = "VC_ATTR15_VALUE")
	private String VC_ATTR15_VALUE;

	@Field
	@Column(name = "VC_ATTR16_VALUE")
	private String VC_ATTR16_VALUE;

	@Field
	@Column(name = "VC_ATTR17_VALUE")
	private String VC_ATTR17_VALUE;

	@Field
	@Column(name = "VC_ATTR18_VALUE")
	private String VC_ATTR18_VALUE;

	@Field
	@Column(name = "VC_ATTR19_VALUE")
	private String VC_ATTR19_VALUE;

	@Field
	@Column(name = "VC_ATTR2_VALUE")
	private String VC_ATTR2_VALUE;

	@Field
	@Column(name = "VC_ATTR20_VALUE")
	private String VC_ATTR20_VALUE;

	@Field
	@Column(name = "VC_ATTR21_VALUE")
	private String VC_ATTR21_VALUE;

	@Field
	@Column(name = "VC_ATTR22_VALUE")
	private String VC_ATTR22_VALUE;

	@Field
	@Column(name = "VC_ATTR23_VALUE")
	private String VC_ATTR23_VALUE;

	@Field
	@Column(name = "VC_ATTR24_VALUE")
	private String VC_ATTR24_VALUE;

	@Field
	@Column(name = "VC_ATTR25_VALUE")
	private String VC_ATTR25_VALUE;

	@Field
	@Column(name = "VC_ATTR3_VALUE")
	private String VC_ATTR3_VALUE;

	@Field
	@Column(name = "VC_ATTR4_VALUE")
	private String VC_ATTR4_VALUE;

	@Field
	@Column(name = "VC_ATTR5_VALUE")
	private String VC_ATTR5_VALUE;

	@Field
	@Column(name = "VC_ATTR6_VALUE")
	private String VC_ATTR6_VALUE;

	@Field
	@Column(name = "VC_ATTR7_VALUE")
	private String VC_ATTR7_VALUE;

	@Field
	@Column(name = "VC_ATTR8_VALUE")
	private String VC_ATTR8_VALUE;

	@Field
	@Column(name = "VC_ATTR9_VALUE")
	private String VC_ATTR9_VALUE;

	@Field
	@Column(name = "NM_ATTR1_VALUE")
	private float NM_ATTR1_VALUE;

	@Field
	@Column(name = "NM_ATTR2_VALUE")
	private float NM_ATTR2_VALUE;

	@Field
	@Column(name = "NM_ATTR3_VALUE")
	private float NM_ATTR3_VALUE;

	@Field
	@Column(name = "NM_ATTR4_VALUE")
	private float NM_ATTR4_VALUE;

	@Field
	@Column(name = "NM_ATTR5_VALUE")
	private float NM_ATTR5_VALUE;

	@Field
	@Column(name = "VC_ATTR1_UNT_VALUE")
	private String VC_ATTR1_UNT_VALUE;

	@Field
	@Column(name = "VC_ATTR2_UNT_VALUE")
	private String VC_ATTR2_UNT_VALUE;

	@Field
	@Column(name = "VC_ATTR3_UNT_VALUE")
	private String VC_ATTR3_UNT_VALUE;

	@Field
	@Column(name = "VC_ATTR4_UNT_VALUE")
	private String VC_ATTR4_UNT_VALUE;

	@Field
	@Column(name = "VC_ATTR5_UNT_VALUE")
	private String VC_ATTR5_UNT_VALUE;

	@Field
	@Column(name = "VC_VRNT_IMG_1")
	private String VC_VRNT_IMG_1;

	@Field
	@Column(name = "VC_VRNT_IMG_2")
	private String VC_VRNT_IMG_2;

	@Field
	@Column(name = "VC_VRNT_IMG_3")
	private String VC_VRNT_IMG_3;

	@Field
	@Column(name = "VC_VRNT_OPTN_1")
	private String VC_VRNT_OPTN_1;

	@Field
	@Column(name = "VC_VRNT_OPTN_2")
	private String VC_VRNT_OPTN_2;

	@Field
	@Column(name = "VC_VRNT_OPTN_3")
	private String VC_VRNT_OPTN_3;

	@Field
	@Column(name = "VC_VRNT_OPTN_4")
	private String VC_VRNT_OPTN_4;

	@Field
	@Column(name = "VC_VRNT_OPTN_5")
	private String VC_VRNT_OPTN_5;

	@Field
	@Column(name = "NM_VRNT_OPTN_1")
	private float NM_VRNT_OPTN_1;

	@Field
	@Column(name = "NM_VRNT_OPTN_2")
	private float NM_VRNT_OPTN_2;

	@Field
	@Column(name = "NM_VRNT_OPTN_3")
	private float NM_VRNT_OPTN_3;

	@Field
	@Column(name = "NM_VRNT_OPTN_4")
	private float NM_VRNT_OPTN_4;

	@Field
	@Column(name = "NM_VRNT_OPTN_5")
	private float NM_VRNT_OPTN_5;

	@Field
	@Column(name = "VC_VRNT_OPTN_1_UNT")
	private String VC_VRNT_OPTN_1_UNT;

	@Field
	@Column(name = "VC_VRNT_OPTN_2_UNT")
	private String VC_VRNT_OPTN_2_UNT;

	@Field
	@Column(name = "VC_VRNT_OPTN_3_UNT")
	private String VC_VRNT_OPTN_3_UNT;

	@Field
	@Column(name = "VC_VRNT_OPTN_4_UNT")
	private String VC_VRNT_OPTN_4_UNT;

	@Field
	@Column(name = "VC_VRNT_OPTN_5_UNT")
	private String VC_VRNT_OPTN_5_UNT;
	
	@Field
	@Column(name = "dtCrtdAt")
	private Date dtCrtdAt;

	
	public static class Builder {
		private Product build;

		public Builder(String id, long prdct_id, long prdct_ctgry_id,
				String vcPrdNm, String vcVrntNm, String vcPrdImg1Pth,
				String vcPrdImg2Pth, String vcPrdImg3Pth, String vcPrdImg4Pth,
				int isCod, int isFrShpng, int isFtrd, int isHtdl,
				int isPrdctVrnt, String vcPrdDsc, float nmDlPrc, float nmSp,
				int nmQntty, String VC_ATTR1_VALUE, String VC_ATTR10_VALUE,
				String VC_ATTR11_VALUE, String VC_ATTR12_VALUE,
				String VC_ATTR13_VALUE, String VC_ATTR14_VALUE,
				String VC_ATTR15_VALUE, String VC_ATTR16_VALUE,
				String VC_ATTR17_VALUE, String VC_ATTR18_VALUE,
				String VC_ATTR19_VALUE, String VC_ATTR2_VALUE,
				String VC_ATTR20_VALUE, String VC_ATTR21_VALUE,
				String VC_ATTR22_VALUE, String VC_ATTR23_VALUE,
				String VC_ATTR24_VALUE, String VC_ATTR25_VALUE,
				String VC_ATTR3_VALUE, String VC_ATTR4_VALUE,
				String VC_ATTR5_VALUE, String VC_ATTR6_VALUE,
				String VC_ATTR7_VALUE, String VC_ATTR8_VALUE,
				String VC_ATTR9_VALUE, float NM_ATTR1_VALUE,
				float NM_ATTR2_VALUE, float NM_ATTR3_VALUE,
				float NM_ATTR4_VALUE, float NM_ATTR5_VALUE,
				String VC_ATTR1_UNT_VALUE, String VC_ATTR2_UNT_VALUE,
				String VC_ATTR3_UNT_VALUE, String VC_ATTR4_UNT_VALUE,
				String VC_ATTR5_UNT_VALUE, long nmVrntId, String VC_VRNT_IMG_1,
				String VC_VRNT_IMG_2, String VC_VRNT_IMG_3,
				String VC_VRNT_OPTN_1, String VC_VRNT_OPTN_2,
				String VC_VRNT_OPTN_3, String VC_VRNT_OPTN_4,
				String VC_VRNT_OPTN_5, float NM_VRNT_OPTN_1,
				float NM_VRNT_OPTN_2, float NM_VRNT_OPTN_3,
				float NM_VRNT_OPTN_4, float NM_VRNT_OPTN_5,
				String VC_VRNT_OPTN_1_UNT, String VC_VRNT_OPTN_2_UNT,
				String VC_VRNT_OPTN_3_UNT, String VC_VRNT_OPTN_4_UNT,
				String VC_VRNT_OPTN_5_UNT,Date dtCrtdAt) {

			build = new Product();

			build.id = id;
			build.prdct_id = prdct_id;
			build.prdct_ctgry_id = prdct_ctgry_id;
			build.nmVrntId = nmVrntId;
			build.vcPrdNm = vcPrdNm;
			build.vcVrntNm = vcVrntNm;
			build.vcPrdImg1Pth = vcPrdImg1Pth;
			build.vcPrdImg2Pth = vcPrdImg2Pth;
			build.vcPrdImg3Pth = vcPrdImg3Pth;
			build.vcPrdImg4Pth = vcPrdImg4Pth;
			build.isCod = isCod;
			build.isFrShpng = isFrShpng;
			build.isFtrd = isFtrd;
			build.isHtdl = isHtdl;
			build.isPrdctVrnt = isPrdctVrnt;
			build.vcPrdDsc = vcPrdDsc;
			build.nmDlPrc = nmDlPrc;
			build.nmSp = nmSp;
			build.nmQntty = nmQntty;
			build.VC_ATTR1_VALUE = VC_ATTR1_VALUE;
			build.VC_ATTR10_VALUE = VC_ATTR10_VALUE;
			build.VC_ATTR11_VALUE = VC_ATTR11_VALUE;
			build.VC_ATTR12_VALUE = VC_ATTR12_VALUE;
			build.VC_ATTR13_VALUE = VC_ATTR13_VALUE;
			build.VC_ATTR14_VALUE = VC_ATTR14_VALUE;
			build.VC_ATTR15_VALUE = VC_ATTR15_VALUE;
			build.VC_ATTR16_VALUE = VC_ATTR16_VALUE;
			build.VC_ATTR17_VALUE = VC_ATTR17_VALUE;
			build.VC_ATTR18_VALUE = VC_ATTR18_VALUE;
			build.VC_ATTR19_VALUE = VC_ATTR19_VALUE;
			build.VC_ATTR2_VALUE = VC_ATTR2_VALUE;
			build.VC_ATTR20_VALUE = VC_ATTR2_VALUE;
			build.VC_ATTR21_VALUE = VC_ATTR21_VALUE;
			build.VC_ATTR22_VALUE = VC_ATTR22_VALUE;
			build.VC_ATTR23_VALUE = VC_ATTR23_VALUE;
			build.VC_ATTR24_VALUE = VC_ATTR24_VALUE;
			build.VC_ATTR25_VALUE = VC_ATTR25_VALUE;
			build.VC_ATTR3_VALUE = VC_ATTR3_VALUE;
			build.VC_ATTR4_VALUE = VC_ATTR4_VALUE;
			build.VC_ATTR5_VALUE = VC_ATTR5_VALUE;
			build.VC_ATTR6_VALUE = VC_ATTR6_VALUE;
			build.VC_ATTR7_VALUE = VC_ATTR7_VALUE;
			build.VC_ATTR8_VALUE = VC_ATTR8_VALUE;
			build.VC_ATTR9_VALUE = VC_ATTR9_VALUE;
			build.NM_ATTR1_VALUE = NM_ATTR1_VALUE;
			build.NM_ATTR2_VALUE = NM_ATTR2_VALUE;
			build.NM_ATTR3_VALUE = NM_ATTR3_VALUE;
			build.NM_ATTR4_VALUE = NM_ATTR4_VALUE;
			build.NM_ATTR5_VALUE = NM_ATTR5_VALUE;
			build.VC_ATTR1_UNT_VALUE = VC_ATTR1_UNT_VALUE;
			build.VC_ATTR2_UNT_VALUE = VC_ATTR2_UNT_VALUE;
			build.VC_ATTR3_UNT_VALUE = VC_ATTR3_UNT_VALUE;
			build.VC_ATTR4_UNT_VALUE = VC_ATTR4_UNT_VALUE;
			build.VC_ATTR5_UNT_VALUE = VC_ATTR5_UNT_VALUE;
			build.VC_VRNT_IMG_1 = VC_VRNT_IMG_1;
			build.VC_VRNT_IMG_2 = VC_VRNT_IMG_2;
			build.VC_VRNT_IMG_3 = VC_VRNT_IMG_3;
			build.VC_VRNT_OPTN_1 = VC_VRNT_OPTN_1;
			build.VC_VRNT_OPTN_2 = VC_VRNT_OPTN_2;
			build.VC_VRNT_OPTN_3 = VC_VRNT_OPTN_3;
			build.VC_VRNT_OPTN_4 = VC_VRNT_OPTN_4;
			build.VC_VRNT_OPTN_5 = VC_VRNT_OPTN_5;
			build.NM_VRNT_OPTN_1 = NM_VRNT_OPTN_1;
			build.NM_VRNT_OPTN_2 = NM_VRNT_OPTN_2;
			build.NM_VRNT_OPTN_3 = NM_VRNT_OPTN_3;
			build.NM_VRNT_OPTN_4 = NM_VRNT_OPTN_4;
			build.NM_VRNT_OPTN_5 = NM_VRNT_OPTN_5;
			build.VC_VRNT_OPTN_1_UNT = VC_VRNT_OPTN_1_UNT;
			build.VC_VRNT_OPTN_2_UNT = VC_VRNT_OPTN_2_UNT;
			build.VC_VRNT_OPTN_3_UNT = VC_VRNT_OPTN_3_UNT;
			build.VC_VRNT_OPTN_4_UNT = VC_VRNT_OPTN_4_UNT;
			build.VC_VRNT_OPTN_5_UNT = VC_VRNT_OPTN_5_UNT;
			build.dtCrtdAt=dtCrtdAt;
		}

		public Product build() {
			return build;
		}
	}

	public static Builder getBuilder(String id, long prdct_id,
			long prdct_ctgry_id, String vcPrdNm, String vcVrntNm, String vcSts,
			String vcPrdImg1Pth, String vcPrdImg2Pth, String vcPrdImg3Pth,
			String vcPrdImg4Pth, int isCod, int isFrShpng, int isFtrd,
			int isHtdl, int isPrdctVrnt, String vcPrdDsc, float nmMrp,
			float nmSp, int nmQntty, String VC_ATTR1_VALUE,
			String VC_ATTR10_VALUE, String VC_ATTR11_VALUE,
			String VC_ATTR12_VALUE, String VC_ATTR13_VALUE,
			String VC_ATTR14_VALUE, String VC_ATTR15_VALUE,
			String VC_ATTR16_VALUE, String VC_ATTR17_VALUE,
			String VC_ATTR18_VALUE, String VC_ATTR19_VALUE,
			String VC_ATTR2_VALUE, String VC_ATTR20_VALUE,
			String VC_ATTR21_VALUE, String VC_ATTR22_VALUE,
			String VC_ATTR23_VALUE, String VC_ATTR24_VALUE,
			String VC_ATTR25_VALUE, String VC_ATTR3_VALUE,
			String VC_ATTR4_VALUE, String VC_ATTR5_VALUE,
			String VC_ATTR6_VALUE, String VC_ATTR7_VALUE,
			String VC_ATTR8_VALUE, String VC_ATTR9_VALUE, float NM_ATTR1_VALUE,
			float NM_ATTR2_VALUE, float NM_ATTR3_VALUE, float NM_ATTR4_VALUE,
			float NM_ATTR5_VALUE, String VC_ATTR1_UNT_VALUE,
			String VC_ATTR2_UNT_VALUE, String VC_ATTR3_UNT_VALUE,
			String VC_ATTR4_UNT_VALUE, String VC_ATTR5_UNT_VALUE,
			long nmVrntId, String VC_VRNT_IMG_1, String VC_VRNT_IMG_2,
			String VC_VRNT_IMG_3, String VC_VRNT_OPTN_1, String VC_VRNT_OPTN_2,
			String VC_VRNT_OPTN_3, String VC_VRNT_OPTN_4,
			String VC_VRNT_OPTN_5, float NM_VRNT_OPTN_1, float NM_VRNT_OPTN_2,
			float NM_VRNT_OPTN_3, float NM_VRNT_OPTN_4, float NM_VRNT_OPTN_5,
			String VC_VRNT_OPTN_1_UNT, String VC_VRNT_OPTN_2_UNT,
			String VC_VRNT_OPTN_3_UNT, String VC_VRNT_OPTN_4_UNT,
			String VC_VRNT_OPTN_5_UNT,Date dtCrtdAt) {

		return new Builder(id, prdct_id, prdct_ctgry_id, vcPrdNm, vcVrntNm,
				vcPrdImg1Pth, vcPrdImg2Pth, vcPrdImg3Pth, vcPrdImg4Pth, isCod,
				isFrShpng, isFtrd, isHtdl, isPrdctVrnt, vcPrdDsc, nmMrp, nmSp,
				nmQntty, VC_ATTR1_VALUE, VC_ATTR10_VALUE, VC_ATTR11_VALUE,
				VC_ATTR12_VALUE, VC_ATTR13_VALUE, VC_ATTR14_VALUE,
				VC_ATTR15_VALUE, VC_ATTR16_VALUE, VC_ATTR17_VALUE,
				VC_ATTR18_VALUE, VC_ATTR19_VALUE, VC_ATTR2_VALUE,
				VC_ATTR20_VALUE, VC_ATTR21_VALUE, VC_ATTR22_VALUE,
				VC_ATTR23_VALUE, VC_ATTR24_VALUE, VC_ATTR25_VALUE,
				VC_ATTR3_VALUE, VC_ATTR4_VALUE, VC_ATTR5_VALUE, VC_ATTR6_VALUE,
				VC_ATTR7_VALUE, VC_ATTR8_VALUE, VC_ATTR9_VALUE, NM_ATTR1_VALUE,
				NM_ATTR2_VALUE, NM_ATTR3_VALUE, NM_ATTR4_VALUE, NM_ATTR5_VALUE,
				VC_ATTR1_UNT_VALUE, VC_ATTR2_UNT_VALUE, VC_ATTR3_UNT_VALUE,
				VC_ATTR4_UNT_VALUE, VC_ATTR5_UNT_VALUE, nmVrntId,
				VC_VRNT_IMG_1, VC_VRNT_IMG_2, VC_VRNT_IMG_3, VC_VRNT_OPTN_1,
				VC_VRNT_OPTN_2, VC_VRNT_OPTN_3, VC_VRNT_OPTN_4, VC_VRNT_OPTN_5,
				NM_VRNT_OPTN_1, NM_VRNT_OPTN_2, NM_VRNT_OPTN_3, NM_VRNT_OPTN_4,
				NM_VRNT_OPTN_5, VC_VRNT_OPTN_1_UNT, VC_VRNT_OPTN_2_UNT,
				VC_VRNT_OPTN_3_UNT, VC_VRNT_OPTN_4_UNT, VC_VRNT_OPTN_5_UNT,dtCrtdAt);
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
	 * @return the prdct_id
	 */
	public long getPrdct_id() {
		return prdct_id;
	}

	/**
	 * @param prdct_id
	 *            the prdct_id to set
	 */
	public void setPrdct_id(long prdct_id) {
		this.prdct_id = prdct_id;
	}

	/**
	 * @return the prdct_ctgry_id
	 */
	public long getPrdct_ctgry_id() {
		return prdct_ctgry_id;
	}

	/**
	 * @param prdct_ctgry_id
	 *            the prdct_ctgry_id to set
	 */
	public void setPrdct_ctgry_id(long prdct_ctgry_id) {
		this.prdct_ctgry_id = prdct_ctgry_id;
	}

	/**
	 * @return the vcPrdNm
	 */
	public String getVcPrdNm() {
		return vcPrdNm;
	}

	/**
	 * @param vcPrdNm
	 *            the vcPrdNm to set
	 */
	public void setVcPrdNm(String vcPrdNm) {
		this.vcPrdNm = vcPrdNm;
	}

	/**
	 * @return the vcVrntNm
	 */
	public String getVcVrntNm() {
		return vcVrntNm;
	}

	/**
	 * @param vcVrntNm the vcVrntNm to set
	 */
	public void setVcVrntNm(String vcVrntNm) {
		this.vcVrntNm = vcVrntNm;
	}

	/**
	 * @return the vcPrdImg1Pth
	 */
	public String getVcPrdImg1Pth() {
		return vcPrdImg1Pth;
	}

	/**
	 * @param vcPrdImg1Pth
	 *            the vcPrdImg1Pth to set
	 */
	public void setVcPrdImg1Pth(String vcPrdImg1Pth) {
		this.vcPrdImg1Pth = vcPrdImg1Pth;
	}

	/**
	 * @return the vcPrdImg2Pth
	 */
	public String getVcPrdImg2Pth() {
		return vcPrdImg2Pth;
	}

	/**
	 * @param vcPrdImg2Pth
	 *            the vcPrdImg2Pth to set
	 */
	public void setVcPrdImg2Pth(String vcPrdImg2Pth) {
		this.vcPrdImg2Pth = vcPrdImg2Pth;
	}

	/**
	 * @return the vcPrdImg3Pth
	 */
	public String getVcPrdImg3Pth() {
		return vcPrdImg3Pth;
	}

	/**
	 * @param vcPrdImg3Pth
	 *            the vcPrdImg3Pth to set
	 */
	public void setVcPrdImg3Pth(String vcPrdImg3Pth) {
		this.vcPrdImg3Pth = vcPrdImg3Pth;
	}

	/**
	 * @return the vcPrdImg4Pth
	 */
	public String getVcPrdImg4Pth() {
		return vcPrdImg4Pth;
	}

	/**
	 * @param vcPrdImg4Pth
	 *            the vcPrdImg4Pth to set
	 */
	public void setVcPrdImg4Pth(String vcPrdImg4Pth) {
		this.vcPrdImg4Pth = vcPrdImg4Pth;
	}

	/**
	 * @return the isCod
	 */
	public int getIsCod() {
		return isCod;
	}

	/**
	 * @param isCod
	 *            the isCod to set
	 */
	public void setIsCod(int isCod) {
		this.isCod = isCod;
	}

	/**
	 * @return the isFrShpng
	 */
	public int getIsFrShpng() {
		return isFrShpng;
	}

	/**
	 * @param isFrShpng
	 *            the isFrShpng to set
	 */
	public void setIsFrShpng(int isFrShpng) {
		this.isFrShpng = isFrShpng;
	}

	/**
	 * @return the isFtrd
	 */
	public int getIsFtrd() {
		return isFtrd;
	}

	/**
	 * @param isFtrd
	 *            the isFtrd to set
	 */
	public void setIsFtrd(int isFtrd) {
		this.isFtrd = isFtrd;
	}

	/**
	 * @return the isHtdl
	 */
	public int getIsHtdl() {
		return isHtdl;
	}

	/**
	 * @param isHtdl
	 *            the isHtdl to set
	 */
	public void setIsHtdl(int isHtdl) {
		this.isHtdl = isHtdl;
	}

	/**
	 * @return the isPrdctVrnt
	 */
	public int getIsPrdctVrnt() {
		return isPrdctVrnt;
	}

	/**
	 * @param isPrdctVrnt
	 *            the isPrdctVrnt to set
	 */
	public void setIsPrdctVrnt(int isPrdctVrnt) {
		this.isPrdctVrnt = isPrdctVrnt;
	}

	/**
	 * @return the vcPrdDsc
	 */
	public String getVcPrdDsc() {
		return vcPrdDsc;
	}

	/**
	 * @param vcPrdDsc
	 *            the vcPrdDsc to set
	 */
	public void setVcPrdDsc(String vcPrdDsc) {
		this.vcPrdDsc = vcPrdDsc;
	}

	/**
	 * @return the nmMrp
	 */
	public float getNmDlPrc() {
		return nmDlPrc;
	}

	/**
	 * @param nmMrp
	 *            the nmMrp to set
	 */
	public void setNmDlPrc(float nmDlPrc) {
		this.nmDlPrc = nmDlPrc;
	}

	/**
	 * @return the nmSp
	 */
	public float getNmSp() {
		return nmSp;
	}

	/**
	 * @param nmSp
	 *            the nmSp to set
	 */
	public void setNmSp(float nmSp) {
		this.nmSp = nmSp;
	}

	/**
	 * @return the nmQnty
	 */
	public int getNmQntty() {
		return nmQntty;
	}

	/**
	 * @param nmQnty
	 *            the nmQnty to set
	 */
	public void setNmQntty(int nmQntty) {
		this.nmQntty = nmQntty;
	}

	/**
	 * @return the vC_ATTR1_VALUE
	 */
	public String getVC_ATTR1_VALUE() {
		return VC_ATTR1_VALUE;
	}

	/**
	 * @param vC_ATTR1_VALUE
	 *            the vC_ATTR1_VALUE to set
	 */
	public void setVC_ATTR1_VALUE(String vC_ATTR1_VALUE) {
		VC_ATTR1_VALUE = vC_ATTR1_VALUE;
	}

	/**
	 * @return the vC_ATTR10_VALUE
	 */
	public String getVC_ATTR10_VALUE() {
		return VC_ATTR10_VALUE;
	}

	/**
	 * @param vC_ATTR10_VALUE
	 *            the vC_ATTR10_VALUE to set
	 */
	public void setVC_ATTR10_VALUE(String vC_ATTR10_VALUE) {
		VC_ATTR10_VALUE = vC_ATTR10_VALUE;
	}

	/**
	 * @return the vC_ATTR11_VALUE
	 */
	public String getVC_ATTR11_VALUE() {
		return VC_ATTR11_VALUE;
	}

	/**
	 * @param vC_ATTR11_VALUE
	 *            the vC_ATTR11_VALUE to set
	 */
	public void setVC_ATTR11_VALUE(String vC_ATTR11_VALUE) {
		VC_ATTR11_VALUE = vC_ATTR11_VALUE;
	}

	/**
	 * @return the vC_ATTR12_VALUE
	 */
	public String getVC_ATTR12_VALUE() {
		return VC_ATTR12_VALUE;
	}

	/**
	 * @param vC_ATTR12_VALUE
	 *            the vC_ATTR12_VALUE to set
	 */
	public void setVC_ATTR12_VALUE(String vC_ATTR12_VALUE) {
		VC_ATTR12_VALUE = vC_ATTR12_VALUE;
	}

	/**
	 * @return the vC_ATTR13_VALUE
	 */
	public String getVC_ATTR13_VALUE() {
		return VC_ATTR13_VALUE;
	}

	/**
	 * @param vC_ATTR13_VALUE
	 *            the vC_ATTR13_VALUE to set
	 */
	public void setVC_ATTR13_VALUE(String vC_ATTR13_VALUE) {
		VC_ATTR13_VALUE = vC_ATTR13_VALUE;
	}

	/**
	 * @return the vC_ATTR14_VALUE
	 */
	public String getVC_ATTR14_VALUE() {
		return VC_ATTR14_VALUE;
	}

	/**
	 * @param vC_ATTR14_VALUE
	 *            the vC_ATTR14_VALUE to set
	 */
	public void setVC_ATTR14_VALUE(String vC_ATTR14_VALUE) {
		VC_ATTR14_VALUE = vC_ATTR14_VALUE;
	}

	/**
	 * @return the vC_ATTR15_VALUE
	 */
	public String getVC_ATTR15_VALUE() {
		return VC_ATTR15_VALUE;
	}

	/**
	 * @param vC_ATTR15_VALUE
	 *            the vC_ATTR15_VALUE to set
	 */
	public void setVC_ATTR15_VALUE(String vC_ATTR15_VALUE) {
		VC_ATTR15_VALUE = vC_ATTR15_VALUE;
	}

	/**
	 * @return the vC_ATTR16_VALUE
	 */
	public String getVC_ATTR16_VALUE() {
		return VC_ATTR16_VALUE;
	}

	/**
	 * @param vC_ATTR16_VALUE
	 *            the vC_ATTR16_VALUE to set
	 */
	public void setVC_ATTR16_VALUE(String vC_ATTR16_VALUE) {
		VC_ATTR16_VALUE = vC_ATTR16_VALUE;
	}

	/**
	 * @return the vC_ATTR17_VALUE
	 */
	public String getVC_ATTR17_VALUE() {
		return VC_ATTR17_VALUE;
	}

	/**
	 * @param vC_ATTR17_VALUE
	 *            the vC_ATTR17_VALUE to set
	 */
	public void setVC_ATTR17_VALUE(String vC_ATTR17_VALUE) {
		VC_ATTR17_VALUE = vC_ATTR17_VALUE;
	}

	/**
	 * @return the vC_ATTR18_VALUE
	 */
	public String getVC_ATTR18_VALUE() {
		return VC_ATTR18_VALUE;
	}

	/**
	 * @param vC_ATTR18_VALUE
	 *            the vC_ATTR18_VALUE to set
	 */
	public void setVC_ATTR18_VALUE(String vC_ATTR18_VALUE) {
		VC_ATTR18_VALUE = vC_ATTR18_VALUE;
	}

	/**
	 * @return the vC_ATTR19_VALUE
	 */
	public String getVC_ATTR19_VALUE() {
		return VC_ATTR19_VALUE;
	}

	/**
	 * @param vC_ATTR19_VALUE
	 *            the vC_ATTR19_VALUE to set
	 */
	public void setVC_ATTR19_VALUE(String vC_ATTR19_VALUE) {
		VC_ATTR19_VALUE = vC_ATTR19_VALUE;
	}

	/**
	 * @return the vC_ATTR2_VALUE
	 */
	public String getVC_ATTR2_VALUE() {
		return VC_ATTR2_VALUE;
	}

	/**
	 * @param vC_ATTR2_VALUE
	 *            the vC_ATTR2_VALUE to set
	 */
	public void setVC_ATTR2_VALUE(String vC_ATTR2_VALUE) {
		VC_ATTR2_VALUE = vC_ATTR2_VALUE;
	}

	/**
	 * @return the vC_ATTR20_VALUE
	 */
	public String getVC_ATTR20_VALUE() {
		return VC_ATTR20_VALUE;
	}

	/**
	 * @param vC_ATTR20_VALUE
	 *            the vC_ATTR20_VALUE to set
	 */
	public void setVC_ATTR20_VALUE(String vC_ATTR20_VALUE) {
		VC_ATTR20_VALUE = vC_ATTR20_VALUE;
	}

	/**
	 * @return the vC_ATTR21_VALUE
	 */
	public String getVC_ATTR21_VALUE() {
		return VC_ATTR21_VALUE;
	}

	/**
	 * @param vC_ATTR21_VALUE
	 *            the vC_ATTR21_VALUE to set
	 */
	public void setVC_ATTR21_VALUE(String vC_ATTR21_VALUE) {
		VC_ATTR21_VALUE = vC_ATTR21_VALUE;
	}

	/**
	 * @return the vC_ATTR22_VALUE
	 */
	public String getVC_ATTR22_VALUE() {
		return VC_ATTR22_VALUE;
	}

	/**
	 * @param vC_ATTR22_VALUE
	 *            the vC_ATTR22_VALUE to set
	 */
	public void setVC_ATTR22_VALUE(String vC_ATTR22_VALUE) {
		VC_ATTR22_VALUE = vC_ATTR22_VALUE;
	}

	/**
	 * @return the vC_ATTR23_VALUE
	 */
	public String getVC_ATTR23_VALUE() {
		return VC_ATTR23_VALUE;
	}

	/**
	 * @param vC_ATTR23_VALUE
	 *            the vC_ATTR23_VALUE to set
	 */
	public void setVC_ATTR23_VALUE(String vC_ATTR23_VALUE) {
		VC_ATTR23_VALUE = vC_ATTR23_VALUE;
	}

	/**
	 * @return the vC_ATTR24_VALUE
	 */
	public String getVC_ATTR24_VALUE() {
		return VC_ATTR24_VALUE;
	}

	/**
	 * @param vC_ATTR24_VALUE
	 *            the vC_ATTR24_VALUE to set
	 */
	public void setVC_ATTR24_VALUE(String vC_ATTR24_VALUE) {
		VC_ATTR24_VALUE = vC_ATTR24_VALUE;
	}

	/**
	 * @return the vC_ATTR25_VALUE
	 */
	public String getVC_ATTR25_VALUE() {
		return VC_ATTR25_VALUE;
	}

	/**
	 * @param vC_ATTR25_VALUE
	 *            the vC_ATTR25_VALUE to set
	 */
	public void setVC_ATTR25_VALUE(String vC_ATTR25_VALUE) {
		VC_ATTR25_VALUE = vC_ATTR25_VALUE;
	}

	/**
	 * @return the vC_ATTR3_VALUE
	 */
	public String getVC_ATTR3_VALUE() {
		return VC_ATTR3_VALUE;
	}

	/**
	 * @param vC_ATTR3_VALUE
	 *            the vC_ATTR3_VALUE to set
	 */
	public void setVC_ATTR3_VALUE(String vC_ATTR3_VALUE) {
		VC_ATTR3_VALUE = vC_ATTR3_VALUE;
	}

	/**
	 * @return the vC_ATTR4_VALUE
	 */
	public String getVC_ATTR4_VALUE() {
		return VC_ATTR4_VALUE;
	}

	/**
	 * @param vC_ATTR4_VALUE
	 *            the vC_ATTR4_VALUE to set
	 */
	public void setVC_ATTR4_VALUE(String vC_ATTR4_VALUE) {
		VC_ATTR4_VALUE = vC_ATTR4_VALUE;
	}

	/**
	 * @return the vC_ATTR5_VALUE
	 */
	public String getVC_ATTR5_VALUE() {
		return VC_ATTR5_VALUE;
	}

	/**
	 * @param vC_ATTR5_VALUE
	 *            the vC_ATTR5_VALUE to set
	 */
	public void setVC_ATTR5_VALUE(String vC_ATTR5_VALUE) {
		VC_ATTR5_VALUE = vC_ATTR5_VALUE;
	}

	/**
	 * @return the vC_ATTR6_VALUE
	 */
	public String getVC_ATTR6_VALUE() {
		return VC_ATTR6_VALUE;
	}

	/**
	 * @param vC_ATTR6_VALUE
	 *            the vC_ATTR6_VALUE to set
	 */
	public void setVC_ATTR6_VALUE(String vC_ATTR6_VALUE) {
		VC_ATTR6_VALUE = vC_ATTR6_VALUE;
	}

	/**
	 * @return the vC_ATTR7_VALUE
	 */
	public String getVC_ATTR7_VALUE() {
		return VC_ATTR7_VALUE;
	}

	/**
	 * @param vC_ATTR7_VALUE
	 *            the vC_ATTR7_VALUE to set
	 */
	public void setVC_ATTR7_VALUE(String vC_ATTR7_VALUE) {
		VC_ATTR7_VALUE = vC_ATTR7_VALUE;
	}

	/**
	 * @return the vC_ATTR8_VALUE
	 */
	public String getVC_ATTR8_VALUE() {
		return VC_ATTR8_VALUE;
	}

	/**
	 * @param vC_ATTR8_VALUE
	 *            the vC_ATTR8_VALUE to set
	 */
	public void setVC_ATTR8_VALUE(String vC_ATTR8_VALUE) {
		VC_ATTR8_VALUE = vC_ATTR8_VALUE;
	}

	/**
	 * @return the vC_ATTR9_VALUE
	 */
	public String getVC_ATTR9_VALUE() {
		return VC_ATTR9_VALUE;
	}

	/**
	 * @param vC_ATTR9_VALUE
	 *            the vC_ATTR9_VALUE to set
	 */
	public void setVC_ATTR9_VALUE(String vC_ATTR9_VALUE) {
		VC_ATTR9_VALUE = vC_ATTR9_VALUE;
	}

	/**
	 * @return the nM_ATTR1_VALUE
	 */
	public float getNM_ATTR1_VALUE() {
		return NM_ATTR1_VALUE;
	}

	/**
	 * @param nM_ATTR1_VALUE
	 *            the nM_ATTR1_VALUE to set
	 */
	public void setNM_ATTR1_VALUE(float nM_ATTR1_VALUE) {
		NM_ATTR1_VALUE = nM_ATTR1_VALUE;
	}

	/**
	 * @return the nM_ATTR2_VALUE
	 */
	public float getNM_ATTR2_VALUE() {
		return NM_ATTR2_VALUE;
	}

	/**
	 * @param nM_ATTR2_VALUE
	 *            the nM_ATTR2_VALUE to set
	 */
	public void setNM_ATTR2_VALUE(float nM_ATTR2_VALUE) {
		NM_ATTR2_VALUE = nM_ATTR2_VALUE;
	}

	/**
	 * @return the nM_ATTR3_VALUE
	 */
	public float getNM_ATTR3_VALUE() {
		return NM_ATTR3_VALUE;
	}

	/**
	 * @param nM_ATTR3_VALUE
	 *            the nM_ATTR3_VALUE to set
	 */
	public void setNM_ATTR3_VALUE(float nM_ATTR3_VALUE) {
		NM_ATTR3_VALUE = nM_ATTR3_VALUE;
	}

	/**
	 * @return the nM_ATTR4_VALUE
	 */
	public float getNM_ATTR4_VALUE() {
		return NM_ATTR4_VALUE;
	}

	/**
	 * @param nM_ATTR4_VALUE
	 *            the nM_ATTR4_VALUE to set
	 */
	public void setNM_ATTR4_VALUE(float nM_ATTR4_VALUE) {
		NM_ATTR4_VALUE = nM_ATTR4_VALUE;
	}

	/**
	 * @return the nM_ATTR5_VALUE
	 */
	public float getNM_ATTR5_VALUE() {
		return NM_ATTR5_VALUE;
	}

	/**
	 * @param nM_ATTR5_VALUE
	 *            the nM_ATTR5_VALUE to set
	 */
	public void setNM_ATTR5_VALUE(float nM_ATTR5_VALUE) {
		NM_ATTR5_VALUE = nM_ATTR5_VALUE;
	}

	/**
	 * @return the vC_ATTR1_UNT_VALUE
	 */
	public String getVC_ATTR1_UNT_VALUE() {
		return VC_ATTR1_UNT_VALUE;
	}

	/**
	 * @param vC_ATTR1_UNT_VALUE
	 *            the vC_ATTR1_UNT_VALUE to set
	 */
	public void setVC_ATTR1_UNT_VALUE(String vC_ATTR1_UNT_VALUE) {
		VC_ATTR1_UNT_VALUE = vC_ATTR1_UNT_VALUE;
	}

	/**
	 * @return the vC_ATTR2_UNT_VALUE
	 */
	public String getVC_ATTR2_UNT_VALUE() {
		return VC_ATTR2_UNT_VALUE;
	}

	/**
	 * @param vC_ATTR2_UNT_VALUE
	 *            the vC_ATTR2_UNT_VALUE to set
	 */
	public void setVC_ATTR2_UNT_VALUE(String vC_ATTR2_UNT_VALUE) {
		VC_ATTR2_UNT_VALUE = vC_ATTR2_UNT_VALUE;
	}

	/**
	 * @return the vC_ATTR3_UNT_VALUE
	 */
	public String getVC_ATTR3_UNT_VALUE() {
		return VC_ATTR3_UNT_VALUE;
	}

	/**
	 * @param vC_ATTR3_UNT_VALUE
	 *            the vC_ATTR3_UNT_VALUE to set
	 */
	public void setVC_ATTR3_UNT_VALUE(String vC_ATTR3_UNT_VALUE) {
		VC_ATTR3_UNT_VALUE = vC_ATTR3_UNT_VALUE;
	}

	/**
	 * @return the vC_ATTR4_UNT_VALUE
	 */
	public String getVC_ATTR4_UNT_VALUE() {
		return VC_ATTR4_UNT_VALUE;
	}

	/**
	 * @param vC_ATTR4_UNT_VALUE
	 *            the vC_ATTR4_UNT_VALUE to set
	 */
	public void setVC_ATTR4_UNT_VALUE(String vC_ATTR4_UNT_VALUE) {
		VC_ATTR4_UNT_VALUE = vC_ATTR4_UNT_VALUE;
	}

	/**
	 * @return the vC_ATTR5_UNT_VALUE
	 */
	public String getVC_ATTR5_UNT_VALUE() {
		return VC_ATTR5_UNT_VALUE;
	}

	/**
	 * @param vC_ATTR5_UNT_VALUE
	 *            the vC_ATTR5_UNT_VALUE to set
	 */
	public void setVC_ATTR5_UNT_VALUE(String vC_ATTR5_UNT_VALUE) {
		VC_ATTR5_UNT_VALUE = vC_ATTR5_UNT_VALUE;
	}

	/**
	 * @return the nmVrntId
	 */
	public long getNmVrntId() {
		return nmVrntId;
	}

	/**
	 * @param nmVrntId
	 *            the nmVrntId to set
	 */
	public void setNmVrntId(long nmVrntId) {
		this.nmVrntId = nmVrntId;
	}

	/**
	 * @return the vC_VRNT_IMG_1
	 */
	public String getVC_VRNT_IMG_1() {
		return VC_VRNT_IMG_1;
	}

	/**
	 * @param vC_VRNT_IMG_1
	 *            the vC_VRNT_IMG_1 to set
	 */
	public void setVC_VRNT_IMG_1(String vC_VRNT_IMG_1) {
		VC_VRNT_IMG_1 = vC_VRNT_IMG_1;
	}

	/**
	 * @return the vC_VRNT_IMG_2
	 */
	public String getVC_VRNT_IMG_2() {
		return VC_VRNT_IMG_2;
	}

	/**
	 * @param vC_VRNT_IMG_2
	 *            the vC_VRNT_IMG_2 to set
	 */
	public void setVC_VRNT_IMG_2(String vC_VRNT_IMG_2) {
		VC_VRNT_IMG_2 = vC_VRNT_IMG_2;
	}

	/**
	 * @return the vC_VRNT_IMG_3
	 */
	public String getVC_VRNT_IMG_3() {
		return VC_VRNT_IMG_3;
	}

	/**
	 * @param vC_VRNT_IMG_3
	 *            the vC_VRNT_IMG_3 to set
	 */
	public void setVC_VRNT_IMG_3(String vC_VRNT_IMG_3) {
		VC_VRNT_IMG_3 = vC_VRNT_IMG_3;
	}

	/**
	 * @return the vC_VRNT_OPTN_1
	 */
	public String getVC_VRNT_OPTN_1() {
		return VC_VRNT_OPTN_1;
	}

	/**
	 * @param vC_VRNT_OPTN_1
	 *            the vC_VRNT_OPTN_1 to set
	 */
	public void setVC_VRNT_OPTN_1(String vC_VRNT_OPTN_1) {
		VC_VRNT_OPTN_1 = vC_VRNT_OPTN_1;
	}

	/**
	 * @return the vC_VRNT_OPTN_2
	 */
	public String getVC_VRNT_OPTN_2() {
		return VC_VRNT_OPTN_2;
	}

	/**
	 * @param vC_VRNT_OPTN_2
	 *            the vC_VRNT_OPTN_2 to set
	 */
	public void setVC_VRNT_OPTN_2(String vC_VRNT_OPTN_2) {
		VC_VRNT_OPTN_2 = vC_VRNT_OPTN_2;
	}

	/**
	 * @return the vC_VRNT_OPTN_3
	 */
	public String getVC_VRNT_OPTN_3() {
		return VC_VRNT_OPTN_3;
	}

	/**
	 * @param vC_VRNT_OPTN_3
	 *            the vC_VRNT_OPTN_3 to set
	 */
	public void setVC_VRNT_OPTN_3(String vC_VRNT_OPTN_3) {
		VC_VRNT_OPTN_3 = vC_VRNT_OPTN_3;
	}

	/**
	 * @return the vC_VRNT_OPTN_4
	 */
	public String getVC_VRNT_OPTN_4() {
		return VC_VRNT_OPTN_4;
	}

	/**
	 * @param vC_VRNT_OPTN_4
	 *            the vC_VRNT_OPTN_4 to set
	 */
	public void setVC_VRNT_OPTN_4(String vC_VRNT_OPTN_4) {
		VC_VRNT_OPTN_4 = vC_VRNT_OPTN_4;
	}

	/**
	 * @return the vC_VRNT_OPTN_5
	 */
	public String getVC_VRNT_OPTN_5() {
		return VC_VRNT_OPTN_5;
	}

	/**
	 * @param vC_VRNT_OPTN_5
	 *            the vC_VRNT_OPTN_5 to set
	 */
	public void setVC_VRNT_OPTN_5(String vC_VRNT_OPTN_5) {
		VC_VRNT_OPTN_5 = vC_VRNT_OPTN_5;
	}

	/**
	 * @return the nM_VRNT_OPTN_1
	 */
	public float getNM_VRNT_OPTN_1() {
		return NM_VRNT_OPTN_1;
	}

	/**
	 * @param nM_VRNT_OPTN_1
	 *            the nM_VRNT_OPTN_1 to set
	 */
	public void setNM_VRNT_OPTN_1(float nM_VRNT_OPTN_1) {
		NM_VRNT_OPTN_1 = nM_VRNT_OPTN_1;
	}

	/**
	 * @return the nM_VRNT_OPTN_2
	 */
	public float getNM_VRNT_OPTN_2() {
		return NM_VRNT_OPTN_2;
	}

	/**
	 * @param nM_VRNT_OPTN_2
	 *            the nM_VRNT_OPTN_2 to set
	 */
	public void setNM_VRNT_OPTN_2(float nM_VRNT_OPTN_2) {
		NM_VRNT_OPTN_2 = nM_VRNT_OPTN_2;
	}

	/**
	 * @return the nM_VRNT_OPTN_3
	 */
	public float getNM_VRNT_OPTN_3() {
		return NM_VRNT_OPTN_3;
	}

	/**
	 * @param nM_VRNT_OPTN_3
	 *            the nM_VRNT_OPTN_3 to set
	 */
	public void setNM_VRNT_OPTN_3(float nM_VRNT_OPTN_3) {
		NM_VRNT_OPTN_3 = nM_VRNT_OPTN_3;
	}

	/**
	 * @return the nM_VRNT_OPTN_4
	 */
	public float getNM_VRNT_OPTN_4() {
		return NM_VRNT_OPTN_4;
	}

	/**
	 * @param nM_VRNT_OPTN_4
	 *            the nM_VRNT_OPTN_4 to set
	 */
	public void setNM_VRNT_OPTN_4(float nM_VRNT_OPTN_4) {
		NM_VRNT_OPTN_4 = nM_VRNT_OPTN_4;
	}

	/**
	 * @return the nM_VRNT_OPTN_5
	 */
	public float getNM_VRNT_OPTN_5() {
		return NM_VRNT_OPTN_5;
	}

	/**
	 * @param nM_VRNT_OPTN_5
	 *            the nM_VRNT_OPTN_5 to set
	 */
	public void setNM_VRNT_OPTN_5(float nM_VRNT_OPTN_5) {
		NM_VRNT_OPTN_5 = nM_VRNT_OPTN_5;
	}

	/**
	 * @return the vC_VRNT_OPTN_1_UNT
	 */
	public String getVC_VRNT_OPTN_1_UNT() {
		return VC_VRNT_OPTN_1_UNT;
	}

	/**
	 * @param vC_VRNT_OPTN_1_UNT
	 *            the vC_VRNT_OPTN_1_UNT to set
	 */
	public void setVC_VRNT_OPTN_1_UNT(String vC_VRNT_OPTN_1_UNT) {
		VC_VRNT_OPTN_1_UNT = vC_VRNT_OPTN_1_UNT;
	}

	/**
	 * @return the vC_VRNT_OPTN_2_UNT
	 */
	public String getVC_VRNT_OPTN_2_UNT() {
		return VC_VRNT_OPTN_2_UNT;
	}

	/**
	 * @param vC_VRNT_OPTN_2_UNT
	 *            the vC_VRNT_OPTN_2_UNT to set
	 */
	public void setVC_VRNT_OPTN_2_UNT(String vC_VRNT_OPTN_2_UNT) {
		VC_VRNT_OPTN_2_UNT = vC_VRNT_OPTN_2_UNT;
	}

	/**
	 * @return the vC_VRNT_OPTN_3_UNT
	 */
	public String getVC_VRNT_OPTN_3_UNT() {
		return VC_VRNT_OPTN_3_UNT;
	}

	/**
	 * @param vC_VRNT_OPTN_3_UNT
	 *            the vC_VRNT_OPTN_3_UNT to set
	 */
	public void setVC_VRNT_OPTN_3_UNT(String vC_VRNT_OPTN_3_UNT) {
		VC_VRNT_OPTN_3_UNT = vC_VRNT_OPTN_3_UNT;
	}

	/**
	 * @return the vC_VRNT_OPTN_4_UNT
	 */
	public String getVC_VRNT_OPTN_4_UNT() {
		return VC_VRNT_OPTN_4_UNT;
	}

	/**
	 * @param vC_VRNT_OPTN_4_UNT
	 *            the vC_VRNT_OPTN_4_UNT to set
	 */
	public void setVC_VRNT_OPTN_4_UNT(String vC_VRNT_OPTN_4_UNT) {
		VC_VRNT_OPTN_4_UNT = vC_VRNT_OPTN_4_UNT;
	}

	/**
	 * @return the vC_VRNT_OPTN_5_UNT
	 */
	public String getVC_VRNT_OPTN_5_UNT() {
		return VC_VRNT_OPTN_5_UNT;
	}
	
	public Date getDtCrtdAt() {
		return dtCrtdAt;
	}

	public void setDtCrtdAt(Date dtCrtdAt) {
		this.dtCrtdAt = dtCrtdAt;
	}

	/**
	 * @param vC_VRNT_OPTN_5_UNT
	 *            the vC_VRNT_OPTN_5_UNT to set
	 */
	public void setVC_VRNT_OPTN_5_UNT(String vC_VRNT_OPTN_5_UNT) {
		VC_VRNT_OPTN_5_UNT = vC_VRNT_OPTN_5_UNT;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	



}
