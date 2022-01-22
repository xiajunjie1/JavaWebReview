<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导入测试</title>
</head>
<body>
<%@ include file="/First.jsp" %>
<%response.setCharacterEncoding("utf-8");
  response.setContentType("text/html;charset=utf-8");
%>
<!-- 
静态导入：
	将所需要的程序代码，做出替换
	
动态导入：
	使用静态导入进行并不会关心具体的文件类型，而是将代码全部合并在一起后在进行处理，
	而除了此类导入之外，在JSP中又提供了一个动态导入操作，该导入功能最大的特点是可以区分
	被导入的页面是动态文件还是静态文件，如果是静态文件则功能与静态导入相同，如果是动态文件，则先进行动态文件处理
	在将结果包进来
	
	动态导入是使用JSP标签实现的
	<--jsp:include page=""/>:不传递任何参数，且一定要添加/让标签完结
	<--jsp:include page="">:导入页面并传递参数
	<--jsp:param name="参数名称" value="参数内容" />
	<--jsp:param name="参数名称" value="参数内容" />
	...
	<--/jsp:include>
	
	动态导入可以实现静态导入的功能，动态导入最重要的功能是一个参数传递
	所有的参数可以在被传递的JSP页面中通过内置的request对象获取到
	
	静态导入与动态导入的区别：
		静态导入：先进行导入处理，而后再进行页面的执行处理
		动态导入：如果包含的页面是静态页面则与静态导入一致，如果包含的是动态页面，则先处理，而后导入结果
		
		假如在主页面定义一个局部变量num,在要导入的jsp页面中同样定义一个num变量
		倘若使用静态导入，那么程序会报错，变量名冲突
		倘若使用动态导入，程序可用正常运行，因为在导入代码之前，jsp中的程序已经运行完毕，生成的为静态页面代码
	
 -->
 <jsp:include page="Received.jsp">
 <jsp:param name="title" value="how are you "/>
 <jsp:param name="content" value="hello world"/>
 </jsp:include>
</body>
</html>