package com.mvc.service;

import java.util.Map;

import com.mvc.dao.IdeptDao;

public interface IdeptService {

	public void addBatch()throws Exception;
	
	public Map<String,Object> split(int currentpage,int pagesize)throws Exception;
	public Map<String,Object> split(int currentpage,int pagesize,String colum,String keyword)throws Exception;
}
