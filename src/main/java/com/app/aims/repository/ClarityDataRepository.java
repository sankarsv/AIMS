package com.app.aims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.Clarity;

@Repository
public interface ClarityDataRepository extends JpaRepository<Clarity, Integer> {
	
	@Query("SELECT u FROM Clarity u WHERE u.version = :version")
	public List<Clarity> findByBillingVersionId(@Param("version") Integer versionId);
}
