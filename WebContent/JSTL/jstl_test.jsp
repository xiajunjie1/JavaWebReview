<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--c表示标签的前缀，它里面包含很多功能，使用c:xx的形式进行调用
    	uri是一个tld文件，它所描述的是标签中所有可以使用到的功能定义
    	
    	使用JSTL要将相关的jar包都手动复制到WEB-INF下的lib中
    		taglibs-standard-impl-1.2.5.jar
    		taglibs-standard-jstlel-1.2.5.jar
    		taglibs-standard-spec-1.2.5.jar
    	
     --%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <% String path=request.getContextPath();%>
 <%-- 
 JSTL最早是一个第三方的标准组件库，后来已经作为一个JavaEE的标准组件了
 	JSTL为JSP标签编程
 		服务器端：<jsp:forward>
 		包含指令：<jsp:include>
  --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>JSTL</title>
</head>
<body>
<%pageContext.setAttribute("message", "JSTL输出"); %>
<!-- jstl输出 -->
<c:out value="${message}"></c:out>
<%--
c:if标签属性
	【必选】test:编写一个布尔判断的表达式，结合el进行处理
	【可选】var：将判断的结果保存到变量中，需要定义变量名称
	【可选】scope属性：定义var声明的变量的保存范围（page，request，session，application）
 --%>
<c:if test="${param.msg=='xiajunjie'}" var="res" scope="session">
	<h2>你好！</h2>
</c:if>
<h2>
判断结果：${sessionScope.res}
</h2>
<%--
【必选】items:表示要迭代输出的的集合（List map 数组）
【必选】var：每次迭代取出的对象所保存的page属性范围的属性名称
【可选】begin：迭代开始的索引
【可选】end：迭代结束的索引
【可选】step：迭代操作时的步长
【可选】varStatus：var的状态

<c:forEach items="" var="" begin="" end="" step="" varStatus="">

</c:forEach>

 --%>

<table class="table table-hover">
<thead>
<tr>
<th>部门编号</th>
<th>部门名称</th>
<th>部门地点</th>
</tr>
</thead>
<tbody>
<c:forEach items="${ldept}" var="dept" >
<tr>
<td>${dept.dno}</td>
<td>${dept.dname}</td>
<td>${dept.loc}</td>

</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>