package com.app.aims.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtil {

	public static String encodePlaintext(String plainText) {
		if (plainText != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(plainText);
			System.out.println(" Encoded pwd ---> " + hashedPassword);
			return hashedPassword;
		}
		return plainText;

	}

}
