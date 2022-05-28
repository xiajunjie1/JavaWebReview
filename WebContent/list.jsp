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

<title>传送集合参数</title>
</head>
<body>
<form action="<%=path %>/user/getlistparam" method="post">
<!-- 第一个集合参数的值 -->
<div>
<label>用户名：</label>
<input type="text" name="user[0].username">

</div>
<div>
<label>年龄</label>
<input type="text" name="user[0].age"/>
</div>

<div>
<label>住址</label>
<input type="text" name="user[0].addr" />
</div>
<hr>
<!-- 第2个集合参数的值 -->
<div>
<label>用户名：</label>
<input type="text" name="user[1].username">

</div>
<div>
<label>年龄</label>
<input type="text" name="user[1].age"/>
</div>

<div>
<label>住址</label>
<input type="text" name="user[1].addr" />
</div>

<div>
<button type="submit">提交</button>
</div>
</form>
</body>
</html>