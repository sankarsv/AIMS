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
	    
	    @Autowired
	    private BillingVersionRespository billingVersionRepository;
	    
	    @Override
	    public void addBillingDiscrepancyDetails(BillingDiscrepancy billingDiscrDetails) {
	        // TODO Auto-generated method stub
	        Session session = sessionFactory.getCurrentSession();
	        session.save(billingDiscrDetails);
	    }
	    
		@Override
		public List<BillingDiscrepancy> retriveBillingDescrepancyByBrmAndVersion(String brm, Integer version){
		    // TODO Auto-generated method stub
	        List<BillingDiscrepancy> billingDiscrepancyDetails = billingDiscrepancyDataRepository.findBillingDescrepancyByBillingVersionId(brm,version);
	        return billingDiscrepancyDetails;
		}
		
		
		@Override
		public void addBillingDiscrepancyDetailsList(List<BillingDiscrepancy> billingDiscrDetailsList){
		    // TODO Auto-generated method stub
			
			System.out.println("Enter in to addBillingDiscrepancyDetailsList in  BillingdicrepancyDaoImpl-->"+billingDiscrDetailsList);
			if(billingDiscrDetailsList!=null) {
	        Session session = sessionFactory.getCurrentSession();
	        /*String hql = String.format("delete from %s","BillingDiscrepancy");
	        Query query = session.createQuery(hql);
	        query.executeUpdate();*/
	        billingDiscrepancyDataRepository.deleteByVersion(billingDiscrDetailsList.get(0).getVersion());
	        for(BillingDiscrepancy billingDiscrDetails : billingDiscrDetailsList) {
	        	
	        	session.saveOrUpdate(billingDiscrDetails);
	        }
	        System.out.println("Exit from addBillingDiscrepancyDetailsList in BillingdicrepancyDaoImpl");
		}
	        
	        
		}
		
		@Override
		public Integer getMaxDescripancyVersion() {
			Session session = sessionFactory.getCurrentSession();
	        String queryStr1 = "select max(version) from BillingDiscrepancy";
	        Query query = session.createQuery(queryStr1);
	        query.setMaxResults(1);
	       return (Integer) query.uniqueResult(); 
		}

		@Override
		public int updateDiscrepancyVersionInBillingVersion(BillingDetailsReq billingDetailReq,String discrepancyVersion) {
//			Session session = sessionFactory.getCurrentSession();
//			String query = "UPDATE BillingVersion bv SET bv.discrerpancyVersion=:discrepancyVersion WHERE bv.periodMonth=:month and bv.year=:year";
//			int result = session.createQuery(query)
//				.setString("month", billingDetailReq.getMonth())
//				.setString("year", billingDetailReq.getYear())
//				.setString(discrepancyVersion, discrepancyVersion)
//				.executeUpdate();
//			session.close();
			Integer year = null;
			if(StringUtils.hasText(billingDetailReq.getYear())) {
				year = Integer.parseInt(billingDetailReq.getYear());
			}
			int result = billingVersionRepository.updateDiscrepancyVersion(discrepancyVersion,billingDetailReq.getMonth(),year);
			
			return result;
			
		}
}
