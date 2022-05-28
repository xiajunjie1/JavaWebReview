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

<title>表单上传</title>
</head>
<body>
<form action="<%=path %>/user/getparam" method="post">
<div>
<label>用户名：</label>
<input type="text" name="username">

</div>
<div>
<label>年龄</label>
<input type="text" name="age"/>
</div>

<div>
<label>住址</label>
<input type="text" name="addr" />
</div>

<div>
<input type="checkbox" name="hobby" value="足球">足球
<input type="checkbox" name="hobby" value="篮球">篮球
<input type="checkbox" name="hobby" value="羽毛球">羽毛球
<input type="checkbox" name="hobby" value="乒乓球">乒乓球
</div>
<div>
<button type="submit">提交</button>
</div>
</form>
</body>
</html>