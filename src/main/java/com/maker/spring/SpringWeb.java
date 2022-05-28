package com.maker.spring;
/**
 * 在Servlet中获取Spring应用上下文，从而使用Spring
 * 	Spring提供获取应用上下文的工具
 * 		Spring提供一个监听器，ContextLoaderListener
 * 		该监听器内部加载Spring配置文件，创建应用上下文对象，并存储到ServletContext作用域中
 * 	ContextLoaderListener的使用
 * 		1.导入Spring-web的坐标，在web.xml文件中配置该监听器,同时要配置全局变量告知其配置文件的位置
 * 		2.使用WebApplicationContextUtils获得应用上下文对象ApplicationContext
 * 
 * */
public class SpringWeb {

}
