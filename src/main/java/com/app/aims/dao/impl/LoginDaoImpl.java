package com.app.aims.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.UserCred;
import com.app.aims.dao.LoginDao;

@Repository
public class LoginDaoImpl implements LoginDao {


	    @Autowired
	    private SessionFactory sessionFactory;

		@Override
		public boolean findByUser(UserCred userCred) {
			Session session = sessionFactory.getCurrentSession();
	        boolean userFound = false;
			String SQL_QUERY =" from UserCred where userId = :_userId AND password = :_password";
			Query query = session.createQuery(SQL_QUERY);
			//Integer userId = userCred.getUserId();
			query.setParameter("_userId",userCred.getUserId());
			query.setParameter("_password",userCred.getPassword());
			List list = query.list();
			session.close();
			if ((list != null) && (list.size() > 0)) {
				userFound= true;
			}
			return userFound;  
		}

}
