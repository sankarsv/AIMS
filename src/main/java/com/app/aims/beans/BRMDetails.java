package com.app.aims.beans;

import java.io.Serializable;
import java.util.List;

public class BRMDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brmId;
	private String brmName;
	
	private List<LocationBean> locationDetails;
	
	public String getBrmId() {
		return brmId;
	}
	public void setBrmId(String brmId) {
		this.brmId = brmId;
	}
	public String getBrmName() {
		return brmName;
	}
	public void setBrmName(String brmName) {
		this.brmName = brmName;
	}
	public List<LocationBean> getLocationDetails() {
		return locationDetails;
	}
	public void setLocationDetails(List<LocationBean> locationDetails) {
		this.locationDetails = locationDetails;
	}
	
	
	
}
