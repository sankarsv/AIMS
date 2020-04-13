package com.app.aims.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.app.aims.beans.UserDetailRequest;
import com.app.aims.dao.BaseLineDao;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.dao.EmployeeMergedDetailsDao;
import com.app.aims.dao.UserDao;
import com.app.aims.service.EmployeeService;
import com.app.aims.service.UserService;
import com.app.aims.util.CommonUtil;
import com.app.aims.util.DateUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDtlDao;

	@Override
	public UserDetailRequest findById(Integer userID) {
		 System.out.println(" Printing UserID in findById Method in UserServiceImpl " + userID);
		 UserDetailRequest userDtlRq = new UserDetailRequest();
		// TODO Auto-generated method stub
		 UserDetail usrdtl = userDtlDao.findById(userID);
		 if(usrdtl!=null) {
		 userDtlRq.setUserId(usrdtl.getUserID());
		 userDtlRq.setPwd(usrdtl.getPwd());
		 userDtlRq.setChangedDate(usrdtl.getChangedDate());
		 }
		 
		return userDtlRq;
	}

	@Override
	public void createUser(UserDetailRequest userDtl) {
		System.out.println(" Printing UserDetail in createUser Method in UserServiceImpl " + userDtl);
		// TODO Auto-generated method stub
		UserDetail userDtldto = new UserDetail();
		if((userDtl.getUserId()!=null&&userDtl.getPwd()!=null)&&(userDtl.getUserId().SIZE>=0 && !userDtl.getPwd().isEmpty())) {
		userDtldto.setUserID(userDtl.getUserId());
		String hashedPassword = CommonUtil.encodePlaintext(userDtl.getPwd());
		userDtldto.setPwd(hashedPassword);
		//userDtldto.setPwd(userDtl.getPwd());
		userDtldto.setChangedDate(userDtl.getChangedDate());
		}
		userDtlDao.addUserDetail(userDtldto);
	}

}
