<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.maker.database.*,com.maker.dao.*" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录验证</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
Userdao_imp userdao=new Userdao_imp(); 
String username=request.getParameter("username");
String password=request.getParameter("password");

User user=userdao.getUser(username);

if(user==null){
	%>
	<jsp:forward page="index.jsp">
	<jsp:param value="用户不存在！请检查用户名" name="errmsg"/>
	</jsp:forward>
	<%
} else{
	if(!(user.getPassword().equals(password))){
	%>
	<jsp:forward page="index.jsp">
	<jsp:param value="密码错误！" name="errmsg"/>
	</jsp:forward>
<% }else{
	String nickname=user.getNickname();
	session.setAttribute("uname", username);
	session.setAttribute("nickname", nickname);
	%>
	
	<jsp:forward page="welcome.jsp">
	<jsp:param value="<%=user.getNickname() %>" name="user"/>
	</jsp:forward>
	

<%
}
	}
%>


</body>
</html>