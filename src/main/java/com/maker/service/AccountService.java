package com.maker.service;

import java.util.List;

import com.maker.bean.Account;

public interface AccountService {
	public void transfer(String outname,String inname,Double money)throws Exception;
	public List<Account> findall()throws Exception;
}
