package com.maker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//所有的Servlet中，一定存在强制的父类继承要求，因为该技术出现的时间比较早
//现阶段的面向对象程序设计理念是不要求存在有强制继承的关系的
//所有的Servlet要想使用，必须要在Web.xml中配置Servlet的映射
//注意：可以给一个Servlet配置多个映射路径，多个映射路径的使用与Servlet关联是通过Servlet-name配置进行捆绑的
//映射路径可以配置为/*的模式，表示匹配任意的访问
public class HelloServlet extends HttpServlet {

	
	//该类中重写方法的两个参数对象，是每一次请求处理的时候，由容器提供的
	//在HTTP请求中，一般4xx的错误都是属于客户端请求错误，当Servlet中没有与当前请求模式匹配的HTTP处理方法存在，则会出现405的错误
	@Override //进行GET请求的处理
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=resp.getWriter();//在整个Servlet中对于响应流的获取只能获取唯一的一次，即该代码只能使用一次
		req.getSession();
		resp.setContentType("text/html;charset=UTF-8");
		pw.println("Hello Servlet");
		pw.println("<a href='AnnoServlet.action'>跳转</a>");
		pw.close();
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
