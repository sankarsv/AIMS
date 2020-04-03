package com.app.aims.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.aims.Exceptions.InvalidRequestException;

@Controller
public class ExceptionHandlerController {

	@ExceptionHandler(InvalidRequestException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex){
		
		return "database_error";
	}
	
}
