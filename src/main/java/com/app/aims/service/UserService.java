package com.app.aims.service;

import com.app.aims.beans.UserDetail;
import com.app.aims.beans.UserDetailRequest;

public interface UserService {

	public void createUser(UserDetailRequest userDtlRq);
    public UserDetailRequest findById(Integer userID);

}
