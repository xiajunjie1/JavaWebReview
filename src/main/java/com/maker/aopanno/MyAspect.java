package com.maker.aopanno;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("myAspect2")
@Aspect//标注当前的类是一个切面类
public class MyAspect {
	//配置前置增强，在该注解中指定对哪些切点进行前置增强
	@Before("execution(* com.maker.aopanno.*.*(..))")
	public void before(){
		System.out.println("before show...");
	}
	
	//@AfterReturning("MyAspect.pointcut()")//引用抽取的切点表达式
	@AfterReturning("pointcut()")
	public void afterReturning(){
		System.out.println("after show...");
	}
	
	//切点表达式的抽取
	@Pointcut("execution(* com.maker.aopanno.*.*(..))")
	public void pointcut(){}
}
