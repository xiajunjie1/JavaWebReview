package com.maker.dao.impl;

import javax.xml.ws.RespectBinding;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.maker.dao.UserDao;
//Component注解，用来代替bean标签
//@Component("userdao")
//可以用以下的注解来代替，功能一样，一般表示dao层
@Repository("userdao")
public class UserDaoImp implements UserDao {

	@Override
	public void save() throws Exception {
		System.out.println("【save方法执行】");

	}

}
