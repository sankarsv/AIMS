package com.app.aims.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="employee_merged_details",schema="aims")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeMergedDetails {
	
	@Id 
	@Column(name="id")
	private int employeeId;

    @Column(name="employee_type")
    private String employeeType;

    public EmployeeMergedDetails() {}
    
    public EmployeeMergedDetails(int employeeId, String employeeType, String currentLocation, String firstName,
			String lastName, String baseBranch, String dob, String gender, String overallExp, String aimsExp,
			String baseCountry, String baseDc, String categoryName, String grade, @NotNull Integer wonId,
			@NotNull Integer portfolioId, @NotNull LocalDate startDate, @NotNull LocalDate endDate,
			@NotNull String travelType, @NotNull String employeeTravelCountry, @NotNull String teamRole,
			@NotNull String employeeActiveClientId, @NotNull String deputeBranch, @NotNull String deputeDc,
			@NotNull String employeeLocationId, @NotNull Integer percentageAllocation, @NotNull String portfolioName,
			@NotNull String portfolioType, @NotNull Integer brmEmpId, @NotNull Integer onsiteLeadEmpId,
			@NotNull Integer dmEmpId, @NotNull String mappDesignation,  @NotNull Integer offshoreLeadEmpId, @NotNull Integer billingEmpId,
			@NotNull String description, @NotNull Integer projectId, @NotNull String projectName,
			@NotNull String projectLocation, @NotNull LocalDate projectChangeDate, @NotNull String workGeography,
			@NotNull String workCountry, @NotNull String workLocation, @NotNull String clientGeography,
			@NotNull String clientCountry, @NotNull String ip, @NotNull String customer, @NotNull String groupCustomer,
			@NotNull String projectHash, @NotNull String projectLocationWrtIndia, @NotNull String projectType,
			@NotNull String pureTurnkeyFlag, @NotNull String swonCategory, @NotNull String projectCluster,
			@NotNull String iou, @NotNull String subIou, @NotNull String parentIou, @NotNull String childIou,
			@NotNull String bfsCluster,@NotNull BaseLine baseLine ) {
		super();
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
		this.wonId = wonId;
		this.portfolioId = portfolioId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.travelType = travelType;
		this.employeeTravelCountry = employeeTravelCountry;
		this.teamRole = teamRole;
		this.employeeActiveClientId = employeeActiveClientId;
		this.deputeBranch = deputeBranch;
		this.deputeDc = deputeDc;
		this.employeeLocationId = employeeLocationId;
		this.percentageAllocation = percentageAllocation;
		this.portfolioName = portfolioName;
		this.portfolioType = portfolioType;
		this.brmEmpId = brmEmpId;
		this.onsiteLeadEmpId = onsiteLeadEmpId;
		this.dmEmpId = dmEmpId;
		this.offshoreLeadEmpId = offshoreLeadEmpId;
		this.billingEmpId = billingEmpId;
		this.description = description;
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectLocation = projectLocation;
		this.projectChangeDate = projectChangeDate;
		this.workGeography = workGeography;
		this.workCountry = workCountry;
		this.workLocation = workLocation;
		this.clientGeography = clientGeography;
		this.clientCountry = clientCountry;
		this.ip = ip;
		this.customer = customer;
		this.groupCustomer = groupCustomer;
		this.projectHash = projectHash;
		this.projectLocationWrtIndia = projectLocationWrtIndia;
		this.projectType = projectType;
		this.pureTurnkeyFlag = pureTurnkeyFlag;
		this.swonCategory = swonCategory;
		this.projectCluster = projectCluster;
		this.iou = iou;
		this.subIou = subIou;
		this.parentIou = parentIou;
		this.childIou = childIou;
		this.bfsCluster = bfsCluster;
		this.mappDesignation = mappDesignation;
		this.baseLine = baseLine;
	}

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

	@Column(name="won_id"  )
	@NotNull
    private Integer wonId;

	@Column(name="portfolio_id"  )
	@NotNull
    private Integer portfolioId;
	
	@Column(name="start_date"  )
	@NotNull
    private LocalDate startDate;
	
	@Column(name="end_date"  )
	@NotNull
    private LocalDate endDate;
	
	@Column(name="travel_type"  )
	@NotNull
    private String travelType;
	
	@Column(name="employee_travel_country")
	@NotNull
    private String employeeTravelCountry;
	
	@Column(name="team_role")
	@NotNull
    private String teamRole;
	
	@Column(name="employee_active_client_id")
	@NotNull
    private String employeeActiveClientId;
	
	@Column(name="depute_branch")
	@NotNull
    private String deputeBranch;
	
	@Column(name="depute_dc")
	@NotNull
    private String deputeDc;
	
	@Column(name="employee_location_id")
	@NotNull
    private String employeeLocationId;
	
	@Column(name="percentage_allocation" )
	@NotNull
    private Integer percentageAllocation;

	@Column(name="portfolio_name" )
	@NotNull
    private String portfolioName;

	@Column(name="portfolio_type" )
	@NotNull
    private String portfolioType;
	
	@Column(name="brm_empId" )
	@NotNull
    private Integer brmEmpId;
	
	@Column(name="onsite_lead_empid" )
	@NotNull
    private Integer onsiteLeadEmpId;
	
	@Column(name="dm_emp_id" )
	@NotNull
    private Integer dmEmpId;
	
	@Column(name="mapp_designation" )
	@NotNull
    private String mappDesignation;
	
	public String getMappDesignation() {
		return mappDesignation;
	}

	public void setMappDesignation(String mappDesignation) {
		this.mappDesignation = mappDesignation;
	}

	@Column(name="offshore_lead_emp_id" )
	@NotNull
    private Integer offshoreLeadEmpId;
	
	@Column(name="billing_emp_id" )
	@NotNull
    private Integer billingEmpId;
	
	@Column(name="description" )
	@NotNull
    private String description;
	
	
	@Column(name="project_id" )
	@NotNull
    private Integer projectId;
	
	@Column(name="project_name" )
	@NotNull
    private String projectName;
	
	@Column(name="project_location" )
	@NotNull
    private String projectLocation;
	
	@Column(name="project_change_date" )
	@NotNull
    private LocalDate projectChangeDate;
	
	@Column(name="work_geography" )
	@NotNull
    private String workGeography;
	
	@Column(name="work_country" )
	@NotNull
    private String workCountry;
	
	@Column(name="work_location" )
	@NotNull
    private String workLocation;
	
	@Column(name="client_geography" )
	@NotNull
    private String clientGeography;
	
	@Column(name="client_country" )
	@NotNull
    private String clientCountry;
	
	@Column(name="ip" )
	@NotNull
    private String ip;
	
	@Column(name="customer" )
	@NotNull
    private String customer;
	
	@Column(name="group_customer" )
	@NotNull
    private String groupCustomer;
	
	@Column(name="project_hash" )
	@NotNull
    private String projectHash;
	
	@Column(name="project_location_wrt_india" )
	@NotNull
    private String projectLocationWrtIndia;
	
	@Column(name="project_type" )
	@NotNull
    private String projectType;
	
	@Column(name="pure_turnkey_flag" )
	@NotNull
    private String pureTurnkeyFlag;
	
	@Column(name="swon_category" )
	@NotNull
    private String swonCategory;
	
	
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

	public Integer getWonId() {
		return wonId;
	}

	public void setWonId(Integer wonId) {
		this.wonId = wonId;
	}

	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getEmployeeTravelCountry() {
		return employeeTravelCountry;
	}

	public void setEmployeeTravelCountry(String employeeTravelCountry) {
		this.employeeTravelCountry = employeeTravelCountry;
	}

	public String getTeamRole() {
		return teamRole;
	}

	public void setTeamRole(String teamRole) {
		this.teamRole = teamRole;
	}

	public String getEmployeeActiveClientId() {
		return employeeActiveClientId;
	}

	public void setEmployeeActiveClientId(String employeeActiveClientId) {
		this.employeeActiveClientId = employeeActiveClientId;
	}

	public String getDeputeBranch() {
		return deputeBranch;
	}

	public void setDeputeBranch(String deputeBranch) {
		this.deputeBranch = deputeBranch;
	}

	public String getDeputeDc() {
		return deputeDc;
	}

	public void setDeputeDc(String deputeDc) {
		this.deputeDc = deputeDc;
	}

	public String getEmployeeLocationId() {
		return employeeLocationId;
	}

	public void setEmployeeLocationId(String employeeLocationId) {
		this.employeeLocationId = employeeLocationId;
	}

	public Integer getPercentageAllocation() {
		return percentageAllocation;
	}

	public void setPercentageAllocation(Integer percentageAllocation) {
		this.percentageAllocation = percentageAllocation;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getPortfolioType() {
		return portfolioType;
	}

	public void setPortfolioType(String portfolioType) {
		this.portfolioType = portfolioType;
	}

	public Integer getBrmEmpId() {
		return brmEmpId;
	}

	public void setBrmEmpId(Integer brmEmpId) {
		this.brmEmpId = brmEmpId;
	}

	public Integer getOnsiteLeadEmpId() {
		return onsiteLeadEmpId;
	}

	public void setOnsiteLeadEmpId(Integer onsiteLeadEmpId) {
		this.onsiteLeadEmpId = onsiteLeadEmpId;
	}

	public Integer getDmEmpId() {
		return dmEmpId;
	}

	public void setDmEmpId(Integer dmEmpId) {
		this.dmEmpId = dmEmpId;
	}

	public Integer getOffshoreLeadEmpId() {
		return offshoreLeadEmpId;
	}

	public void setOffshoreLeadEmpId(Integer offshoreLeadEmpId) {
		this.offshoreLeadEmpId = offshoreLeadEmpId;
	}

	public Integer getBillingEmpId() {
		return billingEmpId;
	}

	public void setBillingEmpId(Integer billingEmpId) {
		this.billingEmpId = billingEmpId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public LocalDate getProjectChangeDate() {
		return projectChangeDate;
	}

	public void setProjectChangeDate(LocalDate projectChangeDate) {
		this.projectChangeDate = projectChangeDate;
	}

	public String getWorkGeography() {
		return workGeography;
	}

	public void setWorkGeography(String workGeography) {
		this.workGeography = workGeography;
	}

	public String getWorkCountry() {
		return workCountry;
	}

	public void setWorkCountry(String workCountry) {
		this.workCountry = workCountry;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getClientGeography() {
		return clientGeography;
	}

	public void setClientGeography(String clientGeography) {
		this.clientGeography = clientGeography;
	}

	public String getClientCountry() {
		return clientCountry;
	}

	public void setClientCountry(String clientCountry) {
		this.clientCountry = clientCountry;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getGroupCustomer() {
		return groupCustomer;
	}

	public void setGroupCustomer(String groupCustomer) {
		this.groupCustomer = groupCustomer;
	}

	public String getProjectHash() {
		return projectHash;
	}

	public void setProjectHash(String projectHash) {
		this.projectHash = projectHash;
	}

	public String getProjectLocationWrtIndia() {
		return projectLocationWrtIndia;
	}

	public void setProjectLocationWrtIndia(String projectLocationWrtIndia) {
		this.projectLocationWrtIndia = projectLocationWrtIndia;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getPureTurnkeyFlag() {
		return pureTurnkeyFlag;
	}

	public void setPureTurnkeyFlag(String pureTurnkeyFlag) {
		this.pureTurnkeyFlag = pureTurnkeyFlag;
	}

	public String getSwonCategory() {
		return swonCategory;
	}

	public void setSwonCategory(String swonCategory) {
		this.swonCategory = swonCategory;
	}

	public String getProjectCluster() {
		return projectCluster;
	}

	public void setProjectCluster(String projectCluster) {
		this.projectCluster = projectCluster;
	}

	public String getIou() {
		return iou;
	}

	public void setIou(String iou) {
		this.iou = iou;
	}

	public String getSubIou() {
		return subIou;
	}

	public void setSubIou(String subIou) {
		this.subIou = subIou;
	}

	public String getParentIou() {
		return parentIou;
	}

	public void setParentIou(String parentIou) {
		this.parentIou = parentIou;
	}

	public String getChildIou() {
		return childIou;
	}

	public void setChildIou(String childIou) {
		this.childIou = childIou;
	}

	public String getBfsCluster() {
		return bfsCluster;
	}

	public void setBfsCluster(String bfsCluster) {
		this.bfsCluster = bfsCluster;
	}

	@Column(name="project_cluster" )
	@NotNull
    private String projectCluster;
	
	@Column(name="iou" )
	@NotNull
    private String iou;
	
	@Column(name="sub_iou" )
	@NotNull
    private String subIou;
	
	@Column(name="parent_iou_name" )
	@NotNull
    private String parentIou;
	
	@Column(name="child_iou_name" )
	@NotNull
    private String childIou;
	
	@Column(name="bfs_cluster" )
	@NotNull
    private String bfsCluster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="base_line"  )
    private BaseLine baseLine;

	public BaseLine getBaseLine() {
		return baseLine;
	}

	public void setBaseLine(BaseLine baseLine) {
		this.baseLine = baseLine;
	}
	
	
}
