<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆成功</title>
<link rel="stylesheet" href="../css/bootstrap.min.css"/>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
</head>
<body>
<%request.setCharacterEncoding("utf-8");
//String user=request.getParameter("user");
String user="";
if(session.getAttribute("uname")!=null){
	if((user=session.getAttribute("nickname").toString())!=null){
		
	}else{
		user=session.getAttribute("uname").toString();
	}

%>
<div class="jumbotron col-lg-6 col-lg-offset-4">
    <div class="container">
        <h1>欢迎登陆页面！</h1>
        <p><h2>欢迎<%=user %>访问！</h2></p>
        <a class="btn btn-primary" href="logout.jsp">注销用户</a>
        
    </div>
</div>
<%}
else{
	
%>
<div class="jumbotron col-lg-6 col-lg-offset-4">
    <div class="container">
        <h1>禁止访问！</h1>
        <p><h2>未登录用户禁止访问！3s后进入登录面</h2></p>
        
    </div>
</div>
<%
response.addHeader("refresh","3;url=index.jsp");
} 

%>
</body>
</html>