package com.mvc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.database.DB_Util;
import com.mvc.dao.IsitesDao;
import com.mvc.vo.Sites;

public class SitesDaoImp implements IsitesDao {
	private Connection con=DB_Util.getConn();
	private PreparedStatement pstmt=null;
	@Override
	public boolean doCreate(Sites vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Sites vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<String> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sites findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sites> findAll() throws SQLException {
		// TODO Auto-generated method stub
		String sql="Select id,name,url from sites";
		List<Sites> slist=new ArrayList<>();
		this.pstmt=this.con.prepareStatement(sql);
		ResultSet rs=this.pstmt.executeQuery();
		while(rs.next()){
			Sites sit=new Sites();
			sit.setId(rs.getInt(1));
			sit.setName(rs.getString(2));
			sit.setUrl(rs.getString(3));
			slist.add(sit);
		}
		return slist;
	}

	@Override
	public List<Sites> findSplit(Integer curpage, Integer pagesize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sites> findSplit(Integer curpage, Integer pagesize, String colum, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount(String colum, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
