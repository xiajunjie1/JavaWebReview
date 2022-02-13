<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.maker.util.*,com.maker.dao.*" %>
 <% 
 request.setCharacterEncoding("UTF-8");
 String path=request.getContextPath();
 	
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>新增广告处理</title>
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
<li><a href="ads_list.jsp">广告列表</a></li>
</ul>
</div>
</div>
</nav>
<%
ParameterUtil pu=new ParameterUtil(request,"/upload/ad/");
Adsdao adao=new Adsdao();
Ads ads=new Ads();
ads.setTitle(pu.getParameter("title"));
ads.setUrl(pu.getParameter("url"));
;
ads.setNote(pu.getParameter("note"));
String name=pu.SaveFile("pic").get(0);
ads.setPhoto(name);
pu.clean();
int result=adao.add(ads);
if(result>0){
%>
<h2>添加成功!</h2>
<%}else{ %>
<h2>
添加失败
</h2><%
pu.deletFile(name);
} %>
</body>
</html>