<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%--
  JSP内置对象
  	WEB容器为用户所提供好的一个可以直接使用的对象，开发者不需进行任何的实例化操作
  	
  	属性范围
  		page
  		request
  		session
  		application
  	以下几个方法可以实现属性范围的操作：
  		void setAttribute(String name,Object value) 设置属性名称以及属性内容
  		Object getAttribute(String name) 根据属性名称获取对应的内容，不存在返回null
  		Enumeration<String> getAttributeNames()获取全部属性名称
  		void RemoveAttribute(String name)删除指定属性
  		以上的方法可以在pageContext、request、session、application对象中进行调用
  		
  	page:描述的是一个Web页面，所以page属性范围保存的内容，只允许在当前的页面中进行操作，如果进行了跳转，那么是无法获取的
  		page属性范围，如果要进行设置，需要使用pageContext对象来进行设置
  		注意：如果使用静态包含的话，仍然可以获取到相应的属性值。动态包含和跳转类似，无法获取到pageContext的属性对象。
  	
  	request：表示的是一个请求信息，在整个JavaWeb处理之中，请求是一个核心的话题，而请求处理全部是通过request内置对象完成的
  			在整个正确的设计之中，request属性是非常有用处的
  			request属性对象可以在一次"服务器跳转"后依然可以获取所设置的属性内容
  			服务端跳转和客户端跳转：服务端跳转，实际过程中，客户仅发出了一次请求，该请求被服务器内部转发，做出了一次回应
  			客户端跳转，客户发出了多次请求，之前的请求就丢失了，所以相当于重复发出了一个request，所以无法获取之前request的内容
  	
  	session：解决了客户端跳转的属性范围传递的问题，该属性的特点在于设置了session范围的属性，在没有关闭页面的情况下，该属性持续有效
  	
  	application：解决了只要关闭页面，就无法访问属性的问题。所有的属性保存在服务器之中，不管用户是否关闭页面，都可以获取到设置的
  				属性，只有在WEB容器重启的时候才会消失。session属性是每个用户独享，而application是每个用户共享
  				注意：属性范围保存的越久，所占用内存时间也就越久，如果在application中保存了过多的信息，那么这个信息对象是有可能
  				会被持续保存的，那么最终有可能由于保存的内容过多，而造成服务器的性能下降	
  
  
   --%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内置对象</title>
</head>
<body>

<%
pageContext.setAttribute("title", "测试"); 
pageContext.setAttribute("point", 100);

request.setAttribute("test", "this is test");

session.setAttribute("stitle", "session范围属性");
%>

<%
String title=pageContext.getAttribute("title").toString();
int point=Integer.parseInt(pageContext.getAttribute("point").toString());
%>
<h2>获取到Title值：<%=title %></h2>
<h2>获取到point值：<%=point %></h2>

<jsp:forward page="reqtest.jsp" />
</body>
</html>