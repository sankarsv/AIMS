package com.app.aims.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.aims.beans.Employee;
import com.app.aims.beans.Portfolio;
import com.app.aims.dao.BaseLineDao;
import com.app.aims.dao.EmployeeDao;

public class CommonUtil {
	
    @Autowired
	static EmployeeDao employeeDao;
    
    @Autowired
    static BaseLineDao baseLineDao;
	
	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static String encodePlaintext(String plainText) {
		if (plainText != null) {
			String hashedPassword = passwordEncoder.encode(plainText);
			System.out.println(" Encoded pwd ---> " + hashedPassword);
			return hashedPassword;
		}
		return plainText;

	}
	
	public static boolean isPasswordMatch (String password,String encodedPassword) {
		return passwordEncoder.matches(password,encodedPassword);
	}

	public static Map<Integer,Employee> getEmployeeDetailMap() {
		Map<Integer,Employee> employeeDetailMap = new HashMap<Integer,Employee>();
		List<Employee> employeeList = employeeDao.getEmployeeDetails();
		employeeList.stream().forEach(e -> {
			employeeDetailMap.put(e.getEmployeeId(), e);
		});
		return employeeDetailMap;
	}
	
	public static Map<Integer,String> getPortfolioMap() {
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
