<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.io.*" %>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>读取文件</title>
</head>
<body>
<% 
String name=request.getParameter("fname");
if(name==null||"".equals(name)){
	throw new Exception("文件参数传递错误");
}
File file=new File(this.getServletContext().getRealPath("/upload/"+name));
if(!file.exists()){
	throw new Exception("文件不存在");
}
BufferedReader br=new BufferedReader(new FileReader(file));
String line="";
while((line=br.readLine())!=null){
	%>
	<%=line%><br/>
	<%
}
br.close();
%>
</body>
</html>