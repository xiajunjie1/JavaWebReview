<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>config内置对象</title>
</head>

<%--
	config可以获取初始化配置参数的内容
	它获取的初始化参数配置在<servlet>标签中，形式如下：
		<init-param>
			<param-name></param-name>
			<param-value></param-value>
		</init-param>
		
	application的初始化参数，加载一次，一般用来作为全局参数
	config的初始化参数可以重复加载，一般用来作为局部参数
	
	
			
		
 --%>
<body>
<%=config.getServletName() %>
<br/>
<%=config.getInitParameterNames() %>
<br/>

</body>
</html>