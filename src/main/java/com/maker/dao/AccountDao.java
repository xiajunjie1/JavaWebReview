package com.maker.dao;

import java.util.List;

import com.maker.domain.Account;

public interface AccountDao {
	public List<Account> findAll()throws Exception;
	public void save(Account ac)throws Exception;
	public void update(Account ac)throws Exception;
	public void delete(String name)throws Exception;
}
