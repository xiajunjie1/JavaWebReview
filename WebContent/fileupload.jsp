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

<title>文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/fileupload" method="post" enctype="multipart/form-data">
<div>
<label>名字：</label>
<input type="text" name="name" />
</div>
<div>
<label>图片：</label>
<input type="file" name="photo">
</div>
<div>
<button type="submit">提交</button>
</div>
</form>
</body>
</html>