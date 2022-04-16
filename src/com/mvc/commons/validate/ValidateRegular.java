package com.mvc.commons.validate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mvc.commons.bean.ServletObject;

/**
 * 用枚举的方式来实现验证的规则，因为所有的验证规则是有限的
 * */
public enum ValidateRegular {
	
	STRING{//字符串的验证规则，该内容不允许为空，null和""都不允许

		@Override
		public boolean check(String... value) {
			if(value==null || value.length==0){
				return false;
			}
			if(value.length!=1){
				//单内容判断
				return false;
			}
			if(value[0]==null || "".equals(value[0])){
				return false;
			}
			return true;//验证通过
		}
		
	},
	
	INT{ //该内容必须为整数

		@Override
		public boolean check(String... value) {
			if(ValidateRegular.STRING.check(value)){
				//输入数据不为空
				return value[0].matches("\\d+");
			}
			return false;
		}
		
	},
	LONG{
		@Override
		public boolean check(String... value) {//该内容必须为整数
			if(ValidateRegular.STRING.check(value)){
				//输入数据不为空
				return value[0].matches("\\d+");
			}
			return false;
		}
	},
	
	DOUBLE{//该内容必须为小数
		@Override
		public boolean check(String... value) {
			if(ValidateRegular.STRING.check(value)){
				//输入数据不为空
				return value[0].matches("\\d+(\\.\\d+)?");
			}
			return false;
		}
	},
	BOOLEAN{
		@Override
		public boolean check(String... value) {
			if(ValidateRegular.STRING.check(value)){
				return "true".equals(value[0]) || "false".equals(value[0]);
			}
			return false;
		}
	},
	DATE{//该内容必须为日期
		@Override
		public boolean check(String... value) {
			if(ValidateRegular.STRING.check(value)){
				//输入数据不为空
				return value[0].matches("\\d{4}-\\d{2}-\\d{2}");
			}
			return false;
		}
	},
	DATETIME{
		@Override
		public boolean check(String... value) {
			if(ValidateRegular.STRING.check(value)){
				//输入数据不为空
				return value[0].matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
			}
			return false;
		}
	},
	RAND{//验证码
		@Override
		public boolean check(String... value) {
			String rand=(String)ServletObject.getSession().getAttribute("rand");
			if(ValidateRegular.STRING.check(rand)){
				//验证码不为空
				return rand.equalsIgnoreCase(value[0]);
			}
			return false;
		}
		
	},
	INT_ARRAY{

		@Override
		public boolean check(String... value) {
			if(value==null || value.length==0){
				return false;
			}
			for(String str : value){
				if(str==null || "".equals(str)){
					return false;
				}else{
					if(!str.matches("\\d+")){
						//不是整数
						return false;
					}
				}
			}
			
			return true;
		}
		
	},
	LONG_ARRAY{

		@Override
		public boolean check(String... value) {
			if(value==null || value.length==0){
				return false;
			}
			for(String str : value){
				if(str==null || "".equals(str)){
					return false;
				}else{
					if(!str.matches("\\d+")){
						//不是整数
						return false;
					}
				}
			}
			
			return true;
		}
		
	},
	STRING_ARRAY{

		@Override
		public boolean check(String... value) {
			if(value==null || value.length==0){
				return false;
			}
			for(String str : value){
				if(str==null || "".equals(str)){
					//如果数组中有一个值为空，那么就验证失败，返回false
					return false;
				}
			}
			return true;
		}
		
	},
	IMAGE{
		//此处的value传入的并不是请求参数值，它传入的是请求参数名称
		@Override
		public boolean check(String... value) {
			// TODO Auto-generated method stub
			String[] types={"image/png","image/jpg","image/jpeg","image/bmp"};
			List<String> mimetypes=new ArrayList<String>(Arrays.asList(types));
			if(value==null){
				return false;
			}
			return ServletObject.getParameterUtil().uploadmimeCheck(value[0], mimetypes);
		}
		
	};
	/*
	 * 定义一个公共的抽象方法（有需要可以定义接口），实现具体数据的检查，因为所有传入的数据类型都是字符串
	 * request.getParameter()返回的类型就是字符串
	 * @param value 要验证的数据
	 * @return 验证通过返回true 否则返回false
	 * */
	public abstract boolean check(String... value);
		
	
}
