package com.maker.database;
import java.sql.*;
/**
 * JavaBean是一个Java程序，进行代码的可重用设计
 * 通过这个自定义的程序类，通过这个自定义的程序类可以实现一些程序功能包装，但是如果想要在一个WEB项目中使用JavaBean
 * 那么对于程序的存储是有要求的，所有的JavaBean必须编译为“*.class”文件（没有包的Bean是不存在的），并且必须保存在
 * “WEB项目/WEB-INF/classes”目录之中
 * 
 * */
public class DB_Util {
private final static String Driver="com.mysql.cj.jdbc.Driver";
private final static String url="jdbc:mysql://localhost:3306/chat?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
private final static String uname="root";
private final static String pwd="123456";
private final static ThreadLocal<Connection> tl=new ThreadLocal<>();

public static Connection getConn(){
	Connection con=tl.get();
	if(con!=null)return con;
	else{
		con=rebuildCon();
		return con;
	}
	
}

private static Connection rebuildCon(){
	Connection con=null;
	try {
		Class.forName(Driver);
		con=DriverManager.getConnection(url, uname, pwd);
		tl.set(con);
		return con;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
}

public static void closeCon(){
	Connection con=tl.get();
	if(con!=null){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}tl.remove();
}
}
