<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    

<html>
<head>
<title>배송 정보</title>

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">
function kakaopay(){
	IMP.init('imp18070471');
	IMP.request_pay({		
		pg : 'kakaopay',
		name : 'MyTechMall 주문 결제' ,         // 결제창에 뜨는 이름
		amount : ${sum} 	//가격 
	},function(rsp){
		var options = "";
		if(rsp.success){
			alert( "결제 완료") ;
			$("#confirm").submit();
        }else{
        	alert( "결제 실패") ; 
        	document.location.href="/order/shippingInfo";
        }
	})
};
</script>
</head>
<body>
	<jsp:include page="../../../menu.jsp" />
	
	<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">주문 / 배송</h1>
	      </div>
	    </div>
	 </section>
      
	
	<div class="container"  style="width: 85%;"> 
			<div style="padding-top: 50px">
			<table class="table table-hover table-striped ">
				<tr class="table-primary ">
					<th>상품</th>
					<th>가격</th>
					<th>수량</th>
					<th>소계</th>
					<th>비고</th>
				</tr>
				<c:forEach var="c" items="${shippingCartViewList}">
				<c:set var="sum" value="${sum + (c.unitPrice) * (c.quantity) }" />
					<tr>
						<td>${c.productId} - ${c.pname}</td>
						<td><fmt:formatNumber value="${c.unitPrice}"/>원</td>
						<td>${c.quantity}</td>
						<td><fmt:formatNumber value="${(c.unitPrice) * (c.quantity)}"/>원</td>
						<td></td>
					</tr>
				</c:forEach>
				<!-- if (cartList == null)
						cartList = new ArrayList<Product>(); -->
				<tr>
					<th></th>
					<th></th>
					<th>총액</th>
					<th><fmt:formatNumber value="${sum}"/>원</th>
				</tr>
			</table>
		</div>
	
<hr>
      <div class="container border my-3 rounded-5 text-center">
      <div class="row mt-4" >
		<form id="confirm" action="confirm" class="form-horizontal" method="post">
			<%-- <input type="hidden" name="cartId" value="<%=request.getParameter("cartId")%>" /> --%>
			<div class="form-group row py-2">
				<label class="col-12 col-sm-4 align-self-center">수령인</label>
				<div class="col-12 col-sm-5 align-self-center">
					<input name="name" type="text" class="form-control" />
				</div>
			</div>
			<div class="form-group row py-2">
				<label class="col-12 col-sm-4 align-self-center">연락처</label>
				<div class="col-12 col-sm-5 align-self-center">
					<input name="phone" type="text" class="form-control" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" />
				</div>
			</div>
			<div class="form-group row py-2">
				<label class="col-12 col-sm-4 align-self-center">우편번호</label>
				<div class="col-12 col-sm-5 align-self-center">
					<input name="postcode" type="text" class="form-control" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" />
				</div>
			</div>
			<div class="form-group row py-2">
				<label class="col-12 col-sm-4 align-self-center">배송지 주소</label>
				<div class="col-12 col-sm-5 align-self-center">
					<input name="address" type="text" class="form-control" />
				</div>
			</div>
			<div class="form-group row py-2">
				<label class="col-12 col-sm-4 align-self-center">배송메모</label>
				<div class="col-12 col-sm-5 align-self-center">
					<input name="memo" type="text" class="form-control" />
				</div>
			</div>
			<input hidden="hidden" name="orderDate" value="${date}" />
			<input hidden="hidden" name="dateForId" value="${dateForId}" />
			<input hidden="hidden" name="sum" value="${sum}" />
			
			<div class="form-group  row pt-3">
				<div class="col-12  mt-3">
                    <button	type="submit" class="btn btn-primary" onclick="kakaopay()">결제</button>
					<a href="/cart/list?id=${cartId}" class="btn btn-secondary" role="button"> 취소 </a>
               	</div> 
               	</div>	
               	
               	
			<input type="text" hidden  name="shippingCartIds" value="${shippingCartIds}"> 
		</form>
	</div>
	</div>
	</div>
</body>
</html>
