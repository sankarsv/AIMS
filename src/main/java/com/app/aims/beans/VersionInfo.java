package com.app.aims.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="HCVERSION",schema="aims")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class VersionInfo {
	
	
	@Id 
	@Column(name="VERSION_NO")
	private int versionNo;
	
	
	
	@Column(name="LOAD_DATE")
	private Date loadDate;

	@Column(name="CURRENT_IND")
	private String current_ind;

	public VersionInfo() {
		super();
	}


	public int getVersionNo() {
		return versionNo;
	}


	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}


	public Date getLoadDate() {
		return loadDate;
	}


	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}


	public String getCurrent_ind() {
		return current_ind;
	}


	public void setCurrent_ind(String current_ind) {
		this.current_ind = current_ind;
	}
    
    
}