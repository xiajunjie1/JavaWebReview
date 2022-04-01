package com.mvc.util;

/**
 * 进行首字母大小写转换的String工具类
 * 	在注入时，假如类为MemberServiceImp.class，那么bean管理的名称就应该为"memberServiceImp"
 * 	这里的首字母是需要小写的
 * */
public class StringUtil {//实现字符串的相关处理
private StringUtil(){}

/*
 * 首字母大写
 * */
public static String initcap(String str){
	if(str==null || "".equals(str)){
		return str;
	}
	if(str.length()==1){
		return str.toUpperCase();
	}
	return str.substring(0,1).toUpperCase()+str.substring(1);
}
/*
 * 首字母小写
 * */
public static String firstLower(String str){
	if(str==null || "".equals(str)){
		return str;
	}
	if(str.length()==1){
		return str.toLowerCase();
	}
	return str.substring(0,1).toLowerCase()+str.substring(1);
}
}
