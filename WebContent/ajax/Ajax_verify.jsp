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

<title>验证码</title>
<script type="text/javascript" >
window.onload=function(){
	document.querySelector("#vcode").addEventListener("blur", function(){
		var vcode=document.querySelector("#vcode").value;
		var xhr=new XMLHttpRequest();
		if(xhr.withCredentials){
			alert("该浏览器不支持ajax");
		}else{
			xhr.open("post","checkcode.action");
			xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xhr.onload=function(env){
				var result=xhr.responseText;
				if(result=="true"){
					document.querySelector("#status").className="glyphicon glyphicon-ok";
				}else{
					document.querySelector("#status").className="glyphicon glyphicon-remove";
					document.querySelector("#codeimg").src="../verfycode.action?pid="+Math.random();
					document.querySelector("#vcode").value="";
				}
			}
		}
		xhr.send("code="+vcode);
	}, false);
	
	
}
</script>
</head>
<body>
<div class="col-md-6" style="position:relative;top:100px">
<label>验证码</label><input type="text" id="vcode" name="vcode"/><span id="status"></span><img alt="验证码" id="codeimg" src="<%=path %>/verfycode.action?pid=<%=Math.random()*100%>">
</div>
${vcode}
</body>
</html>