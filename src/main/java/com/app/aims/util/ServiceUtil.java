package com.app.aims.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.aims.beans.Employee;
import com.app.aims.beans.Portfolio;
import com.app.aims.dao.BaseLineDao;
import com.app.aims.dao.EmployeeDao;

@Service
public class ServiceUtil {
	
    @Autowired
	EmployeeDao employeeDao;
    
    @Autowired
    BaseLineDao baseLineDao;
	
	

	public  Map<Integer,Employee> getEmployeeDetailMap() {
		Map<Integer,Employee> employeeDetailMap = new HashMap<Integer,Employee>();
		List<Employee> employeeList = employeeDao.getEmployeeDetails();
		employeeList.stream().forEach(e -> {
			employeeDetailMap.put(e.getEmployeeId(), e);
		});
		return employeeDetailMap;
	}
	
	public  Map<Integer,String> getPortfolioMap() {
		Map<Integer,String> portFolioMap = new HashMap<Integer,String>();
		List<Portfolio> portfolioList = baseLineDao.getPortfolio();
		portfolioList.stream().forEach(p -> {
			portFolioMap.put(p.getBrmEmpId(),p.getBrmname());
			portFolioMap.put(p.getBilling_lead_emp_id(),p.getBilling_lead_name());
			portFolioMap.put(p.getDm_emp_id(), p.getDm_name());
		});
		return portFolioMap;
	}
	
}
