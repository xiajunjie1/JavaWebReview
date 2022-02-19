package com.maker.servlet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet异步请求响应
 * 	异步请求响应中最为重要的是一个"AsyncContext"接口，但是也需要清楚另外一个核心的问题
 * 	异步响应是需要启动一个新的线程来实现响应处理的，既然是多线程的应用，那么肯定要考虑使用Runnable接口
 * 
 * 	
 * */
@WebServlet(value="/Async.action",asyncSupported=true)//默认情况，是不开启异步支持的
public class AsyncServlet extends HttpServlet {
	private class Asyservlet implements Runnable{
		private AsyncContext asycontext;
		public Asyservlet(AsyncContext asycontext){
			this.asycontext=asycontext;
		}
		@Override
		public void run() {
			System.err.println("Asyservlet线程："+Thread.currentThread().getName());
			try{
		
			String msg=this.asycontext.getRequest().getParameter("info");
			TimeUnit.SECONDS.sleep(2);//模拟延迟
			this.asycontext.getResponse().getWriter().println("<h2>【echo】"+msg+"<h2>");
			this.asycontext.complete();//异步处理完成
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.err.println("Servlet线程："+Thread.currentThread().getName());
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		//所有关于AsyncContext的操作均在在ServletRequest接口中定义
		if(req.isAsyncSupported()){
			AsyncContext asyncontext=req.startAsync();
			asyncontext.start(new Asyservlet(asyncontext));//启动异步操作
			
		}else{
			resp.getWriter().println("<h2>暂不支持异步操作</h2>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
