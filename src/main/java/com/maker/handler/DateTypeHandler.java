package com.maker.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
/**
 * 该类的作用是将java的Date类转成转成Long类型在存到数据库中的对应字段
 * 	即birthday字段，在Java的VO对象中是date类型，而在数据库对应的是bigint（long）类型
 * 该类继承了一个Mybatis提供的BaseTypeHandler类
 * 	
 * */
public class DateTypeHandler extends BaseTypeHandler<Date> {

	/*
	 * 将数据库中获取到的字段，转成Date类型
	 * @param rs为查询到的数据集
	 * @param s 列名称
	 * */
	@Override
	public Date getNullableResult(ResultSet rs, String s) throws SQLException {
		// TODO Auto-generated method stub
		Long time=rs.getLong(s);
		Date date=new Date(time);
		return date;
	}
	
	/*
	 * 将数据库中获取到的字段，转成Date类型
	 * @param rs为查询到的数据集
	 * @param index 列的索引
	 * */
	@Override
	public Date getNullableResult(ResultSet rs, int index) throws SQLException {
		// TODO Auto-generated method stub
		Long time=rs.getLong(index);
		Date date=new Date(time);
		return date;
		
	}
	
	/*
	 * 将数据库中获取到的字段，转成Date类型
	 * @param callableStatement为Statement对象
	 * @param index 列索引
	 * */
	@Override
	public Date getNullableResult(CallableStatement callableStatement, int index) throws SQLException {
		// TODO Auto-generated method stub
		Long time=callableStatement.getLong(index);
		Date date=new Date(time);
		return date;
	}
	/*
	 * 将Java的数据，转成Date类型
	 * @param pstmt statement对象
	 * @param index 数据库中字段的位置
	 * @param date 需要转成数据库字段类型的java数据
	 * */
	@Override
	public void setNonNullParameter(PreparedStatement pstmt, int index, Date date, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		pstmt.setLong(index, date.getTime());
		
	}

}
