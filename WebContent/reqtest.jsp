<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>request测试</title>
</head>
<body>

<h2>内容为：<%=request.getAttribute("test") %></h2>
<h2>session内容为：<%=session.getAttribute("stitle") %></h2>

<%request.setAttribute("test2", request.getAttribute("test")); 
%>
<!-- 此处如果继续用服务器跳转，那么下一个页面还是可以获取到request的属性-->
<a href="reqtest2.jsp">客户端跳转</a>
</body>
</html>