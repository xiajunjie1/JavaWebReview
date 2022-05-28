package com.maker.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maker.service.UserService;

/**
 * 在Web层Servlet中使用Spring获取bean
 * 	但是每次获取bean都要加载配置文件，重复实例化应用上下文对象
 * 考虑到上述情况，在Web环境中可以使用监听器来对配置文件进行加载
 * 	在Web项目中，可以使用ServletContextListener监听Web应用的启动，我们可以在Web应用启动时
 * 	就加载Spring配置文件，创建应用上下文对象ApplicationContext，在将其存储到最大的域ServletContext域中
 * 	这样就可以在任意位置域中获取应用上下文ApplicationContext对象了
 * 	
 * */
@WebServlet("/uServlet.do")
public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext app=(ApplicationContext) req.getServletContext().getAttribute("app");
		UserService uservice=(UserService) app.getBean("uservice");
		try {
			uservice.UserSave();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
