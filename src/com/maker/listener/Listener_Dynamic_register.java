package com.maker.listener;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;

import com.maker.filter.Filter_Dynamic;
import com.maker.servlet.Servlet_Dynamic;
/**
 * 动态注册Servlet、Filter、Listener
 * */
//@WebListener
public class Listener_Dynamic_register implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		ServletContext application=event.getServletContext();
		Servlet_Dynamic servlet=new Servlet_Dynamic();
		Filter_Dynamic filter=new Filter_Dynamic();
		Listener_Dynamic listener=new Listener_Dynamic();
		RegisterServlet(application,servlet);
		RegisterFilter(application,filter);
		RegisterListener(application,listener);
	}
	
	private void RegisterServlet(ServletContext sc,HttpServlet servlet){
		ServletRegistration.Dynamic register=sc.addServlet("Servlet_Dynamic", servlet.getClass());
		System.out.println(servlet.getClass().getName());
		register.setLoadOnStartup(1);//Servlet启动时的加载
		register.addMapping("/dynamic.action");
		register.setInitParameter("sname", "【DynamicServlet】初始化参数");
		
	}
	
	private void RegisterFilter(ServletContext sc,Filter filter){
		FilterRegistration.Dynamic register=sc.addFilter("Filter_Dynamic", filter.getClass());
		register.setInitParameter("fname", "【DynamicFilter】初始化参数");
		//参数1：dispatcher方式、参数2：false-请求前触发，true-请求后触发、参数3：过滤路径
		register.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD), false, "/*");
	}
	
	private void RegisterListener(ServletContext sc,ServletRequestListener listener){
		sc.addListener(listener.getClass());
	}
		
}
