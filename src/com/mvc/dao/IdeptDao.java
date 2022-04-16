package com.mvc.dao;

import com.mvc.commons.ibase.IbaseDao;
import com.mvc.vo.Dept;

public interface IdeptDao extends IbaseDao<String, Dept> {
	public void addBach()throws Exception;
}
