package com.maker.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * 过滤器
 * 	应用场景：某一些操作只允许登录后的用户访问，某一些操作需要屏蔽一些关键词
 * 	在一个标准的WEB请求中，所有的请求服务都可以通过request接收，而所有的回应通过response来完成
 * 	在不改变原始操作结构的情况下，如果中间可以引入一些自动执行的过滤操作，那么整个代码就会非常容易维护了
 * 	在整个请求和回应的处理过程，开发者是不需要关注过滤器的存在的
 * 		过滤器核心接口：Filter
 * 			早期的Filter接口中，包含三个抽象方法
 * 				init():实现过滤器的初始化操作回调，后来的版本中，将其变更为了default方法
 * 				doFilter():实现具体的过滤操作
 * 				destroy():过滤器销毁的回调，后来的版本中，将其变更为了default方法
 * 			所以早期在使用过滤器的时候，需要实现上面三个抽象方法，而现在在新版本中只需要实现一个doFilter()方法即可
 * 	在新版本的Filter实现结构之中，已经不再去直接实现Filter接口了，而是像Servlet一样去继承专属协议的抽象类，这样做的目的是为了统一实现结构
 * 	
 * 	重点：
 * 		1.过滤器是依据匹配的路径来进行执行处理的
 * 		2.在进行过滤器处理的时候必须使用"chain.doFilter(request,response)方法进行用户请求目标路径的跳转，如果不跳转，则表示当前的响应
 * 			由过滤器自行完成
 * 		3.过滤器的基本生命周期与Servlet类似，但是不需要做任何配置其就在容器初始化的时候自动进行init()方法的调用
 * 		4.FilterConfig与ServletConfig的作用相同，都是获取初始化配置参数的
 * 		
 * */
//由于Tomcat8的Servlet的版本不够新，所以此处仍采用实现Filter接口的方式来实现过滤器
//HttpFilter是Servlet4.0才出现的Filter的实现类，新版本的Filter直接继承该抽象类即可
public class Filter_review implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("过滤器销毁");
	}
	
	/*
	 * 如果想要进行过滤的操作实现，有一个最为重要的前提条件：那么就是一定要放行，如果不放行
	 * 则所有的请求都会被过滤器拦截，而要想放行就需要通过FilterChain接口实例来实现
	 * */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("用户请求过滤");
		HttpServletRequest httpreq=(HttpServletRequest)req;
		//如果采用默认的dsipatcher模式，为请求时触发，这样就在访问Servlet之前触发该方法
		//如果在Servlet（Redirect.java）中设置了request，session属性，并进行跳转，此处就无法获取到
		//在web.xml中可以配置dispatcher模式，修改该模式，可以让过滤器的触发方式变为跳转时触发
		System.out.println("request的属性："+httpreq.getAttribute("title"));
		System.out.println("session的属性："+httpreq.getSession().getAttribute("title"));
		fc.doFilter(req, resp);//放行
		System.out.println("服务端响应过滤");
		//过滤器会执行两次，用户请求执行一次，服务器响应执行一次
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//Tomcat加载的时候执行
		System.out.println("过滤器初始化："+config.getInitParameter("title"));
		
	}

}
