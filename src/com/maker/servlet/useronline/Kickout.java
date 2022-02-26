package com.maker.servlet.useronline;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/kickout.action")
public class Kickout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid=req.getParameter("uid");
		Map<String,Boolean> map=(Map<String, Boolean>) req.getServletContext().getAttribute("online");
		if(map.containsKey(uid)){
			//如果该用户还未被删除
			map.put(uid, true);//将它的状态置为true表示已剔除
		}
		req.getServletContext().setAttribute("online", map);
		resp.sendRedirect("OnlineUserManager/admin/userlist.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
