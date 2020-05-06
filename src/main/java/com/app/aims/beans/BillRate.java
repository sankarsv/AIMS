package com.app.aims.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="billrate",schema="aims")
public class BillRate  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7536774219345394015L;
	@Id
	@Column(name="employee_id")	
	private String empId;
	
	@Column(name="billrate")	
	private String billrate;
	
	@Column(name="currencycr")	
	private String currentyCR;
	
	@Column(name="startdate")	
	private String startdate;
	
	@Column(name="enddate")	
	private String enddate;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getBillrate() {
		return billrate;
	}

	public void setBillrate(String billrate) {
		this.billrate = billrate;
	}

	public String getCurrentyCR() {
		return currentyCR;
	}

	public void setCurrentyCR(String currentyCR) {
		this.currentyCR = currentyCR;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	
	
}
