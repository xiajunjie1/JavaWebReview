package com.maker.proxy;
/**
 * 增强类，对目标类中的方法进行增强
 * */
public class Advice {
	public void before(){
		System.out.println("前置增强");
		
	}
	
	public void after(){
		System.out.println("后置增强");
	}
}
