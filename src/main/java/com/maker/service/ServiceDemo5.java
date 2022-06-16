package com.maker.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.maker.dao.UserMapper;
import com.maker.domain.User;
/**
 * 一对多开发模型测试
 * */
public class ServiceDemo5 {
	public static void main(String[] args){
		try{InputStream stream=Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(stream);
		SqlSession session=factory.openSession();
		UserMapper user=session.getMapper(UserMapper.class);
		List<User> ulist=user.findAll2();
		System.out.println(ulist);
	}catch(Exception e){
		e.printStackTrace();
	}
		
	}
}
