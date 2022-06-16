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
import com.maker.dao.OrderMapperAnno;
import com.maker.domain.Account;
import com.maker.domain.Order;

public class MyBatisAnnoTest2 {
	private OrderMapperAnno order;
	@Before//此处的Before注解是Junit提供的注解，代表在执行测试之前执行该方法
	public void beforeHandler(){
		try{InputStream stream=Resources.getResourceAsStream("sqlMapConfigAnno.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(stream);
		SqlSession session=factory.openSession(true);//自动提交
		order=session.getMapper(OrderMapperAnno.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void OneToOneTest(){
		try {
			List<Order> orders =order.getAll();
			System.out.println(orders);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void OneToOneTest2(){
		try{
			List<Order> orders=order.getAll2();
			System.out.println(orders);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3(){
		try {
			List<Order> o=order.getOrdersByUid(1);
			System.out.println(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
