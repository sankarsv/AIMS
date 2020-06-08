package com.app.aims.vo;

public class BillingDetailsReq {

	private BillingDetailsResp billingDetailsFilter;
	private String month;
	private String year;
	private String brmName;
	private Integer brmId;
	private String filterBy;
	private String freezeInd;
	private String version;

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
	
	public String getBrmName() {
		return brmName;
	}
	public void setBrmName(String brmName) {
		this.brmName = brmName;
	}
	public String getFreezeInd() {
		return freezeInd;
	}
	public void setFreezeInd(String freezeInd) {
		this.freezeInd = freezeInd;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public BillingDetailsResp getBillingDetailsFilter() {
		return billingDetailsFilter;
	}
	public void setBillingDetailsFilter(BillingDetailsResp billingDetailsFilter) {
		this.billingDetailsFilter = billingDetailsFilter;
	}
	public Integer getBrmId() {
		return brmId;
	}
	public void setBrmId(Integer brmId) {
		this.brmId = brmId;
	}
	public String getFilterBy() {
		return filterBy;
	}
	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}
	
}
