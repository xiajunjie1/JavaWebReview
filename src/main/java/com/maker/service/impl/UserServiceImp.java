package com.maker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.maker.dao.UserDao;
import com.maker.service.UserService;

//@Component("uservice")
//可以用以下注解替代，一般代表Service层
@Service("uservice")
public class UserServiceImp implements UserService {
	//进行依赖注入
	@Autowired
	//此处填写bean的id值
	@Qualifier("userdao")
	private UserDao udao;
	@Override
	public void UserSave() throws Exception {
		// TODO Auto-generated method stub
		udao.save();
	}

}
