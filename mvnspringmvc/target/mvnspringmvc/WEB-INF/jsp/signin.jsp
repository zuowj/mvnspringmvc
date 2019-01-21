<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>梦在旅途演示博客-登录</title>
<style type="text/css">
	#loginbox{
		width:300px;
		margin:100px auto 0 auto;
		padding:50px;
		border:5px groove gray;
	}
	
	#loginbox div{
		margin:20px auto;
	}
	
	.txtcenter{
		text-align:center;
	}
</style>
</head>
<body>
	<form method="post">
	<div id="loginbox">
		<div><h3>欢迎，请登录！</h3></div>
		<div>用户ID：<input type="text" id="txtuid" name="uid" /></div>
		<div>密&nbsp;&nbsp; &nbsp; 码：<input type="password" id="txtpwd" name="pwd" /></div>
		<div>
			<input type="submit" id="btnSubmit" value="登 录" />
			<input type="reset" id="btnReset" value="重置" />
		</div>
	</div>
	</form>
	<div class="txtcenter">
		<c:if test="${message!=null}">
			<p>${message}</p>
		</c:if>
	</div>
	<p class="txtcenter">Copyright 2018  zuowj.cnblogs.com and zuowenjun.cn demo.</p>
</body>
</html>