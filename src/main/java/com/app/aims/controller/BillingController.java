package com.app.aims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BillingVersion;
import com.app.aims.beans.GenerateBaseLineRequest;
import com.app.aims.service.BillingService;
import com.app.aims.vo.BaseResponse;
import com.app.aims.vo.BillingDetailUpdateReq;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;
import com.app.aims.vo.DownloadXlsResponse;

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
	
	

	@PostMapping(value="/updateFreeze", headers="Accept=application/json")
    public ResponseEntity<Object> updateFreezeInd(@RequestBody BillingVersion billingVersion) throws InvalidRequestException
    {
     
		if(validReq(billingVersion)) {

	    	boolean status = billingService.updateFreeze(billingVersion);
	    	return new ResponseEntity<Object>(status, HttpStatus.OK);	
		} 
		else
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    	
    }
	
	
	
	@PostMapping(value = "/getBillingDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBillingDetails(@RequestBody BillingDetailsReq billingDetailReq) {
		if(validReq(billingDetailReq)) {
			List<BillingDetailsResp> billingDetails = billingService.getBillingDetails(billingDetailReq);
			if(billingDetails != null) {
				if(!(billingDetails.get(0).getErrorList().size() > 0)) {
					return new ResponseEntity<Object>(billingDetails, HttpStatus.OK);
				} else if("NOT FOUND".equalsIgnoreCase(billingDetails.get(0).getErrorList().get(0))){
					System.out.println("Value not in DB");
					return new ResponseEntity<>("Value not in DB", HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value = "/updateBillingDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateBilingDetails(@RequestBody BillingDetailUpdateReq updateBillingDetailReq) {
		if (validReq(updateBillingDetailReq)) {
		BaseResponse resp = billingService.updateBillingDetails(updateBillingDetailReq);
		if(resp == null) {
			return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		} else {
			if("NOT FOUND".equalsIgnoreCase(resp.getErrorList().get(0))) {
				System.out.println("Value not in DB");
				return new ResponseEntity<>("Value not in DB", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	  }
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);	
	}
	
	@PostMapping(value = "/updateFreezeNew", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateFreezeInd(@RequestBody BillingDetailsReq billingDetailReq) {
		if (validReq(billingDetailReq) && billingDetailReq.getFreezeInd() != null) {
			BaseResponse resp = billingService.updateFreezeInd(billingDetailReq);
			if (resp == null) {
				return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
			} else {
				if ("NOT FOUND".equalsIgnoreCase(resp.getErrorList().get(0))) {
					System.out.println("Value not in DB");
					return new ResponseEntity<>("Value not in DB", HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value="/downloadBilling")
    public ResponseEntity<byte[]>  exportHCData(@RequestBody BillingDetailsReq billingDetailReq) throws Exception {
    	if(!((StringUtils.hasText(billingDetailReq.getVersion())) || 
    			(StringUtils.hasText(billingDetailReq.getBrmName()) && StringUtils.hasText(billingDetailReq.getMonth()) && StringUtils.hasText(billingDetailReq.getYear())))) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	DownloadXlsResponse response=	billingService.downloadXlsBillingReport(billingDetailReq);
    	if(response == null || response.getErrorList().size() > 0) {
    		if ("NOT FOUND".equalsIgnoreCase(response.getErrorList().get(0))) {
				System.out.println("Value not in DB");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
    	}
	    byte[] output = response.getByteArray();
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("charset", "utf-8");
	    responseHeaders.setContentType(MediaType.valueOf("text/html"));
	    responseHeaders.setContentLength(output.length);
	    responseHeaders.set("Content-disposition", "attachment; filename=BillingDetails.xls");
	    return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
	    
    }
	
	private boolean validReq(BillingDetailUpdateReq req) {
		try {
			if(req != null && req.getVersion() != null && req.getBillingDetailsList() != null && req.getBillingDetailsList().size() > 0) {
				int parseInt = Integer.parseInt(req.getVersion());
				System.out.println("Version To be updated --> " + parseInt);
				return true;
			} else {
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean validReq(BillingDetailsReq billingDetailReq) {
		return (StringUtils.hasText(billingDetailReq.getBrmName()) && StringUtils.hasText(billingDetailReq.getMonth()) && StringUtils.hasText(billingDetailReq.getYear()));
	}
	
	private boolean validReq(BillingVersion billingVersion) {
		return (StringUtils.hasText(billingVersion.getBrmId()) && StringUtils.hasText(billingVersion.getMonth()) && !StringUtils.isEmpty(billingVersion.getYear()) && StringUtils.hasText(billingVersion.getFreezeInd()));
	}
}
