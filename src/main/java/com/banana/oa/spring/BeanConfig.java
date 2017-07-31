package com.banana.oa.spring;

import java.beans.PropertyVetoException;
import java.io.IOException;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class BeanConfig {

	/*@Autowired
	private Environment env;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties(){
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[]{new ClassPathResource("jdbc.properties")};
		pspc.setLocations( resources );
		pspc.setIgnoreUnresolvablePlaceholders( true );
		return pspc;
	}*/
	
	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		/*dataSource.setDriverClass(env.getProperty("driver"));
		dataSource.setJdbcUrl(env.getProperty("url"));
		dataSource.setUser(env.getProperty("user"));
		dataSource.setPassword(env.getProperty("password"));*/
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/oa?useUnicode=true&characterEncoding=utf8");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		
		dataSource.setMaxPoolSize(30);
		dataSource.setMinPoolSize(10);
		dataSource.setAutoCommitOnClose(false);
		dataSource.setCheckoutTimeout(1000);
		dataSource.setAcquireRetryAttempts(2);
		
		return dataSource;
	}
	
	public org.apache.ibatis.session.Configuration mybatisConfiguration() {
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setUseGeneratedKeys(true);
		configuration.setUseColumnLabel(true);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setLogImpl(Slf4jImpl.class);
		
		return configuration;
	}
	
	@SuppressWarnings("resource")
	@Autowired
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(ComboPooledDataSource dataSource) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfiguration(mybatisConfiguration());
		sqlSessionFactoryBean.setTypeAliasesPackage("com.banana.oa.entity");
		ApplicationContext ctx = new ClassPathXmlApplicationContext();
		sqlSessionFactoryBean.setMapperLocations(ctx.getResources("mapper/*.xml"));
		
		return sqlSessionFactoryBean;
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.banana.oa.dao");
		return mapperScannerConfigurer;
	}
	
	@Autowired
	@Bean
	public DataSourceTransactionManager transactionManager(ComboPooledDataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
}
