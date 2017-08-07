package com.banana.oa.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Performance {
	
	private static final Logger logger = LoggerFactory.getLogger(Performance.class);

	public void perform() {
		logger.info("perform ...");
	}
}
