package com.maker.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 通过过滤器，统一设置编码
 * 考虑到有可能在外部根据不同的项目动态的设置编码，此类过滤器一般都采用web.xml的方式进行配置
 * */
public class Encoding_Filter implements Filter {
	private final String DefaultEncoding="UTF-8";
	private String charset;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		// 为程序设置编码
		req.setCharacterEncoding(this.charset);
		resp.setCharacterEncoding(this.charset);
		//resp.setContentType("text/html;charset="+this.charset);
		fc.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 加载初始化参数，如果有字符编码的初始化参数，则给charset赋值，若没有则给charset赋默认值
		this.charset=config.getInitParameter("charset");
		if(this.charset==null||"".equals(this.charset)){
			//无初始化参数
			this.charset=this.DefaultEncoding;
		}

	}

}
