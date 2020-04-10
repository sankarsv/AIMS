package com.app.aims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.UserCred;
import com.app.aims.dao.LoginDao;
import com.app.aims.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;

	@Override
	public boolean findByUser(UserCred userCred) {
		return loginDao.findByUser(userCred);
	}
    
}
 	