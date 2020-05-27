package com.app.aims.dao;

import java.util.Date;
import java.util.List;

import com.app.aims.beans.BaseLine;
import com.app.aims.beans.Portfolio;

public interface BaseLineDao {

	public  BaseLine getMaxBaseLineDetails(Date date);
	public  List<Portfolio> getPortfolio();
}
