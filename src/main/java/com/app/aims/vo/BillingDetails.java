package com.app.aims.vo;

public class BillingDetails {

	private String empId;
	private int billableHrs;
	private double billableDays;
	private double effortHrs;
	
	private double extraHrs;
	
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
