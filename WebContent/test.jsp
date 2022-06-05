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

<title>Test</title>
</head>
<body>
<h2>测试页面</h2>
<h1>${name }</h1>
</body>
</html>