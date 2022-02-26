package com.maker.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/**
 * ServletContext监听器
 * 	在整个项目中，application描述的是当前的WEB容器，而且整个Tomcat中，对于每个项目都有一个独立的WEB容器
 * 	即：在一个Tomcat启动的时候，会有若干个项目，每一个项目都会有一个ServletContext接口实例，但是这多个接口
 * 	实例之间无法调用
 * 	如果运行WEB项目，则一定要启动Tomcat，可能在Tomcat启动的时候进行一些重要的初始化操作
 * 	例如：每一个web容器会自动的设置一些application属性内容
 * */
//@WebListener
public class ServletContext_Listener implements ServletContextListener {
	/*
	 * 上下文销毁时触发
	 * */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("【上下文环境销毁】："+event.getServletContext().getVirtualServerName());
	}
	/*
	 * 上下文初始化触发
	 * */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("【上下文环境初始化】："+event.getServletContext().getVirtualServerName());
	}

}
