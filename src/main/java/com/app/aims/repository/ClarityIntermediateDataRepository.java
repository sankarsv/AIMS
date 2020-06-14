package com.app.aims.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.ClarityFileData;

@Repository
public interface ClarityIntermediateDataRepository extends JpaRepository<ClarityFileData, Integer> {
	
	@Transactional
	public List<ClarityFileData> findAll();
 
}
