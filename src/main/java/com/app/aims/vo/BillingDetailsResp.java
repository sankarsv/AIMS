package com.app.aims.vo;

public class BillingDetailsResp extends BaseResponse{
	
	private String projectId;
	
	private String empName;
	
	private String dmName;

	private String brnname;
	
	private String wonNumber;
	
	private String locationId;
	
	private String stoName;
	
	private String officeId;
	
	private int billableHrs;
	
	private int billableDays;	
	
	private int effortHrs;
	
	private int extraHrs;
	
	private double extraBilling;
	
	private double billingAmount;
	
	private String remarks;
	
	private String freezeInd;

	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public String getDmName() {
		return dmName;
	}
	public void setDmName(String dmName) {
		this.dmName = dmName;
	}
	public String getWonNumber() {
		return wonNumber;
	}
	public void setWonNumber(String wonNumber) {
		this.wonNumber = wonNumber;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getStoName() {
		return stoName;
	}
	public void setStoName(String stoName) {
		this.stoName = stoName;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public int getBillableHrs() {
		return billableHrs;
	}
	public void setBillableHrs(int billableHrs) {
		this.billableHrs = billableHrs;
	}
	public int getBillableDays() {
		return billableDays;
	}
	public void setBillableDays(int billableDays) {
		this.billableDays = billableDays;
	}
	public int getEffortHrs() {
		return effortHrs;
	}
	public void setEffortHrs(int effortHrs) {
		this.effortHrs = effortHrs;
	}
	public int getExtraHrs() {
		return extraHrs;
	}
	public void setExtraHrs(int extraHrs) {
		this.extraHrs = extraHrs;
	}
	public double getExtraBilling() {
		return extraBilling;
	}
	public void setExtraBilling(double extraBilling) {
		this.extraBilling = extraBilling;
	}
	public double getBillingAmount() {
		return billingAmount;
	}
	public void setBillingAmount(double billingAmount) {
		this.billingAmount = billingAmount;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFreezeInd() {
		return freezeInd;
	}
	public void setFreezeInd(String freezeInd) {
		this.freezeInd = freezeInd;
	}
	public String getBrnname() {
		return brnname;
	}
	public void setBrnname(String brnname) {
		this.brnname = brnname;
	}
}
