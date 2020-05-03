package com.app.aims.beans;

import java.io.Serializable;

public class BARatioBean extends AbstractReportBean implements Serializable {

	
	private String baCountTot;
	private String baCountPerc;
	private String baCountTotOn;
	private String baCountTotOff;
	private String baCountPerOn;
	private String baCountPerOff;
	public String getBaCountTot() {
		return baCountTot;
	}
	public void setBaCountTot(String baCountTot) {
		this.baCountTot = baCountTot;
	}
	public String getBaCountPerc() {
		return baCountPerc;
	}
	public void setBaCountPerc(String baCountPerc) {
		this.baCountPerc = baCountPerc;
	}
	public String getBaCountTotOn() {
		return baCountTotOn;
	}
	public void setBaCountTotOn(String baCountTotOn) {
		this.baCountTotOn = baCountTotOn;
	}
	public String getBaCountTotOff() {
		return baCountTotOff;
	}
	public void setBaCountTotOff(String baCountTotOff) {
		this.baCountTotOff = baCountTotOff;
	}
	public String getBaCountPerOn() {
		return baCountPerOn;
	}
	public void setBaCountPerOn(String baCountPerOn) {
		this.baCountPerOn = baCountPerOn;
	}
	public String getBaCountPerOff() {
		return baCountPerOff;
	}
	public void setBaCountPerOff(String baCountPerOff) {
		this.baCountPerOff = baCountPerOff;
	}
	
	
	
	

}
