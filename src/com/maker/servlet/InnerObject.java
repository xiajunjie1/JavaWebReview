package com.maker.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 
 * Servlet内置对象：
 * 	HttpServletRequest
 * 	HttpServletResponse
 * 	ServletConfig
 * 	ServletContext
 * 	HttpSession
 * 
 * ServletConfig对象，可以获取Servlet的初始化参数，一般是在init(ServletConfig config)当中进行初始化参数的获取
 * 但是，也可以通过getServletConfig()方法，在服务请求的阶段获取到相应的内置对象
 * 
 * application内置对象(ServletContext接口对象)
 * 	获取application对象有两种方法：
 * 		GenericServlet父类：public ServletContext getServletContext()
 * 		ServletRequest接口：public ServletContext getServletContext()
 * 
 * 	session是属于Http协议范畴，session同时也需要Cookie的支持，而想要获取Cookie，则一定要解析头信息
 * 	这样就得出了一个结论：只有HttpServletRequest接口才可以获取到HttpSession接口对象，在HttpServletRequest定义有如下的方法：
 * 		获取HttpSession实例
 * 			public HttpSession getSession()
 * 				有HttpSession对象，就直接返回，没有就创建一个
 * 			public HttpSession getSession(boolean create)
 * 				当参数为true，则一定返回有Session对象，若为false，无session对象就返回空
 */

@WebServlet(value="/inner",initParams={@WebInitParam(name="first",value="xia"),@WebInitParam(name="last",value="junjie")})
public class InnerObject extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取config
		Enumeration<String> enu=this.getServletConfig().getInitParameterNames();
		while(enu.hasMoreElements()){
			String name=enu.nextElement();
			String value=this.getServletConfig().getInitParameter(name);
			System.out.println(name+"="+value);
		}
		
		//利用GenericServlet获取application
		System.out.println("realPath1"+super.getServletContext().getRealPath("/"));
		//利用ServletRequest获取application
		System.out.println("realPath2"+req.getServletContext().getRealPath("/"));
		//相对而言，ServletRequest接口获取application更稳定
		
		
		//获取HttpSession接口实例对象
		HttpSession session=req.getSession();
		System.out.println("sessionID="+session.getId());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
