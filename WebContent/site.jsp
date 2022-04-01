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

<title>显示site信息</title>
</head>
<body>
<table border="1"><thead>
<tr>
<th>id</th>
<th>名字</th>
<th>url</th>
</tr>
</thead>
<tbody>
<c:forEach items="${sites}" var="site">
<tr>
<td>${site.id }</td>
<td>${site.name }</td>
<td>${site.url }</td>

</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>