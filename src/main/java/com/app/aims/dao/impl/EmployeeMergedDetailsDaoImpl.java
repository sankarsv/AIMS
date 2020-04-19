package com.app.aims.dao.impl;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.Employee;
import com.app.aims.beans.EmployeeMergedDetails;
import com.app.aims.beans.HCDetails;
import com.app.aims.dao.EmployeeMergedDetailsDao;

@Repository
public class EmployeeMergedDetailsDaoImpl implements EmployeeMergedDetailsDao {

	 @Autowired
	    private SessionFactory sessionFactory;
	
	@Override
	public List<HCDetails> findByVersionNo(int versionNo) {
		 Session session = sessionFactory.getCurrentSession();
	        Criteria cr = session.createCriteria(HCDetails.class);
	        cr.add(Restrictions.eq("versionNo", new Integer(versionNo)));
	        return cr.list();
	}

	@Override
	public void addEmployeeMergedDetails(List<EmployeeMergedDetails> employeeMergedDetails) {
		// TODO Auto-generated method stub
		 Session session = sessionFactory.getCurrentSession();
		 session.save(employeeMergedDetails);
	}

//	@Override
//	public Integer getMaxBaseLineNumber() {
//		 Session session = sessionFactory.getCurrentSession();
//		DetachedCriteria maxId = DetachedCriteria.forClass(EmployeeMergedDetails.class)
//			    .setProjection( Projections.max("baseLine") );
//		 Criteria cr = session.createCriteria(EmployeeMergedDetails.class)
//			    .add( Restrictions.eq("baseLine", maxId) );
//		 cr.setMaxResults(1);
//	 return	(Integer) cr.uniqueResult();
//	}
	
	
	
	
	

}
