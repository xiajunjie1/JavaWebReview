<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.maker.UserManger.domain.*" %>
 <% String path=request.getContextPath();
 	String uname="user";
 	if(session.getAttribute("user")!=null){
 		User u=(User)session.getAttribute("user");
 		uname=u.getUsername();
 	}
 	
 %>

 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>


<style type="text/css">
	.navbar{
		min-height:50px;
		background:url(<%=path%>/img/head.jpeg);
	}
</style>
<title>标题栏</title>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand " href="#"><h2 class="text-danger" style="position:relative;top:-20px;text-shadow: #f2f2f2 3px 2px 3px"><%=application.getInitParameter("sysName") %></h2></a>
    </div>
    <ul class="nav navbar-nav navbar-right">
     <li><a href="#"><img alt="" src="<%=path%>/img/ico.jpeg" width=30px height=30px class="img-circle"></a></li>
      <li> <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" style="background:url(<%=path%>/img/head.jpeg);height:30px;position:relative;top:13px;border:none;">
      <%=uname %><span class="caret"></span></button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="#">个人信息</a></li>
        <li><a href="#">注销</a></li>
      </ul>
      </li>
     
    </ul>
  </div>
</nav>



</body>
</html>