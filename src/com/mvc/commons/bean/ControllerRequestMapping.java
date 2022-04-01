package com.mvc.commons.bean;

import java.lang.reflect.Method;
/**
 * 该类是一个信息保存的类，保存的是访问路径匹配到的Action处理类的信息以及该处理类中对应的处理方法的信息
 * */
public class ControllerRequestMapping {
	private Class<?> actionClazz;//保存匹配的Action类信息
	private Method actionMethod;//保存匹配的Action方法信息
	
	public ControllerRequestMapping(Class<?> clazz,Method method){
		this.actionClazz=clazz;
		this.actionMethod=method;
	}
	
	public Class<?> getActionClazz() {
		return actionClazz;
	}
	public Method getActionMethod() {
		return actionMethod;
	}

	
	
}
