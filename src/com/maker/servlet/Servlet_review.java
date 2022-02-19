package com.maker.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet生命周期
 * 	Tomcat需要JVM支持，而所有的服务组件又运行在Tomcat中，于是在对象实例化前后就有容器需要完成的部分
 * 	1、【无法控制】容器加载：所有的Servlet需要在web.xml中进行配置，里面一般都会提供<servlet-class>的内容，该内容就是明确描述Servlet程序类的完整名称
 * 			如果此时配置信息错误，那么肯定无法进行容器的正确启动
 * 	2、【无法控制】对象实例化：正常的对象实例化肯定使用的是关键字new，但是在WEB容器中的Servlet组件是利用反射机制实现的，所以Servlet中一般要提供一个无参构造
 * 	3、【可控制】初始化：需要为一些使用的资源进行配置，一般只会初始化一次
 * 	4、【可控制】服务支持：需要对HTTP请求进行多次处理和响应
 * 	5、【可控制】销毁：在Servlet程序被回收之前，做一些资源释放的操作
 * 	6、【无法控制】对象回收：Tomcat是运行在JVM中的，当某些类对象不在需要使用时，通过GC来回收处理
 * 	7、【无法控制】容器卸载：整个Tomcat停止运行，所有在Tomcat之中有关的处理资源就全部释放了
 * 
 * 
 * 	Servlet基础声明周期（可控制部分）
 * 		public void init()throws ServletException	属于GenericServlet，初始化处理方法
 * 
 * 		protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException 
 * 		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
 * 		处理Http get/post请求的方法，所属类型HttpServlet
 * 		
 * 		public void destroy()	属于Servlet接口，销毁接口
 * 		
 * 
 * 		默认情况下每一个Servlet部署完成后，实际上不会立即执行，也就是说默认情况下Servlet是不会随着容器启动
 * 		而自动进行对象实例化处理的，但是在第一次访问Servlet的时候，就会出现Servlet初始化
 * 
 * 		配置了<load-on-startup>，Servlet将在容器启动时，自动实现对象实例化和初始化的操作机制
 * 		该配置数值越小，Servlet越先执行，需要注意的是load-on-startup配置最小值为0，小于0代表Servlet不使用
 * 		但是在Spring框架中，经常通过负数来优先执行
 * 
 * 	Servlet扩展生命周期
 * 		public void init(ServletConfig config)throws ServletException
 * 			Servlet初始化，同时可以通过ServletConfig获取初始化参数
 * 		public void service(ServletRequest req,ServletResponse resp)throws ServletException,IOException
 * 			进行请求的处理与响应，不与具体的协议有关
 * 		protected void service(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
 * 			针对HTTP协议的请求进行处理与响应操作
 * 
 * 
 * 
 * 
 * */
@WebServlet(value="/servlet.review",initParams={@WebInitParam(name="title",value="xia")})
public class Servlet_review extends HttpServlet {

	public Servlet_review(){
		System.out.println("Servlet调用构造函数");
	}
	
	@Override
	public void destroy() {
		// 销毁一般有两种情况：当Servlet长时间不被使用，会自动调用destroy方法销毁Servlet
		//第二种情况：关闭容器，会调用该方法进行Servlet的销毁
		System.out.println("Servlet销毁");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理用户HTTP请求
		//执行Servlet可知，当存在Service方法时，doGet和doPost不再执行
		//由源代码可知，每次客户发送HTTP请求，在Service中进行处理，根据请求类型的不同（Get/Post...)调用对应的处理方法
		//当我们覆写了service方法后，自然不会再去根据请求类型的不同调用doGet,doPost...等方法了
		//该结构为模板设计模式
		System.out.println("处理用户HTTP请求");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 初始化时通过ServletConfig对象获取初始化参数
		//运行Servlet可知，当存在该方法时无参的init不会再执行了
		System.out.println("执行初始化，初始化参数为："+config.getInitParameter("title"));
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Servlet初始化");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Servlet服务支持");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
