package com.maker.servlet.jstl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.maker.el.vo.*;
@WebServlet("/JSTL.do")
public class JSTLSevlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Dept> list=new ArrayList<>();
		for(int i=0;i<3;i++){
			Dept dept=new Dept();
			dept.setDno(100+i);
			dept.setDname("Dept-"+i);
			dept.setLoc("武汉");
			list.add(dept);
		}
		req.setAttribute("ldept", list);
		req.getRequestDispatcher("/JSTL/jstl_test.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
