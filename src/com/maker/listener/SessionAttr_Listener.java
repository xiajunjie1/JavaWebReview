package com.maker.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttr_Listener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// 增加Session属性
		System.out.println("添加Session属性，属性名："+event.getName()+",属性值："+event.getValue());
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// 移除Session属性
		System.out.println("移除Session属性，属性名："+event.getName()+",属性值："+event.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// 替换Session属性
		System.out.println("替换Session属性，属性名："+event.getName()+",属性值："+event.getValue());
	}

}
