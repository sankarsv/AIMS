package com.app.aims.beans;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="allocation",schema="aims")
public class EmployeeAllocation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//
	@Id 
	@Column(name="allocation_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "employee_id",insertable = false, updatable = false)
//	@Fetch(FetchMode.JOIN)
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id",referencedColumnName = "employee_id")
    private Employee empId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="project_id")	
    private Project project;
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name="won_id",insertable = false, updatable = false)
	 * 
	 * @Fetch(FetchMode.JOIN) private WonPortfolio won;
	 */
	@Column(name="won_id"  )
	private Integer wonId;
	
	
	@Column(name="project_change_date"  )
	@NotNull
    private LocalDate projectChangeDate;
		
	
	@Column(name="portfolio_id")
    private int portfolioId;
	
	@Column(name="start_date"  )
	@NotNull
    private LocalDate startDate;
	
	@Column(name="end_date"  )
	@NotNull
    private LocalDate endDate;
	
	@Column(name="travel_type"  )
	@NotNull
    private String travelType;
//	
//	@Column(name="employee_travel_country")
//	@NotNull
//    private String employeeTravelCountry;
//	
	@Column(name="percentage_allocation")
	private double percAllocation = 0;
	
	
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

	public Integer getWonId() {
		return wonId;
	}

	public void setWonId(Integer wonId) {
		this.wonId = wonId;
	}

	public LocalDate getProjectChangeDate() {
		return projectChangeDate;
	}

	public void setProjectChangeDate(LocalDate projectChangeDate) {
		this.projectChangeDate = projectChangeDate;
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

	public double getPercAllocation() {
		return percAllocation;
	}

	public void setPercAllocation(double percAllocation) {
		this.percAllocation = percAllocation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
	/*
	 * public WonPortfolio getWon() { return won; }
	 * 
	 * public void setWonId(WonPortfolio won) { this.won = won; }
	 */
	 

	
	
}
