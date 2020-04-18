package com.app.aims.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.ExportXlsRequest;
import com.app.aims.beans.GenerateBaseLineRequest;
import com.app.aims.beans.SearchResponse;
import com.app.aims.beans.VersionInfo;
import com.app.aims.service.EmployeeService;
import com.app.aims.service.ExportXlsService;
import com.app.aims.service.UploadXlsService;
import com.app.aims.vo.SearchRequest;


@RestController
@RequestMapping(value={"/user"})
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    ExportXlsService exportXlsService;
    
    @Autowired
    UploadXlsService uploadXlsService;

//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Employee> getUserById(@PathVariable("id") int id) {
//        System.out.println("Fetching User with id " + id);
//        Employee user = employeeService.findById(id);
//        if (user == null) {
//            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<Employee>(user, HttpStatus.OK);
//    }

//    @PostMapping(value="/create",headers="Accept=application/json")
//    public ResponseEntity<Void> createUser(@RequestBody Employee user, UriComponentsBuilder ucBuilder){
//        employeeService.createUser(user);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getEmpId()).toUri());
//        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//    }
    
    @PostMapping(value="/upload")
    public ResponseEntity<Void> uploadXls(@RequestParam("file") MultipartFile file){
    	try {
    		
    	
       	 System.out.println("Token check passed");
       	 //System.out.println(uploadXlsRequest.getXlsBytes());
       	 uploadXlsService.uploadXls(file.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    
    @GetMapping(value="/get", headers="Accept=application/json")
    public List<Employee> downloadxls() {
        List<Employee> tasks=employeeService.getUser();
        return tasks;

    }
    
    @PostMapping(value="/generate-baseline", headers="Accept=application/json")
    public ResponseEntity  generateBaseline(@RequestBody GenerateBaseLineRequest generateBaseLineRequest) throws InvalidRequestException, ParseException {
    	
	   employeeService.generateBaseLine(generateBaseLineRequest);
	   return new ResponseEntity<>("OK", HttpStatus.OK);
	    
    }
    
    @GetMapping(value="/versioninfo", headers="Accept=application/json")
    public ResponseEntity<List<VersionInfo>>  generateBaseline() throws InvalidRequestException, ParseException {
    	
    	List<VersionInfo> list = employeeService.getVersionInfo();
	   return new ResponseEntity<>(list, HttpStatus.OK);
	    
    }
    
    @PostMapping(value="/download", headers="Accept=application/json")
    public ResponseEntity<byte[]>  getAllUser(@RequestBody ExportXlsRequest exportXlsRequest) throws InvalidRequestException {
    	
	    byte[] output =	exportXlsService.downloadXlsReportOfEmployees(exportXlsRequest);
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("charset", "utf-8");
	    responseHeaders.setContentType(MediaType.valueOf("text/html"));
	    responseHeaders.setContentLength(output.length);
	    responseHeaders.set("Content-disposition", "attachment; filename=employeeDetails.xls");
	    return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
	    
    }
    
    @PostMapping(value="/getEmployeeDetails")
    public ResponseEntity<Object> getEmployeeDetails(@RequestBody SearchRequest req){
    	System.out.println("Request is inside");
    	List<SearchResponse> searchResponseList = null;
    	
    	if(req.getEmpId() != null) {
    		searchResponseList = employeeService.leftJoinData(req.getEmpId());
    	} else if(req.getGlId() != null) {
    		searchResponseList = employeeService.leftJoinDataByBrm(req.getGlId());
    	} else if (req.getDmId() != null) {
    		searchResponseList = employeeService.leftJoinDataByDM(req.getDmId());
    	} else {
    	 return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    	}
    	
		return new ResponseEntity<Object>(searchResponseList, HttpStatus.OK);
    	
    }
 
    @GetMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateUser(@RequestBody EditRequest editRequest)
    {
        System.out.println("sd");
        //Employee user = employeeService.findById(editRequest.getEmEmpNo());
//        if (user==null) {
//            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
//        }
        employeeService.update(editRequest);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping (value="/delete", headers ="Accept=application/json")
    public ResponseEntity<Employee> deleteUser(@RequestBody EditRequest editRequest){
        
        employeeService.deleteUserById(editRequest.getEmpNo());
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
    
    
}
