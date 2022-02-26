<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String path=request.getContextPath();%>
 <%--EL与简单java类 
 	通过EL，可以通过属性直接获取到java类对象，并输出相应的信息，这样做可以减少import java包的操作
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
<h2>【员工号】：${emp.eno}</h2><br/>
<h2>【员工名】：${emp.name}</h2><br/>
<h2>【员工部门】：${emp.dept.dname}</h2><br/>
<h2>【部门号】：${emp.dept.dno}</h2><br/>
<h2>【部门地点】：${emp.dept.loc}</h2><br/>

</body>
</html>