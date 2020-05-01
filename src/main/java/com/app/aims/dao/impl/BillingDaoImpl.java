package com.app.aims.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BillingVersion;
import com.app.aims.dao.BillingDao;

@Transactional
@Repository
public class BillingDaoImpl implements BillingDao {


	    @Autowired
	    private SessionFactory sessionFactory;
	    

		@Override
		public List<BRMDetails> retrieveBRMInfo() {
		    // TODO Auto-generated method stub
	        Session session = sessionFactory.getCurrentSession();
	        @SuppressWarnings("unchecked")
	        Criteria crit = session.createCriteria(BillingVersion.class).setProjection(
	        	    Projections.distinct(Projections.projectionList()
	        	    .add(Projections.property("type"), "type") ))
	        	.setResultTransformer(Transformers.aliasToBean(BillingVersion.class)); 

	        List<BillingVersion> brmDetails = crit.list();
	        
	        return populateBRMInfo(brmDetails);
		}
		
		private List<BRMDetails> populateBRMInfo(List<BillingVersion> billingVersionList) {
			
			List<BRMDetails> brmDetails = new ArrayList<BRMDetails>();
			BRMDetails brmDetails2 = null;
			if (billingVersionList != null) {
				
				for (BillingVersion billingVersion : billingVersionList) {
					brmDetails2 = new BRMDetails();
					brmDetails2.setBrmId(billingVersion.getBrmId());
					brmDetails2.setBrmName("");
					brmDetails.add(brmDetails2);
				}
			}
			
			return brmDetails;
		}
	    
		
}
