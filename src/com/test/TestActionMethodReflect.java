package com.test;

import java.lang.reflect.Method;
import java.util.Map;

import com.action.DeptAction;
import com.mvc.util.method.MethodParameterUtil;

public class TestActionMethodReflect {
	public static void main(String[] args){
		//Method actionmethod=getMethod(DeptAction.class,"split");
		//Class<?>[] types=actionmethod.getParameterTypes();
		//for(Class<?> type : types){
			//System.out.println(type);
		//}
		Map<String,Class<?>> result=MethodParameterUtil.getMethodParameters(DeptAction.class,"split" );
		for(Map.Entry<String, Class<?>> entry : result.entrySet()){
			System.out.println("参数名："+entry.getKey()+",参数类型："+entry.getValue());
		}
	}
	
	public static Method getMethod(Class<?> clazz,String methodName){
		Method[] methods=clazz.getMethods();
		for(Method m : methods){
			if(methodName.equals(m.getName())){
				return m;
			}
			
		}
		return null;
	}
}
