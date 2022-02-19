package com.maker.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 对于Session的状态的操作一般分为如下的几种形式
 * 	用户访问时候会在服务端创建一个Session对象，既然可以创建Session对象，则也可以针对于Session
 * 	销毁进行监听，Session的完整处理流程都是可以监听到的
 * 	在进行Session操作的时候，例如：用户如果登录成功则一般都会保存一个用户名到session属性中，这个时候
 * 	就表示要进行Session属性的操作，则Session属性的操作，则属性的操作也存在有对应的监听
 * */

public class Session_Listener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		// 当访问的页面中有getSession()操作的时候，该方法会被调用
		//当关闭浏览器再打开访问该页面后，会再次调用创建session并触发该方法
		System.out.println("请求Session创建："+hse.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		/* 通常关闭浏览器，是无法触发该方法的，实际上Session存储的WEB容器是一个以MAP集合的形式保存的，
		 * MAP的结构是存储在内存之中的，如果没有特殊的操作控制，这个Map集合所占用的内存是不会释放的
		 * 要触发销毁方法，一般有以下几种情况
		 * 	1.手动调用HttpSession.invalidate()方法
		 * 	2.等待一段时间（默认30min）未向服务器发送请求，Session会自动被销毁，该时间可在Tomcat的配置文件web.xml中修改<session-config>标签中的内容进行手动修改
		 * 	3.Tomcat、或者ide工具中reload机制
		 * */
		System.out.println("请求Session销毁："+hse.getSession().getId());
	}

}
