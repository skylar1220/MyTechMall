<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script src="https://code.jquery.com/jquery-3.7.0.js"></script>

<html>
<head>

<title>Login</title>
</head>
<%-- if(<%=adminT%>){
<%
boolean adminT = request.isUserInRole("admin");
%>
		if ( confirm("관리자 로그인이 되어있습니다. 로그아웃하시겠습니까?") ) {
			$.ajax({
                url: "/login_Process.jsp", // 로그아웃 처리를 수행하는 서블릿 경로
                type: "GET",
                success: function(response) {
                    // 세션 무효화 후 필요한 동작 수행 (예: 폼 제출)
                   alert("관리자 로그아웃되었습니다.")
                }
            });
			
			/* let formData = $("form").serialize(); 
			if($("#login")[0].checkValidity()){
				event.preventDefault();
				$.ajax({
					url: '/member/login' ,
					type: 'POST',
					data : formData,
					success: function(){
						$("#login").submit();
					},
					error:function(){
						alert("아이디와 비밀번호를 확인해주세요.");
						document.location.href="/member/login";
					}
				});
			} */
		} else {
		 	return;
		}
	} else{ --%>
<!-- <script type="text/javascript">
function loginSubmit(){
	let formData = $("form").serialize(); 
	if($("#login")[0].checkValidity()){
		$.ajax({
			url: '/member/login' ,
			type: 'POST',
			data : formData,
			dataType: "json",
			success: function(data){
				alert(data)
				if(data == "admin"){
					alert("현재 계정을 로그아웃하고 관리자 계정으로 로그인합니다.");
					$("#login").submit();
				}
				$("#login").submit();
			},
			error:function(){
				alert("아이디와 비밀번호를 확인해주세요.");
				document.location.href="/member/login";
			}
		});
	}
} 
</script> -->
<script type="text/javascript">
function loginSubmit(){
	let formData = $("form").serialize(); 
		$.ajax({
			url: '/member/login' ,
			type: 'POST',
			data : formData,
			success: function(data){
				alert(data)
				document.location.href="/welcome";
			},
			error:function(){
				alert("아이디와 비밀번호를 확인해주세요.");
				document.location.href="/member/login";
			}
		});
} 
</script>

<body>
	<jsp:include page="../../../menu.jsp" />
    
	<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">로그인</h1>
	      </div>
	    </div>
	 </section>
      
	
	<div class="container">
             <div class="row">
                <div class="col-lg-12 col-md-12">
	                <div class="container mx-auto my-5" style="width: 80%;">
	                <div class="container border my-5 rounded-5">
	                <div class="container mx-auto my-5 pt-5 " style="width: 80%;">
	                        	<form action="login" id="login" method="post"  class="form-horizontal">
	                        			<div class="form-group row my-3">
	                        				<input hidden id="message" type="text" value="${message}"></input>
											<label class="form-label col-12 col-sm-4  " >
						                   		아이디
											</label>
											<div class="col-12 col-sm-5 align-self-center">
												<input type="text" class="form-control" placeholder="ID" name="memberId"  id="memberId"  required autofocus>
											</div>	
										</div>
										<div class="form-group row  my-3">
											<label class="form-label col-12 col-sm-4  " >
						                   		비밀번호
											</label>
											<div class="col-12 col-sm-5 align-self-center">
												<input type="password" class="form-control" placeholder="Password" name="password"  id="password" required >
											</div>	
										</div>
										<%-- 
										<div class="form-group row">
											<div ${ (login == "fail")? '' : 'style="display: none;"' }  class="alert alert-danger" role="alert"  class="mb-3">
											  아이디와 비밀번호를 확인해주세요.
											</div>
										</div>
										 --%>
	                        			<div class="form-group  row">
						                	<div class="col-12 mt-3 mb-3  text-center">
							                    <button class="btn btn btn-lg btn-success" type="submit" onclick="loginSubmit()">로그인</button>
						                	</div> 
					                	</div>
	                        	
	                        	</form>
	                            </div>
	                        </div>
	                    </div>
	                    </div>
                </div>
    </div>
</body>
</html>