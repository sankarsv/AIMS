package com.app.aims.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="billingversion",schema="aims", uniqueConstraints=@UniqueConstraint(columnNames= {"brm_empno", "periodmonth", "year"}))
public class BillingVersion  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7407183647631676571L;


	@Id
	@Column(name="version")	
	private Integer version;
	
	@Column(name="brm_empno")	
	private String brm_EmpNo;
	
	@Column(name="periodmonth")	
	private String periodMonth;
	
	@Column(name="year")	
	private Integer year;
	
	@Column(name="location")	
	private String location;
	
	@Column(name="freezeind")	
	private String freezeInd;
	
	@Column(name="draftindicator")	
	private String draftIndicator;
	
	@Column(name="billingcomments")	
	private String billingComments;
	
	@Column(name="clarityversion")	
	private String clarityVersion;
	
	@Column(name="discrerpancyversion")	
	private String discrerpancyVersion;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getBrm_EmpNo() {
		return brm_EmpNo;
	}

	public void setBrm_EmpNo(String brm_EmpNo) {
		this.brm_EmpNo = brm_EmpNo;
	}

	public String getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(String periodMonth) {
		this.periodMonth = periodMonth;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFreezeInd() {
		return freezeInd;
	}

	public void setFreezeInd(String freezeInd) {
		this.freezeInd = freezeInd;
	}

	public String getDraftIndicator() {
		return draftIndicator;
	}

	public void setDraftIndicator(String draftIndicator) {
		this.draftIndicator = draftIndicator;
	}

	public String getBillingComments() {
		return billingComments;
	}

	public void setBillingComments(String billingComments) {
		this.billingComments = billingComments;
	}

	public String getClarityVersion() {
		return clarityVersion;
	}

	public void setClarityVersion(String clarityVersion) {
		this.clarityVersion = clarityVersion;
	}

	public String getDiscrerpancyVersion() {
		return discrerpancyVersion;
	}

	public void setDiscrerpancyVersion(String discrerpancyVersion) {
		this.discrerpancyVersion = discrerpancyVersion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	


}
