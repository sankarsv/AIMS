package com.app.aims.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="portfolio",schema="aims")
public class Portfolio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="portfolio_id" )
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer portfolioId;
	
	@Column(name="portfolio_name" )
    private String portfolioName;

	@Column(name="portfolio_type" )
    private String portfolioType;
	
	@Column(name="location")
	private String location;
	
	@Column(name="sto")
	private String sto;
	
	@Column(name="lto")
	private String lto;
	
	@Column(name="brm_empid" )
    private Integer brmEmpId;
	
	@Column(name="brmname" )
    private String brmname;
	
	@Column(name="brmnamehc" )
    private String brmnamehc;
	
	@Column(name="brmnamebilling" )
    private String brmnamebilling;
	
	@Column(name="em_empno" )
    private Integer em_empno;
	
	@Column(name="em_name" )
    private String em_name;
	
	@Column(name="emnamehc" )
    private String emnamehc;
	
	@Column(name="emnamebilling" )
    private String emnamebilling;
	
	@Column(name="dm_name" )
    private String dm_name;
	
	@Column(name="dmnamehc" )
    private String dmnamehc;
	
	@Column(name="dmnamebilling" )
    private String dmnamebilling;
	
	@Column(name="dm_emp_id" )
    private Integer dm_emp_id;
	
	@Column(name="billing_lead_emp_id" )
    private Integer billing_lead_emp_id;
	
	@Column(name="billing_lead_name" )
    private String billing_lead_name;
	
	@Column(name="description" )
    private String description;
	
	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSto() {
		return sto;
	}

	public void setSto(String sto) {
		this.sto = sto;
	}

	public String getLto() {
		return lto;
	}

	public void setLto(String lto) {
		this.lto = lto;
	}

	public String getBrmname() {
		return brmname;
	}

	public void setBrmname(String brmname) {
		this.brmname = brmname;
	}

	public String getBrmnamehc() {
		return brmnamehc;
	}

	public void setBrmnamehc(String brmnamehc) {
		this.brmnamehc = brmnamehc;
	}

	public String getBrmnamebilling() {
		return brmnamebilling;
	}

	public void setBrmnamebilling(String brmnamebilling) {
		this.brmnamebilling = brmnamebilling;
	}

	public Integer getEm_empno() {
		return em_empno;
	}

	public void setEm_empno(Integer em_empno) {
		this.em_empno = em_empno;
	}

	public String getEm_name() {
		return em_name;
	}

	public void setEm_name(String em_name) {
		this.em_name = em_name;
	}

	public String getEmnamehc() {
		return emnamehc;
	}

	public void setEmnamehc(String emnamehc) {
		this.emnamehc = emnamehc;
	}

	public String getEmnamebilling() {
		return emnamebilling;
	}

	public void setEmnamebilling(String emnamebilling) {
		this.emnamebilling = emnamebilling;
	}

	public String getDm_name() {
		return dm_name;
	}

	public void setDm_name(String dm_name) {
		this.dm_name = dm_name;
	}

	public String getDmnamehc() {
		return dmnamehc;
	}

	public void setDmnamehc(String dmnamehc) {
		this.dmnamehc = dmnamehc;
	}

	public String getDmnamebilling() {
		return dmnamebilling;
	}

	public void setDmnamebilling(String dmnamebilling) {
		this.dmnamebilling = dmnamebilling;
	}

	public Integer getDm_emp_id() {
		return dm_emp_id;
	}

	public void setDm_emp_id(Integer dm_emp_id) {
		this.dm_emp_id = dm_emp_id;
	}

	public Integer getBilling_lead_emp_id() {
		return billing_lead_emp_id;
	}

	public void setBilling_lead_emp_id(Integer billing_lead_emp_id) {
		this.billing_lead_emp_id = billing_lead_emp_id;
	}

	public String getBilling_lead_name() {
		return billing_lead_name;
	}

	public void setBilling_lead_name(String billing_lead_name) {
		this.billing_lead_name = billing_lead_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
