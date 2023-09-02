<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>장바구니</title>
</head>
 <script src="https://code.jquery.com/jquery-3.7.0.js"></script>

<script type="text/javascript"> 
	$(document).ready(function(){
		if($("#message").val()!=""){
			alert($("#message").val());
			document.location.href="/member/login";
			}
	});
</script>

<body>
	<jsp:include page="../../../menu.jsp" />
	
	<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">장바구니</h1>
	      </div>
	    </div>
	 </section>
      
     <input id="message" hidden value="${message}"> 
      
	<div class="container mx-auto my-5" style="width: 85%;">
             <div class="row">
                <div class="col-lg-12 col-md-12">
					<form action="/order/shippingInfo" method="post" >
						<div class="row">
							<table width="100%">
								<tr>
									<!-- <td align="left"><a href="./deleteCart.jsp?cartId=" class="btn btn-danger">삭제하기</a></td> -->
									<td align="right"><button class="btn btn-success">주문하기</button></td>
								</tr>
							</table>
						</div>
						<div style="padding-top: 50px">
							<table class="table table-hover table-striped ">
								<tr class="table-primary ">
									<th>상품</th>
									<th>가격</th>
									<th>수량</th>
									<th>소계</th>
									<th>비고</th>
								</tr>
								<c:forEach var="c" items="${cartlist}">
								<c:set var="sum" value="${sum + (c.unitPrice) * (c.quantity) }" />
								<c:set var="cartIds" value="${cartIds},${c.cartId}"  /> <!-- ,7,8,9-->
									<tr>
										<td>${c.productId} - ${c.pname}</td>
										<td><fmt:formatNumber value="${c.unitPrice}"/>원</td>
										<td>${c.quantity}</td>
										<td><fmt:formatNumber value="${(c.unitPrice) * (c.quantity)}"/>원</td>
										<td><a href="/cart/delete?id=${c.cartId}" class="badge text-bg-danger">삭제</a></td>
									</tr>
								</c:forEach>
								<tr>
									<th></th>
									<th></th>
									<th>총액</th>
									<th><fmt:formatNumber value="${sum}"/>원</th>
									<th></th>
								</tr>
							</table>
							<!-- 최종으로 주문할 상품 리스트 넘기기  -->
							<input type="text" hidden  name="shippingCartIds" value="${cartIds}">  
							<a href="/product/list" class="btn btn-secondary"> &laquo; 쇼핑 계속하기</a>
						</div>
						<hr>
					</form>
			</div>
			</div>
			</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>
