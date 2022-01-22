<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接收传递参数</title>
</head>
<body>
<%


request.setCharacterEncoding("utf-8");
String title=request.getParameter("title");
String content=request.getParameter("content");


%>
<h2>标题是：<%=title %></h2><br/>
<h2>内容是：<%=content %></h2>
</body>
</html>