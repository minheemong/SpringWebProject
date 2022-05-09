<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" >
</head>
</head>
<body>
<div id="wrap" align="center">
<h1>게시글 리스트</h1>
<table class="list">
	<tr><td colspan="5" style="border: white; text-align: right">
		<div style="float:left;">${loginUser.id}(${loginUser.name})님 로그인 
			<input type="button" value="정보수정" onClick="location.href='memberEditForm'"/>
			<input type="button" value="로그아웃"	onClick="location.href='logout'"></div>
		<div style="float:right;"><a href="boardWriteForm">게시글 등록</a></div>	</td></tr>
	<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th>	<th>조회</th></tr>
		<c:forEach var="board" items="${boardList}">
			<tr><td align="center">${board.num }</td>
				<td>
					<a href="boardView?num=${board.num}">${board.title}</a> 
						<c:if test="${board.replycnt>0}">
							<span style="color:red; font-weight:bold;">[${board.replycnt}]</span>
						</c:if>
					</td>
				<td align="center">${board.userid}</td>
				<td align="center"><fmt:formatDate value="${board.writedate }" /></td>
				<td align="center">${board.readcount}</td>	</tr>
		</c:forEach>
</table>

<br><br>

<div id="paging" align="center" style="font-size:110%;">
	<c:url var="action" value="main"/>
	<c:if test="${paging.prev}">
		<a href="${action}?page=${paging.beginPage-1}">◀</a>&nbsp;
	</c:if>
	<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
		<c:choose>
			<c:when test="${paging.page==index}">
				<span style="color:red;font-weight:bold;">${index}&nbsp;</span>
			</c:when>
			<c:otherwise>
				<a href="${action}?page=${index}">${index}</a>&nbsp;
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${paging.next}">
		<a href="${action}?page=${paging.endPage+1}">▶</a>&nbsp;
	</c:if>
</div>
</div>
</body>
</html>