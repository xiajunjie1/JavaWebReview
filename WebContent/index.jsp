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

<title>首页</title>
</head>
<body>
	<table class="table ">
		<tr>
			<td><button class="btn btn-primary" onclick="javascript:window.location.href='account/saveUi'">添加</button></td>
			<td><button class="btn btn-primary" onclick="javascript:window.location.href='account/list'">显示</button></td>
		</tr>
	</table>
</body>
</html>