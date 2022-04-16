package com.mvc.util.method;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

public class MethodParameterUtil {//参数解析的工具类
	private MethodParameterUtil(){}
	/*
	 * 根据指定名称获取method对象
	 * @param clazz 需要解析的类
	 * @param methodName 传入方法的名称
	 * @return 找到相应的方法返回方法对象，否则返回空
	 * */
	public static Method getMethodByname(Class<?> clazz,String methodName){
		Method[] methods=clazz.getDeclaredMethods();
		for(Method m : methods){
			if(methodName.equals(m.getName())){
				return m;
			}
		}
		return null;
	}
	
	/*
	 * 获取方法中参数的名称和类型
	 * @param clazz 要解析的类
	 * @param methodname 方法名称
	 * @return 将查到的方法参数名和类型保存到map中返回，若无则返回长度为0的map
	 * */
	public static Map<String,Class<?>> getMethodParameters(Class<?> clazz,String methodname){
		Map<String,Class<?>> pmap=new LinkedHashMap<>();//由于参数是有顺序的，所以在此处用LinkedHashMap
		if(clazz==null || methodname==null || "".equals(methodname)){
			return pmap;
		}
		Method targetMethod=getMethodByname(clazz,methodname);
		System.out.println(targetMethod);
		//Spring提供的工具类，可以解析方法的参数名称，该类要依赖Spring-core、commons-logging、slf4j
		String[] names=new LocalVariableTableParameterNameDiscoverer().getParameterNames(targetMethod);
		Class<?>[] types=targetMethod.getParameterTypes();
		for(int x=0;x<names.length;x++){
			//由于参数是顺序的，所以此处获取到名称数组和类型数组也是一一对应的
			pmap.put(names[x], types[x]);
		}
		return pmap;
	}
	
	/*
	 * 获取方法中参数的名称和类型
	 * @param clazz 要解析的类
	 * @param method 方法对象
	 * @return 将查到的方法参数名和类型保存到map中返回，若无则返回长度为0的map
	 * */
	public static Map<String,Class<?>> getMethodParameters(Class<?> clazz,Method method){
		Map<String,Class<?>> pmap=new LinkedHashMap<>();
		if(clazz==null || method==null){
			return pmap;
		}
		String[] names =new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
		Class<?>[] types=method.getParameterTypes();
		for(int x=0;x<names.length;x++){
			pmap.put(names[x], types[x]);
		}
		return pmap;
	}

}
