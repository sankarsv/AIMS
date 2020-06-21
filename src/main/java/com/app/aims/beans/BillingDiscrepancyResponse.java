package com.app.aims.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

public class BillingDiscrepancyResponse {

	public BillingDiscrepancyResponse() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4423840919843137706L;


	private Integer discrepancyId;

	private String fileName;

	private String brm;

	private String dm;

	private String location;

	private String currency;

	private String projectNo;

	private String projectName;

	private String employeeId;

	private String officeId;

	private String employeeName;

	private String rateWithoutTax;

	private String accruedHours;

	private String clarityHours;

	private String difference;

	private String currentInvoiceHours;

	private String remarks;

	private String cleanupComments;

	public Integer getDiscrepancyId() {
		return discrepancyId;
	}

	public void setDiscrepancyId(Integer discrepancyId) {
		this.discrepancyId = discrepancyId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBrm() {
		return brm;
	}

	public void setBrm(String brm) {
		this.brm = brm;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRateWithoutTax() {
		return rateWithoutTax;
	}

	public void setRateWithoutTax(String rateWithoutTax) {
		this.rateWithoutTax = rateWithoutTax;
	}

	public String getAccruedHours() {
		return accruedHours;
	}

	public void setAccruedHours(String accruedHours) {
		this.accruedHours = accruedHours;
	}

	public String getClarityHours() {
		return clarityHours;
	}

	public void setClarityHours(String clarityHours) {
		this.clarityHours = clarityHours;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	public String getCurrentInvoiceHours() {
		return currentInvoiceHours;
	}

	public void setCurrentInvoiceHours(String currentInvoiceHours) {
		this.currentInvoiceHours = currentInvoiceHours;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCleanupComments() {
		return cleanupComments;
	}

	public void setCleanupComments(String cleanupComments) {
		this.cleanupComments = cleanupComments;
	}

}
