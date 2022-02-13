<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.maker.util.*,java.util.*" %>
 <% 
 request.setCharacterEncoding("UTF-8");
 String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>文件上传处理</title>
</head>
<body>
<%
 ParameterUtil pu=new ParameterUtil(request);
 String name=pu.getParameter("name");
 String[] role=pu.getParameterValues("role");
 List<String> pic=pu.SaveFile("pic");
%>
<h2>name：<%=name %></h2>
<h2>role：<%=Arrays.toString(role) %></h2>
<% for(String n : pic){ %>
<img  src="<%=path%>/upload/<%=n%>" width="192px" height="108px">
<%}
pu.clean();
%>
</body>
</html>