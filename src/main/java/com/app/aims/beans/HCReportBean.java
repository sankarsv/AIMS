package com.app.aims.beans;

import java.io.Serializable;

public class HCReportBean extends AbstractReportBean implements Serializable {

	private String offTot;
	private String onsiteTot;
	private String offPerc;
	private String onsitePerc;
	public String getOffTot() {
		return offTot;
	}
	public void setOffTot(String offTot) {
		this.offTot = offTot;
	}
	public String getOnsiteTot() {
		return onsiteTot;
	}
	public void setOnsiteTot(String onsiteTot) {
		this.onsiteTot = onsiteTot;
	}
	public String getOffPerc() {
		return offPerc;
	}
	public void setOffPerc(String offPerc) {
		this.offPerc = offPerc;
	}
	public String getOnsitePerc() {
		return onsitePerc;
	}
	public void setOnsitePerc(String onsitePerc) {
		this.onsitePerc = onsitePerc;
	}
	
	
}
