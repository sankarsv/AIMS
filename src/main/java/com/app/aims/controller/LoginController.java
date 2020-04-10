package com.app.aims.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.GenerateBaseLineRequest;
import com.app.aims.beans.UserCred;
import com.app.aims.security.JwtGenarator;
import com.app.aims.security.model.JwtUser;
import com.app.aims.service.LoginService;
import com.app.aims.vo.LoginReq;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private JwtGenarator jwtGenarator;
	
	@Autowired
	private LoginService loginService;
	
	public LoginController(JwtGenarator jwtGenerator) {
        this.jwtGenarator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {

        return jwtGenarator.generate(jwtUser);

    }

    @PostMapping(value="/authenticate")
    public ResponseEntity<String>  login(@RequestBody (required = true) LoginReq loginReq) throws InvalidRequestException, ParseException {
    	
    	if(StringUtils.hasLength(loginReq.getUserName()) 
    			&& StringUtils.hasLength(loginReq.getPassword())) {
    	String token = null;
    	UserCred userCred = new UserCred();
    	userCred.setUserId(loginReq.getUserName());
    	userCred.setPassword (loginReq.getPassword());
    	boolean validUser = loginService.findByUser(userCred);
    	
    	if(validUser) {
    		final JwtUser jwtUser = new JwtUser();
    		jwtUser.setUserName(loginReq.getUserName());
    		token = generate(jwtUser);
    		return new ResponseEntity<>(token, HttpStatus.OK);
    	}
    	return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
    }
}
