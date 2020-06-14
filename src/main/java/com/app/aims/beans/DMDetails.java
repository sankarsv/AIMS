package com.app.aims.beans;

import java.io.Serializable;

public class DMDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dmId;
	private String dmName;
	public String getDmId() {
		return dmId;
	}
	public void setDmId(String dmId) {
		this.dmId = dmId;
	}
	public String getDmName() {
		return dmName;
	}
	public void setDmName(String dmName) {
		this.dmName = dmName;
	}
	
}
