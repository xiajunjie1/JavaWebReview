package com.maker.filter.useronline;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
@WebFilter(urlPatterns={"/OnlineUserManager/front/*"})
public class User_off implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest hreq=(HttpServletRequest)req;
		String uid=(String)hreq.getSession().getAttribute("userid");
		Map<String,Boolean> map=(Map<String, Boolean>) hreq.getServletContext().getAttribute("online");
		if(map.containsKey(uid)){
			boolean status=map.get(uid);
			if(status){//已移除
				hreq.getSession().invalidate();//销毁当前用户的session
				hreq.getRequestDispatcher("/OnlineUserManager/login.jsp?errmsg=该用户已被管理员强制离线").forward(req, resp);
			}else{
				fc.doFilter(hreq, resp);
			}
			
		}else{
			//该用户已移除
			hreq.getRequestDispatcher("/OnlineUserManager/login.jsp?errmsg=未登录").forward(hreq, resp);
			
		}
		
		

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
