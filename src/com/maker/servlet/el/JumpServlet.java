package com.maker.servlet.el;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.maker.el.vo.*;
@WebServlet("/eltest.do")
public class JumpServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Emp emp=new Emp();
		Dept dept=new Dept();
		emp.setEno(101);
		emp.setName("xia");
		dept.setDname("deptA");
		dept.setDno(110);
		dept.setLoc("wuhan");
		emp.setDept(dept);
		req.setAttribute("emp", emp);
		req.getRequestDispatcher("/EL/EL_test2.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
