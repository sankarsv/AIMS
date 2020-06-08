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

}
