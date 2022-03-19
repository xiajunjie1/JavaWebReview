<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.util.*,java.io.*,org.apache.commons.fileupload.*,org.apache.commons.fileupload.disk.*,org.apache.commons.fileupload.servlet.*" %>
 <%!
 	public static final Long MAX_SIZE=3145728L;//设置最大上传内容为3M
 	public static final Long FILE_MAX_SIZE=1048576L;//设置单个文件上传为3M
 	public static final String TEMP_DIR="/temp/";//设置临时目录
 	public static final String UPLOAD_DIR="/upload/";//设置上传目录
 	public static final String DEFAULT_ENCODING="UTF-8";//设置默认编码
 	%>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>FileUpload文件上传处理</title>

<!-- 
直接运行该程序，会保存，原因是因为realpath下，WEB-INF没有相应的jar包，所以无法识别fileupload组件的类
需要在realpath下WEB-INF中的lib添加相应的jar包
 -->
</head>
<body>
<%
request.setCharacterEncoding(DEFAULT_ENCODING);
DiskFileItemFactory factory=new DiskFileItemFactory();//磁盘管理类
factory.setRepository(new File(TEMP_DIR));//临时存储目录
ServletFileUpload fileupload=new ServletFileUpload(factory);//上传文件核心处理类
fileupload.setSizeMax(MAX_SIZE);//设置最大上传大小
fileupload.setFileSizeMax(FILE_MAX_SIZE);//设置单个文件最大上传大小
if(fileupload.isMultipartContent(request)){
	//表单被封装，即请求数据为二进制数据，请求表单中包含有文件上传控件
	//获取所有的请求参数
	Map<String,List<FileItem>> map=fileupload.parseParameterMap(request);
	//遍历所有的上传项
	for(Map.Entry<String,List<FileItem>> entry:map.entrySet()){
		String parname=entry.getKey();
		List<FileItem> parvalue=entry.getValue();
		
		%>
		<p><%=parname %>=
		<% 
		for(FileItem fi:parvalue){
			if(fi.isFormField()){
				//请求参数为普通的文本数据
				%>
				<%=fi.getString(DEFAULT_ENCODING) %>
				<% 
			}else{
				//非文本数据,即二进制数据
				if(fi.getSize()>0){
					//文本选择框选中了文件
					//获取文件的后缀
					System.out.println(fi.getContentType());
					String end=fi.getContentType().substring(fi.getContentType().lastIndexOf("/")+1);
					//使用UUID工具类，实现自动命名的处理，防止名称重复
					String filename=UUID.randomUUID()+"."+end;
					//设置保存文件的物理路径
					String filepath=this.getServletContext().getRealPath(UPLOAD_DIR)+filename;
					System.out.println(filepath);
					//保存文件
					fi.write(new File(filepath));
					fi.delete();//清空上传信息
					%>
					<img width="100px" height="100px" src="<%=path+UPLOAD_DIR+filename%>"/>
					<% 
				}
			
			
			
			}
		}%>
		</p>
		<% 
	}
	
}
%>
</body>
</html>