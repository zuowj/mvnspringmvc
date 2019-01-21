<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>博客列表</title>
</head>
<body>
	<div style="text-align: right;">
		<span>[ ${sessionScope.loginUid }(${sessionScope.loginUname }),
		<a href="${pageContext.request.contextPath}/account/signout">[退出]</a> ]</span>&nbsp;&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath}/blog/editpost" target="_blank">[ +发表博文 ]</a>
	</div>
	<div>
		<form method="post" action="${pageContext.request.contextPath }/blog/querylist">
			<fieldset>
				<legend>范围查询：</legend>
				<span>开始时间：</span> <input type="text" name="frmDate" 
					value="${param.frmDate }"
					placeholder="yyyy-MM-dd" />  
				<span>结束时间：</span> <input type="text"
					name="toDate" placeholder="yyyy-MM-dd"  
					value="${param.toDate}" />
				<button id="btnquery">查询</button>
			</fieldset>
		</form>
	</div>
	<c:choose>
		<c:when test="${posts!=null && posts.size()>0}">
			<c:forEach items="${posts}" var="item">
				<div>
					<h2>
						<a href="${pageContext.request.contextPath}/blog/post/${item.id }"
							target="_blank">${item.title}</a>&nbsp;&nbsp;
							<a href="${pageContext.request.contextPath}/blog/editpost/${item.id }" 
							target="_blank" style="color:red;font-size:16px;">[修改]</a>
					</h2>
					<h4>
						作者：${item.author },时间：
						<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />
					</h4>
					<p>${item.content }</p>
				</div>
				<hr />
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p style="color:red;">没有任何博客文章记录！</p>
		</c:otherwise>
	</c:choose>

</body>
</html>