package com.maker.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;

/**
 * SessionID监听器
 * 	
 * */
//@WebListener
public class SessionID_Listener implements HttpSessionIdListener {
	/*
	 * 每当SessionID修改之后都会触发该监听方法的执行
	 * 	通过访问Session_attribute.jsp触发了Session创建监听事件
	 * 	在Session_changeId.jsp页面中执行了request.changeSessionId()方法
	 * 	该方法触发了SessionID监听事件，但是修改了ID值并不会影响Session属性值，这说明该方法只改变了SessionID
	 * 	而调用session.invalidate()方法时候也会产生SessionID的变化，但是那相当于是注销Session后，继续访问
	 * 	网页会重新创建一个新的Session
	 * */
	@Override
	public void sessionIdChanged(HttpSessionEvent hse, String oldSessionId) {
		
		System.out.println("SessionID修改，新ID："+hse.getSession().getId()+",老ID："+oldSessionId);
		
	}

}
