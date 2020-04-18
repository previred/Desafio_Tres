package com.previred.challenge.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorController {

	@ResponseBody
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorResponse> serviceException(ServiceException e) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.Builder()
					.message(e.getMessage())
					.exception(e.getClass().getSimpleName()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionError(Exception e) {
		return new ResponseEntity<ErrorResponse>(
				ErrorResponse.Builder()
					.message(e.getMessage())
					.exception(e.getClass().getSimpleName()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
