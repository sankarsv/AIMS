package com.app.aims.beans;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name="employee_allocation",schema="public")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeAllocation {
	
//	@Id 
//	@Column(name="id")
	private int id;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "employee_id")
    private Employee empId;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="project_id"  )
    private Project project;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="won_id")
    private WonPortfolio won;
	
//	@Column(name="project_change_date"  )
//	@NotNull
    private LocalDate projectChangeDate;
		
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="portfolio_id")
    private Portfolio portfolioId;
	
//	@Column(name="start_date"  )
//	@NotNull
    private LocalDate startDate;
	
//	@Column(name="end_date"  )
//	@NotNull
    private LocalDate endDate;
	
//	@Column(name="travel_type"  )
//	@NotNull
    private String travelType;
	
//	@Column(name="employee_travel_country")
//	@NotNull
    private String employeeTravelCountry;
	
//	@Column(name="team_role")
//	@NotNull
    private String teamRole;
	
//	@Column(name="employee_active_client_id")
//	@NotNull
    private String employeeActiveClientInd;
	
//	@Column(name="depute_branch")
//	@NotNull
    private String deputeBranch;
	
//	@Column(name="depute_dc")
//	@NotNull
    private String deputeDc;
	
//	@Column(name="employee_location_id")
//	@NotNull
    private String employeeLocationId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}

	public Project getProject() {
		return project;
	}

	public void setProjectId(Project project) {
		this.project = project;
	}

	public WonPortfolio getWon() {
		return won;
	}

	public void setWonId(WonPortfolio won) {
		this.won = won;
	}

	public LocalDate getProjectChangeDate() {
		return projectChangeDate;
	}

	public void setProjectChangeDate(LocalDate projectChangeDate) {
		this.projectChangeDate = projectChangeDate;
	}

	public Portfolio getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Portfolio portfolioId) {
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

	public String getEmployeeActiveClientInd() {
		return employeeActiveClientInd;
	}

	public void setEmployeeActiveClientInd(String employeeActiveClientInd) {
		this.employeeActiveClientInd = employeeActiveClientInd;
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
	
	
}
