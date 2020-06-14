package com.app.aims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.aims.beans.Clarity;

import com.app.aims.service.ClarityService;

@RestController
@RequestMapping(value = { "/user" })
public class ClarityController {

	@Autowired
	ClarityService clarityService;

	@GetMapping(value = "/getClarityDetailsByBillingVersionID", headers = "Accept=application/json")
	public ResponseEntity<Object> getClarityDetailsByBillingVersionID(
			@Param("BillingVersionID") Integer versionID) {
		System.out.println("Entered in to Clarity Controller --->" + versionID);
		List<Clarity> clarityDetails = clarityService.getClarityDetailsByBillingVersionID(versionID);
		System.out.println("Printing Clarity Details in Clarity Controller --->" + clarityDetails);
		return new ResponseEntity<Object>(clarityDetails, HttpStatus.OK);

	}

	
}
