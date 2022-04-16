package com.mvc.commons.bean;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.util.ParameterUtil;

/**
 * 为每一个线程保存一个HttpServletRequest和HttpServletResponse对象
 * 对request和response两个对象进行包装
 * 
 * 考虑到每个Action可能都存在接收参数的情况，将ParameterUtil也保存在ThreadLocal中，以供每个线程使用
 * */
public class ServletObject {
	private static final ThreadLocal<HttpServletRequest> Thread_Request=new ThreadLocal<>();
	private static final ThreadLocal<HttpServletResponse> Thread_Response=new ThreadLocal<>();
	private static final ThreadLocal<ParameterUtil> Thread_Parameter=new ThreadLocal<>();
	private ServletObject(){}
	public static void setThreadRequest(HttpServletRequest req){
		Thread_Request.set(req);
	}
	
	public static void setThreadResponse(HttpServletResponse resp){
		Thread_Response.set(resp);
	}
	
	public static void setParameterUtil(ParameterUtil pu){
		Thread_Parameter.set(pu);
	}
	
	public static HttpServletRequest getReq(){
		return Thread_Request.get();
	}
	
	public static HttpServletResponse getResp(){
		return Thread_Response.get();
	}
	
	public static ParameterUtil getParameterUtil(){
		return Thread_Parameter.get();
	}
	
	public static HttpSession getSession(){
		return Thread_Request.get().getSession();
	}
	
	public static ServletContext getApplication(){
		return Thread_Request.get().getServletContext();
	}
	
	public static void clean(){
		Thread_Request.remove();
		Thread_Response.remove();
		Thread_Parameter.get().clean();
		Thread_Parameter.remove();
	}
}
