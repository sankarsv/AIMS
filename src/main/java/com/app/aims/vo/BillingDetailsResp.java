package com.app.aims.vo;

public class BillingDetailsResp extends BaseResponse{
	
	private String projectId;
	
	private String empName;
	
	private String dmName;

	private String brmName;
	
	private String wonNumber;
	
	private String locationId;
	
	private String stoName;
	
	private String officeId;
	
	private Integer billableHrs;
	
	private Double billableDays;	
	
	private Double effortHrs;
	
	private Double extraHrs;
	
	private Double extraBilling;
	
	private Double billingAmount;
	
	private String remarks1;
	
	private String remarks2;
	
	private String freezeInd;
	
	private String version;
	
	private String billRate;
	

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
	
	public String getFreezeInd() {
		return freezeInd;
	}
	public void setFreezeInd(String freezeInd) {
		this.freezeInd = freezeInd;
	}
	public String getBrmName() {
		return brmName;
	}
	public void setBrmName(String brmName) {
		this.brmName = brmName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public String getRemarks2() {
		return remarks2;
	}
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	
	public String getBillRate() {
		return billRate;
	}
	public void setBillRate(String billRate) {
		this.billRate = billRate;
	}
}
