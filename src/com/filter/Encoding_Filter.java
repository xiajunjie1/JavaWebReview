package com.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class Encoding_Filter implements Filter {

	private static final String DEFAULT_ENCODING="UTF-8";
	private String charset;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest sr, ServletResponse srp, FilterChain fc)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		sr.setCharacterEncoding(this.charset);
		srp.setCharacterEncoding(this.charset);
		fc.doFilter(sr, srp);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.charset=config.getInitParameter("charset");
		if(this.charset==null || "".equals(this.charset)){
			this.charset=this.DEFAULT_ENCODING;
		}
	}

}
