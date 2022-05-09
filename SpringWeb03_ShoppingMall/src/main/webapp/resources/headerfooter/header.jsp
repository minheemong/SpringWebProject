<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/shopping.css" >
<script src="resources/script/member.js"></script>
<script src="resources/script/mypage.js"></script>
</head>
<body>
	<div id="wrap">
	<header>
		<div id="logo"><!-- 최상단 "/" 리퀘스트 요청 링크 -->
			<a href="/shop/"><img src="resources/images/logo.png" width="180" height="100"/></a></div>
		<nav id="top_menu"><!-- top menu -->
			<ul>
				<c:choose>
					<c:when test="${empty loginUser}">
						<li><a href="loginForm">LOGIN</a></li><li><a href="contract">JOIN</a></li>
					</c:when>
					<c:otherwise>
						<li style="color:blue;font-weight:bold;font-size:110%;">
						${loginUser.name}(${loginUser.id})</li>
						<li><a href="memeberEditForm">정보수정</a></li><li><a href="logout">LOGOUT</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="cartList">CART</a></li><li><a href="myPage">MY PAGE</a></li>
				<li><a href="qnaList">Q&amp;A (1:1)</a></li>
			</ul>
		</nav><!-- category menu -->
		<nav id="category_menu"><!-- 카테고리 메뉴 시작 Heels Boots Sandals 등 -->
			<ul>
				<li><a href="category?kind=1">Heels</a></li>
				<li><a href="category?kind=2">Boots</a></li>
				<li><a href="category?kind=3">Sandals</a></li>
				<li><a href="category?kind=4">Sneakers</a></li>
				<li><a href="category?kind=5">Sleeper</a></li>
				<li><a href="category?kind=6">On sale</a></li>
			</ul>
		</nav>
		<div class="clear"></div><br>
	</header>

