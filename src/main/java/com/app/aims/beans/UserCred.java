package com.app.aims.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="USER",schema="AIMS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserCred {

	@Id 
	@Column(name="USERID")
	private String userId;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="CHANGED_DATE")
    private Date date;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
