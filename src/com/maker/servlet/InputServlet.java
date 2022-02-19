package com.maker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class InputServlet extends HttpServlet {
	

	/**
	 * 在配置Servlet映射的时候，最好保证url-pattern和前端的html文件是在同一个目录下的
	 * 这样html是相对路径在访问Servlet的，相对而言比较方便
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");//如果要返回HTML内容且要输出中文必须加上该语句，否则会乱码
		PrintWriter pw=response.getWriter();
		String name=request.getParameter("name");
		pw.println("<h2>"+name+"</h2>");
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
