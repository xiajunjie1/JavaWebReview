package com.mvc.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.mvc.commons.annotation.Aspect;
import com.mvc.commons.annotation.AutoWired;
import com.mvc.commons.annotation.Service;
import com.mvc.dao.IdeptDao;
import com.mvc.service.IdeptService;
@Service
@Aspect
public class DeptServiceImp implements IdeptService {
	@AutoWired
	private IdeptDao deptdao;
	@Override
	public void addBatch() throws Exception {
		// TODO Auto-generated method stub
		deptdao.addBach();
	}
	@Override
	public Map<String, Object> split(int currentpage, int pagesize) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> result=new HashMap<>();
		result.put("allrecord", deptdao.getAllCount());
		result.put("result", deptdao.findSplit(currentpage, pagesize));
		return result;
	}
	@Override
	public Map<String, Object> split(int currentpage, int pagesize, String colum, String keyword) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> result=new HashMap<>();
		if(colum==null || "".equals(colum)|| keyword==null || "".equals(keyword)){
			return split(currentpage,pagesize);
		}else{
			
			result.put("allrecord", deptdao.getAllCount(colum, keyword));
			result.put("result",deptdao.findSplit(currentpage, pagesize, colum, keyword));
		}
		return result;
	}

}
