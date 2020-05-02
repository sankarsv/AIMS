package com.app.aims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.Billing;

@Repository
public interface BillingDataRepository extends JpaRepository<Billing, Integer> {

	public List<Billing> findByVersion(int version);
}
