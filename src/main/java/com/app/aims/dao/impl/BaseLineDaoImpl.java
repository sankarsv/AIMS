package com.app.aims.dao.impl;

import java.util.Date;
import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.BaseLine;
import com.app.aims.beans.EmployeeAllocation;
import com.app.aims.beans.HCDetails;
import com.app.aims.beans.Portfolio;
import com.app.aims.beans.VersionInfo;
import com.app.aims.dao.BaseLineDao;

@Repository
@Transactional
public class BaseLineDaoImpl implements BaseLineDao {

    @Autowired
    private SessionFactory sessionFactory;
    
	@Override
	public  BaseLine getMaxBaseLineDetails(Date date) {
		
		List<BaseLine> baseLineList = getMaxBaseLineDetailsList();
		
		 Integer baseLIneNo = (Integer) 0;
		 
        if(baseLineList == null || baseLineList.size() < 1) {
        	baseLIneNo = Integer.parseInt("10001");
        }else {
        	BaseLine maxBaseLineDetail = baseLineList.get(0);
        	baseLIneNo = maxBaseLineDetail.getBaseLineNo() + 1;
        }

//        String insertQuery = "INSERT INTO BaseLine(date, baseLineNo) VALUES (:date, :baseLineNo)"; 
//        
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery(insertQuery);
//        query.setParameter("date", date);
//        query.setParameter("baseLineNo", baseLIneNo);
//        
//     
//        
//    	//user.setEmpName(val.getEmpName());
//        int status = query.executeUpdate();
//       
//        if(status == 1) {
//        	List<BaseLine> newBaseLineList = getMaxBaseLineDetailsList();
//        	
//        	if(newBaseLineList == null || newBaseLineList.size() < 1) {
//        		throw new InternalError("Something went wrong please try again later.");
//        	}
//        	
//        	return newBaseLineList.get(0);
//        }
//        
//        throw new InternalError("Something went wrong please try again later.");
        
        
        Session session = sessionFactory.getCurrentSession();
        BaseLine baseLine = new BaseLine();
        baseLine.setBaseLineNo(baseLIneNo);
        baseLine.setDate(date);
        session.save(baseLine);
        
     	List<BaseLine> newBaseLineList = getMaxBaseLineDetailsList();    	
    	if(newBaseLineList == null || newBaseLineList.size() < 1) {
    		throw new InternalError("Something went wrong please try again later.");
    	}
    	
    	return newBaseLineList.get(0);
	}
	
	public List<BaseLine>  getMaxBaseLineDetailsList() {
		// TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();
        String queryStr1 = "from BaseLine where baseLineNo = (select max(baseLineNo) from BaseLine )";
        Query query = session.createQuery(queryStr1);
       return  query.list();       
	}

	@Override
	public List<Portfolio> getPortfolio() {
		Session session = sessionFactory.getCurrentSession();
		String queryStr1 = "FROM Portfolio";
		Query query = session.createQuery(queryStr1);
		return query.list();
	}

	@Override
	public int getMaxBillingVersion() {
		Session session = sessionFactory.getCurrentSession();
        String queryStr1 = "select max(version) from BillingVersion";
        Query query = session.createQuery(queryStr1);
        query.setMaxResults(1);
       return (Integer) query.uniqueResult(); 
	}

	@Override
	public List<HCDetails> getHCDetails(Integer version) {
		Session session = sessionFactory.getCurrentSession();
        String queryStr1 = "from HCDetails where versionNo = :version";
        Query query = session.createQuery(queryStr1);
        query.setParameter("version", version.intValue());
        return query.list();
	}
	
}
