package com.maker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Spring配置类，通过该类+注解的方式取代xml配置文件
 * */

@Configuration //说明该类是一个配置类
@ComponentScan("com.maker")//<context:component-scan base-package="com.maker" />
@PropertySource("classpath:jdbc.properties")//<context:property-placeholder location="classpath:jdbc.properties"/>
public class SpringConfig {
	@Value("${jdbc.Driver}")//从properties文件中获取相应的数据
	private String driver;
	@Value("${jdbc.Url}")
	private String url;
	@Value("${jdbc.Username}")
	private String username;
	@Value("${jdbc.Password}")
	private String password;
	
	@Bean("druid_datasource")//替代bean标签，导入第三方类
	public DruidDataSource get(){
		DruidDataSource datasource=new DruidDataSource();
		datasource.setDriverClassName(driver);
		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		return datasource;
	}
	
}
