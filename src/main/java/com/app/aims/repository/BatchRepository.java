package com.app.aims.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.BatchAuditDetails;

@Transactional
@Repository
public interface BatchRepository extends JpaRepository<BatchAuditDetails, Integer> {
	
	//public List<BatchAuditDetails> findAll();
	
	public BatchAuditDetails findByBatchStatus(String batchStatus);

	public BatchAuditDetails findByFileId(long fileId);
 
}
