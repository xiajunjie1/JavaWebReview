package com.maker.UserManger.service;

import java.util.List;

import com.maker.UserManger.domain.Role;
import com.maker.dao.UserDao;

public interface RoleService {
	
	public List<Role> listAll();
	
	public boolean addRole(Role role);
		
	
}
