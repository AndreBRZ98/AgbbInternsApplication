package com.agbb.agbbinterns.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.agbb.agbbinterns.exception.AgbbInternsException;

@Component
@Aspect
public class LoggingAspect {

	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	@AfterThrowing(pointcut = "execution(* com.agbb.agbbinterns.service.*Impl.*(..) )", throwing = "exception")
	public void logServiceException(AgbbInternsException exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
}

