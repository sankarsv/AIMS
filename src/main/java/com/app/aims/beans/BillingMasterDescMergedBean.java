package com.app.aims.beans;

import java.io.Serializable;

public class BillingMasterDescMergedBean implements Serializable {

	public BillingMasterDescMergedBean() {
		super();
	}

	private static final long serialVersionUID = -4423840919843137706L;

	//Taken from Descrepancy Entity
	private String brm;
	private String dm;
	private String location;
	private String projectNo;
	private String projectName;
	private String employeeId;
	private String officeId;
	private String employeeName;
	private String difference;
	//private String remarks;
	
	//Taken from Billing Entity
	private String stoName;
	private String remarks1;
	private String remarks2;
	private Integer billableHrs;
	private Double billableDays;
	private Double effortHrs;
	private Double extraHrs;
	private Double extraBilling;
	private Double billingAmount;
	
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

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getStoName() {
		return stoName;
	}

	public void setStoName(String stoName) {
		this.stoName = stoName;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}

	public Integer getBillableHrs() {
		return billableHrs;
	}

	public void setBillableHrs(Integer billableHrs) {
		this.billableHrs = billableHrs;
	}

	public Double getBillableDays() {
		return billableDays;
	}

	public void setBillableDays(Double billableDays) {
		this.billableDays = billableDays;
	}

	public Double getEffortHrs() {
		return effortHrs;
	}

	public void setEffortHrs(Double effortHrs) {
		this.effortHrs = effortHrs;
	}

	public Double getExtraHrs() {
		return extraHrs;
	}

	public void setExtraHrs(Double extraHrs) {
		this.extraHrs = extraHrs;
	}

	public Double getExtraBilling() {
		return extraBilling;
	}

	public void setExtraBilling(Double extraBilling) {
		this.extraBilling = extraBilling;
	}

	public Double getBillingAmount() {
		return billingAmount;
	}

	public void setBillingAmount(Double billingAmount) {
		this.billingAmount = billingAmount;
	}

	@Override
	public String toString() {
		return "BillingMasterDescMergedBean [brm=" + brm + ", dm=" + dm + ", location=" + location + ", projectNo="
				+ projectNo + ", projectName=" + projectName + ", employeeId=" + employeeId + ", officeId=" + officeId
				+ ", employeeName=" + employeeName + ", difference=" + difference + ", stoName=" + stoName
				+ ", remarks1=" + remarks1 + ", remarks2=" + remarks2 + ", billableHrs=" + billableHrs
				+ ", billableDays=" + billableDays + ", effortHrs=" + effortHrs + ", extraHrs=" + extraHrs
				+ ", extraBilling=" + extraBilling + ", billingAmount=" + billingAmount + "]";
	}

	

	// BillingDiscrepancy table properties Ends

}
