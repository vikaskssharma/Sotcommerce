package com.sot.ecommerce.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class CommonLoggingAspect {

	/** Logger instance. **/
	private static final Logger logger = LoggerFactory.getLogger(CommonLoggingAspect.class);

	Long startTime = 0l;
	Long endTime = 0l;

	@Pointcut("execution(* com.sot.ecommerce.merchant.service..*.*(..)) || execution(* com.sot.ecommerce.common.dao..*.*(..)))")
	private void loggingOperation() {
	}

	@Before("loggingOperation()")
	public void logBefore(JoinPoint joinPoint) {
		startTime = System.currentTimeMillis();
		logger.info("The Method " + joinPoint.getSignature().getName()
				+ "() in Target Class "
				+ joinPoint.getTarget().getClass().getName()
				+ " Starts execution");
	}

	@After("loggingOperation()")
	public void logAfter(JoinPoint joinPoint) {
		endTime = System.currentTimeMillis();
		logger.info("The Method " + joinPoint.getSignature().getName()
				+ "() in Target Class "
				+ joinPoint.getTarget().getClass().getName()
				+ " Finish execution in " + (endTime - startTime)
				+ " milli - seconds");
	}

	@AfterThrowing(pointcut = "loggingOperation()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		logger.error("The Method " + joinPoint.getSignature().getName()
				+ "() in Target Class "
				+ joinPoint.getTarget().getClass().getName()
				+ " throws exception. Exception details are::" + e);
	}

}
