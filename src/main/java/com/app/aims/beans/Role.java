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
@Table(name = "ROLE", schema = "AIMS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ROLEID")})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Role {

	@Id
	@Column(name = "ROLEID" , unique = true)
	@NotNull
	private Integer roleID;

	@Column(name = "ROLENAME")
	@NotNull
	private String roleName;

	@Column(name = "CHANGED_DATE")
	private Date changedDate;
	
	
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name="ROLEID")
    private Set<UserRole> userRoles;

	public Role(Integer roleID, String roleName, Date changedDate) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
		this.changedDate = changedDate;

	}

	public Role() {
		super();
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		return "Role [roleID=" + roleID + ", roleName=" + roleName + ", changedDate=" + changedDate + "]";
	}

}