<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#paging{font-size:150%;}
</style>
</head>
<body>
<div id="paging" align="center" style="font-size:110%;">
	<c:url var="action" value="${param.command}"/>
	<c:if test="${param.prev}">
		<a href="${action}?page=${param.beginPage-1}">◀</a>&nbsp;
	</c:if>
	<c:forEach begin="${param.beginPage}" end="${param.endPage}" var="index">
		<c:choose>
			<c:when test="${param.page==index}">
				<span style="color:red; font-weight:bold;">${index}&nbsp;</span>
			</c:when>
			<c:otherwise>
				<a href="${action}?page=${index}">${index}</a>&nbsp;
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${param.next}">
		<a href="${action}?page=${param.endPage+1}">▶</a>&nbsp;
	</c:if>
</div>
<!-- 현재 페이지가 나타나는 내용에 param.command, prev, beginPage, endPage, page, next 값이 파라미터로 전달되어야 정상
표현되는 페이지 입니다. -->
</body>
</html>