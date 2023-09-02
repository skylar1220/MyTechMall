<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Login</title>
</head>

<%
String memberId = (String)session.getAttribute("memberId");
boolean memberT = false;
if(memberId!=null) {
	memberT = true;
}
%>   
<script type="text/javascript">
function loginSubmit(){
		if(<%=memberT%>){
			if ( confirm("회원 로그인이 되어있습니다. 로그아웃하시겠습니까?") ) {
				 $.ajax({
	                url: "/admin_login_Process.jsp", // 로그아웃 처리를 수행하는 서블릿 경로
	                type: "GET",
	                success: function(response) {
	                    // 세션 무효화 후 필요한 동작 수행 (예: 폼 제출)
	                    $("#login").submit();
	                }
	            });
			} else {
				return;
			}
	 	}
		else{
			$("#login").submit();
		}	
};
</script>

<body>
	<jsp:include page="../../menu.jsp" />

    <section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">관리자 로그인</h1>
	      </div>
	    </div>
	 </section>
	
	
		<div class="container">
             <div class="row">
                <div class="col-lg-12 col-md-12">
	                <div class="container mx-auto my-5" style="width: 80%;">
	                <div class="container border my-5 rounded-5">
	                <div class="container mx-auto my-5 pt-2 " style="width: 80%;">
	                        	<form action="j_security_check" id="login" method="post"  class="form-signin">
			                        	<c:if test="${ param.error != null }">
											<div class='alert alert-danger'>아이디와 비밀번호를 확인해 주세요</div>
										</c:if>
	                        			<div class="form-group row my-3">
	                        				<input hidden id="message" type="text" value="${message}"></input>
											<label class="form-label col-12 col-sm-4  " >
						                   		아이디
											</label>
											<div class="col-12 col-sm-5 align-self-center">
												<input type="text" class="form-control" placeholder="ID" name="j_username"  required autofocus>
											</div>	
										</div>
										<div class="form-group row  my-3">
											<label class="form-label col-12 col-sm-4  " >
						                   		비밀번호
											</label>
											<div class="col-12 col-sm-5 align-self-center">
												<input type="password" class="form-control" placeholder="Password" name="j_password"  required >
											</div>	
										</div>
	                        			<div class="form-group  row">
						                	<div class="col-12 mt-3 mb-3  text-center">
							                    <button class="btn btn btn-lg btn-success" onclick="loginSubmit()" type="submit" >로그인</button>
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