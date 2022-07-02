<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>账户信息展示</title>
</head>
<body>
	<table	class="table">
		<caption><h1>账户展示</h1></caption>
		<thead>
			<tr>
			<th>姓名</th>
			<th>金额</th>
			</tr>
			<c:forEach items="${alist}" var="ac">
				<tr>
					<td>${ac.name}</td>
					<td>${ac.money }</td>
				</tr>
			</c:forEach>
		</thead>
	</table>
</body>
</html>