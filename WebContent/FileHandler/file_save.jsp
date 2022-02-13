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

<title>存储文件</title>
</head>
<body>
<%
String fpath=this.getServletContext().getRealPath("/upload/myfile.txt");
File file=new File(fpath);

File pfile=file.getParentFile();
if(!pfile.exists()){
	pfile.mkdirs();
}
if(!file.exists()){
	file.createNewFile();
}

PrintWriter pw=new PrintWriter(file);
pw.append("this is my test\n");
pw.append("中文测试\n");
pw.close();
%>
</body>
</html>