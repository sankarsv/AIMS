package com.app.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.UserDetail;

@Repository
public interface UserRepository extends JpaRepository<UserDetail, Integer> {

	public UserDetail findByUserID(Integer userID);
 
}
