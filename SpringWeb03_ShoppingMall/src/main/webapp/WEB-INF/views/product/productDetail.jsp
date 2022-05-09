<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="/resources/headerfooter/header.jsp"%>
<%@ include file="/resources/sub02/sub_image.html"%>
<%@ include file="/resources/sub02/sub_menu.html"%>

<article>
<h1>Item</h1>
<div id="itemdetail">
	<form method="post" name="formm">
		<fieldset><legend>Item detail Info</legend><span style="float:left;">
			<img src="resources/product_images/${productVO.image}"
				style="border-radius:20px;"/></span>
			<h2>${productVO.name}</h2>
			<label>가 격 : </label><p> ${productVO.price2} 원</p>
			<label>수 량 : </label><input type="text" name="quantity" size="2" value="1"><br>
			<label>제품설명 : </label><label>${productVO.content}</label><br>
			<input type="hidden" name="pseq" value="${productVO.pseq}"><br>
		</fieldset>
		<div class="clear"></div>
		<div id="buttouns">
			<input type="button" value="장바구니에 담기" class="submit" onclick="go_cart();">
			<input type="button" value="즉시구매" class="submit" onclick="go_order();">
			<input type="button" value="메인으로" class="submit" onclick="location.href='/shop/'">
		</div>
	</form>
</div>
</article>
<%@ include file="/resources/headerfooter/footer.jsp"%>