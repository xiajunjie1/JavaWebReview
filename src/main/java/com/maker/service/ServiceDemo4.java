package com.maker.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maker.dao.DeptMapper;
import com.maker.dao.OrderMapper;
import com.maker.domain.Dept;
import com.maker.domain.Order;
/**
 * 一对一开发模型测试
 * */
public class ServiceDemo4 {
	public static void main(String[] args){
		try {
			InputStream stream=Resources.getResourceAsStream("sqlMapConfig.xml");
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(stream);
			SqlSession session=factory.openSession();
			OrderMapper oMapper=session.getMapper(OrderMapper.class);
			List<Order> olist=oMapper.findAll();
			System.out.println("【获得结果】"+olist);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
