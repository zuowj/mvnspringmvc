<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" import="cn.zuowenjun.java.mvc.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
<%-- ${post.id>0?"编辑"+ post.title:"新增博文" } --%>
<%
	Post post=(Post)request.getAttribute("post");
	if(post==null){
		out.print("post is null!");
		return;
	}
	
	if(post.getId()>0){
		out.print("编辑"+ post.getTitle());
	}else{
		out.print("新增博文");
	}
%>
</title>
</head>
<body>
	<form:form modelAttribute="post" method="POST" id="mainForm"
		action="${pageContext.request.contextPath }/blog/editpost">
		<div>文章标题：</div>
		<div>
			<form:input path="title" />
		</div>
		<div>作者：</div>
		<div>
			<form:input path="author" />
		</div>
		<div>文章内容：</div>
		<div>
			<form:textarea path="content" rows="10" style="width:100%;" />
		</div>
		<div>
			<button type="button" id="btnSave" data-action="update" onclick="javascript:doSubmit(this);">保存</button>
			<c:if test="${post.id>0 }">
			<button type="button" id="btnDelete" data-action="delete" style="color:red;" onclick="javascript:doSubmit(this);">删除</button>
			</c:if>
			<form:hidden path="id"/>
			<input type="hidden" name="doAction" id="_doAction" />
		</div>
	</form:form>
	<c:if test="${result!=null && fn:length(result)>0 }">
		<p>操作结果：${result}</p>
	</c:if>
	<script type="text/javascript">
		function doSubmit(btn){
			if(!confirm("你确定要" + btn.innerText +"吗？")){
				return false;
			}
			var actionVal=btn.getAttribute("data-action");
			//alert(actionVal);
			document.getElementById("_doAction").setAttribute("Value",actionVal);
			document.getElementById("mainForm").submit();
		}
	</script>
</body>
</html>