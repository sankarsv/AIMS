package com.app.aims.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.EmployeeAllocation;
import com.app.aims.beans.Portfolio;
import com.app.aims.dao.EmployeeDao;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {


	    @Autowired
	    private SessionFactory sessionFactory;

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
	    public boolean update(EditRequest editRequest) {
	        // TODO Auto-generated method stub
	        Session session = sessionFactory.getCurrentSession();
	        //Transaction transaction = session.beginTransaction();
	        Portfolio portfolioId = null;
	        Integer empNo = 0;
	        if (editRequest.getEmEmpNo() != null)
	        	empNo = new Integer(editRequest.getEmpNo());
	        String queryStr1 = "FROM Employee where employeeId = :empId";
	        
	        Query query = session.createQuery(queryStr1);
	        query.setParameter("empId", empNo);
	        List<Employee> employee =  query.list();
	        
	        
	        if (employee != null && !employee.isEmpty() && employee.get(0) != null)
	        	portfolioId = ((Employee)employee.get(0)).getEmployeeAllocations().get(0).getPortfolioId();
	        
	        String queryStr = "UPDATE Portfolio set brmEmpId = :brmEmpId where portfolioId = :portfolioId"; 
	        
	        Query query1 = session.createQuery(queryStr);
	        System.out.println("Before Updating the Table");
	        query1.setParameter("brmEmpId", new Integer(editRequest.getBrmEmpNo()));
	        query1.setParameter("portfolioId", portfolioId.getPortfolioId());
	    	//user.setEmpName(val.getEmpName());
	        int status = query1.executeUpdate();
	        if (status == 1)
	        	return true;
	        return false;
	    }

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
		public List<Employee> getEmployeeDetailsByDate(Date date) {
			
			if(date == null) {
				return null;
			}
			
			// TODO Auto-generated method stub
			Session session = sessionFactory.getCurrentSession();
			String queryStr1 = "FROM Employee where createdAt = :createdAt";
			Query query = session.createQuery(queryStr1);
			query.setParameter("createdAt", date);
			return query.list();    
		}

		@Override
		public Employee findById(int id) {
			// TODO Auto-generated method stub
			return null;
		}
	    
}
