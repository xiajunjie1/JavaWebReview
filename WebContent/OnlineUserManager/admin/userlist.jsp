<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.*" %>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>用户列表</title>
</head>
<body>
<table class="table table-hover">
<caption>用户列表</caption>
<thead>

<tr>
<th>用户</th>
<th>状态</th>
<th>操作</th>

</tr>
</thead>
<tbody>
<%Map<String,Boolean> map=(Map<String,Boolean>)application.getAttribute("online"); 
 for(Map.Entry<String,Boolean> entry : map.entrySet()){
	 String uid=entry.getKey();
	 boolean status=entry.getValue();
%>
<tr>
<td><%=uid %></td>
<td>
<%if(status==true){ %>
<span class="label label-danger">已剔除</span>
<!-- 状态为false，代表管理员已经强制下线，在利用过滤器拦截每个用户session，查询相应状态，
如果发现状态为false，即调用Session.invalidate()，监听器监听到session销毁，将application
中对应的用户删除掉
-->
<%} else{%>
<span class="label label-success">在线</span><%} %></td>
<td><a class="btn btn-danger" href="<%=path%>/kickout.action?uid=<%=uid%>">强制下线</a></td>
</tr>
<%} %>
</tbody>
</table>
</body>
</html>