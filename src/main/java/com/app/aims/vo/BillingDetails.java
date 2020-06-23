package com.app.aims.vo;

public class BillingDetails {

	private String empId;
	private int brm;
	private int billableHrs;
	private double billableDays;
	private double effortHrs;
	
	private double extraHrs;
	private String billRate;
	private double extraBilling;
	private double billingAmount;
	private String remarks1;
	private String remarks2;
	private String action;
	private String stoName;
	private String locationId;
	private String wonNumber;
	private String officeId;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public int getBillableHrs() {
		return billableHrs;
	}
	public void setBillableHrs(int billableHrs) {
		this.billableHrs = billableHrs;
	}
	public double getBillableDays() {
		return billableDays;
	}
	public void setBillableDays(double billableDays) {
		this.billableDays = billableDays;
	}
	public double getEffortHrs() {
		return effortHrs;
	}
	public void setEffortHrs(double effortHrs) {
		this.effortHrs = effortHrs;
	}
	public double getExtraHrs() {
		return extraHrs;
	}
	public void setExtraHrs(double extraHrs) {
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getStoName() {
		return stoName;
	}
	public void setStoName(String stoName) {
		this.stoName = stoName;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getWonNumber() {
		return wonNumber;
	}
	public void setWonNumber(String wonNumber) {
		this.wonNumber = wonNumber;
	}
	public String getBillRate() {
		return billRate;
	}
	public void setBillRate(String billRate) {
		this.billRate = billRate;
	}
	public int getBrm() {
		return brm;
	}
	public void setBrm(int brm) {
		this.brm = brm;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	
}
