package com.maker.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
/**
 * 若想要监听Request范围内的Attribute的变化，可以使用ServletRequestAttributeListener
 * 	可以通过事件处理类获取以下的内容（ServletRequestAttributeEvent）
 * 		public String getName():获取属性名称
 * 		public Object getValue():获取属性内容
 * */
public class ReqAttribute_listener implements ServletRequestAttributeListener {

	/*
	 * request.setAttribute("属性名称"，"属性值")
	 * 新增属性时触发
	 * */
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * requeset.removeAttribute("属性名称")
	 * 删除已存在属性时触发
	 * */
	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * request.setAttribute("属性名称","属性值")
	 * 	修改已存在的属性时触发
	 * */
	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub

	}

}
