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

<title>修改SessionID</title>
</head>
<body>
<%

request.changeSessionId();//修改SessionID

%>
<h2><%=session.getAttribute("name").toString() %></h2>

<%session.removeAttribute("name");
	session.invalidate();
%>
</body>
</html>