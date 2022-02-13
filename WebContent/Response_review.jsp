<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%-- pageEncoding相当于设置了response.setCharacterEncoding --%>
    
 <%@ page import="java.io.*" %>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>Response复习</title>
</head>
<body>
<!-- 

response对象实现了HttpServletResponse接口，其父接口为ServletResponse接口，继承关系和ServletRequest类似
服务器端对客户端所做出的任何的输出内容实际上都属于响应，如果说现在服务器端要对用户请求的信息进行HTML的输出操作，实际上这个过程就是一个标准的响应
response中获取输出流实例的方法：
	public void flushBuffer()throws IOException:清空缓冲区
	public void resetBuffer():重置缓冲区
	public int getBufferSize():获取缓冲区大小
	public ServletOutputStream() getOutputStream()throws IOException:获取客户端输出字节流
	public PrintWriter getWriter() throws IOException
	
	
设置头信息：
	通过添加头信息进行跳转
	
请求重定向
	response中也提供有跳转的方法
	public void sendRedirect(String local)throws IOExcepiton
	当程序代码执行到此语句后，会自动跳转到处理操作，跳转后地址栏发生改变，属于客户端跳转
	
	关于服务器端跳转和客户端跳转需要注意的点：
		客户端跳转，会将所有的动态代码执行完毕后，再进行跳转。而服务器跳转则是遇到跳转语句后
		就会进行跳转，后面的语句不会在执行，那么就会存在一个问题。当在操作数据库等资源的时候
		如果在服务器跳转之后关闭资源，那么关闭资源的语句是不会执行的，这样就会出现资源关不上的情况
 -->
 <%!
 public int count=1;
 %>
 
 <%
 PrintWriter writer=response.getWriter();
 writer.println("<h2>response输出测试</h2>");
 
 if(count<5){
	 %>
	 <h2>正在等待跳转<%=count++ %></h2>
	 <%
	 //通过设置头信息进行刷新
	 response.addHeader("refresh", "1");
	 
 }else{
	 //通过设置头信息进行跳转，该跳转属于客户端跳转，地址栏会变
	 response.addHeader("refresh", "1;url=input.html");
 }
 %>
 
</body>
</html>