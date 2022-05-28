<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@page import="java.util.List,com.maker.UserManger.domain.Role" %>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>角色页展示</title>
<style type="text/css">
	.headform{
		border-bottom:3px solid #f2f2f2;
	}
</style>

</head>
<body>
<table class="table table-hover">
<caption>
<div style="margin:5px"><h3>用户管理</h3></div>
<div class="headform">
	<a href="<%=path %>/pages/Roleadd.jsp" class="btn btn-default">添加</a>
	<a href="javascript:location.reload()" class="btn btn-default">刷新</a>
</div>
</caption>

<thead>
<tr>
<th width=10%>角色ID</th>
<th width=20%>角色名</th>
<th>角色描述</th>
<th width=5%>操作</th>
</tr>
</thead>
<tbody>
	<% 
	List<Role> rlist=(List<Role>)request.getAttribute("rlist");
	for(int i=0;i<rlist.size();i++){ %>
	<tr>
	<td><%=rlist.get(i).getId() %></td>
	<td><%=rlist.get(i).getRoleName() %></td>
	<td><%=rlist.get(i).getRoleDesc() %></td>
	<td><a href="#" class="btn btn-danger">删除</a></td>
	</tr>
	<%} %>
	</tbody>
</table>
</body>
</html>