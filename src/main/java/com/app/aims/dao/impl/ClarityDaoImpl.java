package com.app.aims.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.Clarity;
import com.app.aims.dao.ClarityDao;
import com.app.aims.repository.ClarityDataRepository;

@Transactional
@Repository
public class ClarityDaoImpl implements ClarityDao {


	    @Autowired
	    private ClarityDataRepository clarityDataRepository;
	    
		@Override
		public List<Clarity>  retriveClarityDetailsByBillingVersionID(Integer versionID){
		    // TODO Auto-generated method stub
	        List<Clarity> clarityDetails = clarityDataRepository.findByBillingVersionId(versionID);
	        return clarityDetails;
		}
		
		


}
