package com.app.aims.service;

import java.util.List;

import com.app.aims.vo.FileStatus;

public interface BatchDetailsService {

    public List<FileStatus> findAllFileProcessDetails();


}
