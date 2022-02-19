package com.maker.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class Listener_Dynamic implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		// TODO Auto-generated method stub
		System.out.println("动态request监听器初始化");

	}

}
