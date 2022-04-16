<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String path=request.getContextPath();%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>数据显示</title>
</head>
<body>
<table class="table table-hover">
<thead>
<tr>
<th>部门号</th>
<th>部门名</th>
<th>位置</th>
</tr>
</thead>
<tbody>
<c:forEach items="${result}" var="dept">
<tr>
<td>${dept.deptno }</td>
<td>${dept.deptname }</td>
<td>${dept.loc }</td>
</tr>
</c:forEach>
</tbody>

</table>
<span>总条数：${record }</span>
</body>
</html>