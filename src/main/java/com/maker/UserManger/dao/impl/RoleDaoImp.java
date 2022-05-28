package com.maker.UserManger.dao.impl;

import java.lang.annotation.Annotation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.maker.UserManger.dao.RoleDao;
import com.maker.UserManger.domain.Role;

public class RoleDaoImp implements RoleDao {
	
	private JdbcTemplate jtemplate;

	@Override
	public boolean doCreate(Role vo) throws Exception {
		// TODO Auto-generated method stub
		int row=this.jtemplate.update("insert into sys_role(roleName,roleDesc) values(?,?)",vo.getRoleName(),vo.getRoleDesc());
		
		return row>0;
	}

	@Override
	public boolean doUpdate(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doDelete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public List<Role> doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		String sql="Select id,roleName,roleDesc from sys_role";
		List<Role> list=this.jtemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
		return list;
	}

	@Override
	public Role doQueryById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String sql="Select * from sys_role where id=?";
		Role role=this.jtemplate.queryForObject(sql, new BeanPropertyRowMapper<Role>(Role.class),id);
		return role;
	}

	@Override
	public List<Role> findSplit(Integer curpage, Integer pagesize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findSplit(Integer curpage, Integer pagesize, String colum, String keyword) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public JdbcTemplate getJtemplate() {
		return jtemplate;
	}

	public void setJtemplate(JdbcTemplate jtemplate) {
		this.jtemplate = jtemplate;
	}

}
