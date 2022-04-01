package com.mvc.commons.abs;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.database.DB_Util;
/**
 * 数据层操作公共抽象类
 * 
 * */
public abstract class AbstractDao {
	//该类是一个不完整的工具类
	//以下属性作为公共属性，所有继承了的子类都有该属性
	protected Connection con;
	protected PreparedStatement pstmt;
	
	public AbstractDao(){
		//抽象类的构造器，是子类实例化时调用的，抽象类并不能被直接实例化，所以该类没办法被直接调用
		this.con=DB_Util.getConn();//获取数据库连接
		
	}
}
