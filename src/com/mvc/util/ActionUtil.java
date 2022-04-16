package com.mvc.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.mvc.util.method.MethodParameterUtil;

/**
 * 工具类，用来解析获得相应的上传路径
 * 	通过调用DataTypeConvert接收请求参数，并根据调用方法的参数类型转换请求参数
 * DispatcherServlet直接调用该工具类，进行方法参数的解析，最终将客户端传入的参数注入到对应的方法参数中
 * 
 * */
public class ActionUtil {
private static final String Upload_Method_Name="getUploadPath";//固定的方法名称，定义在AbstractAction类中

/*
 *判断当前参数是否为简单类型 
 *如果是以下的基本类型，则返回true，是封装的对象则返回false
 * */
private static boolean isBasic(Class<?> clazz){
	return "int".equals(clazz.getName()) ||
			"java.lang.Integer".equals(clazz.getName()) ||
			"long".equals(clazz.getName())||
			"java.lang.Long".equals(clazz.getName())||
			"double".equals(clazz.getName())||
			"java.lang.Double".equals(clazz.getName())||
			"boolean".equals(clazz.getName())||
			"java.lang.Boolean".equals(clazz.getName())||
			"java.lang.String".equals(clazz.getName())||
			"java.util.Date".equals(clazz.getName())||
			"com.mvc.commons.bean.MultipartFile".equals(clazz.getName());
	
}

public static boolean isArrays(Class<?> clazz){
	return "int[]".equals(clazz.getSimpleName())||
			"Integer[]".equals(clazz.getSimpleName())||
			"long[]".equals(clazz.getSimpleName())||
			"Long[]".equals(clazz.getSimpleName())||
			"String[]".equals(clazz.getSimpleName());
}

/*
 * 获取指定Action类中方法参数内容的数组集合
 * @param actionObj 要进行反射调用的Action对象
 * @param method 要调用的方法，该方法可以解析出参数的结构
 * @return 方法参数的数组内容，该数组在invoke反射调用方法时，作为参数传入
 * */
public static Object[] getMethodParameterValue(Object actionObj,Method method){
	Object[] result=null;
	if(actionObj!=null){
	//解析调用的方法，返回一个该方法参数信息的map集合
	Map<String,Class<?>> map=MethodParameterUtil.getMethodParameters(actionObj.getClass(), method);
	if(map.size()==0){
		//此方法不存在参数
		result=new Object[]{};
	}
	else{
		//方法上有参数
		result=new Object[map.size()];
		int foot=0;
		for(Map.Entry<String, Class<?>> entry : map.entrySet()){
			System.out.println("【PPPPPPPPP】"+entry.getKey());
			System.out.println("【TTTTTTTTT】"+entry.getValue().getName());
			if(isBasic(entry.getValue())){
				//参数类型为基本类型
			result[foot++]=DataTypeConvert.convert(entry.getKey(), entry.getValue());
			}else if(isArrays(entry.getValue())){
			result[foot++]=DataTypeConvert.convertArray(entry.getKey(), entry.getValue());
			}else{
				Object target=null;
				try {
					target=entry.getValue().getDeclaredConstructor().newInstance();
					DataTypeConvert.setVoObjectFieldValue(target);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				result[foot++]=target;
			}
			}
		}
	}
	return result;	
}

	

public static String getUpload(Object actionObject){
	//根据action类的实例，获取上传路径
	String path="/upload/";//设置一个默认的路径
	//根据名称获取对应的方法
	Method method=MethodParameterUtil.getMethodByname(actionObject.getClass(), Upload_Method_Name);
	if(method!=null){
		try {
		//调用方法，由于该方法在抽象类中已经定义了，其子类Action中也都会继承该方法，调用该方法返回值应该为一个字符串（路径）
		path=(String)method.invoke(actionObject);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	return path;
}
}
