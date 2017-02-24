/**
 * 
 */
package com.sot.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vaibhav.jain
 *
 */
@Entity
@Table(name="ORDER_STATUS_TB_MASTER")
public class OrderStatusTbMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="NM_STTS_CD")
	private long nmsttsCd;
	
	@Column(name="VC_STTS_DESC")
	private String vcSttsDesc;

	/**
	 * @return the nmsttsCd
	 */
	public long getNmsttsCd() {
		return nmsttsCd;
	}

	/**
	 * @return the vcSttsDesc
	 */
	public String getVcSttsDesc() {
		return vcSttsDesc;
	}

}
