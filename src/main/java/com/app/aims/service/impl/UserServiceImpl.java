package com.app.aims.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.BaseLine;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.EmployeeAllocation;
import com.app.aims.beans.EmployeeMergedDetails;
import com.app.aims.beans.GenerateBaseLineRequest;
import com.app.aims.beans.UserDetail;
import com.app.aims.dao.BaseLineDao;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.dao.EmployeeMergedDetailsDao;
import com.app.aims.dao.UserDao;
import com.app.aims.service.EmployeeService;
import com.app.aims.service.UserService;
import com.app.aims.util.DateUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDtlDao;

	@Override
	public UserDetail findById(int userID) {
		 System.out.println(" Printing UserID in findById Method in UserServiceImpl " + userID);
		// TODO Auto-generated method stub
		return userDtlDao.findById(userID);
	}

	@Override
	public void createUser(UserDetail userDtl) {
		System.out.println(" Printing UserDetail in createUser Method in UserServiceImpl " + userDtl);
		// TODO Auto-generated method stub
		userDtlDao.addUserDetail(userDtl);
	}

}
