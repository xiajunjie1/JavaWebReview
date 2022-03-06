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
<%--已经在Linux虚拟机上安装好FTP服务了，
	登录用户ftp1、ftp2,密码为“xia123”
	
	FTP是TCP/IP协议中的一部分，属于应用层协议。使用FTP最主要的功能是对文件进行管理，所以在FTP内部对于文件的
	支持有两种传输模式：
		文本传输（ASCII码，默认）和二进制传输（Binary），通常文本文件使用文本传输，文件、图片、声音、视频等采用
		
	FTP的操作一般分为：服务器端、客户端，客户端需要通过特定的FTP服务进行服务器的连接（大部分FTP的服务器上都是需要进行用户登录认证的）
	实际上，在FTP通讯都需要有两个连接存在，一个连接时传送FTP命令，另一个连接时传输数据，FTP中一般支持两种工作模式：
	standard模式（PORT模式），另一种是Passive（PASV模式），这两种模式概念如下：
		PORT主动模式：当客户端与服务端连接后，客户端打开一个新的本地端口，随后将此端口告诉给FTP服务端，这样FTP服务端就会主动的连接到FTP客户端公布的端口，随后进行数据传输
		PASV被动模式：FTP在定义的时候就公布了一个操作端口（一般为21端口），这样客户端连接之后会明确的知道该操作端口并且进行数据传输
		
	如果想用工具进行ftp访问，可以使用Apache提供的commons-net组件包进行开发
 --%>
<title>FTP测试</title>
</head>
<body>

</body>
</html>