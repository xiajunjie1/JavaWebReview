package com.maker.servlet.el;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.maker.el.vo.*;
import java.util.*;
@WebServlet("/eltest2.do")
public class JumpServlet2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Dept> depts=new ArrayList<>();
		Map<Long,Dept> map=new HashMap<>();//由于EL表达式中，调取Map的时候，key不支持Integer类型，但是支持Long类型
		for(int i=0;i<5;i++){
			Dept dept=new Dept();
			dept.setDname("Dept-"+i);
			dept.setDno(100+i);
			dept.setLoc("wuhan");
			depts.add(dept);
		}
		for(int j=0;j<5;j++){
			Dept dept=new Dept();
			dept.setDname("Dept~"+j);
			dept.setDno(200+j);
			dept.setLoc("beijing");
			map.put((long)j, dept);
			
		}
		
		req.setAttribute("dlist", depts);
		req.setAttribute("mlist", map);
		req.getRequestDispatcher("/EL/EL_test3.jsp").forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
