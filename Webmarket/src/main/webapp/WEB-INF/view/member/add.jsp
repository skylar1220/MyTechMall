<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<script type="text/javascript">
	function addSubmit(){
		let password =  $("form").find("#password").val();
		let password_confirm =  $("form").find("#password_confirm").val();
		let formData = $("form").serialize(); 
		
		if(password!=password_confirm){
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		} else{
			$.ajax({
				url: '/member/add' ,
				type: 'POST',
				data : formData,
				success: function(response){
					$("form").submit();
					window.location.href = 'http://localhost:8080/member/welcome?m=add' ;
				},
				error:function(){
					alert('이미 가입된 아이디입니다. 다른 아이디를 입력해주세요.')
				}
			}); 
		}
	}
</script>
<title>회원가입</title>
</head>
<body>
	<jsp:include page="/menu.jsp" />

	<section class="py-3 text-center " 
	style="height: 290px; background-size: cover; text-shadow: black 0.1em 0.1em 0.2em; color:white;
	background-image: url('https://velog.velcdn.com/images/dlguswl936/post/3898dd29-16b6-4dd9-8e1f-4c56eefcae76/image.jpg'); ">
	    <div class="row py-lg-5">
	      <div class="col-lg-6 col-md-8 mx-auto">
	       <h1 class="display-4 fw-bolder my-5 text-center">회원가입</h1>
	      </div>
	    </div>
	 </section>
      
	<div class="container my-5">
     <div class="col-lg-12 col-md-12">
     <div class="container mx-auto" style="width: 80%;">
      <div class="container border my-3 rounded-5 text-center">
       <div class="row my-5" >
		<form name="addForm" class="form-horizontal" action="add" method="post"> 
				<div class="form-group row pt-5 pb-2">
					<div class="col-12 col-sm-4 align-self-center " >
                   		아이디
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input id="memberId"  name="memberId"  type="text" class="form-control" required autofocus  maxlength="20" >
					</div>	
				</div>
				<div class="form-group row py-2">
					<div class="col-12 col-sm-4 align-self-center " >
                   		비밀번호
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input name="password" id="password"  type="password"  class="form-control" required minlength="4" maxlength="20" >
					</div>		
				</div>
				<div class="form-group row py-2">
					<div class="col-12 col-sm-4 align-self-center " >
                   		비밀번호 확인
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input name="password_confirm" id="password_confirm"  type="password"  class="form-control" required minlength="4" maxlength="20" >
					</div>		
				</div>
				<div class="form-group row py-2">
					<div class="col-12 col-sm-4 align-self-center " >
                   		이름
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input   name="name"  type="text"  class="form-control" required maxlength="20">
					</div>		
				</div>
				
				<div class="form-group row py-2">
					<div class="col-12 col-sm-4 align-self-center " >
					  이메일            		
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input type="text" name="mail" id="mail"  maxlength="50"  class="form-control"  required>
					</div>		
				</div>
				
				<div class="form-group row py-2">
					<div class="col-12 col-sm-4 align-self-center " >
					  주소          		
					</div>
					<div class="col-12 col-sm-5 align-self-center">
						<input name="address"  type="text"  id=address  class="form-control" required  maxlength="30">
					</div>		
				</div>
				
				<div class="form-group  row pt-3">
				<div class="col-12  mt-3">
                    <input type="button" class="btn btn-primary " onclick="addSubmit()" value="등록 ">
               	</div> 
               	</div>		
		</form>
		</div>
		</div>
		</div>
		</div>
	</div>
</body>
</html>