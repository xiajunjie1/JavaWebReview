package com.maker.UserManger.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.maker.UserManger.dao.RoleDao;
import com.maker.UserManger.dao.UserDao;
import com.maker.UserManger.domain.Role;
import com.maker.UserManger.domain.User;
import com.maker.UserManger.service.UserService;

public class UserServiceImp implements UserService {
	private UserDao udao;
	private RoleDao rdao;
	
	@Override
	public List<User> listuser() {
		// TODO Auto-generated method stub
		List<User> ulist=new ArrayList<>();
		try {
			ulist=udao.doQueryAll();
			
			for(User u : ulist){
				List<Integer>  ids=udao.getrole(u.getId());
				List<Role> roles=new ArrayList<Role>();
					for(Integer id: ids){
						 roles.add(rdao.doQueryById(id));
					}
					u.setRole(roles);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ulist;
	}
	
	
	@Override
	public boolean saveUser(User user,Integer[] rids) {
		// TODO Auto-generated method stub
		if(user==null || user.getUsername()==null || "".equals(user.getUsername())){
			return false;
		}
		
		if(rids==null){
			rids=new Integer[0];
		}
		try {
			
			Long uid=udao.doCreateGetId(user);
			if(uid<1)return false;
			for(Integer rid:rids){
				 if(udao.doCreateuser_role(uid, rid)<1)return false;
			}
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public UserDao getUdao() {
		return udao;
	}

	public void setUdao(UserDao udao) {
		this.udao = udao;
	}

	public RoleDao getRdao() {
		return rdao;
	}

	public void setRdao(RoleDao rdao) {
		this.rdao = rdao;
	}


	@Override
	public boolean delUser(Long id) {
		// TODO Auto-generated method stub
		if(id==null||id==0L)return false;
		try {
			return this.udao.doDelete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public User Login(String username, String password) {
		User user=null;
		if(username==null || "".equals(username)){
			return user;
		}
		try {
			user=this.udao.findByUnameAndPwd(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}



}
