package com.app.aims.vo;

public class BillingDetailsReq {

	private String month;
	private String year;
	private String brmName;
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
	
}
