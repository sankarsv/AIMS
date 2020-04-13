package com.app.aims.beans;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.app.aims.service.ValidPassword;


public class UserDetailRequest {

	@Min(value = 367273, message = "userId should not be less than 6")
	@Max(value = 367273740, message = "userId should not be greater than 10")
	private Integer userId;

	// @NotEmpty(message = "Password may not be empty")
	// @Size(min = 8, max = 10, message = "Password must be between 02 and 08
	// characters long")
	
	@ValidPassword
	private String pwd;

	private Date changedDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
		return "UserDetailRequest [userId=" + userId + ", pwd=" + pwd + ", changedDate=" + changedDate + "]";
	}

}