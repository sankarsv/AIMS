package com.app.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.BillingVersion;

@Repository
public interface BillingVersionRespository extends JpaRepository<BillingVersion, Integer> {

}
