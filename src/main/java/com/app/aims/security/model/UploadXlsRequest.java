package com.app.aims.security.model;


public class UploadXlsRequest {

	private String xlsBytes;
	
	
	public void setXlsBytes(String xlsBytes) {
		this.xlsBytes = xlsBytes;
	}
	
	
	public String getXlsBytes() {
		return this.xlsBytes;
	}
	
	public UploadXlsRequest(String xlsBytes) {
		this.xlsBytes = xlsBytes;
	}
	
}
