package com.app.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.BillingFileData;

@Repository
public interface BillingFileDataRepository extends JpaRepository<BillingFileData, Integer> {
	
 
}
