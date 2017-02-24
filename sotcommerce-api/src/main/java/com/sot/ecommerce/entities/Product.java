/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package com.sot.ecommerce.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="PRODUCT_TB_MASTER")
public class Product implements Serializable {
	public Product() {
	}
	
	@Column(name="NM_PRD_ID", nullable=false)	
	@Id	
	@GeneratedValue(generator="COM_SOT_ECOMMERCE_PERSISTANCE_PRODUCT_PRODUCTID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="COM_SOT_ECOMMERCE_PERSISTANCE_PRODUCT_PRODUCTID_GENERATOR", strategy="native")	
	private java.math.BigDecimal productID;
	
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_CTGRY_ID", referencedColumnName="NM_CTGRY_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private Category NM_CTGRY;
	
	@Column(name="VC_PRD_NM", nullable=false, length=100)	
	private String name;
	
	@Column(name="VC_PRD_DSC", nullable=true, length=300)	
	private String desc;
	
	@Column(name="VC_STS", nullable=true, length=20)	
	private String status;
	
	@Column(name="NM_MRP", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal mrp;
	
	@Column(name="NM_SP", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal sellingPrice;
	
	@Column(name="NM_QNTY", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal quantity;
	
	@Column(name="NM_EXPTD_DLVRY_DYS", nullable=true, precision=0, scale=-127)	
	private java.math.BigDecimal expectedDelvDate;
	
	@Column(name="IS_HTDL", nullable=false, length=1)	
	private int isHotDeal;
	
	@Column(name="IS_FTRD", nullable=false, length=1)	
	private int isFeatured;
	
	@Column(name="IS_COD", nullable=false, length=1)	
	private int isCOD;
	
	@Column(name="VC_PRD_IMG1_PTH", nullable=true, length=256)	
	private String productImagePath1;
	
	@Column(name="VC_PRD_IMG2_PTH", nullable=true, length=256)	
	private String productImagePath2;
	
	@Column(name="VC_PRD_IMG3_PTH", nullable=true, length=256)	
	private String productImagePath3;
	
	@Column(name="VC_PRD_IMG4_PTH", nullable=true, length=256)	
	private String productImagePath4;
	
	@Column(name="VC_ATTR1_VALUE", nullable=true, length=128)	
	private String attr1Value;
	
	@Column(name="VC_ATTR2_VALUE", nullable=true, length=128)	
	private String attr2Value;
	
	@Column(name="VC_ATTR3_VALUE", nullable=true, length=128)	
	private String attr3Value;
	
	@Column(name="VC_ATTR4_VALUE", nullable=true, length=128)	
	private String attr4Value;
	
	@Column(name="VC_ATTR5_VALUE", nullable=true, length=128)	
	private String attr5Value;
	
	@Column(name="VC_ATTR6_VALUE", nullable=true, length=128)	
	private String attr6Value;
	
	@Column(name="VC_ATTR7_VALUE", nullable=true, length=128)	
	private String attr7Value;
	
	@Column(name="VC_ATTR8_VALUE", nullable=true, length=128)	
	private String attr8Value;
	
	@Column(name="VC_ATTR9_VALUE", nullable=true, length=128)	
	private String attr9Value;
	
	@Column(name="VC_ATTR10_VALUE", nullable=true, length=128)	
	private String attr10Value;
	
	@Column(name="VC_ATTR11_VALUE", nullable=true, length=128)	
	private String attr11Value;
	
	@Column(name="VC_ATTR12_VALUE", nullable=true, length=128)	
	private String attr12Value;
	
	@Column(name="VC_ATTR13_VALUE", nullable=true, length=128)	
	private String attr13Value;
	
	@Column(name="VC_ATTR14_VALUE", nullable=true, length=128)	
	private String attr14Value;
	
	@Column(name="VC_ATTR15_VALUE", nullable=true, length=128)	
	private String attr15Value;
	
	@Column(name="VC_ATTR16_VALUE", nullable=true, length=128)	
	private String attr16Value;
	
	@Column(name="VC_ATTR17_VALUE", nullable=true, length=128)	
	private String attr17Value;
	
	@Column(name="VC_ATTR18_VALUE", nullable=true, length=128)	
	private String attr18Value;
	
	@Column(name="VC_ATTR19_VALUE", nullable=true, length=128)	
	private String attr19Value;
	
	@Column(name="VC_ATTR20_VALUE", nullable=true, length=128)	
	private String attr20Value;
	
	@Column(name="VC_ATTR21_VALUE", nullable=true, length=128)	
	private String attr21Value;
	
	@Column(name="VC_ATTR22_VALUE", nullable=true, length=128)	
	private String attr22Value;
	
	@Column(name="VC_ATTR23_VALUE", nullable=true, length=128)	
	private String attr23Value;
	
	@Column(name="VC_ATTR24_VALUE", nullable=true, length=128)	
	private String attr24Value;
	
	@Column(name="VC_ATTR25_VALUE", nullable=true, length=128)	
	private String attr25Value;
	
	@Column(name="VC_IMG1_PTH", nullable=true, length=256)	
	private String attributeImgPath1;
	
	@Column(name="VC_IMG2_PTH", nullable=true, length=256)	
	private String attributeImgPath2;
	
	@Column(name="VC_IMG3_PTH", nullable=true, length=256)	
	private String attributeImgPath3;
	
	@Column(name="VC_IMG4_PTH", nullable=true, length=256)	
	private String attributeImgPath4;
	
	@Column(name="VC_IMG5_PTH", nullable=true, length=256)	
	private String attributeImgPath5;
	
	@Column(name="VC_IMG6_PTH", nullable=true, length=256)	
	private String attributeImgPath6;
	
	@Column(name="VC_IMG7_PTH", nullable=true, length=256)	
	private String attributeImgPath7;
	
	@Column(name="VC_IMG8_PTH", nullable=true, length=256)	
	private String attributeImgPath8;
	
	@Column(name="VC_IMG9_PTH", nullable=true, length=256)	
	private String attributeImgPath9;
	
	@Column(name="VC_IMG10_PTH", nullable=true, length=256)	
	private String attributeImgPath10;
	
	@Column(name="DT_CRTD_AT", nullable=false)	
	private java.sql.Timestamp createdDate;
	
	@Column(name="DT_UPDTD_AT", nullable=false)	
	private java.sql.Timestamp updatedDate;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_CRTD_BY", referencedColumnName="NM_USR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private User NM_CRTD_BY;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="NM_UPDTD_BY", referencedColumnName="NM_USR_ID", nullable=false) })	
	@org.hibernate.annotations.LazyToOne(value=org.hibernate.annotations.LazyToOneOption.NO_PROXY)	
	private User NM_UPDTD_BY;
	
	@Column(name="IS_DLTD", nullable=false, length=1)	
	private int isDeleted;
	
	@Column(name="NM_STR_ID", nullable=false, precision=0, scale=-127)	
	private java.math.BigDecimal storeId;
	
	@Column(name="IS_FR_SHPNG", nullable=false, length=1)	
	private int isFreeShipping;
	
	@Column(name="IS_OT_OF_STCK", nullable=false, length=1)	
	private int isOutOfStock;
	
	@OneToMany(mappedBy="NM_PRD", targetEntity=OrderProductDetail.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORDER_PRODUCT_TB_DETAIL = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_PRD", targetEntity=Promotion.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set PROMOTION_TB_MASTER = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_PRD", targetEntity=RelatedProduct.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set RELATED_PRODUCT_TB_DTL = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_RLTD_PRD", targetEntity=RelatedProduct.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set RELATED_PRODUCT_TB_DTL1 = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_PRD", targetEntity=SimilarProduct.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set SIMILAR_PRODUCT_TB_DTL = new java.util.HashSet();
	
	@OneToMany(mappedBy="NM_SMLR_PRD", targetEntity=SimilarProduct.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set SIMILAR_PRODUCT_TB_DTL1 = new java.util.HashSet();
	
	public void setProductID(java.math.BigDecimal value) {
		this.productID = value;
	}
	
	public java.math.BigDecimal getProductID() {
		return productID;
	}
	
	public java.math.BigDecimal getORMID() {
		return getProductID();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDesc(String value) {
		this.desc = value;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setMrp(java.math.BigDecimal value) {
		this.mrp = value;
	}
	
	public java.math.BigDecimal getMrp() {
		return mrp;
	}
	
	public void setSellingPrice(java.math.BigDecimal value) {
		this.sellingPrice = value;
	}
	
	public java.math.BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	
	public void setQuantity(java.math.BigDecimal value) {
		this.quantity = value;
	}
	
	public java.math.BigDecimal getQuantity() {
		return quantity;
	}
	
	public void setExpectedDelvDate(java.math.BigDecimal value) {
		this.expectedDelvDate = value;
	}
	
	public java.math.BigDecimal getExpectedDelvDate() {
		return expectedDelvDate;
	}
	
	public void setIsHotDeal(int value) {
		this.isHotDeal = value;
	}
	
	public int getIsHotDeal() {
		return isHotDeal;
	}
	
	public void setIsFeatured(int value) {
		this.isFeatured = value;
	}
	
	public int getIsFeatured() {
		return isFeatured;
	}
	
	public void setIsCOD(int value) {
		this.isCOD = value;
	}
	
	public int getIsCOD() {
		return isCOD;
	}
	
	public void setProductImagePath1(String value) {
		this.productImagePath1 = value;
	}
	
	public String getProductImagePath1() {
		return productImagePath1;
	}
	
	public void setProductImagePath2(String value) {
		this.productImagePath2 = value;
	}
	
	public String getProductImagePath2() {
		return productImagePath2;
	}
	
	public void setProductImagePath3(String value) {
		this.productImagePath3 = value;
	}
	
	public String getProductImagePath3() {
		return productImagePath3;
	}
	
	public void setProductImagePath4(String value) {
		this.productImagePath4 = value;
	}
	
	public String getProductImagePath4() {
		return productImagePath4;
	}
	
	public void setAttr1Value(String value) {
		this.attr1Value = value;
	}
	
	public String getAttr1Value() {
		return attr1Value;
	}
	
	public void setAttr2Value(String value) {
		this.attr2Value = value;
	}
	
	public String getAttr2Value() {
		return attr2Value;
	}
	
	public void setAttr3Value(String value) {
		this.attr3Value = value;
	}
	
	public String getAttr3Value() {
		return attr3Value;
	}
	
	public void setAttr4Value(String value) {
		this.attr4Value = value;
	}
	
	public String getAttr4Value() {
		return attr4Value;
	}
	
	public void setAttr5Value(String value) {
		this.attr5Value = value;
	}
	
	public String getAttr5Value() {
		return attr5Value;
	}
	
	public void setAttr6Value(String value) {
		this.attr6Value = value;
	}
	
	public String getAttr6Value() {
		return attr6Value;
	}
	
	public void setAttr7Value(String value) {
		this.attr7Value = value;
	}
	
	public String getAttr7Value() {
		return attr7Value;
	}
	
	public void setAttr8Value(String value) {
		this.attr8Value = value;
	}
	
	public String getAttr8Value() {
		return attr8Value;
	}
	
	public void setAttr9Value(String value) {
		this.attr9Value = value;
	}
	
	public String getAttr9Value() {
		return attr9Value;
	}
	
	public void setAttr10Value(String value) {
		this.attr10Value = value;
	}
	
	public String getAttr10Value() {
		return attr10Value;
	}
	
	public void setAttr11Value(String value) {
		this.attr11Value = value;
	}
	
	public String getAttr11Value() {
		return attr11Value;
	}
	
	public void setAttr12Value(String value) {
		this.attr12Value = value;
	}
	
	public String getAttr12Value() {
		return attr12Value;
	}
	
	public void setAttr13Value(String value) {
		this.attr13Value = value;
	}
	
	public String getAttr13Value() {
		return attr13Value;
	}
	
	public void setAttr14Value(String value) {
		this.attr14Value = value;
	}
	
	public String getAttr14Value() {
		return attr14Value;
	}
	
	public void setAttr15Value(String value) {
		this.attr15Value = value;
	}
	
	public String getAttr15Value() {
		return attr15Value;
	}
	
	public void setAttr16Value(String value) {
		this.attr16Value = value;
	}
	
	public String getAttr16Value() {
		return attr16Value;
	}
	
	public void setAttr17Value(String value) {
		this.attr17Value = value;
	}
	
	public String getAttr17Value() {
		return attr17Value;
	}
	
	public void setAttr18Value(String value) {
		this.attr18Value = value;
	}
	
	public String getAttr18Value() {
		return attr18Value;
	}
	
	public void setAttr19Value(String value) {
		this.attr19Value = value;
	}
	
	public String getAttr19Value() {
		return attr19Value;
	}
	
	public void setAttr20Value(String value) {
		this.attr20Value = value;
	}
	
	public String getAttr20Value() {
		return attr20Value;
	}
	
	public void setAttr21Value(String value) {
		this.attr21Value = value;
	}
	
	public String getAttr21Value() {
		return attr21Value;
	}
	
	public void setAttr22Value(String value) {
		this.attr22Value = value;
	}
	
	public String getAttr22Value() {
		return attr22Value;
	}
	
	public void setAttr23Value(String value) {
		this.attr23Value = value;
	}
	
	public String getAttr23Value() {
		return attr23Value;
	}
	
	public void setAttr24Value(String value) {
		this.attr24Value = value;
	}
	
	public String getAttr24Value() {
		return attr24Value;
	}
	
	public void setAttr25Value(String value) {
		this.attr25Value = value;
	}
	
	public String getAttr25Value() {
		return attr25Value;
	}
	
	public void setAttributeImgPath1(String value) {
		this.attributeImgPath1 = value;
	}
	
	public String getAttributeImgPath1() {
		return attributeImgPath1;
	}
	
	public void setAttributeImgPath2(String value) {
		this.attributeImgPath2 = value;
	}
	
	public String getAttributeImgPath2() {
		return attributeImgPath2;
	}
	
	public void setAttributeImgPath3(String value) {
		this.attributeImgPath3 = value;
	}
	
	public String getAttributeImgPath3() {
		return attributeImgPath3;
	}
	
	public void setAttributeImgPath4(String value) {
		this.attributeImgPath4 = value;
	}
	
	public String getAttributeImgPath4() {
		return attributeImgPath4;
	}
	
	public void setAttributeImgPath5(String value) {
		this.attributeImgPath5 = value;
	}
	
	public String getAttributeImgPath5() {
		return attributeImgPath5;
	}
	
	public void setAttributeImgPath6(String value) {
		this.attributeImgPath6 = value;
	}
	
	public String getAttributeImgPath6() {
		return attributeImgPath6;
	}
	
	public void setAttributeImgPath7(String value) {
		this.attributeImgPath7 = value;
	}
	
	public String getAttributeImgPath7() {
		return attributeImgPath7;
	}
	
	public void setAttributeImgPath8(String value) {
		this.attributeImgPath8 = value;
	}
	
	public String getAttributeImgPath8() {
		return attributeImgPath8;
	}
	
	public void setAttributeImgPath9(String value) {
		this.attributeImgPath9 = value;
	}
	
	public String getAttributeImgPath9() {
		return attributeImgPath9;
	}
	
	public void setAttributeImgPath10(String value) {
		this.attributeImgPath10 = value;
	}
	
	public String getAttributeImgPath10() {
		return attributeImgPath10;
	}
	
	public void setCreatedDate(java.sql.Timestamp value) {
		this.createdDate = value;
	}
	
	public java.sql.Timestamp getCreatedDate() {
		return createdDate;
	}
	
	public void setUpdatedDate(java.sql.Timestamp value) {
		this.updatedDate = value;
	}
	
	public java.sql.Timestamp getUpdatedDate() {
		return updatedDate;
	}
	
	public void setIsDeleted(int value) {
		this.isDeleted = value;
	}
	
	public int getIsDeleted() {
		return isDeleted;
	}
	
	public void setStoreId(java.math.BigDecimal value) {
		this.storeId = value;
	}
	
	public java.math.BigDecimal getStoreId() {
		return storeId;
	}
	
	public void setIsFreeShipping(int value) {
		this.isFreeShipping = value;
	}
	
	public int getIsFreeShipping() {
		return isFreeShipping;
	}
	
	public void setIsOutOfStock(int value) {
		this.isOutOfStock = value;
	}
	
	public int getIsOutOfStock() {
		return isOutOfStock;
	}
	
	public void setNM_CTGRY(Category value) {
		this.NM_CTGRY = value;
	}
	
	public Category getNM_CTGRY() {
		return NM_CTGRY;
	}
	
	public void setNM_CRTD_BY(User value) {
		this.NM_CRTD_BY = value;
	}
	
	public User getNM_CRTD_BY() {
		return NM_CRTD_BY;
	}
	
	public void setNM_UPDTD_BY(User value) {
		this.NM_UPDTD_BY = value;
	}
	
	public User getNM_UPDTD_BY() {
		return NM_UPDTD_BY;
	}
	
	public void setORDER_PRODUCT_TB_DETAIL(java.util.Set value) {
		this.ORDER_PRODUCT_TB_DETAIL = value;
	}
	
	public java.util.Set getORDER_PRODUCT_TB_DETAIL() {
		return ORDER_PRODUCT_TB_DETAIL;
	}
	
	
	public void setPROMOTION_TB_MASTER(java.util.Set value) {
		this.PROMOTION_TB_MASTER = value;
	}
	
	public java.util.Set getPROMOTION_TB_MASTER() {
		return PROMOTION_TB_MASTER;
	}
	
	
	public void setRELATED_PRODUCT_TB_DTL(java.util.Set value) {
		this.RELATED_PRODUCT_TB_DTL = value;
	}
	
	public java.util.Set getRELATED_PRODUCT_TB_DTL() {
		return RELATED_PRODUCT_TB_DTL;
	}
	
	
	public void setRELATED_PRODUCT_TB_DTL1(java.util.Set value) {
		this.RELATED_PRODUCT_TB_DTL1 = value;
	}
	
	public java.util.Set getRELATED_PRODUCT_TB_DTL1() {
		return RELATED_PRODUCT_TB_DTL1;
	}
	
	
	public void setSIMILAR_PRODUCT_TB_DTL(java.util.Set value) {
		this.SIMILAR_PRODUCT_TB_DTL = value;
	}
	
	public java.util.Set getSIMILAR_PRODUCT_TB_DTL() {
		return SIMILAR_PRODUCT_TB_DTL;
	}
	
	
	public void setSIMILAR_PRODUCT_TB_DTL1(java.util.Set value) {
		this.SIMILAR_PRODUCT_TB_DTL1 = value;
	}
	
	public java.util.Set getSIMILAR_PRODUCT_TB_DTL1() {
		return SIMILAR_PRODUCT_TB_DTL1;
	}
	
	
	public String toString() {
		return String.valueOf(getProductID());
	}
	
	@Transient	
	private boolean _saved = false;
	
	public void onSave() {
		_saved=true;
	}
	
	
	public void onLoad() {
		_saved=true;
	}
	
	
	public boolean isSaved() {
		return _saved;
	}
	
	
}
