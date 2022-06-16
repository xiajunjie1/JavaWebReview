package com.maker.dao;

import java.util.List;

import com.maker.domain.User;

public interface UserMapper {
	
	public void save(User u)throws Exception;
	public List<User> findAll()throws Exception;
	public User findById(Integer id)throws Exception;
	public List<User> findAll2()throws Exception;
}
