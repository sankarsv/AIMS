package com.app.aims.vo;

public class BillingDetails {

	private String empId;
	private int billableHrs;
	private int billableDays;
	private int effortHrs;
	
	private int extraHrs;
	
	private double extraBilling;
	private double billingAmount;
	private String remarks;
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
}
