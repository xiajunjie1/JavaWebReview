package com.mvc.commons.abs;
/**
 * 业务层公共抽象类
 * 	完成一些公共业务处理逻辑
 * 	即将不需要与数据库交互的公共方法定义在该类中
 * */
public class AbstractService {
	/*
	 * 验证年龄为18-80岁之间
	 * */
	public boolean checkAge(int age){
		return age>=18 && age<=80;
	}
	
	/*
	 * 验证性别字段的合法性
	 * */
	public boolean checkSex(String sex){
		
		return "男".equals(sex)||"女".equals(sex);
	}
	
	/*
	 * 验证传入的数据中是否含有空值
	 * @params 传入需要判断的数据项
	 * @return 含有空值返回false，无空值返回true 
	 * */
	public boolean checkEmpty(String ...params){
		for(String param : params){
			if(param==null || "".equals(param)){
				return false;//传入的数据中含有空值
			}
			
		}
		return true;
	}
}
