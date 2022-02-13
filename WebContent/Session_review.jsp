<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String path=request.getContextPath();%>
 
 <!--
 
  Session
  	session描述的是一个会话的概念，主要目的是为了解决HTTP协议的无状态问题
  	在WEB容器中为了方便的区分不同的用户，每一个用户都有一个对应的session（会话）对象
  	当用户通过浏览器想WEB服务器发出访问请求时，服务器会自动对该用户的session状态进行维护
  	不同用户拥有各自的session对象，不同的session之间彼此隔离，不允许直接访问
  	
  session内置对象实现了HttpSession接口，它是一个独立的接口，并没有继承什么父接口，因为session的概念只存在HTTP协议中
  
  session工作原理：
  	session在整个开发中是用来进行用户身份标识的，但是既然HTTP是无状态的的操作形式，那么它是如何实现这种身份标识的呢？
  	实际上这种标识的实现依靠的Cookie（头信息）以及服务器端算法机制实现的
  	Session的属性范围会在每次浏览器关闭后自动销毁，主要原因在于Cookie数据的丢失
  	实际上针对不同的Session，web容器都会提供一个SessionID的信息，通过public String getId()获取SessionID
  	查看Cookie中，有JSESSIONID这个Cookie，它即是Session对象的SessionID
  	需要注意的是，JSESSIONID并不是长期保存在客户端浏览器的Cookie之中，它是每次发送请求并且有了回应之后才会自动的设置在客户端的浏览器之中（第一次请求时，没有JSESSIONID）
  	在传统的开发过程之中，SessionID一旦分配，那么一般就都不允许改变的，但是在Servlet3.1版本中，增加了一个request.changeSessionId()方法，可以更改SessionID
  	另外，由于Session的实现是基于Cookie而来的，如果禁用了Cookie，那么Session也是无法使用的
  
  Session与线程池：
  	在整个session处理机制之中，用户所发送的每一条请求，如果想要进行处理，都需要通过Tomcat进行线程资源的分配，也就是说在整个Tomcat之中
  	存在一个预设的线程池配置，若未做出过任何修改，那么这个线程池可以实现的线程个数是有限的，但是在实际开发中，一般线程池的大小可以设为"内核线程数量*2"
  	调整线程池的配置，修改Tomcat目录下/conf/server.xml文件，找到<Connector>标签，在后面加上以下属性
  		maxThreads="数量" minSpareThreads="数量" acceptCount="数量"，这三个属性分配对应最大线程数，初始化线程数，阻塞对列数
  	Tomcat能处理的最大并发访问数：maxThreads+accpetCount
  	
  
  session对象的使用
  	基于Session的特点，可以进行用户登录认证的处理
  	
  
  -->
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>Session复习</title>
</head>
<body>
<h2>SessionID=<%=session.getId() %></h2>
<h2>是否为新用户=<%=session.isNew() %></h2>
<h2>CPU的内核数量=<%=Runtime.getRuntime().availableProcessors() %></h2>
<h2>Session与线程池：<%=Thread.currentThread().getName() %></h2>

</body>
</html>