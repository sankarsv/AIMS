package com.app.aims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.BatchAuditDetails;
import com.app.aims.dao.BatchDao;
import com.app.aims.service.BatchDetailsService;
import com.app.aims.vo.FileStatus;


@Service
@Transactional
public  class BatchDetailsServiceImpl implements BatchDetailsService {

    
	@Autowired
	BatchDao batchDao;

	@Override
	public List<FileStatus> findAllFileProcessDetails() {
		FileStatus fileStatus = null;
		List<FileStatus> fileStatusList = new ArrayList<FileStatus>();
		try {
			List<BatchAuditDetails> batchAuditDetails = batchDao.getAllFileProcessDetails();
			if(batchAuditDetails != null && batchAuditDetails.size() > 0) {
				for (BatchAuditDetails batchAuditDetail : batchAuditDetails) {
					fileStatus = new FileStatus();
					fileStatus.setBatchStatus(batchAuditDetail.getBatchStatus());
					//fileStatus.setChangedDate(batchAuditDetail.getChangedDate());
					fileStatus.setFileType(batchAuditDetail.getFileType());
					//fileStatus.setFileId(batchAuditDetail.getFileId());
					fileStatus.setLoadDate(batchAuditDetail.getLoadDate());
					fileStatusList.add(fileStatus);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileStatusList;
	}


	
	
}
