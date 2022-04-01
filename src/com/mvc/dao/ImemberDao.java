package com.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import com.mvc.commons.ibase.IbaseDao;
import com.mvc.vo.Member;

/**
 * 针对特定的数据库表创建的dao接口，它需要继承IbaseDao，并传入ID和VO的类型
 * 可以在该接口中，对该数据表所有的特定操作进行操作方法的扩充
 * 
 * 公共操作直接写在IbaseDao接口中，而个性化的数据操作写在本接口（或其他数据操作子接口）中
 * */
public interface ImemberDao extends IbaseDao<String,Member> {
	
	/*
	 * 根据email地址获取member信息
	 * @param email:要查询的email
	 * @return 如果查询到相应的数据则封装成VO对象进行返回，未查到返回null
	 * @throws 该方法进行的数据库操作，所以异常为SQLEXception
	 * @tip 该方法为该接口特有的方法，而不是声明在base接口中的公共操作方法
	 * */
	
	public Member findByEmail(String email)throws SQLException;
}
