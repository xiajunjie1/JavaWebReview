package com.maker.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.maker.dao.SysUserMapper;
import com.maker.dao.UserMapper;
import com.maker.domain.SysUser;
import com.maker.domain.User;
/**
 * 多对多开发模型测试
 * */
public class ServiceDemo6 {
	public static void main(String[] args){
		try{InputStream stream=Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(stream);
		SqlSession session=factory.openSession();
		SysUserMapper user=session.getMapper(SysUserMapper.class);
		List<SysUser> ulist=user.findAll();
		System.out.println(ulist);
	}catch(Exception e){
		e.printStackTrace();
	}
		
	}
}
