package com.maker.service;

import java.util.List;

import com.maker.domain.Account;

public interface AccountService {
	public List<Account> listall()throws Exception;
	public void save(Account ac)throws Exception;
	public void update(Account ac)throws Exception;
	public void delete(String name)throws Exception;
}
