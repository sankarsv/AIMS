package com.app.aims.beans;

public class GenerateBaseLineRequest {

	private String date; // date format dd-mm-yyyy. This is the date for which base line needs to be created.

	public GenerateBaseLineRequest(String date) {
		super();
		this.date = date;
	}

	public GenerateBaseLineRequest() {
		
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
