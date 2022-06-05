package com.maker.dao;

public interface AccountDao {
	public void out(String name,Double money)throws Exception;
	public void in(String name,Double money)throws Exception;
}
