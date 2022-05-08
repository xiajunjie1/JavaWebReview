package com.maker.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SpringBeanCopy {
	//引用类型
	private SpringBean sbean;
	//普通类型
	private String name;
	private int age;
	private String addr;
	//集合类型
	private List<String> slist;
	private Map<String,SpringBean> smap;
	private Properties prop;
	
	
	public SpringBeanCopy(){
		System.err.println("调用无参构造");
	}
	//带参构造，为依赖注入准备
	public SpringBeanCopy(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	public void SpringInit(){
		System.err.println("初始化方法！");
	}
	
	public void SpringDestroy(){
		System.err.println("销毁方法！");
	}

	public void setSbean(SpringBean sbean) {
		this.sbean = sbean;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setSlist(List<String> slist) {
		this.slist = slist;
	}
	public void setSmap(Map<String, SpringBean> smap) {
		this.smap = smap;
	}
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
	public void show(){
		System.out.println("【sbean】"+this.sbean);
		System.out.println("【name】"+this.name);
		System.out.println("【age】"+this.age);
		System.out.println("【addr】"+this.addr);
		System.out.println("【slist】"+this.slist);
		System.out.println("【smap】"+this.smap);
		System.out.println("【prop】"+this.prop);
	}
}
