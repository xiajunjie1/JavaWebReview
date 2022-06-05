package com.maker.proxy;
/**
 * 被代理的目标类，因为本次测试是基于cglib实现的，所以不需要有实现接口
 * */
public class Target {
	public void update(String uid){
		System.out.println("修改用户【"+uid+"】的信息");
	}
	
	public void query(){
		System.out.println("查询全部数据...");
	}
}
