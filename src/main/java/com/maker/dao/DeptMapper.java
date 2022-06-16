package com.maker.dao;

import java.util.List;

import com.maker.domain.Dept;

public interface DeptMapper {
	
	public List<Dept> findAllSplit()throws Exception;
}
