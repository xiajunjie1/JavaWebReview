<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- JSTL核心标签 -->  
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!-- JSTL函数标签 -->
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <!-- JSTL格式化标签 -->
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>JSTL测试2</title>
</head>
<body>
<!-- 函数标签，一般是结合EL表达式进行使用的 -->
<c:if test="${fn:contains(para1,'xia')}">
<h2>包含xia---${fn:toUpperCase(para1)}</h2>
</c:if>
<!-- 格式化标签
	日期格式化
	数字格式化
 -->
<fmt:formatDate value="${date1}" pattern="yyyy年MM月dd日"/>
<fmt:formatNumber value="${money}" currencyCode="zh"/>
</body>
</html>