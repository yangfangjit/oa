package com.banana.oa.aop;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.banana.oa.aop.Performance;
import com.banana.oa.service.BaseTest;

public class PerformanceTest extends BaseTest {

	/*
	 * AOP test
	 * must be provided in inject way, otherwise AOP won't work if we new a Performance object
	 */
	@Autowired
	private Performance performance;
	
	@Test
	public void testPerform() {
		performance.perform();
	}

}
