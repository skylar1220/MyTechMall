<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>

<title>상품 수정</title>
</head>
<body>

	<jsp:include page="../../../../menu.jsp" />
	
	<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">상품수정</h1>
	      </div>
	    </div>
	 </section>
  
	<section class="container py-5 mx-5 my-5" >
		<div class="container px-4 px-lg-5 mt-5">
		<!-- 검색창 -->
		<div class="d-flex flex-row-reverse">
			<form class="row g-3">
				<div class="col-auto" style="padding: 2px">
					<select name="f" class="form-select form-select" > 
						<!-- <option selected>선택</option> -->
						<option  ${(param.f == "pname")? "selected" : ""} value="pname">상품명</option>
						<option  ${(param.f == "descriptions")? "selected" : ""} value="descriptions">상품설명</option>
					</select>
				</div>
				
				<div class="col-auto" style="padding: 2px">
					<label for="search" class="visually-hidden">search</label> 
					<input name="q" type="text" class="form-control" value="${param.q}"  placeholder="search">
				</div>	 <!-- list?f=pname&q=갤럭시 -->
				<div class="col-auto" style="padding: 2px">
					<input type="submit" class="btn btn-dark mt-auto" value="search" style="margin-right: 20px"></input>
					
				</div>
			</form>
		</div>
			
		<!-- 일괄 버튼, 상품카드 체크 연동 폼  -->
		<form action="" method="post">
			<!-- 일괄 버튼 -->
			<div >
				<div class="col-auto" >
					<input type="submit" class="btn btn-secondary mb-3" name="cmd" value="선택공개"></input>
					<input type="submit" class="btn btn-dark mb-3" name="cmd" value="선택삭제"></input>
					<!-- <a class="btn btn-primary mb-3" href="./add">상품등록</a> -->
					<c:set var = "ids" value="" />
					<c:forEach var="p" items="${plist}">
						<c:set var="ids" value="${ids} ${p.productId }"  />
					</c:forEach>
					<input type="hidden" name="ids" value="${ids}">
				</div>
				
			</div>

			<!-- 상품 카드 -->
			<div class="row gx-4 gx-lg-5 row-cols-3 justify-content-center">
				<c:forEach var="p" items="${plist}">
					<div class="g-col-3 mb-5">
						
						<!-- 체크 -->
						<div class="form-check form-check-inline" >
							<c:set var="open" value="" />
							<c:if test ="${p.pub}">
								<c:set var="open" value="checked" />
							</c:if>
							  	<input class="form-check-input" type="checkbox" name="open-id" ${open}  value="${p.productId}">
							  	<label class="form-check-label">공개</label>
						</div>
						<div class="form-check form-check-inline" style="margin-left: 5px">
						  	<input class="form-check-input" type="checkbox" name="del-id" value="${p.productId}">
						  	<label class="form-check-label">삭제</label>
						</div>
						
						<div class="card h-100">
							<a href="./edit?id=${p.productId}" style="text-align: center;">
								<img class="card-img-top" src="/resources/images/${p.filename}" alt="..."
									 style="width: 80%;  margin: auto;"  />
							</a> 
							
							<div class="card-body p-4" style="height: 100px;">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder"><a href="./edit?id=${p.productId}">${p.pname}</a></h5>
									<!-- Product price-->
									<p><fmt:formatNumber value="${p.unitPrice}" />원</p>
									
									<p style="color: gray; font-size: small;" >리뷰 ${p.cmtCount}</p>
								</div>
							</div>

							<%-- <!-- 버튼들: 나중에 어떻게 할지 생각 -->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="./product?id=${p.productId}">수정</a> 
									<a class="btn btn-outline-dark mt-auto" href="#" onclick="addToCart()">삭제</a>
								</div>
							</div> --%>
							
						</div>
					</div>
				</c:forEach>
			</div>
		</form>
			
			<!-- 페이징 스타일-->
			<style>
				.page-link {
					color: #000;
					background-color: #fff;
					border: 1px solid #ccc;
				}
				
				.page-item.active .page-link {
					z-index: 1;
					color: #555;
					font-weight: bold;
					background-color: #f1f1f1;
					border-color: #ccc;
				}
				
				.page-link:focus, .page-link:hover {
					color: #000;
					background-color: #fafafa;
					border-color: #ccc;
				}
			</style>

			<!-- 페이징 -->
			<nav aria-label="Page navigation example">

				<c:set var="page" value="${ (empty param.p)?1 : param.p}" />
				<c:set var="startNum" value="${page-(page-1)%5}" />
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/6), '.') }" />

				<ul class="pagination justify-content-center">

					<c:if test="${startNum-5>=1}">
						<li class="page-item"><a class="page-link"
							href="?p=${startNum-5}&t=&q=">Previous</a></li>
					</c:if>
					<c:forEach var="i" begin="0" end="4">
						<c:if test="${startNum+i <=lastNum }">
							<li class="page-item ${ (page == (startNum+i))?  'active' : '' } ">
							<a class="page-link" 
							href="?p=${startNum+i}&f=${param.f}&q=${param.q}">${startNum+i}</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${startNum+5<=lastNum}">
						<li class="page-item"><a class="page-link"
							href="?p=${startNum+5}&t=&q="">Next</a></li>
					</c:if>

				</ul>
			</nav>

		</div>
	</section>

	<hr>

	<jsp:include page="../../footer.jsp" />
</body>
</html>
