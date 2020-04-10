package com.app.aims.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name="portfolio",schema="aims")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Portfolio {
	
//	@Column(name="id" )
//	@NotNull
//	@Id
    private Integer portfolioId;
	
//	@Column(name="portfolio_name" )
//	@NotNull
    private String portfolioName;

//	@Column(name="portfolio_type" )
//	@NotNull
    private String portfolioType;
	
//	@Column(name="brm_empid" )
//	@NotNull
    private Integer brmEmpId;
	
//	@Column(name="onsite_lead_empid" )
//	@NotNull
    private Integer onsiteLeadEmpId;
	
//	@Column(name="dm_emp_id" )
//	@NotNull
    private Integer dmEmpId;
	
//	@Column(name="offshore_lead_emp_id" )
//	@NotNull
    private Integer offshoreLeadEmpId;
	
//	@Column(name="billing_emp_id" )
//	@NotNull
    private Integer billingEmpId;
	
//	@Column(name="description" )
//	@NotNull
    private Integer description;

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

	public Integer getDescription() {
		return description;
	}

	public void setDescription(Integer description) {
		this.description = description;
	}

	// 	List<BaseLine> newBaseLineList = getMaxBaseLineDetailsList();
	//        	
	//        	if(newBaseLineList == null || newBaseLineList.size() < 1) {
	//        		throw new InternalError("Something went wrong please try again later.");
	//        	}
	//        	
	//        	return newBaseLineList.get(0);
	
	
}
