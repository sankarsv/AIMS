package com.app.aims.beans;

import java.io.Serializable;

public class BillingReportBean  extends AbstractReportBean implements Serializable {

	private String billableCountTot;
	private String nbCountTot;
	private String billableCountPerc;
	private String nbCountPerc;
	private String onbillableCount;
	private String offbillabeCount;
	private String onbillabePerc;
	private String offbillabePerc;
	
	public String getBillableCountTot() {
		return billableCountTot;
	}
	public void setBillableCountTot(String billableCountTot) {
		this.billableCountTot = billableCountTot;
	}
	public String getNbCountTot() {
		return nbCountTot;
	}
	public void setNbCountTot(String nbCountTot) {
		this.nbCountTot = nbCountTot;
	}
	public String getBillableCountPerc() {
		return billableCountPerc;
	}
	public void setBillableCountPerc(String billableCountPerc) {
		this.billableCountPerc = billableCountPerc;
	}
	public String getNbCountPerc() {
		return nbCountPerc;
	}
	public void setNbCountPerc(String nbCountPerc) {
		this.nbCountPerc = nbCountPerc;
	}
	public String getOnbillableCount() {
		return onbillableCount;
	}
	public void setOnbillableCount(String onbillableCount) {
		this.onbillableCount = onbillableCount;
	}
	public String getOffbillabeCount() {
		return offbillabeCount;
	}
	public void setOffbillabeCount(String offbillabeCount) {
		this.offbillabeCount = offbillabeCount;
	}
	public String getOnbillabePerc() {
		return onbillabePerc;
	}
	public void setOnbillabePerc(String onbillabePerc) {
		this.onbillabePerc = onbillabePerc;
	}
	public String getOffbillabePerc() {
		return offbillabePerc;
	}
	public void setOffbillabePerc(String offbillabePerc) {
		this.offbillabePerc = offbillabePerc;
	}
	
	
	
	
}
