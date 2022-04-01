package com.mvc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mvc.commons.abs.AbstractDao;
import com.mvc.commons.annotation.Repository;
import com.mvc.dao.ImemberDao;
import com.mvc.vo.Member;
/**
 * 接口实例类
 * 	实现子接口，完成对数据表的具体操作
 * */
@Repository//此为数据层
public class MemberDaoImp extends AbstractDao implements ImemberDao {
	//在抽象父类中，已经定义了connection属性，并且在构造方法中已经获取了connection对象
	
	@Override
	public boolean doCreate(Member member) throws SQLException {
		String sql="insert into member(mid,name,age,email,sex,birthday,note)Values(?,?,?,?,?,?,?)";
		super.pstmt=super.con.prepareStatement(sql);
		super.pstmt.setString(1, member.getMid());
		super.pstmt.setString(2, member.getName());
		super.pstmt.setInt(3, member.getAge());
		super.pstmt.setString(4, member.getEmail());
		super.pstmt.setString(5, member.getSex());
		super.pstmt.setDate(6, new java.sql.Date(member.getBirthday().getTime()));
		super.pstmt.setString(7, member.getNote());
			
		
		return super.pstmt.executeUpdate()>0;//有更新记录行数的返回
	}

	@Override
	public boolean doEdit(Member member) throws SQLException {
		String sql="Update member set name=?,age=?,email=?,sex=?,birthday=?,note=? where mid=?";
		super.pstmt=super.con.prepareStatement(sql);
		
		super.pstmt.setString(1, member.getName());
		super.pstmt.setInt(2, member.getAge());
		super.pstmt.setString(3, member.getEmail());
		super.pstmt.setString(4, member.getSex());
		super.pstmt.setDate(5, new java.sql.Date(member.getBirthday().getTime()));
		super.pstmt.setString(6, member.getNote());
		super.pstmt.setString(7, member.getMid());
		return super.pstmt.executeUpdate()>0;//有更新记录行数的返回
	}

	@Override
	public boolean doRemove(Set<String> ids) throws SQLException {
		StringBuffer sql=new StringBuffer(30);
		sql.append("Delete from member where mid in (");
		Iterator<String> it=ids.iterator();
		while(it.hasNext()){
			it.next();
			if(it.hasNext()){
			sql.append("?,");
			}else{
				sql.append("?)");
			}
		}
		System.out.println(sql);
		super.pstmt=super.con.prepareStatement(sql.toString());
		it=ids.iterator();
		int foot=1;
		while(it.hasNext()){
			
			super.pstmt.setString(foot++, it.next());
		}
		
		return super.pstmt.executeUpdate()==ids.size();
	}

	@Override
	public Member findById(String id) throws SQLException {
		String sql="Select mid,name,age,email,sex,birthday,note from member where mid =?";
		super.pstmt=super.con.prepareStatement(sql);
		super.pstmt.setString(1, id);
		ResultSet rs=super.pstmt.executeQuery();
		Member m=null;
		while(rs.next()){
			m=new Member();
			m.setMid(rs.getString(1));
			m.setName(rs.getString(2));
			m.setAge(rs.getInt(3));
			m.setEmail(rs.getString(4));
			m.setSex(rs.getString(5));
			m.setBirthday(new Date(rs.getDate(6).getTime()));
			m.setNote(rs.getString(7));
		}
		return m;
	}

	@Override
	public List<Member> findAll() throws SQLException {
		String sql="Select mid,name,age,email,sex,birthday,note from member";
		super.pstmt=super.con.prepareStatement(sql);
		List<Member> list=new ArrayList<>();
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()){
			Member m=new Member();
			m.setMid(rs.getString(1));
			m.setName(rs.getString(2));
			m.setAge(rs.getInt(3));
			m.setEmail(rs.getString(4));
			m.setSex(rs.getString(5));
			m.setBirthday(new Date(rs.getDate(6).getTime()));
			m.setNote(rs.getString(7));
			list.add(m);
		}
		return list;
	}

	@Override
	public List<Member> findSplit(Integer curpage, Integer pagesize) throws SQLException {
		String sql="Select mid,name,age,email,sex,birthday,note from member limit ?,?";
		super.pstmt=super.con.prepareStatement(sql);
		super.pstmt.setInt(1, (curpage-1)*pagesize);
		super.pstmt.setInt(2, pagesize);
		List<Member> list=new ArrayList<>();
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()){
			Member m=new Member();
			m.setMid(rs.getString(1));
			m.setName(rs.getString(2));
			m.setAge(rs.getInt(3));
			m.setEmail(rs.getString(4));
			m.setSex(rs.getString(5));
			m.setBirthday(new Date(rs.getDate(6).getTime()));
			m.setNote(rs.getString(7));
			list.add(m);
		}
		return list;
	}

	@Override
	public List<Member> findSplit(Integer curpage, Integer pagesize, String colum, String keyword) throws SQLException {
		String sql="Select mid,name,age,email,sex,birthday,note from member where "+colum+" like ? limit ?,?";
		super.pstmt=super.con.prepareStatement(sql);
		super.pstmt.setString(1, "%"+keyword+"%");
		super.pstmt.setInt(3, (curpage-1)*pagesize);
		super.pstmt.setInt(4, pagesize);
		List<Member> list=new ArrayList<>();
		ResultSet rs=super.pstmt.executeQuery();
		while(rs.next()){
			Member m=new Member();
			m.setMid(rs.getString(1));
			m.setName(rs.getString(2));
			m.setAge(rs.getInt(3));
			m.setEmail(rs.getString(4));
			m.setSex(rs.getString(5));
			m.setBirthday(new Date(rs.getDate(6).getTime()));
			m.setNote(rs.getString(7));
			list.add(m);
		}
		return list;	}

	@Override
	public Long getAllCount() throws SQLException {
		String sql="Select count(*) from member";
		super.pstmt=super.con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;//无记录返回0
	}

	@Override
	public Long getAllCount(String colum, String keyword) throws SQLException {
		String sql="Select count(*) from member where "+colum+" like ?";
		super.pstmt=super.con.prepareStatement(sql);
		super.pstmt.setString(1, keyword);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getLong(1);
		}
		return 0L;//无记录返回0
	}

	@Override
	public Member findByEmail(String email) throws SQLException {
		String sql="Select mid,name,age,email,sex,birthday,note from member where email =?";
		super.pstmt=super.con.prepareStatement(sql);
		super.pstmt.setString(1, email);
		ResultSet rs=super.pstmt.executeQuery();
		Member m=null;
		while(rs.next()){
			m=new Member();
			m.setMid(rs.getString(1));
			m.setName(rs.getString(2));
			m.setAge(rs.getInt(3));
			m.setEmail(rs.getString(4));
			m.setSex(rs.getString(5));
			m.setBirthday(new Date(rs.getDate(6).getTime()));
			m.setNote(rs.getString(7));
		}
		return m;
	}

}
