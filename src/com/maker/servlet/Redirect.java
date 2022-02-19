package com.maker.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet跳转
 * 	客户端跳转：
 * 		1、超链接
 * 		2、JavaScript
 * 		3、ServletResponse内置对象
 * 			public void sendRedirect(String location)throws IOException
 * 	服务器端跳转：
 * 		1、<jsp:forward>标签
 * 		2、pageContext对象
 * 		3、RequestDispatcher接口
 * 			public RequestDispatcher(String path)
 * 				该方法需要明确传入一个要跳转的完整路径，这个完整路径指的是以"/"开头的Web路径信息，而后才可以使用forward()跳转
 * 	
 * 	重要结论：
 * 		在WEB开发中，对于JSP页面中的数据显示，往往只显示一次，所以这个时候最佳的传递处理形式使用的就是request属性范围
 * 
 * 	Dispatcher类型
 * 		REQUEST	默认
 * 		FORWARD
 * 		INCLUDE
 * 		ERROR
 * 		ASYNC
 * 	没有特别的需求，一般都采用默认的转发方式
 * */
@WebServlet("/redirect.action")
public class Redirect extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		req.setAttribute("title", "requestparam");
		req.getSession().setAttribute("title", "sessionparam");
		//客户端跳转，request属性范围无法传递
		//resp.sendRedirect("show.jsp");
		//服务器端跳转，全部用HttpServletRequest对象实现
		RequestDispatcher reqdpatcher=req.getRequestDispatcher("/show.jsp");
		reqdpatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
