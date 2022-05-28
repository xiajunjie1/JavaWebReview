package com.maker.UserManger.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.maker.UserManger.dao.RoleDao;
import com.maker.UserManger.domain.Role;
import com.maker.UserManger.service.RoleService;


public class RoleServiceImp implements RoleService {
	private RoleDao rdao;
	@Override
	public List<Role> listAll() {
		// TODO Auto-generated method stub
		List<Role> rlist=new ArrayList<>();
		try {
			rlist= rdao.doQueryAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rlist;
	}
	public void setRdao(RoleDao rdao) {
		this.rdao = rdao;
	}
	@Override
	public boolean addRole(Role role) {
		// TODO Auto-generated method stub
		if(role==null || role.getRoleName()==null||"".equals(role.getRoleName())){
			System.err.println("参数出错！");
			return false;
		}
		try {
			this.rdao.doCreate(role);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}
