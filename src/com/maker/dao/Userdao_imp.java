package com.maker.dao;

import com.maker.database.DB_Util;
import java.sql.*;

public class Userdao_imp {
	
	public User getUser(String username){
		User u=null;
		Connection con=DB_Util.getConn();
		String sql="Select id,username,password,nickname from user where username=?";
		PreparedStatement pstat=null;
		ResultSet rs=null;
		try {
			pstat=con.prepareStatement(sql);
			pstat.setString(1, username);
			rs=pstat.executeQuery();
			if(rs.next()){
				u=new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setNickname(rs.getString("nickname"));
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}
}
