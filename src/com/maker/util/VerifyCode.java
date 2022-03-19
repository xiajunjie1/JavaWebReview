package com.maker.util;
/**
 * 生成随机验证码数字
 * */
public class VerifyCode {
	public static String getCode(int length){
		char[] codes={ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R','S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuilder code=new StringBuilder();
		for(int i=0;i<length;i++){
			int index=(int)(Math.random()*(codes.length-1));
			code.append(codes[index]);
		}
		return code.toString();
	}


}