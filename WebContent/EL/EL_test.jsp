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

<title>EL表达式</title>
</head>
<%--表达式语言
${属性名称}：获取属性值，如果属性不存在，则用空字符串代替了null

表达式基础语法：
	表达式标志位
		pageScope
		requestScope
		sessionScope
		applicationScope
	除了以上四种标志位之外，还存在多个标志位，但表达式主要操作就是围绕着这四种属性范围展开的
		
		param标志位：对应的操作就是request.getParameter()
		利用该标志位进行输出的话，发现结果是null也会用空字符串代替	
	需要清楚的是，使用此类方式仅仅能够实现输出，而无法进行参数的处理
	
		header标志位：获取头信息
		cookie标志位：获取cookie信息
		
		pageContext标志位：页面上下文
		
		
	在实际的开发中，EL使用的最多的环境就是实现属性的输出操作，一般的做法是通过Servlet传递属性内容，而后交由EL进行显示
	如果出现同名属性的情况，EL默认是由小范围到大范围的显示
	可以通过xxxScope来指定EL输出的属性范围（具体是pageContext、request、session或者application）	
	
 --%>
<body>
<%
pageContext.setAttribute("test", "【PageContext】test");
request.setAttribute("test", "【request】test");
session.setAttribute("test", "【session】test");
application.setAttribute("test", "【application】test");
%>
<h2>【param标志位参数输出】：${param.message}</h2><br/>
<h2>操作主机：${header.host}</h2><br/>
<h2>操作来源：${header.Referer}</h2><br/><!-- 直接访问是没办法获取到该信息了，要通过其他链接访问才能获取到该信息 -->
<h2>SessionID：${cookie.JSESSIONID.value}</h2><br/>
<h2>【pageContext标志位】上下文路径：${pageContext.request.contextPath}</h2><br/>
<h2>【pageContext标志位】当前访问路径：${pageContext.request.requestURL}</h2><br/>
<h2>【pageContext标志位】用户请求模式：${pageContext.request.method}</h2><br/>
<h2>【pageContext标志位】SESSIONID：${pageContext.session.id}</h2><br/>
<!-- 由以上的输出形式可知，EL表示的本质，实际上就是利用反射 -->
<p>
<h2>${test}</h2><!-- 默认显示的是pageContext对象中的test属性，如果该属性不存在，则往request上找，以此类推 -->
</p>
<!-- 为属性指定具体范围后输出 -->
<p>
<h2>${pageScope.test}</h2><br/>
<h2>${requestScope.test}</h2><br/>
<h2>${sessionScope.test}</h2><br/>
<h2>${applicationScope.test}</h2><br/>
</p>
</body>
</html>