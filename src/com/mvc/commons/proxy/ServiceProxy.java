package com.mvc.commons.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.database.DB_Util;
/**
 * 利用动态代理，实现切面，从而达到控制业务层事务的目的。
 * 
 * */
public class ServiceProxy implements InvocationHandler {
	//定义需要开启事务的方法的开头标记
	private static final List<String> Transaction_Method_head=Arrays.asList(
			"add","create","edit","update","delete","remove","change","move","Add","Create","Edit","Update","Delete","Remove","change","move"
			);
	
	private Object target;//需要代理的类实例
	public void bind(Object obj){
		this.target=obj;//绑定类实例
	}
	
	/*
	 * 将传入的实例的方法，在此处进行调用
	 * */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		boolean tflag=this.openTransaction(method.getName());//定义是否事务是否需要开启的标志
		Object result=null;//保存了返回结果
		System.out.println("开始判断是否需要开启事务..."+tflag);
		if(tflag){
			//需要开启事务
			DB_Util.getConn().setAutoCommit(false);
			System.out.println("开启事务");
		}
		try{
			//调用被代理类的业务处理方法
			result=method.invoke(this.target, args);
			if(tflag){
				//如果开启了事务，且执行成功，就进行事务的提交
				DB_Util.getConn().commit();
			}
			}catch(Exception e){
				if(tflag){
					//如果开启了事务，出现异常，进行事务的回滚
					DB_Util.getConn().rollback();
				}
				throw e;
			}finally{
				DB_Util.getConn().close();
			}
		return result;
	}
	
	
	/*
	 * 判断调用的方法是否需要开启事务
	 * @param methodname 调用的方法名
	 * @return 需要开启事务返回true，否则返回false
	 * */
	private boolean openTransaction(String methodname){
		Iterator<String> it=this.Transaction_Method_head.iterator();
		while(it.hasNext()){
			if(methodname.startsWith(it.next())){
				return true;//方法名的开头包含定义好的标记
				
			}
		}
		return false;
	}

}
