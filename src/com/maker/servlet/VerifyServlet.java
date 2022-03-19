package com.maker.servlet;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maker.util.VerifyCode;
import com.maker.util.VerifyCodeImage;
@WebServlet("/verfycode.action")
public class VerifyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		//resp.setCharacterEncoding("UTF-8");
		
		String code=VerifyCode.getCode(4);
		System.out.println("@@@@@"+code);
		req.getSession().setAttribute("vcode", code);
		VerifyCodeImage vci=new VerifyCodeImage(code);
		//设置头信息，禁止图像缓存
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", -1);
		resp.setContentType("image/jpeg");
		ServletOutputStream sos=resp.getOutputStream();
		ImageIO.write(vci.createImage(), "jpeg",sos );
		sos.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
