package com.app.aims.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.UserDetail;
import com.app.aims.beans.UserRole;
import com.app.aims.repository.UserRepository;
import com.app.aims.security.JwtGenarator;
import com.app.aims.security.model.JwtUser;
import com.app.aims.util.CommonUtil;
import com.app.aims.vo.LoginReq;
import com.app.aims.vo.LoginResp;

@RestController
public class LoginController {
	
	private JwtGenarator jwtGenarator;
	
	@Autowired
	private UserRepository repository;
	
	public LoginController(JwtGenarator jwtGenerator) {
        this.jwtGenarator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {

        return jwtGenarator.generate(jwtUser);

    }
    
    @PostMapping(value="/login")
    public ResponseEntity<Object>  login(@RequestBody (required = true) LoginReq loginReq) throws InvalidRequestException, ParseException {
    	
    	if(loginReq.getUserId() != null && loginReq.getUserId() > 5 
				&& StringUtils.hasLength(loginReq.getPassword())) {
			String token = null;
			UserDetail userCred = new UserDetail();
			userCred.setUserID(loginReq.getUserId());
			userCred.setPwd(loginReq.getPassword());
			// boolean validUser = loginService.findByUser(userCred);
			UserDetail userCredTest = repository.findByUserID(loginReq.getUserId());
			if (userCredTest != null) {
				boolean validUser = CommonUtil.isPasswordMatch(loginReq.getPassword(),userCredTest.getPwd());
				if (validUser) {
					final JwtUser jwtUser = new JwtUser();
					jwtUser.setUserName(userCredTest.getUserID().toString());
					String userRoleNames = "";
					if(userCredTest.getUserRoles() != null && userCredTest.getUserRoles().size() > 0 ) {
						for (UserRole userDet : userCredTest.getUserRoles()) {
							if(userDet.getRole() != null) {
								userRoleNames = userRoleNames+userDet.getRole().getRoleName()+",";
							}
						}
						if(userRoleNames.endsWith(",")) {
							userRoleNames = userRoleNames.substring(0,userRoleNames.length()-1);
						} else {
							userRoleNames = "DEV";
						}
					}
					jwtUser.setRole(userRoleNames);
					jwtUser.setId(System.currentTimeMillis());
					token = generate(jwtUser);
					LoginResp loginResp = new LoginResp();
					loginResp.setToken(token);
					return new ResponseEntity<>(loginResp, HttpStatus.OK);
				}
				return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
			}
		}
    	return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
    }
}
