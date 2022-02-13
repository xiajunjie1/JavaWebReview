<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.*" %>
    <%
 String scheme=request.getScheme();//http/https
 String server=request.getServerName();//域名或ip
 int port=request.getServerPort();//服务器端口
 String base=scheme+"://"+server+":"+port+request.getContextPath()+"/";
 out.println(base);
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Request对象复习</title>
<base href="<%=base%>"> <!-- 配置了base后，所有的资源都会从base路径开始往下找 -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<%--
request在实际开发中，最重要的一点就是可以直接获取客户端的请求信息，在整个的请求信息里面
最重要的一点就是客户端参数的接收，在HttpServletRequest接口里面，最重要的方法就是下面的参数接收
	public String getParameter(String name),这个方法如果在没有请求参数的情况下，返回为null
	

请求参数的接收方法是由其父接口ServletRequest中定义的
在常规的开发当中，对于HTTP的请求参数最为常见的一种形式是通过表单的方式来进行定义，表单里面定义相关组件，
而后在组件里面编写的内容。如果是用表单的方式进行请求，那么，当表单控件不输入任何值的时候，服务器端接收到的值为""而不是null
如果表单传递的是数字，那么服务器端接收到的依然会是字符串的形式，这种情况需要在进行数据的转型

GET模式：通过浏览器地址栏输入地址访问，即为一种GET请求，如果有请求的数据，则会直接附加在连接地址后面，表单上
		如果用GET模式，那么请求参数也会附在连接地址后面，但是地址栏不是无限长的（一般来说只能输入4K-5K的数据内容，
		如果要进行文件上传，那么肯定是不能使用GET模式的。）
POST模式：只能用于表单提交上，理论上可以提交很大的提交内容


request.getParameter("")和request.getAttribute("")
	以上两个方法属于不同的分类，并没有任何的联系，要用getAttribute()方法的话，必须得先通过setAttribute()方法设置属性
	同时设置的属性内容都是用Object来描述的，即该属性值可以是一个任意的类对象。
	
什么时候可以使用request.getParameter("")
	表单参数（随着表单提交将所有的数据一起发送到服务器端）
	利用地址重写传递的参数信息
	使用特定的标签指令，如<jsp:forward> <jsp:include>传递的参数内容
	
	
接收数组请求参数
	public String[] getParameterValues(String name)
	所有的参数都可以通过getValues方法来获取，只是某些参数如果只有一个，那么数组的长度为1
	也就是说getParameterValues方法可以替代getParameter方法
	
	
动态接收参数
	无论是用getParameter(String name)还是用getParameterValues(String name)方法
	进行参数接收，都要明确统一好参数的名称，如果不统一，则无法进行接收，考虑到程序的多变性，ServletRequest
	接口中提供一个可以实现全部请求参数名称的接收方法，就可以通过其实现参数的动态接收
		public Enumeration<String> getParameterNames()
	该方法的返回值是一个枚举，枚举接口中提供有两个核心方法：hasMoreElements()、nextElements()
	
	
	
请求乱码处理
	一般可以借助ServletRequest接口中定义的方法进行请求乱码的处理
	public void setCharacterEncoding(String env)throws UnsupportedException
	
	

获取上下文路径
	之前学习过Tomcat的虚拟目录的配置，在配置虚拟目录的时候，就存在一个上下文名称
	如<Context path="/" docBase="D:\myWeb"/>在进行访问的时候，通过"/"进行目录的访问
	但是随着你配置的虚拟目录不同，这个名称也要动态的获取到，所以可以利用HttpServletRequest接口
	提供的方法来获取此信息：
		public String getContextPath()
	此方法获取的，就是当前配置的访问路径
	在Web程序的开发过程中，会应用到各种资源：css、js、图片、视频等，在进行这些资源访问的时候，必须准确的设置好对应的路径，否则将无法进行加载
	在以后的开发中，可以使用request.getContextPath()实现资源定位，整体的处理效果是最方便的
	因为不管用户如何移动程序代码，只要根路径的匹配不改变，资源就可以直接进行完整定位
	
Base资源定位
	在实际的项目开发中，定位资源的最佳方法其实是通过<Base>来实现的，利用它可以进一步简化资源的获取操作
	
 --%>

<body>

<img  src="img/bg.jpg" width=200px height=100px />

<h2>上下文变量：<%=request.getContextPath() %></h2>
</body>
</html>