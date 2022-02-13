<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%!
    	public static final String LOGIN_URL="check.jsp";
    	//之所以定义为常量，是为了便于代码维护而准备的
    	
    %>
    <%
    request.setCharacterEncoding("utf-8");
    String errmsg=request.getParameter("errmsg");
    %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<style type="text/css">
body{
background:url("pic/bg.jpg") no-repeat;
background-size:cover;
position:absolute;
width:100%;
height:100%;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
	$("#hint").hide();
});
var validate=function(){
	var flag=true;
	var msg="";
	if($("#username").val()==""){
		
        msg+="用户名为空 ";
		flag=false;
	}
	if($("#password").val()==""){
		$("#password").focus();
		msg+="密码为空 ";
		flag=false;
	}
	console.log(msg);
	$("#errMsg").html(msg);
	if(!flag){
		$("#hint").show();
	}
	return flag;
};

var closeHint=function(){
	
	$("#hint").hide();
	
};
</script>
</head>
<body>
<div class=" col-lg-3" style="background:rgba(240,240,240,0.75);position:relative;top:30%;left:38%;border-radius:30px;box-shadow: #000 3px 3px 3px;">
	<h3 class="text-left text-primary">系统用户 <small class="text-primary">登录</small></h3>
	<hr style="color:#060060">
	<form class="form-horizontal" action="<%=LOGIN_URL %>" method="post">
		<div class="form-group">
			
		      <div class="alert alert-danger alert-dismissable col-lg-10 col-lg-offset-1" id="hint">
            <button type="button" class="close" onclick="closeHint();" >
                &times;
            </button>
          <span id="errMsg"></span>
        </div>
		<div class="col-lg-1"></div>
		<div class="col-lg-10 input-group">
<span class=" input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
			<input type="text" class="form-control"  id="username" name="username"
				   placeholder="请输入用户名">
		</div>
	</div>
		<div class="form-group">
		<div class="col-lg-1"></div>
		<div class="col-lg-10 input-group">
<span class=" input-group-addon"><span class="glyphicon glyphicon-credit-card"></span></span>
			<input type="password" class="form-control" id="password" name="password"
				   placeholder="请输入密码">
		</div>
	</div>

	<div class="form-group">
    <div class="col-lg-offset-1 col-lg-10">
      <div class="checkbox">
        <label>
          <input type="checkbox">请记住我
        </label>
      </div>
    </div>
</div>
<div class="form-group">
    <div class="col-lg-offset-2 col-sm-8">
      <button type="submit" class="btn btn-block btn-success" onclick="return validate();">登录</button>
    </div>
  </div>
	</form>
	
	<%if(errmsg!=null&&!errmsg.equals("")){
%>
	<div class="form-group">
			
		      <div class="alert alert-danger alert-dismissable col-lg-10 col-lg-offset-1" id="hint">
            <button type="button" class="close"  data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
          <%=errmsg %>
        </div>
<%
	} %>

</div>

</body>
</html>