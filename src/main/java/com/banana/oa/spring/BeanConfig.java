package com.banana.oa.spring;

import java.beans.PropertyVetoException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class BeanConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties(){
	   PropertySourcesPlaceholderConfigurer pspc =
	      new PropertySourcesPlaceholderConfigurer();
	   Resource[] resources = new ClassPathResource[]{ new ClassPathResource( "jdbc.properties" ) };
	  pspc.setLocations( resources );
	  pspc.setIgnoreUnresolvablePlaceholders( true );
	  return pspc;
	}
	
	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getProperty("driver"));
		dataSource.setJdbcUrl(env.getProperty("url"));
		dataSource.setUser(env.getProperty("user"));
		dataSource.setPassword(env.getProperty("password"));
		
		dataSource.setMaxPoolSize(30);
		dataSource.setMinPoolSize(10);
		dataSource.setAutoCommitOnClose(false);
		dataSource.setCheckoutTimeout(1000);
		dataSource.setAcquireRetryAttempts(2);
		
		return dataSource;
	}
	
	@Autowired
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(ComboPooledDataSource dataSource) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("classpath:mybatis-config.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.banana.oa.entity");
		Resource[] mapperLocations = new Resource[] {new ClassPathResource("classpath:mapper/*.xml")};
		sqlSessionFactoryBean.setMapperLocations(mapperLocations);
		
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.banana.oa.entity");
		return mapperScannerConfigurer;
	}
	
}
