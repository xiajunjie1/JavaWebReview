<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PageContext复习</title>
</head>

<%--

PageContext内置对象，可以实现page属性范围的定义，但是如果认真观察PageContext对应的类型
中提供的方法，你会发现它可以实现全部四种属性范围的操作

PageContext中定义了一些常量标记，这些标记全部都是int类型的常量，使用的形式为"XXX_SCOPE"
APPLICATION_SCOPE
PAGE_SCOPE
REQUEST_SCOPE
SESSION_SCOPE

扩充的重载方法：
void setAttribute(String name,Object value int scope)
Object getAttribute(String name,int scope)
void  removeAttrib(String name , int scope)

由以上的属性和方法可知，通过一个PageContext对象可以设置四种属性范围

pageContext仅仅能出现在JSP程序当中

--%>
<body>
<%
pageContext.setAttribute("req", "request属性", pageContext.REQUEST_SCOPE);
pageContext.setAttribute("seon", "session属性",pageContext.SESSION_SCOPE);

%>

<jsp:forward page="PcontextTest.jsp" />
</body>
</html>