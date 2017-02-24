package com.sot.ecommerce.entities;



import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the ISSUE_REASON_TB_MASTER database table.
 * 
 */
@Entity
@Table(name="ISSUE_REASON_TB_MASTER")
@NamedQuery(name="IssueReasonTbMaster.findAll", query="SELECT i FROM IssueReasonTbMaster i")
public class IssueReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ISSUE_REASON_TB_MASTER_NMISSRSNID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ISSUE_REASON_TB_MASTER_NMISSRSNID_GENERATOR")
	@Column(name="NM_ISS_RSN_ID")
	private long nmIssRsnId;

	@Column(name="VC_ISS_RSN_NM")
	private String vcIssRsnNm;
    public IssueReason() {
	}

	public long getNmIssRsnId() {
		return this.nmIssRsnId;
	}

	public void setNmIssRsnId(long nmIssRsnId) {
		this.nmIssRsnId = nmIssRsnId;
	}

	public String getVcIssRsnNm() {
		return this.vcIssRsnNm;
	}

	public void setVcIssRsnNm(String vcIssRsnNm) {
		this.vcIssRsnNm = vcIssRsnNm;
	}

	
	

}