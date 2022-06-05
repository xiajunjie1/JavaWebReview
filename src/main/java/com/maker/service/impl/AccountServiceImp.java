package com.maker.service.impl;

import java.util.List;

import com.maker.bean.Account;
import com.maker.dao.AccountDao;
import com.maker.service.AccountService;

public class AccountServiceImp implements AccountService{

	private AccountDao dao;
	@Override
	public void transfer(String outname, String inname, Double money) throws Exception {
		// TODO Auto-generated method stub
		if(outname==null||inname==null||money==null||"".equals(outname)||"".equals(inname)||money<0){
			System.err.println("参数不合法");
			//throw new Exception();
			return;
		}
		dao.out(outname, money);
		int i=10/0;//抛出异常，若不开启事务上面账户的钱减少了，下面账户的钱不会增加
		dao.out(inname,money);
	}
	
	public void setDao(AccountDao dao){
		this.dao=dao;
	}

	@Override
	public List<Account> findall() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
