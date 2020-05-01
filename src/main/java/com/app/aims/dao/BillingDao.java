package com.app.aims.dao;

import java.util.Date;
import java.util.List;

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BillingFileData;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.FileData;
import com.app.aims.beans.VersionInfo;

public interface BillingDao {

	    public List<BRMDetails> retrieveBRMInfo();
		
}
