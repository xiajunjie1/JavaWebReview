<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="com.maker.util.*,com.maker.dao.*,java.util.*" %>
 <% 
 request.setCharacterEncoding("UTF-8");
 String path=request.getContextPath();
 Ads ad=(Ads)request.getAttribute("adobj");
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>编辑处理</title>
</head>
<body>
<nav class="navbar navbar-default">
<div class="container-fluid">
<div class="navbar-header">
<a class="navbar-brand" href="index.jsp;">广告展示</a>
</div>
<div>
<ul class="nav navbar-nav">
<li><a href="ads_add.jsp">新增广告</a></li>
<li><a href="ads_list.jsp">广告列表</a></li>
</ul>
</div>
</div>
</nav>
<% 
ParameterUtil pu=new ParameterUtil(request,"/upload/ad/");
Ads newad=new Ads();
newad.setTitle(pu.getParameter("title"));
newad.setUrl(pu.getParameter("url"));
newad.setNote(pu.getParameter("note"));
String photo=pu.getParameter("photo");
newad.setPhoto(photo);
List<String> filenames=pu.getUUidNames("pic");//通过该方法，获取文件名，如果获取到的文件名个数大于0，那么说明有上传过文件
if(filenames.size()>0){
	//修改广告内容时，上传了图片
	pu.SaveFile("pic", Arrays.asList(photo));
	pu.clean();
}
int aid=Integer.parseInt(pu.getParameter("aid"));
Adsdao adao=new Adsdao();
int result=adao.updateAdByid(aid, newad);
if(result>0){
	
%>
<h2>更新成功</h2>
<% 
}else{
%><h2>更新失败</h2>
<%} %>
</body>
</html>