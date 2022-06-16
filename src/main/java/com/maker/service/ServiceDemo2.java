package com.maker.service;

import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.maker.dao.UserMapper;
import com.maker.domain.User;
/**
 * 类型处理器测试
 * */
public class ServiceDemo2 {
	public static void main(String[] args){
		try{InputStream stream=Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(stream);
		SqlSession session=factory.openSession();
		UserMapper user=session.getMapper(UserMapper.class);
		User u=new User();
		u.setName("abc");
		u.setPassword("567");
		//u.setBirthday(new Date());
		user.save(u);
		
		System.out.println("【查询数据库】："+user.findAll());
		session.commit();
		session.close();
	}catch(Exception e){
		e.printStackTrace();
	}
		
	}
}
