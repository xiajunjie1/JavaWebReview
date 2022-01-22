<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    %>
    
 <%@ page import="java.util.List,java.util.ArrayList" %><%--利用import导入多个包 --%>
 <%@ page import="java.time.*" %> <%--利用多个import导入多个包 --%>
    
    <%-- 在Web.xml统一配置了错误页，所以errorPage="ErrorPage.jsp"可以不用指定 --%>
    <%--errorPage指定错误页 --%>
    <%/*
    	定义页面显示编码：pageEncoding="UTF-8"
    		在一个页面之中只允许设置一个页面编码，如果设置的页面编码重复了，Tomcat会报语法错误
    		
    	MIME：在Email之中，都存在一个附件的概念，这个附件如果直接打开是可以关联本机的程序的
    		MIME是一种根据文件扩展名匹配相关应用程序的标识，page指令中可以根据contentType属性来实现MIME的定义
    		在传统的Web项目中，所有的JSP程序执行之后实际上最终所完成的功能就是HTML代码的输出，而之所以可以输出HTML代码
    		主要原因就在于其MIME定义的类型为text/html
    		JSP默认为text/html
    		如果想要改变其默认的数据显示类型，就可以利用MIME的方式来进行控制
    */ %>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Scriptlet复习</title>
</head>
<body>
	<%
	/*
		page指令：所有的JSP程序都是以“解释型”的方式进行运行的，并且所有的JSP程序
		最终都会转成"*.java"源代码，但是在进行这种转换的过程中，就需要对转换后的代码
		进行一些相应属性的配置，才可以实现最终所需要的目的，而这样的操作就可以通过page完成
		Page实际上描述的就是当前的JSP对象（类似于Java中的Object），相当于做了一些公共的要求
		
		page指令主要定义了一个JSP页面中的相关属性内容，例如：import导入系统包，MIME配置，错误页，IO缓冲区配置
		使用格式：《%@ page 属性名称1=属性内容1 属性名称2=属性内容2...%》
		
		错误页的配置，通过两个page属性进行定义：
			错误处理页：进行所有错误统一显示的的页面，这个页面上必须配置“isErrorPage=true”属性；
			程序页面：只要所有有关的JSP程序，都需要通过“errorPage”属性明确的找到错误处理页。
			
		地址栏不改变，但最终的显示页面跳转，这种不改变地址栏请求路径的跳转在JavaWeb之中成为“服务器跳转”，这种跳转在MVC设计模式之中，将有非常重要的设计意义
		
		
		若有需对JSP页面，要配置同一个错误地址，可以通过修改项目下/WEB-INF/web.xml配置文件实现统一的错误处理
		
		由于有多个状态码，为了简化错误配置，可以用以下方法
			<error-page>
				<exception-type>java.lang.Exception</exception-type>
				<location>/ErrorPage.jsp</location>
			</error-page>
		这种方式可以匹配多个状态码
		*/ 
		
		//int err=10/0;//制造一个Exception
		List<String> list=new ArrayList<>();
		list.add("item1");
		list.add("item2");
		list.add("item3");
		for(String str : list){
			%>
			<li><%=str %></li>
			<% 
		}
		%>
		
	
</body>
</html>