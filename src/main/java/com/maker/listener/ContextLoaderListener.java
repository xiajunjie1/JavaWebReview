package com.maker.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@WebListener
public class ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("ServletContext销毁，Spring容器销毁");
		

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext context=sce.getServletContext();
		//通过web.xml配置一个全局变量，然后将配置文件名配在web.xml上，可以降低代码的耦合度
		String filename=context.getInitParameter("SpringConfig");
		ApplicationContext app=new ClassPathXmlApplicationContext(filename);
		context.setAttribute("app", app);
	}

}
