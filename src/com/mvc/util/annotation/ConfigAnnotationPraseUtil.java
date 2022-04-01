package com.mvc.util.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.mvc.commons.annotation.Controller;
import com.mvc.commons.annotation.Repository;
import com.mvc.commons.annotation.RequestMapping;
import com.mvc.commons.annotation.Service;
import com.mvc.commons.bean.ControllerRequestMapping;
import com.mvc.util.StringUtil;

/**
 * 对Action类进行解析，并生成访问路径，将对应的访问路径和Action类及类中的方法对应起来
 * 最后将所有信息包装成一个Map集合，并返回这个集合
 * */
public class ConfigAnnotationPraseUtil {
	private Map<String,ControllerRequestMapping> controllerMapResult=new HashMap<>();
	//保存Service和Repository的类信息，根据设置的名称（value）进行保存  key:设置的名称，value：需要注入的类型
	//如果没有设置名称，那程序就采用自动的方式为其设置名称，即使用类名称进行处理，即AutoWired注解中如果有设置name，一般会用该map的数据来获取所需要注入的类
	private Map<String,Class> nameAndTypeMap=new HashMap<>();
	//保存Service和Repository的类信息，根据实现的接口进行保存 key:接口的类型，value：需要注入的类型
	//利用接口形式实现的管理，可以利用类型实现注入，即AutoWired注解中如果没有设置name，一般会用该map的数据来获取所需要注入的类
	private Map<Class,Class> objectInterfaceAndClassMap=new HashMap<>();
	private String parenturl;//定义的父路径，Action类上的RequestMapping注解中的值
	private Class<?> clazz;//传入的类是Action类
	
	
	public ConfigAnnotationPraseUtil(Class<?> clazz){
		this.clazz=clazz;//当前要根据传入的Action进行解析处理
		this.classHandle();//实现解析控制
		
	}
	
	private void classHandle(){//进行具体的解析处理操作
		Annotation[] annotations=this.clazz.getAnnotations();//获取全部的Annotation
		for(Annotation an : annotations){
			if(an.annotationType().equals(Controller.class)){
			try{	
				//该类中，有Controller注解，表示是一个控制类
				RequestMapping mapping=this.clazz.getAnnotation(RequestMapping.class);//获取指定的注解
				this.parenturl=mapping.value();//获取父路径，如果没有配置父路径，默认是/
				if(this.parenturl.lastIndexOf("/")==-1){
					//父路径不是以 / 结尾的
					this.parenturl+="/";
				}
				this.MethodHandle();
			}catch(Exception e){
				e.printStackTrace();
			}
			}
			else if(an.annotationType().equals(Service.class)){
				//业务层注解
				try{
					Service service=this.clazz.getAnnotation(Service.class);
					if("none".equalsIgnoreCase(service.value())){
						//注解业务层或数据层时，未设置名称（未设置value）
						//由类名直接生成该名字，首字母小写
						String key=StringUtil.firstLower(this.clazz.getSimpleName());
						this.nameAndTypeMap.put(key, this.clazz);
					}else{
						//用户设置了具体的名称
						this.nameAndTypeMap.put(service.value(), this.clazz);
					}
					//根据接口类型保存业务层类型
					Class<?>[] interfaces=this.clazz.getInterfaces();
					for(Class<?> inte : interfaces){
						this.objectInterfaceAndClassMap.put(inte, this.clazz);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				}
				
			else if(an.annotationType().equals(Repository.class)){
				//数据层的注解
				try{
				Repository repository=this.clazz.getAnnotation(Repository.class);
				if("none".equalsIgnoreCase(repository.value())){
					//注解业务层或数据层时，未设置名称（未设置value）
					//由类名直接生成该名字，首字母小写
					String key=StringUtil.firstLower(this.clazz.getSimpleName());
					this.nameAndTypeMap.put(key, this.clazz);
				}else{
					//用户设置了具体的名称
					this.nameAndTypeMap.put(repository.value(), this.clazz);
				}
				//根据接口类型保存业务层类型
				Class<?>[] interfaces=this.clazz.getInterfaces();
				for(Class<?> inte : interfaces){
					this.objectInterfaceAndClassMap.put(inte, this.clazz);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			}
		}
	}
	
	
	
	public void MethodHandle(){
		//解析相应的方法
		if(this.parenturl==null){
			this.parenturl="";
		}
		Method[] methods=this.clazz.getDeclaredMethods();//获取action类中所有声明的方法
		for(Method m : methods){
			if(m.isAnnotationPresent(RequestMapping.class)){
				//判断拥有指定的注解类
				RequestMapping mapping=m.getAnnotation(RequestMapping.class);
				String path=this.parenturl+mapping.value();//访问的路径
				//将访问的路径和对应的Action处理类、处理方法对应起来，并且保存在map中
				this.controllerMapResult.put(path,new ControllerRequestMapping(this.clazz,m) );
			}
		}
	}
	
	
	/*
	 * 将一个Action类的解析结果，进行返回
	 * key：用户的访问路径
	 * value：保存对应的action处理类和处理方法信息的对象实例
	 * */
	public Map<String, ControllerRequestMapping> getControllerMapResult() {
		return controllerMapResult;
	}

	public Map<String, Class> getNameAndTypeMap() {
		return nameAndTypeMap;
	}



	public Map<Class, Class> getObjectInterfaceAndClassMap() {
		return objectInterfaceAndClassMap;
	}


}
