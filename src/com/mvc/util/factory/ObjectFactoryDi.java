package com.mvc.util.factory;

import java.lang.reflect.Proxy;

import com.mvc.commons.proxy.ServiceProxy;

/**
 * 工厂类，注入对象实例
 * */
public class ObjectFactoryDi {
	//考虑到后续开发中，业务层操作可能很多地方都用得到，所以将其保存在ThreadLocal中
	private static final ThreadLocal<Object> Service_Obj=new ThreadLocal<>();
	private ObjectFactoryDi(){}
	public static <T>T getInstance(Class<T> clazz){
		Object result=null;
		try{
			result=clazz.getDeclaredConstructor().newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return (T)result;
	}
	/*
	 * 获取业务层实例对象，考虑到业务层涉及到事务的操作，所以使用该方法来获取业务层代理类
	 * */
	public static <T>T getServiceInstance(Class<T> clazz){//留给控制层调用
		Object result=null;
		try{
			Service_Obj.set(clazz.getDeclaredConstructor().newInstance());
			ServiceProxy proxy=new ServiceProxy();
			proxy.bind(Service_Obj.get());
			result=Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), proxy);
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("#@#@"+result.getClass().getSimpleName());
		return (T)result;
	}
	
	public static Object getOriginalService(){
		//获取原生的Service对象,留给其他对方使用
		return Service_Obj.get();
	}
}
