package com.mvc.util.validate;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.mvc.commons.bean.ControllerRequestMapping;
import com.mvc.commons.bean.ServletObject;
import com.mvc.commons.validate.ValidateRegular;
import com.mvc.util.ResourceBundleUtil;

/**
 * 读取验证规则的工具类
 * */
public class ValidationRuleUtil {
	/*
	 * 将读取到的验证规则进行拆分，同时调用具体的验证处理方法进行验证
	 * 	@param rule 全部的验证规则
	 * 	@return 返回错误信息，key为参数名称，value为错误信息，如果有问题，map长度一定是大于0的
	 * */
	public static Map<String,String> getRule(String rule){
		//rule=deptno:int|deptname:string|loc:string|hobby:string[]
		Map<String,String> map=new HashMap<>();
		String[] ruleResults=rule.split("\\|");//数据拆分
		for(int x=0;x<ruleResults.length;x++){
			//tmp[0]=请求参数 tmp[1]规则
			String[] tmp=ruleResults[x].split("\\:");
			if(!check(tmp[1],tmp[0])){
				//如果验证失败,将参数名称和错误信息存放在map中
				map.put(tmp[0], ResourceBundleUtil.getkey("validation.error."+tmp[1]));
			}
		}
		return map;
	}
	
	public static String getValidateRule(String basename,ControllerRequestMapping crm)throws Exception{
		if(basename==null||"".equals(basename)){
			return null;
		}
		
		ResourceBundle rb=ResourceBundle.getBundle(basename);
		//定义在规则配置文件中的内容，形式如下：com.action.DeptAction.mix=deptno:int|deptname:string|loc:string|hobby:string[]
		String rulekey=crm.getActionClazz().getName()+"."+crm.getActionMethod().getName();//拼凑出key值
		//System.out.println("【rulekey==】"+rulekey);
		String rule=rb.getString(rulekey);//获取规则
		return rule;
		
	}
	
	public static String getErrorpath(String basename,ControllerRequestMapping crm)throws Exception {
		String path=null;
		
			path=getValidateRule(basename,crm);
		
			System.out.println("【xxxx】"+path);
		
		
		return path;
	}
	private static boolean check(String regular,String paraname){
		//获取所有的参数，都采用数组的形式
		String[] values=ServletObject.getParameterUtil().getParameterValues(paraname);
		switch(regular){
		case "string":return ValidateRegular.STRING.check(values);
		case "int":return ValidateRegular.INT.check(values);
		case "long":return ValidateRegular.LONG.check(values);
		case "double":return ValidateRegular.DOUBLE.check(values);
		case "boolean":return ValidateRegular.BOOLEAN.check(values);
		case "date":return ValidateRegular.DATE.check(values);
		case "datetime":return ValidateRegular.DATETIME.check(values);
		case "int[]":return ValidateRegular.INT_ARRAY.check(values);
		case "long[]":return ValidateRegular.LONG_ARRAY.check(values);
		case "string[]":return ValidateRegular.STRING_ARRAY.check(values);
		case "image":return ValidateRegular.IMAGE.check(paraname);//根据参数名来进行验证
		}
		return false;
	}
}
