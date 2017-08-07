package com.banana.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.banana.oa.aop.Performance;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private Performance performance;// must in inject way, aop won't work if we new a Performance object   

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		logger.info("home");
		performance.perform();
		return "home";
	}
}
