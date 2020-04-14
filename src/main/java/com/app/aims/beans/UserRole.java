package com.app.aims.beans;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "USERROLE", schema = "AIMS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8850214804833264874L;
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ROLEID", nullable=false)
	 private Role role;

	@Id
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="USERID", nullable=false)
	 private UserDetail userDetail;
	
	@Column(name = "CHANGED_DATE")
	private Date changedDate;
	
	public Date getChangedDate() {
		return changedDate;
	}

	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	
	
	@Override
	public String toString() {
		return "UserRole [roleName=" + role.getRoleName() + ", userid=" + userDetail.getUserID() + ", changedDate=" + changedDate + "]";
	}

}