package com.app.aims.beans;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name="employee_allocation_percentage",schema="public")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeAllocationPercentage {

//	@Column(name="id" )
//	@NotNull
//	@Id
    private Integer employeeAllocationPercentage;
	
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "employee_id")
    private EmployeeAllocation employeeId;
	
	
//	@Column(name="percentage_allocation" )
//	@NotNull
    private Integer percentageAllocation;


	public Integer getEmployeeAllocationPercentage() {
		return employeeAllocationPercentage;
	}


	public void setEmployeeAllocationPercentage(Integer employeeAllocationPercentage) {
		this.employeeAllocationPercentage = employeeAllocationPercentage;
	}


	public EmployeeAllocation getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(EmployeeAllocation employeeId) {
		this.employeeId = employeeId;
	}


	public Integer getPercentageAllocation() {
		return percentageAllocation;
	}


	public void setPercentageAllocation(Integer percentageAllocation) {
		this.percentageAllocation = percentageAllocation;
	}
	
	
}
