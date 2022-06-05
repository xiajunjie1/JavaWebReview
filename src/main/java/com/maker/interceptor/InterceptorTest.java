package com.maker.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class InterceptorTest implements HandlerInterceptor {
	
	/**
	 * 在整个流程都执行完毕后
	 * */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion...");
	}
	
	/**
	 * 目标方法执行之后，视图返回之前执行
	 * 	执行目标方法后，可以对ModelAndView的值进行修改
	 * */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle...");
	}

	
	/**
	 * 在目标方法执行之前执行
	 * 	返回值如果为false，那么表示不放行，后面的目标方法以及拦截器相应的方法均不执行
	 * 	返回值为true则表示放行
	 * */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle...");
		return true;
	}

}
