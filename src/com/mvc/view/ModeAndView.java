package com.mvc.view;

import java.util.Map;

import com.mvc.commons.bean.ServletObject;

/**
 * 在实际的项目开发中，一定会通过Action返回内容，那么一般会有如下的三种形式：
 * 	1.直接返回字符串：通过控制器跳转到jsp页面
 * 	2.返回完整数据：跳转路径以及所传递的属性内容（使用ModelAndView）进行封装
 * 	3.void：再进行ajax直接响应（JSON/XML)的时候不需要返回值
 * */
public class ModeAndView {
	private String view;//视图路径（跳转路径）

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
	public ModeAndView(){}
	public ModeAndView(String view){
		this.view=view;
	}
	public ModeAndView(String view,String name,Object value){
		this.view=view;
		this.add(name, value);
	}
	
	public ModeAndView(String view,Map<String,Object> map){
		this.view=view;
		this.add(map);
	}
	
	public void add(String name,Object value){
		ServletObject.getReq().setAttribute(name, value);
	}
	
	public void add(Map<String,Object> map){
		for(Map.Entry<String, Object> entry : map.entrySet()){
			ServletObject.getReq().setAttribute(entry.getKey(), entry.getValue());
		}
	}
}
