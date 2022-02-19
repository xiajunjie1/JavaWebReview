package com.maker.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
/**
 * 配置Session数据的钝化和Session数据的激活，需要在META-INF中添加一个content.xml
 * 该文件的内容是一个固定形式
 * 
 * */
public class SessionActivation_Listener implements HttpSessionActivationListener {
	/*
	 * Session的数据存储（钝化）
	 * 	Session数据以Map形式保存在内存中，但是如果一段时间不用的话，则比较浪费内存空间
	 * 	所以这个时候可以考虑部分数据内容通过序列化的形式进行钝化处理（通过一个二进制文件进行存储）
	 * */
	@Override
	public void sessionDidActivate(HttpSessionEvent event) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Session的数据激活
	 * 	当Session数据钝化一段时间后，如果该用户又活跃了，则可以通过反序列化机制，通过二进制文件把Session内容
	 * 	
	 * */
	@Override
	public void sessionWillPassivate(HttpSessionEvent event) {
		// TODO Auto-generated method stub

	}

}
