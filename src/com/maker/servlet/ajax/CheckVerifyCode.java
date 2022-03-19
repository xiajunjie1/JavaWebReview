package com.maker.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ajax/checkcode.action")
public class CheckVerifyCode extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String code=req.getParameter("code");
		if(code!=null&&!("".equals(code))){
			if(code.equalsIgnoreCase(new String().valueOf(req.getSession().getAttribute("vcode")))){	
				resp.getWriter().print("true");//验证码输入正确
			}else{
				resp.getWriter().print("false");
			}
		}else{
			resp.getWriter().print("false");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
