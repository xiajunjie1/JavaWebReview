<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.maker.UserManger.domain.*" %>
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
<title>添加用户</title>
</head>
<body>
<form action="<%=path%>/user/saveuser">
<table class="table table-bordered">
	<caption><div><h3>用户添加</h3></div><div style="float:right;">
	<a class="btn btn-default" href="<%=request.getContextPath()%>/user/userlist">返回</a></div></caption>
	<thead></thead>
	<tbody>
	<tr>
	<td class="well well-sm">用户名</td>
	<td><input type="text" name="username"></td>
	<td class="well well-sm">邮箱</td>
	<td><input type="text" name="email"></td>
	</tr>
	<tr><td class="well well-sm">密码</td>
	<td><input type="password" name="password"></td>
	<td class="well well-sm">电话号码</td>
	<td><input type="tel" name="phoneNum"></td></tr>
	<tr>
	<td class="well well-sm">角色</td>
	<td colspan="3">
	<%List<Role> rlist=(List<Role>)request.getAttribute("rlist"); 
		for(Role r : rlist){
			
		
	%>
	<input type="checkbox" name="rids" value=<%=r.getId() %>><%=r.getRoleName() %>
	<%} %>
	</td>
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