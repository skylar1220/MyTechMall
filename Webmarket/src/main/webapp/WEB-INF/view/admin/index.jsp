<%@ page contentType="text/html; charset=utf-8"%>

<html>
<head>
<head>
	<title>관리자홈</title>
	<!--  CSS  -->
	<link rel="stylesheet" href="../../resources/css/styles.css"/>
	<link rel="stylesheet" href="../../resources/css/bootstrap.min.css" />
	<!--   JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<jsp:include page="../../../menu.jsp" />
	
	<header class="bg-dark py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">관리자 페이지</h1>
			</div>
		</div>
	</header>
	
	<div class="container" style="margin-top: 20px;">
		<div class="text-center">
			<a class="btn btn-primary" href="#" >상품 관리</a>
		</div>
	</div>	
		<hr>
	
	<jsp:include page="../footer.jsp" />
</body>
</html>