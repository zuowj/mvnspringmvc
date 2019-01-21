<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%
	response.setStatus(200);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404-页面不存在！</title>
</head>
<body>
	<div style="width: 600px; margin-top: 100px;margin-left:auto;margin-right:auto;">
		<p>你访问的资源不存在，可能被外星人吃掉了！</p>
		<p>请求URL：<span id="rawUrl"></span></p>
	</div>
	
	<script type="text/javascript">
		window.onload=function(){
			document.getElementById("rawUrl").innerHTML=window.location.href;
		};
	</script>
</body>
</html>