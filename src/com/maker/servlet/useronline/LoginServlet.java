package com.maker.servlet.useronline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
/**
 * 用来处理前端页面传递过来的登录参数，username和password
 * 
 * */
@WebServlet("/LoginServlet.action")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String name=req.getParameter("username");
		System.out.println("##$##"+req.getParameter("password"));
		if("xia123".equals(req.getParameter("password"))){
			//登录成功设置session
			req.getSession().setAttribute("userid", name);
			resp.sendRedirect("OnlineUserManager/front/welcome.jsp");
		}else{
			resp.sendRedirect("OnlineUserManager/login.jsp?errmsg=wrong password");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
}
