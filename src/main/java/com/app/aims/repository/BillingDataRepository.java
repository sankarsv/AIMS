package com.app.aims.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.Billing;

@Repository
public interface BillingDataRepository extends JpaRepository<Billing, Integer> {

	public List<Billing> findByVersion(int version);
	
	@Query(value = "SELECT u FROM User u WHERE u.version IN :versions")
	public List<Billing> findByVersionList(@Param("versions") Collection<Integer> versions);
}
