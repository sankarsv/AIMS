package com.app.aims.vo;

import java.util.List;

public class BillingDetailUpdateReq {

	private String version;
	private String month;
	private String year;
	private List<BillingDetails> billingDetailsList;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<BillingDetails> getBillingDetailsList() {
		return billingDetailsList;
	}
	public void setBillingDetailsList(List<BillingDetails> billingDetailsList) {
		this.billingDetailsList = billingDetailsList;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
}
