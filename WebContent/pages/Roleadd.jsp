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
<style type="text/css">
td{
	width:25%;
	text-align:center;
}
</style>
<title>添加角色</title>
</head>
<body>
<form action="<%=path%>/role/roleadd">
<table class="table table-bordered">
	<caption><div><h3>角色添加</h3></div><div style="float:right;">
	<a class="btn btn-default" href="<%=request.getContextPath()%>/role/rolelist">返回</a></div></caption>
	<thead></thead>
	<tbody>
	<tr>
	<td class="well well-sm">角色名称</td>
	<td><input type="text" name="roleName"></td>
	<td class="well well-sm">角色描述</td>
	<td><input type="text" name="roleDesc"></td>
	</tr>
	<tr>
		<td class="well well-sm" colspan="4"><button type="submit" class="btn btn-primary">添加</button>
			<button type="reset" class="btn btn-info">重置</button>
		</td>
	</tr>
	</tbody>
</table>
</form>
</body>
</html>