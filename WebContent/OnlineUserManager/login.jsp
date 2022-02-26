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

<title>登录</title>
</head>
<body>
<%String errmsg=request.getParameter("errmsg");
	if(errmsg!=null&&!"".equals(errmsg)){
%>
<h2>tip:<%=errmsg %></h2>
<%} %>
<form action="<%=path%>/LoginServlet.action" class="form-horizontal" method="post">
<div class="input-group">
<input type="text" class="form-control"  id="username" name="username" placeholder="请输入用户名" />

<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码"/>

</div>
<div class="input-group">
<button type="submit" class="btn btn-success">提交</button>
</div>
</form>
</body>
</html>