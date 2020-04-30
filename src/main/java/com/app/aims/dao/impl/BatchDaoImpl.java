package com.app.aims.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.BatchAuditDetails;
import com.app.aims.beans.BillingFileData;
import com.app.aims.beans.FileData;
import com.app.aims.dao.BatchDao;
import com.app.aims.repository.BatchRepository;
import com.app.aims.repository.BillingFileDataRepository;
import com.app.aims.repository.FileDataRepository;

@Transactional
@Repository
public class BatchDaoImpl implements BatchDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	BatchRepository batchRepository;
	
	@Autowired
	FileDataRepository fdRepository;
	
	@Autowired
	BillingFileDataRepository bdRepository;
	
	@Override
	public void saveFileProcessDetails(BatchAuditDetails batchAuditDetails) {
		Session session = sessionFactory.getCurrentSession();
		session.save(batchAuditDetails);

	}

	@Override
	public List<BatchAuditDetails> getAllFileProcessDetails() throws Exception {
		return batchRepository.findAll();
	}

	@Override
	public void updateStatusIfAlreadyExistHC() {
		Session session = sessionFactory.getCurrentSession();
		List<FileData> fileDataList = fdRepository.findAll();
		if(fileDataList != null && fileDataList.size() > 0) {
			for (FileData fileData : fileDataList) {
				BatchAuditDetails batchAuditDetails = batchRepository.findByFileId(fileData.getFileId());
				if(batchAuditDetails != null && "U".equalsIgnoreCase(batchAuditDetails.getBatchStatus())) {
		    		batchAuditDetails.setBatchStatus("A");
		    		batchAuditDetails.setChangedDate(new Date());
		    		session.update(batchAuditDetails);
		    	}
			}
		}
	}

	public void _updateStatusIfAlreadyExistBR() {
		Session session = sessionFactory.getCurrentSession();
		List<BillingFileData> billingDataList = bdRepository.findAll();
		if(billingDataList != null && billingDataList.size() > 0) {
			for (BillingFileData billingData : billingDataList) {
				BatchAuditDetails batchAuditDetails = batchRepository.findByFileId(billingData.getFileId());
				if(batchAuditDetails != null && "U".equalsIgnoreCase(batchAuditDetails.getBatchStatus())) {
		    		batchAuditDetails.setBatchStatus("A");
		    		batchAuditDetails.setChangedDate(new Date());
		    		session.update(batchAuditDetails);
		    	}
			}
		}
	}
	
	@Override
	public void updateStatusIfAlreadyExistBR() {
		Session session = sessionFactory.getCurrentSession();
		BatchAuditDetails batchAuditDetails = new BatchAuditDetails();
		batchAuditDetails.setBatchStatus("U");
		batchAuditDetails.setFileType("BR");
		Example<BatchAuditDetails> batchAuditDetailsExample = Example.of(batchAuditDetails);
		BatchAuditDetails batchAuditDetailsRes = batchRepository.findOne(batchAuditDetailsExample).get();
		batchAuditDetailsRes.setBatchStatus("A");
		batchAuditDetailsRes.setChangedDate(new Date());
		session.update(batchAuditDetails);

	}
}
