package com.app.aims.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UserDetailResponse {

	private Integer Id;

	private Integer userId;

	private String status;

	public UserDetailResponse(Integer Id, Integer userId, String status) {
		super();
		this.Id = Id;
		this.userId = userId;
		this.status = status;

	}

	public UserDetailResponse() {
		super();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserDetailResponse [Id=" + Id + ", userId=" + userId + ", status=" + status + "]";
	}

}