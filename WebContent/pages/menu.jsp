<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>菜单</title>

<style type="text/css">
#menu{
	background:url(<%=request.getContextPath()%>/img/menu.jpeg);
	position:relative;
	min-height:500px;
	top:-20px;
	border:2px solid #929292;
	padding:5px;
	padding-top:15px
}

.panel-default{
	background:RGBA(80,80,80,0.5)
}

a:hover{
	color:#f2f2f2;
}
a:visited{
color:#f2f2f2;
}
a{
color:#f2f2f2
}

.contain{
	margin:1px;
	padding:1px;
	min-height:500px;
}


</style>
</head>

<body >
<div class="col-lg-12 contain">
<div id="menu" class="col-lg-2">
<div class="panel-group" id="accordion">
	<div class="panel panel-default">
		<div class="panel-heading " style="background:#000;color:#fff">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-list-alt"></span>
				<a data-toggle="collapse" data-parent="#accordion" 
				   href="#collapseOne">
					个人事务
				</a>
			</h4>
		</div>
		<div id="collapseOne" class="panel-collapse collapse in">
			<div class="panel-body">
				<li><a href="#">个人信息</a></li>
				
			</div>
		</div>
		<div class="panel panel-default">
		<div class="panel-heading" style="background:#000;color:#fff">
			<h4 class="panel-title">
				<span class="glyphicon glyphicon-cog"></span>
				<a data-toggle="collapse" data-parent="#accordion" 
				   href="#collapseTwo">
					系统管理
				</a>
			</h4>
		</div>
		<div id="collapseTwo" class="panel-collapse collapse">
			<div class="panel-body">
				<li><a href="<%=request.getContextPath()%>/role/rolelist" target="contentframe">角色管理</a></li>
				<li><a href="<%=request.getContextPath()%>/user/listuser" target="contentframe">用户管理</a></li>
			</div>
		</div>
	</div>
	
</div>

</div>

</div>

</body>
</html>