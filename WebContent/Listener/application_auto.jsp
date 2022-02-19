<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="java.util.*" %>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>application初始化信息</title>
</head>
<!-- 观察application在Web容器启动时，进行的初始化属性设置 -->
<body>
<%
Enumeration<String> enu=application.getAttributeNames();
while(enu.hasMoreElements()){
	String name=enu.nextElement();
	%>
	<p><%=name %>=<%=application.getAttribute(name) %></p><br/>
	<% 
}
%>
</body>
</html>