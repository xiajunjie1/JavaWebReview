package com.maker.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.maker.UserManger.domain.User;

/**
 * 进行登录验证，拦截未登录的访问请求
 * */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user=(User) request.getSession().getAttribute("user");
		//判断用户是否登录，如果登录，则在session当中保存有用户信息，若没有则视为未登录或者登录状态异常
		//若用户未登录，则转发到登录页面
		if(user==null||user.getUsername()==null||"".equals(user.getUsername())){
			response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
			return false;
		}
		return true;
	}

}
