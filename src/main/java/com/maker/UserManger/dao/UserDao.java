package com.maker.UserManger.dao;

import java.util.List;

import com.maker.UserManger.common.IbaseDao;
import com.maker.UserManger.domain.User;

public interface UserDao extends IbaseDao<Long, User> {
	public List<Integer> getrole(Long uid)throws Exception;
	
	public Long doCreateGetId(User user)throws Exception;
	
	public int doCreateuser_role(Long uid,Integer rid)throws Exception;
	
	public User findByUnameAndPwd(String username,String password)throws Exception;
}
