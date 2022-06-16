package com.maker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.maker.domain.Account;

public interface AccountMapperAnno {
	@Insert("insert into account Values(#{name},#{money})")
	public void insert(Account ac)throws Exception;
	@Update("update account set money=#{money} where name=#{name}")
	public void update(Account ac)throws Exception;
	@Delete("delete from account where name=#{name}")
	public void delete(String name)throws Exception;
	@Select("Select * from account ")
	public List<Account> findAll()throws Exception;
	@Select("Select * from account where name=#{name}")
	public Account getOne(String name)throws Exception;
	
}
