<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/admin/header.jsp"%>
<%@ include file="/resources/admin/sub_menu.jsp"%>
<article>
<h1>Q&amp;A 게시판</h1>
<form name="frm">
<input type="hidden" name="qseq" value="${qnaVO.qseq}">
<table id="orderList"> <!-- 게시물의 내용 -->
	<tr><th width="20%">제목</th><td align="left">${qnaVO.subject} ${qnaVO.rep}</td></tr>
	<tr><th>등록일</th><td align="left"><fmt:formatDate value="${qnaVO.indate}"/></td></tr>
	<tr><th>내용</th><td align="left">${qnaVO.content}</td></tr>
</table>
<!-- 관리자가 쓴 답글 또는 답글쓰는 입력란 표시 -->
<c:choose>
	<c:when test='${qnaVO.rep=="1"}'> <!-- 관리자 답변 전 표시 -->
		<table id="orderList">
			<tr><td colspan="2"><img src="resources/admin/opinionimg01.gif"></td></tr>
			<tr><td colspan="2"><textarea name="reply" rows="3" cols="50"></textarea>
				<input type="button" class="btn" value="저장" onclick="go_rep()"></td></tr>
		</table>
		</c:when>
		<c:otherwise> <!-- 관리자 답변 완료 후 표시 -->
			<table id="orderList"><tr><th>댓글</th><td>${qnaVO.reply}</td></tr></table>
		</c:otherwise>
	</c:choose>
	<input type="button" class="btn" value="목록" 	
onClick="location.href='adminQnaList'">
</form>
</article>

<%@ include file="/resources/admin/footer.jsp"%>