<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import="java.io.*" %>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>获取Cookie</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
Cookie[] cookies=request.getCookies();
PrintWriter pout=response.getWriter();
for(Cookie cook : cookies){
	pout.println("<h2>"+cook.getName()+"="+cook.getValue()+"</h2>");
}
%>
</body>
</html>