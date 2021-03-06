package com.app.aims.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "USER", schema = "AIMS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "USERID")})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class UserDetail {

	@Id
	@Column(name = "USERID" , unique = true)
	@NotNull
	private Integer userID;

	@Column(name = "PASSWORD")
	@NotNull
	private String pwd;

	@Column(name = "CHANGED_DATE")
	private Date changedDate;
	

	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="USERID")
    private Set<UserRole> userRoles;
	
	public UserDetail(Integer userID, String pwd, Date changedDate) {
		super();
		this.userID = userID;
		this.pwd = pwd;
		this.changedDate = changedDate;

	}

	public UserDetail() {
		super();
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getChangedDate() {
		return changedDate;
	}

	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "UserDetail [userID=" + userID + ", pwd=" + pwd + ", changedDate=" + changedDate + "]";
	}

}