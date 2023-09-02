<%@page import="service.CartService"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="" />
<meta name="author" content="" />
<title>Shop Homepage - Start Bootstrap Template</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
 <!-- 이거 오류나는 이유
 <link rel="stylesheet" href="../resources/css/bootstrap.min.css" /> -->
 <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
</head>
<script>
 $(document).ready(function(){
        $.ajax({
           type:"GET",
           url : "/menuProcess.jsp",
           success:function(data){
              $("#cartNum").text(data.trim());
           }
        });
 })
</script>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg  navbar-dark" style="background-color:#8294C4;">
	<div class="container px-4 px-lg-5">
	<!-- <button id="test" onclick="test()">test</button>  -->
		<div class="navbar-header">
			<a class="navbar-brand" href="/welcome">Home</a>
		</div>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto  mb-2 mb-lg-0 ms-lg-4">
				<c:choose>
					<c:when test="${empty memberId}"> <!-- ?? -->
						<li class="nav-item"><a class="nav-link"
							href="/member/login">로그인</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/member/add">회원 가입</a>
					</c:when>
					<c:otherwise>
						<li style="padding-top: 7px; color: silver;">
							<a class="nav-link " href="/member/edit">[${memberId}님]</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" id="btn_logout" href="/member/logout"  >로그아웃 </a>
						</li>
					</c:otherwise>
				</c:choose>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">상품
						목록</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item"
							href="/product/list">전체상품</a></li>
						<li><hr class="dropdown-divider" /></li>
						<li><a class="dropdown-item"
							href="/product/list?c=Phone">휴대폰</a></li>
						<li><a class="dropdown-item"
							href="/product/list?c=Notebook">노트북</a></li>
						<li><a class="dropdown-item"
							href="/product/list?c=Tab">태블릿PC</a></li>
						<li><a class="dropdown-item"
							href="/product/list?c=Audio">음향가전</a></li>
						<li><a class="dropdown-item"
							href="/product/list?c=Other">기타</a></li>
					</ul></li>
				
				<%
				boolean adminT = request.isUserInRole("admin");
				%>
				<c:set var="adminTrue" value="<%=adminT%>" />
				<c:choose>
				<c:when test="${adminTrue}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">관리자
							메뉴</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/admin/product/list">상품 수정</a></li>
							<li><a class="dropdown-item" href="/admin/product/add">상품 등록</a></li>
							<li><a class="dropdown-item" href="/admin/order/list">주문 관리</a></li>
						</ul></li>
					<li class="nav-item">
						<a class="nav-link" href="/admin_logout"  >관리자 로그아웃 </a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="nav-item">
						<a class="nav-link" id="btn_logout" href="/admin/product/list"  >관리자 로그인</a>
					</li>
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<a class="btn btn-outline-light" href="/cart/list">
			<i class="bi-cart-fill me-1"></i> 장바구니 
			<span id="cartNum" class="badge bg-light text-black ms-1 rounded-pill">${cartCount}</span>
		</a>
	</div>
</nav>
</body>