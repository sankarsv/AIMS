package com.app.aims.dao;

import com.app.aims.beans.UserCred;

public interface LoginDao {

	    public boolean findByUser(UserCred userCred);
}
