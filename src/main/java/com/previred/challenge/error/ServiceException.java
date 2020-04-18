package com.previred.challenge.error;

public class ServiceException  extends RuntimeException {
	
	private static final long serialVersionUID = 720344521383255358L;

	public ServiceException (String errorMessage) {
        super(errorMessage);
    }
}
