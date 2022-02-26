<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.sql.*" %><%--原生的JDBC jar包 --%>
 <%@ page import="javax.naming.*" %><%--JNDI命名查找jar包 --%>
 <%@ page import="javax.sql.*" %><%--DataSource接口实例jar包 --%>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>获取数据库连接池的连接</title>
</head>
<!-- 
	使用Tomcat所提供的数据库连接池，首先需要到META-INF/content.xml中配置DataSource的信息
	之后需要到WEB-INF/web.xml中引用配置好的DataSource
	本次的数据库连接时通过JNDI查找到DataSource来获取最终所需要的Connection接口实例，简单起见，本次测试直接用JSP获取Connection
 -->
<body>
<%! /*
	DataSource名为jdbc/xia,此处这么定义与Tomcat的运行机制有关
	实际上进行JNDI资源查找的时候，一般都需要首先找到一个"java:comp/env/"资源，然后再利用此资源查找具体的JNDI资源
	如果说现在使用的是Oracle WebLogic或者是IBM WebSphere,会自动帮用户获取到该资源，但是用的免费Tomcat是无法自动获取到当前的资源
	所以要修改资源查找的名称
	*/
	public static final String DataSourceName="java:comp/env/jdbc/xia";
%>

<%
Context context=new InitialContext();//初始化名称查找上下文
DataSource datasource=(DataSource)context.lookup(DataSourceName);//查找JNDI资源
Connection conn=datasource.getConnection();//获取连接资源
%>
<h2>数据库连接池：<%=conn %></h2>
<%
conn.close();//将数据库连接还到资源池当中
%>
</body>
</html>