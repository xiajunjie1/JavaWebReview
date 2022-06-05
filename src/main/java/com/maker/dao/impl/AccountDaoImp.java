package com.maker.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.maker.dao.AccountDao;

public class AccountDaoImp implements AccountDao {
	
	private JdbcTemplate jtemplate;

	@Override
	public void out(String name, Double money) throws Exception {
		String sql="update account set money=money-? where name=?";
		int row=this.jtemplate.update(sql,money,name);
		System.out.println("影响行数："+row);
	}

	@Override
	public void in(String name, Double money) throws Exception {
		String sql="update account set money=money+? where name=?";
		int row=this.jtemplate.update(sql,money,name);
		System.out.println("影响行数："+row);

	}

	public JdbcTemplate getJtemplate() {
		return jtemplate;
	}

	public void setJtemplate(JdbcTemplate jtemplate) {
		this.jtemplate = jtemplate;
	}

}
