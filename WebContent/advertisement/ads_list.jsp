<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.maker.dao.*,java.util.*" %>
 <% String path=request.getContextPath();
 	request.setCharacterEncoding("UTF-8");
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>广告列表</title>
</head>
<body>
<nav class="navbar navbar-default">
<div class="container-fluid">
<div class="navbar-header">
<a class="navbar-brand" href="index.jsp;">广告展示</a>
</div>
<div>
<ul class="nav navbar-nav">
<li><a href="ads_add.jsp">新增广告</a></li>
<li><a href="javascript:;">广告列表</a></li>
</ul>
</div>
</div>
</nav>

<table class="table table-hover">
<caption>广告列表</caption>
<thead>

<tr>
<th>标题</th>
<th>链接</th>
<th>图片</th>
<th>备注</th>
<th>操作</th>
</tr>
</thead>
<tbody>
<%
Adsdao adao=new Adsdao();
List<Ads> alist=adao.listad();
for(Ads ad : alist){
%>
<tr>
<td><%=ad.getTitle() %></td>
<td><a href="<%=ad.getUrl()%>"><%=ad.getUrl() %></a></td>
<td><img src="<%=path %>/upload/ad/<%=ad.getPhoto() %>" width="100px" height="50px" /></td>
<td><%=ad.getNote() %></td>
<td><a class="btn btn-primary" href="ads_edit.jsp?aid=<%=ad.getAid()%>">编辑</a>&nbsp;<a class="btn btn-danger" href="ads_delete.jsp?aid=<%=ad.getAid()%>">删除</a></td>
</tr>
<%} %>
</tbody>
</table>
</body>
</html>