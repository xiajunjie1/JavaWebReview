package com.maker.service.impl;

import org.springframework.stereotype.Service;

import com.maker.exception.myException;
import com.maker.service.Eservice;
@Service("Eservice")
public class EserviceImp implements Eservice {
	/*
	 * 通用异常
	 * */
	@Override
	public void CommonsException() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception();

	}
	
	/*
	 * 空指针异常
	 * */
	@Override
	public void NpointerException() throws Exception {
		// TODO Auto-generated method stub
		String str=null;
		System.out.println(str.indexOf(1));
	}
	/*
	 * 数字转型异常
	 * */
	@Override
	public void NumFormatException() throws Exception {
		// TODO Auto-generated method stub
		String str="hello";
		Integer.parseInt(str);

	}
	
	/*
	 * 自定义异常
	 * */
	@Override
	public void MyException() throws Exception {
		// TODO Auto-generated method stub
		throw new myException();
	}

}
