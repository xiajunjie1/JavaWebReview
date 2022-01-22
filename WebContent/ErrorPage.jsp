<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    <%-- 错误显示页，通过isErrorPage来标识该页面--%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页</title>
</head>
<body>
<h1>Sorry,出错了！</h1>
<hr>
<%=exception %><!-- exception描述了异常信息 -->
</body>
</html>