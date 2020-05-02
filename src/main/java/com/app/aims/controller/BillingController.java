package com.app.aims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.aims.beans.BRMDetails;
import com.app.aims.service.BillingService;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;

@RestController
@RequestMapping(value={"/user"})
public class BillingController {

	@Autowired
    BillingService billingService;
	
	@GetMapping(value="/getBRMDetails", headers="Accept=application/json")
    public ResponseEntity<Object> getBRMDetails()
    {
     
    	List<BRMDetails> brmDetails = billingService.getBRMDetails();
    	return new ResponseEntity<Object>(brmDetails, HttpStatus.OK);
    	
    }
	@PostMapping(value = "/getBillingDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBillingDetailResponse(@RequestBody BillingDetailsReq billingDetailReq) {
		if(validReq(billingDetailReq)) {
			List<BillingDetailsResp> billingDetails = billingService.getBillingDetails(billingDetailReq);
			if(billingDetails != null) {
				if(!(billingDetails.get(0).getErrorList().size() > 0)) {
					return new ResponseEntity<Object>(billingDetails, HttpStatus.OK);
				} else if(billingDetails.get(0).getErrorList().get(0).equalsIgnoreCase("NOT FOUND")){
					System.out.println("Value not in DB");
					return new ResponseEntity<>("Value not in DB", HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	
	private boolean validReq(BillingDetailsReq billingDetailReq) {
		return (StringUtils.hasText(billingDetailReq.getBrmId()) && StringUtils.hasText(billingDetailReq.getMonth()) && StringUtils.hasText(billingDetailReq.getYear()));
	}
	
}
