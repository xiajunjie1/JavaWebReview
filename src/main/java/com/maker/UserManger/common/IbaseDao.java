package com.maker.UserManger.common;

import java.util.List;

public interface IbaseDao<ID,VO> {
	
	public boolean doCreate(VO vo)throws Exception;
	
	public boolean doUpdate(ID id)throws Exception;
	
	public boolean doDelete(ID id)throws Exception;
	
	public List<VO> doQueryAll()throws Exception;
	
	public VO doQueryById(ID id)throws Exception;
	
	public List<VO> findSplit(Integer curpage,Integer pagesize)throws Exception;
	
	public List<VO> findSplit(Integer curpage,Integer pagesize,String colum,String keyword)throws Exception;
	
	public Long getCount()throws Exception;
}
