<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>

<title>상품 목록</title>
</head>
<body>
	<jsp:include page="/menu.jsp" />
	
    <section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <c:set var = "catego" value="${ (empty param.c)? '상품목록' :  param.c}  "  />
				<h1 class="display-4 fw-bolder my-5 text-center">${catego}</h1>
	      </div>
	 </section>
      

	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<!-- 검색창 -->
			<div class="d-flex flex-row-reverse">
				<form class="row g-3">
					<div class="col-auto" style="padding-right: 0px">
						<select name="f" class="form-select form-select" > <!-- 키:f, 값: pname, des -->
							<!-- <option selected>선택</option> -->
							<option  ${(param.f == "pname")? "selected" : ""} value="pname">상품명</option>
							<option  ${(param.f == "descriptions")? "selected" : ""} value="descriptions">상품설명</option>
						</select>
					</div>
					<div class="col-auto" style="padding-right: 0px">
						<label for="search" class="visually-hidden">search</label> 
						<input name="q" type="text" class="form-control" value="${param.q}"  placeholder="search">
					</div>	 <!-- list?f=pname&q=갤럭시 -->
					<div class="col-auto">
						<input type="submit" class="btn btn-primary mb-3" value="search"></input>
					</div>
				</form>
			</div>

			<!-- 상품 카드 -->
			<div class="row gx-4 gx-lg-5 row-cols-3 justify-content-center">
				<c:forEach var="p" items="${plist}">
					<div class="g-col-3 mb-5">
					
						<div class="card h-100">
						<a href="/product/detail?id=${p.productId}" style="text-align: center;">
							<img class="card-img-top" src="/img/${p.filename}" alt="..." 
							 style="width: 80%;  margin: auto;" />
						</a> 


							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder" ><a href="/product/detail?id=${p.productId}">${p.pname}</a></h5>
									<!-- Product price-->
									<p><fmt:formatNumber value="${p.unitPrice}" />원</p>
									
									<p style="color: gray; font-size: small;" >리뷰 ${p.cmtCount}</p>
								</div>
							</div>

						</div>
					</div>

				</c:forEach>

			</div>



			<!-- 페이징 -->
			<nav aria-label="Page navigation example">
			
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

				<c:set var="page" value="${ (empty param.p)?1 : param.p}" />
				
				<c:set var="startNum" value="${page-(page-1)%5}" />
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/6), '.') }" />

				<ul class="pagination justify-content-center">

					<c:if test="${startNum-5>=1}">
						<li class="page-item"><a class="page-link"
							href="?p=${startNum-5}&t=&q=&c=">Previous</a></li>
					</c:if>
					<c:forEach var="i" begin="0" end="4">
						<c:if test="${startNum+i <=lastNum }">
							<li class="page-item ${ (page == (startNum+i))?  'active' : '' } ">
							<a class="page-link" 
							href="?p=${startNum+i}&f=${param.f}&q=${param.q}&c=${param.c}">${startNum+i}</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${startNum+5<=lastNum}">
						<li class="page-item"><a class="page-link"
							href="?p=${startNum+5}&t=&q=&c=">Next</a></li>
					</c:if>

				</ul>
			</nav>

		</div>
	</section>

	<hr>

	<jsp:include page="../footer.jsp" />
</body>
</html>
