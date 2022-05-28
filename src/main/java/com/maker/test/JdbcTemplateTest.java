package com.maker.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maker.bean.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateTest {
	@Autowired
	@Qualifier("jtemplate")
	private JdbcTemplate jtemplate;
	@Test
	public void addTest(){
		int row=jtemplate.update("insert into account values(?,?)","wangwu",10000);
		System.out.println("新增："+row);
	}
	
	@Test
	public void deleteTest(){
		int row=jtemplate.update("delete from account where name=?","lisi");
		System.out.println("删除："+row);
	}
	
	@Test
	public void updateTest(){
		int row=jtemplate.update("update account set money=? where name=?",12000,"zhangsan");
		System.out.println("更新："+row);
	}
	
	/**
	 * 查询全部
	 * 	query方法
	 * 	
	 * */
	@Test
	public void queryTest(){
		/*
		 *@param psc sql语句
		 *@param RowMapper接口对象，自动进行数据封装，将每一行包装成集合元素返回
		 *BeanPropertyRowMapper为RowMapper接口的实现类对象
		 *@return 返回一个集合，集合的类型由泛型和传入的Class参数确定
		 * */
		List<Account> alist=this.jtemplate.query("Select * from account", new BeanPropertyRowMapper<Account>(Account.class));
		System.out.println("【查询全部】："+alist);
	}
	
	/**
	 * 查询单条数据
	 * */
	@Test
	public void queryTest2(){
		/*
		 *@param psc sql语句
		 *@param RowMapper接口对象，自动进行数据封装，将每一行包装成集合元素返回
		 *@param args 填充占位符的参数
		 *BeanPropertyRowMapper为RowMapper接口的实现类对象
		 *@return 返回一个对象，对象的类型由泛型和传入的Class参数确定
		 * */
		Account ac=this.jtemplate.queryForObject("Select * from account where name=?", new BeanPropertyRowMapper<Account>(Account.class),"zhangsan");
		System.out.println("【查询单条数据】："+ac);
	}
	
	/**
	 * 聚合查询
	 * */
	@Test
	public void queryTest3(){
		long count=this.jtemplate.queryForObject("Select count(*) from account", Long.class);
		System.out.println("【聚合查询】："+count);
	}

	public JdbcTemplate getJtemplate() {
		return jtemplate;
	}

	public void setJtemplate(JdbcTemplate jtemplate) {
		this.jtemplate = jtemplate;
	}
}
