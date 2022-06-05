package com.maker.interceptor;
/**
 * SpringMVC拦截器
 * 	SpringMVC拦截器类似于Servlet中的过滤器Filter，用于对处理器进行预处理和后处理
 * 	将拦截器按一定的顺序联结成一条链，这条链称为拦截器链。在访问被拦截的方法或字段时，拦截器链中的
 * 	拦截器就会按照之前其定义的顺序被调用，拦截器也是AOP思想的具体实现
 * 
 * 	Interceptor和Filter的区别
 * 		使用范围：
 * 			Filter是Servlet规范中的一部分，任何的Java Web工程都可以使用，interceptor是SpringMVC框架的
 * 			只能在SpringMVC中使用
 * 		拦截范围：
 * 			interceptor可以在配置了拦截范围后，单独配置不拦截的资源
 * 
 * 		自定义拦截器：
 * 			创建拦截器，实现HandlerInterceptor接口
 * 			配置拦截器
 * 			测试拦截器
 * 
 * 	多个Interceptor的执行顺序：
 * 		根据配置的顺序，配置在前面，preHandle先执行
 * 		先通过的interceptor，postHandle和afterCompletion则后执行
 * 
 * 	
 * 	关于拦截器拦截范围的匹配：
 * 		/* ： 匹配一级，即 /list , /add 等
     	/** ： 匹配多级，即 /add , /add/user, /add/user/… 等
		/**  匹配的范围更大
		 
 * 	interceptor拦截
 * 		interceptor可以拦截配置好拦截地址的Controller和静态资源
 * 		jsp和Servlet是不会被拦截器给拦截的，因为在访问的时候，找的到对应的Servlet类，那么自然就无法找到SpringMVC的DispatcherServlet核心控制器了
 * 		所以SpringMVC相关的功能都不会被调用
 * 
 * */
public class Springmvc_interceptor {

}
