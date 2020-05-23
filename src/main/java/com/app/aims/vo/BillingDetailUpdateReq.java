package com.app.aims.vo;

import java.util.List;

public class BillingDetailUpdateReq {

	private String version;
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
}
