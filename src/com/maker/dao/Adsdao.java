package com.maker.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.maker.database.DB_Util;

public class Adsdao {
	
	public List<Ads> listad(){
		List<Ads> list=null;
		try{
		Connection con=DB_Util.getConn();
		String sql="Select aid,title,url,photo,note from ad";
		PreparedStatement pstat=con.prepareStatement(sql);
		ResultSet rs=pstat.executeQuery();
		list=new ArrayList<Ads>();
		while(rs.next()){
			Ads ads=new Ads();
			ads.setAid(rs.getInt(1));
			ads.setTitle(rs.getString(2));
			ads.setUrl(rs.getString(3));
			ads.setPhoto(rs.getString(4));
			ads.setNote(rs.getString(5));
			list.add(ads);
		}
		rs.close();
		pstat.close();
		DB_Util.closeCon();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public int add(Ads ads){
		int result=-1;
		try{
			Connection con=DB_Util.getConn();
			String sql="insert into ad values(null,?,?,?,?)";
			PreparedStatement pstat=con.prepareStatement(sql);
			pstat.setString(1, ads.getTitle());
			pstat.setString(2, ads.getUrl());
			pstat.setString(3, ads.getPhoto());
			pstat.setString(4, ads.getNote());
			 result=pstat.executeUpdate();
			 pstat.close();
			 DB_Util.closeCon();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	public Ads getAdsByid(int id){
		Ads ads=new Ads();
		try{
		Connection con=DB_Util.getConn();
		String sql="Select aid,title,url,photo,note from ad where aid=?";
		PreparedStatement pstat=con.prepareStatement(sql);
		pstat.setInt(1, id);
		ResultSet rs=pstat.executeQuery();
		if(rs.next()){
			ads.setAid(rs.getInt(1));
			ads.setTitle(rs.getString(2));
			ads.setUrl(rs.getString(3));
			ads.setPhoto(rs.getString(4));
			ads.setNote(rs.getString(5));
		}
		rs.close();
		pstat.close();
		DB_Util.closeCon();
		}catch(Exception e){
			e.printStackTrace();
		}
		return ads;
	}
	
	public int deleteAdByid(int id){
		int result=-1;
		try{
			Connection con=DB_Util.getConn();
			String sql="delete from ad where aid=?";
			PreparedStatement pstat=con.prepareStatement(sql);
			pstat.setInt(1, id);
			 result=pstat.executeUpdate();
			pstat.close();
			DB_Util.closeCon();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateAdByid(int id,Ads ad){
		int result=-1;
		try{
			Connection con=DB_Util.getConn();
			String sql="Update ad set title=?,url=?,note=? where aid=?";
			PreparedStatement pstat=con.prepareStatement(sql);
			pstat.setString(1, ad.getTitle());
			pstat.setString(2, ad.getUrl());
			pstat.setString(3, ad.getNote());
			pstat.setInt(4, id);
			 result=pstat.executeUpdate();
			pstat.close();
			DB_Util.closeCon();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
