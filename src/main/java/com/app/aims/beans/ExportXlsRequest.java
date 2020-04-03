package com.app.aims.beans;

public class ExportXlsRequest {

	private Integer baseLine;

	public ExportXlsRequest() {
		super();
	}

	public ExportXlsRequest(Integer baseLine) {
		super();
		this.baseLine = baseLine;
	}

	public Integer getBaseLine() {
		return baseLine;
	}

	public void setBaseLine(Integer baseLine) {
		this.baseLine = baseLine;
	}
	
}
