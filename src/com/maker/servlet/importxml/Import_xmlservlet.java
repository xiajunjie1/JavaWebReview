package com.maker.servlet.importxml;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maker.util.ParameterUtil;
import com.maker.xml.Import_xml;
@WebServlet("/importxml.action")
public class Import_xmlservlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		ParameterUtil pu=new ParameterUtil(req);//自定义的FileUpload组件工具类
		List<String> namelist=pu.getTempFilenames();//获取上传的文件的临时文件名
		String filename=req.getServletContext().getRealPath("/temp/")+namelist.get(0);
		System.out.println(filename);
		Import_xml importxml=new Import_xml(filename);
		try {
			importxml.saveData();
			resp.getWriter().println("数据导入成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.getWriter().println("数据导入失败");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
