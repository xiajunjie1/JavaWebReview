package com.database;

import java.sql.Connection;

public class Test {
	public static void main(String[] args){
		Connection con=DB_Util.getConn();
		System.out.println(con);
	}
}
