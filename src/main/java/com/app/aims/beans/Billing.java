package com.app.aims.beans;

import com.app.aims.beans.BillingId;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="billingmaster",schema="aims",uniqueConstraints=@UniqueConstraint(columnNames= {"version", "employee_id"}))
@IdClass(BillingId.class)
public class Billing  implements Serializable{
	
	public Billing() {
		super();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4423840919843137706L;
	
	@Id
	@Column(name="version")
	private Integer version;
	
	private String projectId; //NA
	
	@Id
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
	private Integer billableHrs;
	
	@Column(name="billabledays")
	private Double billableDays;	
	
	@Column(name="effort")
	private Double effortHrs;
	
	@Column(name="extrahours")
	private Double extraHrs;
	
	@Column(name="extrabilling")
	private Double extraBilling;
	
	@Column(name="billableamount")
	private Double billingAmount;
	
	@Column(name="remarks1")
	private String remarks1;
	
	@Column(name="remarks2")
	private String remarks2;
	
	@OneToOne(mappedBy = "billing", cascade = CascadeType.ALL)
    private BillingRate bilingRate;
	
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
	public String getBrnname() {
		return brnname;
	}
	public void setBrnname(String brnname) {
		this.brnname = brnname;
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
	
	public Integer getBillableHrs() {
		return billableHrs;
	}
	public void setBillableHrs(Integer billableHrs) {
		this.billableHrs = billableHrs;
	}
	public Double getBillableDays() {
		return billableDays;
	}
	public void setBillableDays(Double billableDays) {
		this.billableDays = billableDays;
	}
	public Double getEffortHrs() {
		return effortHrs;
	}
	public void setEffortHrs(Double effortHrs) {
		this.effortHrs = effortHrs;
	}
	public Double getExtraHrs() {
		return extraHrs;
	}
	public void setExtraHrs(Double extraHrs) {
		this.extraHrs = extraHrs;
	}
	public Double getExtraBilling() {
		return extraBilling;
	}
	public void setExtraBilling(Double extraBilling) {
		this.extraBilling = extraBilling;
	}
	public Double getBillingAmount() {
		return billingAmount;
	}
	public void setBillingAmount(Double billingAmount) {
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
	public BillingRate getBilingRate() {
		return bilingRate;
	}
	public void setBilingRate(BillingRate bilingRate) {
		this.bilingRate = bilingRate;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

}
