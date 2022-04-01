package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Util {
	private final static String Driver="com.mysql.cj.jdbc.Driver";
	private final static String url="jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
	private final static String uname="root";
	private final static String pwd="123456";
	private final static ThreadLocal<Connection> tl=new ThreadLocal<>();
	
	public static Connection getConn(){
		Connection con=tl.get();
		if(con==null){
			con=rebuild();
		}
		return con;
	}
	
	public static Connection rebuild(){
		Connection con=null;
		try{
			Class.forName(Driver);
			con=DriverManager.getConnection(url,uname,pwd);
			tl.set(con);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return con;
		}
	
	
	public static void closeConn(){
		Connection con=tl.get();
		if(con!=null){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		tl.remove();
	}
}
