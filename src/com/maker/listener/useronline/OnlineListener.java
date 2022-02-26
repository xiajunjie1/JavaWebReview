package com.maker.listener.useronline;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;

/**
 * 监听session、application等变化，从而改变在线用户的状态
 * */
@WebListener
public class OnlineListener implements ServletContextListener,HttpSessionListener,HttpSessionAttributeListener {
	
	private ServletContext application;
	private int UV=0;;
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// 登录成功，已设置了Session，需要将用户信息存储到application中
		if("userid".equals(event.getName())){
			//新增的属性名为userid
			Map<String,Boolean> map=(Map<String, Boolean>) this.application.getAttribute("online");
			map.put(event.getValue().toString(), false);//获取新增的属性值（用户名）作为key，然后设置value为false，代表状态为在线
			this.application.setAttribute("online", map);
			this.UV++;
			this.application.setAttribute("uv", this.UV);
		}
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// 用户离开了
		String uid=(String)event.getSession().getAttribute("userid");
		Map<String,Boolean> map=(Map<String,Boolean>)this.application.getAttribute("online");
		map.remove(uid);//删除该用户
		this.UV--;
		this.application.setAttribute("online", map);//重新设置用户信息
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * Servlet容器初始化
	 * 
	 * */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 初始化application
		this.application=event.getServletContext();
		//map集合中，key为用户名，value为状态，true：为已剔除，false为在线
		application.setAttribute("online", new HashMap<String,Boolean>());
		
	}

}
