package com.maker.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maker.aop.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AopRunTest {
@Autowired
@Qualifier("TestService")
private TestService target;
	@Test
	public void test1(){
		target.save();
	}
	
	@Test
	public void test2(){
		target.update();
	}
	@Test
	public void test3(){
		target.query();
	}
}
