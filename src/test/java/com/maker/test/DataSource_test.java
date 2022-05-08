package com.maker.test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource_test {
	/**
	 * 测试手动创建c3p0数据源
	 * */
	@Test
	public void datasourceTest(){
		ComboPooledDataSource datasource=new ComboPooledDataSource();
		try {
			datasource.setDriverClass("com.mysql.cj.jdbc.Driver");
			datasource.setJdbcUrl("jdbc:mysql://localhost:3306/mydb?character=utf8&useSSL=false&ServerTimezone=UTC");
			datasource.setUser("root");
			datasource.setPassword("123456");
			Connection con=datasource.getConnection();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void datasourceTest2(){
		DruidDataSource datasource=new DruidDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/mydb?character=utf8&useSSL=false&ServerTimezone=UTC");
		datasource.setUsername("root");
		datasource.setPassword("123456");
		
		try {
			Connection con=datasource.getConnection();
			System.out.println(con);
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void Springdatasource(){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		ComboPooledDataSource datasource1=(ComboPooledDataSource) app.getBean("datasource_c3p0");
		try{Connection c3p0_con=datasource1.getConnection();
		System.out.println("【Spring获取c3p0数据源连接】"+c3p0_con);
		c3p0_con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 通过Spring配置druid数据源
	 * 在Spring的 xml文件中读取properties文件，对数据源属性进行注入
	 * */
	@Test
	public void Springdatasource2(){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		DruidDataSource datasource=(DruidDataSource) app.getBean("datasource_druid");
		try {
			Connection druid_con=datasource.getConnection();
			System.out.println("【Spring利用properties文件获取druid数据源】"+druid_con);
			druid_con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
