package message.maker.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.maker.handler.ExportHandle;
import message.maker.util.DateConvert;
/**
 * 通过访问Servlet来进行导出
 * 	通过Servlet来进行导出xml，那么就需要设置一个正确MIME显示类型
 * 	
 * */
@WebServlet("/export.action")
public class ExportServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/xml;charset=UTF-8");//设置MIME类型
		Date beginDate=DateConvert.StringToDate(req.getParameter("begin"));
		Date endDate=DateConvert.StringToDate(req.getParameter("end"));
		resp.getOutputStream().println(new ExportHandle(beginDate,endDate).getData());
		String filename="xia-"+req.getParameter("begin")+"."+req.getParameter("end")+".xml";
		resp.setHeader("Content-Disposition","attachment;filename="+filename);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
