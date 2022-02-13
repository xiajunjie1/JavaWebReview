<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获取内存</title>
</head>
<body>
<%-- JSP隐式注释，不会发送到客户端 --%>
<!-- 显示注释，会发送到 客户端 -->
<%/*

Scriptlet(脚本小程序)
即写在简括号百分号中的java代码

所有JSP程序最终都转为了Java程序类，而在整个Web容器之中，每一个JSP程序实际上都只会提供有唯一一个实例化对象

而Scriptlet实际上就属于类结构的定义项



*/ %>

<%! //结构定义的Scriptlet，相当与在类中进行的定义
	int count =0;//相当于全局变量
	//定义全局常量
	public static final String Message="全局常量";
	
	//定义方法,此时定义的方法只允许一个JSP页面使用
	public int Sum(int ... args){
		int all=0;
		for(int temp : args){
			all+=temp;
		}
		return all;
		
	}
%>
<% //代码编写的Scriptlet，相当于在方法中进行的定义
	int number=1;//相当于局部变量
%>

<h1>JSP的表达式输出:<%=count%></h1><br/>
<h1>获取内存！</h1><br/>



<%
//out.println("<h2>最大可用内存："+(Runtime.getRuntime().maxMemory()>>20)+"Mb</h2>");
//out.println("<h2>初始化可用内存："+(Runtime.getRuntime().totalMemory()>>20)+"Mb</h2>");
out.println("<h2>count="+(count++)+"</h2>");
out.println("<h2>number="+(number++)+"</h2>");
//页面刷新，count的值会不停的递增，而number的值一直都是1
//此时的count变量实际上是JSP对象中的属性，而在整个的JSP对象实例化后，该属性只会定义一次，
//所以后续所有的操作都是在原有基础之上进行的扩展，而number是一个局部变量（相当于定义在方法中）
//每一次刷新都要执行这个方法，那么number变量就会重复声明，所以它的值一直不变
out.println("<h2>常量："+Message+"</h2>");

out.println("<h2>调用方法："+Sum(1,2,3,4,5,6,7)+"</h2>");


%>

<a >
test
</a>
</body>
</html>