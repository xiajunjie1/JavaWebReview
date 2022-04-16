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

<title>表单输入</title>
</head>
<body>
<div class="col-lg-offset-4 col-lg-8">
<form action="mix.action" method="post" enctype="multipart/form-data">
	<div class="input-group">
	<input type="text" name="deptno" placeholder="请填写部门号"/><span>${errmsg.deptno }</span>
	</div>
	<div class="input-group">
	<input type="text" name="deptname" placeholder="请填写部门名称"/><span>${errmsg.deptname }</span>
	</div>
	<div class="input-group"> 
	<input type="text" name="loc" placeholder="请填写部门所在地"/><span>${errmsg.loc }</span> 
	</div>
	<div class="input-group">
	<label for="hobby">兴趣:</label>
	<input type="checkbox" value="游戏" name="hobby">游戏
	<input type="checkbox" value="音乐" name="hobby">音乐
	<input type="checkbox" value="电影" name="hobby">电影
	<input type="checkbox" value="运动" name="hobby">运动<span>${errmsg.hobby }</span>
	</div>
	<div class="input-group">
	<input type="file" name="photo"> ${errmsg.photo }
	</div>
	<div class="input-group">
	<button type="submit" class="btn btn-primary">提交</button>
	</div>
</form>
</div>
</body>
</html>