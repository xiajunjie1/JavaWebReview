package com.maker.proxy;

import java.lang.reflect.Method;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 该测试是基于cglib进行的操作，当前版本的Spring框架中，已经集成cglib相应的jar包
 * */
public class ProxyTest {
	public static void main(String[] args){
		Advice advice=new Advice();
		//创建增强器
		Enhancer enhancer=new Enhancer();
		//设置父类(目标类）
		Target target=new Target();
		enhancer.setSuperclass(target.getClass());
		//设置回调,参数是一个callback接口数组
		enhancer.setCallback(new MethodInterceptor() {
			
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy arg3) throws Throwable {
				// TODO Auto-generated method stub
				if("update".equals(method.getName())){
					advice.before();
					}
				Object invoke=method.invoke(target, args);
				if("query".equals(method.getName())){
				advice.after();
				}
				
				return invoke;
			}
		});
		
		Target proxy=(Target) enhancer.create();
		proxy.update("xiajunjie");
		
		//proxy.query();
	}
}
