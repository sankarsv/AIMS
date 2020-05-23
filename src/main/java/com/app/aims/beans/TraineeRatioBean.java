package com.app.aims.beans;

import java.io.Serializable;

public class TraineeRatioBean extends AbstractReportBean implements Serializable {

	private String trCountTot;
	private String trCountPerc;
	private String ontrCountTot;
	private String ontrCountPerc;
	private String offtrCountTot;
	private String offtrCountPerc;
	public String getTrCountTot() {
		return trCountTot;
	}
	public void setTrCountTot(String trCountTot) {
		this.trCountTot = trCountTot;
	}
	public String getTrCountPerc() {
		return trCountPerc;
	}
	public void setTrCountPerc(String trCountPerc) {
		this.trCountPerc = trCountPerc;
	}
	public String getOntrCountTot() {
		return ontrCountTot;
	}
	public void setOntrCountTot(String ontrCountTot) {
		this.ontrCountTot = ontrCountTot;
	}
	public String getOntrCountPerc() {
		return ontrCountPerc;
	}
	public void setOntrCountPerc(String ontrCountPerc) {
		this.ontrCountPerc = ontrCountPerc;
	}
	public String getOfftrCountTot() {
		return offtrCountTot;
	}
	public void setOfftrCountTot(String offtrCountTot) {
		this.offtrCountTot = offtrCountTot;
	}
	public String getOfftrCountPerc() {
		return offtrCountPerc;
	}
	public void setOfftrCountPerc(String offtrCountPerc) {
		this.offtrCountPerc = offtrCountPerc;
	}
	
	
}
