<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.maker.dao.*,com.maker.util.*,java.io.*" %>
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

<title>删除处理</title>
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
if(request.getParameter("aid")!=null){ 
int aid=Integer.parseInt(request.getParameter("aid"));
 Adsdao adao=new Adsdao();
 Ads ad=new Ads();
 ad=adao.getAdsByid(aid);
 String pic=ad.getPhoto();
 File file=new File(request.getServletContext().getRealPath("/upload/ad/")+pic);
 if(file.exists()){
	 file.delete();
 }
 int result=adao.deleteAdByid(aid);

 if(result>0){
 
%>
<h2>删除成功</h2>
<%}else{ %><h2>删除失败</h2><%}
 }
else
{ 
%>
<h2>该项不存在，无法删除</h2>
<%} %>
</body>
</html>