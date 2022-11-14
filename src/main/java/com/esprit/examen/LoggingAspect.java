package com.esprit.examen;

import org.apache.logging.log4j.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private static final Logger log = LogManager.getLogger(LoggingAspect.class);
	@After("execution(* tn.esprit.spring.service.*.get*(..))")
	public void apres(JoinPoint thisJoinPoint) {
		log.info("Out of the method (After)" + thisJoinPoint.getSignature().getName());
	}
}
