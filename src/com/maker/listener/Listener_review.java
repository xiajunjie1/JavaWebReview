package com.maker.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 监听器
 * 	监听器中分为事件源和事件的处理机制，所有的监听处理机制都是基于java本身的事件处理方式
 * 	来实现的，只不过本次的监听是要求在WEB容器的环境中进行监控
 * 
 * 	listener监听的对象一般是对象域中的操作状态及上下文环境来进行监控，本次首先先来观察Request的监控
 * 	在程序开发之中，每一个Servlet处理完成的数据信息一般会通过request属性传递到JSP页面进行操作，
 * 	此时就属于request属性的操作，所以依然属于一种请求监听，而在请求处理完成就表示请求监听的结束，
 * 	所以也可以进行一个事件的处理
 * 
 * 	监听器使用场景：
 * 		如一个页面的访问次数的统计，一个视频播放量的统计，在线人数...
 * 
 * 	如果想针对Request状态进行监听，则可以使用ServletRequestListener接口
 * 	该接口提供以下两个方法：
 * 		请求初始化处理：requestInitalized()
 * 		请求的结束处理：requestDestroyed()
 * 	这样的两个方法都会产生一个ServletRequestEvent事件
 * 	该事件可以获取ServletRequest接口实例，ServletContext接口实例
 * 
 * 注解模式，直接在类上面加上@WebListener注解即可，但是该做法不利于代码的动态维护
 * */
public class Listener_review implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// 监听请求完毕
		System.out.println("#####请求完毕#####");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
	
		// 监听请求初始化
				System.out.println("@@@@@请求初始化@@@@@");
		
	}

}
