<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@page import="java.util.*,com.maker.UserManger.domain.*" %>
 <% String path=request.getContextPath();
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>用户页展示</title>
<style type="text/css">
	.headform{
		border-bottom:3px solid #f2f2f2;
	}
</style>

<script type="text/javascript">
	var user_del=function(url){
		if(confirm("确定删除该用户吗？")){
		console.log(url);
		location.href=url;
	}
	}

</script>

</head>
<body>
<table class="table table-hover">
<caption>
<div style="margin:5px"><h3>用户管理</h3></div>
<div class="headform">
	<a href="<%=path %>/user/SaveUi" class="btn btn-default">添加</a>
	<a href="javascript:location.reload()" class="btn btn-default">刷新</a>
</div>
</caption>

<thead>
<tr>
<th width=10%>用户ID</th>
<th width=20%>用户名</th>
<th width=20%>邮箱地址</th>
<th width=20%>电话号码</th>
<th width=25%>角色</th>
<th width=5%>操作</th>
</tr>
</thead>
<tbody>
	<% 
	List<User> ulist=ulist=(List<User>)request.getAttribute("userlist");
	if(ulist==null){
		ulist=new ArrayList<>();
	}
	for(int i=0;i<ulist.size();i++){ %>
	<tr>
	<td><%=ulist.get(i).getId()==null?"":ulist.get(i).getId() %></td>
	<td><%=ulist.get(i).getUsername()==null?"":ulist.get(i).getUsername() %></td>
	<td><%=ulist.get(i).getEmail()==null?"":ulist.get(i).getEmail() %></td>
	<td><%=ulist.get(i).getPhoneNum()==null?"":ulist.get(i).getPhoneNum() %></td>
	<%
		StringBuffer sb=new StringBuffer();
		for(Role role : ulist.get(i).getRole()){
				sb.append(role.getRoleName()+" ");
		}
	%>
	<td ><%=sb %></td>
	
	<td><a href="#" onclick="user_del('<%=path %>/user/userdel/<%=ulist.get(i).getId() %>')" class="btn btn-danger">删除</a></td>
	</tr>
	<%} %>
	</tbody>
</table>
</body>
</html>