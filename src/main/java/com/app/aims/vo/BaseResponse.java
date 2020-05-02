package com.app.aims.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseResponse {

	private String empId;
	private String brmId;
	private String dmId;
	
	@JsonIgnore
	private List<String> errorList;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public String getDmId() {
		return dmId;
	}
	public void setDmId(String dmId) {
		this.dmId = dmId;
	}
	public String getBrmId() {
		return brmId;
	}
	public void setBrmId(String brmId) {
		this.brmId = brmId;
	}
	public List<String> getErrorList() {
		if(this.errorList == null) {
			this.errorList = new ArrayList<String>();
		}
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	
	public void addError(String error) {
		getErrorList().add(error);
	}
}
