package com.maker.dao;

import java.util.List;

import com.maker.domain.Account;

/**
 * dao接口
 * 	利用mybatis代理开发的方式进行Dao层的编写，不需要实现该接口，只需要在配置文件当中配置好相应的接口即可
 * */
public interface AccountMapper {
	public List<Account> getAll()throws Exception;
	public Account getOne(String name)throws Exception;
	public List<Account> getCondition(Account account)throws Exception;
	public List<Account> getConditionByNames(List<String> names)throws Exception;
}
