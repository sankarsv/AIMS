package com.app.aims.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.BillingFileData;
import com.app.aims.beans.ClarityFileData;
import com.app.aims.beans.Employee;
import com.app.aims.beans.FileData;
import com.app.aims.beans.Portfolio;
import com.app.aims.beans.VersionInfo;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.repository.AllocationRepository;
import com.app.aims.repository.SearchRepository;

@Transactional
@Repository
public class EmployeeDaoImpl implements EmployeeDao {


	    @Autowired
	    private SessionFactory sessionFactory;
	    
	    @Autowired
		private SearchRepository searchRepository;
	    
	    @Autowired
		private AllocationRepository allocationRepository;
	    
	    
	    @Override
	    public void addUser(Employee user) {
	        // TODO Auto-generated method stub
	        Session session = sessionFactory.getCurrentSession();
	        session.save(user);
	    }

	    @Override
	    public List<Employee> getUser() {
	        // TODO Auto-generated method stub
	        Session session = sessionFactory.getCurrentSession();
	        @SuppressWarnings("unchecked")
	        List<Employee> list= session.createCriteria(Employee.class).list();
	        return list;
	    }

	    

	    @Override
	    public boolean uploadFile(FileData  fileData) {
	        // TODO Auto-generated method stub
	        try {
	        	Session session = sessionFactory.getCurrentSession();
	        	truncateTable(fileData.getClass().getSimpleName(),session);
		        session.save(fileData);
		        
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	    	return true;
	        
	    }
	    
	    public int truncateTable(String myTable, Session session){
	        String hql = String.format("delete from %s",myTable);
	        Query query = session.createQuery(hql);
	        return query.executeUpdate();
	    }
//
//	    @Override
//	    public boolean update(EditRequest editRequest) {
//	        // TODO Auto-generated method stub
//	        Session session = sessionFactory.getCurrentSession();
//	        //Transaction transaction = session.beginTransaction();
//	        Portfolio portfolioId = null;
//	        Integer empNo = 0;
//	        if (editRequest.getEmEmpNo() != null)
//	        	empNo = new Integer(editRequest.getEmpNo());
//	        String queryStr1 = "FROM Employee where employeeId = :empId";
//	        
//	        Query query = session.createQuery(queryStr1);
//	        query.setParameter("empId", empNo);
//	        List<Employee> employee =  query.list();
//	        
//	        
//	        if (employee != null && !employee.isEmpty() && employee.get(0) != null)
//	        	portfolioId = ((Employee)employee.get(0)).getEmployeeAllocations().get(0).getPortfolioId();
//	        
//	        String queryStr = "UPDATE Portfolio set brmEmpId = :brmEmpId where portfolioId = :portfolioId"; 
//	        
//	        Query query1 = session.createQuery(queryStr);
//	        System.out.println("Before Updating the Table");
//	        query1.setParameter("brmEmpId", new Integer(editRequest.getBrmEmpNo()));
//	        query1.setParameter("portfolioId", portfolioId.getPortfolioId());
//	    	//user.setEmpName(val.getEmpName());
//	        int status = query1.executeUpdate();
//	        if (status == 1)
//	        	return true;
//	        return false;
//	    }

	    @Override
	    public boolean delete(String empId) {
	       
	        // TODO Auto-generated method stub
	        Session session = sessionFactory.getCurrentSession();
	        //Transaction transaction = session.beginTransaction();
	        Portfolio portfolioId = null;
	        Integer empNo = 0;
	        if (empId != null)
	        	empNo = new Integer(empId);
	        String queryStr1 = "UPDATE Employee set status = 'C' where employeeId = :empId";
	        
	        Query query1 = session.createQuery(queryStr1);
	        query1.setParameter("empId", empNo);
	        System.out.println("Before Updating the Table");
	    	//user.setEmpName(val.getEmpName());
	        int status = query1.executeUpdate();
	        if (status == 1)
	        {
	        	System.out.println("Delete is success");
	        	return true;
	        }
	        return false;

	    }

	    @Override
		public List<Employee> getEmployeeDetails() {
			
			// TODO Auto-generated method stub
			Session session = sessionFactory.getCurrentSession();
			String queryStr1 = "FROM Employee";
			Query query = session.createQuery(queryStr1);
			//query.setParameter("createdAt", date);
			return query.list();    
		}

		@Override
		public Employee findById(int id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<VersionInfo> getVersionInfo() {
			// TODO Auto-generated method stub
			Session session = sessionFactory.getCurrentSession();
	        @SuppressWarnings("unchecked")
	        List<VersionInfo> list= session.createCriteria(VersionInfo.class).list();
	        return list;
		}

		@Override
		public boolean uploadBrFile(BillingFileData billingFileData) {
	        // TODO Auto-generated method stub
	        try {
	        	Session session = sessionFactory.getCurrentSession();
	        	truncateTable(billingFileData.getClass().getSimpleName(),session);
		        session.save(billingFileData);
		        
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	    	return true;
	        
	    }

		@Override
		public void deleteDetails(List<String> empIds) {
			allocationRepository.deleteEmployees(empIds);
			searchRepository.deleteEmployees(empIds);
		}

		@Override
		public boolean uploadClFile(ClarityFileData clarityFileData) {
	        // TODO Auto-generated method stub
	        try {
	        	Session session = sessionFactory.getCurrentSession();
	        	truncateTable(clarityFileData.getClass().getSimpleName(),session);
		        session.save(clarityFileData);
		        
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	    	return true;
	        
	    }
	    
		
}
