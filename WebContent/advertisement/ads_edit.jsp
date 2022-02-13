<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*,com.maker.util.*,com.maker.dao.*" %>   
 <% 
 request.setCharacterEncoding("UTF-8");
 String path=request.getContextPath();

 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>编辑表单</title>
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

if(request.getParameter("aid")!=null){
	int aid=Integer.parseInt(request.getParameter("aid"));
	Adsdao adao=new Adsdao();
	Ads ad=adao.getAdsByid(aid);
	request.setAttribute("adobj", ad);
%>
<form action="ads_edit_do.jsp" enctype="multipart/form-data" method="post">
<div class="form-group">
<label for="title">标题</label>
<input type="text" id="title" name="title" value="<%=ad.getTitle() %>" />
</div>


<div class="form-group">
<label for="url">链接</label>
<input type="text" id="url" name="url" value="<%=ad.getUrl() %>"/>
</div>

<div class="form-group">
<label for="pic">图片</label>
<input type="file" id="pic" name="pic" /><%=ad.getPhoto() %>
</div>

<div class="form-group">
<label for="note">备注</label>
<textarea rows="6" cols="50" id="note" name="note" value="<%=ad.getNote()%>"><%=ad.getNote()%></textarea>
</div>
<input type="hidden" name="aid" value="<%=ad.getAid() %>"/>
<input type="hidden" name="photo" value=<%=ad.getPhoto() %>>
<div class="form-group"><button type="submit" class="btn btn-success">提交</button><button type="reset" class="btn btn-warning">重置</button></div>

</form>
<%}else{
	%>
	<h2>该广告条目不存在！</h2>
	<% 
} %>
</body>
</html>