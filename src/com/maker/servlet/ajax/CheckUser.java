package com.maker.servlet.ajax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maker.database.DB_Util;
/**
 * 用来验证用户名是否存在
 * */
@WebServlet("/ajax/checkuser.action")
public class CheckUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String username=req.getParameter("uname");
		Connection con=DB_Util.getConn();
		String sql="Select count(*) from user where username=?";
		String flag="true";
		if(username!=null &&!("".equals(username))){
		try{
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			long row=rs.getLong(1);
			if(row>0){
				//用户名存在，不可用
				flag="false";
				//resp.getWriter().print(flag);//尽量不要添加换行空格等，防止返回的值里面出现意料之外的结果
				break;
			}
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DB_Util.closeCon();
			resp.getWriter().print(flag);
		}
		}else{
			resp.getWriter().print("false");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req,resp);
	}

}
