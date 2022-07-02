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

<title>账户录入</title>
</head>
<body>
<table class="table">
	<caption><h2>账户录入</h2></caption>
	<tbody>
	<form action="<%=path %>/account/save" method="post">
		<tr>
		<td width=30%>姓名</td>
		<td><input type="text" placeholder="请输入账户姓名" name="name"/></td>
		</tr>
		<tr>
		<td width=30%>
			账户金额
		</td>
		<td>
			<input type="text" placeholder="请输入账户金额" name="money"/>
		</td>
		</tr>
		<tr>
			<td colspan="2"><button class="btn btn-success" type="submit">提交</button></td>
		</tr>
		</form>
	</tbody>
</table>
</body>
</html>