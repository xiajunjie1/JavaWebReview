<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>request测试2</title>
</head>
<body>

<h2>内容为：<%=request.getAttribute("test2") %></h2>
<h2>session内容为：<%=session.getAttribute("stitle") %></h2>

</body>
</html>