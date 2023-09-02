<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/menu.jsp" />

	<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">회원정보</h1>
	      </div>
	    </div>
	 </section>
      

	<div class="container py-5" align="center">
		<h2 ${ (param.m == "add")? "" : "hidden" } class='alert alert-success'> 회원가입을 축하드립니다.</h2>
		<h2 ${ (param.m == "edit")? "" : "hidden" } class='alert alert-success'> 회원정보가 정상적으로 수정되었습니다.</h2>
		<h2 ${ (param.m == "delete")? "" : "hidden" } class='alert alert-danger'> 회원정보가 삭제되었습니다.</h2>
	</div>
</body>
</html>