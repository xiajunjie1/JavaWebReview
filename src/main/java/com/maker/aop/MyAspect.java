package com.maker.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {
	
	public void before(){
		System.out.println("前置增强");
	}
	
	public void afterReturning(){
		System.out.println("后置增强");
	}
	/*
	 * 环绕增强
	 * ProceedingJoinPoint:正在执行的连接点-切点
	 * */
	public Object round(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("环绕增强，前");
		Object obj=pjp.proceed();//切点方法
		System.out.println("环绕增强，后");
		return obj;
	}
	/*
	 * 抛出异常后，执行该增强方法
	 * */
	public void afterThrow(){
		System.out.println("异常后增强");
	}
	/*
	 * 最终增强后后置增强的区别：
	 * 	如果在执行切点方法的时候，抛出了异常，那么后置增强是不会执行的，而最终增强还是会最后执行
	 * 	若最终增强和环绕增强、后置增强都执行，那么最终增强先执行，环绕后置增强再执行，最后执行后置增强
	 * 	若最终增强和抛出异常后增强都执行，那么最终增强先执行
	 * */
	public void after(){
		System.out.println("最终增强");
	}
	
	
}
