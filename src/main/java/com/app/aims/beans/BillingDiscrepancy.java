package com.app.aims.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "billingdiscrepancy", schema = "aims")
public class BillingDiscrepancy implements Serializable {

	public BillingDiscrepancy() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4423840919843137706L;

	// BillingDiscrepancy table properties Starts

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_id_seq")
	@SequenceGenerator(name = "entity_id_seq", sequenceName = "global_id_sequence", allocationSize = 1)
	@Column(name = "discrepancy_id", unique = true, updatable = false, nullable = false)
	private Integer discrepancyId;

	@Column(name = "version")
	private Integer version;

	@Column(name = "billingversionid")
	private Integer billingVersionId;

	@Column(name = "filename")
	private String fileName;

	@Column(name = "brm")
	private String brm;

	@Column(name = "dm")
	private String dm;

	@Column(name = "location")
	private String location;

	@Column(name = "currency")
	private String currency;

	@Column(name = "projectno")
	private String projectNo;

	@Column(name = "projectname")
	private String projectName;

	@Column(name = "employeeid")
	private String employeeId;

	@Column(name = "officeid")
	private String officeId;

	@Column(name = "employeename")
	private String employeeName;

	@Column(name = "ratewithouttax")
	private String rateWithoutTax;

	@Column(name = "accruedhours")
	private String accruedHours;

	@Column(name = "clarityhours")
	private String clarityHours;

	@Column(name = "difference")
	private String difference;

	@Column(name = "currentinvoicehours")
	private String currentInvoiceHours;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "cleanupcomments")
	private String cleanupComments;

	public Integer getDiscrepancyId() {
		return discrepancyId;
	}

	public void setDiscrepancyId(Integer discrepancyId) {
		this.discrepancyId = discrepancyId;
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

	// BillingDiscrepancy table properties Ends

}
