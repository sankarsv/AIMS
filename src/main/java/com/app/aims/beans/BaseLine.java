package com.app.aims.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name="base_line",schema="public")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseLine {

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the baseLineNo
	 */
	public Integer getBaseLineNo() {
		return baseLineNo;
	}

	/**
	 * @param baseLineNo the baseLineNo to set
	 */
	public void setBaseLineNo(Integer baseLineNo) {
		this.baseLineNo = baseLineNo;
	}

//	@Id 
//	@Column(name="id")
	private int employeeId;
	
	public BaseLine(int employeeId, Date date, Integer baseLineNo) {
		super();
		this.employeeId = employeeId;
		this.date = date;
		this.baseLineNo = baseLineNo;
	}

	
	
//	@Column(name="date")
    private Date date;

//	@Column(name="base_line_no")
    private Integer baseLineNo;
	
	public BaseLine() {}
}
