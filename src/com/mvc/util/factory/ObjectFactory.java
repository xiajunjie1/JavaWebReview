package com.mvc.util.factory;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

import com.mvc.commons.proxy.ServiceProxy;

/**
 * 工厂类，用来生成各种dao接口实例
 * 	为了统一管理各个数据表的子接口dao，可以采用properties资源文件的形式进行配置
 * 	如果项目中要用到某一个dao实例对象，一定要在该资源文件当中进行注册
 * 
 * */
public class ObjectFactory {
	private static final ResourceBundle Dao_resource=ResourceBundle.getBundle("com/mvc/resource/dao");
	private static final ResourceBundle Service_resource=ResourceBundle.getBundle("com/mvc/resource/service");
	
	/*
	 * 按照指定的资源key获取dao实例对象
	 * @param key 在properties中定义的dao对象名的key
	 * @param clazz 是一种结构名称的标注，即由传入的该类决定了泛型的类型
	 * @param <T> 泛型类型，因此该类可以返回所有的dao子对象
	 * @return 返回的dao对象实例，如果没有发现指定的key返回空
	 * */
	public static <T>T getDaoInstance(String key,Class<T>...clazz){
		String classname=null;//注册在资源文件中的类名
		try{
			classname=Dao_resource.getString(key);//key不存在出现异常
		}catch(Exception e){
			e.printStackTrace();
		}
		if(classname==null || "".equals(classname)){
			return null;
		}
		try{//根据反射获取对象实例
			return (T) Class.forName(classname).getDeclaredConstructor().newInstance();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * 获取Service实例，实际上是返回一个代理实例
	 * @param key 在properties中定义的dao对象名的key
	 * @param clazz 是一种结构名称的标注，即由传入的该类决定了泛型的类型
	 * @param <T> 泛型类型，因此该类可以返回所有的Service子对象
	 * @return 返回的Service代理对象，如果没有发现指定的key返回空
	 * */
	public static <T>T getServiceInstance(String key,Class<T>...clazz){
		String classname=null;
		try{
			classname=Service_resource.getString(key);
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		if(classname==null||"".equals(classname)){
			return null;
		}
		try{
			ServiceProxy sp=new ServiceProxy();
			sp.bind(Class.forName(classname).getDeclaredConstructor().newInstance());
			//return (T)sp;
			return (T)Proxy.newProxyInstance(Class.forName(classname).getClassLoader(), Class.forName(classname).getInterfaces(), sp);
			
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
	}
}
