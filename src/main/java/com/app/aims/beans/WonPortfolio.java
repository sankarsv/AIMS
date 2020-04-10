package com.app.aims.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@Entity
//@Table(name="won_portfolio_mapping",schema="aims")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WonPortfolio {

//	@Column(name="project_id" )
//	@NotNull
    private Integer projectId;
	
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public Integer getWonId() {
		return wonId;
	}

	public void setWonId(Integer wonId) {
		this.wonId = wonId;
	}

//	@Column(name="portfolio_id" )
//	@NotNull
    private Integer portfolioId;
	
//	@Column(name="won_id" )
//	@NotNull
//	@Id
    private Integer wonId;
	
}
