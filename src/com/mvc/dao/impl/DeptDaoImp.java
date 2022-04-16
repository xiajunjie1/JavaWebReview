package com.mvc.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.database.DB_Util;
import com.mvc.commons.abs.AbstractDao;
import com.mvc.commons.annotation.Repository;
import com.mvc.dao.IdeptDao;
import com.mvc.vo.Dept;
@Repository
public class DeptDaoImp extends AbstractDao implements IdeptDao {

	@Override
	public boolean doCreate(Dept vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Dept vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<String> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dept findById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dept> findSplit(Integer curpage, Integer pagesize) throws SQLException {
		// TODO Auto-generated method stub
		String sql="Select deptno,deptname,loc from dept limit ?,?";
		PreparedStatement pstmt=null;
		pstmt=super.con.prepareStatement(sql);
		pstmt.setInt(1, curpage);
		pstmt.setInt(2, pagesize);
		ResultSet rs=pstmt.executeQuery();
		List<Dept> dlist=new ArrayList<>();
		while(rs.next()){
			Dept d=new Dept();
			d.setDeptno(rs.getLong(1));
			d.setDeptname(rs.getString(2));
			d.setLoc(rs.getString(3));
			dlist.add(d);
		}
		return dlist;
	}

	@Override
	public List<Dept> findSplit(Integer curpage, Integer pagesize, String colum, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		String sql="Select deptno,deptname,loc from dept where "+colum+" like ? limit ?,?";
		PreparedStatement pstmt=null;
		pstmt=super.con.prepareStatement(sql);
		pstmt.setString(1, "%"+keyword+"%");
		pstmt.setInt(2, curpage);
		pstmt.setInt(3, pagesize);
		ResultSet rs=pstmt.executeQuery();
		List<Dept> dlist=new ArrayList<>();
		while(rs.next()){
			Dept d=new Dept();
			d.setDeptno(rs.getLong(1));
			d.setDeptname(rs.getString(2));
			d.setLoc(rs.getString(3));
			dlist.add(d);
		}
		return dlist;
	}

	@Override
	public Long getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		String sql="Select count(*) from dept";
		PreparedStatement pstmt=null;
		Long record=0L;
		try{
		pstmt=super.con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			record=rs.getLong(1);
		}
		if(rs!=null){
			rs.close();
		}
		}catch(Exception e){
			System.err.println("获取记录数失败");
			
			if(pstmt!=null){
			pstmt.close();
			}
			e.printStackTrace();
		}
		return record;
	}

	@Override
	public Long getAllCount(String colum, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		if(colum==null || "".equals(colum)|| keyword==null || "".equals(keyword)){
			return getAllCount();
		}
		String sql="Select count(*) from dept where "+colum+" like ?";
		PreparedStatement pstmt=null;
		Long record=0L;
		pstmt=super.con.prepareStatement(sql);
		pstmt.setString(1, "%"+keyword+"%");
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			record=rs.getLong(1);
		}
		if(rs!=null){
			rs.close();
		}
		if(pstmt!=null){
			pstmt.close();
		}
		return record;
	}
	/*
	 * 用来初始化，仅使用一次
	 * */
	@Override
	public void addBach() throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into dept (deptno,deptname,loc) values(?,?,?)";
		PreparedStatement pstmt=super.con.prepareStatement(sql);
		
		for(int x=0;x<1000;x++){
			pstmt.setString(1, String.valueOf(1001+x));
			pstmt.setString(2, "dept-"+x);
			pstmt.setString(3, "wuhan");
			pstmt.addBatch();
		}
		
		System.out.println("增加了："+pstmt.executeBatch());
		pstmt.close();
	}

}
