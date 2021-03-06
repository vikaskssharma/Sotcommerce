/**
 * 
 */
package com.sot.ecommerce.web.vo;

/**
 * @author simanchal.patra
 * 
 */
public class ProductImagableVariantData {
	
	private String id;

	private Long nmVrntId;

	private Object title;

	private String image1;

	private String image2;

	private String image3;

	private String imagableAttributeMapping;

	public ProductImagableVariantData(String id, Long nmVrntId, Object title,
			String image1, String image2, String image3,
			String imagableAttributeMapping) {
		this.id = id;
		this.nmVrntId = nmVrntId;
		this.title = title;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.imagableAttributeMapping = imagableAttributeMapping;
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
	 * @return the id
	 */
	public Long getNmVrntId() {
		return nmVrntId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setNmVrntId(Long nmVrntId) {
		this.nmVrntId = nmVrntId;
	}

	/**
	 * @return the title
	 */
	public Object getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(Object title) {
		this.title = title;
	}

	/**
	 * @return the image1
	 */
	public String getImage1() {
		return image1;
	}

	/**
	 * @param image1
	 *            the image1 to set
	 */
	public void setImage1(String image1) {
		this.image1 = image1;
	}

	/**
	 * @return the image2
	 */
	public String getImage2() {
		return image2;
	}

	/**
	 * @param image2
	 *            the image2 to set
	 */
	public void setImage2(String image2) {
		this.image2 = image2;
	}

	/**
	 * @return the image3
	 */
	public String getImage3() {
		return image3;
	}

	/**
	 * @param image3
	 *            the image3 to set
	 */
	public void setImage3(String image3) {
		this.image3 = image3;
	}

	/**
	 * @return the imagableAttributeMapping
	 */
	public String getImagableAttributeMapping() {
		return imagableAttributeMapping;
	}

	/**
	 * @param imagableAttributeMapping
	 *            the imagableAttributeMapping to set
	 */
	public void setImagableAttributeMapping(String imagableAttributeMapping) {
		this.imagableAttributeMapping = imagableAttributeMapping;
	}

}
