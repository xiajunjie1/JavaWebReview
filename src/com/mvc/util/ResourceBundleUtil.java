package com.mvc.util;

import java.util.ResourceBundle;

/**
 * 工具类
 * 对资源配置文件进行读取
 * */
public class ResourceBundleUtil {
	private static ResourceBundle bundle;
	private ResourceBundleUtil(){}
	
	public static void setMessageBasename(String baseName){
		bundle=ResourceBundle.getBundle(baseName);
	}
	
	public static String getkey(String key){
		try{
			//防止读取message中文信息的时候乱码
			return new String(bundle.getString(key).getBytes("ISO-8859-1"),"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
