package com.maker.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maker.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans2.xml")
public class TransactionTest {
	@Autowired
	@Qualifier("accountservice")
private AccountService service;
	
	@Test
	public void test1(){
		try {
			service.transfer("zhangsan", "wangwu", 500.00);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("转账异常");
		}
	}
}
