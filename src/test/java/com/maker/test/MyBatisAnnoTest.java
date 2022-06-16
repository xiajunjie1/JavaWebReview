package com.maker.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.maker.dao.AccountMapperAnno;
import com.maker.domain.Account;

public class MyBatisAnnoTest {
	private AccountMapperAnno account;
	@Before//此处的Before注解是Junit提供的注解，代表在执行测试之前执行该方法
	public void beforeHandler(){
		try{InputStream stream=Resources.getResourceAsStream("sqlMapConfigAnno.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(stream);
		SqlSession session=factory.openSession(true);//自动提交
		account=session.getMapper(AccountMapperAnno.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertTest(){
		Account ac=new Account();
		ac.setName("luozixiang");
		ac.setMoney(102345.00);
		try {
			account.insert(ac);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void updateTest(){
		Account ac=new Account();
		ac.setName("luozixiang");
		ac.setMoney(22222.22);
		try{
			account.update(ac);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectOneTest(){
		try{
			Account result=account.getOne("luozixiang");
			System.out.println(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void selectAllTest(){
		try{
			List<Account> alist=account.findAll();
			System.out.println(alist);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest(){
		try{
			account.delete("luozixiang");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
