package com.navastud.complementouf.exception;

import org.springframework.http.HttpStatus;

public class SpringMvcException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3670478139747930150L;

	private final HttpStatus httpStatus;

	public SpringMvcException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
