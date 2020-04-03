package com.app.aims.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.aims.security.JwtGenarator;
import com.app.aims.security.model.JwtUser;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private JwtGenarator jwtGenarator;
	
	public LoginController(JwtGenarator jwtGenerator) {
        this.jwtGenarator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {

        return jwtGenarator.generate(jwtUser);

    }

}
