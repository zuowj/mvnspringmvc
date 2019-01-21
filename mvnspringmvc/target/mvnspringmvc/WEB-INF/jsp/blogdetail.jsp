<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>博文详情：${post.title }</title>
</head>
<body>
	<div>
		<h2>
			${post.title}
		</h2>
		<h4>
			作者：${post.author },
			时间：<fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd" />
		</h4>
		<p>${post.content }</p>
	</div>
	<hr/>
	<div>
		<c:choose>
			<c:when test="${comments!=null && fn:length(comments)>0 }">
				<c:forEach items="${ comments}" var="item">
					<div style="margin:10px auto;">
						<div style="border-bottom:solid 1px gray;margin-bottom:5px;">
						${item.createby } 回复于：<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" />
						</div>
						<div>
							${item.content }
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>暂无相关评论！</p>
			</c:otherwise>
		</c:choose>
		<div>
			<form method="post" action="../savecomment">
			<h3>发表新评论：</h3>
			<p>评论人：<input type="text" id="createby" name="createby" /></p>
			<p>评论内容：</p>
			<p><textarea rows="5" style="width:100%" id="content" name="content"></textarea>
			</p>
			<p>
				<button id="btnreply">提交评论</button>
				<input type="hidden" name="postid" value="${post.id }" />
			</p>
			</form>
			<div>
				<c:if test="${msg!=null}">
					<p>提交评论结果：${msg}</p>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>