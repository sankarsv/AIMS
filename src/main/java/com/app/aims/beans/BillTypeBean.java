package com.app.aims.beans;

import java.io.Serializable;

public class BillTypeBean extends AbstractReportBean implements Serializable {

	private String fpCountTotal;
	private String tmCountTotal;
	private String onFPCountTotal;
	private String onTMCountTotal;
	private String offFPCountTotal;
	private String offTMCountTotal;
	private String fpCountPerc;
	private String tmCountPerc;

	private String ontmCountPerc;
	private String onfpCountPerc;
	private String offtmCountPerc;
	private String offfpCountPerc;
	
	public String getFpCountTotal() {
		return fpCountTotal;
	}
	public void setFpCountTotal(String fpCountTotal) {
		this.fpCountTotal = fpCountTotal;
	}
	public String getTmCountTotal() {
		return tmCountTotal;
	}
	public void setTmCountTotal(String tmCountTotal) {
		this.tmCountTotal = tmCountTotal;
	}
	public String getOnFPCountTotal() {
		return onFPCountTotal;
	}
	public void setOnFPCountTotal(String onFPCountTotal) {
		this.onFPCountTotal = onFPCountTotal;
	}
	public String getOnTMCountTotal() {
		return onTMCountTotal;
	}
	public void setOnTMCountTotal(String onTMCountTotal) {
		this.onTMCountTotal = onTMCountTotal;
	}
	public String getOffFPCountTotal() {
		return offFPCountTotal;
	}
	public void setOffFPCountTotal(String offFPCountTotal) {
		this.offFPCountTotal = offFPCountTotal;
	}
	public String getOffTMCountTotal() {
		return offTMCountTotal;
	}
	public void setOffTMCountTotal(String offTMCountTotal) {
		this.offTMCountTotal = offTMCountTotal;
	}
	public String getFpCountPerc() {
		return fpCountPerc;
	}
	public void setFpCountPerc(String fpCountPerc) {
		this.fpCountPerc = fpCountPerc;
	}
	public String getTmCountPerc() {
		return tmCountPerc;
	}
	public void setTmCountPerc(String tmCountPerc) {
		this.tmCountPerc = tmCountPerc;
	}
	public String getOntmCountPerc() {
		return ontmCountPerc;
	}
	public void setOntmCountPerc(String ontmCountPerc) {
		this.ontmCountPerc = ontmCountPerc;
	}
	public String getOnfpCountPerc() {
		return onfpCountPerc;
	}
	public void setOnfpCountPerc(String onfpCountPerc) {
		this.onfpCountPerc = onfpCountPerc;
	}
	public String getOfftmCountPerc() {
		return offtmCountPerc;
	}
	public void setOfftmCountPerc(String offtmCountPerc) {
		this.offtmCountPerc = offtmCountPerc;
	}
	public String getOfffpCountPerc() {
		return offfpCountPerc;
	}
	public void setOfffpCountPerc(String offfpCountPerc) {
		this.offfpCountPerc = offfpCountPerc;
	}

	
}
