package com.app.aims.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingDiscrepancy;
import com.app.aims.beans.BillingVersion;
import com.app.aims.beans.Clarity;
import com.app.aims.beans.Employee;
import com.app.aims.dao.BillingDao;
import com.app.aims.dao.BillingDiscrepancyDao;
import com.app.aims.dao.ClarityDao;
import com.app.aims.repository.BatchRepository;
import com.app.aims.repository.BillingDataRepository;
import com.app.aims.repository.BillingDiscrepancyDataRepository;
import com.app.aims.repository.BillingVersionRespository;
import com.app.aims.repository.ClarityDataRepository;
import com.app.aims.repository.FileDataRepository;
import com.app.aims.vo.BillingDetailUpdateReq;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;

@Transactional
@Repository
public class BillingDiscrepancyDaoImpl implements BillingDiscrepancyDao {
    
   


	    @Autowired
	    private SessionFactory sessionFactory;
	    
	    @Autowired
	    private BillingDiscrepancyDataRepository billingDiscrepancyDataRepository;
	    
	    @Override
	    public void addBillingDiscrepancyDetails(BillingDiscrepancy billingDiscrDetails) {
	        // TODO Auto-generated method stub
	        Session session = sessionFactory.getCurrentSession();
	        session.save(billingDiscrDetails);
	    }
	    
		@Override
		public List<BillingDiscrepancy> retriveBillingDescrepancyByBillingVersionID(String brm){
		    // TODO Auto-generated method stub
	        Session session = sessionFactory.getCurrentSession();
	        List<BillingDiscrepancy> billingDiscrepancyDetails = billingDiscrepancyDataRepository.findBillingDescrepancyVyBillingVersionId(brm);
	        return billingDiscrepancyDetails;
		}
		
		
		@Override
		public void addBillingDiscrepancyDetailsList(List<BillingDiscrepancy> billingDiscrDetailsList){
		    // TODO Auto-generated method stub
			
			System.out.println("Enter in to addBillingDiscrepancyDetailsList in  BillingdicrepancyDaoImpl-->"+billingDiscrDetailsList);
			if(billingDiscrDetailsList!=null) {
	        Session session = sessionFactory.getCurrentSession();
	        String hql = String.format("delete from %s","BillingDiscrepancy");
	        Query query = session.createQuery(hql);
	        query.executeUpdate();
	        for(BillingDiscrepancy billingDiscrDetails : billingDiscrDetailsList) {
	        	
	        	session.saveOrUpdate(billingDiscrDetails);
	        }
	        System.out.println("Exit from addBillingDiscrepancyDetailsList in BillingdicrepancyDaoImpl");
		}
	        
	        
		}
		
		


}
