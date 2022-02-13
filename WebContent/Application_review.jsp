<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import="java.util.*,java.io.*" %>
 
 <% String path=request.getContextPath();%>
 
 <%--
 application:
 	描述Servlet上下文的内置对象，它的完整类型是：ServletContext,该接口的实现是在Tomcat中实现的
 	在每一个Tomcat中，实际上都可以配置多个虚拟目录，而不同的虚拟目录实际上就都属于各个不同的WEB上下文
 	每一个WEB上下文（WEB项目）都存在一个application上下文信息
 	
 	获取真实路径：
 		public String getRealPath(String path)
 		在进行path设置的时候所设置的内容就是WEB的访问路径
 		
 	getServletContext()方法可以代替application
 	
 	application初始化参数的设置
 		application的属性，本质上就是一个map集合，key固定位String，value固定位Object
 		application的属性，可以通过修改web.xml文件进行设置
 		具体为添加context-param标签
 		<context-param>
 			<param-name>...</param-name>
 			<param-value>...</param-value>
 		</context-param>
 		public String getInitParameter(String name)：获取指定名称的初始化参数
 		public Enumeration<String> getInitParameterNames()：获取全部初始化参数的名称
 		public boolean setInitParameter(String name,String value)：设置初始化参数
 
  --%>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>Application复习</title>
</head>
<body>
项目根路径为：<%=path %><br/>
真实路径为：<%=application.getRealPath(path) %><br/>
路径2为：<%=this.getServletContext().getRealPath("/") %><br/>

初始化参数：<%=application.getInitParameter("myparam") %><br/>

	<%
	PrintWriter pout=response.getWriter();
	Enumeration<String> names=application.getInitParameterNames();
	
	
	while(names.hasMoreElements()){
		String name=names.nextElement();
		System.out.println(name);
		pout.println("参数名称-"+name+"="+application.getInitParameter(name)+"<br/>");
	}
	%>

</body>
</html>