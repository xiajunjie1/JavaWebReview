package com.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.commons.annotation.RequestMapping;
import com.mvc.commons.bean.ControllerRequestMapping;
import com.mvc.commons.bean.DependencyObject;
import com.mvc.commons.bean.ServletObject;
import com.mvc.util.ActionUtil;
import com.mvc.util.ParameterUtil;
import com.mvc.util.ResourceBundleUtil;
import com.mvc.util.scanner.ScannerPackageUtil;
import com.mvc.util.validate.ValidationRuleUtil;
import com.mvc.view.ModeAndView;

/**
 * 为了避免出现传统控制层中，多个Servlet的问题，采用Action代替传统的控制层
 * 	所有用户的请求都首先交给Action处理，而Action是通过DispatchServlet分发找到的
 * 	要想通过Servlet做分发，要依赖于两个注解：@Controller、@RequestMapping
 * 
 * Action的解析处理
 * 	Action是用户自己的定义的类，与WEB容器没有关系，但是在执行的时候，又需要去依赖WEB容器
 * 	因为如果没有WEB容器提供分发处理的机制，那么Action肯定是无法执行的，但是这个分发处理是通过
 * 	@RequestMapping的注解实现的
 * 	
 * 	在真正的分发处理之前，就需要进行一些Action解析上的处理，例如：需要将Action可以使用的路径
 * 	解析出来，而后找到与之匹配的方法，按照java反射机制实现方法调用的要求来讲：需要提供有Class类
 * 	而后还要提供Method类两个实例才可以完成，所以就需要根据不同的Action做出匹配，最佳的做法创建有一个
 * 	匹配的结构解析类
 * */
public class DispatchServlet extends HttpServlet {
	public static final String DEFAULT_ERROR_PAGE="/errors.jsp";//公共错误页
	private String validationBaseName;
	private String errorPageBaseName;
	private String messageBaseName;
	private String errorpage;//错误页的地址
	//【验证标记】true:未创建Validation.properties不需要验证，false:需要验证
	private boolean validateFlag;
	//【错误页标记】true：未创建ErrorPage.properties,未配置错误页，false：配置了错误页
	private boolean errorPageFlag;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//初始化的时候进行参数配置
		//获取ServletContext所配置的初始化上下文环境
		super.init(config);//需要调用该方法，让容器初始化一个ServletContext，否则会报空指针异常
		String basePackages=super.getServletContext().getInitParameter("basepackage");
		System.out.println("初始化："+basePackages);
		//扫描包
		ScannerPackageUtil.ScannerHandle(super.getClass(), basePackages);
		this.validationBaseName=super.getServletConfig().getInitParameter("validationBasename");
		this.errorPageBaseName=super.getServletConfig().getInitParameter("errorPageBasename");
		this.messageBaseName=super.getServletConfig().getInitParameter("messageBasename");
		if(this.messageBaseName!=null){
			//配置了消息资源
			ResourceBundleUtil.setMessageBasename(this.messageBaseName);
		}
		this.validateFlag=this.validationBaseName==null || "".equals(this.validationBaseName);
		this.errorPageFlag=this.errorPageBaseName==null || "".equals(this.errorPageBaseName);
		if(this.errorPageFlag){
			//没有配置具体的错误页
			this.errorpage=this.DEFAULT_ERROR_PAGE;
		}else{
			try{
				this.errorpage=ResourceBundle.getBundle(this.errorPageBaseName).getString("global.error.page");
			}catch(Exception e){
				this.errorpage=this.DEFAULT_ERROR_PAGE;
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println(req.getServletPath());//如果是localhost:8080/WebTest2/pages/message/test.action,那么返回的则是/pages/message/test.action
		//所以再进行分发处理的时候，就是针对于当前请求路径的内容进行分析，而后获取最终所需的访问路径。
		String path=req.getServletPath().substring(0,req.getServletPath().lastIndexOf("."));//去掉请求路径后的.action
		Map<String,ControllerRequestMapping>mapping= ScannerPackageUtil.getAction_Map();//获取映射
		//System.out.println(path);
		//System.out.println(mapping);
		ControllerRequestMapping rmapping=(ControllerRequestMapping) mapping.get(path);
		try {
			
			ServletObject.setThreadRequest(req);
			ServletObject.setThreadResponse(resp);
			Object actionObj=rmapping.getActionClazz().getDeclaredConstructor().newInstance();//反射获取Action类对象
			//通过解析到的action对象，获取上传路径，为实例化ParameterUtil做准备
			String uploadpath=ActionUtil.getUpload(actionObj);
			ParameterUtil pu=new ParameterUtil(req,uploadpath);
			//将ParameterUtil对象存到ServletObject中，这样在项目中就可以随时调用此工具对象了
			ServletObject.setParameterUtil(pu);
			//配置验证
			Map<String,String> errors=new HashMap<>();//保存错误信息
		
			if(!this.validateFlag){//创建了验证配置资源文件
				String rule=null;
				try{
					 rule=ValidationRuleUtil.getValidateRule(this.validationBaseName, rmapping);
				//System.out.println(rule);
				}catch(Exception e){
					System.err.println("未找到参数验证规则");
				}
				if(rule!=null){
					//需要验证
					errors=ValidationRuleUtil.getRule(rule);
				}
				
			}
			//System.out.println("【errors】--"+errors);
			System.out.println(ServletObject.getParameterUtil().getAllUploadFile());
		if(errors==null||errors.size()==0){//验证通过，没有错误正常执行
			//传入控制层对象Action
			DependencyObject depobj=new DependencyObject(actionObj);
			//对action中的业务层对象进行注入
			depobj.inject();
			//对匹配到的Action中的方法进行调用
			Method targetMethod=rmapping.getActionMethod();
			Object[] params=ActionUtil.getMethodParameterValue(actionObj, targetMethod);
			Object obj=targetMethod.invoke(actionObj,params);
			if(obj instanceof String){
				req.getRequestDispatcher(obj.toString()).forward(req, resp);
			}else if(obj instanceof ModeAndView){
				ModeAndView mv=(ModeAndView)obj;
				req.getRequestDispatcher(mv.getView()).forward(req, resp);
			}
			}else{//有错误信息
				req.getSession().setAttribute("errmsg", errors);
				if(!this.errorPageFlag){
					//配置了错误页
					String dispatchpath=null;
					try{dispatchpath=ValidationRuleUtil.getErrorpath(this.errorPageBaseName, rmapping);}catch(Exception e)
					{
						System.err.println("找不到局部错误页");
						dispatchpath=this.errorpage;
					}
					if(dispatchpath==null||"".equals(dispatchpath)){
						dispatchpath=this.errorpage;
					}
					System.err.println("当前错误页为："+dispatchpath);
					req.getRequestDispatcher(dispatchpath).forward(req, resp);
				}else{
					req.getRequestDispatcher(this.DEFAULT_ERROR_PAGE).forward(req, resp);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ServletObject.clean();
		} 
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);;
	}

}
