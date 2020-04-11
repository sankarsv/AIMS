package com.app.aims.service;

import com.app.aims.beans.UserDetail;

public interface UserService {

	public void createUser(UserDetail user);
    public UserDetail findById(int userID);

}
