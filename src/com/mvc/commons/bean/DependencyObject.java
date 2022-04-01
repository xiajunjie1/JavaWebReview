package com.mvc.commons.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

import com.mvc.commons.annotation.Aspect;
import com.mvc.commons.annotation.AutoWired;
import com.mvc.util.factory.ObjectFactoryDi;
import com.mvc.util.scanner.ScannerPackageUtil;

/**
 * 对象注入的类
 * */
public class DependencyObject {
//如果要注入的类是Service（业务层）的类，那么TargetObj就是Action
//如果要注入的类是数据层的类，那么TargetObj就是Service类
private Object TargetObj;
public DependencyObject(Object TargetObj){
	this.TargetObj=TargetObj;
}

public void inject(){
	System.out.println(this.TargetObj.getClass().getSimpleName());
	//目前只考虑注入成员属性的情况
	Field[] fields=this.TargetObj.getClass().getDeclaredFields();
	System.out.println(Arrays.toString(fields));
	for(int i=0;i<fields.length;i++){
		System.out.println("查找属性");
		//检查到属性上有注入注解
		if(fields[i].isAnnotationPresent(AutoWired.class)){
			Class<?> injectClaz=null;
			AutoWired autowired=fields[i].getAnnotation(AutoWired.class);
			if("none".equals(autowired.name())){
				
				injectClaz=ScannerPackageUtil.getByTypeMap().get(fields[i].getType());
			}else{
				injectClaz=ScannerPackageUtil.getByNameMap().get(autowired.name());
			}
			if(injectClaz!=null){
				//找到了需要注入的类
				try{
					fields[i].setAccessible(true);//解除封装
				if(injectClaz.isAnnotationPresent(Aspect.class)){
				
					//有Aspect注解，说明是业务类，并且需要开启事务
					
					//注入业务层对象
					fields[i].set(this.TargetObj,ObjectFactoryDi.getServiceInstance(injectClaz));
					System.out.println("注入业务类："+injectClaz);
					//由于业务层对象中，可能存在数据层对象需要注入，所以需要递归调用注入
					new DependencyObject(ObjectFactoryDi.getOriginalService()).inject();
					
				}else{
					//无Aspect注解，即不需开启事务，直接获得相应的实例对象即可
					fields[i].set(this.TargetObj, ObjectFactoryDi.getInstance(injectClaz));
					System.out.println("注入类："+injectClaz);
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
}
