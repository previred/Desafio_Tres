package com.previred.challenge.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogServiceAspect {
	
	@Pointcut("execution(* com.previred.challenge.service..*(..)) && args(..)")
	public void logService() {}
	
	@Around("logService()")
	public Object log(ProceedingJoinPoint pjp) throws Throwable{
		StopWatch sw = new StopWatch();
		try {
			sw.start();
			return pjp.proceed();
		}finally {
			sw.stop();
			log.info("timelapsd: {} (ms), - {}.{} (..)",
					sw.getTotalTimeMillis(),
					pjp.getTarget().getClass().getSimpleName(),
					pjp.getSignature().getName()
			);
			
			log.debug("timelapsd: {} (ms), - {} (..)",
					sw.getTotalTimeMillis(),
					pjp.getSignature().getName()
			);;
		}
		
	}
	
	@AfterThrowing(pointcut = "logService()", throwing = "error")
	public void exceptionLogger(JoinPoint joinPoint, Throwable error) {
		log.error("{} (..), Type: {}, Message: {}",
				joinPoint.getSignature().getName(),
				joinPoint.getClass().getSimpleName(),
				error.getMessage());
	}
}
