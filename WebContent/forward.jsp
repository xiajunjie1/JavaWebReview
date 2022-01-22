<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳转测试</title>
</head>
<%--页面跳转
	1.跳转时不传递参数：
		《jsp:forward page="目标路径" /》
	2.跳转时传递参数
		《jsp:forward page="目标路径"》《jsp:param name="" value=""/》《/jsp:forward》
 --%>
<body>
<%
String message="my test";
if(message.contains("test")){
	request.setCharacterEncoding("utf-8");//在传递参数之前，一定要进行该设置，否则传递中文参数会出现乱码
	%>
	<jsp:forward page="Received.jsp">
	<jsp:param value="测试" name="title"/>
	<jsp:param value="<%=message %>" name="content"/>
	</jsp:forward>
	<%
	//该跳转，地址栏的地址不会发生变化，所以属于服务器跳转
}
else{%>
<h2>不包含参数</h2>
<% 
	
}
%>
</body>
</html>