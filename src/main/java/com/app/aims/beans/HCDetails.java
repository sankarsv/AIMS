package com.app.aims.beans;

import java.io.Serializable;
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
@Table(name="HCMASTER",schema="aims")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HCDetails implements Serializable {
	
	@Id 
	@Column(name="VERSION_NO")
	private int versionNo;

	@Id
	@Column(name="EMPLOYEE_ID")
	private int empNo;
	
	@Column(name="WORK_GEOGRAPHY")
	private String workGeography;

	@Column(name="WORK_COUNTRY")
	private String workCountry;

	@Column(name="WORK_LOCATION")
	private String workLocation;

	@Column(name="CLIENT_GEOGRAPHY")
	private String clientGeography;

	@Column(name="CLIENT_COUNTRY")
	private String clientCountry;

	@Column(name="IP")
	private String ip;

	@Column(name="SP")
	private String sp;
	

	@Column(name="SUB_SP")
	private String subSP;
	

	@Column(name="CUSTOMER")
	private String customer;
	

	@Column(name="GROUP_CUSTOMER")
	private String groupCustomer;
	
	@Column(name="RM")
	private String rm;

	@Column(name="BRM")
	private String brm;

	@Column(name="GL")
	private String gl;
	
	@Column(name="AM_ID")
	private String amID;
	
	@Column(name="AM")
	private String am;;
	
	@Column(name="PROJECT_ID")
	private String projectID;
	
	@Column(name="PL")
	private String pl;
	
	@Column(name="PROJECT_NAME")
	private String projectName;
	
	@Column(name="PROJECT_LOCATION")
	private String projectLoc;

	@Column(name="PROJECT_TYPE")
	private String projectType;

	@Column(name="TURNKEY")
	private String turnkey;

	@Column(name="SWONCATEGORY")
	private String snowcategory;

	@Column(name="CLUSTER")
	private String cluster;


	@Column(name="IOU")
	private String iou;
	
	@Column(name="SUB_IOU")
	private String subiou;
	
	@Column(name="EMP_NAME")
	private String empName;
	
	@Column(name="BRM_1")
	private String brm1;

	@Column(name="DM")
	private String dm;
	

	@Column(name="EMPLOYEE_TYPE")
	private String employeetype;


	@Column(name="DOJ")
	private String doj;

	@Column(name="DEPUTEB_RANCH")
	private String deupteBranch;
	

	@Column(name="DEPUTE_DC")
	private String deputeDC;
	
	@Column(name="EMPLOYEE_LOCATION")
	private String empAllocation;
	
	@Column(name="BASE_COUNTRY")
	private String baseCountry;
	
	@Column(name="BASE_BRANCH")
	private String baseBranch;
	
	@Column(name="BASE_DC")
	private String baseDC;
	@Column(name="TRAVEL_COUNTRY")
	private String travelCountry;
	@Column(name="TRAVEL_TYPE")
	private String travelType;
	@Column(name="DESIGNATION")
	private String designation;
	

	@Column(name="GRADE")
	private String grade;
	@Column(name="MAPP_DESIGNATION")
	private String mapDesignation;
	@Column(name="SENIOR_JUNIOR")
	private String seniorjunior;
	@Column(name="CC_IND")
	private String ccInd;

	@Column(name="PERSON_TYPE")
	private String personType;
	@Column(name="SUB_PERSON_TYPE")
	private String subPersonType;
	@Column(name="SDB_NAME")
	private String sdbname;
	@Column(name="EXPERIENCE")
	private String experience;
	
	@Column(name="TOTAL_EXPERIENCE")
	private String totExp;
	@Column(name="ALLOCATION_START")
	private String allocStart;
	@Column(name="ALLOCATION_END")
	private String allocEnd;
	@Column(name="PERCENTAGE_ALLOCATION")
	private String percAlloc;
	
	@Column(name="EMPLOYEE_CLUSTER")
	private String employeecluster;
	@Column(name="PARENT_IOU")
	private String parentiou;
	@Column(name="CHILD_IOU")
	private String childiou;
	@Column(name="TEAM_ROLE")
	private String teamrole;
	

	@Column(name="PLATFORM")
	private String platform;
	@Column(name="DC")
	private String dc;
	@Column(name="SITE")
	private String site;
	
	
	
	@Column(name="employee_id")
	private int employeeId;

    @Column(name="employee_type")
    private String employeeType;

    
    public HCDetails() {
    	super();
    }


	public int getVersionNo() {
		return versionNo;
	}


	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}


	public int getEmpNo() {
		return empNo;
	}


	public void setEmpNo(int empNo) {
		this.empNo = empNo;
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


	public String getSp() {
		return sp;
	}


	public void setSp(String sp) {
		this.sp = sp;
	}


	public String getSubSP() {
		return subSP;
	}


	public void setSubSP(String subSP) {
		this.subSP = subSP;
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


	public String getRm() {
		return rm;
	}


	public void setRm(String rm) {
		this.rm = rm;
	}


	public String getBrm() {
		return brm;
	}


	public void setBrm(String brm) {
		this.brm = brm;
	}


	public String getGl() {
		return gl;
	}


	public void setGl(String gl) {
		this.gl = gl;
	}


	public String getAmID() {
		return amID;
	}


	public void setAmID(String amID) {
		this.amID = amID;
	}


	public String getAm() {
		return am;
	}


	public void setAm(String am) {
		this.am = am;
	}


	public String getProjectID() {
		return projectID;
	}


	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}


	public String getPl() {
		return pl;
	}


	public void setPl(String pl) {
		this.pl = pl;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getProjectLoc() {
		return projectLoc;
	}


	public void setProjectLoc(String projectLoc) {
		this.projectLoc = projectLoc;
	}


	public String getProjectType() {
		return projectType;
	}


	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}


	public String getTurnkey() {
		return turnkey;
	}


	public void setTurnkey(String turnkey) {
		this.turnkey = turnkey;
	}


	public String getSnowcategory() {
		return snowcategory;
	}


	public void setSnowcategory(String snowcategory) {
		this.snowcategory = snowcategory;
	}


	public String getCluster() {
		return cluster;
	}


	public void setCluster(String cluster) {
		this.cluster = cluster;
	}


	public String getIou() {
		return iou;
	}


	public void setIou(String iou) {
		this.iou = iou;
	}


	public String getSubiou() {
		return subiou;
	}


	public void setSubiou(String subiou) {
		this.subiou = subiou;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public String getBrm1() {
		return brm1;
	}


	public void setBrm1(String brm1) {
		this.brm1 = brm1;
	}


	public String getDm() {
		return dm;
	}


	public void setDm(String dm) {
		this.dm = dm;
	}


	public String getEmployeetype() {
		return employeetype;
	}


	public void setEmployeetype(String employeetype) {
		this.employeetype = employeetype;
	}


	public String getDoj() {
		return doj;
	}


	public void setDoj(String doj) {
		this.doj = doj;
	}


	public String getDeupteBranch() {
		return deupteBranch;
	}


	public void setDeupteBranch(String deupteBranch) {
		this.deupteBranch = deupteBranch;
	}


	public String getDeputeDC() {
		return deputeDC;
	}


	public void setDeputeDC(String deputeDC) {
		this.deputeDC = deputeDC;
	}


	public String getEmpAllocation() {
		return empAllocation;
	}


	public void setEmpAllocation(String empAllocation) {
		this.empAllocation = empAllocation;
	}


	public String getBaseCountry() {
		return baseCountry;
	}


	public void setBaseCountry(String baseCountry) {
		this.baseCountry = baseCountry;
	}


	public String getBaseBranch() {
		return baseBranch;
	}


	public void setBaseBranch(String baseBranch) {
		this.baseBranch = baseBranch;
	}


	public String getBaseDC() {
		return baseDC;
	}


	public void setBaseDC(String baseDC) {
		this.baseDC = baseDC;
	}


	public String getTravelCountry() {
		return travelCountry;
	}


	public void setTravelCountry(String travelCountry) {
		this.travelCountry = travelCountry;
	}


	public String getTravelType() {
		return travelType;
	}


	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getMapDesignation() {
		return mapDesignation;
	}


	public void setMapDesignation(String mapDesignation) {
		this.mapDesignation = mapDesignation;
	}


	public String getSeniorjunior() {
		return seniorjunior;
	}


	public void setSeniorjunior(String seniorjunior) {
		this.seniorjunior = seniorjunior;
	}


	public String getCcInd() {
		return ccInd;
	}


	public void setCcInd(String ccInd) {
		this.ccInd = ccInd;
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


	public String getSdbname() {
		return sdbname;
	}


	public void setSdbname(String sdbname) {
		this.sdbname = sdbname;
	}


	public String getExperience() {
		return experience;
	}


	public void setExperience(String experience) {
		this.experience = experience;
	}


	public String getTotExp() {
		return totExp;
	}


	public void setTotExp(String totExp) {
		this.totExp = totExp;
	}


	public String getAllocStart() {
		return allocStart;
	}


	public void setAllocStart(String allocStart) {
		this.allocStart = allocStart;
	}


	public String getAllocEnd() {
		return allocEnd;
	}


	public void setAllocEnd(String allocEnd) {
		this.allocEnd = allocEnd;
	}


	public String getPercAlloc() {
		return percAlloc;
	}


	public void setPercAlloc(String percAlloc) {
		this.percAlloc = percAlloc;
	}


	public String getEmployeecluster() {
		return employeecluster;
	}


	public void setEmployeecluster(String employeecluster) {
		this.employeecluster = employeecluster;
	}


	public String getParentiou() {
		return parentiou;
	}


	public void setParentiou(String parentiou) {
		this.parentiou = parentiou;
	}


	public String getChildiou() {
		return childiou;
	}


	public void setChildiou(String childiou) {
		this.childiou = childiou;
	}


	public String getTeamrole() {
		return teamrole;
	}


	public void setTeamrole(String teamrole) {
		this.teamrole = teamrole;
	}


	public String getPlatform() {
		return platform;
	}


	public void setPlatform(String platform) {
		this.platform = platform;
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
    
    
   }
