<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import="java.io.*,java.util.*,org.apache.commons.fileupload.servlet.*" %>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>上传文件处理</title>
</head>
<body>
<%--
显示的内容为null
设置了文件上传后，request中的getParameter方法就失效了
如果想要进行参数的接收的话，需要使用ServletRequest接口中的如下方法:
	public ServletInputStream getInputStream()throws IOException
	
 --%>
<%--  <h2>name=<%=request.getParameter("name") %></h2>--%>
<%
/*
显示内容如下：
------WebKitFormBoundary7hmcFoelQUZJ0hNC Content-Disposition: form-data; name="name" test
------WebKitFormBoundary7hmcFoelQUZJ0hNC Content-Disposition: form-data; name="pic"; filename="bg.jpg" Content-Type: image/jpeg 二进制数据
如果由开发者直接对这些二进制流进行处理实在是过于繁琐了，所以一般会使用一些上传组件来进行简化
如Apache维护的FileUpload组件
需要注意的是，单独使用FileUpload组件还无法实现文件的上传，还要使用commons-io的组件
	除了以上可以见到的程序处理机制外，实际上现在对于整个上传部分（请求处理），都会存在一些不一样MIME信息
	
	Scanner sc=new Scanner(request.getInputStream());
	sc.delimiter().compile(";");
	response.setCharacterEncoding("utf-8");
	PrintWriter pw=response.getWriter();

	while(sc.hasNext()){
		pw.println(sc.next());
		
	}
	
*/


%>
<%--获取MIME请求信息 --%>
MIME=<%=request.getContentType() %>

<%
/*
FileUpload组件：
	DiskFileItemFactory：磁盘管理类，该类的主要功能是进行所有提交数据临时目录的指定
	ServletFileUpload：上传文件接收类，该类的主要功能是实现上传数据的接收，只要上传内容道临时目录之中，就可以利用此类接收
	
	所有上传的信息都保存在临时目录之中，而最终通过FileItem获取到的二进制数据，通过write方法保存到指定的目标路径之中
*/
ServletFileUpload sfu=new ServletFileUpload();

%>
</body>
</html>