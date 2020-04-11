package com.app.aims.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="employee",schema="public")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Employee {
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBaseBranch() {
		return baseBranch;
	}

	public void setBaseBranch(String baseBranch) {
		this.baseBranch = baseBranch;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOverallExp() {
		return overallExp;
	}

	public void setOverallExp(String overallExp) {
		this.overallExp = overallExp;
	}

	public String getAimsExp() {
		return aimsExp;
	}

	public void setAimsExp(String aimsExp) {
		this.aimsExp = aimsExp;
	}

	public String getBaseCountry() {
		return baseCountry;
	}

	public void setBaseCountry(String baseCountry) {
		this.baseCountry = baseCountry;
	}

	public String getBaseDc() {
		return baseDc;
	}

	public void setBaseDc(String baseDc) {
		this.baseDc = baseDc;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Id 
	@Column(name="id")
	private int employeeId;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empId")
	private List<EmployeeAllocation> employeeAllocations;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "employeeId")
	private EmployeeAllocationPercentage employeeAllocationPercentage;

	

	public EmployeeAllocationPercentage getEmployeeAllocationPercentage() {
		return employeeAllocationPercentage;
	}

	public void setEmployeeAllocationPercentage(EmployeeAllocationPercentage employeeAllocationPercentage) {
		this.employeeAllocationPercentage = employeeAllocationPercentage;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<EmployeeAllocation> getEmployeeAllocations() {
		return employeeAllocations;
	}

	public void setEmployeeAllocations(List<EmployeeAllocation> employeeAllocations) {
		this.employeeAllocations = employeeAllocations;
	}

	public Employee(int employeeId, List<EmployeeAllocation> employeeAllocation, String employeeType, String currentLocation, String firstName, String lastName,
			String baseBranch, String dob, String gender, String overallExp, String aimsExp, String baseCountry,
			String baseDc, String categoryName, String grade) {
		super();
		this.employeeAllocations = employeeAllocation;
		this.employeeId = employeeId;
		this.employeeType = employeeType;
		this.currentLocation = currentLocation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.baseBranch = baseBranch;
		this.dob = dob;
		this.gender = gender;
		this.overallExp = overallExp;
		this.aimsExp = aimsExp;
		this.baseCountry = baseCountry;
		this.baseDc = baseDc;
		this.categoryName = categoryName;
		this.grade = grade;
	}

	public Employee() {
		super();
	}

	@Column(name="employee_type")
    private String employeeType;

    @Column(name="current_location")
    private String currentLocation;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="base_branch")
    private String baseBranch;
    
    @Column(name="dob")
    private String dob;
    
    @Column(name="gender")
    private String gender;
    
    @Column(name="overall_exp")
    private String overallExp;
    
    @Column(name="aims_exp")
    private String aimsExp;
    
    @Column(name="base_country")
    private String baseCountry;
    
    @Column(name="base_dc")
    private String baseDc;
    
    @Column(name="category_name")
    private String categoryName;
    
    @Column(name="grade")
    private String grade;
    
    @Column(name="created_at")
    private Date createdAt;
    
    @Column(name="status")
    private String status;



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}