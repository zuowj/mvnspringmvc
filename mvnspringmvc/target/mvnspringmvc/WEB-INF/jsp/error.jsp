<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" import="java.io.*,java.lang.*" %>
<%!

	//获取完整堆栈信息
	String getStackTrace(Throwable throwable){
	StringWriter stringWriter=new StringWriter();
	PrintWriter printWriter=new PrintWriter(stringWriter);

	try {
		throwable.printStackTrace(printWriter);
		return stringWriter.toString();
	}finally {
		printWriter.close();
	}
}

%>
<%
	Exception ex= (Exception)request.getAttribute("exception");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发生错误！</title>
</head>
<body>
	<h2>对不起，处理请求时发生错误！</h2>
	<p>错误信息：${exception.getMessage() }</p>
	<p>错误详情：<%=getStackTrace(ex) %></p>
	<p>请求URL：${url}</p>
	<p><a href="javascript:history.back();">[返回]</a></p>
</body>
</html>
