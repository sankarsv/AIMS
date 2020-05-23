package com.app.aims.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="billingversion",schema="aims", uniqueConstraints=@UniqueConstraint(columnNames= {"brm", "periodmonth", "year"}))
public class BillingVersion  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7407183647631676571L;


	@Column(name="brm")	
	private String brmId;
	
	
	@Column(name="periodmonth")	
	private String month;
	
	@Column(name="year")	
	private Integer year;
	
	@Id
	@Column(name="version")	
	private Integer version;
	
	@Column(name="freezeind")	
	private String freezeInd;
	

	public String getBrmId() {
		return brmId;
	}

	public void setBrmId(String brmId) {
		this.brmId = brmId;
	}


	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}


	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFreezeInd() {
		return freezeInd;
	}

	public void setFreezeInd(String freezeInd) {
		this.freezeInd = freezeInd;
	}


}
