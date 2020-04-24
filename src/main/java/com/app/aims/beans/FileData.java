package com.app.aims.beans;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "HC_INTERMEDIATE", schema = "aims")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class FileData {

	@Id
	@Column(name = "UPLOAD_TIME" , unique = true)
	@NotNull
	private Date uploadTime;

	
	@Column(name = "FILEDATA")
	@NotNull
	private byte[] fileData;

	public FileData() {
		super();
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	



}