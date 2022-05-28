package com.maker.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Spring JdbcTemplate的使用
 * 	Spring为我们提供的JDBC的操作工具，例如操作关系型数据库的：JdbcTemplate、HibernateTemplate
 * 	操作NoSQL数据库的RedisTemplate，操作消息队列的JmsTemplate等等
 * 
 * 
 * Spring JdbcTemplate使用步骤
 * 	1.导入spring-jdbc和spring-tx（事务支持）坐标
 * 	2.创建数据库表和实体
 * 	3.创建JdbcTemplate对象
 * 	4.执行数据库操作
 * */
public class SpringJdbcTemplate {
	@Test
	public void test1(){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		DruidDataSource datasource=(DruidDataSource) app.getBean("datasource_druid");
		JdbcTemplate jTemplate=new JdbcTemplate();
		jTemplate.setDataSource(datasource);
		//插入数据
		int row=jTemplate.update("insert into account Values(?,?)","zhangsan",5000);
		System.out.println("影响行数："+row);
		
	}
	/**
	 * 让Spring来产生模板对象
	 * */
	@Test
	public void test2(){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jTemplate=(JdbcTemplate) app.getBean("jtemplate");
		int row=jTemplate.update("insert into account Values(?,?)","lisi",3000);
		System.out.println("影响行数："+row);		
	}
}
