package com.maker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.maker.domain.Order;
import com.maker.domain.User;
/**
 * 基于注解的一对多的开发
 * */
public interface UserMapperAnno {
	@Select("Select * from user where id=#{id}")
	public User findById(Integer id)throws Exception;
	public User findOne(Integer id)throws Exception;
	
	@Select("Select * from user")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="password",property="password"),
		@Result(
				property="orders",
				column="id",
				javaType=List.class,//需要接收多个结果
				many=@Many(select="com.maker.dao.OrderMapperAnno.getOrdersByUid")
				)
	})
	public List<User> findAll()throws Exception;
	
}
