package com.maker.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Login_Filter implements Filter{
	private String auth;//接收初始化参数，标记验证哪个字段

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest res, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest hres=(HttpServletRequest)res;
		//System.out.println(hres.getServletPath());
		if(this.auth==null||"".equals(this.auth)){
			//不需要验证
			fc.doFilter(res, resp);
		}else{
			if(hres.getServletPath().equals("/Login/index.jsp")||hres.getServletPath().equals("/Login/check.jsp")){
				fc.doFilter(res, resp);
			}else if(hres.getSession().getAttribute(this.auth)!=null)
			{
				//属性存在，已经正常登录
				fc.doFilter(res, resp);
			}else{
				System.out.println("用户未登录");
				res.getRequestDispatcher("/Login/index.jsp?errmsg=请登录后再访问").forward(res, resp);
			}
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.auth=config.getInitParameter("auth");
		
		

	}

}
