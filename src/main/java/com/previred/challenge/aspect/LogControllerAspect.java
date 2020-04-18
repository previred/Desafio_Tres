package com.previred.challenge.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogControllerAspect {

	@AfterReturning(
			pointcut = "within(@org.springframework.web.bind.annotation.RestController *) ",
			returning = "retVal"
			)
	public void logAfterMethod(JoinPoint jPoint, Object retVal) {
		@SuppressWarnings("rawtypes")
		ResponseEntity 	responseEntity = (ResponseEntity) retVal;
		if(responseEntity != null) {
			log.info("HttpSatus: {}, {}, {} (..)", 
					responseEntity.getStatusCodeValue(), 
					jPoint.getTarget().getClass().getSimpleName(),
					jPoint.getSignature().getName());
		}else{
			log.info("{}, {} (..)",  
					jPoint.getTarget().getClass().getSimpleName(),
					jPoint.getSignature().getName());
		}
	}
	
}
