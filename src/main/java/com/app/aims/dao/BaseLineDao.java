package com.app.aims.dao;

import java.util.Date;
import java.util.List;

import com.app.aims.beans.BaseLine;

public interface BaseLineDao {

	public  BaseLine getMaxBaseLineDetails(Date date);
}
