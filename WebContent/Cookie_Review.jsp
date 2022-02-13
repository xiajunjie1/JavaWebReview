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

<title>Cookie操作</title>
</head>

<%--
Cookie指的是服务器端在客户端所保留的一组信息内容，但是Cookie可以保存很多的内容，可是它是有容量上限的
用户在进行HTTP访问的时候，才会由服务器端向客户端进行Cookie的设置，但是Cookie已经设置成功后，在每一次客户请求的时候都会自动的将
这个Cookie发送到服务器端上去，从而实现Cookie的数据应用

public Cookie(String name,String value):传入Cookie的名字和内容
	Cookie不支持无参构造，因为协议中规定，Cookie必须有名字和内容
public String getName()：获取Cookie的名字
public void setValue(String newValue)：设置Cookie的内容
public String getValue()：获取Cookie的值

将Cookie设置到客户端，需要通过response内置对象
	public void addCookie(Cookie cookie)
	要想确定Cookie是否设置成功了，可以打开浏览器的开发者工具，找到Application
	
	需要得到Cookie需要用到request内置对象中的
	public Cookie[] getCookies()：获取请求中所带的全部Cookie
	一般不建议在Cookie中保存中文数据
	
	默认情况下，Cookie对象仅仅是能保存在当前浏览器的使用范围之中，如果将浏览器关闭，是无法获取到之前设置的Cookie
	如果想让数据保存的时间长一些，可以设置保存的最大时效
		public int getMaxAge()：获取Cookie的最大时效
		public void setMaxAge(int age),时间单位为秒，设置Cookie的最大时效
		
	Cookie路径的匹配操作
		public void setPath(String url):设置Cookie的生效路径
		public String getPath()：获取Cookie的使用路径
		
		如果说现在"get_cookie.jsp"页面处于根路径下，那么只能匹配到存储路径为"/"下的Cookie，而无法获取其子路径的Cookie
		如果"get_cookie.jsp"页面处于/login子路径下，那么可以获取到/login路径的Cookie数据，也可以获取到其父路径的Cookie数据，也就是说能获取到全部的Cookie数据
		
		在以后的开发中，如果某些Cookie的信息只能够存在特定的路径下访问，那时候就可以配置path，默认的path是当前项目的虚拟路径
		
 --%>
<body>
<%
Cookie cook=new Cookie("Message","中文数据测试");
Cookie cob=new Cookie("Msg","thisiscooktest");
cook.setMaxAge(3600);
cob.setMaxAge(3600);
cob.setPath("/FirstTest/Login/");
response.addCookie(cook);
response.addCookie(cob);
%>
</body>
</html>