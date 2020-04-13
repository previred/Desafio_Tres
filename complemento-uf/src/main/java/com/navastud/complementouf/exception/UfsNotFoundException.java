package com.navastud.complementouf.exception;

import org.springframework.http.HttpStatus;

public class UfsNotFoundException extends SpringMvcException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1766816808218828673L;

	public UfsNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
		// TODO Auto-generated constructor stub
	}

}
