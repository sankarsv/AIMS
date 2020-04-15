package com.app.aims.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtil {
	
	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static String encodePlaintext(String plainText) {
		if (plainText != null) {
			String hashedPassword = passwordEncoder.encode(plainText);
			System.out.println(" Encoded pwd ---> " + hashedPassword);
			return hashedPassword;
		}
		return plainText;

	}
	
	public static boolean isPasswordMatch (String password,String encodedPassword) {
		return passwordEncoder.matches(password,encodedPassword);
	}

}
