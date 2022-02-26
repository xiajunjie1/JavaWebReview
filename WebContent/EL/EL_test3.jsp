<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*" %>
 <% String path=request.getContextPath();%>
 <%--EL与List、Map集合 
 	EL进支持单个对象的输出，也就是说，EL没有办法直接迭代输出集合
 --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>EL与简单java类</title>
</head>
<body>
<%-- 
EL可以通过反射机制，直接获取对象的属性 
从而达到简化输出的效果
 --%>
<table class="table table-hover">
<thead>
<tr>
<td>部门号</td>
<td>部门名称</td>
<td>部门位置</td>
</tr>
</thead>
<tbody>
<%
List dlist=(List)request.getAttribute("dlist");
Iterator it=dlist.iterator();
while(it.hasNext()){
	//由于pageContext仅在当前页面使用，所以属于仅用一次的属性
pageContext.setAttribute("dept", it.next());//Object类型，但是EL的原理是反射机制，所以不用进行强制转型也能获取到相应的属性
%>
<tr>
<td>${dept.dno}</td>
<td>${dept.dname}</td>
<td>${dept.loc}</td>
</tr>
<% 
}
%>
<%--可以通过key，获取map的value，遍历的方式和List集合的类似 --%>
<tr>
<td>${mlist[0].dno}</td>
<td>${mlist[0].dname}</td>
<td>${mlist[0].loc}</td>
</tr>
</tbody>
</table>

</body>
</html>