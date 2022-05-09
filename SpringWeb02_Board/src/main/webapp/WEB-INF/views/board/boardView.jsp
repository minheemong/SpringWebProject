<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" >
<script src="resources/script/board.js"></script>
</head>
<body>
<div id="wrap" align="center">
	<h1>게시글 상세보기</h1>
	<table>
		<tr><th>작성자</th><td>${board.userid}</td><th>이메일</th><td>${board.email}</td></tr>
		<tr><th>작성일</th><td><fmt:formatDate value="${board.writedate}"/></td>
			<th>조회수</th><td>${board.readcount }</td></tr>
		<tr><th>제목</th><td colspan="3">${board.title}</td></tr>
		<tr><th>내용</th><td colspan="2"><pre>${board.content}</pre></td>
			<td  width="300" align="center">
				<c:choose>
					<c:when test="${empty board.imagename}">
						<img src="resources/upload/noname.jpg"  width="250">
					</c:when>
					<c:otherwise>
						<img src="resources/upload/${board.imagename}" width="250">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table><br> <br>
	<input type="button" value="게시글 리스트" onclick="location.href='main'">
	<input type="button" value="게시글 수정" onclick="open_win('boardEditForm?num=${board.num}', 'update')">
	<input type="button" value="게시글 삭제" onclick="open_win('boardDeleteForm?num=${board.num}', 'delete')">


</div>
</div><br />
<c:set var="now" value="<%=new java.util.Date()%>"></c:set>
<div id="wrap" align="center">
<form action="addReply" method="post" name="frm2">
<input type="hidden" name="boardnum" value="${board.num}">
<table>
	<tr><th>작성자</th><th>작성일시</th><th>내용</th><th>&nbsp;</th></tr>
	<tr align="center">
		<td width="100">${loginUser.id}	<input type="hidden" name="userid" value="${loginUser.id}">	</td>
		<td width="100"><fmt:formatDate value="${now}"	pattern="MM/dd HH:mm"></fmt:formatDate></td>
		<td width="670"><input type="text" name="reply" size="85"></td>
		<td width="100"><input type="submit" value="답글작성" onclick="return reply_check();"></td></tr>
	<c:forEach var="reply" items="${replyList}">
		<tr><td align="center">${reply.userid}</td>
			<td align="center">
				<fmt:formatDate value="${reply.writedate}"	pattern="MM/dd HH:mm"></fmt:formatDate></td>
			<td>${reply.content}</td>
			<td align="center">
				<c:if test="${reply.userid==loginUser.id}">
					<input type="button" value="삭제" 
				onclick="location.href='deleteReply?num=${reply.num}&boardnum=${reply.boardnum}'">
				</c:if>&nbsp;</td>
		</tr>
	</c:forEach>
</table>
</form>	
</div>	
	

</body>
</html>