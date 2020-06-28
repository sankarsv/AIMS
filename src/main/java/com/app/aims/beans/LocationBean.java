package com.app.aims.beans;

import java.io.Serializable;

public class LocationBean implements Serializable {

	private String geography;
	private String location;
	private String count;
	
	public String getGeography() {
		return geography;
	}
	public void setGeography(String geography) {
		this.geography = geography;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
}
