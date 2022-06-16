package com.maker.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.maker.domain.Order;
import com.maker.domain.User;

/**
 * 用注解的方式实现一对一查询
 * */
public interface OrderMapperAnno {
	/*
	 * 一对一查询方式1
	 * */
	@Select("Select * from orders o,user u where o.uid=u.id")
	@Results({
		@Result(column="oid",property="oid"),
		@Result(column="ordertime",property="ordertime"),
		@Result(column="total",property="total"),
		@Result(column="uid",property="user.id"),
		@Result(column="name",property="user.name")
	})
	/*
	 * 一对一查询，封装方式二
	 * */
	public List<Order> getAll()throws Exception;
	@Select("Select * from orders")
	@Results(
			{
				@Result(column="oid",property="oid"),
				@Result(column="ordertime",property="ordertime"),
				@Result(column="total",property="total"),
				@Result(
						property="user",//要封装的属性名称
						column="uid",//根据哪个字段去查询user表
						javaType=User.class,//要封装的实体类型
						//select属性 代表查询那个接口的方法获得数据
						one=@One(select="com.maker.dao.UserMapperAnno.findById")
				)
			}
			)
	public List<Order> getAll2()throws Exception;
	public Order getOne(Long oid)throws Exception;
	
	@Select("Select * from orders where uid=#{uid}")
	public List<Order> getOrdersByUid(Integer uid)throws Exception;
	
}
