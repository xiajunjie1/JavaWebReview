<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pageContext接收数据</title>
</head>
<body>
<h2>req请求：<%=request.getAttribute("req") %></h2>
<h2>Seon请求：<%=session.getAttribute("seon") %></h2>
</body>
</html>