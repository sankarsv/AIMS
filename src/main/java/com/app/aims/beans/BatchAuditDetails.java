package com.app.aims.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="BATCH_AUDIT",schema="aims")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class BatchAuditDetails {
	
	@Id
	@Column(name = "FILE_ID" , unique = true)
	@NotNull
	private long fileId;
	
	@NotNull
	@Column(name="ADDED_DATE")
	private Date loadDate;

	@NotNull
	@Column(name="FILE_TYPE")
	private String fileType;
	
	@Column(name="CHANGED_DATE")
	private Date changedDate;
	
	@NotNull
	@Column(name="BATCH_STATUS")
	private String batchStatus;

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getChangedDate() {
		return changedDate;
	}

	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}

	public String getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	
}
