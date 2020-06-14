package com.app.aims.dao;

import java.util.List;

import com.app.aims.beans.BatchAuditDetails;

public interface BatchDao {

	public void saveFileProcessDetails(BatchAuditDetails batchAuditDetails);
	public List<BatchAuditDetails> getAllFileProcessDetails() throws Exception;
	public void updateStatusIfAlreadyExistBR();
	public void updateStatusIfAlreadyExistHC();
	public void updateStatusIfAlreadyExistCl();
}
