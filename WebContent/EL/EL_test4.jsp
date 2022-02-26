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

<title>EL运算符</title>
</head>
<%--
EL运算符
	EL作为输出操作的支持语法，但是进行数据输出之前进行一些数据的简单计算，所以这个时候就可以通过EL运算符来进行处理了
	
 --%>
<body>
${10 gt 20?"正确的":"错误的"}
</body>
</html>