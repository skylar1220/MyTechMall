<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>


<title>주문 관리</title>
</head>
<body>
	<jsp:include page="/menu.jsp" />
	
     	<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">주문 관리</h1>
	      </div>
	    </div>
	 </section>

	<section class="py-5 mx-5" >
			<div class="row my-3 mx-5">
               	<div class="col-12 col-md-12 col-sm-12 my-3 mx-1">
               		<c:forEach var="s" items="${shippingList}">
               		<div class="row col-12 col-md-12 col-sm-12 container border my-3 rounded-5" style="float: none; margin: 0 auto;">
               			
               			<div class ="col-4 col-md-4 col-sm-4 my-auto">
                			<div class="row my-3">
                				<div class="col-lg-4 col-md-6  col-sm-12 col-12 font-weight-bold" >
                				주문일시
                				</div>
                				<div class="col-lg-8 col-md-6  col-sm-12 col-12 ">
                				 ${s.orderDate}
                				</div>
                			</div>	
                			<div class="row my-3">
                				<div class="col-lg-4 col-md-6 col-sm-12 col-12 font-weight-bold " >
                				주문번호
                				</div>
                				<div class=" col-lg-8 col-md-6 col-sm-12 col-12 ">
                				  ${s.shippingId}
                				</div>
                			</div>	
                			<div class="row my-3">
                				<div class="col-lg-4 col-md-6 col-sm-12 col-12 font-weight-bold " >
                				요청 메시지
                				</div>
                				<div class=" col-lg-8 col-md-6 col-sm-12 col-12 ">
                				  ${s.memo}
                				</div>
                			</div>	
               			</div>
               			<div class ="col-4 col-md-4 col-sm-4  my-auto">
                			<div class="row my-3">
                				<div class="col-lg-3 col-md-6  col-sm-12 col-12 font-weight-bold" >
                				수령인
                				</div>
                				<div class="col-lg-8 col-md-6  col-sm-12 col-12 ">
                				 ${s.name}
                				</div>
                			</div>	
                			<div class="row my-3">
                				<div class="col-lg-3 col-md-6 col-sm-12 col-12 font-weight-bold " >
                				연락처
                				</div>
                				<div class=" col-lg-8 col-md-6 col-sm-12 col-12 ">
                				  ${s.phone}
                				</div>
                			</div>	
                			<div class="row my-3">
                				<div class="col-lg-3 col-md-6 col-sm-12 col-12 font-weight-bold " >
                				배송 주소
                				</div>
                				<div class=" col-lg-8 col-md-6 col-sm-12 col-12 ">
                				  ${s.address}
                				</div>
                			</div>	
               			</div>
               			
               			<div class ="col-4 col-md-4 col-sm-4  my-auto">
               			<form method="post" action="list">
                			<div class="row my-3">
                				<div class="col-lg-4 col-md-6  col-sm-12 col-12 font-weight-bold" >
                				배송상태
                				</div>
                				<div class="col-lg-7 col-md-6  col-sm-12 col-12 ">
	                				 <c:if test="${s.status == 'prepare'}">
	                				 배송준비중
	                				 </c:if>
	                				 <c:if test="${s.status == 'shipping'}">
	                				 배송중
	                				 </c:if>
	                				 <c:if test="${s.status == 'done'}">
	                				 배송완료
	                				 </c:if>
                				</div>
                			</div>
                				
                			<div class="row my-3 align-items-center">
                				<div class="col-lg-4 col-md-6 col-sm-12 col-12 font-weight-bold " >
                				상태변경
                				</div>
                				<div class=" col-lg-7 col-md-6 col-sm-12 col-12 ">
                					 <input hidden name = "shippingId"  value="${s.shippingId}" >
                					 <input hidden name = "page"  value="${ (empty param.p)?1 : param.p}" >
	                				 <select id="status"  name = "status" class="form-select">
	                				 	<option value="prepare"   ${ (s.status == 'prepare') ?  "selected"  : "" }  >배송준비중</option>
	                				 	<option  value="shipping" ${ (s.status == 'shipping') ?  "selected"  : "" }  >배송중</option>
	                				 	<option  value="done"     ${ (s.status == 'done') ?  "selected"  : "" } >배송완료</option>
	                				 </select>
                				</div>
                			</div>	
                			
                			<div class="row my-3">
	                			<div class="col-lg-12 col-md-12 col-sm-12 col-12">
									<a href="/admin/order/delete?shippingId=${s.shippingId}" class="btn btn-secondary px-5" role="button">주문삭제</a>
									<input type="submit" class="btn btn-primary px-5" value="변경적용">
							</div>
                			</div>	
               			</form>
               			</div>
             		</div>
             		</c:forEach>
                 </div>
             </div>

				<!-- 페이징 -->
				<c:set var="page" value="${ (empty param.p)?1 : param.p}" />
				<c:set var="startNum" value="${page-(page-1)%5}" />
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/6), '.') }" />
				<nav aria-label="Page navigation example">
				
				  <ul class="pagination justify-content-center">
					  <c:if test="${startNum-5>=1}">
							<li class="page-item"><a class="page-link"
								href="?p=${startNum-5}">Previous</a></li>
						</c:if>
						<c:forEach var="i" begin="0" end="3">
							<c:if test="${startNum+i <=lastNum }">
								<li class="page-item ${ (page == (startNum+i))?  'active' : '' } ">
								<a class="page-link" 
								href="?p=${startNum+i}">${startNum+i}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${startNum+5<=lastNum}">
							<li class="page-item"><a class="page-link"
								href="?p=${startNum+5}">Next</a></li>
						</c:if>
				  </ul>
				</nav>
	</section>

	<hr>

	<jsp:include page="../../footer.jsp" />
</body>
</html>
