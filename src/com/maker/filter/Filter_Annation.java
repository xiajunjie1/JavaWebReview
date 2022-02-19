package com.maker.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 注解实现过滤器：
 * 	注解中的参数可以参考Servlet中的参数
 * 
 * 过滤器的执行顺序
 * 	在一个项目中考虑到代码结构的设计需要，有可能有多个过滤器，但是如果说有多个过滤器的时候，这多个过过滤器按什么顺序执行呢
 * 	默认情况下，过滤器的执行顺序按照过滤器的类名称的字母顺序进行执行。
 * 	注意：过滤器的执行顺序是由其自身的类名称来决定的，和filter-name没有任何直接联系
 */
@WebFilter(urlPatterns = { "/Login/*" })//过滤Login下的所有文件
public class Filter_Annation implements Filter {

    /**
     * Default constructor. 
     */
    public Filter_Annation() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		System.out.println("登录过滤");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
