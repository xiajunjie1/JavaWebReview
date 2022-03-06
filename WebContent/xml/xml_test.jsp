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

<title>xml学习</title>
</head>
<%--
	xml编程
		xml是一种可以实现跨平台、跨网络并且不受程序开发平台的数据描述语言，在实际的项目开发中可以通过xml方便的实现数据交换、系统配置功能
		
 --%>
<body>
<!-- 模拟一个通讯录 
		对于此程序，有一个问题。如果该程序不运行，单单去看代码，那么会发现数据不是特别的清晰，但是如果同样的
		操作功能现在通过xml来实现，则要比HTML代码的结构清晰许多
		相较于HTML代码，XML文件结构上更加的清晰，但是显示的功能不是那么强大（因为HTML标签的所有目的都是为了进行页面展示）
		XML中标签没有所谓的显示功能，仅仅是能够提供数据标记功能，所以xml结构上会更加的强大，但是显示方式确实不足
	xml的核心组成部分：
		1、前导声明：<?xml version="XML版本编号" encoding="中文显示编码" standalone="是否独立运行" ?>
			version:规定了XML的语法版本，现在有且只有1.0
			encoding:如果xml中包含有中文数据，则必须设置该编码，否则无法正常显示
			standalone:XML仅仅可以描述数据结构，但是有些时候需要通过一些其他语法（css xslt）让其显示，如果要有其他相关文件引入，则此时内容必须设为no，如果独立运行则设置为yes
		2、数据主体：描述XML所要保存的数据信息
			在xml中数据可以通过元素和属性两种形式进行描述
			实际上，常见的做法是用元素描述数据
			每一个XML中都必须存在一个根节点（或称为“根元素”），所有的其他数据节点都必须在根节点中定义
			一个元素中，可以设置多个属性，每个属性之间用空格分隔，属性的内容必须用双引号封装
			如果描述的一些数据中含有特殊字符，那么需要用到转义字符：
				&amp;== &
				&lt;== <
				&gt;== >
				&quot;== "
				&apos;== '
		
				
-->
<div>
<div><strong>姓名：</strong>骡智翔</div>
<div><strong>电话：</strong>888</div>
<div><strong>住址：</strong>长江底部</div>
</div>
<hr>
<div>
<div><strong>姓名：</strong>沉冠西</div>
<div><strong>电话：</strong>111</div>
<div><strong>住址：</strong>黄河底部</div>
</div>
<hr>
<div>
<div><strong>姓名：</strong>内摔摔</div>
<div><strong>电话：</strong>120</div>
<div><strong>住址：</strong>医院</div>
</div>
</body>
</html>