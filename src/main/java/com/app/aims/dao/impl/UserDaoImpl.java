package com.app.aims.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.app.aims.beans.UserDetail;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.dao.UserDao;
import com.app.aims.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired(required = true)
	private UserRepository userRepository;

	@Override
	public void addUserDetail(UserDetail user) {
		// TODO Auto-generated method stub
		System.out.println(" Printing user in addUserDetail Method in UserDaoImpl " + user);
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public UserDetail findById(Integer userId) {
		// TODO Auto-generated method stub
		System.out.println(" Printing userId in findById Method in UserDaoImpl " + userId);
		Optional<UserDetail> userDtl = userRepository.findById(userId).isPresent() ? userRepository.findById(userId)
				: null;
		if (userDtl != null) {
			System.out.println(" Printing userDtl object in findById Method in UserDaoImpl " + userDtl.get());
			return userDtl.get();
		}
		return null;

	}

}
