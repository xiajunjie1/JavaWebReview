package com.mvc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.mvc.commons.bean.ServletObject;

/**
 * 数据类型转换处理类，用来处理接收到的用户请求参数
 * 	所有用户提交到服务器端的参数类型都是String类型，既然都是String类型，那么就可以向指定的类型进行转换
 * 	但是在转换之前需要考虑到数据结构的组成，例如如果要转成int型，那么字符串应该由数字组成
 * 
 * 如果调用的方法，参数中含有Vo对象，那么根据属性的名称，调用Setter方法，将客户端传过来的参数一次注入到对应的属性当中
 * 在这个过程中，就要求客户端传入的参数name和属性的name保持一致，并且setter方法都是以setXxx()的形式定义的
 * 
 * 
 * */
public class DataTypeConvert {
	//如果字符串想要转为日期或者是日期时间，就必须考虑到多线程并发访问下的处理情况
	//相比SimpleDateFormat,该类是线程安全的
	private static final DateTimeFormatter Date_Time_Formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter Date_Formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final ZoneId Zone_Id=ZoneId.systemDefault();//获取当前系统默认的Zone id
	private DataTypeConvert(){}//不需要实例化该对象
	
	/*
	 * 将用户传入的参数，通过setter方法赋值给VO对象的属性
	 * 一般setter都为setXxx()的形式，Xxx为属性名
	 * @ obj vo对象
	 * 
	 * */
	public static void setVoObjectFieldValue(Object obj){
		Field[] fields=obj.getClass().getDeclaredFields();
		for(Field f : fields){
			try{
				Method setMethod=obj.getClass().getDeclaredMethod("set"+StringUtil.initcap(f.getName()), f.getType());
				//规定用户传入的参数名字要和Vo类属性名称保持一致
				//如果有部分属性，用户并未传入相应的值，那么则会直接赋予空值
				setMethod.invoke(obj, convert(f.getName(),f.getType()));
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	}
	
	/*
	 * 基本类型参数的转换
	 * @param paramname action方法参数名称
	 * @type 参数类型
	 * @return 将客户端请求的参数（parameter）转换后返回
	 * */
	public static Object convert(String paramname,Class<?> type){
		if(paramname!=null && !("".equals(paramname))){
		//参数名称存在，获得请求参数
		String value=ServletObject.getParameterUtil().getParameter(paramname);
		if(value!=null){
		try{
		if("int".equals(type.getName())||"java.lang.Integer".equals(type.getName())){
				if(value.matches("\\d+")){
					return Integer.parseInt(value);
				}
				}
			
		if("long".equals(type.getName())||"java.lang.Long".equals(type.getName())){
				if(value.matches("\\d+")){
					return Long.parseLong(value); 
				}			
				}
		if("double".equals(type.getName())||"java.lang.Double".equals(type.getName())){
				if(value.matches("\\d+.\\d+")){
					return Double.parseDouble(value);
				}
		}
		if("boolean".equals(type.getName())||"java.lang.Boolean".equals(type.getName())){
				return Boolean.parseBoolean(value);
		}
		
		if("java.util.Date".equals(type.getName())){
			if(value.matches("\\d{4}-\\d{2}-\\d{2}")){
				LocalDate ld=LocalDate.parse(value,Date_Formatter);
				Instant instant=ld.atStartOfDay(Zone_Id).toInstant();
				return Date.from(instant);
			}else if(value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){
				LocalDate ld=LocalDate.parse(value,Date_Time_Formatter);
				Instant instant=ld.atStartOfDay(Zone_Id).toInstant();
				return Date.from(instant);
			}
		}
		if("java.lang.String".equals(type.getName())){
			return value;
		}
		if("com.mvc.commons.bean.MultipartFile".equals(type.getName())){
			//根据参数内容，获取上传文件的对象
			return ServletObject.getParameterUtil().getAllUploadFile().get(paramname).get(0);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		}
		
		return null;
	}
	
	/*
	 * 接收到的请求数据为特定类型的数组
	 * 	在此处为了简单只考虑int型和long型的数组
	 * */
	public static Object convertArray(String paramname,Class<?> type){
		String[] result=null;
		if(paramname!=null && !("".equals(paramname))){
			result=ServletObject.getParameterUtil().getParameterValues(paramname);
			if("int[]".equals(type.getSimpleName())){
				//方法的参数列表为int型数组
				int[] values=new int[result.length];
				for(int i=0;i<result.length;i++){
					if(result[i].matches("\\d+"));
					values[i]=Integer.parseInt(result[i]);
				}
			}else if("Integer[]".equals(type.getSimpleName())){
				Integer[] values=new Integer[result.length];
				for(int i=0;i<result.length;i++){
					if(result[i].matches("\\d+"));
					values[i]=Integer.parseInt(result[i]);
				}
			}else if("long[]".equals(type.getSimpleName())){
				long[] values=new long[result.length];
				for(int i=0;i<result.length;i++){
					if(result[i].matches("\\d+"));
					values[i]=Long.parseLong(result[i]);
				}
			}else if("Long[]".equals(type.getSimpleName())){
				Long[] values=new Long[result.length];
				for(int i=0;i<result.length;i++){
					if(result[i].matches("\\d+"));
					values[i]=Long.parseLong(result[i]);
				}
			}else if("String[]".equals(type.getSimpleName())){
				//如果不是int或long类型的数组，那么就认为是传入的字符串数组
				return result;
			}
		}
		//如果以上类型都不满足，那么返回空代表没有满足的类型
		return null;
	}
	
}
