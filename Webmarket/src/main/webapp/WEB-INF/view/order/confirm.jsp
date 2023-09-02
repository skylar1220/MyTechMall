<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    

<html>
<head>
<title>주문 완료</title>
</head>
<body>
	<jsp:include page="../../../menu.jsp" />
	
      	<section class="py-3 text-center "  style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
		background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">주문완료</h1>
	      </div>
	    </div>
	 </section>
      
	<div class="row" >
	<div class="container mx-auto my-2" style="width: 85%;">
      <div class="container border my-3 rounded-5 text-center">
		<div class="text-center my-5">
			<h1>주문이 완료되었습니다.</h1>
			<br>
			<h3>배송번호 ${s.shippingId}  /   주문일자:${s.orderDate}</h3>
		</div>
		</div>
		</div>
	</div>

	<div class="row my-2" >
	<div class="container" style="width: 85%;"> 
			<table class="table table-hover table-striped ">
				<tr class="table-primary ">
					<th>주문번호</th>
					<th>상품</th>
					<th>가격</th>
					<th>수량</th>
					<th>소계</th>
				</tr>
				<c:forEach var="ov" items="${orderViewList}">
					<tr>
						<td>${ov.orderDetailId}</td>
						<td>${ov.pname}</td>
						<td><fmt:formatNumber value="${ov.unitPrice}"/>원</td>
						<td>${ov.quantity}</td>
						<td><fmt:formatNumber value="${(ov.unitPrice) * (ov.quantity)}"/>원</td>
					</tr>
				</c:forEach>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th>총액</th>
					<th><fmt:formatNumber value="${s.sum}"/>원</th>
				</tr>
			</table>
		</div>
	</div>
	
	<div class="row  my-2" >
	<div class="container mx-auto" style="width: 85%;">
      <div class="container border my-3 rounded-5 text-center">
      <div class="row mt-4" >
		<form id="confirm" action="confirm" class="form-horizontal" method="post">
			<input hidden name="shippingCartIds" value="${s.shippingId}" /> 
			<div class="form-group row py-2">
				<label class="col-12 col-sm-4 align-self-center">수령인</label>
				<div class="col-12 col-sm-5 align-self-center">
					<input name="name" type="text" class="form-control" readonly value="${s.name}"  />
				</div>
			</div>
			<div class="form-group row py-2">
				<label class="col-12 col-sm-4 align-self-center">연락처</label>
				<div class="col-12 col-sm-5 align-self-center">
					<input name="phone" type="text" class="form-control" readonly value="${s.phone}"/>
				</div>
			</div>
			<div class="form-group row py-2">
				<label class="col-12 col-sm-4 align-self-center">우편번호</label>
				<div class="col-12 col-sm-5 align-self-center">
					<input name="postcode" type="text" class="form-control"readonly value="${s.postcode}"  />
				</div>
			</div>
			<div class="form-group row py-2">
				<label class="col-12 col-sm-4 align-self-center">배송지 주소</label>
				<div class="col-12 col-sm-5 align-self-center">
					<input name="address" type="text" class="form-control" readonly value="${s.address}"  />
				</div>
			</div>
			<div class="form-group row py-2">
				<label class="col-12 col-sm-4 align-self-center">배송메모</label>
				<div class="col-12 col-sm-5 align-self-center">
					<input name="memo" type="text" class="form-control" readonly value="${s.memo}" />
				</div>
			</div>
			<input hidden="hidden" name="orderDate" value="${date}" />
			<input hidden="hidden" name="dateForId" value="${dateForId}" />
			<input hidden="hidden" name="sum" value="${sum}" />
			
			<div class="form-group  row pt-3">
				<div class="col-12  mt-3">
                    <a href="/product/list" class="btn btn-secondary" role="button">계속 쇼핑하기</a>
					<a href="/welcome" class="btn btn-secondary" role="button">홈으로</a>
               	</div> 
               	</div>	
               	
               	
		</form>
	</div>
	</div>
	</div>
	</div>
	
</body>
</html>
