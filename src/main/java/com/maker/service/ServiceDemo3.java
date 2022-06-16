package com.maker.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maker.dao.DeptMapper;
import com.maker.domain.Dept;
/**
 * 分页助手测试
 * */
public class ServiceDemo3 {
	public static void main(String[] args){
		try {
			InputStream stream=Resources.getResourceAsStream("sqlMapConfig.xml");
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(stream);
			SqlSession session=factory.openSession();
			DeptMapper deptMapper=session.getMapper(DeptMapper.class);
			//设置分页
			PageHelper phelper=new PageHelper();
			int pageNum=2;//当前页数
			int pageSize=5;//当前页显示条数
			phelper.startPage(pageNum, pageSize);
			List<Dept> dlist=deptMapper.findAllSplit();
			for(Dept d:dlist){
				System.out.println("【查询结果】:"+d);
			}
			
		//获取当前的分页信息
			PageInfo<Dept> pinfo =new PageInfo<>(dlist);
			System.out.println("当前页："+pinfo.getPageNum());
			System.out.println("显示条数："+pinfo.getSize());
			System.out.println("总页数："+pinfo.getTotal());
			System.out.println("上一页："+pinfo.getPrePage());
			System.out.println("下一页："+pinfo.getNextPage());
			System.out.println("是否为首页："+pinfo.isIsFirstPage());
			System.out.println("是否为尾页："+pinfo.isIsLastPage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
