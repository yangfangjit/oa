package com.banana.oa.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class Audience {
	
	private static Logger logger = LoggerFactory.getLogger(Audience.class);
	
	@Pointcut("execution(* com.banana.oa.aop.Performance.perform(..))")
	public void performance() {}
	
	@Before("performance()")
	public void silenceCellPhone() {
		logger.info("silence cell phone");
	}
	
	@Before("performance()")
	public void takeSeats() {
		logger.info("take seats");
	}
	
	@AfterReturning("performance()")
	public void applause() {
		logger.info("CLAP CLAP CLAP");
	}
	
	@AfterThrowing("performance()") 
	public void demandRefund() {
		logger.info("Demanding a refund");
	}
}
