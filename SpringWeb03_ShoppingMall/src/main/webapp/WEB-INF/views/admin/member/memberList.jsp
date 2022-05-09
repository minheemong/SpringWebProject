<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/admin/header.jsp"%>
<%@ include file="/resources/admin/sub_menu.jsp"%>
<article>
   <h1>상품리스트</h1>
   <form name="frm" method="post">
      <table>
         <tr>
            <td width="642">회원명<input type="text" name="key" value="${key}">
               <input class="btn" type="button" name="btn_search" value="검색"
               onClick="go_search();"> <input class="btn" type="button"
               name="btn_total" value="전체보기 " onClick="go_total();"> <input
               class="btn" type="button" name="btn_write" value="상품등록"
               onClick="go_wrt();"></td>
         </tr>
      </table>
   <table id="productList">
      <tr>
         <th>id</th>
         <th>name</th>
         <th>email</th>
         <th>address</th>
         <th>zip_num</th>
         <th>phone</th>
         <th>가입일</th>
         <th>사용유무</th>
      </tr>
            <c:forEach items="${memberList}" var="memberVO">
               <tr>
                  <td>${memberVO.id}</td>
                  <td><a href="#" onClick="go_detail('${memberVO.id}')">${memberVO.name}</a></td>
                  <td><fmt:formatNumber value="${memberVO.email}" /></td>
                  <td><fmt:formatNumber value="${memberVO.address}" /></td>
                  <td><fmt:formatNumber value="${memberVO.zip_num}" /></td>
                  <td><fmt:formatNumber value="${memberVO.phone}" /></td>
                  <td><fmt:formatDate value="${memberVO.indate}" /></td>
            </c:forEach>
   </table>
   </form>
   
   <br>
	<jsp:include page="/resources/paging/paging.jsp">
		<jsp:param name="page" value="${paging.page}"/>
		<jsp:param name="beginPage" value="${paging.beginPage}" />
		<jsp:param name="endPage" value="${paging.endPage}"/>
		<jsp:param name="prev" value="${paging.prev}"/>
		<jsp:param name="next" value="${paging.next}"/>
		<jsp:param name="command" value="memberList"/>
	</jsp:include>

</article>
<%@ include file="/resources/admin/footer.jsp"%>