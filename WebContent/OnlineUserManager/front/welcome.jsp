<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>欢迎登录</title>
</head>
<body>
<h2>欢迎访问：</h2><%=session.getAttribute("userid") %><br/>
总用户信息：<%=application.getAttribute("online") %>
总用户数：<%=application.getAttribute("uv") %>
</body>
</html>