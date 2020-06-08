package com.app.aims.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "clarity", schema = "aims")
public class Clarity implements Serializable {

	public Clarity() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4423840919843137706L;

	// Clarity table properties Starts
	@Id
	@Column(name = "clarity_id")
	private Integer clarityId;

	@Column(name = "version")
	private Integer version;

	@Column(name = "billingversionid")
	private Integer billingVersionId;

	@Column(name = "transactionclass")
	private String transactionClass;

	@Column(name = "cccio")
	private String cccio;

	@Column(name = "resourcemanager")
	private String resourceManager;

	@Column(name = "timesheetdepartment")
	private String timesheetDepartment;

	@Column(name = "lastnamefirstname")
	private String lastNameFirstName;

	@Column(name = "resourceid")
	private String resourceId;

	@Column(name = "officeid")
	private String officeId;

	@Column(name = "cin")
	private String cin;

	@Column(name = "sumofhours")
	private String sumOfHours;

	@Column(name = "averagerate")
	private String averageRate;

	@Column(name = "ratewithouttax")
	private String rateWithoutTax;

	public Integer getClarityId() {
		return clarityId;
	}

	public void setClarityId(Integer clarityId) {
		this.clarityId = clarityId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getBillingVersionId() {
		return billingVersionId;
	}

	public void setBillingVersionId(Integer billingVersionId) {
		this.billingVersionId = billingVersionId;
	}

	public String getTransactionClass() {
		return transactionClass;
	}

	public void setTransactionClass(String transactionClass) {
		this.transactionClass = transactionClass;
	}

	public String getCccio() {
		return cccio;
	}

	public void setCccio(String cccio) {
		this.cccio = cccio;
	}

	public String getResourceManager() {
		return resourceManager;
	}

	public void setResourceManager(String resourceManager) {
		this.resourceManager = resourceManager;
	}

	public String getTimesheetDepartment() {
		return timesheetDepartment;
	}

	public void setTimesheetDepartment(String timesheetDepartment) {
		this.timesheetDepartment = timesheetDepartment;
	}

	public String getLastNameFirstName() {
		return lastNameFirstName;
	}

	public void setLastNameFirstName(String lastNameFirstName) {
		this.lastNameFirstName = lastNameFirstName;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getSumOfHours() {
		return sumOfHours;
	}

	public void setSumOfHours(String sumOfHours) {
		this.sumOfHours = sumOfHours;
	}

	public String getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(String averageRate) {
		this.averageRate = averageRate;
	}

	public String getRateWithoutTax() {
		return rateWithoutTax;
	}

	public void setRateWithoutTax(String rateWithoutTax) {
		this.rateWithoutTax = rateWithoutTax;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// Clarity table properties Ends

}
