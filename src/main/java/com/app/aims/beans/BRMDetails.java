package com.app.aims.beans;

import java.io.Serializable;

public class BRMDetails implements Serializable{

	private String brmId;
	private String brmName;
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
	
	
}
