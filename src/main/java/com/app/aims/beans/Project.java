package com.app.aims.beans;


import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="PROJECT",schema="aims")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="project_id" )
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	

	@Column(name="customer" )
	@NotNull
    private String customer;
	
	@Column(name="ip" )
	@NotNull
    private String ip;
	
	
	@Column(name="sp" )
	private String sp;
	

	@Column(name="subsp" )
	private String subsp;
	
	@Column(name="brm" )
	@NotNull
    private String brm;
	
	@Column(name="gl" )
	@NotNull
    private String gl;
	

	@Column(name="amid" )
	@NotNull
    private String amid;
	

	@Column(name="am" )
	@NotNull
    private String am;
	
	
	@Column(name="pl" )
	@NotNull
    private String pl;
	

	@Column(name="group_customer" )
	@NotNull
    private String groupCustomer;
	

	@Column(name="project_hash" )
	@NotNull
    private String projectHash;
	


	@Column(name="project_location_wrt_india " )
	@NotNull
    private String projectLocWRTIndia;
		
		
	@Column(name="project_type" )
	@NotNull
    private String projectType;
	
	@Column(name="pure_turnkey_flag" )
	@NotNull
    private String pureTurnkeyFlag;
	
	@Column(name="swon_category" )
	@NotNull
    private String swonCategory;
	
	
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
	
	@Column(name="expired_ind" )
	private String expiredInd;

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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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

	public String getSubsp() {
		return subsp;
	}

	public void setSubsp(String subsp) {
		this.subsp = subsp;
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

	public String getAmid() {
		return amid;
	}

	public void setAmid(String amid) {
		this.amid = amid;
	}

	public String getAm() {
		return am;
	}

	public void setAm(String am) {
		this.am = am;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
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

	public String getProjectLocWRTIndia() {
		return projectLocWRTIndia;
	}

	public void setProjectLocWRTIndia(String projectLocWRTIndia) {
		this.projectLocWRTIndia = projectLocWRTIndia;
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

	public String getExpiredInd() {
		return expiredInd;
	}

	public void setExpiredInd(String expiredInd) {
		this.expiredInd = expiredInd;
	}
	
}
