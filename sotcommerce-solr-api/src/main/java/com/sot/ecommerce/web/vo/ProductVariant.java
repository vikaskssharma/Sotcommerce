package com.sot.ecommerce.web.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 
 * 
 * @author simanchal.patra
 */
@Entity
@Table(name = "ProductVariant")
public class ProductVariant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Field
	private String id;

	@Field
	@Column(name = "nmVrntId")
	private long nmVrntId;

	@Field
	@Column(name = "product_Id")
	private long product_Id;

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
	@Column(name = "vrntNmDlPrc")
	private float vrntNmDlPrc;

	@Field
	@Column(name = "vrntNmSp")
	private float vrntNmSp;

	@Field
	@Column(name = "vrntNmQntty")
	private int vrntNmQntty;

	public static class Builder {

		private ProductVariant build;

		public Builder(String id, long nmVrntId, long product_Id,
				String VC_VRNT_IMG_1, String VC_VRNT_IMG_2,
				String VC_VRNT_IMG_3, String VC_VRNT_OPTN_1,
				String VC_VRNT_OPTN_2, String VC_VRNT_OPTN_3,
				String VC_VRNT_OPTN_4, String VC_VRNT_OPTN_5, String vcVrntNm,
				float NM_VRNT_OPTN_1, float NM_VRNT_OPTN_2,
				float NM_VRNT_OPTN_3, float NM_VRNT_OPTN_4,
				float NM_VRNT_OPTN_5, String VC_VRNT_OPTN_1_UNT,
				String VC_VRNT_OPTN_2_UNT, String VC_VRNT_OPTN_3_UNT,
				String VC_VRNT_OPTN_4_UNT, String VC_VRNT_OPTN_5_UNT,
				float vrntNmDlPrc, float vrntNmSp, int vrntNmQntty) {

			build = new ProductVariant();
			build.id = id;
			build.nmVrntId = nmVrntId;
			build.product_Id = product_Id;
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
			build.vrntNmDlPrc = vrntNmDlPrc;
			build.vrntNmSp = vrntNmSp;
			build.vrntNmQntty = vrntNmQntty;
		}

		public ProductVariant build() {
			return build;
		}
	}

	public static Builder getBuilder(String id, long nmVrntId, long product_Id,
			String VC_VRNT_IMG_1, String VC_VRNT_IMG_2, String VC_VRNT_IMG_3,
			String VC_VRNT_OPTN_1, String VC_VRNT_OPTN_2,
			String VC_VRNT_OPTN_3, String VC_VRNT_OPTN_4,
			String VC_VRNT_OPTN_5, String vcVrntNm, float NM_VRNT_OPTN_1,
			float NM_VRNT_OPTN_2, float NM_VRNT_OPTN_3, float NM_VRNT_OPTN_4,
			float NM_VRNT_OPTN_5, String VC_VRNT_OPTN_1_UNT,
			String VC_VRNT_OPTN_2_UNT, String VC_VRNT_OPTN_3_UNT,
			String VC_VRNT_OPTN_4_UNT, String VC_VRNT_OPTN_5_UNT,
			float vrntNmDlPrc, float vrntNmSp, int vrntNmQntty) {

		return new Builder(id, nmVrntId, product_Id, VC_VRNT_IMG_1,
				VC_VRNT_IMG_2, VC_VRNT_IMG_3, VC_VRNT_OPTN_1, VC_VRNT_OPTN_2,
				VC_VRNT_OPTN_3, VC_VRNT_OPTN_4, VC_VRNT_OPTN_5, vcVrntNm,
				NM_VRNT_OPTN_1, NM_VRNT_OPTN_2, NM_VRNT_OPTN_3, NM_VRNT_OPTN_4,
				NM_VRNT_OPTN_5, VC_VRNT_OPTN_1_UNT, VC_VRNT_OPTN_2_UNT,
				VC_VRNT_OPTN_3_UNT, VC_VRNT_OPTN_4_UNT, VC_VRNT_OPTN_5_UNT,
				vrntNmDlPrc, vrntNmSp, vrntNmQntty);
	}

	public long getNmVrntId() {
		return this.nmVrntId;
	}

	public void setNmVrntId(long nmVrntId) {
		this.nmVrntId = nmVrntId;
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
	 * @return the product_Id
	 */
	public long getProduct_Id() {
		return product_Id;
	}

	/**
	 * @param product_Id
	 *            the product_Id to set
	 */
	public void setProduct_Id(long product_Id) {
		this.product_Id = product_Id;
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

	/**
	 * @param vC_VRNT_OPTN_5_UNT
	 *            the vC_VRNT_OPTN_5_UNT to set
	 */
	public void setVC_VRNT_OPTN_5_UNT(String vC_VRNT_OPTN_5_UNT) {
		VC_VRNT_OPTN_5_UNT = vC_VRNT_OPTN_5_UNT;
	}

	/**
	 * @return the vrntNmDlPrc
	 */
	public float getVrntNmDlPrc() {
		return vrntNmDlPrc;
	}

	/**
	 * @param vrntNmDlPrc
	 *            the vrntNmDlPrc to set
	 */
	public void setVrntNmDlPrc(float vrntNmDlPrc) {
		this.vrntNmDlPrc = vrntNmDlPrc;
	}

	/**
	 * @return the vrntNmSp
	 */
	public float getVrntNmSp() {
		return vrntNmSp;
	}

	/**
	 * @param vrntNmSp
	 *            the vrntNmSp to set
	 */
	public void setVrntNmSp(float vrntNmSp) {
		this.vrntNmSp = vrntNmSp;
	}

	/**
	 * @return the vrntNmQntty
	 */
	public int getVrntNmQntty() {
		return vrntNmQntty;
	}

	/**
	 * @param vrntNmQntty
	 *            the vrntNmQntty to set
	 */
	public void setVrntNmQntty(int vrntNmQntty) {
		this.vrntNmQntty = vrntNmQntty;
	}

}