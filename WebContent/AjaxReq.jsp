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

<title>Ajax</title>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	var user=new Array()
	user.push({username:"xia3",age:18});
	user.push({username:"xia4",age:22});
	
	$.ajax({
		type:"POST",
		url:"user/getinfo",
		data:JSON.stringify(user),
		contentType:"application/json;charset=utf-8"
	});
});
</script>
</body>
</html>