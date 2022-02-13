package com.maker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在Servlet中，可以使用注解的方式来定义Servlet
 * @WebServlet注解
 * WebServlet注解的属性如下：
 * 	name		String			等价Servlet-name,若未配置，则使用类名称
 *  urlPatterns String[]		等价url-pattern
 *  value		String[]		与urlPatterns等价，两者不可同时出现
 *  loadOnStartup int			Servlet加载顺序，等价<load-on-startup>
 *  initParams	WebInitParam[]	Servlet初始化参数，等价<init-param>
 *  aysncSupported	boolean		Servlet是否支持异步处理，等价<aysnc-Supported>
 *  description		String		Servlet描述信息，等价于<description>
 *  displayName		String		Servlet显示名称，等价于<display-name>
 *  
 *  若用注解配置Servlet，则不能在web.xml中重复配置Servlet
 * */

@WebServlet("/AnnoServlet.action")
public class AnnonationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter pw=resp.getWriter();
		pw.println("<h2>基于注解的Servlet</h2>");
		pw.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
