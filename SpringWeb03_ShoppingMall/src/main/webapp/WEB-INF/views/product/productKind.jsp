<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/resources/headerfooter/header.jsp"%>
<%@ include file="/resources/sub02/sub_image.html"%>
<%@ include file="/resources/sub02/sub_menu.html"%>

<article>
<h2>Item</h2>
<c:forEach items="${productKindList}" var="productVO">
	<div id="item">
		<a href="productDetail?pseq=${productVO.pseq}">
			<img src="resources/product_images/${productVO.image}"/>
			<h3>${productVO.name}</h3><p>${productVO.price2}</p>
		</a>
	</div>
</c:forEach>
<div class="clear"></div>
</article>
<%@ include file="/resources/headerfooter/footer.jsp"%>