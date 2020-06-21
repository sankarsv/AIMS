package com.app.aims.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
@Table(name="employee",schema="aims")
public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id 
	@Column(name="employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	
	
	@OneToMany(targetEntity=EmployeeAllocation.class, mappedBy = "empId", orphanRemoval = true, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EmployeeAllocation> employeeAllocations;
	
//	@OneToOne(fetch = FetchType.LAZY, mappedBy = "employeeId")
//	private EmployeeAllocationPercentage employeeAllocationPercentage;

	

	public Employee() {
		super();
	}

	@Column(name="employee_type")
    private String employeeType;

    @Column(name="current_location")
    private String currentLocation;
    


    @Column(name="office_id")
    private String officeId;
    
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
    
    @Column(name="cc_indicator")
    private String ccIndicator;
    
    @Column(name="mapp_designation")
    private String mappDesignation;
    
    @Column(name="senior_junior")
    private String seniorJunior;
    @Column(name="person_type")
    private String personType;

    @Column(name="sub_person_type")
    private String subPersonType;
    
    @Column(name="sob_name")
    private String sobName;
    
    @Column(name="joining_date")
    private String joiningDate;
    
    @Column(name="previous_experience")
    private String prevExp;
    
    @Column(name="total_experience")
    private String totalExp;
    
    @Column(name="brm")
    private String brm;
    
    @Column(name="em")
    private String em;
    
    @Column(name="dm")
    private String dm;
    
    @Column(name="deputebranch")
    private String deputeBranch;
    
    @Column(name="deputedc")
    private String deputeDC;
    
    @Column(name="employeelocation")
    private String empLocation;
    
    @Column(name="exployeetravel")
    private String empTravel;
    
    @Column(name="traveltype")
    private String travelType;
    
    @Column(name="employeecluster")
    private String empCluster;
    
    @Column(name="teamrole")
    private String teamRole;
    
    @Column(name="parentiou")
    private String parentIOU;
    
    @Column(name="childiou")
    private String childIOU;
    
    @Column(name="platforms")
    private String platforms;
    
    @Column(name="dc")
    private String dc;
    
    @Column(name="site")
    private String site;
    
    @Column(name="created_at")
    private String createdAT;
    
    @Column(name="status")
    private String status;
    
 	@Column(name="expired_ind")
 	private String expiredInd;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public List<EmployeeAllocation> getEmployeeAllocations() {
		return employeeAllocations;
	}

	public void setEmployeeAllocations(List<EmployeeAllocation> employeeAllocations) {
		this.employeeAllocations = employeeAllocations;
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

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
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

	public String getCcIndicator() {
		return ccIndicator;
	}

	public void setCcIndicator(String ccIndicator) {
		this.ccIndicator = ccIndicator;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getSubPersonType() {
		return subPersonType;
	}

	public void setSubPersonType(String subPersonType) {
		this.subPersonType = subPersonType;
	}

	public String getSobName() {
		return sobName;
	}

	public void setSobName(String sobName) {
		this.sobName = sobName;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getPrevExp() {
		return prevExp;
	}

	public void setPrevExp(String prevExp) {
		this.prevExp = prevExp;
	}

	public String getTotalExp() {
		return totalExp;
	}

	public void setTotalExp(String totalExp) {
		this.totalExp = totalExp;
	}

	public String getBrm() {
		return brm;
	}

	public void setBrm(String brm) {
		this.brm = brm;
	}

	public String getEm() {
		return em;
	}

	public void setEm(String em) {
		this.em = em;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getDeputeBranch() {
		return deputeBranch;
	}

	public void setDeputeBranch(String deputeBranch) {
		this.deputeBranch = deputeBranch;
	}

	public String getDeputeDC() {
		return deputeDC;
	}

	public void setDeputeDC(String deputeDC) {
		this.deputeDC = deputeDC;
	}

	public String getEmpLocation() {
		return empLocation;
	}

	public void setEmpLocation(String empLocation) {
		this.empLocation = empLocation;
	}

	public String getEmpTravel() {
		return empTravel;
	}

	public void setEmpTravel(String empTravel) {
		this.empTravel = empTravel;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getEmpCluster() {
		return empCluster;
	}

	public void setEmpCluster(String empCluster) {
		this.empCluster = empCluster;
	}

	public String getTeamRole() {
		return teamRole;
	}

	public void setTeamRole(String teamRole) {
		this.teamRole = teamRole;
	}

	public String getParentIOU() {
		return parentIOU;
	}

	public void setParentIOU(String parentIOU) {
		this.parentIOU = parentIOU;
	}

	public String getChildIOU() {
		return childIOU;
	}

	public void setChildIOU(String childIOU) {
		this.childIOU = childIOU;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getCreatedAT() {
		return createdAT;
	}

	public void setCreatedAT(String createdAT) {
		this.createdAT = createdAT;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpiredInd() {
		return expiredInd;
	}

	public void setExpiredInd(String expiredInd) {
		this.expiredInd = expiredInd;
	}

	public String getMappDesignation() {
		return mappDesignation;
	}

	public void setMappDesignation(String mappDesignation) {
		this.mappDesignation = mappDesignation;
	}

	public String getSeniorJunior() {
		return seniorJunior;
	}

	public void setSeniorJunior(String seniorJunior) {
		this.seniorJunior = seniorJunior;
	}
 	

    
    
}