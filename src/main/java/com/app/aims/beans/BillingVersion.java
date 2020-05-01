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
	private String periodMonth;
	
	@Column(name="year")	
	private String year;
		public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@Column(name="version")	
	private String version;
	
	@Column(name="freezeind")	
	private String freezeInd;
	

	public String getBrmId() {
		return brmId;
	}

	public void setBrmId(String brmId) {
		this.brmId = brmId;
	}

	public String getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(String periodMonth) {
		this.periodMonth = periodMonth;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFreezeInd() {
		return freezeInd;
	}

	public void setFreezeInd(String freezeInd) {
		this.freezeInd = freezeInd;
	}


}
