package com.app.aims.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "USER", schema = "public")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class UserDetail {

	@Id
	@Column(name = "USERID")
	@NotNull
	private Integer userID;

	@Column(name = "PASSWORD")
	@NotNull
	private String pwd;

	@Column(name = "CHANGED_DATE")
	private Date changedDate;

	public UserDetail(int userID, String pwd, Date changedDate) {
		super();
		this.userID = userID;
		this.pwd = pwd;
		this.changedDate = changedDate;

	}

	public UserDetail() {
		super();
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
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

	@Override
	public String toString() {
		return "UserDetail [userID=" + userID + ", pwd=" + pwd + ", changedDate=" + changedDate + "]";
	}

}