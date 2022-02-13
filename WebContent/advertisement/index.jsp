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

<title>广告</title>
</head>
<body>
<nav class="navbar navbar-default">
<div class="container-fluid">
<div class="navbar-header">
<a class="navbar-brand" href="javascript:;">广告展示</a>
</div>
<div>
<ul class="nav navbar-nav">
<li><a href="ads_add.jsp">新增广告</a></li>
<li><a href="ads_list.jsp">广告列表</a></li>
</ul>
</div>
</div>
</nav>

<div id="myCarousel" class="carousel slide col-lg-offset-3 col-lg-5">
	<!-- 轮播（Carousel）指标 -->

	<!-- 轮播（Carousel）项目 -->
	<div class="carousel-inner">
	<% 
	Adsdao adao=new Adsdao();
	List<Ads> list=adao.listad();
	int active=0;
	String cls1="item active";
	String cls2="item";
	for(Ads ad : list){
	%>
		<div class="<%=active++==0?cls1:cls2%>">
			<a href="<%=ad.getUrl()%>"><span style="font-size:20px;position:relative;left:40%;"><%=ad.getTitle() %></span><img src="<%=path %>/upload/ad/<%=ad.getPhoto() %>"  width=300px height=150px style="position:relative;left:20%;"/></a>
			<span><%=ad.getNote() %></span>
		</div>
		<%} %>
	</div>
	<!-- 轮播（Carousel）导航 -->
	<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
		<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		<span class="sr-only">Previous</span>
	</a>
	<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
		<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		<span class="sr-only">Next</span>
	</a>
</div> 
</body>
</html>