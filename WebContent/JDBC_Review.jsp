<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*" %><%--JDBC驱动包 --%>
<%@ page import="com.maker.database.*" %>
    <%--
    	想要在JavaWeb中操作数据库，则需要在Tomcat中配置相应的驱动包
    	配置驱动程序，可以考虑将jar包拷贝到Tomcat目录下的lib中
    
     --%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据库测试</title>
</head>
<body>
<%! 
/*
public static final String driver="com.mysql.cj.jdbc.Driver";	
public static final String url="jdbc:mysql://localhost:3306/chat?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
public static final String username="root";
public static final String password="123456";
*/
%>
<table>
<th><tr>
<td>用户名</td>
<td>昵称</td>
</tr></th>
<tbody>
<%
	String sql="Select username,nickname from user";
	//Class.forName(driver);
	//Connection con=DriverManager.getConnection(url,username,password);
	Connection con=DB_Util.getConn();
	PreparedStatement pstat=con.prepareStatement(sql);
	ResultSet rs=pstat.executeQuery();
	while(rs.next()){
		String uname=rs.getString(1);
		String nickname=rs.getString(2);
		%>
		<tr>
		<td><%=uname%></td>
		<td><%=nickname %></td>
	</tr>
	<% }
	DB_Util.closeCon();//在Web程序中一定要记住关闭资源，否则它是关不上的，除非重启Tomcat服务器
	%>

</tbody>
</table>
</body>
</html>