package com.previred.challenge.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderClassName = "Builder", buildMethodName = "build", builderMethodName = "Builder")
public class ErrorResponse {

	private final String message;
	
	private final String exception;
	
}
