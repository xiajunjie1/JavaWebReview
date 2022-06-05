package com.maker.aop;
/**
 * 目标类
 * */
public class TestService {
	/*
	 * 切点
	 * */
	public void save(){
		System.out.println("目标方法执行save...");
	}
	
	public void update(){
		System.out.println("执行update方法...");
		int i=10/0;//产生异常
	}
	
	public void query(){
		System.out.println("执行query方法...");
	}
}
