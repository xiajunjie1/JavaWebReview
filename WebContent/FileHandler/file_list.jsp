<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import="java.io.*,java.text.*" %>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>文件列表</title>
<style type="text/css">
table{
	width:600px;
	height:300px;
	position:absolute;
	left:200px;
	top:100px;
}

td{
border:1px soild #000;
width:200px;
}
</style>
</head>
<body>
<%
String dir=this.getServletContext().getRealPath("/upload");
File file=new File(dir);
if(!file.exists()){
	file.mkdirs();
}
File[] files=file.listFiles();
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<table  border="1" style="border:1px solid #000">
<thead>
<tr>
<th>文件名称</th>
<th>文件大小</th>
<th>最后修改日期</th>
</tr>
</thead>

<tbody>
<%for(int i=0;i<files.length;i++){
	File f=files[i];
	%>
	<tr>
	<td><a href="file_load.jsp?fname=<%=f.getName() %>"><%=f.getName() %></a></td>
	<td><%=f.length() %></td>
	<td><%=sdf.format(f.lastModified()) %></td>
	</tr>
	<% 
} %>

</tbody>
</table>
</body>
</html>