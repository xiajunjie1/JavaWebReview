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
import com.maker.dao.SysUserMapperAnno;
import com.maker.dao.UserMapperAnno;
import com.maker.domain.Account;
import com.maker.domain.Order;
import com.maker.domain.SysUser;
import com.maker.domain.User;

public class MyBatisAnnoTest4 {
	private SysUserMapperAnno user;
	@Before//此处的Before注解是Junit提供的注解，代表在执行测试之前执行该方法
	public void beforeHandler(){
		try{InputStream stream=Resources.getResourceAsStream("sqlMapConfigAnno.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(stream);
		SqlSession session=factory.openSession(true);//自动提交
		user=session.getMapper(SysUserMapperAnno.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void ManyToManyTest(){
		try {
			List<SysUser> ulist=user.findAll();
			//User u=user.findById(1);
			System.out.println(ulist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
