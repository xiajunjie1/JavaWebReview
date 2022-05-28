package com.maker.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maker.config.SpringConfig;
import com.maker.view.UserController;

@RunWith(SpringJUnit4ClassRunner.class)//指定用Spring来运行Junit
@ContextConfiguration("classpath:applicationContext.xml")//指定Spring配置文件
//@ContextConfiguration(classes={SpringConfig.class})//指定Spring配置类
public class SpringJunitTest {
	@Autowired
	@Qualifier("usercontroller")
	private UserController uc;
	
	@Test
	public void test(){
		uc.add();
	}
}
