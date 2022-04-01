package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.service.IsitesService;
import com.mvc.util.factory.ObjectFactory;
/**
 * 控制层Servlet，处理客户端请求，同时调用模型层（业务层）代码
 * 控制层不做任何显示处理，仅将处理的结果返回给前端
 * 业务层（模型层）作为一个独立的模块，可以放到任意一个项目中执行
 * 
 * 但是以上这种传统的MVC会存在有一个问题，就是业务的调用依赖于Servlet，当要处理的业务比较多的时候
 * Servlet就会非常的多，所有的Servlet属于控制层，那么控制层最终一定要调用业务层进行业务处理
 * 不管是控制层获取业务层控制实例还是业务层获取数据层的实例都需要通过工厂类来手工实现对象的获取操作
 * 这种直接依托类关联的操作结构是传统项目中最大的弊端
 * 
 * 参数以及VO对象之间的转换处理操作，往往都需要手工实例化VO类的对象，而后进行对象属性的配置以及相关
 * 参数的数据类型转换处理操作，这种操作一定是严重重复的
 * */
@WebServlet("/site.action")
public class SiteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IsitesService siteService=ObjectFactory.getServiceInstance("sites.service",IsitesService.class);
		try {
			req.setAttribute("sites",siteService.getSites());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("/site.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
