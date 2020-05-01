package com.app.aims.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="billingmaster",schema="aims",uniqueConstraints=@UniqueConstraint(columnNames= {"version", "employee_id"}))
public class Billing  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4423840919843137706L;
	@Id
	@Column(name="version")
	private int version;
	
	private String projectId; //NA
	@Column(name="employee_id")
	private String empId;
	
	@Column(name="employee_name")	
	private String empName;
	
	@Column(name="dmid")
	private String dmId;
	
	@Column(name="dmname")
	private String dmName;
	

	@Column(name="brmname")
	private String brnname;
	
	
	@Column(name="won")
	private String wonNumber;
	
	@Column(name="onsite_offshore")
	private String locationId;
	
	@Column(name="sto")
	private String stoName;
	
	
	private String officeId;
	
	@Column(name="billablehours")
	private int billableHrs;
	
	@Column(name="billabledays")
	private int billableDays;	
	
	@Column(name="effort")
	private int effortHrs;
	
	@Column(name="extrahours")
	private int extraHrs;
	
	@Column(name="extrabilling")
	private double extraBilling;
	
	@Column(name="billableamount")
	private double billingAmount;
	
	@Column(name="remarks1")
	private String remarks1;
	
	@Column(name="remarks")
	private String remarks2;
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
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
	public String getWonNumber() {
		return wonNumber;
	}
	public void setWonNumber(String wonNumber) {
		this.wonNumber = wonNumber;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getStoName() {
		return stoName;
	}
	public void setStoName(String stoName) {
		this.stoName = stoName;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public int getBillableHrs() {
		return billableHrs;
	}
	public void setBillableHrs(int billableHrs) {
		this.billableHrs = billableHrs;
	}
	public int getBillableDays() {
		return billableDays;
	}
	public void setBillableDays(int billableDays) {
		this.billableDays = billableDays;
	}
	public int getEffortHrs() {
		return effortHrs;
	}
	public void setEffortHrs(int effortHrs) {
		this.effortHrs = effortHrs;
	}
	public int getExtraHrs() {
		return extraHrs;
	}
	public void setExtraHrs(int extraHrs) {
		this.extraHrs = extraHrs;
	}
	public double getExtraBilling() {
		return extraBilling;
	}
	public void setExtraBilling(double extraBilling) {
		this.extraBilling = extraBilling;
	}
	public double getBillingAmount() {
		return billingAmount;
	}
	public void setBillingAmount(double billingAmount) {
		this.billingAmount = billingAmount;
	}
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public String getRemarks2() {
		return remarks2;
	}
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}


	public String getBrnname() {
		return brnname;
	}
	public void setBrnname(String brnname) {
		this.brnname = brnname;
	}
}
