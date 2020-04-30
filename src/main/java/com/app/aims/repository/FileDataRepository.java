package com.app.aims.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.FileData;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Integer> {
	
	@Transactional
	public List<FileData> findAll();
 
}
