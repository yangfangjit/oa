package com.banana.oa.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.banana.oa.aop.Audience;
import com.banana.oa.aop.Performance;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

	@Bean
	public Audience audience() {
		return new Audience();
	}
	
	@Bean
	public Performance performance() {
		return new Performance();
	}
}
