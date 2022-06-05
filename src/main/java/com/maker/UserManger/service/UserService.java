package com.maker.UserManger.service;

import java.util.List;

import com.maker.UserManger.dao.UserDao;
import com.maker.UserManger.domain.User;

public interface UserService {
	
	public List<User> listuser();
	public boolean saveUser(User user,Integer[] rids);
	public boolean delUser(Long id);
	public User Login(String username,String password);
}
