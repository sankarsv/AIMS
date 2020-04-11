package com.app.aims.dao;

import java.util.Date;
import java.util.List;

import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.UserDetail;

public interface UserDao {

	    public void addUserDetail(UserDetail user);
	    public UserDetail findById(int userId);
	    
}
